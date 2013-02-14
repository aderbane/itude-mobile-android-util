package com.itude.mobile.android.util.tests;

import com.itude.mobile.android.util.ByteUtil;

import junit.framework.TestCase;

public class ByteUtilTest extends TestCase
{
  private static final String TESTSTRING = "blabla";

  public void testEncodeStringToBytes()
  {
    byte[] result = ByteUtil.encodeStringToBytes(TESTSTRING, ByteUtil.UTF8);
    assertEquals(TESTSTRING, ByteUtil.encodeBytesToString(result, ByteUtil.UTF8));
  }

}
