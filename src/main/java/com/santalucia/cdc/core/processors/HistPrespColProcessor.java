package com.santalucia.cdc.core.processors;

import com.santalucia.cdc.core.domain.EventoPresupuestoColDomain;
import com.santalucia.cdc.core.domain.budgets.collectivebudget.PresupuestoColectivoDomain;
import com.santalucia.cdc.core.domain.budgets.common.figure.DatoPersonalDomain;
import com.santalucia.cdc.core.domain.budgets.common.figure.contactdata.DomicilioPersDomain;
import com.santalucia.cdc.core.domain.budgets.common.figure.contactdata.MedioDeContactoDomain;
import com.santalucia.cdc.core.domain.budgets.common.geograph.CoordenadaDomain;
import com.santalucia.cdc.core.domain.budgets.common.geograph.DomicilioPresupuestoDomain;
import com.santalucia.cdc.core.domain.budgets.common.payment.DatoOtrosCobPagBancDomain;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.declaration.com.resp.RespuestaDomain;
import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.AnimalDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.CaracteristicaDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.DomicilioDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.FiguraDomain;
import com.santalucia.cdc.core.service.policies.PolizaColectivaClientService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class HistPrespColProcessor implements ItemProcessor<EventoPresupuestoColDomain, EventoPresupuestoColDomain> {
  public static final String ANONIMO = "**********";
  @Autowired
  private PolizaColectivaClientService polizaService;

  /**
   * Metodo para procesar los presupuestos
   *
   * @param eventoPresupuestoColDomain
   * @return
   */
  @Override
  public EventoPresupuestoColDomain process(EventoPresupuestoColDomain eventoPresupuestoColDomain) {

    PresupuestoColectivoDomain budget = eventoPresupuestoColDomain.getPresupuestoColectivo();

    String numIdpresupuesto = budget.getDatoIdentificativo().getNumIdentificacion();

    if (polizaService.getPolizaColectiva(numIdpresupuesto)) {
      budget.getDatoIdentificativo().setIndFormalizado("S");

    } else if (polizaService.getHistoricoColectiva(numIdpresupuesto)) {
      budget.getDatoIdentificativo().setIndFormalizado("S");
    } else {
      Instant thirtyDaysAfter = budget.getFechaYEstado().getFecha().getFecAlta().plus(30, ChronoUnit.DAYS);
      if (Instant.now().isAfter(thirtyDaysAfter)) {
        anonimizate(eventoPresupuestoColDomain);
        eventoPresupuestoColDomain.getPresupuestoColectivo().getFechaYEstado().getFecha().setFecAnonimizacion(Instant.now());
        eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoIdentificativo().setIndAnonimizado("S");
      }

    }
    return eventoPresupuestoColDomain;
  }

  /**
   * Metodo para anonimizar los campos correspondientes de los presupuestos
   *
   * @param eventoPresupuestoColDomain
   * @return
   */
  public EventoPresupuestoColDomain anonimizate(EventoPresupuestoColDomain eventoPresupuestoColDomain) {

    TipoMDLDomain tipoMDLDomainAnonimizado = new TipoMDLDomain();
    LocalDate localDate = LocalDate.of(1, 1, 1);
    Instant anonDate = localDate.atStartOfDay(ZoneOffset.UTC).toInstant();
    tipoMDLDomainAnonimizado.setCodMDL(ANONIMO);
    tipoMDLDomainAnonimizado.setCodOrigen(ANONIMO);
    tipoMDLDomainAnonimizado.setDescOrigen(ANONIMO);


    // PRESUPUESTO COLECTIVO

    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setTipoDomBancario(tipoMDLDomainAnonimizado);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setEntidadBancaria(tipoMDLDomainAnonimizado);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setNumDigContrNumCuent(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setNumDigContrEntidOfic(ANONIMO);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getDatoCobro().getDatoMedioCobro().getDatoCobroBancario().setNumCuentaBanc(ANONIMO);
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
      datos.setEntidadBancOtroCobro(tipoMDLDomainAnonimizado);
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

    DomicilioPresupuestoDomain domicilioPresupuestoDomain = eventoPresupuestoColDomain.getPresupuestoColectivo().getEstructuraGeografica().getDomicilioPresupuesto();
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

    eventoPresupuestoColDomain.getPresupuestoColectivo().getEstructuraGeografica().setDomicilioPresupuesto(domicilioPresupuestoDomain);

    CoordenadaDomain coordenadaDomainAnonimizada = eventoPresupuestoColDomain.getPresupuestoColectivo().getEstructuraGeografica().getCoordenada();
    coordenadaDomainAnonimizada.setTipoCoordenada(tipoMDLDomainAnonimizado);
    coordenadaDomainAnonimizada.setIndSistema(ANONIMO);
    coordenadaDomainAnonimizada.setNumCoordX(0.0);
    coordenadaDomainAnonimizada.setNumCoordY(0.0);
    eventoPresupuestoColDomain.getPresupuestoColectivo().getEstructuraGeografica().setCoordenada(coordenadaDomainAnonimizada);


    // OBJETO ASEGURADO

    List<ObjetosAseguradosDomain> objetosAseguradosDomainList = eventoPresupuestoColDomain.getObjetosAsegurados();


    for (ObjetosAseguradosDomain obj : objetosAseguradosDomainList) {

      CaracteristicaDomain caracteristicaDomainAnonima = obj.getCaracteristica();

      List<DomicilioDomain> domicilioDomains = caracteristicaDomainAnonima.getDomicilios();

      for (DomicilioDomain domicilio : domicilioDomains) {

        domicilio.setPais(tipoMDLDomainAnonimizado);
        domicilio.setProvincia(tipoMDLDomainAnonimizado);
        domicilio.setLocalidad(tipoMDLDomainAnonimizado);
        domicilio.setCodMunicipio(ANONIMO);
        domicilio.setCodEntColectiva(ANONIMO);
        domicilio.setCodEntSingular(ANONIMO);
        domicilio.setCodNucPobla(ANONIMO);
        domicilio.setDenomPobla(tipoMDLDomainAnonimizado);
        domicilio.setCodPostal(ANONIMO);
        domicilio.setTipoVia(tipoMDLDomainAnonimizado);
        domicilio.setDesDomicilio(ANONIMO);
        domicilio.setNumNumero(ANONIMO);
        domicilio.setNumComplemento(ANONIMO);
        domicilio.setNumBloque(ANONIMO);
        domicilio.setNumPortal(ANONIMO);
        domicilio.setNumEscalera(ANONIMO);
        domicilio.setNumPiso(ANONIMO);
        domicilio.setNumPuerta(ANONIMO);
        domicilio.setIndNormalizado(ANONIMO);
        domicilio.setDesOtrosDatos(ANONIMO);


      }
      obj.getCaracteristica().setDomicilios(domicilioDomains);


      List<FiguraDomain> figuraDomainList = caracteristicaDomainAnonima.getFiguras();

      for (FiguraDomain figura : caracteristicaDomainAnonima.getFiguras()) {
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

    eventoPresupuestoColDomain.setObjetosAsegurados(objetosAseguradosDomainList);

    //DECLARACION

    List<DeclaracionDomain> declaracionDomainList = eventoPresupuestoColDomain.getDeclaracion();

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

    eventoPresupuestoColDomain.setDeclaracion(declaracionDomainList);

    return eventoPresupuestoColDomain;
  }
}
