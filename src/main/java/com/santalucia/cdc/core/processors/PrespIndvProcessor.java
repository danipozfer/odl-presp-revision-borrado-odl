package com.santalucia.cdc.core.processors;

import com.santalucia.cdc.core.domain.EventoPresupuestoIndvDomain;
import com.santalucia.cdc.core.domain.budgets.common.figure.DatoPersonalDomain;
import com.santalucia.cdc.core.domain.budgets.common.figure.contactdata.DomicilioPersDomain;
import com.santalucia.cdc.core.domain.budgets.common.figure.contactdata.MedioDeContactoDomain;
import com.santalucia.cdc.core.domain.budgets.common.geograph.CoordenadaDomain;
import com.santalucia.cdc.core.domain.budgets.common.geograph.DomicilioPresupuestoDomain;
import com.santalucia.cdc.core.domain.budgets.common.payment.DatoOtrosCobPagBancDomain;
import com.santalucia.cdc.core.domain.budgets.individualbudget.PresupuestoIndividualDomain;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.declaration.com.resp.RespuestaDomain;
import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.AnimalDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.CaracteristicaDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.DomicilioDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.FiguraDomain;
import com.santalucia.cdc.core.service.policies.PolizaIndividualClientService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PrespIndvProcessor implements ItemProcessor<EventoPresupuestoIndvDomain, EventoPresupuestoIndvDomain> {

  public static final String ANONIMO = "**********";

  @Autowired
  private PolizaIndividualClientService polizaService;

  /**
   * Metodo para procesar los presupuestos
   *
   * @param eventoPresupuestoIndvDomain
   * @return
   */
  @Override
  public EventoPresupuestoIndvDomain process(EventoPresupuestoIndvDomain eventoPresupuestoIndvDomain) {
    PresupuestoIndividualDomain budget = eventoPresupuestoIndvDomain.getPresupuestoIndividual();

    String numIdpresupuesto = budget.getDatoIdentificativo().getNumIdentificacion();

    if (polizaService.getPolizaIndividual(numIdpresupuesto)) {
      budget.getDatoIdentificativo().setIndFormalizado("S");

    } else if (polizaService.getHistoricoIndividual(numIdpresupuesto)) {
      budget.getDatoIdentificativo().setIndFormalizado("S");

    } else {
      Instant thirtyDaysAfter = budget.getFechaYEstado().getFecha().getFecAlta().plus(30, ChronoUnit.DAYS);
      if (Instant.now().isAfter(thirtyDaysAfter)) {
        eventoPresupuestoIndvDomain = anonimizate(eventoPresupuestoIndvDomain);
        eventoPresupuestoIndvDomain.getPresupuestoIndividual().getFechaYEstado().getFecha().setFecAnonimizacion(Instant.now());
        eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoIdentificativo().setIndAnonimizado("S");
      }
    }
    return eventoPresupuestoIndvDomain;
  }

  /**
   * Metodo para anonimizar los campos correspondientes de los presupuestos
   *
   * @param eventoPresupuestoIndvDomain
   * @return
   */
  public EventoPresupuestoIndvDomain anonimizate(EventoPresupuestoIndvDomain eventoPresupuestoIndvDomain) {

    TipoMDLDomain tipoMDLDomainAnonimizado = new TipoMDLDomain();
    LocalDate localDate = LocalDate.of(1, 1, 1);
    Instant anonDate = localDate.atStartOfDay(ZoneOffset.UTC).toInstant();
    tipoMDLDomainAnonimizado.setCodMDL(ANONIMO);
    tipoMDLDomainAnonimizado.setCodOrigen(ANONIMO);
    tipoMDLDomainAnonimizado.setDescOrigen(ANONIMO);


    //PRESUPUESTO INDIVIDUAL

    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setTipoDomBancario(tipoMDLDomainAnonimizado);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setEntidadBancaria(tipoMDLDomainAnonimizado);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setNumDigContrNumCuent(ANONIMO);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setNumDigContrEntidOfic(ANONIMO);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setNumCuentaBanc(ANONIMO);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setTitulCuentBanc(ANONIMO);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setCodIban(ANONIMO);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setCodBic(ANONIMO);

    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setTipoDomCobro(tipoMDLDomainAnonimizado);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setPais(tipoMDLDomainAnonimizado);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setProvincia(tipoMDLDomainAnonimizado);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setLocalidad(tipoMDLDomainAnonimizado);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setCodMunicipio(ANONIMO);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setCodPostal(ANONIMO);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setCodEntColectiva(ANONIMO);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setCodEntSingular(ANONIMO);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setCodNucPobla(ANONIMO);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setDenomPoblaPers(tipoMDLDomainAnonimizado);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setTipoVia(tipoMDLDomainAnonimizado);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setDescDomicilio(ANONIMO);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setNumDomicilio(ANONIMO);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setNumBloqueDomic(ANONIMO);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setCompNumDomic(ANONIMO);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setNumPortalDomic(ANONIMO);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setNumEscalDomic(ANONIMO);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setNumPisoDomic(ANONIMO);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setNumPuertaDomic(ANONIMO);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatoMedioCobro().getDatoCobroFisico().setOtrosDatosDomic(ANONIMO);

    for (DatoOtrosCobPagBancDomain datos : eventoPresupuestoIndvDomain.getPresupuestoIndividual().getDatoCobro().getDatosOtrosCobPagBanc()) {
      datos.setCodBic(ANONIMO);
      datos.setCodIban(ANONIMO);
      datos.setNumDigContrNumCuent(ANONIMO);
      datos.setNumDigContrEntidOfic(ANONIMO);
      datos.setTitulCuentBanc(ANONIMO);
      datos.setNumCuentaBanc(ANONIMO);
      datos.setEntidadBancOtroCobro(tipoMDLDomainAnonimizado);
      datos.setTipoDomBancOtroCob(tipoMDLDomainAnonimizado);
    }

    for (DatoPersonalDomain datoPersonalDomain : eventoPresupuestoIndvDomain.getPresupuestoIndividual().getFigura().getDatosPersonales()) {
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

      DomicilioPersDomain domicilioPersDomainAnonimizado = datoPersonalDomain.getDomicilioPers();
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
      domicilioPersDomainAnonimizado.setIndNormalizado(ANONIMO);
      domicilioPersDomainAnonimizado.setDesOtrosDatos(ANONIMO);

      datoPersonalDomain.setDomicilioPers(domicilioPersDomainAnonimizado);

      for (MedioDeContactoDomain medioDeContactoDomain : datoPersonalDomain.getMediosDeContactos()) {
        medioDeContactoDomain.setMedioContacto(tipoMDLDomainAnonimizado);
        medioDeContactoDomain.setPaisOrigen(tipoMDLDomainAnonimizado);
        medioDeContactoDomain.setNumTelefono(ANONIMO);
        medioDeContactoDomain.setNomCorreoElectronico(ANONIMO);
      }
    }

    DomicilioPresupuestoDomain domicilioPresupuestoDomain = eventoPresupuestoIndvDomain.getPresupuestoIndividual().getEstructuraGeografica().getDomicilioPresupuesto();
    domicilioPresupuestoDomain.setIdDomicilio(ANONIMO);
    domicilioPresupuestoDomain.setPaisPresup(tipoMDLDomainAnonimizado);
    domicilioPresupuestoDomain.setLocalidadPresup(tipoMDLDomainAnonimizado);
    domicilioPresupuestoDomain.setProvinciaPresup(tipoMDLDomainAnonimizado);
    domicilioPresupuestoDomain.setCodMunicipio(ANONIMO);
    domicilioPresupuestoDomain.setCodEntColectiva(ANONIMO);
    domicilioPresupuestoDomain.setCodEntSingular(ANONIMO);
    domicilioPresupuestoDomain.setCodNucPobla(ANONIMO);
    domicilioPresupuestoDomain.setDenomPoblaPresup(tipoMDLDomainAnonimizado);
    domicilioPresupuestoDomain.setCodPostal(ANONIMO);
    domicilioPresupuestoDomain.setTipoViaPresup(tipoMDLDomainAnonimizado);
    domicilioPresupuestoDomain.setDesDomicilio(ANONIMO);
    domicilioPresupuestoDomain.setNumNumero(ANONIMO);
    domicilioPresupuestoDomain.setNumComplemento(ANONIMO);
    domicilioPresupuestoDomain.setNumBloque(ANONIMO);
    domicilioPresupuestoDomain.setNumPortal(ANONIMO);
    domicilioPresupuestoDomain.setNumEscalera(ANONIMO);
    domicilioPresupuestoDomain.setNumPuerta(ANONIMO);
    domicilioPresupuestoDomain.setNumPiso(ANONIMO);
    domicilioPresupuestoDomain.setIndNormalizado(ANONIMO);
    domicilioPresupuestoDomain.setDesOtrosDatos(ANONIMO);

    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getEstructuraGeografica().setDomicilioPresupuesto(domicilioPresupuestoDomain);

    CoordenadaDomain coordenadaDomainAnonimizada = eventoPresupuestoIndvDomain.getPresupuestoIndividual().getEstructuraGeografica().getCoordenada();
    coordenadaDomainAnonimizada.setTipoCoordenada(tipoMDLDomainAnonimizado);
    coordenadaDomainAnonimizada.setIndSistema(ANONIMO);
    coordenadaDomainAnonimizada.setNumCoordX(0.0);
    coordenadaDomainAnonimizada.setNumCoordY(0.0);
    eventoPresupuestoIndvDomain.getPresupuestoIndividual().getEstructuraGeografica().setCoordenada(coordenadaDomainAnonimizada);


    //OBJETOS ASEGURADOS

    List<ObjetosAseguradosDomain> objetosAseguradosDomainList = eventoPresupuestoIndvDomain.getObjetosAsegurados();


    for (ObjetosAseguradosDomain obj : objetosAseguradosDomainList) {

      CaracteristicaDomain caracteristicaDomainAnonima = obj.getCaracteristica();
      List<DomicilioDomain> domicilioDomains = caracteristicaDomainAnonima.getDomicilios();
      for (DomicilioDomain domicilios : domicilioDomains) {
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
        domicilios.setIndNormalizado(ANONIMO);
        domicilios.setDesOtrosDatos(ANONIMO);


      }
      obj.getCaracteristica().setDomicilios(domicilioDomains);


      List<FiguraDomain> figuraDomainList = caracteristicaDomainAnonima.getFiguras();

      for (FiguraDomain figura : figuraDomainList) {
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


      }

      obj.getCaracteristica().setFiguras(figuraDomainList);

      List<AnimalDomain> animalDomainsList = caracteristicaDomainAnonima.getAnimales();

      for (AnimalDomain animal : animalDomainsList) {
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

    }

    eventoPresupuestoIndvDomain.setObjetosAsegurados(objetosAseguradosDomainList);


    //DECLARACIONES

    List<DeclaracionDomain> declaracionDomainList = eventoPresupuestoIndvDomain.getDeclaracion();

    for (DeclaracionDomain dec : declaracionDomainList) {

      List<com.santalucia.cdc.core.domain.declaration.com.CaracteristicaDomain> caracList = dec.getCaracteristicas();

      for (com.santalucia.cdc.core.domain.declaration.com.CaracteristicaDomain caracteristicaDomain : caracList) {

        caracteristicaDomain.setPregunta(tipoMDLDomainAnonimizado);
        caracteristicaDomain.setIndAplicPregunta(ANONIMO);

        List<RespuestaDomain> respuestaDomainList = caracteristicaDomain.getRespuestas();
        for (RespuestaDomain res : respuestaDomainList) {
          res.setRespFacilitada(tipoMDLDomainAnonimizado);
          res.setIndTipoRespuesta(ANONIMO);

        }
        caracteristicaDomain.setRespuestas(respuestaDomainList);
      }
      dec.setCaracteristicas(caracList);


    }

    eventoPresupuestoIndvDomain.setDeclaracion(declaracionDomainList);

    return eventoPresupuestoIndvDomain;
  }
}
