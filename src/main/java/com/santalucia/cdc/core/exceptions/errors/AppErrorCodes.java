package com.santalucia.cdc.core.exceptions.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.santalucia.arq.ams.componentes.exceptions.errors.ErrorCode;
@Getter
@AllArgsConstructor
public enum AppErrorCodes implements ErrorCode {
  ERROR_ITEMREADER_FEIGN("app.error.itemreader.feign"),

  ERROR_ITEMREADER_RETRY_PARAMS("app.error.itemreader.retry.params");
  private final String code;

}
