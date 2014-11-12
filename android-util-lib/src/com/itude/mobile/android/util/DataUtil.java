/*
 * (C) Copyright Itude Mobile B.V., The Netherlands
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.itude.mobile.android.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.apache.commons.codec.binary.Base64;

import android.content.Context;

import com.itude.mobile.android.util.log.MBLog;

/**
 * Utility class for methods to handle data.
 */
public final class DataUtil
{
  private static final String       TAG               = "DataUtil";

  private static DataUtil           _instance;
  private Context                   _context;

  private final Map<String, Reader> _filenameToReader = new HashMap<String, Reader>();
  private final Reader              _readerNone       = new ReadFromNone();
  private final ReadFromAll         _readerAll        = new ReadFromAll();

  /**
   * Default constructor
   */
  private DataUtil()
  {
  }

  /**
   * @return {@link DataUtil}
   */
  public static DataUtil getInstance()
  {
    if (_instance == null)
    {
      _instance = new DataUtil();
    }

    return _instance;
  }

  /**
   * Set the {@link Context} for this instance
   * 
   * @param context {@link Context}
   */
  public void setContext(Context context)
  {
    _context = context;
    AssetUtil.getInstance().setContext(_context);
    FileUtil.getInstance().setContext(_context);
  }

  /**
   * Should be called after someone creates a file otherwise
   * the file will not be seen by  {@link #readFromAssetOrFile(String)}.
   * Currently called automatically by {@link FileUtil#writeObjectToFile(Object, String)}
   * and {@link FileUtil#writeToFile(byte[], String, String)}.
   *
   * @param filename file name
   */
  public void clearReaderCachForFile(String filename)
  {
    _filenameToReader.remove(filename);
  }

  /**
   * Get the byte array from an asset or file.
   * 
   * @param filename file name
   * @return byte array
   */
  public byte[] readFromAssetOrFile(String filename)
  {
    if (_filenameToReader.containsKey(filename))
    {
      // we have previously read this file, so use the correct reader directly.
      Reader readerForThisFile = _filenameToReader.get(filename);
      return readerForThisFile.read(filename);
    }
    else
    {
      // first time we try to read this file, try all readers
      TwinResult<byte[], Reader> result = _readerAll.read(filename);
      if (result._mainResult == null)
      {
        // in future we just dont try for this file anymore
        _filenameToReader.put(filename, _readerNone);
        // and only the first time we give a message
        String message = "DataUtil.readFromAssetOrFile: unable to read file or asset data from file with name " + filename;
        MBLog.i(TAG, message);
      }
      else
      {
        // in future immediately use the correct reader for this filename
        _filenameToReader.put(filename, result._secondResult);
      }
      return result._mainResult;
    }
  }

  /**
   * Compress byte array 
   * 
   * @param uncompressed byte array
   * @return compressed byte array
   */
  public byte[] compress(byte[] uncompressed)
  {
    byte[] result = null;

    Deflater deflater = new Deflater();
    deflater.setInput(uncompressed);
    deflater.finish();

    // Create an expandable byte array to hold the compressed data. 
    // You cannot use an array that's the same size as the original because 
    // there is no guarantee that the compressed data will be smaller than 
    // the uncompressed data. 

    ByteArrayOutputStream bos = new ByteArrayOutputStream(uncompressed.length);

    // Compress the data 
    byte[] buf = new byte[1024];
    while (!deflater.finished())
    {
      int count = deflater.deflate(buf);
      bos.write(buf, 0, count);
    }
    deflater.end();

    try
    {
      bos.close();
    }
    catch (IOException e)
    {
      MBLog.w(TAG, "Unable to close stream");
    }

    // Get the compressed data 

    result = bos.toByteArray();

    return result;
  }

  /**
   * Decompress byte array 
   * 
   * @param compressed byte array
   * @return decompressed byte array
   */
  public byte[] decompress(byte[] compressed)
  {
    return decompress(compressed, 0);
  }

  public byte[] decompress(byte[] compressed, int bytesToSkip)
  {
    Inflater decompressor = new Inflater();
    decompressor.setInput(compressed, bytesToSkip, compressed.length - bytesToSkip);

    // Create an expandable byte array to hold the decompressed data 
    ByteArrayOutputStream bos = new ByteArrayOutputStream(compressed.length);

    // Decompress the data 
    byte[] buf = new byte[1024];
    while (!decompressor.finished())
    {
      try
      {
        int count = decompressor.inflate(buf);
        bos.write(buf, 0, count);
      }
      catch (DataFormatException e)
      {
        decompressor.end();
        return null;
      }
    }
    decompressor.end();
    try
    {
      if (bos != null) bos.close();
    }
    catch (IOException e)
    {
      MBLog.w(TAG, "Unable to close stream");
    }

    // Get the decompressed data 
    byte[] decompressedData = bos.toByteArray();
    return decompressedData;
  }

}
/**
 * Reader
 */
interface Reader
{
  /**
   * Read file
   * 
   * @param filename file name
   * @return byte array
   */
  byte[] read(String filename);
}

// support for encrypted xml files
// those are stored in corresponding .glb files
// note: this method should just be called with .xml filenames
class ReadFromGLB implements Reader
{
  // this cache contains xml files which are already unobfuscated
  private final Map<String, byte[]> _cacheOfXml = new HashMap<String, byte[]>();

  @Override
  public byte[] read(String filename)
  {
    if (!filename.endsWith(".xml")) return null;

    synchronized (_cacheOfXml)
    {
      // note: the cache has the XML filename as the key
      byte[] dataFromCache = _cacheOfXml.get(filename);
      if (dataFromCache != null)
      {
        return dataFromCache;
      }
      // is the xml filename in the cache with a null value?
      // in that case, it means we tried to load the .glb before but it failed (not present)
      if (_cacheOfXml.containsKey(filename)) return null;
    } // end sync block

    // first time we try to load the corresponding .glb
    String glbName = filename.substring(0, filename.lastIndexOf('.')) + ".glb";
    byte[] glbFileData = DataUtil.getInstance().readFromAssetOrFile(glbName);
    if (glbFileData != null)
    {
      // glb found, but is it valid?
      if (glbFileData.length > 4 && glbFileData[0] == '-' && glbFileData[1] == 'i' && glbFileData[2] == 't' && glbFileData[3] == 'u')
      {
        glbFileData = unobfuscate(glbFileData);
        synchronized (_cacheOfXml)
        {
          _cacheOfXml.put(filename, glbFileData);
        }
        return glbFileData;
      }
    }
    // glb not found
    // in future do not try to load the GLB as it isnt present
    synchronized (_cacheOfXml)
    {
      _cacheOfXml.put(filename, null);
    }
    return null;
  }

  // xml files can be (slightly) obfuscated at deploy time
  // obfuscation is done by:
  // step 1: convert file using base64
  // step 2: use java.util.zip.Deflator to compress
  // step 3: write a special signature (binary -itu)
  // step 4: write the compressed stream
  // this routine reverses those steps
  private byte[] unobfuscate(byte[] data)
  {
    // reverse step 3:  write a special signature (binary -itu)
    // and 
    // Reversing step 2: use java.util.zip.Deflator to compress
    data = DataUtil.getInstance().decompress(data, 4);
    // reverse step 1: convert using base64
    Base64 b64 = new Base64();

    return b64.decode(data);
  }
}
class ReadFromAsset implements Reader
{

  @Override
  public byte[] read(String filename)
  {
    try
    {
      return AssetUtil.getInstance().getByteArray(filename);
    }
    catch (Exception e)
    {
      // do not show or propagate exception,
    }
    return null;
  }

}
class ReadFromFile implements Reader
{
  @Override
  public byte[] read(String filename)
  {
    try
    {
      return FileUtil.getInstance().getByteArray(filename);
    }
    catch (Exception e)
    {
      // do not show or propagate exception,
    }
    return null;
  }
}
/**
 * A special Reader that always returns null.
 * @author Gert
 *
 */
class ReadFromNone implements Reader
{
  @Override
  public byte[] read(String filename)
  {
    return null;
  }
}

class ReadFromAll
{
  private final ReadFromAsset _fromAsset = new ReadFromAsset();
  private final ReadFromFile  _fromFile  = new ReadFromFile();
  private final ReadFromGLB   _fromGlb   = new ReadFromGLB();

  public TwinResult<byte[], Reader> read(String filename)
  {
    Reader theReaderThatRead = _fromFile;
    byte[] result = theReaderThatRead.read(filename);
    if (result == null)
    {
      theReaderThatRead = _fromAsset;
      result = theReaderThatRead.read(filename);
      if (result == null)
      {
        theReaderThatRead = _fromGlb;
        result = theReaderThatRead.read(filename);
        if (result == null) theReaderThatRead = null;
      }
    }
    return new TwinResult<byte[], Reader>(result, theReaderThatRead);
  }
}
