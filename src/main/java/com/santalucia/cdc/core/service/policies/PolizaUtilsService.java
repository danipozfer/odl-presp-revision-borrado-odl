package com.santalucia.cdc.core.service.policies;



import java.util.UUID;

/**
 * Servicio de utilidades de pólizas
 *
 */
public interface PolizaUtilsService {

  /**
   * Retorna el UUID proporcionado, generando uno nuevo si es nulo
   *
   * @param uuid uuid a utilizar o null
   * @return UUID UUID generado
   */
  UUID getOrSetUUID(UUID uuid);
}
