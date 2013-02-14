package com.itude.mobile.android.util.exceptions;

import com.itude.mobile.android.util.ItudeException;

public class MBDateParsingException extends ItudeException
{

  /**
   * 
   */
  private static final long serialVersionUID = -3824251496748591427L;

  public MBDateParsingException(String msg)
  {
    super(msg);
  }

  public MBDateParsingException(String msg, Throwable throwable)
  {
    super(msg, throwable);
  }

}
