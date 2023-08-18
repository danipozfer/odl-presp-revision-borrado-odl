package com.santalucia.cdc.core.mappers;

import com.santalucia.cdc.core.domain.EventoPresupuestoColDomain;
import com.santalucia.cdc.core.domain.EventoPresupuestoIndvDomain;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EventoPresupuestoIndvMapper {

  EventoPresupuestoIndvDomain toDomain (PresupuestoResource presupuestoResource);
}
