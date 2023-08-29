package com.santalucia.cdc.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santalucia.cdc.core.domain.insurance.hash.HashDomain;
import com.santalucia.cdc.core.domain.insurance.polizas.PolizaDomain;
import com.santalucia.cdc.core.exceptions.HashGenerationException;
import com.santalucia.cdc.core.service.PolizaUtilsService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class DefaultPolizaUtilsService implements PolizaUtilsService {
  private final ObjectMapper objectMapper;

  /**
   * Constructor de clase
   * @param oMapper Object Mapper
   */
  public DefaultPolizaUtilsService(ObjectMapper oMapper) {
    this.objectMapper = oMapper;
  }

  /**
   * Determina si la poliza es un certificado
   * @param poliza poliza a determinar
   * @return boolean indicador de si es certificado
   */
  @Override
  public boolean isCertificado(PolizaDomain poliza) {
    return "C".equals(poliza.getDatosIdentificativos().getCodTipoPoliza())
      && poliza.getDatosIdentificativos().getNumCertificado() != null
      && !"0".equals(poliza.getDatosIdentificativos().getNumCertificado());
  }

  /**
   * Determina si la poliza es un individual
   * @param poliza poliza a determinar
   * @return boolean indicador de si es individual
   */
  @Override
  public boolean isIndividual(PolizaDomain poliza) {
    return "I".equals(poliza.getDatosIdentificativos().getCodTipoPoliza());
  }

  /**
   * Determina si la poliza es colectiva
   * @param poliza poliza a determinar
   * @return boolean indicador de si es colectiva
   */
  @Override
  public boolean isColectiva(PolizaDomain poliza) {
    return "C".equals(poliza.getDatosIdentificativos().getCodTipoPoliza()) &&
      !isCertificado(poliza);
  }


  /**
   * Generador de hash SHA-256 para la poliza
   * @param poliza poliza a generar
   * @return String devuelve el hash generado
   */
  @Override
  public String hashGenerator(HashDomain poliza) {
    String hash = null;
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      digest.reset();
      digest.update(objectMapper.writeValueAsString(poliza).getBytes(StandardCharsets.UTF_8));
      hash = String.format("%040x", new BigInteger(1, digest.digest()));
    } catch (NoSuchAlgorithmException nsae) {
      throw new HashGenerationException(nsae);
    } catch (JsonProcessingException jpe) {
      throw new HashGenerationException(jpe);
    }
    return hash;
  }

  /**
   * Retorna el UUID proporcionado, generando uno nuevo si es nulo
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
