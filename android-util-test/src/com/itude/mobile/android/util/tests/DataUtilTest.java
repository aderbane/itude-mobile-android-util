/*
 * (C) Copyright ItudeMobile.
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

import junit.framework.TestCase;

import com.itude.mobile.android.util.DataUtil;

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
