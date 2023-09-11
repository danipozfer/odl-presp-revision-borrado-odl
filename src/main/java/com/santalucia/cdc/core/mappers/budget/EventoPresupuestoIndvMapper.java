package com.santalucia.cdc.core.mappers.budget;

import com.santalucia.cdc.core.domain.EventoPresupuestoColDomain;
import com.santalucia.cdc.core.domain.EventoPresupuestoIndvDomain;
import org.mapstruct.Builder;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, builder = @Builder(disableBuilder = true))
public interface EventoPresupuestoIndvMapper {

  EventoPresupuestoIndvDomain toDomain (PresupuestoResource presupuestoResource);
}
