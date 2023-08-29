package com.santalucia.cdc.core.mappers.budget;

/*import com.santalucia.cdc.core.domain.declaracion.DeclaracionDomain;*/

import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)

public interface DeclaracionDomainMapper {
  /**
   *
   * @param declaracionResource
   * @return
   */
  DeclaracionDomain toDomain (DeclaracionResource declaracionResource);

  /**
   *
   * @param declaracionDomain
   * @return
   */
  /*DeclaracionResource toResource (DeclaracionDomain declaracionDomain);*/

}
