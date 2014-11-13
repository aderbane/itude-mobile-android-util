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

import java.io.UnsupportedEncodingException;

import com.itude.mobile.android.util.log.MBLog;

/**
 * Utility class for methods to handle bytes.
 */
public final class ByteUtil
{
  private static final String TAG  = "ByteUtil";

  public static String        UTF8 = "UTF-8";

  /**
   * Default constructor
   */
  private ByteUtil()
  {
  }

  /**
   * Encodes a {@link String} into a byte array.
   * 
   * @param str byte array
   * @param encodingType encoding type
   *   
   * @return byte array
   */
  static public byte[] encodeStringToBytes(String str, String encodingType)
  {
    try
    {
      return str.getBytes(encodingType);
    }
    catch (UnsupportedEncodingException e)
    {
      MBLog.w(TAG, "unable is encode bytes with type " + encodingType);
    }
    return str.getBytes();
  }

  /**
   * Encodes byte array into a byte array.
   * 
   * @param bytes byte array
   * @param encodingType encoding type
   *   
   * @return byte array
   */
  static public byte[] encodeBytes(byte[] bytes, String encodingType)
  {
    String result = encodeBytesToString(bytes, encodingType);
    try
    {
      return result.getBytes(UTF8);
    }
    catch (UnsupportedEncodingException e)
    {
      MBLog.w(TAG, "unable is encode bytes with type " + encodingType);
    }
    return result.getBytes();
  }

  /**
   * Encodes byte array into a {@link String}.
   * 
   * @param bytes byte array
   * @param encodingType encoding type
   *   
   * @return {@link String}
   */
  static public String encodeBytesToString(byte[] bytes, String encodingType)
  {
    String result = "";
    try
    {
      result = new String(bytes, encodingType);
    }
    catch (UnsupportedEncodingException e)
    {
      MBLog.w(TAG, "unable is encode bytes with type " + encodingType);
    }
    return result;
  }

}
