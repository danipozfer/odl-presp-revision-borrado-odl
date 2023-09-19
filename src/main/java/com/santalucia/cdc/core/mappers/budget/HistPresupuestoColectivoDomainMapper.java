package com.santalucia.cdc.core.mappers.budget;

import com.santalucia.arq.ams.odl.presupuestos.historico.colectivo.api.model.DatoIdentificativoResource;
import com.santalucia.arq.ams.odl.presupuestos.historico.colectivo.api.model.DomicilioPersResource;
import com.santalucia.arq.ams.odl.presupuestos.historico.colectivo.api.model.EntityModelPresupuestoColectivoResource;
import com.santalucia.arq.ams.odl.presupuestos.historico.colectivo.api.model.PresupuestoColectivoRequestBodyResource;
import com.santalucia.cdc.core.domain.budgets.collectivebudget.PresupuestoColectivoDomain;
import com.santalucia.cdc.core.domain.budgets.common.figure.contactdata.DomicilioPersDomain;
import com.santalucia.cdc.core.domain.budgets.common.identif.DatoIdentificativoDomain;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@SuppressWarnings("NullAway")
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true), uses = OffsetDateTimeMapper.class)
public interface HistPresupuestoColectivoDomainMapper {
  /**
   * @param presupuestoColectivoResource
   * @return
   */
  @Mapping(source = "estructuraGeografica.domicilioPresupuesto.codNucleoPobla", target = "estructuraGeografica.domicilioPresupuesto.codNucPobla")
  @Mapping(source = "estructuraGeografica.domicilioPresupuesto.descDomicilio", target = "estructuraGeografica.domicilioPresupuesto.desDomicilio")

  @Mapping(source = "datoCobro.datoMedioCobro.datoCobroFisico.codNucleoPobla", target = "datoCobro.datoMedioCobro.datoCobroFisico.codNucPobla")
  @Mapping(source = "datoCobro.datoMedioCobro.datoCobroFisico.conEntSingular", target = "datoCobro.datoMedioCobro.datoCobroFisico.codEntSingular")

  @Mapping(source = "datoCobro.datoMedioCobro.datoCobroFisico.localidad.descMDL", target = "datoCobro.datoMedioCobro.datoCobroFisico.localidad.descOrigen")

  @Mapping(source = "estructuraComercial.tipoColaborador.codTMDL", target = "estructuraComercial.tipoColaborador.codMDL")

  PresupuestoColectivoDomain toDomain(EntityModelPresupuestoColectivoResource presupuestoColectivoResource);

  /**
   * @param presupuestoColectivoDomain
   * @return
   */
  @Mapping(source = "estructuraGeografica.domicilioPresupuesto.codNucPobla", target = "estructuraGeografica.domicilioPresupuesto.codNucleoPobla")
  @Mapping(source = "estructuraGeografica.domicilioPresupuesto.desDomicilio", target = "estructuraGeografica.domicilioPresupuesto.descDomicilio")
  @Mapping(source = "datoCobro.datoMedioCobro.datoCobroFisico.codNucPobla", target = "datoCobro.datoMedioCobro.datoCobroFisico.codNucleoPobla")
  @Mapping(source = "datoCobro.datoMedioCobro.datoCobroFisico.codEntSingular", target = "datoCobro.datoMedioCobro.datoCobroFisico.conEntSingular")
  @Mapping(source = "datoCobro.datoMedioCobro.datoCobroFisico.localidad.descOrigen", target = "datoCobro.datoMedioCobro.datoCobroFisico.localidad.descMDL")
  @Mapping(source = "estructuraComercial.tipoColaborador.codMDL", target = "estructuraComercial.tipoColaborador.codTMDL")
  PresupuestoColectivoRequestBodyResource toResource(PresupuestoColectivoDomain presupuestoColectivoDomain);

  /**
   * @param embedded
   * @return
   */

  List<PresupuestoColectivoDomain> toDomainsfromResources(List<EntityModelPresupuestoColectivoResource> embedded);
  @Mapping(source = "descDomicilio",target = "desDomicilio")
  DomicilioPersDomain domPersOut (DomicilioPersResource in);

  @Mapping(source = "desDomicilio",target = "descDomicilio")
  DomicilioPersResource domPersOutp (DomicilioPersDomain in);

  @Mapping(source = "indOrigenRecomendador",target = "indOrigRecomendador")
  DatoIdentificativoDomain datoIdenOut(com.santalucia.arq.ams.odl.presupuestos.historico.colectivo.api.model.DatoIdentificativoResource in);

  @Mapping(source = "indOrigRecomendador",target = "indOrigenRecomendador")
  DatoIdentificativoResource datoIdenOutp(DatoIdentificativoDomain in);


}
