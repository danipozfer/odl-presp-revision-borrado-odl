package com.santalucia.cdc.core.mappers.budget;

import com.santalucia.arq.ams.odl.presupuestos.individual.api.model.DatoIdentificativoResource;
import com.santalucia.arq.ams.odl.presupuestos.individual.api.model.DomicilioPersResource;
import com.santalucia.arq.ams.odl.presupuestos.individual.api.model.EntityModelPresupuestoIndividualResource;
import com.santalucia.arq.ams.odl.presupuestos.individual.api.model.PresupuestoIndividualRequestBodyResource;
import com.santalucia.cdc.core.domain.budgets.common.figure.contactdata.DomicilioPersDomain;
import com.santalucia.cdc.core.domain.budgets.common.identif.DatoIdentificativoDomain;
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
  @Mapping(source = "estructuraGeografica.domicilioPresupuesto.codNucleoPobla", target = "estructuraGeografica.domicilioPresupuesto.codNucPobla")
  @Mapping(source = "estructuraGeografica.domicilioPresupuesto.descDomicilio", target = "estructuraGeografica.domicilioPresupuesto.desDomicilio")
  @Mapping(source = "datoCobro.datoMedioCobro.datoCobroFisico.codNucleoPobla", target = "datoCobro.datoMedioCobro.datoCobroFisico.codNucPobla")
  @Mapping(source = "datoCobro.datoMedioCobro.datoCobroFisico.conEntSingular", target = "datoCobro.datoMedioCobro.datoCobroFisico.codEntSingular")
  @Mapping(source = "datoCobro.datoMedioCobro.datoCobroFisico.localidad.descMDL", target = "datoCobro.datoMedioCobro.datoCobroFisico.localidad.descOrigen")
  @Mapping(source = "estructuraComercial.tipoColaborador.codTMDL", target = "estructuraComercial.tipoColaborador.codMDL")
  PresupuestoIndividualDomain toDomain(EntityModelPresupuestoIndividualResource body);


  /**
   * @param presupuestoIndividualDomain
   * @return
   */
  @Mapping(source = "estructuraGeografica.domicilioPresupuesto.codNucPobla", target = "estructuraGeografica.domicilioPresupuesto.codNucleoPobla")
  @Mapping(source = "estructuraGeografica.domicilioPresupuesto.desDomicilio", target = "estructuraGeografica.domicilioPresupuesto.descDomicilio")
  @Mapping(source = "datoCobro.datoMedioCobro.datoCobroFisico.codNucPobla", target = "datoCobro.datoMedioCobro.datoCobroFisico.codNucleoPobla")
  @Mapping(source = "datoCobro.datoMedioCobro.datoCobroFisico.codEntSingular", target = "datoCobro.datoMedioCobro.datoCobroFisico.conEntSingular")
  @Mapping(source = "datoCobro.datoMedioCobro.datoCobroFisico.localidad.descOrigen", target = "datoCobro.datoMedioCobro.datoCobroFisico.localidad.descMDL")
  @Mapping(source = "estructuraComercial.tipoColaborador.codMDL", target = "estructuraComercial.tipoColaborador.codTMDL")
  PresupuestoIndividualRequestBodyResource toResource(PresupuestoIndividualDomain presupuestoIndividualDomain);

  /**
   * @param embedded
   * @return
   */

  List<PresupuestoIndividualDomain> toDomainsfromResources(List<EntityModelPresupuestoIndividualResource> embedded);

  @Mapping(source = "descDomicilio", target = "desDomicilio")
  DomicilioPersDomain domPersOut(com.santalucia.arq.ams.odl.presupuestos.individual.api.model.DomicilioPersResource in);

  @Mapping(source = "desDomicilio", target = "descDomicilio")
  DomicilioPersResource domPersOutp(DomicilioPersDomain in);

  @Mapping(source = "indOrigenRecomendador", target = "indOrigRecomendador")
  DatoIdentificativoDomain datoIdenOut(DatoIdentificativoResource in);

  @Mapping(source = "indOrigRecomendador", target = "indOrigenRecomendador")
  DatoIdentificativoResource datoIdenOutp(DatoIdentificativoDomain in);
}
