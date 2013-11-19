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

public final class AssertUtil
{
  private AssertUtil()
  {

  }

  public static <T> void notNull(String varName, T object)
  {
    if (object == null) throw new NullPointerException(varName + " is null");
  }

  public static void notEmpty(String varName, String str)
  {
    notNull(varName, str);
    if (str.length() == 0) throw new ItudeException(varName + " is empty");
  }

  public static void notZero(String varName, long value)
  {
    if (value == 0) throw new ItudeException(varName + " is empty");
  }

}
