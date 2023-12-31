package com.santalucia.cdc.core.mappers.budget;

import com.santalucia.arq.ams.odl.presupuestos.objeto.asegurado.api.model.DomicilioResource;
import com.santalucia.arq.ams.odl.presupuestos.objeto.asegurado.api.model.EntityModelObjetoAseguradoPresupuestoResource;
import com.santalucia.arq.ams.odl.presupuestos.objeto.asegurado.api.model.ObjetoAseguradoPresupuestoRequestBodyResource;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.DomicilioDomain;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@SuppressWarnings("NullAway")
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true), uses = OffsetDateTimeMapper.class)
public interface ObjetoAseguradoDomainMapper {
  /**
   * @param objetoAsegurado
   * @return
   */
  ObjetosAseguradosDomain toDomain(EntityModelObjetoAseguradoPresupuestoResource objetoAsegurado);

  /**
   * @param securedObject
   * @return
   */
  ObjetoAseguradoPresupuestoRequestBodyResource toResource(ObjetosAseguradosDomain securedObject);

  /**
   * @param in
   * @return
   */

  @Mapping(source = "descDomicilio", target = "desDomicilio")
  DomicilioDomain domOut(DomicilioResource in);

  /**
   * @param in
   * @return
   */

  @Mapping(source = "desDomicilio", target = "descDomicilio")
  DomicilioResource domOutp(DomicilioDomain in);


}
