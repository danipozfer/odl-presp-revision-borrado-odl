package com.santalucia.cdc.core.mappers.budget;


import com.santalucia.arq.ams.odl.presupuestos.declaraciones.api.model.DeclaracionRequestBodyResource;
import com.santalucia.arq.ams.odl.presupuestos.declaraciones.api.model.EntityModelDeclaracionResource;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true), uses = OffsetDateTimeMapper.class)

public interface DeclaracionDomainMapper {
  /**
   * @param declaracionResource
   * @return
   */
  DeclaracionDomain toDomain(EntityModelDeclaracionResource declaracionResource);

  /**
   * @param declaracionDomain
   * @return
   */
  DeclaracionRequestBodyResource toResource(DeclaracionDomain declaracionDomain);

  /**
   * @param lst
   * @return
   */
  List<DeclaracionDomain> toDomainsfromResources(List<EntityModelDeclaracionResource> lst);


}
