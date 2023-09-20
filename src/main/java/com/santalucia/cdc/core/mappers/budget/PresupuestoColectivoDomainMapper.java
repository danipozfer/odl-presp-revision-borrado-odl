package com.santalucia.cdc.core.mappers.budget;


import com.santalucia.arq.ams.odl.presupuestos.colectivo.api.model.DomicilioPersResource;
import com.santalucia.arq.ams.odl.presupuestos.colectivo.api.model.EntityModelPresupuestoColectivoResource;
import com.santalucia.arq.ams.odl.presupuestos.colectivo.api.model.PresupuestoColectivoRequestBodyResource;
import com.santalucia.cdc.core.domain.budgets.collectivebudget.PresupuestoColectivoDomain;
import com.santalucia.cdc.core.domain.budgets.common.figure.contactdata.DomicilioPersDomain;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@SuppressWarnings("NullAway")
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true), uses = OffsetDateTimeMapper.class)
public interface PresupuestoColectivoDomainMapper {
  /**
   * @param presupuestoColectivoResource
   * @return
   */
  @Mapping(source = "estructuraGeografica.domicilioPresupuesto.descDomicilio", target = "estructuraGeografica.domicilioPresupuesto.desDomicilio")
  @Mapping(source = "datoCobro.datoMedioCobro.datoCobroFisico.conEntSingular", target = "datoCobro.datoMedioCobro.datoCobroFisico.codEntSingular")
  PresupuestoColectivoDomain toDomain(EntityModelPresupuestoColectivoResource presupuestoColectivoResource);

  /**
   * @param presupuestoColectivoDomain
   * @return
   */
  @Mapping(source = "estructuraGeografica.domicilioPresupuesto.desDomicilio", target = "estructuraGeografica.domicilioPresupuesto.descDomicilio")
  @Mapping(source = "datoCobro.datoMedioCobro.datoCobroFisico.codEntSingular", target = "datoCobro.datoMedioCobro.datoCobroFisico.conEntSingular")
  PresupuestoColectivoRequestBodyResource toResource(PresupuestoColectivoDomain presupuestoColectivoDomain);

  /**
   * @param embedded
   * @return
   */

  List<PresupuestoColectivoDomain> toDomainsfromResources(List<EntityModelPresupuestoColectivoResource> embedded);

  /**
   * @param in
   * @return
   */

  @Mapping(source = "descDomicilio", target = "desDomicilio")
  DomicilioPersDomain domPersOut(DomicilioPersResource in);

  /**
   * @param in
   * @return
   */

  @Mapping(source = "desDomicilio", target = "descDomicilio")
  com.santalucia.arq.ams.odl.presupuestos.colectivo.api.model.DomicilioPersResource domPersOutp(DomicilioPersDomain in);

}
