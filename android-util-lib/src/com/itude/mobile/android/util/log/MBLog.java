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
package com.itude.mobile.android.util.log;

import android.util.Log;

import com.itude.mobile.android.util.BuildConfig;

public class MBLog
{

  private static boolean shouldLog(int logLevel)
  {
    // Not deadcode, only in IDE it is.
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
