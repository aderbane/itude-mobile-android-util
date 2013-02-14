package com.itude.mobile.android.util.tests;

import com.itude.mobile.android.util.DoubleUtil;

import junit.framework.TestCase;

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
