package com.santalucia.cdc.core.mappers.budget;

import com.santalucia.arq.ams.odl.historico.presupuestos.declaraciones.api.model.EntityModelDeclaracionResource;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface HistDeclaracionDomainMapper {
  /**
   *
   * @param declaracionResource
   * @return
   */
  DeclaracionDomain toDomain (com.santalucia.arq.ams.odl.historico.presupuestos.declaraciones.api.model.EntityModelDeclaracionResource declaracionResource);

  /**
   *
   * @param embedded
   * @return
   */

  List<DeclaracionDomain> toDomainsfromResourcesEntityModel(List<com.santalucia.arq.ams.odl.presupuestos.declaraciones.api.model.EntityModelDeclaracionResource> embedded);

  /**
   *
   * @param embedded
   * @return
   */
  List<DeclaracionDomain> toDomainsfromResources(List<EntityModelDeclaracionResource> embedded);
}
