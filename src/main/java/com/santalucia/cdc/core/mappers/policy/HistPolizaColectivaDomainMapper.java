package com.santalucia.cdc.core.mappers.policy;

import com.santalucia.arq.ams.odl.historico.polizas.colectivas.api.model.DatosPersonalesResource;
import com.santalucia.arq.ams.odl.historico.polizas.colectivas.api.model.HistoricoPolizaColectivaDetailResource;
import com.santalucia.arq.ams.odl.historico.polizas.colectivas.api.model.HistoricoPolizaColectivaResource;
import com.santalucia.cdc.core.domain.policy.figuras.DatoPersonalDomain;
import com.santalucia.cdc.core.domain.policy.polizas.PolizaDomain;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * Mapper del historico de polizas colectivas
 *
 */
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = OffsetDateTimeMapper.class)
public interface HistPolizaColectivaDomainMapper {

  /***************/
  /** ONE TO ONE**/
  /***************/

  /**
   * @param domain
   * @return
   */
  HistoricoPolizaColectivaResource toResource(PolizaDomain domain);

  /**
   * @param resource
   * @return
   */
  @Mapping(target="datosIdentificativos.numCertificado", ignore=true)
  @Mapping(target="figuras.datosPersonales", qualifiedByName="ignoreIndivH")
  PolizaDomain toDomain(HistoricoPolizaColectivaDetailResource resource);

  /*****************/
  /** MANY TO MANY**/
  /*****************/

  /**
   * Transformacion de Domain a Resource
   * @param list lista del historico de polizas
   * @return lista de polizas
   */
  List<PolizaDomain> toDomainsfromResources(List<HistoricoPolizaColectivaDetailResource> list);

  /**
   * Mapper para ignorar los campos de individuales dentro de la lista
   * @param res
   * @return
   */
  @Mapping(target="codBenefic", ignore=true)
  @Mapping(target="descBenefic", ignore=true)
  @Mapping(target="fecNacCalculada", ignore=true)
  @Mapping(target="textoLibre", ignore=true)
  @Named("ignoreIndivH")
  DatoPersonalDomain toDatoColect(DatosPersonalesResource res);

}
