package com.santalucia.cdc.core.processors;

import com.santalucia.cdc.core.domain.EventoPresupuestoColDomain;
import com.santalucia.cdc.core.domain.TipoMDLDomain;
import com.santalucia.cdc.core.domain.budgets.collectiveBudget.PresupuestoColectivoDomain;
import com.santalucia.cdc.core.domain.budgets.common.DomicilioPersDomain;
import com.santalucia.cdc.core.domain.budgets.common.MedioDeContactoDomain;
import com.santalucia.cdc.core.domain.budgets.common.figure.DatoPersonalDomain;
import com.santalucia.cdc.core.domain.budgets.common.geograph.CoordenadaDomain;
import com.santalucia.cdc.core.domain.budgets.common.geograph.DomicilioPresupuestoDomain;
import com.santalucia.cdc.core.domain.budgets.common.payment.DatoOtrosCobPagBancDomain;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.declaration.RespuestaDomain;
import com.santalucia.cdc.core.domain.insurance.polizas.PolizaDomain;
import com.santalucia.cdc.core.domain.securedObject.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.domain.securedObject.characteristics.AnimalDomain;
import com.santalucia.cdc.core.domain.securedObject.characteristics.CaracteristicaDomain;
import com.santalucia.cdc.core.domain.securedObject.characteristics.DomicilioDomain;
import com.santalucia.cdc.core.domain.securedObject.characteristics.FiguraDomain;
import com.santalucia.cdc.core.service.PolizaColectivaClientService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class HistPrespColProcessor  implements ItemProcessor<EventoPresupuestoColDomain, EventoPresupuestoColDomain> {
  public static final String ANONIMO = "**********";
  @Autowired
  private PolizaColectivaClientService polizaService;

  @Override
  public EventoPresupuestoColDomain process(EventoPresupuestoColDomain eventoPresupuestoColDomain) throws Exception {
    EventoPresupuestoColDomain updatedEvent = new EventoPresupuestoColDomain();
    PresupuestoColectivoDomain budget = eventoPresupuestoColDomain.getPresupuestoColectivo();
    List<ObjetosAseguradosDomain> securedObjects = eventoPresupuestoColDomain.getObjetosAsegurados();
    List<DeclaracionDomain> declarations = eventoPresupuestoColDomain.getDeclaracion();

    String numIdpresupuesto = budget.getDatoIdentificativo().getNumIdentificacion();
    //Buscar en polizas
    PolizaDomain insurance = polizaService.getPolizaColectiva(numIdpresupuesto, null);
    List<PolizaDomain> hInsurances = polizaService.findAllHistoricoColectiva(numIdpresupuesto, null);

    //Si hay resultado poner el ind a S
    if (insurance != null || !hInsurances.isEmpty()) {
      budget.getDatoIdentificativo().setIndFormalizado("S");

      updatedEvent.setIndTipoEvento(eventoPresupuestoColDomain.getIndTipoEvento());
      updatedEvent.setPresupuestoColectivo(budget);
      updatedEvent.setObjetosAsegurados(securedObjects);
      updatedEvent.setDeclaracion(declarations);

    } else {//Si no hay resultado comprobar fecha
      Instant thirtyDaysAfter = LocalDate.now().plusDays(30).atStartOfDay(ZoneOffset.UTC).toInstant();
      if (budget.getFechaYEstado().getFecha().getFecAlta().isBefore(thirtyDaysAfter)) {   //Fecha anterior (anonimizamos y ponemos fecAnonimiizacion al día actual)
        updatedEvent = eventoPresupuestoColDomain;
      }
      else {
        updatedEvent = anonimizate(eventoPresupuestoColDomain);
      }
    }
    return updatedEvent;
  }


  private EventoPresupuestoColDomain anonimizate(EventoPresupuestoColDomain eventoPresupuestoColDomain) {

    TipoMDLDomain tipoMDLDomainAnonimizado = new TipoMDLDomain();
    Instant anonDate = Instant.ofEpochSecond(0);
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
      datos.setNumCuentaBanc(ANONIMO);
      datos.setEntidadBancOtroCob(tipoMDLDomainAnonimizado);
      datos.setTipoDomBancOtroCob(tipoMDLDomainAnonimizado);
    }

    for (DatoPersonalDomain datoPersonalDomain : eventoPresupuestoColDomain.getPresupuestoColectivo().getFigura().getDatosPersonales()) {
      datoPersonalDomain.setTipoRol(tipoMDLDomainAnonimizado);
      datoPersonalDomain.setNumOrdenRol(ANONIMO);
      datoPersonalDomain.setNumIdPersona(ANONIMO);
      datoPersonalDomain.setNumIdCliente(ANONIMO);
      datoPersonalDomain.setTipoPersona(tipoMDLDomainAnonimizado);
      datoPersonalDomain.setTipoDocumento(tipoMDLDomainAnonimizado);
      datoPersonalDomain.setNumDocumento(ANONIMO);
      datoPersonalDomain.setSexoPersona(tipoMDLDomainAnonimizado);
      datoPersonalDomain.setTxtNombre(ANONIMO);
      datoPersonalDomain.setTxtPrimerApellido(ANONIMO);
      datoPersonalDomain.setTxtSegundoApellido(ANONIMO);
      datoPersonalDomain.setTxtRazonSocial(ANONIMO);
      datoPersonalDomain.setFecNacimiento(anonDate);
      datoPersonalDomain.setProfesion(tipoMDLDomainAnonimizado);
      datoPersonalDomain.setAgrupProfesion(tipoMDLDomainAnonimizado);
      datoPersonalDomain.setNacionalidad(tipoMDLDomainAnonimizado);
      datoPersonalDomain.setIndEstadoCivil(ANONIMO);

      DomicilioPersDomain domicilioPersDomainAnonimizado = new DomicilioPersDomain();
      domicilioPersDomainAnonimizado.setIdDomicilio(ANONIMO);
      domicilioPersDomainAnonimizado.setPaisDomPers(tipoMDLDomainAnonimizado);
      domicilioPersDomainAnonimizado.setLocalidadDomPers(tipoMDLDomainAnonimizado);
      domicilioPersDomainAnonimizado.setProvinciaDomPers(tipoMDLDomainAnonimizado);
      domicilioPersDomainAnonimizado.setCodMunicipio(ANONIMO);
      domicilioPersDomainAnonimizado.setCodEntColectiva(ANONIMO);
      domicilioPersDomainAnonimizado.setCodEntSingular(ANONIMO);
      domicilioPersDomainAnonimizado.setCodNucPobla(ANONIMO);
      domicilioPersDomainAnonimizado.setDenomPoblaDomPers(tipoMDLDomainAnonimizado);
      domicilioPersDomainAnonimizado.setCodPostal(ANONIMO);
      domicilioPersDomainAnonimizado.setTipoViaDomPers(tipoMDLDomainAnonimizado);
      domicilioPersDomainAnonimizado.setDesDomicilio(ANONIMO);
      domicilioPersDomainAnonimizado.setNumDomicilio(ANONIMO);
      domicilioPersDomainAnonimizado.setNumComplemento(ANONIMO);
      domicilioPersDomainAnonimizado.setNumBloque(ANONIMO);
      domicilioPersDomainAnonimizado.setNumPortal(ANONIMO);
      domicilioPersDomainAnonimizado.setNumEscalera(ANONIMO);
      domicilioPersDomainAnonimizado.setNumPuerta(ANONIMO);
      domicilioPersDomainAnonimizado.setNumPiso(ANONIMO);
      domicilioPersDomainAnonimizado.setBlNormalizado(ANONIMO);
      domicilioPersDomainAnonimizado.setDesOtrosDatos(ANONIMO);

      datoPersonalDomain.setDomicilioPers(domicilioPersDomainAnonimizado);

      for (MedioDeContactoDomain medioDeContactoDomain : datoPersonalDomain.getMediosDeContactos()) {
        medioDeContactoDomain.setMedioContacto(tipoMDLDomainAnonimizado);
        medioDeContactoDomain.setPaisOrigen(tipoMDLDomainAnonimizado);
        medioDeContactoDomain.setNumTelefono(ANONIMO);
        medioDeContactoDomain.setNomCorreoElectronico(ANONIMO);
      }
    }

    DomicilioPresupuestoDomain domicilioPresupuestoDomain = new DomicilioPresupuestoDomain();
    domicilioPresupuestoDomain.setIdDomicilio(ANONIMO);
    domicilioPresupuestoDomain.setPais(tipoMDLDomainAnonimizado);
    domicilioPresupuestoDomain.setLocalidad(tipoMDLDomainAnonimizado);
    domicilioPresupuestoDomain.setProvincia(tipoMDLDomainAnonimizado);
    domicilioPresupuestoDomain.setCodMunicipio(ANONIMO);
    domicilioPresupuestoDomain.setCodEntColectiva(ANONIMO);
    domicilioPresupuestoDomain.setCodEntSingular(ANONIMO);
    domicilioPresupuestoDomain.setCodNucPobla(ANONIMO);
    domicilioPresupuestoDomain.setDenomPobla(tipoMDLDomainAnonimizado);
    domicilioPresupuestoDomain.setCodPostal(ANONIMO);
    domicilioPresupuestoDomain.setTipoVia(tipoMDLDomainAnonimizado);
    domicilioPresupuestoDomain.setDesDomicilio(ANONIMO);
    domicilioPresupuestoDomain.setNumNumero(ANONIMO);
    domicilioPresupuestoDomain.setNumComplemento(ANONIMO);
    domicilioPresupuestoDomain.setNumBloque(ANONIMO);
    domicilioPresupuestoDomain.setNumPortal(ANONIMO);
    domicilioPresupuestoDomain.setNumEscalera(ANONIMO);
    domicilioPresupuestoDomain.setNumPuerta(ANONIMO);
    domicilioPresupuestoDomain.setNumPiso(ANONIMO);
    domicilioPresupuestoDomain.setBlNormalizado(ANONIMO);
    domicilioPresupuestoDomain.setDesOtrosDatos(ANONIMO);

    eventoPresupuestoColDomain.getPresupuestoColectivo().getEstructuraGeografica().setDomicilioPresupuesto(domicilioPresupuestoDomain);

    CoordenadaDomain coordenadaDomainAnonimizada = new CoordenadaDomain();
    coordenadaDomainAnonimizada.setTipoCoordenada(tipoMDLDomainAnonimizado);
    coordenadaDomainAnonimizada.setIndSistema(ANONIMO);
    coordenadaDomainAnonimizada.setNumCoordX(0.0);
    coordenadaDomainAnonimizada.setNumCoordY(0.0);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getEstructuraGeografica().setCoordenada(coordenadaDomainAnonimizada);
    //Para fechas 0001-01-01 y para double 0.0 y para integer 0


    // OBJETO ASEGURADO

    List<ObjetosAseguradosDomain> objetosAseguradosDomainList = new ArrayList<>();


    for (ObjetosAseguradosDomain obj : eventoPresupuestoColDomain.getObjetosAsegurados()){

      CaracteristicaDomain caracteristicaDomainAnonima = new CaracteristicaDomain();
      List<DomicilioDomain> domicilioDomains = new ArrayList<>();
      for(DomicilioDomain domicilios : caracteristicaDomainAnonima.getDomicilios()) {
        domicilios.setPais(tipoMDLDomainAnonimizado);
        domicilios.setProvincia(tipoMDLDomainAnonimizado);
        domicilios.setLocalidad(tipoMDLDomainAnonimizado);
        domicilios.setCodMunicipio(ANONIMO);
        domicilios.setCodEntColectiva(ANONIMO);
        domicilios.setCodEntSingular(ANONIMO);
        domicilios.setCodNucPobla(ANONIMO);
        domicilios.setDenomPobla(tipoMDLDomainAnonimizado);
        domicilios.setCodPostal(ANONIMO);
        domicilios.setTipoVia(tipoMDLDomainAnonimizado);
        domicilios.setDesDomicilio(ANONIMO);
        domicilios.setNumNumero(ANONIMO);
        domicilios.setNumComplemento(ANONIMO);
        domicilios.setNumBloque(ANONIMO);
        domicilios.setNumPortal(ANONIMO);
        domicilios.setNumEscalera(ANONIMO);
        domicilios.setNumPiso(ANONIMO);
        domicilios.setNumPuerta(ANONIMO);
        domicilios.setBlNormalizado(ANONIMO);
        domicilios.setDesOtrosDatos(ANONIMO);

        domicilioDomains.add(domicilios);
      }
      obj.getCaracteristica().setDomicilios(domicilioDomains);


      List<FiguraDomain> figuraDomainList = new ArrayList<>();

      for (FiguraDomain figura : caracteristicaDomainAnonima.getFiguras()){
        figura.setIdPersona(ANONIMO);
        figura.setTipoPersona(tipoMDLDomainAnonimizado);
        figura.setTxtNombre(ANONIMO);
        figura.setTxtPrimerApellido(ANONIMO);
        figura.setTxtSegundoApellido(ANONIMO);
        figura.setTxtRazonSocial(ANONIMO);
        figura.setTipoDocumento(tipoMDLDomainAnonimizado);
        figura.setNumDocumento(ANONIMO);
        figura.setFecNacimiento(anonDate);
        figura.setSexo(tipoMDLDomainAnonimizado);
        figura.setNacionalidad(tipoMDLDomainAnonimizado);
        figura.setProfesion(tipoMDLDomainAnonimizado);
        figura.setGrupoProfesion(tipoMDLDomainAnonimizado);
        figura.setIndEstadoCivil(ANONIMO);
        figura.setBeneficiario(tipoMDLDomainAnonimizado);

        figuraDomainList.add(figura);
      }

      obj.getCaracteristica().setFiguras(figuraDomainList);

      List<AnimalDomain> animalDomainsList = new ArrayList<>();

      for (AnimalDomain animal: caracteristicaDomainAnonima.getAnimales()){
        animal.setIndTipoEspecie(ANONIMO);
        animal.setRaza(tipoMDLDomainAnonimizado);
        animal.setIndTipoAnimalComp(ANONIMO);
        animal.setNumIdentAnimalComp(ANONIMO);
        animal.setNomMascota(ANONIMO);
        animal.setNumChip(ANONIMO);
        animal.setFecNacimiento(anonDate);
        animal.setImpValorMascota(0.0);
        animal.setIndPerroMestizo(ANONIMO);
        animal.setIndPerfEstadoSalud(ANONIMO);
      }



      obj.getCaracteristica().setAnimales(animalDomainsList);
      obj.setCaracteristica(caracteristicaDomainAnonima);
      objetosAseguradosDomainList.add(obj);
    }

    eventoPresupuestoColDomain.setObjetosAsegurados(objetosAseguradosDomainList);

    //DECLARACION

    List<DeclaracionDomain> declaracionDomainList = new ArrayList<>();

    for(DeclaracionDomain dec : eventoPresupuestoColDomain.getDeclaracion()){

      List<com.santalucia.cdc.core.domain.declaration.CaracteristicaDomain> caracList = new ArrayList<>();

      for (com.santalucia.cdc.core.domain.declaration.CaracteristicaDomain caracteristicaDomain: dec.getCaracteristicas()){

        caracteristicaDomain.setPregunta(tipoMDLDomainAnonimizado);
        caracteristicaDomain.setIndAplicPregunta(ANONIMO);

        List<RespuestaDomain> respuestaDomainList = new ArrayList<>();
        for (RespuestaDomain res: caracteristicaDomain.getRespuestas()){
          res.setRespFacilitada(tipoMDLDomainAnonimizado);
          res.setIndTipoRespuesta(ANONIMO);
          respuestaDomainList.add(res);
        }
        caracteristicaDomain.setRespuestas(respuestaDomainList);
      }
      dec.setCaracteristicas(caracList);

      declaracionDomainList.add(dec);
    }

    eventoPresupuestoColDomain.setDeclaracion(declaracionDomainList);

    return eventoPresupuestoColDomain;
  }

}
