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

/**
 * This class allows you to create a Runnable with a parameter.
 * @author Gert
 *
 * @param <T> the type of parameter
 */
public abstract class RunnableWithParam<T> implements Runnable
{
  private T _param = null;

  public RunnableWithParam(T p_param)
  {
    _param = p_param;
  }

  protected T getParam()
  {
    return _param;
  }

  @Override
  public abstract void run();
}