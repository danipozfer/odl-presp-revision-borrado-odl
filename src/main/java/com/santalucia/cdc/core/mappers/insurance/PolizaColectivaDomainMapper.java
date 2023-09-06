package com.santalucia.cdc.core.mappers.insurance;

import com.santalucia.arq.ams.odl.polizas.colectivas.api.model.DatosPersonalesResource;
import com.santalucia.arq.ams.odl.polizas.colectivas.api.model.PolizaColectivaDetailResource;
import com.santalucia.arq.ams.odl.polizas.colectivas.api.model.PolizaColectivaResource;
import com.santalucia.cdc.core.domain.insurance.figuras.DatoPersonalDomain;
import com.santalucia.cdc.core.domain.insurance.polizas.PolizaDomain;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * Mapper de polizas colectivas
 *
 */
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = OffsetDateTimeMapper.class)
public interface PolizaColectivaDomainMapper {

  /***************/
  /** ONE TO ONE**/
  /***************/

  /**
   * Mapper entre dominio y resource
   * @param domain origen
   * @return destino
   */
  PolizaColectivaResource toResource(PolizaDomain domain);

  /**
   * Mapper entre dominio y resource
   * @param resource origen
   * @return destino
   */
  @Mapping(target = "datosIdentificativos.numCertificado", ignore = true)
  @Mapping(target="figuras.datosPersonales", qualifiedByName = "ignoreIndiv")
  PolizaDomain toDomain(PolizaColectivaDetailResource resource);

  /*****************/
  /** MANY TO MANY**/
  /*****************/

  /**
   * Mapper de lista de domain a objeto
   * @param lst entrada
   * @return salida
   */
  List<PolizaDomain> toDomainsfromResources(List<PolizaColectivaDetailResource> lst);

  /**
   * Mapper para ignorar los campos de individuales dentro de la lista
   * @param res
   * @return
   */
  @Mapping(target="codBenefic", ignore=true)
  @Mapping(target="descBenefic", ignore=true)
  @Mapping(target="fecNacCalculada", ignore=true)
  @Mapping(target="textoLibre", ignore=true)
  @Named("ignoreIndiv")
  DatoPersonalDomain toDatoColect(DatosPersonalesResource res);
}
