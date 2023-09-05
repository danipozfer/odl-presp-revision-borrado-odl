package com.santalucia.cdc.core.mappers.budget;

//import com.santalucia.cdc.core.domain.presupuestoColectivo.PresupuestoColectivoResource;
import com.santalucia.arq.ams.odl.presupuestos.colectivo.api.model.EntityModelPresupuestoColectivoResource;
import com.santalucia.arq.ams.odl.presupuestos.colectivo.api.model.PresupuestoColectivoRequestBodyResource;
import com.santalucia.cdc.core.domain.budgets.collectiveBudget.PresupuestoColectivoDomain;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)

public interface PresupuestoColectivoDomainMapper {
  /**
   *
   * @param presupuestoColectivoResource
   * @return
   */
  PresupuestoColectivoDomain toDomain (EntityModelPresupuestoColectivoResource presupuestoColectivoResource);

  /**
   *
   * @param presupuestoColectivoDomain
   * @return
   */
  PresupuestoColectivoRequestBodyResource toResource (PresupuestoColectivoDomain presupuestoColectivoDomain);

}
