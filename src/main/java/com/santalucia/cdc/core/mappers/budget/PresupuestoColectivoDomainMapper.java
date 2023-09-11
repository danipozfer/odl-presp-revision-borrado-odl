package com.santalucia.cdc.core.mappers.budget;

//import com.santalucia.cdc.core.domain.presupuestoColectivo.PresupuestoColectivoResource;
import com.santalucia.arq.ams.odl.presupuestos.colectivo.api.model.EntityModelPresupuestoColectivoResource;
import com.santalucia.arq.ams.odl.presupuestos.colectivo.api.model.PresupuestoColectivoRequestBodyResource;
import com.santalucia.cdc.core.domain.budgets.collectivebudget.PresupuestoColectivoDomain;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface PresupuestoColectivoDomainMapper {
  /**
   *
   * @param presupuestoColectivoResource
   * @return
   */
  //PresupuestoColectivoDomain toDomain (EntityModelPresupuestoColectivoResource presupuestoColectivoResource);

  /**
   *
   * @param presupuestoColectivoDomain
   * @return
   */
  //PresupuestoColectivoRequestBodyResource toResource (PresupuestoColectivoDomain presupuestoColectivoDomain);

  /**
   *
   * @param lst
   * @return
   */

    //List<PresupuestoColectivoDomain> toDomainsfromResources(List<PresupuestoColectivoResource> lst);

}
