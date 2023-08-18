package com.santalucia.cdc.core.exceptions;

import com.santalucia.arq.ams.componentes.exceptions.core.SantaluciaCoreBusinessException;
import com.santalucia.cdc.core.exceptions.errors.AppErrorCodes;
public class RecibosFeignException extends SantaluciaCoreBusinessException {

  public RecibosFeignException(Throwable cause) {
    super(AppErrorCodes.ERROR_ITEMREADER_FEIGN, cause);
  }

  public RecibosFeignException() {
    super(AppErrorCodes.ERROR_ITEMREADER_FEIGN);
  }
}
