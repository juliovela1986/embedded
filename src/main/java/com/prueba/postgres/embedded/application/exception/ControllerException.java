package com.prueba.postgres.embedded.application.exception;

import java.io.Serial;
import java.io.Serializable;

public class ControllerException extends RuntimeException implements Serializable {

  public ControllerException() {
    super();
  }

  public ControllerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public ControllerException(String message, Throwable t) {
    super(message, t);
  }

  public ControllerException(String message) {
    super(message);
  }

  public ControllerException(Throwable t) {
    super(t);
  }

  @Serial
  private static final long serialVersionUID = -1040763955897468114L;

}