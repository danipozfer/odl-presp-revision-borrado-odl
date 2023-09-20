package com.santalucia.cdc.core.mappers.budget;

import com.santalucia.arq.ams.odl.presupuestos.individual.api.model.DomicilioPersResource;
import com.santalucia.arq.ams.odl.presupuestos.individual.api.model.EntityModelPresupuestoIndividualResource;
import com.santalucia.arq.ams.odl.presupuestos.individual.api.model.PresupuestoIndividualRequestBodyResource;
import com.santalucia.cdc.core.domain.budgets.common.figure.contactdata.DomicilioPersDomain;
import com.santalucia.cdc.core.domain.budgets.individualbudget.PresupuestoIndividualDomain;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@SuppressWarnings("NullAway")
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true), uses = OffsetDateTimeMapper.class)
public interface PresupuestoIndividualDomainMapper {


  /**
   * @param body
   * @return
   */

  @Mapping(source = "estructuraGeografica.domicilioPresupuesto.descDomicilio", target = "estructuraGeografica.domicilioPresupuesto.desDomicilio")
  @Mapping(source = "datoCobro.datoMedioCobro.datoCobroFisico.conEntSingular", target = "datoCobro.datoMedioCobro.datoCobroFisico.codEntSingular")
  PresupuestoIndividualDomain toDomain(EntityModelPresupuestoIndividualResource body);


  /**
   * @param presupuestoIndividualDomain
   * @return
   */

  @Mapping(source = "estructuraGeografica.domicilioPresupuesto.desDomicilio", target = "estructuraGeografica.domicilioPresupuesto.descDomicilio")
  @Mapping(source = "datoCobro.datoMedioCobro.datoCobroFisico.codEntSingular", target = "datoCobro.datoMedioCobro.datoCobroFisico.conEntSingular")
  PresupuestoIndividualRequestBodyResource toResource(PresupuestoIndividualDomain presupuestoIndividualDomain);

  /**
   * @param embedded
   * @return
   */

  List<PresupuestoIndividualDomain> toDomainsfromResources(List<EntityModelPresupuestoIndividualResource> embedded);

  /**
   * @param in
   * @return
   */

  @Mapping(source = "descDomicilio", target = "desDomicilio")
  DomicilioPersDomain domPersOut(com.santalucia.arq.ams.odl.presupuestos.individual.api.model.DomicilioPersResource in);

  /**
   * @param in
   * @return
   */

  @Mapping(source = "desDomicilio", target = "descDomicilio")
  DomicilioPersResource domPersOutp(DomicilioPersDomain in);


}
