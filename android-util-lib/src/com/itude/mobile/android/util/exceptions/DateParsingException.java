package com.itude.mobile.android.util.exceptions;

import com.itude.mobile.android.util.ItudeException;

public class DateParsingException extends ItudeException
{

  /**
   * 
   */
  private static final long serialVersionUID = -3824251496748591427L;

  public DateParsingException(String msg)
  {
    super(msg);
  }

  public DateParsingException(String msg, Throwable throwable)
  {
    super(msg, throwable);
  }

}
