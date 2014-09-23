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

import android.view.View;
import android.view.ViewGroup;

/**
 * Utility class for methods to handle {@link View}.
 */
public final class ViewUtilities
{

  /**
   * Default constructor
   */
  private ViewUtilities()
  {
  }

  /**
   * Detach a {@link View}
   * 
   * @param view {@link View} to be detached
   * @return detached {@link View}
   */
  public static View detachView(View view)
  {
    if (view != null && view.getParent() != null)
    {
      ViewGroup parent = (ViewGroup) view.getParent();
      parent.removeView(view);
    }

    return view;
  }

  public static void replaceView(View original, View replacement)
  {
    ViewGroup parent = (ViewGroup) original.getParent();
    replacement.setLayoutParams(original.getLayoutParams());

    if (replacement.getId() == View.NO_ID) replacement.setId(original.getId());

    // find out the index of original
    int index = 0;
    for (index = 0; index < parent.getChildCount(); ++index)
      if (parent.getChildAt(index) == original) break;

    parent.removeViewAt(index);
    parent.addView(replacement, index);

  }
}
