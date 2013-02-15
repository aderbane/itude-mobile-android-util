package com.itude.mobile.android.util.exceptions;

import com.itude.mobile.android.util.ItudeException;

public class DataParsingException extends ItudeException
{

  /**
   * 
   */
  private static final long serialVersionUID = -1726186662862494940L;

  public DataParsingException(String msg)
  {
    super(msg);
  }

  public DataParsingException(String msg, Throwable throwable)
  {
    super(msg, throwable);
  }

}
