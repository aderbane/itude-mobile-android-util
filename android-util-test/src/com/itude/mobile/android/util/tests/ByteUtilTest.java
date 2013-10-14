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

import junit.framework.TestCase;

import com.itude.mobile.android.util.ByteUtil;

public class ByteUtilTest extends TestCase
{
  private static final String TESTSTRING = "blabla";

  public void testEncodeStringToBytes()
  {
    byte[] result = ByteUtil.encodeStringToBytes(TESTSTRING, ByteUtil.UTF8);
    assertEquals(TESTSTRING, ByteUtil.encodeBytesToString(result, ByteUtil.UTF8));
  }

}
