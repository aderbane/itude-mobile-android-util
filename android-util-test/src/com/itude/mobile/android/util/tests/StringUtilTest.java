package com.itude.mobile.android.util.tests;

import java.util.Locale;

import junit.framework.TestCase;
import android.util.Log;

import com.itude.mobile.android.util.StringUtil;

public class StringUtilTest extends TestCase
{
  private static final Locale DEFAULT_LOCALE = Locale.getDefault();
  private static final Locale LOCALE_DUTCH   = new Locale("nl", "NL");
  private static final Locale LOCALE_BRITISH = new Locale("en", "GB");

  public void testStripCharacters()
  {
    String inputString = "[12AB34CD56ef]\\";
    Log.d(this.getClass().getSimpleName(), "inputString=" + inputString);
    String stripCharacters = "[]0123456789\\";

    assertEquals("ABCDef", StringUtil.stripCharacters(inputString, stripCharacters));
  }

  public void testMd5()
  {
    String expected = "EF309F61BC2137DA1ED2EE866CF584CF";

    String dateTime = "2010-11-17 11:09:52";
    String deviceId = "IPH A6B2B91B-D6F7-5A42-AF94-4459C6BD47DF";
    String secret = "welkom123";

    String result = StringUtil.md5(dateTime + deviceId + secret);
    assertTrue("Hashes are not equal", expected.equalsIgnoreCase(result));
  }

  public void testFormatPriceWithTwoDecimals()
  {
    String formattedNumber;

    String[] numbers = {"100000", "1000.1234", "-1000.5366", "10.1", "-10.001", "-10.1", ".25"};
    String[] britishNumber = {"100,000.00", "1,000.12", "-1,000.54", "10.10", "-10.00", "-10.10", "0.25"};
    String[] dutchNumbers = {"100.000,00", "1.000,12", "-1.000,54", "10,10", "-10,00", "-10,10", "0,25"};

    // Using default locale
    for (int i = 0; i < numbers.length; i++)
    {
      formattedNumber = StringUtil.formatPriceWithTwoDecimals(DEFAULT_LOCALE, numbers[i]);
      assertEquals(dutchNumbers[i], formattedNumber);
    }

    // Using british locale
    for (int i = 0; i < numbers.length; i++)
    {
      formattedNumber = StringUtil.formatPriceWithTwoDecimals(LOCALE_BRITISH, numbers[i]);
      assertEquals(britishNumber[i], formattedNumber);
    }

    //      Using dutch locale
    for (int i = 0; i < numbers.length; i++)
    {
      formattedNumber = StringUtil.formatPriceWithTwoDecimals(LOCALE_DUTCH, numbers[i]);
      assertEquals(dutchNumbers[i], formattedNumber);
    }

  }

  public void testFormatPriceWithThreeDecimals()
  {
    String formattedNumber;

    String[] numbers = {"100000", "1000.1234", "-1000.5366", "10.1", "-10.001", "-10.1", ".25"};
    String[] britishNumber = {"100,000.000", "1,000.123", "-1,000.537", "10.100", "-10.001", "-10.100", "0.250"};
    String[] dutchNumbers = {"100.000,000", "1.000,123", "-1.000,537", "10,100", "-10,001", "-10,100", "0,250"};

    // Using default locale
    for (int i = 0; i < numbers.length; i++)
    {
      formattedNumber = StringUtil.formatPriceWithThreeDecimals(DEFAULT_LOCALE, numbers[i]);
      assertEquals("dutch=" + dutchNumbers[i] + " formatted=" + formattedNumber, dutchNumbers[i], formattedNumber);
    }

    // Using british locale
    for (int i = 0; i < numbers.length; i++)
    {
      formattedNumber = StringUtil.formatPriceWithThreeDecimals(LOCALE_BRITISH, numbers[i]);
      assertEquals("british=" + britishNumber[i] + " formatted=" + formattedNumber, britishNumber[i], formattedNumber);
    }

    // Using dutch locale
    for (int i = 0; i < numbers.length; i++)
    {
      formattedNumber = StringUtil.formatPriceWithThreeDecimals(LOCALE_DUTCH, numbers[i]);
      assertEquals("dutch=" + dutchNumbers[i] + " formatted=" + formattedNumber, dutchNumbers[i], formattedNumber);
    }
  }

  public void testFormatVolume()
  {
    String formattedNumber;

    String[] numbers = {"100000", "1000.1234", "-1000.5366", "10.1", "-10.001", "-10.1", ".25", "100000000000000"};
    String[] britishNumber = {"100,000", "1,000", "-1,001", "10", "-10", "-10", "0", "100,000,000,000,000"};
    String[] dutchNumbers = {"100.000", "1.000", "-1.001", "10", "-10", "-10", "0", "100.000.000.000.000"};

    // Using default locale
    for (int i = 0; i < numbers.length; i++)
    {
      formattedNumber = StringUtil.formatVolume(DEFAULT_LOCALE, numbers[i]);
      assertEquals(dutchNumbers[i], formattedNumber);
    }

    // Using british locale
    for (int i = 0; i < numbers.length; i++)
    {
      formattedNumber = StringUtil.formatVolume(LOCALE_BRITISH, numbers[i]);
      assertEquals(britishNumber[i], formattedNumber);
    }

    // Using dutch locale
    for (int i = 0; i < numbers.length; i++)
    {
      formattedNumber = StringUtil.formatVolume(LOCALE_DUTCH, numbers[i]);
      assertEquals(dutchNumbers[i], formattedNumber);
    }
  }
}
