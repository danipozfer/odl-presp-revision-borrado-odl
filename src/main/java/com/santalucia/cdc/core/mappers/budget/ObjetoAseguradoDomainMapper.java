package com.santalucia.cdc.core.mappers.budget;

import com.santalucia.arq.ams.odl.recibos.api.model.ObjetoAseguradoResource;
import com.santalucia.cdc.core.domain.budgets.common.securedobject.ObjetosAseguradosDomain;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface ObjetoAseguradoDomainMapper {

  ObjetosAseguradosDomain toDomain (ObjetoAseguradoResource objetoAseguradoResource);

  /**
   *
   * @param objetoAseguradoDomain
   * @return
   */
  //ObjetoAseguradoResource toResource (ObjetoAseguradoDomain objetoAseguradoDomain);

  /**
   *
   * @param lst
   * @return
   */


  //List<ObjetosAseguradosDomain> toDomainsfromResources(List<ObjetoAseguradoResource> lst);
}
