package com.santalucia.cdc.core.mappers.budget;

import com.santalucia.arq.ams.odl.historico.presupuestos.declaraciones.api.model.DeclaracionRequestBodyResource;
import com.santalucia.arq.ams.odl.historico.presupuestos.declaraciones.api.model.EntityModelDeclaracionResource;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@SuppressWarnings("NullAway")
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true), uses = OffsetDateTimeMapper.class)
public interface HistDeclaracionDomainMapper {
  /**
   * @param declaracionResource
   * @return
   */
  DeclaracionDomain toDomain(com.santalucia.arq.ams.odl.historico.presupuestos.declaraciones.api.model.EntityModelDeclaracionResource declaracionResource);

  /**
   * @param declaracion
   * @return
   */

  DeclaracionRequestBodyResource toResource(DeclaracionDomain declaracion);


  /**
   * @param embedded
   * @return
   */
  List<DeclaracionDomain> toDomainsfromResources(List<EntityModelDeclaracionResource> embedded);


}
