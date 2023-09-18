package com.santalucia.cdc.core.mappers.budget;

import com.santalucia.arq.ams.odl.presupuestos.individual.api.model.EntityModelPresupuestoIndividualResource;
import com.santalucia.arq.ams.odl.presupuestos.individual.api.model.PresupuestoIndividualRequestBodyResource;
import com.santalucia.cdc.core.domain.budgets.individualbudget.PresupuestoIndividualDomain;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface PresupuestoIndividualDomainMapper {
  /**
   * @param body
   * @return
   */

  PresupuestoIndividualDomain toDomain(EntityModelPresupuestoIndividualResource body);


  /**
   * @param presupuestoIndividualDomain
   * @return
   */
  PresupuestoIndividualRequestBodyResource toResource(PresupuestoIndividualDomain presupuestoIndividualDomain);

  /**
   * @param embedded
   * @return
   */

  List<PresupuestoIndividualDomain> toDomainsfromResources(List<EntityModelPresupuestoIndividualResource> embedded);


}
