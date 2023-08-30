package com.santalucia.cdc.core.mappers.budget;

import com.santalucia.cdc.core.domain.EventoPresupuestoColDomain;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EventoPresupuestoColMapper {



    EventoPresupuestoColDomain toDomain (PresupuestoResource presupuestoResource);

    /**
     *
     * @param declaracionDomain
     * @return
     */
    /*DeclaracionResource toResource (DeclaracionDomain declaracionDomain);*/


}
