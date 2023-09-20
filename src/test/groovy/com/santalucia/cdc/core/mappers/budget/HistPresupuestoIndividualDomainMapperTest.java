package com.santalucia.cdc.core.mappers.budget;

import com.santalucia.arq.ams.odl.presupuestos.historico.individual.api.model.*;
import com.santalucia.cdc.core.domain.MetadataDomain;
import com.santalucia.cdc.core.domain.budgets.common.campaigns.CampannaDomain;
import com.santalucia.cdc.core.domain.budgets.common.commercial.EstructuraComercialDomain;
import com.santalucia.cdc.core.domain.budgets.common.dateandstate.EstadoDomain;
import com.santalucia.cdc.core.domain.budgets.common.dateandstate.FechaDomain;
import com.santalucia.cdc.core.domain.budgets.common.dateandstate.FechaYEstadoDomain;
import com.santalucia.cdc.core.domain.budgets.common.dateandstate.hist.HistoricoDomain;
import com.santalucia.cdc.core.domain.budgets.common.figure.DatoPersonalDomain;
import com.santalucia.cdc.core.domain.budgets.common.figure.FiguraDomain;
import com.santalucia.cdc.core.domain.budgets.common.figure.contactdata.DomicilioPersDomain;
import com.santalucia.cdc.core.domain.budgets.common.figure.contactdata.MedioDeContactoDomain;
import com.santalucia.cdc.core.domain.budgets.common.geograph.CoordenadaDomain;
import com.santalucia.cdc.core.domain.budgets.common.geograph.DomicilioPresupuestoDomain;
import com.santalucia.cdc.core.domain.budgets.common.geograph.EstructuraGeograficaDomain;
import com.santalucia.cdc.core.domain.budgets.common.identif.DatoIdentificativoDomain;
import com.santalucia.cdc.core.domain.budgets.common.payment.DatoCobroDomain;
import com.santalucia.cdc.core.domain.budgets.common.payment.DatoMedioCobroDomain;
import com.santalucia.cdc.core.domain.budgets.common.payment.DatoOtrosCobPagBancDomain;
import com.santalucia.cdc.core.domain.budgets.common.payment.method.DatoCobroBancarioDomain;
import com.santalucia.cdc.core.domain.budgets.common.payment.method.DatoCobroFisicoDomain;
import com.santalucia.cdc.core.domain.budgets.common.payment.method.DatoCobroInternoDomain;
import com.santalucia.cdc.core.domain.budgets.common.payment.method.DatosCobroOtrosMediosDomain;
import com.santalucia.cdc.core.domain.budgets.common.product.ProductoDomain;
import com.santalucia.cdc.core.domain.budgets.common.securedobject.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.domain.budgets.individualbudget.PresupuestoIndividualDomain;
import com.santalucia.cdc.core.domain.budgets.individualbudget.amount.ImporteDomain;
import com.santalucia.cdc.core.domain.budgets.individualbudget.amount.level.BonificacionDescDomain;
import com.santalucia.cdc.core.domain.budgets.individualbudget.amount.level.ImpuestoDomain;
import com.santalucia.cdc.core.domain.budgets.individualbudget.amount.level.NivelPresupuestoDomain;
import com.santalucia.cdc.core.domain.budgets.individualbudget.amount.level.SobreprimaDomain;
import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import com.santalucia.cdc.core.domain.metadata.AuditoriaDomain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HistPresupuestoIndividualDomainMapperTest {

  @DisplayName("histPresupIndToDomain")
  @Test
  void toDomain() {

    OffsetDateTime offsetDateTime = OffsetDateTime.now();
    EntityModelPresupuestoIndividualResource presupuestoIndividual = new EntityModelPresupuestoIndividualResource();
    DatoIdentificativoResource datoIdentificativoIndividual = new DatoIdentificativoResource();
    presupuestoIndividual.setId("1");

    ProductoResource productoIndividual = new ProductoResource();
    FechaYEstadoResource fechaYEstadoIndividual = new FechaYEstadoResource();
    FechaResource fechaIndividual = new FechaResource();
    EstadoResource estadoIndividual = new EstadoResource();
    HistoricoResource historicoIndividual = new HistoricoResource();
    DatoMedioCobroResource datoMedioCobroIndividual = new DatoMedioCobroResource();
    DatoCobroResource datoCobroIndividual = new DatoCobroResource();
    DatosOtrosCobPagBancResource datosOtrosCobPagBancIndividual = new DatosOtrosCobPagBancResource();
    DatoCobroBancarioResource datoCobroBancarioIndividual = new DatoCobroBancarioResource();
    DatoCobroFisicoResource datoCobroFisicoIndividual = new DatoCobroFisicoResource();
    DatoCobroInternoResource datoCobroInternoIndividual = new DatoCobroInternoResource();
    List<DatosCobroOtrosMedioResource> datosCobroOtrosMedios = new ArrayList<>();
    DomicilioPersResource domicilioPersIndividual = new DomicilioPersResource();
    MediosDeContactoResource mediosDeContactosIndividual = new MediosDeContactoResource();
    DatosPersonalesResource datosPersonalesIndividual = new DatosPersonalesResource();
    FiguraResource figuraIndividual = new FiguraResource();
    EstructuraComercialResource estructuraComercialIndividual = new EstructuraComercialResource();
    EstructuraGeograficaResource estructuraGeograficaIndividual = new EstructuraGeograficaResource();
    DomicilioPresupuestoResource domicilioPresupuestoIndividual = new DomicilioPresupuestoResource();
    CoordenadaResource coordenadaIndividual = new CoordenadaResource();
    ImporteResource importeIndividual = new ImporteResource();
    CampannaResource campannasIndividual = new CampannaResource();
    ObjetosAseguradosResource objetosAseguradosIndividual = new ObjetosAseguradosResource();
    MetadataResource metadataIndividual = new MetadataResource();


    //Datos identificadivos de individual
    datoIdentificativoIndividual.setIdPresupuestoODL("1");
    TipoPresupuestoResource tipoPresupuestoResource= new TipoPresupuestoResource("1", "1", "1");
    datoIdentificativoIndividual.setTipoPresupuesto(tipoPresupuestoResource);
    CompanniaRespServResource companniaRespServResource= new CompanniaRespServResource("1", "1", "1");
    datoIdentificativoIndividual.setCompanniaRespServ(companniaRespServResource);
    SistemaOrigenResource sistemaOrigenResource= new SistemaOrigenResource("1", "1", "1");
    datoIdentificativoIndividual.setSistemaOrigen(sistemaOrigenResource);
    SistemaActualResource sistemaActualResource= new SistemaActualResource("1", "1", "1");
    datoIdentificativoIndividual.setSistemaActual(sistemaActualResource);
    datoIdentificativoIndividual.setNumIdentificacion("1");
    datoIdentificativoIndividual.setCodIdentificacion("1");
    datoIdentificativoIndividual.setNumIdAgrupacion("1");
    datoIdentificativoIndividual.setCodIdAgrupacion("1");
    datoIdentificativoIndividual.setVersPresupuesto(1D);
    datoIdentificativoIndividual.setVersPresupuestoODL("1");
    datoIdentificativoIndividual.setIndVersSeleccionada("1");
    datoIdentificativoIndividual.setIdPolizaOrigen("1");
    datoIdentificativoIndividual.setIdMensaje("1");
    datoIdentificativoIndividual.setCodPackComercial("1");
    datoIdentificativoIndividual.setNumPoliza("1");
    datoIdentificativoIndividual.setNumPackPoliza("1");

    TipoComisionResource tipoComisionResource = new TipoComisionResource("1", "1", "1");
    datoIdentificativoIndividual.setTipoComision(tipoComisionResource);
    datoIdentificativoIndividual.setCodRenovacion("1");
    datoIdentificativoIndividual.setIndCompanniaVendedora("1");
    datoIdentificativoIndividual.setCodOportSalesforce("1");
    datoIdentificativoIndividual.setCodVersionComercial("1");
    PeriodPolizaResource periodPolizaResource = new PeriodPolizaResource("1", "1", "1");
    datoIdentificativoIndividual.setPeriodPoliza(periodPolizaResource);
    datoIdentificativoIndividual.setIndOrigRecomendador("1");
    datoIdentificativoIndividual.setCodEvento("1");

    presupuestoIndividual.setDatoIdentificativo(datoIdentificativoIndividual);
    //Producto individual


    RamoComercialResource ramoComercialResource = new RamoComercialResource("1", "1", "1");
    productoIndividual.setRamoComercial(ramoComercialResource);
    ModalidadComercialResource modalidadComercialResource = new ModalidadComercialResource();
    productoIndividual.setModalidadComercial(modalidadComercialResource);
    ProductoTecnicoResource productoTecnicoResource = new ProductoTecnicoResource("1", "1", "1");
    productoIndividual.setProductoTecnico(productoTecnicoResource);
    ProductoComercialResource productoComercialResource = new ProductoComercialResource("1", "1", "1");
    productoIndividual.setProductoComercial(productoComercialResource);
    productoIndividual.setDescModalidadInterna("1");

    presupuestoIndividual.setProducto(productoIndividual);

    //Historico individual
    EstadoPresupuestoResource estadoPresupuestoResource = new EstadoPresupuestoResource("1", "1", "1");
    historicoIndividual.setEstadoPresupuesto(estadoPresupuestoResource);
    historicoIndividual.setFecEstado(offsetDateTime);
    CausaEstPresupuestoResource causaEstPresupuestoResource = new CausaEstPresupuestoResource("1", "1", "1");
    historicoIndividual.setCausaEstPresupuesto(causaEstPresupuestoResource);
    historicoIndividual.setNumOrdenMovEstado("1");
    historicoIndividual.setIndBloqueoRevision("1");


    //Estado individual
    List<HistoricoResource> listaHistoricosIndividual = new ArrayList<>();
    listaHistoricosIndividual.add(historicoIndividual);
    estadoIndividual.setHistoricos(listaHistoricosIndividual);


    //Fecha individual
    fechaIndividual.setFecAlta(offsetDateTime);
    fechaIndividual.setFecEfectoPoliza(offsetDateTime);
    fechaIndividual.setFecFinValidez(offsetDateTime);
    fechaIndividual.setFecTarificacion(offsetDateTime);
    fechaIndividual.setFecVencPoliza(offsetDateTime);

    //FechaYEstado individual
    fechaYEstadoIndividual.setEstado(estadoIndividual);
    fechaYEstadoIndividual.setFecha(fechaIndividual);

    presupuestoIndividual.setFechaYEstado(fechaYEstadoIndividual);

    //DatoCobroBancario Individual
    datoCobroBancarioIndividual.setCodBic("1");
    datoCobroBancarioIndividual.setCodIban("1");
    TipoDomBancarioResource tipoDomBancarioResource = new TipoDomBancarioResource("1", "1", "1");
    datoCobroBancarioIndividual.setTipoDomBancario(tipoDomBancarioResource);
    EntidadBancariaResource entidadBancariaResource = new EntidadBancariaResource("1", "1", "1");
    datoCobroBancarioIndividual.setEntidadBancaria(entidadBancariaResource);
    datoCobroBancarioIndividual.setNumCuentaBanc(1D);
    datoCobroBancarioIndividual.setNumDigContrEntidOfic("1");
    datoCobroBancarioIndividual.setNumDigContrNumCuent("1");
    datoCobroBancarioIndividual.setTitulCuentBanc("1");


    //DatoCobroFisico Individual
    TipoDomCobroResource tipoDomCobroResource = new TipoDomCobroResource("1","1","1");
    datoCobroFisicoIndividual.setTipoDomCobro(tipoDomCobroResource);
    PaisResource paisResource = new PaisResource("1","1","1");
    datoCobroFisicoIndividual.setPais(paisResource);
    ProvinciaResource provinciaResource = new ProvinciaResource("1","1","1");
    datoCobroFisicoIndividual.setProvincia(provinciaResource);
    LocalidadResource localidadResource = new LocalidadResource("1","1","1");
    datoCobroFisicoIndividual.setLocalidad(localidadResource);
    datoCobroFisicoIndividual.setCodMunicipio("1");
    datoCobroFisicoIndividual.setCodPostal("1");
    datoCobroFisicoIndividual.setCodEntColectiva("1");
    datoCobroFisicoIndividual.setConEntSingular("1");
    datoCobroFisicoIndividual.setCodNucPobla("1");
    DenomPoblaPersResource denomPoblaPersResource = new DenomPoblaPersResource("1","1","1");
    datoCobroFisicoIndividual.setDenomPoblaPers(denomPoblaPersResource);
    TipoViaResource tipoViaResource = new TipoViaResource("1","1","1");
    datoCobroFisicoIndividual.setTipoVia(tipoViaResource);
    datoCobroFisicoIndividual.setDescDomicilio("1");
    datoCobroFisicoIndividual.setNumDomicilio("1");
    datoCobroFisicoIndividual.setCompNumDomic("1");
    datoCobroFisicoIndividual.setNumBloqueDomic("1");
    datoCobroFisicoIndividual.setNumPortalDomic("1");
    datoCobroFisicoIndividual.setNumEscalDomic("1");
    datoCobroFisicoIndividual.setNumPisoDomic("1");
    datoCobroFisicoIndividual.setNumPuertaDomic("1");
    datoCobroFisicoIndividual.setOtrosDatosDomic("1");

    //DatoCobroInterno Individual
    CobPagInterCompResource cobPagInterCompResource = new CobPagInterCompResource("1","1","1");
    datoCobroInternoIndividual.setCobPagInterComp(cobPagInterCompResource);
    //DatosCobroOtrosMedios
    DatosCobroOtrosMedioResource datosCobroOtrosMediosDomain = new DatosCobroOtrosMedioResource("1", "1", "1");
    datosCobroOtrosMedios.add(datosCobroOtrosMediosDomain);
    //DatoMedioCobro Individual
    datoMedioCobroIndividual.setDatoCobroBancario(datoCobroBancarioIndividual);
    datoMedioCobroIndividual.setDatoCobroFisico(datoCobroFisicoIndividual);
    datoMedioCobroIndividual.setDatoCobroInterno(datoCobroInternoIndividual);
    datoMedioCobroIndividual.setDatosCobroOtrosMedios(datosCobroOtrosMedios);

    //DatosOtrosCobPagBanc Individual
    TipoDomBancOtroCobResource tipoDomBancOtroCobResource = new TipoDomBancOtroCobResource("1","1","1");
    datosOtrosCobPagBancIndividual.setTipoDomBancOtroCob(tipoDomBancOtroCobResource);
    EntidadBancOtroCobroResource entidadBancOtroCobroResource = new EntidadBancOtroCobroResource("1","1","1");
    datosOtrosCobPagBancIndividual.setEntidadBancOtroCobro(entidadBancOtroCobroResource);
    datosOtrosCobPagBancIndividual.setNumDigContrNumCuent("1");
    datosOtrosCobPagBancIndividual.setNumDigContrEntidOfic("1");
    datosOtrosCobPagBancIndividual.setNumCuentaBanc(1D);
    datosOtrosCobPagBancIndividual.setTitulCuentBanc("1");
    datosOtrosCobPagBancIndividual.setCodIban("1");
    datosOtrosCobPagBancIndividual.setCodBic("1");

    //DatoCobro Individual
    List<DatosOtrosCobPagBancResource> listdatosOtrosCobPagBanc = new ArrayList<>();
    listdatosOtrosCobPagBanc.add(datosOtrosCobPagBancIndividual);
    NivelCobroResource nivelCobroResource = new NivelCobroResource("1","1","1");
    datoCobroIndividual.setNivelCobro(nivelCobroResource);
    FormaPagoResource formaPagoResource = new FormaPagoResource("1","1","1");
    datoCobroIndividual.setFormaPago(formaPagoResource);
    MedioCobroPagoResource medioCobroPagoResource = new MedioCobroPagoResource("1","1","1");
    datoCobroIndividual.setMedioCobroPago(medioCobroPagoResource);
    datoCobroIndividual.setDatoMedioCobro(datoMedioCobroIndividual);
    datoCobroIndividual.setDatosOtrosCobPagBanc(listdatosOtrosCobPagBanc);

    //DomicilioPers Individual
    domicilioPersIndividual.setIdDomicilio("1");
    PaisDomPersResource paisDomPersResource = new PaisDomPersResource("1","1","1");
    domicilioPersIndividual.setPaisDomPers(paisDomPersResource);
    LocalidadDomPersResource localidadDomPersResource = new LocalidadDomPersResource("1","1","1");
    domicilioPersIndividual.setLocalidadDomPers(localidadDomPersResource);
    ProvinciaDomPersResource provinciaDomPersResource = new ProvinciaDomPersResource("1","1","1");
    domicilioPersIndividual.setProvinciaDomPers(provinciaDomPersResource);
    domicilioPersIndividual.setCodMunicipio("1");
    domicilioPersIndividual.setCodEntColectiva("1");
    domicilioPersIndividual.setCodEntSingular("1");
    domicilioPersIndividual.setCodNucPobla("1");
    DenomPoblaDomPersResource denomPoblaDomPersResource = new DenomPoblaDomPersResource("1","1","1");
    domicilioPersIndividual.setDenomPoblaDomPers(denomPoblaDomPersResource);
    domicilioPersIndividual.setCodPostal("1");
    TipoViaDomPersResource tipoViaDomPersResource = new TipoViaDomPersResource("1","1","1");
    domicilioPersIndividual.setTipoViaDomPers(tipoViaDomPersResource);
    domicilioPersIndividual.setDescDomicilio("1");
    domicilioPersIndividual.setNumDomicilio("1");
    domicilioPersIndividual.setNumComplemento("1");
    domicilioPersIndividual.setNumBloque("1");
    domicilioPersIndividual.setNumPortal("1");
    domicilioPersIndividual.setNumEscalera("1");
    domicilioPersIndividual.setNumPiso("1");
    domicilioPersIndividual.setNumPuerta("1");
    domicilioPersIndividual.setIndNormalizado("1");
    domicilioPersIndividual.setDesOtrosDatos("1");

    //MediosDeContacto Individual
    MedioContactoResource medioContactoResource = new MedioContactoResource("1","1","1");
    mediosDeContactosIndividual.setMedioContacto(medioContactoResource);
    PaisOrigenResource paisOrigenResource = new PaisOrigenResource("1","1","1");
    mediosDeContactosIndividual.setPaisOrigen(paisOrigenResource);
    mediosDeContactosIndividual.setNumTelefono("1");
    mediosDeContactosIndividual.setNomCorreoElectronico("1");

    //DatosPersonales Individual
    TipoRolResource tipoRolResource = new TipoRolResource("1","1","1");
    datosPersonalesIndividual.setTipoRol(tipoRolResource);
    datosPersonalesIndividual.setNumOrdenRol("1");
    datosPersonalesIndividual.setNumIdPersona("1");
    datosPersonalesIndividual.setNumIdCliente("1");
    TipoPersonaResource tipoPersonaResource = new TipoPersonaResource("1","1","1");
    datosPersonalesIndividual.setTipoPersona(tipoPersonaResource);
    TipoDocumentoResource tipoDocumentoResource = new TipoDocumentoResource("1","1","1");
    datosPersonalesIndividual.setTipoDocumento(tipoDocumentoResource);
    datosPersonalesIndividual.setNumDocumento("1");
    SexoPersonaResource sexoPersonaResource = new SexoPersonaResource("1","1","1");
    datosPersonalesIndividual.setSexoPersona(sexoPersonaResource);
    datosPersonalesIndividual.setTxtNombre("1");
    datosPersonalesIndividual.setTxtPrimerApellido("1");
    datosPersonalesIndividual.setTxtSegundoApellido("1");
    datosPersonalesIndividual.setTxtRazonSocial("1");
    datosPersonalesIndividual.setFecNacimiento(offsetDateTime);
    ProfesionResource profesionResource = new ProfesionResource("1","1","1");
    datosPersonalesIndividual.setProfesion(profesionResource);
    AgrupProfesionResource agrupProfesionResource = new AgrupProfesionResource("1","1","1");
    datosPersonalesIndividual.setAgrupProfesion(agrupProfesionResource);
    NacionalidadResource nacionalidadResource = new NacionalidadResource("1","1","1");
    datosPersonalesIndividual.setNacionalidad(nacionalidadResource);
    datosPersonalesIndividual.setIndEstadoCivil("1");
    datosPersonalesIndividual.setDomicilioPers(domicilioPersIndividual);
    List<MediosDeContactoResource> listaMediosDeContactos = new ArrayList<>();
    listaMediosDeContactos.add(mediosDeContactosIndividual);
    datosPersonalesIndividual.setMediosDeContactos(listaMediosDeContactos);

    presupuestoIndividual.setDatoCobro(datoCobroIndividual);

    //Figura individual
    figuraIndividual.setNumAsegurados("1");
    List<DatosPersonalesResource> listaDatosPersonales = new ArrayList<>();
    listaDatosPersonales.add(datosPersonalesIndividual);
    figuraIndividual.setDatosPersonales(listaDatosPersonales);

    presupuestoIndividual.setFigura(figuraIndividual);

    //EstructuraComercial Individual
    CanalMediacionResource canalMediacionResource = new CanalMediacionResource("1","1","1");
    estructuraComercialIndividual.setCanalMediacion(canalMediacionResource);
    MedioDistribucionResource medioDistribucionResource = new MedioDistribucionResource("1","1","1");
    estructuraComercialIndividual.setMedioDistribucion(medioDistribucionResource);
    AgenciaResource agenciaResource = new AgenciaResource("1","1","1");
    estructuraComercialIndividual.setAgencia(agenciaResource);
    ZonaResource zonaResource = new ZonaResource("1","1","1");
    estructuraComercialIndividual.setZona(zonaResource);
    DistritoResource distritoResource = new DistritoResource("1","1","1");
    estructuraComercialIndividual.setDistrito(distritoResource);
    OficinaResource oficinaResource = new OficinaResource("1","1","1");
    estructuraComercialIndividual.setOficina(oficinaResource);
    estructuraComercialIndividual.setCodMediador("1");
    TipoMediadorResource tipoMediadorResource = new TipoMediadorResource("1","1","1");
    estructuraComercialIndividual.setTipoMediador(tipoMediadorResource);
    TipoColaboradorResource tipoColaboradorResource = new TipoColaboradorResource("1","1","1");
    estructuraComercialIndividual.setTipoColaborador(tipoColaboradorResource);
    estructuraComercialIndividual.setCodColaborador("1");
    TipoColaboradorIniResource tipoColaboradorIniResource = new TipoColaboradorIniResource("1","1","1");
    estructuraComercialIndividual.setTipoColaboradorIni(tipoColaboradorIniResource);
    estructuraComercialIndividual.setCodColabInicial("1");
    estructuraComercialIndividual.setCodMonitor("1");
    estructuraComercialIndividual.setCodMonitorInicial("1");
    RedVentaResource redVentaResource = new RedVentaResource("1","1","1");
    estructuraComercialIndividual.setRedVenta(redVentaResource);
    estructuraComercialIndividual.setIndTipoMedioCom("1");

    //Coordenada Individual
    TipoCoordenadaResource tipoCoordenadaResource = new TipoCoordenadaResource("1","1","1");
    coordenadaIndividual.setTipoCoordenada(tipoCoordenadaResource);
    coordenadaIndividual.setNumCoordX(1d);
    coordenadaIndividual.setNumCoordY(1d);
    coordenadaIndividual.setIndSistema("1");

    //DomicilioPresupuesto Individual
    domicilioPresupuestoIndividual.setIdDomicilio("1");
    PaisPresupResource paisPresupResource = new PaisPresupResource("1","1","1");
    domicilioPresupuestoIndividual.setPaisPresup(paisPresupResource);
    LocalidadPresupResource localidadPresupResource = new LocalidadPresupResource("1","1","1");
    domicilioPresupuestoIndividual.setLocalidadPresup(localidadPresupResource);
    ProvinciaPresupResource provinciaPresupResource = new ProvinciaPresupResource("1","1","1");
    domicilioPresupuestoIndividual.setProvinciaPresup(provinciaPresupResource);
    domicilioPresupuestoIndividual.setCodMunicipio("1");
    domicilioPresupuestoIndividual.setCodEntColectiva("1");
    domicilioPresupuestoIndividual.setCodEntSingular("1");
    domicilioPresupuestoIndividual.setCodNucPobla("1");
    DenomPoblaPresupResource denomPoblaPresupResource = new DenomPoblaPresupResource("1","1","1");
    domicilioPresupuestoIndividual.setDenomPoblaPresup(denomPoblaPresupResource);
    domicilioPresupuestoIndividual.setCodPostal("1");
    TipoViaPresupResource tipoViaPresupResource = new TipoViaPresupResource("1","1","1");
    domicilioPresupuestoIndividual.setTipoViaPresup(tipoViaPresupResource);
    domicilioPresupuestoIndividual.setDescDomicilio("1");
    domicilioPresupuestoIndividual.setNumNumero("1");
    domicilioPresupuestoIndividual.setNumComplemento("1");
    domicilioPresupuestoIndividual.setNumBloque("1");
    domicilioPresupuestoIndividual.setNumPortal("1");
    domicilioPresupuestoIndividual.setNumEscalera("1");
    domicilioPresupuestoIndividual.setNumPiso("1");
    domicilioPresupuestoIndividual.setNumPuerta("1");
    domicilioPresupuestoIndividual.setIndNormalizado("1");
    domicilioPresupuestoIndividual.setDesOtrosDatos("1");

    presupuestoIndividual.setEstructuraComercial(estructuraComercialIndividual);

    //EstructuraGeografica Individual
    estructuraGeograficaIndividual.setCoordenada(coordenadaIndividual);
    estructuraGeograficaIndividual.setDomicilioPresupuesto(domicilioPresupuestoIndividual);

    presupuestoIndividual.setEstructuraGeografica(estructuraGeograficaIndividual);
    //Importe Individual
    MonedaResource monedaResource = new MonedaResource("1","1","1");
    importeIndividual.setMoneda(monedaResource);
    importeIndividual.setImpTotalRecibo(1D);
    NivelesPresupuestoResource nivelesPresupuestos = new NivelesPresupuestoResource();
    TipoPrimaResource tipoPrimaResource = new TipoPrimaResource("1","1","1");
    nivelesPresupuestos.setTipoPrima(tipoPrimaResource);
    nivelesPresupuestos.setIndModServEconomico("1d");
    List<NivelesPresupuestoResource> nivelesPresupuestosList = new ArrayList<>();
    nivelesPresupuestosList.add(nivelesPresupuestos);
    SobreprimaResource sobreprimas = new SobreprimaResource();
    sobreprimas.setImpSobreprima(1D);
    List<SobreprimaResource> sobreprimasList = new ArrayList<>();
    sobreprimasList.add(sobreprimas);
    nivelesPresupuestos.setSobreprimas(sobreprimasList);
    BonificacionesDescResource bonificacionesDesc = new BonificacionesDescResource();
    bonificacionesDesc.setImpBonificacionDesc(1d);
    bonificacionesDesc.setPorCesionComisionMed(1d);
    List<BonificacionesDescResource> bonificacionesDescList = new ArrayList<>();
    bonificacionesDescList.add(bonificacionesDesc);
    nivelesPresupuestos.setBonificacionesDesc(bonificacionesDescList);
    ImpuestoResource impuestos = new ImpuestoResource();
    impuestos.setImpRecargoImp(1d);
    List<ImpuestoResource> impuestosList = new ArrayList<>();
    impuestosList.add(impuestos);
    nivelesPresupuestos.setImpuestos(impuestosList);
    importeIndividual.setNivelesPresupuestos(nivelesPresupuestosList);

    presupuestoIndividual.setImporte(importeIndividual);

    //Campanna Individual
    CampannaComercialResource campannaComercialResource = new CampannaComercialResource("1","1","1");
    campannasIndividual.setCampannaComercial(campannaComercialResource);
    campannasIndividual.setIdCampanna("1");
    IncentivoCampannaResource incentivoCampannaResource = new IncentivoCampannaResource("1","1","1");
    campannasIndividual.setIncentivoCampanna(incentivoCampannaResource);
    TipoCampannaResource tipoCampannaResource = new TipoCampannaResource("1","1","1");
    campannasIndividual.setTipoCampanna(tipoCampannaResource);
    campannasIndividual.setFecInicio(offsetDateTime);
    campannasIndividual.setFecFinVigencia(offsetDateTime);
    campannasIndividual.setIndCaracIncentivo("1");
    List<CampannaResource> campannasList = new ArrayList<>();
    campannasList.add(campannasIndividual);
    presupuestoIndividual.setCampannas(campannasList);

    //ObjetosAsegurados Individual
    objetosAseguradosIndividual.setIdObjAsegODL("1");
    List<ObjetosAseguradosResource> objetosAseguradosList = new ArrayList<>();
    objetosAseguradosList.add(objetosAseguradosIndividual);

    presupuestoIndividual.setObjetosAsegurados(objetosAseguradosList);
    /*Auditoria auditoriaDomain = new AuditoriaDomain();
    auditoriaDomain.setCreatedByUser("1");
    auditoriaDomain.setCreatedDate(offsetDateTime);
    auditoriaDomain.setLastModifiedByUser("1");
    auditoriaDomain.setLastModifiedDate(offsetDateTime);*/

    //Metadata Individual
    metadataIndividual.setTxtCircuitoOrigen("1");
    metadataIndividual.setFecSQL(offsetDateTime);
    /*metadataIndividual.setHash("1");*/
    metadataIndividual.setCodVersion("1");
    metadataIndividual.setNumOffsetMDL(1L);
    metadataIndividual.setNumOffsetCarga(1L);
    /*metadataIndividual.setAuditoria(auditoriaDomain);*/
    presupuestoIndividual.setMetadata(metadataIndividual);

    HistPresupuestoIndividualDomainMapperImpl mapper = new HistPresupuestoIndividualDomainMapperImpl();
    PresupuestoIndividualDomain result = mapper.toDomain(presupuestoIndividual);
    assertNotNull(result);

  }
  @DisplayName("histPresupIndToResource")
  @Test
  void toResource() {
    Instant instant = Instant.now();
    PresupuestoIndividualDomain presupuestoIndividual = new PresupuestoIndividualDomain();
    DatoIdentificativoDomain datoIdentificativoIndividual = new DatoIdentificativoDomain();
    TipoMDLDomain tipoMDLDomain = new TipoMDLDomain();
    tipoMDLDomain.setCodMDL("1");
    tipoMDLDomain.setCodOrigen("1");
    tipoMDLDomain.setDescOrigen("1");
    presupuestoIndividual.setId("1");
    ProductoDomain productoIndividual = new ProductoDomain();
    FechaYEstadoDomain fechaYEstadoIndividual = new FechaYEstadoDomain();
    FechaDomain fechaIndividual = new FechaDomain();
    EstadoDomain estadoIndividual = new EstadoDomain();
    HistoricoDomain historicoIndividual = new HistoricoDomain();
    DatoMedioCobroDomain datoMedioCobroIndividual = new DatoMedioCobroDomain();
    DatoCobroDomain datoCobroIndividual = new DatoCobroDomain();
    List<DatoOtrosCobPagBancDomain> datosOtrosCobPagBancLista = new ArrayList<>();
    DatoOtrosCobPagBancDomain datoOtrosCobPagBancIndividual = new DatoOtrosCobPagBancDomain();
    DatoCobroBancarioDomain datoCobroBancarioIndividual = new DatoCobroBancarioDomain();
    DatoCobroFisicoDomain datoCobroFisicoIndividual = new DatoCobroFisicoDomain();
    DatoCobroInternoDomain datoCobroInternoIndividual = new DatoCobroInternoDomain();
    List<DatosCobroOtrosMediosDomain> datosCobroOtrosMedios = new ArrayList<>();
    DomicilioPersDomain domicilioPersIndividual = new DomicilioPersDomain();
    MedioDeContactoDomain mediosDeContactosIndividual = new MedioDeContactoDomain();
    DatoPersonalDomain datosPersonalesIndividual = new DatoPersonalDomain();
    FiguraDomain figuraIndividual = new FiguraDomain();
    EstructuraComercialDomain estructuraComercialIndividual = new EstructuraComercialDomain();
    EstructuraGeograficaDomain estructuraGeograficaIndividual = new EstructuraGeograficaDomain();
    DomicilioPresupuestoDomain domicilioPresupuestoIndividual = new DomicilioPresupuestoDomain();
    CoordenadaDomain coordenadaIndividual = new CoordenadaDomain();
    ImporteDomain importeIndividual = new ImporteDomain();
    CampannaDomain campannasIndividual = new CampannaDomain();
    ObjetosAseguradosDomain objetosAseguradosIndividual = new ObjetosAseguradosDomain();
    MetadataDomain metadataIndividual = new MetadataDomain();


    //Datos identificadivos de individual
    datoIdentificativoIndividual.setIdPresupuestoODL("1");
    datoIdentificativoIndividual.setTipoPresupuesto(tipoMDLDomain);
    datoIdentificativoIndividual.setCompanniaRespServ(tipoMDLDomain);
    datoIdentificativoIndividual.setSistemaOrigen(tipoMDLDomain);
    datoIdentificativoIndividual.setSistemaActual(tipoMDLDomain);
    datoIdentificativoIndividual.setNumIdentificacion("1");
    datoIdentificativoIndividual.setCodIdentificacion("1");
    datoIdentificativoIndividual.setNumIdAgrupacion("1");
    datoIdentificativoIndividual.setCodIdAgrupacion("1");
    datoIdentificativoIndividual.setVersPresupuesto("1");
    datoIdentificativoIndividual.setVersPresupuestoODL("1");
    datoIdentificativoIndividual.setIndVersSeleccionada("1");
    datoIdentificativoIndividual.setIdPolizaOrigen("1");
    datoIdentificativoIndividual.setIdMensaje("1");
    datoIdentificativoIndividual.setCodPackComercial("1");
    datoIdentificativoIndividual.setNumPoliza("1");
    datoIdentificativoIndividual.setNumPackPoliza("1");

    datoIdentificativoIndividual.setTipoComision(tipoMDLDomain);
    datoIdentificativoIndividual.setCodRenovacion("1");
    datoIdentificativoIndividual.setIndCompanniaVendedora("1");
    datoIdentificativoIndividual.setCodOportSalesforce("1");
    datoIdentificativoIndividual.setCodVersionComercial("1");
    datoIdentificativoIndividual.setPeriodPoliza(tipoMDLDomain);
    datoIdentificativoIndividual.setIndOrigRecomendador("1");
    datoIdentificativoIndividual.setCodEvento("1");

    presupuestoIndividual.setDatoIdentificativo(datoIdentificativoIndividual);
    //Producto individual

    productoIndividual.setRamoComercial(tipoMDLDomain);

    productoIndividual.setModalidadComercial(tipoMDLDomain);

    productoIndividual.setProductoTecnico(tipoMDLDomain);

    productoIndividual.setProductoComercial(tipoMDLDomain);
    productoIndividual.setDescModalidadInterna("1");

    presupuestoIndividual.setProducto(productoIndividual);

    //Historico individual

    historicoIndividual.setEstadoPresupuesto(tipoMDLDomain);
    historicoIndividual.setFecEstado(instant);

    historicoIndividual.setCausaEstPresupuesto(tipoMDLDomain);
    historicoIndividual.setNumOrdenMovEstado("1");
    historicoIndividual.setIndBloqueoRevision("1");


    //Estado individual
    List<HistoricoDomain> listaHistoricosIndividual = new ArrayList<>();
    listaHistoricosIndividual.add(historicoIndividual);
    estadoIndividual.setHistoricos(listaHistoricosIndividual);


    //Fecha individual
    fechaIndividual.setFecAlta(instant);
    fechaIndividual.setFecEfectoPoliza(instant);
    fechaIndividual.setFecFinValidez(instant);
    fechaIndividual.setFecTarificacion(instant);
    fechaIndividual.setFecVencPoliza(instant);

    //FechaYEstado individual
    fechaYEstadoIndividual.setEstado(estadoIndividual);
    fechaYEstadoIndividual.setFecha(fechaIndividual);

    presupuestoIndividual.setFechaYEstado(fechaYEstadoIndividual);

    //DatoCobroBancario Individual
    datoCobroBancarioIndividual.setCodBic("1");
    datoCobroBancarioIndividual.setCodIban("1");

    datoCobroBancarioIndividual.setTipoDomBancario(tipoMDLDomain);

    datoCobroBancarioIndividual.setEntidadBancaria(tipoMDLDomain);
    datoCobroBancarioIndividual.setNumCuentaBanc("1");
    datoCobroBancarioIndividual.setNumDigContrEntidOfic("1");
    datoCobroBancarioIndividual.setNumDigContrNumCuent("1");
    datoCobroBancarioIndividual.setTitulCuentBanc("1");


    //DatoCobroFisico Individual
    datoCobroFisicoIndividual.setTipoDomCobro(tipoMDLDomain);
    datoCobroFisicoIndividual.setPais(tipoMDLDomain);

    datoCobroFisicoIndividual.setProvincia(tipoMDLDomain);

    datoCobroFisicoIndividual.setLocalidad(tipoMDLDomain);
    datoCobroFisicoIndividual.setCodMunicipio("1");
    datoCobroFisicoIndividual.setCodPostal("1");
    datoCobroFisicoIndividual.setCodEntColectiva("1");
    datoCobroFisicoIndividual.setCodEntSingular("1");
    datoCobroFisicoIndividual.setCodNucPobla("1");

    datoCobroFisicoIndividual.setDenomPoblaPers(tipoMDLDomain);

    datoCobroFisicoIndividual.setTipoVia(tipoMDLDomain);
    datoCobroFisicoIndividual.setDescDomicilio("1");
    datoCobroFisicoIndividual.setNumDomicilio("1");
    datoCobroFisicoIndividual.setCompNumDomic("1");
    datoCobroFisicoIndividual.setNumBloqueDomic("1");
    datoCobroFisicoIndividual.setNumPortalDomic("1");
    datoCobroFisicoIndividual.setNumEscalDomic("1");
    datoCobroFisicoIndividual.setNumPisoDomic("1");
    datoCobroFisicoIndividual.setNumPuertaDomic("1");
    datoCobroFisicoIndividual.setOtrosDatosDomic("1");

    //DatoCobroInterno Individual

    datoCobroInternoIndividual.setCobPagInterComp(tipoMDLDomain);

    //DatosOtrosCobPagBanc Individual
    datoOtrosCobPagBancIndividual.setTipoDomBancOtroCob(tipoMDLDomain);

    datoOtrosCobPagBancIndividual.setEntidadBancOtroCobro(tipoMDLDomain);
    datoOtrosCobPagBancIndividual.setNumDigContrNumCuent("1");
    datoOtrosCobPagBancIndividual.setNumDigContrEntidOfic("1");
    datoOtrosCobPagBancIndividual.setNumCuentaBanc("1");
    datoOtrosCobPagBancIndividual.setTitulCuentBanc("1");
    datoOtrosCobPagBancIndividual.setCodIban("1");
    datoOtrosCobPagBancIndividual.setCodBic("1");

    //DatoCobro Individual
    datosOtrosCobPagBancLista.add(datoOtrosCobPagBancIndividual);
    datoCobroIndividual.setNivelCobro(tipoMDLDomain);
    datoCobroIndividual.setFormaPago(tipoMDLDomain);
    datoCobroIndividual.setMedioCobroPago(tipoMDLDomain);
    datoCobroIndividual.setDatosOtrosCobPagBanc(datosOtrosCobPagBancLista);

    //DatosCobroOtrosMedios
    DatosCobroOtrosMediosDomain datosCobroOtrosMediosDomain = new DatosCobroOtrosMediosDomain("1", "1", "1");
    datosCobroOtrosMedios.add(datosCobroOtrosMediosDomain);


    //DatoMedioCobro Individual
    datoMedioCobroIndividual.setDatoCobroBancario(datoCobroBancarioIndividual);
    datoMedioCobroIndividual.setDatoCobroFisico(datoCobroFisicoIndividual);
    datoMedioCobroIndividual.setDatoCobroInterno(datoCobroInternoIndividual);
    datoMedioCobroIndividual.setDatosCobroOtrosMedios(datosCobroOtrosMedios);

    //DomicilioPers Individual
    domicilioPersIndividual.setIdDomicilio("1");

    domicilioPersIndividual.setPaisDomPers(tipoMDLDomain);

    domicilioPersIndividual.setLocalidadDomPers(tipoMDLDomain);

    domicilioPersIndividual.setProvinciaDomPers(tipoMDLDomain);
    domicilioPersIndividual.setCodMunicipio("1");
    domicilioPersIndividual.setCodEntColectiva("1");
    domicilioPersIndividual.setCodEntSingular("1");
    domicilioPersIndividual.setCodNucPobla("1");

    domicilioPersIndividual.setDenomPoblaDomPers(tipoMDLDomain);
    domicilioPersIndividual.setCodPostal("1");

    domicilioPersIndividual.setTipoViaDomPers(tipoMDLDomain);
    domicilioPersIndividual.setDesDomicilio("1");
    domicilioPersIndividual.setNumDomicilio("1");
    domicilioPersIndividual.setNumComplemento("1");
    domicilioPersIndividual.setNumBloque("1");
    domicilioPersIndividual.setNumPortal("1");
    domicilioPersIndividual.setNumEscalera("1");
    domicilioPersIndividual.setNumPiso("1");
    domicilioPersIndividual.setNumPuerta("1");
    domicilioPersIndividual.setIndNormalizado("1");
    domicilioPersIndividual.setDesOtrosDatos("1");

    //MediosDeContacto Individual

    mediosDeContactosIndividual.setMedioContacto(tipoMDLDomain);

    mediosDeContactosIndividual.setPaisOrigen(tipoMDLDomain);
    mediosDeContactosIndividual.setNumTelefono("1");
    mediosDeContactosIndividual.setNomCorreoElectronico("1");

    //DatosPersonales Individual

    datosPersonalesIndividual.setTipoRol(tipoMDLDomain);
    datosPersonalesIndividual.setNumOrdenRol("1");
    datosPersonalesIndividual.setNumIdPersona("1");
    datosPersonalesIndividual.setNumIdCliente("1");

    datosPersonalesIndividual.setTipoPersona(tipoMDLDomain);

    datosPersonalesIndividual.setTipoDocumento(tipoMDLDomain);
    datosPersonalesIndividual.setNumDocumento("1");

    datosPersonalesIndividual.setSexoPersona(tipoMDLDomain);
    datosPersonalesIndividual.setTxtNombre("1");
    datosPersonalesIndividual.setTxtPrimerApellido("1");
    datosPersonalesIndividual.setTxtSegundoApellido("1");
    datosPersonalesIndividual.setTxtRazonSocial("1");
    datosPersonalesIndividual.setFecNacimiento(instant);

    datosPersonalesIndividual.setProfesion(tipoMDLDomain);

    datosPersonalesIndividual.setAgrupProfesion(tipoMDLDomain);

    datosPersonalesIndividual.setNacionalidad(tipoMDLDomain);
    datosPersonalesIndividual.setIndEstadoCivil("1");
    datosPersonalesIndividual.setDomicilioPers(domicilioPersIndividual);
    List<MedioDeContactoDomain> listaMediosDeContactos = new ArrayList<>();
    listaMediosDeContactos.add(mediosDeContactosIndividual);
    datosPersonalesIndividual.setMediosDeContactos(listaMediosDeContactos);

    presupuestoIndividual.setDatoCobro(datoCobroIndividual);

    //Figura individual
    figuraIndividual.setNumAsegurados("1");
    List<DatoPersonalDomain> listaDatosPersonales = new ArrayList<>();
    listaDatosPersonales.add(datosPersonalesIndividual);
    figuraIndividual.setDatosPersonales(listaDatosPersonales);

    presupuestoIndividual.setFigura(figuraIndividual);

    //EstructuraComercial Individual

    estructuraComercialIndividual.setCanalMediacion(tipoMDLDomain);

    estructuraComercialIndividual.setMedioDistribucion(tipoMDLDomain);

    estructuraComercialIndividual.setAgencia(tipoMDLDomain);

    estructuraComercialIndividual.setZona(tipoMDLDomain);

    estructuraComercialIndividual.setDistrito(tipoMDLDomain);

    estructuraComercialIndividual.setOficina(tipoMDLDomain);
    estructuraComercialIndividual.setCodMediador("1");

    estructuraComercialIndividual.setTipoMediador(tipoMDLDomain);

    estructuraComercialIndividual.setTipoColaborador(tipoMDLDomain);
    estructuraComercialIndividual.setCodColaborador("1");

    estructuraComercialIndividual.setTipoColaboradorIni(tipoMDLDomain);
    estructuraComercialIndividual.setCodColabInicial("1");
    estructuraComercialIndividual.setCodMonitor("1");
    estructuraComercialIndividual.setCodMonitorInicial("1");

    estructuraComercialIndividual.setRedVenta(tipoMDLDomain);
    estructuraComercialIndividual.setIndTipoMedioCom("1");

    //Coordenada Individual

    coordenadaIndividual.setTipoCoordenada(tipoMDLDomain);
    coordenadaIndividual.setNumCoordX(1d);
    coordenadaIndividual.setNumCoordY(1d);
    coordenadaIndividual.setIndSistema("1");

    //DomicilioPresupuesto Individual
    domicilioPresupuestoIndividual.setIdDomicilio("1");

    domicilioPresupuestoIndividual.setPaisPresup(tipoMDLDomain);

    domicilioPresupuestoIndividual.setLocalidadPresup(tipoMDLDomain);

    domicilioPresupuestoIndividual.setProvinciaPresup(tipoMDLDomain);
    domicilioPresupuestoIndividual.setCodMunicipio("1");
    domicilioPresupuestoIndividual.setCodEntColectiva("1");
    domicilioPresupuestoIndividual.setCodEntSingular("1");
    domicilioPresupuestoIndividual.setCodNucPobla("1");

    domicilioPresupuestoIndividual.setDenomPoblaPresup(tipoMDLDomain);
    domicilioPresupuestoIndividual.setCodPostal("1");

    domicilioPresupuestoIndividual.setTipoViaPresup(tipoMDLDomain);
    domicilioPresupuestoIndividual.setDesDomicilio("1");
    domicilioPresupuestoIndividual.setNumNumero("1");
    domicilioPresupuestoIndividual.setNumComplemento("1");
    domicilioPresupuestoIndividual.setNumBloque("1");
    domicilioPresupuestoIndividual.setNumPortal("1");
    domicilioPresupuestoIndividual.setNumEscalera("1");
    domicilioPresupuestoIndividual.setNumPiso("1");
    domicilioPresupuestoIndividual.setNumPuerta("1");
    domicilioPresupuestoIndividual.setIndNormalizado("1");
    domicilioPresupuestoIndividual.setDesOtrosDatos("1");

    presupuestoIndividual.setEstructuraComercial(estructuraComercialIndividual);

    //EstructuraGeografica Individual
    estructuraGeograficaIndividual.setCoordenada(coordenadaIndividual);
    estructuraGeograficaIndividual.setDomicilioPresupuesto(domicilioPresupuestoIndividual);

    presupuestoIndividual.setEstructuraGeografica(estructuraGeograficaIndividual);
    //Importe Individual

    importeIndividual.setMoneda(tipoMDLDomain);
    importeIndividual.setImpTotalRecibo(1D);
    NivelPresupuestoDomain nivelesPresupuestos = new NivelPresupuestoDomain();

    nivelesPresupuestos.setTipoPrima(tipoMDLDomain);
    nivelesPresupuestos.setIndModServEconomico("1d");
    List<NivelPresupuestoDomain> nivelesPresupuestosList = new ArrayList<>();
    nivelesPresupuestosList.add(nivelesPresupuestos);
    SobreprimaDomain sobreprimas = new SobreprimaDomain();
    sobreprimas.setImpSobreprima(1D);
    List<SobreprimaDomain> sobreprimasList = new ArrayList<>();
    sobreprimasList.add(sobreprimas);
    nivelesPresupuestos.setSobreprimas(sobreprimasList);
    BonificacionDescDomain bonificacionesDesc = new BonificacionDescDomain();
    bonificacionesDesc.setImpBonificacionDesc(1d);
    bonificacionesDesc.setPorCesionComisionMed(1d);
    List<BonificacionDescDomain> bonificacionesDescList = new ArrayList<>();
    bonificacionesDescList.add(bonificacionesDesc);
    nivelesPresupuestos.setBonificacionesDesc(bonificacionesDescList);
    ImpuestoDomain impuestos = new ImpuestoDomain();
    impuestos.setImpRecargoImp(1d);
    List<ImpuestoDomain> impuestosList = new ArrayList<>();
    impuestosList.add(impuestos);
    nivelesPresupuestos.setImpuestos(impuestosList);
    importeIndividual.setNivelesPresupuestos(nivelesPresupuestosList);

    presupuestoIndividual.setImporte(importeIndividual);

    //Campanna Individual

    campannasIndividual.setCampannaComercial(tipoMDLDomain);
    campannasIndividual.setIdCampanna("1");

    campannasIndividual.setIncentivoCampanna(tipoMDLDomain);

    campannasIndividual.setTipoCampanna(tipoMDLDomain);
    campannasIndividual.setFecInicio(instant);
    campannasIndividual.setFecFinVigencia(instant);
    campannasIndividual.setIndCaracIncentivo("1");
    List<CampannaDomain> campannasList = new ArrayList<>();
    campannasList.add(campannasIndividual);
    presupuestoIndividual.setCampannas(campannasList);

    //ObjetosAsegurados Individual
    objetosAseguradosIndividual.setIdObjAsegODL("1");
    List<ObjetosAseguradosDomain> objetosAseguradosList = new ArrayList<>();
    objetosAseguradosList.add(objetosAseguradosIndividual);

    presupuestoIndividual.setObjetosAsegurados(objetosAseguradosList);
    AuditoriaDomain auditoriaDomain = new AuditoriaDomain();
    auditoriaDomain.setCreatedByUser("1");
    auditoriaDomain.setCreatedDate(instant);
    auditoriaDomain.setLastModifiedByUser("1");
    auditoriaDomain.setLastModifiedDate(instant);

    //Metadata Individual
    metadataIndividual.setTxtCircuitoOrigen("1");
    metadataIndividual.setFecSQL(instant);
    metadataIndividual.setHash("1");
    metadataIndividual.setCodVersion("1");
    metadataIndividual.setNumOffsetMDL(1L);
    metadataIndividual.setNumOffsetCarga(1L);
    metadataIndividual.setAuditoria(auditoriaDomain);
    presupuestoIndividual.setMetadata(metadataIndividual);

    HistPresupuestoIndividualDomainMapperImpl mapper = new HistPresupuestoIndividualDomainMapperImpl();
    PresupuestoIndividualRequestBodyResource result = mapper.toResource(presupuestoIndividual);
    assertThat(result).isNotNull();

  }
}
