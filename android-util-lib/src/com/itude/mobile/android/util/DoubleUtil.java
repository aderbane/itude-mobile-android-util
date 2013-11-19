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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class DoubleUtil
{

  private DoubleUtil()
  {
  }

  /**
   * @param value Value that needs to be rounded
   * @param digits number of digits that needs to be rounded to.
   * @return double, with supplied <i>digits</i>
   */
  public static double round(double value, int digits)
  {
    DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
    otherSymbols.setDecimalSeparator('.');// Make sure the decimal separator is a dot, so Double.valueOf always works

    DecimalFormat formatter = new DecimalFormat();
    formatter.setMaximumFractionDigits(digits);
    formatter.setMinimumFractionDigits(digits);
    formatter.setGroupingUsed(false);
    formatter.setDecimalFormatSymbols(otherSymbols);

    return Double.valueOf(formatter.format(value));
  }

}