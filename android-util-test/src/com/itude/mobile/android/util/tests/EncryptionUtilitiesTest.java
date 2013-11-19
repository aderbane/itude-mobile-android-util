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
package com.itude.mobile.android.util.tests;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import junit.framework.TestCase;
import android.annotation.TargetApi;
import android.util.Base64;

import com.itude.mobile.android.util.ByteUtil;
import com.itude.mobile.android.util.EncryptionUtil;

@TargetApi(8)
public class EncryptionUtilitiesTest extends TestCase
{

  private static final String TESTSTRING   = "toplinealex";
  private static final String ENCODINGTYPE = "windows-1252";

  public void testEncryptWiki1()
  {
    byte[] result = EncryptionUtil.encrypt("Key", "Plaintext");
    assertEquals("BBF316E8D940AF0AD3", EncryptionUtil.byte2string(result).toUpperCase());
  }

  public void testEncryptWiki2()
  {
    byte[] result = EncryptionUtil.encrypt("Wiki", "pedia");
    assertEquals("1021BF0420", EncryptionUtil.byte2string(result).toUpperCase());
  }

  public void testEncrypt() throws UnsupportedEncodingException
  {
    byte[] result = EncryptionUtil.encrypt(TESTSTRING.toUpperCase(), TESTSTRING.toUpperCase());
    assertEquals("ÝÕåòÈïÇÊ_Ð", ByteUtil.encodeBytesToString(result, ENCODINGTYPE).trim());
  }

  public void testEncryptMetBase() throws UnsupportedEncodingException
  {
    byte[] result = EncryptionUtil.encrypt(TESTSTRING.toUpperCase(), TESTSTRING.toUpperCase());
    assertEquals("DDD5E5F2C8EFC7CA5FD00F", EncryptionUtil.byte2string(result).toUpperCase());
    assertEquals("w53DlcOlw7LDiMOvw4fDil/DkA8=", Base64.encodeToString(ByteUtil.encodeBytes(result, ENCODINGTYPE), Base64.DEFAULT).trim());
  }

  public void testEncryptMetBase2() throws UnsupportedEncodingException
  {
    byte[] result = EncryptionUtil.encrypt("binckbank01".toUpperCase(), "binckbank01".toUpperCase());
    byte[] ba = new byte[]{2, 55, 24, -50, -76, 56, -115, 99, 14, -105, 68};
    assertEquals(Arrays.toString(ba), Arrays.toString(result));
    assertEquals(ByteUtil.encodeBytesToString(ba, ENCODINGTYPE), ByteUtil.encodeBytesToString(result, ENCODINGTYPE));

    assertEquals("023718CEB4388D630E9744", EncryptionUtil.byte2string(result).toUpperCase());

    assertEquals(EncryptionUtil.byte2string((ByteUtil.encodeBytes(ba, ENCODINGTYPE))),
                 EncryptionUtil.byte2string((ByteUtil.encodeBytes(result, ENCODINGTYPE))));

    byte[] ba2 = new byte[]{2, 55, 24, -61, -114, -62, -76, 56, -62, -115, 99, 14, -30, -128, -108, 68};
    assertTrue(Arrays.equals(ba2, ByteUtil.encodeBytes(ba, ENCODINGTYPE)));
    assertTrue(Arrays.equals(ba2, ByteUtil.encodeBytes(result, ENCODINGTYPE)));

    assertEquals("023718c38ec2b438c28d630ee2809444", EncryptionUtil.byte2string((ByteUtil.encodeBytes(result, ENCODINGTYPE))));
    assertEquals("AjcYw47CtDjCjWMO4oCURA==", Base64.encodeToString(ByteUtil.encodeBytes(result, ENCODINGTYPE), Base64.DEFAULT).trim());

  }
}
