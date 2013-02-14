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