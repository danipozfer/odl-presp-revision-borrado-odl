package com.santalucia.cdc.core.mappers.budget;

import com.santalucia.arq.ams.odl.recibos.api.model.ObjetoAseguradoResource;
import com.santalucia.cdc.core.domain.budgets.common.securedobject.ObjetosAseguradosDomain;

public interface ObjetoAseguradoDomainMapper {

  ObjetosAseguradosDomain toDomain (ObjetoAseguradoResource objetoAseguradoResource);

  /**
   *
   * @param objetoAseguradoDomain
   * @return
   */
  //ObjetoAseguradoResource toResource (ObjetoAseguradoDomain objetoAseguradoDomain);
}
