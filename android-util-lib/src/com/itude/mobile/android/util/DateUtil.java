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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.itude.mobile.android.util.exceptions.DateParsingException;
import com.itude.mobile.android.util.log.MBLog;

/**
 * Utility class for methods to handle data.
 */
public final class DateUtil
{
  private static final String                        TAG                    = "DateUtilities";

  private static final String                        DEFAULT_DATE_FORMAT    = "yyyy-MM-dd'T'HH:mm:ss";
  private static final ThreadLocal<SimpleDateFormat> TLDEFAULTDATEFORMATTER = new ThreadLocal<SimpleDateFormat>()
                                                                            {
                                                                              @Override
                                                                              protected SimpleDateFormat initialValue()
                                                                              {
                                                                                return new SimpleDateFormat(DEFAULT_DATE_FORMAT);
                                                                              }
                                                                            };

  /**
   * Default constructor
   */
  private DateUtil()
  {
  }

  /**
   * 
   * Formats the date depending on the current date assuming the receiver is a date string
   *  
   * @param locale
   * @param dateString
   * @return If the date is equal to the current date, the time is given back as a string. If the date is NOT equal to the current date, then a a date is presented back as a string
   */
  public static String formatDateDependingOnCurrentDate(Locale locale, String dateString)
  {
    String result = dateString;
    Date date = dateFromXML(dateString);

    DateFormat df;
    // We can't just compare two dates, because the time is also compared.
    // Therefore the time is removed and the two dates without time are compared
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);

    Calendar today = Calendar.getInstance();
    today.setTime(new Date());

    if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) && calendar.get(Calendar.MONTH) == today.get(Calendar.MONTH)
        && calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR))
    {
      df = new SimpleDateFormat("HH:mm:ss");
    }
    else
    {
      df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
    }

    // Format the date
    try
    {
      result = df.format(date);

      return result;
    }
    catch (Exception e)
    {
      throw new DateParsingException("Could not get format date depending on current date with input string: " + dateString, e);
    }
  }

  /**
   * Format {@link String}
   * 
   * @param stringToFormat {@link String} to format
   * @param format format
   * @return formatted {@link String} 
   */
  public synchronized static String formatString(String stringToFormat, String format)
  {
    try
    {
      Date date = dateFromXML(stringToFormat);
      if (date != null)
      {
        return dateToString(date, format);
      }
      else
      {
        return null;
      }
    }
    catch (Exception e)
    {
      throw new DateParsingException("Could not parse date from xml value: " + stringToFormat, e);
    }
  }

  /**
   * Date from XML {@link String}
   * 
   * @param stringToFormat {@link String} to format
   * @return {@link Date} 
   */
  public synchronized static Date dateFromXML(String stringToFormat)
  {
    Date value = null;
    if (StringUtil.isNotBlank(stringToFormat))
    {
      try
      {
        String dateString = stringToFormat.substring(0, 19);
        if (dateString != null)
        {
          value = TLDEFAULTDATEFORMATTER.get().parse(dateString);
        }
      }
      catch (Exception e)
      {
        throw new DateParsingException("Could not parse date from xml value: " + stringToFormat, e);
      }
    }
    return value;
  }

  /***
   * Date from {@link String}
   * 
   * @param stringToFormat {@link String} to format
   * @param format format
   * @return {@link Date} 
   */
  public synchronized static Date dateFromString(String stringToFormat, String format)
  {
    Date value = null;
    if (StringUtil.isNotBlank(stringToFormat))
    {
      try
      {
        SimpleDateFormat df = new SimpleDateFormat(format);
        value = df.parse(stringToFormat);
      }
      catch (Exception e)
      {
        throw new DateParsingException("Could not parse date from value: " + stringToFormat, e);
      }
    }
    return value;
  }

  /**
   * Get {@link String} representation of a year
   * @param date {@link Date}
   * @param format format
   * @return {@link String} representation of a year
   */
  public synchronized static String getYear(Date date, String format)
  {
    try
    {
      SimpleDateFormat df = new SimpleDateFormat(format);
      return df.format(date);
    }
    catch (Exception e)
    {
      throw new DateParsingException("Could not get year from value: " + date.getYear(), e);
    }
  }

  /**
   *  Date from XML {@link String}
   * @param stringToFormat {@link String} to format
   * @param format format
   * @return {@link Date}
   */
  public synchronized static Date dateFromXML(String stringToFormat, String format)
  {
    if (StringUtil.isEmpty(format))
    {
      return dateFromXML(stringToFormat);
    }
    else
    {

      try
      {
        String dateString = stringToFormat.substring(0, 19);
        if (dateString != null)
        {
          SimpleDateFormat df = new SimpleDateFormat(format);

          return df.parse(dateString);
        }
        else
        {
          return null;
        }
      }
      catch (Exception e)
      {
        throw new DateParsingException("Could not parse date from xml value: " + stringToFormat, e);
      }
    }
  }

  /**
   * Date to {@link String}
   * 
   * @param date {@link Date}
   * @return {@link String}
   */
  public static String dateToString(Date date)
  {
    return dateToStringDefaultFormat(date);
  }

  /**
   * Date to {@link String}
   * 
   * @param date {@link Date}
   * @param format format
   * @return {@link String}
   */
  public static String dateToString(Date date, String format)
  {
    if (StringUtil.isEmpty(format)) return dateToStringDefaultFormat(date);
    SimpleDateFormat df = new SimpleDateFormat(format);

    try
    {
      return df.format(date);
    }
    catch (Exception e)
    {
      throw new DateParsingException("Could not convert date to string with input date: " + date, e);
    }

  }

  /**
   * Date to {@link String} using the default format (yyyy-MM-dd'T'HH:mm:ss)
   * 
   * @param dateToFormat {@link Date}
   * @return {@link String}
   */

  private static String dateToStringDefaultFormat(Date dateToFormat)
  {
    return TLDEFAULTDATEFORMATTER.get().format(dateToFormat);
  }

  /**
   * Time to {@link String}
   * 
   * @param time long
   * @return {@link String}
   */
  public static String longToString(long time)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(time);
    return dateToStringDefaultFormat(calendar.getTime());
  }

  /**
   * Time to {@link String}
   * 
   * @param time long
   * @param format format
   * @return {@link String}
   */
  public static String longToString(long time, String format)
  {
    if (StringUtil.isEmpty(format)) return longToString(time);
    try
    {
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(time);
      SimpleDateFormat df = new SimpleDateFormat(format);
      return df.format(calendar.getTime());
    }
    catch (Exception e)
    {
      throw new DateParsingException("Could not convert long to string with input long: " + time, e);
    }
  }

  /**
   * Set the time of the {@link Calendar}
   * 
   * @param calender {@link Calendar} 
   * @param time {@link String}
   */
  public static void setCalanderTime(Calendar calender, String time)
  {
    if (StringUtil.isNotBlank(time))
    {
      try
      {
        calender.setTime(TLDEFAULTDATEFORMATTER.get().parse(time));
      }
      catch (ParseException e)
      {
        MBLog.e(TAG, "Couldn't parse date/time value" + time, e);
      }
    }
  }

  /**
   * Create new {@link Calendar} and set time
   * @param time {@link String}
   * @return {@link Calendar}
   */
  public static Calendar createNewCalenderWithTime(String time)
  {
    Calendar calender = null;
    if (StringUtil.isNotBlank(time))
    {
      calender = Calendar.getInstance();
      setCalanderTime(calender, time);
    }
    return calender;
  }

  public static int subtractDays(Date date, Date subtractDate)
  {
    long diff = date.getTime() - subtractDate.getTime();
    return safeLongToInt(diff / (1000 * 60 * 60 * 24));
  }

  /**
   * Long to int
   * 
   * @param l long
   * @return int
   */
  public static int safeLongToInt(long l)
  {
    if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE)
    {
      throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
    }
    return (int) l;
  }

  /**
   * Add days to a {@link Date}
   * 
   * @param date {@link Date}
   * @param days amount of days
   * @return {@link Date}
   */
  public static Date addDays(Date date, int days)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.add(Calendar.DATE, days);
    return cal.getTime();
  }

}