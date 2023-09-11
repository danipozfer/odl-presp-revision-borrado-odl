package com.santalucia.cdc.core.service.policies;

import com.santalucia.cdc.core.domain.policy.hash.HashDomain;
import com.santalucia.cdc.core.domain.policy.polizas.PolizaDomain;

import java.util.UUID;

/**
 * Servicio de utilidades de pólizas
 *
 */
public interface PolizaUtilsService {
  /**
   * Determina si la poliza es un certificado
   *
   * @param poliza poliza a determinar
   * @return boolean indicador de si es certificado
   */
  boolean isCertificado(PolizaDomain poliza);

  /**
   * Determina si la poliza es un individual
   *
   * @param poliza poliza a determinar
   * @return boolean indicador de si es individual
   */
  boolean isIndividual(PolizaDomain poliza);

  /**
   * Determina si la poliza es colectiva
   *
   * @param poliza poliza a determinar
   * @return boolean indicador de si es colectiva
   */
  boolean isColectiva(PolizaDomain poliza);

  /**
   * Generador de hash SHA-256 para la poliza
   *
   * @param poliza poliza a generar
   * @return String devuelve el hash generado
   */
  String hashGenerator(HashDomain poliza);

  /**
   * Retorna el UUID proporcionado, generando uno nuevo si es nulo
   *
   * @param uuid uuid a utilizar o null
   * @return UUID UUID generado
   */
  UUID getOrSetUUID(UUID uuid);
}
