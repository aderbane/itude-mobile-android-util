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

/**
 * Utility template class for when you want to return 2 values.
 * 
 * @param <T1> the main result type, often boolean to indicate success.
 * @param <T2> the secondary result type, typically only valid when the boolean is true
 * 
 */
public class TwinResult<T1, T2>
{
  public T1 _mainResult;
  public T2 _secondResult;

  public TwinResult(T1 p_mainResult, T2 p_secondResult)
  {
    _mainResult = p_mainResult;
    _secondResult = p_secondResult;
  }

}
