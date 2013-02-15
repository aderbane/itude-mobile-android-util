package com.itude.mobile.android.util.tests;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import android.test.AndroidTestCase;

import com.itude.mobile.android.util.AssetUtil;
import com.itude.mobile.android.util.FileUtil;
import com.itude.mobile.android.util.exceptions.DataParsingException;

public class FileUtilTest extends AndroidTestCase
{

  @Override
  protected void setUp() throws Exception
  {
    AssetUtil.getInstance().setContext(mContext);
    FileUtil.getInstance().setContext(mContext);

    super.setUp();
  }

  public void testAssetFileReading()
  {
    byte[] data = AssetUtil.getInstance().getByteArray("fileUtilTest/fileUtilTest.txt");

    assertNotNull(data);
    assertTrue(data.length > 0);
  }

  public void testDirectoryStorage()
  {
    String filePath = "tests/files/FileUtilTest.xml";
    String fileContents = "Hello File " + (new Date()).getTime();

    boolean success = FileUtil.getInstance().writeToFile(fileContents.getBytes(), filePath, null);
    assertTrue(success);

    byte[] data = FileUtil.getInstance().getByteArray(filePath);

    assertNotNull(data);
    assertEquals(new String(data), fileContents);
  }

  public void testFileStorage()
  {
    String filePath = "FileUtilTest.xml";
    String fileContents = "Hello File " + (new Date()).getTime();

    boolean success = FileUtil.getInstance().writeToFile(fileContents.getBytes(), filePath, null);
    assertTrue(success);

    byte[] data = FileUtil.getInstance().getByteArray(filePath);

    assertNotNull(data);
    assertEquals(new String(data), fileContents);
  }

  public void testObjectStorage()
  {
    String filePath = "/tests/files/ObjectTest.xml";
    Properties p = new Properties();
    p.put("key", "value");

    boolean success = FileUtil.getInstance().writeObjectToFile(p, filePath);
    assertTrue("Could not write object to file", success);

    Object o = FileUtil.getInstance().readObjectFromFile(filePath);
    assertNotNull(o);

    assertTrue("Deserialization failure", o instanceof Properties);

    Properties result = (Properties) o;
    assertEquals("value", result.get("key"));
  }

  public void testRemove()
  {
    //first create a file
    String filePath = "testRemove.file";
    String content = "To be removed";
    boolean success = FileUtil.getInstance().writeToFile(content.getBytes(), filePath, null);
    assertTrue(success);

    byte[] result = FileUtil.getInstance().getByteArray(filePath);
    assertTrue(result != null && result.length > 0);

    //then remove it
    FileUtil.getInstance().remove(filePath);
    result = null;

    try
    {
      result = FileUtil.getInstance().getByteArray(filePath);
      fail("Content of file could be read, thus it has not been removed");
    }
    catch (DataParsingException e)
    {
      //expected behavior.
    }
    assertNull(result);
  }

  @Override
  protected void tearDown() throws Exception
  {
    File objectToFileTest = new File("/tests/files/ObjectToFileTest.xml");
    if (objectToFileTest.exists()) objectToFileTest.delete();
    File fileToObjectTest = new File("/tests/files/FileToObjectTest.xml");
    if (fileToObjectTest.exists()) fileToObjectTest.delete();
  }

}
