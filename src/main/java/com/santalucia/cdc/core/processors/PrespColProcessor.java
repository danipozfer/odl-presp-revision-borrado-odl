package com.santalucia.cdc.core.processors;

import com.santalucia.cdc.core.domain.EventoPresupuestoColDomain;
import com.santalucia.cdc.core.domain.TipoMDLDomain;
import com.santalucia.cdc.core.domain.budgets.common.payment.DatoOtrosCobPagBancDomain;
import org.checkerframework.checker.units.qual.A;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;

public class PrespColProcessor implements ItemProcessor<EventoPresupuestoColDomain, EventoPresupuestoColDomain> {

  public static final String ANONIMO = "**********";

  @Override
  public EventoPresupuestoColDomain process(EventoPresupuestoColDomain eventoPresupuestoColDomain) throws Exception {

    //Buscar en polizas

    //Si hay resultado poner el ind a S
    if () {

    } else {//Si no hay resultado comprobar fecha
      if (eventoPresupuestoColDomain.getPresupuestoColectivo().getFechaYEstado().getFecha().getFecAlta().isAfter()) {   //Fecha anterior (anonimizamos y ponemos fecAnonimiizacion al día actual)
        anonimizate(eventoPresupuestoColDomain);
        //Fecha posterior nada
      }
    }
    return eventoPresupuestoColDomain;
  }


  private EventoPresupuestoColDomain anonimizate(EventoPresupuestoColDomain eventoPresupuestoColDomain) {

    TipoMDLDomain tipoMDLDomainAnonimizado = new TipoMDLDomain();
    tipoMDLDomainAnonimizado.setCodMDL(ANONIMO);
    tipoMDLDomainAnonimizado.setCodOrigen(ANONIMO);
    tipoMDLDomainAnonimizado.setDescOrigen(ANONIMO);


    // PRESUPUESTO COLECTIVO

    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setTipoDomBancario(tipoMDLDomainAnonimizado);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setEntidadBancaria(tipoMDLDomainAnonimizado);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setNumDigContrNumCuent(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setNumDigContrEntidOfic(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setNumCuentBanc(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setTitulCuentBanc(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setCodIban(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setCodBic(ANONIMO);

    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setTipoDomCobro(tipoMDLDomainAnonimizado);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setPais(tipoMDLDomainAnonimizado);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setProvincia(tipoMDLDomainAnonimizado);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setLocalidad(tipoMDLDomainAnonimizado);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setCodMunicipio(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setCodPostal(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setCodEntColectiva(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setCodEntSingular(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setCodNucPobla(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setDenomPoblaPers(tipoMDLDomainAnonimizado);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setTipoVia(tipoMDLDomainAnonimizado);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setDescDomicilio(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setNumDomicilio(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setNumBloqueDomic(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setCompNumDomic(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setNumPortalDomic(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setNumEscalDomic(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setNumPisoDomic(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setNumPuertaDomic(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setOtrosDatosDomic(ANONIMO);

    for (DatoOtrosCobPagBancDomain datos : eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatosOtrosCobPagBanc()) {
      datos.setCodBic(ANONIMO);
      datos.setCodIban(ANONIMO);
      datos.setNumDigContrNumCuent(ANONIMO);
      datos.setNumDigContrEntidOfic(ANONIMO);
      datos.setTitulCuentBanc(ANONIMO);
      datos.setEntidadBancOtroCob(tipoMDLDomainAnonimizado);
      datos.setTipoDomBancOtroCob(tipoMDLDomainAnonimizado);
    }




    //Para fechas 0001-01-01 y para double 0.0 y para integer 0
  }
}
