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
