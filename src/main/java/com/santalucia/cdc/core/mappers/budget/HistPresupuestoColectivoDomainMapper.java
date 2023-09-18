package com.santalucia.cdc.core.mappers.budget;

import com.santalucia.arq.ams.odl.presupuestos.historico.colectivo.api.model.EntityModelPresupuestoColectivoResource;
import com.santalucia.arq.ams.odl.presupuestos.historico.colectivo.api.model.PresupuestoColectivoRequestBodyResource;
import com.santalucia.cdc.core.domain.budgets.collectivebudget.PresupuestoColectivoDomain;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@SuppressWarnings("NullAway")
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true), uses = OffsetDateTimeMapper.class)
public interface HistPresupuestoColectivoDomainMapper {
  /**
   * @param presupuestoColectivoResource
   * @return
   */
  PresupuestoColectivoDomain toDomain(EntityModelPresupuestoColectivoResource presupuestoColectivoResource);

  /**
   * @param presupuestoColectivoDomain
   * @return
   */
  PresupuestoColectivoRequestBodyResource toResource(PresupuestoColectivoDomain presupuestoColectivoDomain);

  /**
   * @param embedded
   * @return
   */

  List<PresupuestoColectivoDomain> toDomainsfromResources(List<EntityModelPresupuestoColectivoResource> embedded);


}
