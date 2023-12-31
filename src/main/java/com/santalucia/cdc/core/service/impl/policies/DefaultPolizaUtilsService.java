package com.santalucia.cdc.core.service.impl.policies;

import com.santalucia.cdc.core.service.policies.PolizaUtilsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultPolizaUtilsService implements PolizaUtilsService {

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
