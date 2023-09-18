package com.santalucia.cdc.core.mappers.budget;

import com.santalucia.arq.ams.odl.historico.presupuestos.objeto.asegurado.api.model.EntityModelObjetoAseguradoPresupuestoResource;
import com.santalucia.arq.ams.odl.historico.presupuestos.objeto.asegurado.api.model.ObjetoAseguradoPresupuestoRequestBodyResource;
import com.santalucia.arq.ams.odl.historico.presupuestos.objeto.asegurado.api.model.PagedModelEntityModelObjetoAseguradoPresupuestoEmbeddedResource;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface HistObjetoAseguradoDomainMapper {

  /**
   *
   * @param body
   * @return
   */

  ObjetosAseguradosDomain toDomain(EntityModelObjetoAseguradoPresupuestoResource body);

  /**
   *
   * @param securedObject
   * @return
   */


  ObjetoAseguradoPresupuestoRequestBodyResource toResource(ObjetosAseguradosDomain securedObject);

  /**
   *
   * @param embedded
   * @return
   */

  List<ObjetosAseguradosDomain> toDomainsfromResources(PagedModelEntityModelObjetoAseguradoPresupuestoEmbeddedResource embedded);


}
