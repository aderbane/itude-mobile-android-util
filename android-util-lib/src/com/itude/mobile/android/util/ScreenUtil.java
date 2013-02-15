package com.itude.mobile.android.util;

import android.content.Context;

public final class ScreenUtil
{
  public final static int convertDimensionPixelsToPixels(Context context, float dimensionPixels)
  {
    final float scale = context.getResources().getDisplayMetrics().density;

    return (int) (dimensionPixels * (scale) + 0.5f);
  }

  public final static int getHeightPixelsForPercentage(Context context, float percentage)
  {
    final float height = context.getResources().getDisplayMetrics().heightPixels;
    return (int) ((height / 100) * percentage);
  }

  public final static int getWidthPixelsForPercentage(Context context, float percentage)
  {
    final float width = context.getResources().getDisplayMetrics().widthPixels;

    return (int) ((width / 100) * percentage);
  }
}
