package com.itude.mobile.android.util.tests;

import java.io.UnsupportedEncodingException;

import com.itude.mobile.android.util.DataUtil;

import junit.framework.TestCase;

public class DataUtilTest extends TestCase
{

  public void testCompression()
  {
    try
    {
      byte[] testData = "Test content for testing compression mechanism".getBytes("UTF-8");
      byte[] compressed = DataUtil.getInstance().compress(testData);
      assertNotNull(compressed);
    }
    catch (UnsupportedEncodingException e)
    {
      fail(e.getMessage());
    }
  }

  public void testDecompression()
  {
    try
    {
      String testString = "Test content for testing compression mechanism";
      byte[] testData = testString.getBytes("UTF-8");

      byte[] compressed = DataUtil.getInstance().compress(testData);
      assertNotNull(compressed);

      byte[] result = DataUtil.getInstance().decompress(compressed);
      assertNotNull(result);
      assertEquals(testString, new String(result));
    }
    catch (UnsupportedEncodingException e)
    {
      fail(e.getMessage());
    }
  }
}
