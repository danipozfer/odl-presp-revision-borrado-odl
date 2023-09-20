package com.santalucia.cdc.core.mappers.budget;

import com.santalucia.arq.ams.odl.historico.presupuestos.objeto.asegurado.api.model.DomicilioResource;
import com.santalucia.arq.ams.odl.historico.presupuestos.objeto.asegurado.api.model.EntityModelObjetoAseguradoPresupuestoResource;
import com.santalucia.arq.ams.odl.historico.presupuestos.objeto.asegurado.api.model.ObjetoAseguradoPresupuestoRequestBodyResource;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.DomicilioDomain;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@SuppressWarnings("NullAway")
@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true), uses = OffsetDateTimeMapper.class)
public interface HistObjetoAseguradoDomainMapper {

  /**
   * @param body
   * @return
   */
  ObjetosAseguradosDomain toDomain(EntityModelObjetoAseguradoPresupuestoResource body);

  /**
   * @param securedObject
   * @return
   */
  ObjetoAseguradoPresupuestoRequestBodyResource toResource(ObjetosAseguradosDomain securedObject);

  /**
   * @param list
   * @return
   */

  List<ObjetosAseguradosDomain> toDomainsfromResources(List<EntityModelObjetoAseguradoPresupuestoResource> list);

  /**
   *
   * @param in
   * @return
   */
  @Mapping(source = "descDomicilio", target = "desDomicilio")
  DomicilioDomain domOut(com.santalucia.arq.ams.odl.historico.presupuestos.objeto.asegurado.api.model.DomicilioResource in);

  /**
   *
   * @param in
   * @return
   */
  @Mapping(source = "desDomicilio", target = "descDomicilio")
  DomicilioResource domOutp(DomicilioDomain in);

}
