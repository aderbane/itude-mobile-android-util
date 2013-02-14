package com.itude.mobile.android.util.tests;

import junit.framework.TestCase;

public class StringUtilTest extends TestCase
{
  //  private static final Locale DEFAULT_LOCALE = Locale.getDefault();
  //
  //  public void testStripCharacters()
  //  {
  //    String inputString = "[12AB34CD56ef]\\";
  //    Log.d(this.getClass().getSimpleName(), "inputString=" + inputString);
  //    String stripCharacters = "[]0123456789\\";
  //
  //    assertEquals("ABCDef", StringUtil.stripCharacters(inputString, stripCharacters));
  //  }
  //
  //  public void testMd5()
  //  {
  //    String expected = "EF309F61BC2137DA1ED2EE866CF584CF";
  //
  //    String dateTime = "2010-11-17 11:09:52";
  //    String deviceId = "IPH A6B2B91B-D6F7-5A42-AF94-4459C6BD47DF";
  //    String secret = "welkom123";
  //
  //    String result = StringUtil.md5(dateTime + deviceId + secret);
  //    assertTrue("Hashes are not equal", expected.equalsIgnoreCase(result));
  //  }
  //
  //  public void testFormatPriceWithTwoDecimals()
  //  {
  //    String formattedNumber;
  //
  //    String[] numbers = {"100000", "1000.1234", "-1000.5366", "10.1", "-10.001", "-10.1", ".25"};
  //    String[] britishNumber = {"100,000.00", "1,000.12", "-1,000.54", "10.10", "-10.00", "-10.10", "0.25"};
  //    String[] dutchNumbers = {"100.000,00", "1.000,12", "-1.000,54", "10,10", "-10,00", "-10,10", "0,25"};
  //
  //    // Using default locale
  //    for (int i = 0; i < numbers.length; i++)
  //    {
  //      formattedNumber = StringUtil.formatPriceWithTwoDecimals(DEFAULT_LOCALE, numbers[i]);
  //      assertEquals(dutchNumbers[i], formattedNumber);
  //    }
  //
  //    // Set british locale
  //    Locale british = new Locale("en", "GB");
  //    StringUtil.setDefaultFormattingLocale(british);
  //
  //    for (int i = 0; i < numbers.length; i++)
  //    {
  //      formattedNumber = StringUtil.formatPriceWithTwoDecimals(DEFAULT_LOCALE, numbers[i]);
  //      assertEquals(britishNumber[i], formattedNumber);
  //    }
  //
  //    // Check dutch locale
  //    StringUtil.setDefaultFormattingLocale(new Locale("nl", "NL"));
  //
  //    for (int i = 0; i < numbers.length; i++)
  //    {
  //      formattedNumber = StringUtil.formatPriceWithTwoDecimals(numbers[i]);
  //      assertEquals(dutchNumbers[i], formattedNumber);
  //    }
  //
  //  }
  //
  //  public void testFormatPriceWithThreeDecimals()
  //  {
  //    // Set dutch locale
  //    StringUtil.setDefaultFormattingLocale(new Locale("nl", "NL"));
  //    String formattedNumber;
  //
  //    String[] numbers = {"100000", "1000.1234", "-1000.5366", "10.1", "-10.001", "-10.1", ".25"};
  //    String[] britishNumber = {"100,000.000", "1,000.123", "-1,000.537", "10.100", "-10.001", "-10.100", "0.250"};
  //    String[] dutchNumbers = {"100.000,000", "1.000,123", "-1.000,537", "10,100", "-10,001", "-10,100", "0,250"};
  //
  //    // Using default locale
  //    /*    for (int i = 0; i < numbers.length; i++)
  //        {
  //          formattedNumber = StringUtil.formatPriceWithThreeDecimals(numbers[i]);
  //          assertEquals("dutch=" + dutchNumbers[i] + " formatted=" + formattedNumber, dutchNumbers[i], formattedNumber);
  //        }
  //    */
  //    // Set british locale
  //    StringUtil.setDefaultFormattingLocale(new Locale("en", "GB"));
  //
  //    for (int i = 0; i < numbers.length; i++)
  //    {
  //      formattedNumber = StringUtil.formatPriceWithThreeDecimals(numbers[i]);
  //      assertEquals("british=" + britishNumber[i] + " formatted=" + formattedNumber, britishNumber[i], formattedNumber);
  //    }
  //
  //    // Check dutch locale
  //    StringUtil.setDefaultFormattingLocale(new Locale("nl", "NL"));
  //
  //    for (int i = 0; i < numbers.length; i++)
  //    {
  //      formattedNumber = StringUtil.formatPriceWithThreeDecimals(numbers[i]);
  //      assertEquals("dutch=" + dutchNumbers[i] + " formatted=" + formattedNumber, dutchNumbers[i], formattedNumber);
  //    }
  //
  //  }
  //
  //  public void testFormatVolume()
  //  {
  //    String formattedNumber;
  //
  //    String[] numbers = {"100000", "1000.1234", "-1000.5366", "10.1", "-10.001", "-10.1", ".25", "100000000000000"};
  //    String[] britishNumber = {"100,000", "1,000", "-1,001", "10", "-10", "-10", "0", "100,000,000,000,000"};
  //    String[] dutchNumbers = {"100.000", "1.000", "-1.001", "10", "-10", "-10", "0", "100.000.000.000.000"};
  //
  //    // Using default locale
  //    for (int i = 0; i < numbers.length; i++)
  //    {
  //      formattedNumber = StringUtil.formatVolume(numbers[i]);
  //      assertEquals(dutchNumbers[i], formattedNumber);
  //    }
  //
  //    // Set british locale
  //    StringUtil.setDefaultFormattingLocale(new Locale("en", "GB"));
  //
  //    for (int i = 0; i < numbers.length; i++)
  //    {
  //      formattedNumber = StringUtil.formatVolume(numbers[i]);
  //      assertEquals(britishNumber[i], formattedNumber);
  //    }
  //
  //    // Check dutch locale
  //    StringUtil.setDefaultFormattingLocale(new Locale("nl", "NL"));
  //
  //    for (int i = 0; i < numbers.length; i++)
  //    {
  //      formattedNumber = StringUtil.formatVolume(numbers[i]);
  //      assertEquals(dutchNumbers[i], formattedNumber);
  //    }
  //
  //  }
  //
  //  private void compareEqual(List<String> splittedOld, List<String> splittedNew, String path)
  //  {
  //    assertTrue(path + ", one is null, other is not", (splittedNew == null && splittedOld == null) || splittedNew != null
  //                                                     && splittedOld != null);
  //    if (splittedNew == null) return;
  //    assertTrue(path + ", sizes are different n=" + splittedNew.size() + "o=" + splittedOld.size(), splittedNew.size() == splittedOld.size());
  //    assertTrue(path + ", values are different", splittedNew.equals(splittedOld));
  //  }
  //
  //  public void testSplitPathShouldThrow()
  //  {
  //    Pattern splitPathPattern = Pattern.compile("/");
  //    String[] pathsThatThrow = {"../a/b/c", "/../a/b/c", "/a/../../b"};
  //    List<String> splittedOld = null;
  //    List<String> splittedNew = null;
  //    for (String path : pathsThatThrow)
  //    {
  //      try
  //      {
  //        splittedOld = splitPathOldImplementation(path, splitPathPattern);
  //        throw new AssertionError("expected an MBInvalidRelativePathException (old) " + path);
  //      }
  //      catch (MBInvalidRelativePathException e)
  //      {
  //        // expected
  //      }
  //      try
  //      {
  //        splittedNew = StringUtil.splitPath(path);
  //        throw new AssertionError("expected an MBInvalidRelativePathException (new) " + path);
  //      }
  //      catch (MBInvalidRelativePathException e)
  //      {
  //        // expected
  //      }
  //      compareEqual(splittedOld, splittedNew, path);
  //    }
  //  }
  //
  //  public void testSplitPath()
  //  {
  //    Pattern splitPathPattern = Pattern.compile("/");
  //    String[] pathsToTest = {"a/b/c", "/a/b/c", "a/b/c/", "/a/b/c/", "a/../c", "/a/../c", "a/../c/", "/a/../c/", "a/b/..", "/a/b/..",
  //        "a/b/../", "/a/b/../", "a///////b/..", "/a/b////////////..", "a/////////b////////../", "////////////a/b/../", "./a/b/c",
  //        "/./a/b/c", "a/b/./c/", "/a/b/c/.", "/a/b/c/./", "aaaaaaaaaaaaaaa/b/c", "/a/bbbbbbbbbbbbbbbbbbbbbbbbbb/c",
  //        "a/b/ccccccccccccccccccccccccccc/"};
  //    List<String> splittedOld = null;
  //    List<String> splittedNew = null;
  //    for (String path : pathsToTest)
  //    {
  //      splittedOld = splitPathOldImplementation(path, splitPathPattern);
  //      splittedNew = StringUtil.splitPath(path);
  //      compareEqual(splittedOld, splittedNew, path);
  //    }
  //  }
  //
  //  public void testSplitPathPerformance()
  //  {
  //    // the current splitPath implementation shows a major improvement
  //    // over the old implementation.
  //    // we don't want any changes to splitPath that make the implementation
  //    // a lot slower.
  //    // this test checks if the current impl is still ~3 times as fast as
  //    // originally created.
  //    Pattern splitPathPattern = Pattern.compile("/");
  //    String[] pathsToTest = {"a/b/c", "/a/b/c", "a/b/c/", "/a/b/c/", "a/../c", "/a/../c", "a/../c/", "/a/../c/", "a/b/..", "/a/b/..",
  //        "a/b/../", "/a/b/../", "a///////b/..", "/a/b////////////..", "a/////////b////////../", "////////////a/b/../", "./a/b/c",
  //        "/./a/b/c", "a/b/./c/", "/a/b/c/.", "/a/b/c/./", "aaaaaaaaaaaaaaa/b/c", "/a/bbbbbbbbbbbbbbbbbbbbbbbbbb/c",
  //        "a/b/ccccccccccccccccccccccccccc/"};
  //    int numTimesToIterate = 500;
  //    long timeOld = 0;
  //    long timeNew = 0;
  //    // old implementation
  //    {
  //      System.gc();
  //      System.gc();
  //      long start = System.currentTimeMillis();
  //      for (int i = 0; i < numTimesToIterate; i++)
  //      {
  //        for (String path : pathsToTest)
  //        {
  //          splitPathOldImplementation(path, splitPathPattern);
  //        }
  //      }
  //      long end = System.currentTimeMillis();
  //      timeOld = end - start;
  //    }
  //    // new implementation
  //    {
  //      System.gc();
  //      System.gc();
  //      long start = System.currentTimeMillis();
  //      for (int i = 0; i < numTimesToIterate; i++)
  //      {
  //        for (String path : pathsToTest)
  //        {
  //          StringUtil.splitPath(path);
  //        }
  //      }
  //      long end = System.currentTimeMillis();
  //      timeNew = end - start;
  //    }
  //    long difference = timeOld - timeNew;
  //    assertTrue("splitPath implementation performs badly: slower than old impl", difference > 0);
  //  }
  //
  //  private static List<String> splitPathOldImplementation(String path, Pattern splitPathPattern)
  //  {
  //    List<String> components = new ArrayList<String>();
  //    String[] splitted = splitPathPattern.split(path, 0);
  //
  //    for (String component : splitted)
  //    {
  //      if (component.equals(".") || component.equals("") || component.equals("/"))
  //      {
  //        // Skip
  //      }
  //      else if (component.equals(".."))
  //      {
  //        if (components.size() == 0)
  //        {
  //          throw new MBInvalidRelativePathException(path);
  //        }
  //        components.remove(components.size() - 1);
  //      }
  //      else
  //      {
  //        components.add(component);
  //      }
  //    }
  //
  //    return components;
  //  }

}
