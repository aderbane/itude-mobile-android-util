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

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Utility class for methods to handle device.
 * 
 * The class provides methods for other classes to check what kind of device the application is running on.
 */
public final class DeviceUtil
{

  private static DeviceUtil            _instance;

  private String                       _osVersion                   = null;
  private TwinResult<Integer, Integer> _screenSize                  = null;
  private String                       _screenDensityClassification = null;
  private TwinResult<Float, Float>     _screenDensity               = null;
  private String                       _screenType                  = null;
  private String                       _deviceModel                 = null;

  private static Boolean               _isBigDevice                 = null;

  private static Context               _context                     = null;

  private DeviceUtil()
  {
  }

  /**
   * @return {@link DeviceUtil}
   */
  public static DeviceUtil getInstance()
  {
    if (_instance == null)
    {
      synchronized (DeviceUtil.class)
      {
        if (_instance == null)
        {
          _instance = new DeviceUtil();
        }
      }
    }

    return _instance;
  }

  /**
   * Get the device type
   * 
   * @return Smartphone or Smartphone V14 or Tablet
   */
  public String getDeviceType()
  {
    if (isPhone())
    {
      return "Smartphone";
    }
    else if (isPhoneV14())
    {
      return "Smartphone V14";
    }
    else if (isTablet())
    {
      return "Tablet";
    }
    else
    {
      return "Unknown";
    }
  }

  /**
   * Get the device model
   * 
   * @return device model
   */
  public String getDeviceModel()
  {
    if (_deviceModel == null)
    {
      _deviceModel = Build.MODEL;
      if (_deviceModel == null)
      {
        _deviceModel = "";
      }
    }

    return _deviceModel;
  }

  /**
   * Get the OS version 
   * 
   * @return Android x
   */
  public String getOSVersion()
  {
    if (_osVersion == null)
    {
      switch (Build.VERSION.SDK_INT)
      {
        case (Build.VERSION_CODES.BASE) : //$FALL-THROUGH$
        case (Build.VERSION_CODES.BASE_1_1) :
          _osVersion = "Android 1";
          break;
        case (Build.VERSION_CODES.CUPCAKE) :
          _osVersion = "Android 1.5 Cupcake";
          break;
        case (Build.VERSION_CODES.DONUT) :
          _osVersion = "Android 1.6 Donut";
          break;
        case (Build.VERSION_CODES.ECLAIR) : //$FALL-THROUGH$
        case (Build.VERSION_CODES.ECLAIR_MR1) : //$FALL-THROUGH$
        case (Build.VERSION_CODES.ECLAIR_0_1) :
          _osVersion = "Android 2.0/2.1 Eclair";
          break;
        case (Build.VERSION_CODES.FROYO) :
          _osVersion = "Android 2.2 Froyo";
          break;
        case (Build.VERSION_CODES.GINGERBREAD) :
          _osVersion = "Android 2.3 Gingerbread";
          break;
        case (Build.VERSION_CODES.GINGERBREAD_MR1) :
          _osVersion = "Android 2.3.3 Gingerbread";
          break;
        case (Build.VERSION_CODES.HONEYCOMB) : //$FALL-THROUGH$
        case (Build.VERSION_CODES.HONEYCOMB_MR1) : //$FALL-THROUGH$ 
        case (Build.VERSION_CODES.HONEYCOMB_MR2) :
          _osVersion = "Android 3.0 Honeycomb";
          break;
        case (Build.VERSION_CODES.ICE_CREAM_SANDWICH) : //$FALL-THROUGH$
        case (Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) :
          _osVersion = "Android 4.0 ICS";
          break;
        case (Build.VERSION_CODES.JELLY_BEAN) : //$FALL-THROUGH$
          _osVersion = "Android 4.1 JellyBean";
          break;
        /*        case (Build.VERSION_CODES.JELLY_BEAN_MR1) :
                  _osVersion = "Android 4.2 JellyBean";
                  break;*/

        default :
          _osVersion = "Unknown";
      }

      _osVersion += " (" + Build.VERSION.RELEASE + ")";
    }

    return _osVersion;
  }

  /**
   * Get the screen size
   * 
   * @return screen size
   */
  public TwinResult<Integer, Integer> getScreenSize()
  {
    if (_screenSize == null)
    {
      Display display = getDisplay();
      int width = display.getWidth();
      int height = display.getHeight();

      _screenSize = new TwinResult<Integer, Integer>(width, height);
    }

    return _screenSize;
  }

  /**
   * Get the screen density classification
   * 
   * @return screen density classification
   */
  public String getScreenDensityClassification()
  {
    if (_screenDensityClassification == null)
    {
      DisplayMetrics metrics = getDisplayMetrics();

      switch (metrics.densityDpi)
      {
        case (DisplayMetrics.DENSITY_LOW) :
          _screenDensityClassification = "low";
          break;
        case (DisplayMetrics.DENSITY_MEDIUM) :
          _screenDensityClassification = "medium";
          break;
        case (DisplayMetrics.DENSITY_HIGH) :
          _screenDensityClassification = "high";
          break;
        case (DisplayMetrics.DENSITY_XHIGH) :
          _screenDensityClassification = "xhigh";
          break;
        default :
          _screenDensityClassification = "unknown";
      }
    }

    return _screenDensityClassification;
  }

  /**
   * Get the screen density
   * 
   * @return screen {@link TwinResult}
   */
  public TwinResult<Float, Float> getScreenDensity()
  {
    if (_screenDensity == null)
    {
      DisplayMetrics metrics = getDisplayMetrics();

      _screenDensity = new TwinResult<Float, Float>(metrics.xdpi, metrics.ydpi);
    }

    return _screenDensity;
  }

  /**
   * Get the screen type 
   * 
   * @return small/normal/large/xlarge/unknown 
   */
  public String getScreenType()
  {
    if (_screenType == null)
    {
      int screenType = getLayoutMask();

      switch (screenType)
      {
        case (Configuration.SCREENLAYOUT_SIZE_SMALL) :
          _screenType = "small";
          break;
        case (Configuration.SCREENLAYOUT_SIZE_NORMAL) :
          _screenType = "normal";
          break;
        case (Configuration.SCREENLAYOUT_SIZE_LARGE) :
          _screenType = "large";
          break;
        case (Configuration.SCREENLAYOUT_SIZE_XLARGE) :
          _screenType = "xlarge";
          break;
        case (Configuration.SCREENLAYOUT_SIZE_UNDEFINED) :
          _screenType = "unknown";
          break;
        default :
          _screenType = "unknown";
      }
    }

    return _screenType;
  }

  /**
   * Get the Layout mask 
   * 
   * @return layout mask
   */
  private static int getLayoutMask()
  {
    return getContext().getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
  }

  /**
   * Set the {@link Context} for this instance
   * 
   * @param context {@link Context}
   */
  public void setContext(Context context)
  {
    _context = context;
  }

  /**
   * Is device big?
   * 
   * @return true if device is tablet or screen size is bigger than 7 inch
   */
  public static boolean isBigDeviceType()
  {
    if (_isBigDevice == null)
    {
      //Verifies if the Generalized Size of the device is XLARGE to be
      // considered a Tablet
      boolean xlarge = (getLayoutMask() == Configuration.SCREENLAYOUT_SIZE_XLARGE);

      // If XLarge, checks if the Generalized Density is at least MDPI
      // (160dpi)
      if (xlarge)
      {
        DisplayMetrics metrics = getDisplayMetrics();

        // MDPI=160, DEFAULT=160, DENSITY_HIGH=240, DENSITY_MEDIUM=160,
        // DENSITY_TV=213, DENSITY_XHIGH=320
        if (metrics.densityDpi == DisplayMetrics.DENSITY_DEFAULT || metrics.densityDpi == DisplayMetrics.DENSITY_HIGH
            || metrics.densityDpi == DisplayMetrics.DENSITY_MEDIUM || metrics.densityDpi == DisplayMetrics.DENSITY_TV
            || metrics.densityDpi == DisplayMetrics.DENSITY_XHIGH)
        {

          _isBigDevice = true;

        }
        else
        {
          _isBigDevice = false;
        }
      }
      else
      {
        _isBigDevice = false;

      }
    }
    return _isBigDevice;
  }

  /**
   * Get the display matrix
   * 
   * @return {@link DisplayMetrics}
   */
  private static DisplayMetrics getDisplayMetrics()
  {
    DisplayMetrics metrics = new DisplayMetrics();
    getDisplay().getMetrics(metrics);
    return metrics;
  }

  /**
   * Get the Display
   * 
   * @return {@link Display}
   */
  private static Display getDisplay()
  {
    return ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
  }

  private static Context getContext()
  {
    return _context;
  }

  /**
   * Has device native actionbar support
   * 
   * @return true if device has native actionbar support
   */
  public boolean hasNativeActionBarSupport()
  {
    return Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1;
  }

  /**
   * Is the device a phone
   * 
   * @return true is device is a phone
   */
  public boolean isPhone()
  {
    return !isBigDeviceType() && (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB);
  }

  /**
   * Is the device a phone with Android 4.x and up
   * 
   * @return true is device is a phone with Android 4.x and up
   */
  public boolean isPhoneV14()
  {
    return !isBigDeviceType() && (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1);
  }

  /**
   * Is the device a tablet
   * 
   * @return true is device is a tablet
   */

  public static boolean isTablet()
  {
    return isBigDeviceType();
  }

  /**
   * Generate unique id
   * 
   * @return unique id
   */
  public String getUniqueID()
  {
    String androidID = Settings.Secure.getString(_context.getContentResolver(), Settings.Secure.ANDROID_ID);

    if (androidID == null)
    {
      return "";
    }

    return androidID;
  }

  /**
   * Is Internet connection available?
   * 
   * @return true if Internet connection is available 
   */
  public boolean isInternetConnectionAvailable()
  {
    ConnectivityManager cm = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
    if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected())
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * Is phone service available?
   * 
   * @return true if phone service  is available 
   */
  public boolean isPhoneServiceAvailable()
  {
    TelephonyManager tm = (TelephonyManager) _context.getSystemService(Context.TELEPHONY_SERVICE);
    if (tm.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE)
    {
      return false;
    }
    return true;

  }

  @Override
  public String toString()
  {
    StringBuilder result = new StringBuilder();
    result.append(" - Type: " + getDeviceType() + "\n");
    result.append(" - OS version: " + getOSVersion() + "\n");
    result.append(" - Screen type: " + getScreenType() + "\n");
    result.append(" - Screen size: " + getScreenSize()._mainResult + " x " + getScreenSize()._secondResult + "\n");
    result.append(" - Screen density: " + getScreenDensity()._mainResult + " x " + getScreenDensity()._secondResult + "\n");
    result.append(" - Screen classification: " + getScreenDensityClassification() + "\n");
    result.append(" - Has big screen: " + isBigDeviceType());

    return result.toString();
  }

}
