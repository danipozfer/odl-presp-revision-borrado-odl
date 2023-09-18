package com.santalucia.cdc.core.mappers.budget;

import com.santalucia.arq.ams.odl.presupuestos.objeto.asegurado.api.model.EntityModelObjetoAseguradoPresupuestoResource;
import com.santalucia.arq.ams.odl.presupuestos.objeto.asegurado.api.model.ObjetoAseguradoPresupuestoRequestBodyResource;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;


@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface ObjetoAseguradoDomainMapper {
  /**
   *
   * @param objetoAsegurado
   * @return
   */

  ObjetosAseguradosDomain toDomain(EntityModelObjetoAseguradoPresupuestoResource objetoAsegurado);

  /**
   *
   * @param securedObject
   * @return
   */

  ObjetoAseguradoPresupuestoRequestBodyResource toResource(ObjetosAseguradosDomain securedObject);


}
