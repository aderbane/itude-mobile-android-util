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

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.View;

/**
 * Utility class for methods to handle UI.
 */
public final class UIUtil
{

  /**
   * Default constructor
   */
  private UIUtil()
  {
  }

  /**
   * Make the View have rounded corners
   * 
   * @param view {@link View}
   * @param radius radius
   * @param color color
   */
  public static void makeRoundedView(View view, int radius, int color)
  {
    float[] corner = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
    ShapeDrawable backgroundRounded = new ShapeDrawable(new RoundRectShape(corner, null, corner));

    backgroundRounded.getPaint().setColor(color);
    backgroundRounded.getPaint().setAntiAlias(true);
    view.setBackgroundDrawable(backgroundRounded);
  }

}
