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
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.itude.mobile.android.util.exceptions.DataParsingException;

public final class AssetUtil
{
  private static final String TAG = "AssetUtil";
  private static AssetUtil    _instance;
  private Context             _context;

  private AssetUtil()
  {
  }

  public static AssetUtil getInstance()
  {
    if (_instance == null)
    {
      _instance = new AssetUtil();
    }

    return _instance;
  }

  public void setContext(Context context)
  {
    _context = context;
  }

  public byte[] getByteArray(String fileName) throws DataParsingException
  {
    AssetManager manager = _context.getAssets();
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    InputStream inputStream = null;

    try
    {
      inputStream = manager.open(fileName);

      int read;
      byte[] buffer = new byte[1024];

      while ((read = inputStream.read(buffer, 0, buffer.length)) != -1)
      {
        bytes.write(buffer, 0, read);
      }
      bytes.flush();

    }
    catch (Exception e)
    {
      String message = "AssetUtil.getByteArray: unable to read asset data with filename " + fileName;
      throw new DataParsingException(message, e);
    }
    finally
    {
      try
      {
        if (bytes != null) bytes.close();
      }
      catch (Exception e)
      {
        Log.w(TAG, "Unable to close stream", e);
      }

      try
      {
        if (inputStream != null) inputStream.close();
      }
      catch (Exception e)
      {
        Log.w(TAG, "Unable to close stream");
      }
    }

    if (bytes.toByteArray().length == 0) Log.w(TAG, "AssetUtil.getByteArray: file not found with fileName=");
    return bytes.toByteArray();
  }

}
