package com.santalucia.cdc.core.mappers.budget;

import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface HistDeclaracionDomainMapper {
  /**
   *
   * @param declaracionResource
   * @return
   */
  /*DeclaracionDomain toDomain (DeclaracionResource declaracionResource);*/

  /**
   *
   * @param declaracionDomain
   * @return
   */
  /*DeclaracionResource toResource (DeclaracionDomain declaracionDomain);*/
}
