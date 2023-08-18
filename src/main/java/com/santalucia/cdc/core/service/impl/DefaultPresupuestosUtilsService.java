package com.santalucia.cdc.core.service.impl;

import com.santalucia.cdc.core.service.PresupuestosUtilsService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Servicio con utilidades de presupuesto
 *
 * @author Nfq
 */
@Service
@NoArgsConstructor
public class DefaultPresupuestosUtilsService implements PresupuestosUtilsService {
  /**
   * Retorna el UUID proporcionado, generando uno nuevo si es nulo
   *
   * @param uuid uuid a utilizar o null
   * @return UUID UUID generado
   */
  @Override
  public UUID getOrSetUUID(UUID uuid) {
    UUID result = uuid;
    if (uuid == null) {
      result = UUID.randomUUID();
    }
    return result;
  }
}
