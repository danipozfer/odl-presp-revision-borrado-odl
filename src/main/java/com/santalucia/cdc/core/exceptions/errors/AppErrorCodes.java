package com.santalucia.cdc.core.exceptions.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.santalucia.arq.ams.componentes.exceptions.errors.ErrorCode;
@Getter
public enum AppErrorCodes implements ErrorCode {

  HASH_GENERATION("app.error.hash.generation"),

  POLICY_NOT_FOUND("app.error.policy.not.found"),

  POLICY_HISTORIC_STATE_ERROR("app.error.policy.historic.error"),

  API_CONNECTION("app.error.api.connection"),

  ERROR_ITEMREADER_FEIGN("app.error.itemreader.feign"),

  ERROR_ITEMREADER_RETRY_PARAMS("app.error.itemreader.retry.params");
  private final String code;

  /**
   * Constructor
   * @param code codigo de error
   */
  private AppErrorCodes(String code) {
    this.code = code;
  }

  /**
   * Obtiene el codigo de error
   */
  @Override
  public String getCode() {
    return this.code;
  }
}
