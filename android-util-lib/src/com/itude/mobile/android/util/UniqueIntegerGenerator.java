package com.itude.mobile.android.util;

public class UniqueIntegerGenerator
{

  private static int uniqueInteger = 0;

  public static int getId()
  {
    uniqueInteger++;
    return uniqueInteger;
  }

}
