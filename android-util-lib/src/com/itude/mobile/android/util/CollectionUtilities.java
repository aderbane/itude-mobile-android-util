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

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Utility class for methods to handle collections.
 */
public final class CollectionUtilities
{

  private static Integer INTEGER_ONE = new Integer(1);

  private CollectionUtilities()
  {
  }

  /**
   * Null-safe check if the specified collection is empty.
   *  
   * @param coll the collection to check, may be null
   * @return true if empty or null
   */
  public static boolean isEmpty(Collection<?> coll)
  {
    return (coll == null || coll.isEmpty());
  }

  /**
   * Null-safe check if the specified collection is not empty.
   * 
   * @param coll the collection to check, may be null
   * @return true if non-null and non-empty
   */
  public static boolean isNotEmpty(Collection<?> coll)
  {
    return !CollectionUtilities.isEmpty(coll);
  }

  /**
   * Returns true if the given <i>java.util.Collections</i> contain exactly the same elements with exactly the same cardinalities.
   * That is, if the cardinality of e in a is equal to the cardinality of e in b, for each element e in a or b. 
   * 
   * @param a the first collection, must not be null
   * @param b the second collection, must not be null
   * @return if the collections contain the same elements with the same cardinalities.
   */
  public static boolean isEqualCollection(final Collection<?> a, final Collection<?> b)
  {

    if (a.size() != b.size())
    {
      return false;

    }
    else
    {
      Map<?, ?> mapA = getCardinalityMap(a);
      Map<?, ?> mapB = getCardinalityMap(b);

      if (mapA.size() != mapB.size())
      {
        return false;
      }
      else
      {
        Iterator<?> it = mapA.keySet().iterator();

        while (it.hasNext())
        {
          Object obj = it.next();
          if (getFreq(obj, mapA) != getFreq(obj, mapB))
          {
            return false;
          }
        }
        return true;
      }
    }
  }

  @SuppressWarnings("unchecked")
  public static Map<?, ?> getCardinalityMap(final Collection<?> coll)
  {
    Map count = new HashMap();

    for (Iterator<?> it = coll.iterator(); it.hasNext();)
    {
      Object obj = it.next();
      Integer c = (Integer) (count.get(obj));

      if (c == null)
      {
        count.put(obj, INTEGER_ONE);
      }
      else
      {
        count.put(obj, new Integer(c.intValue() + 1));
      }
    }
    return count;
  }

  private static final int getFreq(final Object obj, final Map<?, ?> freqMap)
  {
    Integer count = (Integer) freqMap.get(obj);
    return count != null ? count.intValue() : 0;
  }

  public static <T> T getFirst(Collection<T> collection)
  {
    if (collection.isEmpty()) return null;
    else return collection.iterator().next();
  }

  public static <T> T get(Iterable<T> collection, int idx)
  {
    Iterator<T> it = collection.iterator();
    T next = null;
    do
    {
      if (it.hasNext()) next = it.next();
      else return null;
      idx--;
    }
    while (idx >= 0);
    return next;
  }
}
