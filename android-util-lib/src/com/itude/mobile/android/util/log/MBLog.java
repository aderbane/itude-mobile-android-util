package com.itude.mobile.android.util.log;

import android.util.Log;

import com.itude.mobile.android.util.BuildConfig;

public class MBLog
{

  private static boolean shouldLog(int logLevel)
  {
    if (BuildConfig.DEBUG || logLevel > Log.DEBUG)
    {
      /*
       * In case of a release version of the application we only want to show the levels 
       * that are more important than debug level
       */
      return true;
    }

    return false;
  }

  public static void d(String tag, String message)
  {
    if (shouldLog(Log.DEBUG))
    {
      Log.d(tag, message);
    }
  }

  public static void d(String tag, String message, Throwable throwable)
  {
    if (shouldLog(Log.DEBUG))
    {
      Log.d(tag, message, throwable);
    }
  }

  public static void v(String tag, String message)
  {
    if (shouldLog(Log.VERBOSE))
    {
      Log.v(tag, message);
    }
  }

  public static void v(String tag, String message, Throwable throwable)
  {
    if (shouldLog(Log.VERBOSE))
    {
      Log.v(tag, message, throwable);
    }
  }

  public static void w(String tag, String message)
  {
    if (shouldLog(Log.WARN))
    {
      Log.w(tag, message);
    }
  }

  public static void w(String tag, Throwable throwable)
  {
    if (shouldLog(Log.WARN))
    {
      Log.w(tag, throwable);
    }
  }

  public static void w(String tag, String message, Throwable throwable)
  {
    if (shouldLog(Log.WARN))
    {
      Log.w(tag, message, throwable);
    }
  }

  public static void i(String tag, String message)
  {
    if (shouldLog(Log.INFO))
    {
      Log.i(tag, message);
    }
  }

  public static void i(String tag, String message, Throwable throwable)
  {
    if (shouldLog(Log.INFO))
    {
      Log.i(tag, message, throwable);
    }
  }

  public static void e(String tag, String message)
  {
    if (shouldLog(Log.ERROR))
    {
      Log.e(tag, message);
    }
  }

  public static void e(String tag, String message, Throwable throwable)
  {
    if (shouldLog(Log.ERROR))
    {
      Log.e(tag, message, throwable);
    }
  }

}
