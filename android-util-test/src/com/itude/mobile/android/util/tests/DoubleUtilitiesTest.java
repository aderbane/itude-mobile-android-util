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

import com.itude.mobile.android.util.DoubleUtil;

public class DoubleUtilitiesTest extends TestCase
{

  public void testRound()
  {
    double result = DoubleUtil.round(new Double(15), 2);
    assertEquals(new Double(15.00), result);
  }

  public void testRoundTwo()
  {
    double result = DoubleUtil.round(new Double(15.56), 2);
    assertEquals(new Double(15.56), result);
  }

}
