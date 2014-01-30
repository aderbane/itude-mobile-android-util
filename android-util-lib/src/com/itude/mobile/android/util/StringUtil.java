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

import java.security.MessageDigest;
import java.text.CharacterIterator;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.StringCharacterIterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Hex;

import android.util.Log;

/**
 * Utility class for methods to handle math.
 */
public final class StringUtil
{
  private static final String TAG   = "StringUtil";

  public static final String  EMPTY = "";

  /**
   * Sefault constructor
   */
  private StringUtil()
  {
  }

  /**
   * Set the formatter.
   * 
   * @param formatter {@link DecimalFormat}
   * @param locale {@link Locale}
   * @param numDec number of decimals
   */
  private static void setupFormatter(DecimalFormat formatter, Locale locale, int numDec)
  {
    setupFormatter(formatter, locale, numDec, numDec);
  }

  /**
   * Set the formatter.
   * 
   * @param formatter {@link DecimalFormat}
   * @param locale {@link Locale}
   * @param minimalDecimalNumbers minimal number of decimals
   * @param maximumDecimalNumbers maximum number of decimals
   */
  private static void setupFormatter(DecimalFormat formatter, Locale locale, int minimalDecimalNumbers, int maximumDecimalNumbers)
  {
    formatter.setDecimalFormatSymbols(new DecimalFormatSymbols(locale));
    formatter.setMinimumIntegerDigits(1);
    formatter.setMinimumFractionDigits(minimalDecimalNumbers);
    formatter.setMaximumFractionDigits(maximumDecimalNumbers);
    formatter.setGroupingUsed(true);
    formatter.setGroupingSize(3);
  }

  /**
   * Strip the given {@link String} with the given {@link String} characters 
   * 
   * @param inputString {@link String} to be stripped 
   * @param stripCharacters {@link String} strip characters
   * @return Stripped {@link String}
   */
  public static String stripCharacters(String inputString, String stripCharacters)
  {
    char[] charArray = stripCharacters.toCharArray();

    for (char c : charArray)
    {
      inputString = inputString.replace(String.valueOf(c), "");
    }

    return inputString;
  }

  /**
   * Strip the given {@link String} with the given character 
   *  
   * @param inputString {@link String} to be stripped 
   * @param stripCharacter strip character
   * @return Stripped {@link String}
   */
  public static String stripCharacter(String inputString, char stripCharacter)
  {
    return inputString.replaceAll(Pattern.quote(Character.toString(stripCharacter)), "");
  }

  /**
   * Appends spaces to the supplied {@link String}
   * 
   * @param level level
   * @return {@link String} of length level spaces
   * 
   */
  public static String getIndentStringWithLevel(int level)
  {
    StringBuffer rt = new StringBuffer(level);
    return appendIndentString(rt, level).toString();
  }

  /**
   * Appends spaces to the supplied StringBuffer, returns the same StringBuffer.
   * 
   * @param appendToMe {@link StringBuffer}
   * @param level level
   * @return the same StringBuffer given as a param, useful for chaining calls
   */
  public static StringBuffer appendIndentString(StringBuffer appendToMe, int level)
  {
    while (level-- > 0)
      appendToMe.append(' ');

    return appendToMe;
  }

  //returns a string formatted as a number with the original amount of decimals assuming the receiver is a float 
  //WARNING: Only use this method to present data to the screen
  public static String formatNumberWithOriginalNumberOfDecimals(String stringToFormat, Locale locale)
  {

    if (stringToFormat == null || stringToFormat.length() == 0)
    {
      return null;
    }

    String result = null;

    DecimalFormat formatter = new DecimalFormat("#################.####################", new DecimalFormatSymbols(locale));

    try
    {
      result = formatter.format(Double.parseDouble(stringToFormat));
    }
    catch (Exception e)
    {
      Log.w(TAG, "Could not format string " + stringToFormat + " as number with original number of decimals (StringUtilities)", e);

      return null;
    }

    return result;
  }

  /**
   * Format {@link String} like a number with two decimals
   * 
   * WARNING: Only use this method to present data to the screen
   * 
   * @param locale {@link Locale}
   * @param stringToFormat {@link String} to format
   * @return a {@link String} formatted as a number with two decimals assuming the receiver is a float string read from XML
   */
  public static String formatNumberWithTwoDecimals(Locale locale, String stringToFormat)
  {
    return formatNumberWithDecimals(locale, stringToFormat, 2);
  }

  /**
   * Format {@link String} like a number with three decimals
   * 
   * WARNING: Only use this method to present data to the screen
   * 
   * @param locale {@link Locale}
   * @param stringToFormat {@link String} to format
   * @return a {@link String} formatted as a number with three decimals assuming the receiver is a float string read from XML
   */
  public static String formatNumberWithThreeDecimals(Locale locale, String stringToFormat)
  {
    return formatNumberWithDecimals(locale, stringToFormat, 3);
  }

  /***
   * Format {@link String} like a number with given decimals
   * 
   * @param stringToFormat {@link String} to format
   * @param exactNumberOfDecimals can be any number, also negative as the used DecimalFormatter accepts it and makes it 0
   * @return a {@link String} formatted as a number with given decimals assuming the receiver is a float string read from XML
   */
  public static String formatNumberWithDecimals(Locale locale, String stringToFormat, int exactNumberOfDecimals)
  {
    return formatNumberWithDecimals(locale, stringToFormat, exactNumberOfDecimals, exactNumberOfDecimals);
  }

  /**
   * 
   * Format {@link String} like a number with given decimals
   * 
   * @param locale {@link Locale}
   * @param stringToFormat {@link String} to format
   * @param minimalNumberOfDecimals minimal amount of decimals. Can be any number, also negative as the used DecimalFormatter 
   * @param maximumNumberOfDecimals maximum amount of decimals. Can be any number, also negative as the used DecimalFormatter 
   * @return a {@link String} formatted as a number with given decimals assuming the receiver is a float string read from XML
   */
  public static String formatNumberWithDecimals(Locale locale, String stringToFormat, int minimalNumberOfDecimals,
                                                int maximumNumberOfDecimals)
  {
    if (stringToFormat == null || stringToFormat.length() == 0)
    {
      return null;
    }

    String result = null;

    DecimalFormat formatter = new DecimalFormat();
    setupFormatter(formatter, locale, minimalNumberOfDecimals, maximumNumberOfDecimals);

    result = formatter.format(Double.parseDouble(stringToFormat));

    return result;
  }

  /**
   * Format {@link String} like a price with two decimals
   *  
   * WARNING: Only use this method to present data to the screen
   * 
   * @param locale {@link Locale}
   * @param stringToFormat {@link String} to format
   * @return a {@link String} formatted as a price with two decimals assuming the receiver is a float string read from XML
   */
  public static String formatPriceWithTwoDecimals(Locale locale, String stringToFormat)
  {
    if (stringToFormat == null || stringToFormat.length() == 0)
    {
      return null;
    }

    int numberStart = stringToFormat.indexOf(" ");
    if (numberStart != -1 || (stringToFormat.indexOf("$") > -1 || stringToFormat.indexOf("€") > -1))
    {
      numberStart = Math.max(stringToFormat.indexOf("$"), stringToFormat.indexOf("€"));
    }

    if (numberStart > -1)
    {
      String prefix = stringToFormat.substring(0, numberStart + 1);
      stringToFormat = stringToFormat.substring(numberStart + 1, stringToFormat.length());
      return prefix + formatNumberWithDecimals(locale, stringToFormat, 2);
    }
    return formatNumberWithDecimals(locale, stringToFormat, 2);
  }

  /**
   * Format {@link String} like a price with two decimals
   *  
   * WARNING: Only use this method to present data to the screen
   * 
   * @param locale {@link Locale}
   * @param doubleToFormat double to format
   * @return a {@link String} formatted as a price with two decimals assuming the receiver is a float string read from XML
   */
  public static String formatPriceWithTwoDecimals(Locale locale, double doubleToFormat)
  {
    DecimalFormat formatter = new DecimalFormat();
    setupFormatter(formatter, locale, 2);

    return formatter.format(doubleToFormat);
  }

  // TODO why is this method not doing anything with the currency sign?
  /**
   * Format {@link String} like a price with three decimals
   *  
   * WARNING: Only use this method to present data to the screen
   * 
   * @param locale {@link Locale}
   * @param stringToFormat {@link String} to format
   * @return a {@link String} formatted as a price with three decimals assuming the receiver is a float string read from XML
   */
  public static String formatPriceWithThreeDecimals(Locale locale, String stringToFormat)
  {
    return formatNumberWithDecimals(locale, stringToFormat, 3);
  }

  /**
   * Format {@link String} as a Volume
   *  
   * WARNING: Only use this method to present data to the screen
   * 
   * @param locale {@link Locale}
   * @param stringToFormat  {@link String} to format
   * @return a string formatted as a volume with group separators (eg, 131.224.000) assuming the receiver is an int string read from XML
   */
  public static String formatVolume(Locale locale, String stringToFormat)
  {
    if (stringToFormat == null || stringToFormat.length() == 0)
    {
      return null;
    }

    String result = null;

    DecimalFormat formatter = new DecimalFormat();
    formatter.setDecimalFormatSymbols(new DecimalFormatSymbols(locale));

    formatter.setGroupingUsed(true);
    formatter.setGroupingSize(3);
    formatter.setMaximumFractionDigits(0);

    result = formatter.format(Double.parseDouble(stringToFormat));

    return result;
  }

  /**
   * Format {@link String} to a percentage with two decimals.
   * the receiver's value should be "as displayed", eg for 30%, the receiver should be 30, not 0.3 
   *
   * @param locale {@link Locale}
   * @param stringToFormat {@link String} to format
   * @return a string formatted as a percentage with two decimals assuming the receiver is a float string read from XML
   */
  public static String formatPercentageWithTwoDecimals(Locale locale, String stringToFormat)
  {
    return formatPriceWithTwoDecimals(locale, stringToFormat) + "%";
  }

  /**
   * Format double to a percentage with two decimals.
   * the receiver's value should be "as displayed", eg for 30%, the receiver should be 30, not 0.3 
   */
  /**
   * @param locale {@link Locale}
   * @param doubleToFormat double to format
   * @return a string formatted as a percentage with two decimals assuming the receiver is a float string read from XML
   */
  public static String formatPercentageWithTwoDecimals(Locale locale, double doubleToFormat)
  {
    return formatPriceWithTwoDecimals(locale, doubleToFormat) + "%";
  }

  /**
   * Has a {@link String}
   * 
   * @param stringToHash {@link String} to hash
   * @return Hashed {@link String}
   */
  public static String md5(String stringToHash)
  {
    MessageDigest digest = null;
    try
    {
      digest = java.security.MessageDigest.getInstance("MD5");
      digest.update(stringToHash.getBytes());
      byte[] hash = digest.digest();
      return new String(Hex.encodeHex(hash));
    }
    catch (Exception e)
    {
      Log.w(TAG, "Could not create hash of following string: " + stringToHash);
    }

    return null;
  }

  /**
   * Strip a {@link String} by removing HTML elements
   * 
   * @param textToStrip {@link String} to strip
   * @return stripped {@link String}
   */
  public static String stripHTMLTags(String textToStrip)
  {

    StringBuffer returnText = new StringBuffer(textToStrip.length());

    CharacterIterator iterator = new StringCharacterIterator(textToStrip);

    boolean finished = true;
    boolean started = false;
    for (char ch = iterator.first(); ch != CharacterIterator.DONE; ch = iterator.next())
    {
      if (ch == '<')
      {
        started = true;
      }
      else if (ch == '>')
      {
        started = false;
        finished = true;
      }
      else if (finished && !started)
      {
        returnText.append(ch);
      }

    }

    return returnText.toString().trim();
  }

  /**
   * Capitalizes every word in str 
   *
   * @param str {@link String}
   * @return Capitalizes {@link String}
   */
  public static String capitalize(String str)
  {
    if (str == null || str.length() == 0) return str;

    boolean capitalizeNext = true;
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < str.length(); ++i)
    {
      char ch = str.charAt(i);
      if (capitalizeNext) result.append(Character.toUpperCase(ch));
      else result.append(ch);

      capitalizeNext = Character.isWhitespace(ch);
    }

    return result.toString();
  }

  /**
   * <p>Checks if a String is not empty ("") and not null.</p>
   *
   * <pre>
   * StringUtils.isNotEmpty(null)      = false
   * StringUtils.isNotEmpty("")        = false
   * StringUtils.isNotEmpty(" ")       = true
   * StringUtils.isNotEmpty("wiebe")     = true
   * StringUtils.isNotEmpty("  wiebe  ") = true
   * </pre>
   *
   * @param str  the String to check, may be null
   * @return <code>true</code> if the String is not empty and not null
   */
  public static boolean isNotEmpty(String str)
  {
    return !isEmpty(str);
  }

  /**
   * <p>Checks if a String is empty ("") or null.</p>
   *
   * <pre>
   * StringUtils.isEmpty(null)      = true
   * StringUtils.isEmpty("")        = true
   * StringUtils.isEmpty(" ")       = false
   * StringUtils.isEmpty("wiebe")     = false
   * StringUtils.isEmpty("  wiebe  ") = false
   * </pre>
   *
   * @param str  the String to check, may be null
   * @return <code>true</code> if the String is empty or null
   */
  public static boolean isEmpty(String str)
  {
    return str == null || str.length() == 0;
  }

  /**
   * <p>Checks if a String is whitespace, empty ("") or null.</p>
   *
   * <pre>
   * StringUtils.isBlank(null)      = true
   * StringUtils.isBlank("")        = true
   * StringUtils.isBlank(" ")       = true
   * StringUtils.isBlank("wiebe")     = false
   * StringUtils.isBlank("  wiebe  ") = false
   * </pre>
   *
   * @param str  the String to check, may be null
   * @return <code>true</code> if the String is null, empty or whitespace
   */
  public static boolean isBlank(String str)
  {
    int strLen;
    if (str == null || (strLen = str.length()) == 0)
    {
      return true;
    }
    for (int i = 0; i < strLen; i++)
    {
      if ((Character.isWhitespace(str.charAt(i)) == false))
      {
        return false;
      }
    }
    return true;
  }

  /**
   * <p>Checks if a String is not empty (""), not null and not whitespace only.</p>
   *
   * <pre>
   * StringUtils.isNotBlank(null)      = false
   * StringUtils.isNotBlank("")        = false
   * StringUtils.isNotBlank(" ")       = false
   * StringUtils.isNotBlank("bob")     = true
   * StringUtils.isNotBlank("  bob  ") = true
   * </pre>
   *
   * @param str  the String to check, may be null
   * @return <code>true</code> if the String is
   *  not empty and not null and not whitespace
   */
  public static boolean isNotBlank(String str)
  {
    return !isBlank(str);
  }

  /**
   * <p>Gets the substring after the first occurrence of a separator.
   * The separator is not returned.</p>
   *
   * <p>A <code>null</code> string input will return <code>null</code>.
   * An empty ("") string input will return the empty string.
   * A <code>null</code> separator will return the empty string if the
   * input string is not <code>null</code>.</p>
   *
   * <pre>
   * StringUtils.substringAfter(null, *)      = null
   * StringUtils.substringAfter("", *)        = ""
   * StringUtils.substringAfter(*, null)      = ""
   * StringUtils.substringAfter("abc", "a")   = "bc"
   * StringUtils.substringAfter("abcba", "b") = "cba"
   * StringUtils.substringAfter("abc", "c")   = ""
   * StringUtils.substringAfter("abc", "d")   = ""
   * StringUtils.substringAfter("abc", "")    = "abc"
   * </pre>
   *
   * @param str  the String to get a substring from, may be null
   * @param separator  the String to search for, may be null
   * @return the substring after the first occurrence of the separator,
   *  <code>null</code> if null String input
   * @since 2.0
   */
  public static String substringAfter(String str, String separator)
  {
    if (isEmpty(str))
    {
      return str;
    }
    if (separator == null)
    {
      return EMPTY;
    }
    int pos = str.indexOf(separator);
    if (pos == -1)
    {
      return EMPTY;
    }
    return str.substring(pos + separator.length());
  }

  /**
   * Get {@link Double} value
   * @param value value
   * @return double value
   */
  public static Double getDoubleValue(String value)
  {
    if (isBlank(value))
    {
      throw new NumberFormatException();
    }

    return Double.parseDouble(value.replaceAll(",", "."));
  }

  /**
   * See:  http://www.javapractices.com/topic/TopicAction.do?Id=96
   */
  public static String escapeHtml(String html)
  {
    final StringBuilder escapedHtml = new StringBuilder();
    final StringCharacterIterator iterator = new StringCharacterIterator(html);
    char character = iterator.current();
    while (character != CharacterIterator.DONE)
    {
      if (character == '<')
      {
        escapedHtml.append("&lt;");
      }
      else if (character == '>')
      {
        escapedHtml.append("&gt;");
      }
      else if (character == '\"')
      {
        escapedHtml.append("&quot;");
      }
      else if (character == '\'')
      {
        escapedHtml.append("&#039;");
      }
      else if (character == '&')
      {
        escapedHtml.append("&amp;");
      }
      else
      {
        //the char is not a special one
        //add it to the result as is
        escapedHtml.append(character);
      }
      character = iterator.next();
    }
    return escapedHtml.toString();
  }

  /**
   * Gets the String that is nested in between two Strings.
   * Only the first match is returned.
   *
   * A <code>null</code> input String returns <code>null</code>.
   * A <code>null</code> open/close returns <code>null</code> (no match).
   * An empty ("") open and close returns an empty string.
   *
   * <pre>
   * StringUtils.substringBetween("w[ie]be", "[", "]") = "ie"
   * StringUtils.substringBetween(null, *, *)          = null
   * StringUtils.substringBetween(*, null, *)          = null
   * StringUtils.substringBetween(*, *, null)          = null
   * StringUtils.substringBetween("", "", "")          = ""
   * StringUtils.substringBetween("", "", "]")         = null
   * StringUtils.substringBetween("", "[", "]")        = null
   * StringUtils.substringBetween("wiebe", "", "")     = ""
   * StringUtils.substringBetween("wiebe", "w", "e")   = "ieb"
   * StringUtils.substringBetween("wiebewiebe", "w", "w")   = "ieb"
   * </pre>
   *
   * @param str  the String containing the substring, may be null
   * @param open  the String before the substring, may be null
   * @param close  the String after the substring, may be null
   * @return the substring, <code>null</code> if no match
   * @since 2.0
   */
  public static String substringBetween(String str, String open, String close)
  {
    if (str == null || open == null || close == null)
    {
      return null;
    }
    int start = str.indexOf(open);
    if (start != -1)
    {
      int end = str.indexOf(close, start + open.length());
      if (end != -1)
      {
        return str.substring(start + open.length(), end);
      }
    }
    return null;
  }

  /**
   * Check a {@link String} with a pattern
   * @param pattern pattern
   * @param value value
   * @return true if {@link String} is valid by the pattern
   */
  public static boolean checkPattern(String pattern, String value)
  {
    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(value);
    return m.matches() ? true : false;
  }
}
