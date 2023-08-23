package com.santalucia.cdc.core.mappers;

import com.santalucia.cdc.core.domain.budgets.common.securedObject.ObjetoAseguradoDomain;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;

public interface ObjetoAseguradoDomainMapper {

  ObjetoAseguradoDomain toDomain (ObjetoAseguradoResource objetoAseguradoResource);

  /**
   *
   * @param objetoAseguradoDomain
   * @return
   */
  //ObjetoAseguradoResource toResource (ObjetoAseguradoDomain objetoAseguradoDomain);
}
