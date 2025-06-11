package com.prueba.postgres.embedded.application.exception;

import java.io.Serializable;

public class ValidationException extends RuntimeException implements Serializable {

  public ValidationException() {
    super();
  }

  public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public ValidationException(String message, Throwable t) {
    super(message, t);
  }

  public ValidationException(String message) {
    super(message);
  }

  public ValidationException(Throwable t) {
    super(t);
  }

  private static final long serialVersionUID = 3451387724349942951L;

}
