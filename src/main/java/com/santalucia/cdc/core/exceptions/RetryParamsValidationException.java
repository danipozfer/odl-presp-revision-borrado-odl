package com.santalucia.cdc.core.exceptions;

import com.santalucia.arq.ams.componentes.exceptions.core.SantaluciaCoreBusinessException;

import com.santalucia.cdc.core.exceptions.errors.AppErrorCodes;
public class RetryParamsValidationException extends SantaluciaCoreBusinessException {
  public RetryParamsValidationException(Throwable cause) {
    super(AppErrorCodes.ERROR_ITEMREADER_RETRY_PARAMS, cause);
  }

  public RetryParamsValidationException() {
    super(AppErrorCodes.ERROR_ITEMREADER_RETRY_PARAMS);
  }
}
