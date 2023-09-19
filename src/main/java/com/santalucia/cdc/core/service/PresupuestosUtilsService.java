package com.santalucia.cdc.core.service;

import java.util.UUID;

/**
 * Interfaz de utilidades
 *
 * @author Nfq
 *
 */
public interface PresupuestosUtilsService {
  /**
   * Retorna el UUID proporcionado, generando uno nuevo si es nulo
   *
   * @param uuid uuid a utilizar o null
   * @return UUID UUID generado
   */
  @SuppressWarnings("NullAway")
  UUID getOrSetUUID(UUID uuid);
}
