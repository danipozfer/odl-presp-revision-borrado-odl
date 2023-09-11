package com.santalucia.cdc.core.mappers.budget;

import com.santalucia.arq.ams.odl.presupuestos.historico.colectivo.api.model.EntityModelPresupuestoColectivoResource;
import com.santalucia.cdc.core.domain.budgets.collectivebudget.PresupuestoColectivoDomain;
import com.santalucia.cdc.core.domain.budgets.individualbudget.PresupuestoIndividualDomain;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface HistPresupuestoIndividualDomainMapper {
  /**
   *
   * @param presupuestoIndividualResource
   * @return
   */
  PresupuestoIndividualDomain toDomain (PresupuestoIndividualResource presupuestoIndividualResource);

  /**
   *
   * @param presupuestoIndividualDomain
   * @return
   */

  PresupuestoIndividualResource toResource(PresupuestoIndividualDomain presupuestoIndividualDomain);

  /**
   *
   * @param lst
   * @return
   */

  List<PresupuestoIndividualDomain> toDomainsfromResources(List<PresupuestoIndividualResource> lst);

}
