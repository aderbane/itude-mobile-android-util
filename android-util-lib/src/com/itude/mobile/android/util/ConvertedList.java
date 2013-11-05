package com.itude.mobile.android.util;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConvertedList<From, To> extends AbstractList<To>
{
  public static interface Converter<From, To>
  {
    public To convert(From from);
  }

  private final List<From>          _backing;
  private final Converter<From, To> _converter;

  public ConvertedList(Collection<From> backing, Converter<From, To> converter)
  {
    _backing = backing instanceof List ? (List<From>) backing : new ArrayList<From>(backing);
    _converter = converter;
  }

  @Override
  public To get(int location)
  {
    return _converter.convert(_backing.get(location));
  }

  @Override
  public int size()
  {
    return _backing.size();
  }
}
