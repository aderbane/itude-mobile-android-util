package com.itude.mobile.android.util.exceptions;

import com.itude.mobile.android.util.ItudeException;

public class MBDataParsingException extends ItudeException
{

  /**
   * 
   */
  private static final long serialVersionUID = -1726186662862494940L;

  public MBDataParsingException(String msg)
  {
    super(msg);
  }

  public MBDataParsingException(String msg, Throwable throwable)
  {
    super(msg, throwable);
  }

}
