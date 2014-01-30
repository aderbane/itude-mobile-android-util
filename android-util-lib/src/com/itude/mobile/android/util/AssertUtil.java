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
 * Utility class with validation checks.
 */
public final class AssertUtil
{
  /**
   * Default constructor
   */
  private AssertUtil()
  {
  }

  /**
   * Checks if passed object is not null (and throws {@link NullPointerException} if not). 
   * Thrown exception contains proper message with passed object name for easier identification.
   * 
   * @param objectName name of passed object
   * @param objectToCheck object to be checked
   * 
   * @throws NullPointerException if passed object is null
   */
  public static <T> void notNull(String objectName, T objectToCheck)
  {
    if (objectToCheck == null) throw new NullPointerException(objectName + " is null");
  }

  /**
   * Checks if passed {@link String} is not null (and throws {@link ItudeException} if not). 
   * Thrown exception contains proper message with passed {@link String} name for easier identification.
   * 
   * @param stringName name of passed {@link String}
   * @param strToCheck value to be checked
   * 
   * @throws ItudeException if passed string is empty
   */
  public static void notEmpty(String stringName, String strToCheck)
  {
    notNull(stringName, strToCheck);
    if (strToCheck.length() == 0) throw new ItudeException(stringName + " is empty");
  }

  /**
   * Checks if passed long value is not zero (and throws {@link ItudeException} if not). 
   * Thrown exception contains proper message with passed {@link String} name for easier identification.
   * 
   * @param stringName name of passed {@link String}
   * @param longToCheck value to be checked
   * 
   * @throws ItudeException if passed value is empty
   */
  public static void notZero(String stringName, long longToCheck)
  {
    if (longToCheck == 0) throw new ItudeException(stringName + " is empty");
  }

}
