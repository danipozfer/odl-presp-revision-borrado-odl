package com.santalucia.cdc.core.mappers.insurance;

import com.santalucia.cdc.core.domain.insurance.polizas.PolizaDomain;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper historico de polizas individuales
 *
 */
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = OffsetDateTimeMapper.class)
public interface HistPolizaIndividualDomainMapper {

  /***************/
  /** ONE TO ONE**/
  /***************/

  /**
   * Mapper entre dominio y resource
   * @param domain origen
   * @return destino
   */
  @Mapping(target = "metadata.fecFinDatos", source="metadata.fecFinDatos")
  @Mapping(target = "fechasYEstados.fechas.fecEfectoComisionPol", source="fechasYEstados.fechas.fecEfectoComisionPol")
  @Mapping(target = "fechasYEstados.fechas.fecTarificacion", source="fechasYEstados.fechas.fecTarificacion")
  @Mapping(target = "estructuraComercial.codMediador", source="estructuraComercial.codMediador")
  HistoricoPolizaIndividualCertificadoResource toResource(PolizaDomain domain);

  /**
   * Mapper entre dominio y resource
   * @param resource origen
   * @return destino
   */
  @Mapping(target = "subcolectivos", ignore = true)
  @Mapping(target = "datosIdentificativos.codColect", ignore = true)
  @Mapping(target = "datosIdentificativos.descColect", ignore = true)
  @Mapping(target = "datosIdentificativos.codTipoContr", ignore = true)
  @Mapping(target = "datosIdentificativos.descTipoContr", ignore = true)
  @Mapping(target = "figuras.codSectorColect", ignore = true)
  @Mapping(target = "figuras.descSectorColect", ignore = true)
  @Mapping(target = "figuras.numPersColect", ignore = true)
  @Mapping(target = "figuras.numTelefColect", ignore = true)
  @Mapping(target = "metadata.fecFinDatos", source="metadata.fecFinDatos")
  @Mapping(target = "fechasYEstados.fechas.fecEfectoComisionPol", source="fechasYEstados.fechas.fecEfectoComisionPol")
  @Mapping(target = "fechasYEstados.fechas.fecTarificacion", source="fechasYEstados.fechas.fecTarificacion")
  @Mapping(target = "estructuraComercial.codMediador", source="estructuraComercial.codMediador")
  PolizaDomain toDomain(HistoricoPolizaIndividualCertificadoDetailResource resource);

  /*****************/
  /** MANY TO MANY**/
  /*****************/

  /**
   * Mapper de lista de domain a objeto
   * @param lst entrada
   * @return salida
   */
  List<PolizaDomain> toDomainsfromResources(List<HistoricoPolizaIndividualCertificadoDetailResource> lst);

}
