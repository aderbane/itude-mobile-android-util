package com.itude.mobile.android.util;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.View;

/**
 * Wiebe
 *
 */
public final class UIUtil
{

  private UIUtil()
  {

  }

  public static void makeRoundedView(View view, int radius, int color)
  {
    float[] corner = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};
    ShapeDrawable backgroundRounded = new ShapeDrawable(new RoundRectShape(corner, null, corner));

    backgroundRounded.getPaint().setColor(color);
    backgroundRounded.getPaint().setAntiAlias(true);
    view.setBackgroundDrawable(backgroundRounded);
  }

}
