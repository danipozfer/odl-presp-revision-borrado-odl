package com.santalucia.cdc.core.mappers.budget;
//import com.santalucia.cdc.core.domain.presupuestoIndividual.PresupuestoIndividualResource;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface PresupuestoIndividualDomainMapper {

  /**
   *
   * @param presupuestoIndividualResource
   * @return
   */
  /*PresupuestoIndividualDomain toDomain (PresupuestoIndividualResource presupuestoIndividualResource);*/

  /**
   *
   * @param presupuestoIndividualDomain
   * @return
   */
  /*PresupuestoIndividualResource toResource (PresupuestoIndividualDomain presupuestoIndividualDomain);*/

  /**
   *
   * @param lst
   * @return
   */

  List<PresupuestoIndividualDomainMapper> toDomainsfromResources(List<PresupuestoIndividualResource> lst);
}
