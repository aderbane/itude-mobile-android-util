/*
 * (C) Copyright ItudeMobile.
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
