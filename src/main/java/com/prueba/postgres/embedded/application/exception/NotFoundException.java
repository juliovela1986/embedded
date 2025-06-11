package com.prueba.postgres.embedded.application.exception;

import java.io.Serializable;

public class NotFoundException extends RuntimeException implements Serializable {

  public NotFoundException() {
    super();
  }

  public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public NotFoundException(String message, Throwable t) {
    super(message, t);
  }

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(Throwable t) {
    super(t);
  }

  private static final long serialVersionUID = -7345348810829719283L;

}