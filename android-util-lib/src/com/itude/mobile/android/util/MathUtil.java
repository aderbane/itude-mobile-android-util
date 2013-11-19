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

public class MathUtil
{

  private final static int ROUND_SCALE = 1000000;

  public static final double truncate(double value)
  {
    double fraction;
    double whole;
    if (value > 0)
    {
      whole = Math.floor(value);
      fraction = Math.floor((value - whole) * ROUND_SCALE) / ROUND_SCALE;
    }
    else
    {
      whole = Math.ceil(value);
      fraction = Math.ceil((value - whole) * ROUND_SCALE) / ROUND_SCALE;
    }
    return whole + fraction;
  }
}
