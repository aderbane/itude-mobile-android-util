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
 * Utility class with comparison checks.
 */
public class ComparisonUtil
{
  /**
   * Checks if passed objects are equal. 
   * 
   * @param o1 {@link Object}
   * @param o2 {@link Object}
   * @return true if equal
   */
  public static boolean safeEquals(Object o1, Object o2)
  {
    if (o1 == null && o2 == null) return true;
    if (o1 == null || o2 == null) return false;
    return o1.equals(o2);
  }

  /**
   * Checks if passed objects are equal. 
   * 
   * @param o1 {@link Comparable}
   * @param o2 {@link Comparable}
   * @return -1 if not equal, 0 if objects are null, 1 if objects are equal
   */
  public static <T extends Comparable<T>> int safeCompare(T o1, T o2)
  {
    return safeCompare(o1, o2, true);
  }

  /**
   * Checks if passed objects are equal. 
   * 
   * @param o1 {@link Comparable}
   * @param o2 {@link Comparable}
   * @param nullsFirst true if first object can be null
   * @return -1 if not equal, 0 if objects are null, 1 if objects are equal
   */
  public static <T extends Comparable<T>> int safeCompare(T o1, T o2, boolean nullsFirst)
  {
    if (o1 == null && o2 == null) return 0;
    if (o1 != null && o2 != null) return o1.compareTo(o2);
    if (o1 == null) return nullsFirst ? -1 : 1;
    return nullsFirst ? 1 : -1;
  }

  public static <T> boolean in(T obj, T... args)
  {
    for (T arg : args)
      if (safeEquals(obj, arg)) return true;

    return false;
  }

  public static <T> T coalesce(T... args)
  {
    for (T arg : args)
      if (arg != null) return arg;

    return null;
  }
}