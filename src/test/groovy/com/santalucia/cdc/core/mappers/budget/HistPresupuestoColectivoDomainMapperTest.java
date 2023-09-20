package com.santalucia.cdc.core.mappers.budget;

import com.santalucia.arq.ams.odl.presupuestos.historico.colectivo.api.model.*;
import com.santalucia.cdc.core.domain.MetadataDomain;
import com.santalucia.cdc.core.domain.budgets.collectivebudget.PresupuestoColectivoDomain;
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

class HistPresupuestoColectivoDomainMapperTest {

  @DisplayName("histPresupColToDomain")
  @Test
  void toDomain() {

    OffsetDateTime offsetDateTime = OffsetDateTime.now();
    EntityModelPresupuestoColectivoResource presupuestoColectivo = new EntityModelPresupuestoColectivoResource();
    DatoIdentificativoResource datoIdentificativoColectivo = new DatoIdentificativoResource();
    presupuestoColectivo.setId("1");

    ProductoResource productoColectivo = new ProductoResource();
    FechaYEstadoResource fechaYEstadoColectivo = new FechaYEstadoResource();
    FechaResource fechaColectivo = new FechaResource();
    EstadoResource estadoColectivo = new EstadoResource();
    HistoricoResource historicoColectivo = new HistoricoResource();
    DatoMedioCobroResource datoMedioCobroColectivo = new DatoMedioCobroResource();
    DatoCobroResource datoCobroColectivo = new DatoCobroResource();
    DatosOtrosCobPagBancResource datosOtrosCobPagBancColectivo = new DatosOtrosCobPagBancResource();
    DatoCobroBancarioResource datoCobroBancarioColectivo = new DatoCobroBancarioResource();
    DatoCobroFisicoResource datoCobroFisicoColectivo = new DatoCobroFisicoResource();
    DatoCobroInternoResource datoCobroInternoColectivo = new DatoCobroInternoResource();
    List<DatosCobroOtrosMedioResource> datosCobroOtrosMedios = new ArrayList<>();
    DomicilioPersResource domicilioPersColectivo = new DomicilioPersResource();
    MediosDeContactoResource mediosDeContactosColectivo = new MediosDeContactoResource();
    DatosPersonalesResource datosPersonalesColectivo = new DatosPersonalesResource();
    FiguraResource figuraColectivo = new FiguraResource();
    EstructuraComercialResource estructuraComercialColectivo = new EstructuraComercialResource();
    EstructuraGeograficaResource estructuraGeograficaColectivo = new EstructuraGeograficaResource();
    DomicilioPresupuestoResource domicilioPresupuestoColectivo = new DomicilioPresupuestoResource();
    CoordenadaResource coordenadaColectivo = new CoordenadaResource();
    CampannaResource campannasColectivo = new CampannaResource();
    ObjetosAseguradosResource objetosAseguradosColectivo = new ObjetosAseguradosResource();
    MetadataResource metadataColectivo = new MetadataResource();


    //Datos identificadivos de individual
    datoIdentificativoColectivo.setIdPresupuestoODL("1");
    TipoPresupuestoResource tipoPresupuestoResource = new TipoPresupuestoResource("1", "1", "1");
    datoIdentificativoColectivo.setTipoPresupuesto(tipoPresupuestoResource);
    CompanniaRespServResource companniaRespServResource = new CompanniaRespServResource("1", "1", "1");
    datoIdentificativoColectivo.setCompanniaRespServ(companniaRespServResource);
    SistemaOrigenResource sistemaOrigenResource = new SistemaOrigenResource("1", "1", "1");
    datoIdentificativoColectivo.setSistemaOrigen(sistemaOrigenResource);
    SistemaActualResource sistemaActualResource = new SistemaActualResource("1", "1", "1");
    datoIdentificativoColectivo.setSistemaActual(sistemaActualResource);
    datoIdentificativoColectivo.setNumIdentificacion("1");
    datoIdentificativoColectivo.setCodIdentificacion("1");
    datoIdentificativoColectivo.setNumIdAgrupacion("1");
    datoIdentificativoColectivo.setCodIdAgrupacion("1");
    datoIdentificativoColectivo.setVersPresupuesto(1D);
    datoIdentificativoColectivo.setVersPresupuestoODL("1");
    datoIdentificativoColectivo.setIndVersSeleccionada("1");
    datoIdentificativoColectivo.setIdPolizaOrigen("1");
    datoIdentificativoColectivo.setIdMensaje("1");
    datoIdentificativoColectivo.setCodPackComercial("1");
    datoIdentificativoColectivo.setNumPoliza("1");
    datoIdentificativoColectivo.setNumPackPoliza("1");

    TipoComisionResource tipoComisionResource = new TipoComisionResource("1", "1", "1");
    datoIdentificativoColectivo.setTipoComision(tipoComisionResource);
    datoIdentificativoColectivo.setCodRenovacion("1");
    datoIdentificativoColectivo.setIndCompanniaVendedora("1");
    datoIdentificativoColectivo.setCodOportSalesforce("1");
    datoIdentificativoColectivo.setCodVersionComercial("1");
    PeriodPolizaResource periodPolizaResource = new PeriodPolizaResource("1", "1", "1");
    datoIdentificativoColectivo.setPeriodPoliza(periodPolizaResource);
    datoIdentificativoColectivo.setIndOrigRecomendador("1");
    datoIdentificativoColectivo.setCodEvento("1");

    presupuestoColectivo.setDatoIdentificativo(datoIdentificativoColectivo);
    //Producto individual


    RamoComercialResource ramoComercialResource = new RamoComercialResource("1", "1", "1");
    productoColectivo.setRamoComercial(ramoComercialResource);
    ModalidadComercialResource modalidadComercialResource = new ModalidadComercialResource();
    productoColectivo.setModalidadComercial(modalidadComercialResource);
    ProductoTecnicoResource productoTecnicoResource = new ProductoTecnicoResource("1", "1", "1");
    productoColectivo.setProductoTecnico(productoTecnicoResource);
    ProductoComercialResource productoComercialResource = new ProductoComercialResource("1", "1", "1");
    productoColectivo.setProductoComercial(productoComercialResource);
    productoColectivo.setDescModalidadInterna("1");

    presupuestoColectivo.setProducto(productoColectivo);

    //Historico individual
    EstadoPresupuestoResource estadoPresupuestoResource = new EstadoPresupuestoResource("1", "1", "1");
    historicoColectivo.setEstadoPresupuesto(estadoPresupuestoResource);
    historicoColectivo.setFecEstado(offsetDateTime);
    CausaEstPresupuestoResource causaEstPresupuestoResource = new CausaEstPresupuestoResource("1", "1", "1");
    historicoColectivo.setCausaEstPresupuesto(causaEstPresupuestoResource);
    historicoColectivo.setNumOrdenMovEstado("1");
    historicoColectivo.setIndBloqueoRevision("1");


    //Estado individual
    List<HistoricoResource> listaHistoricosIndividual = new ArrayList<>();
    listaHistoricosIndividual.add(historicoColectivo);
    estadoColectivo.setHistoricos(listaHistoricosIndividual);


    //Fecha individual
    fechaColectivo.setFecAlta(offsetDateTime);
    fechaColectivo.setFecEfectoPoliza(offsetDateTime);
    fechaColectivo.setFecFinValidez(offsetDateTime);
    fechaColectivo.setFecTarificacion(offsetDateTime);
    fechaColectivo.setFecVencPoliza(offsetDateTime);

    //FechaYEstado individual
    fechaYEstadoColectivo.setEstado(estadoColectivo);
    fechaYEstadoColectivo.setFecha(fechaColectivo);

    presupuestoColectivo.setFechaYEstado(fechaYEstadoColectivo);

    //DatoCobroBancario Individual
    datoCobroBancarioColectivo.setCodBic("1");
    datoCobroBancarioColectivo.setCodIban("1");
    TipoDomBancarioResource tipoDomBancarioResource = new TipoDomBancarioResource("1", "1", "1");
    datoCobroBancarioColectivo.setTipoDomBancario(tipoDomBancarioResource);
    EntidadBancariaResource entidadBancariaResource = new EntidadBancariaResource("1", "1", "1");
    datoCobroBancarioColectivo.setEntidadBancaria(entidadBancariaResource);
    datoCobroBancarioColectivo.setNumCuentaBanc(1D);
    datoCobroBancarioColectivo.setNumDigContrEntidOfic("1");
    datoCobroBancarioColectivo.setNumDigContrNumCuent("1");
    datoCobroBancarioColectivo.setTitulCuentBanc("1");


    //DatoCobroFisico Individual
    TipoDomCobroResource tipoDomCobroResource = new TipoDomCobroResource("1", "1", "1");
    datoCobroFisicoColectivo.setTipoDomCobro(tipoDomCobroResource);
    PaisResource paisResource = new PaisResource("1", "1", "1");
    datoCobroFisicoColectivo.setPais(paisResource);
    ProvinciaResource provinciaResource = new ProvinciaResource("1", "1", "1");
    datoCobroFisicoColectivo.setProvincia(provinciaResource);
    LocalidadResource localidadResource = new LocalidadResource("1", "1", "1");
    datoCobroFisicoColectivo.setLocalidad(localidadResource);
    datoCobroFisicoColectivo.setCodMunicipio("1");
    datoCobroFisicoColectivo.setCodPostal("1");
    datoCobroFisicoColectivo.setCodEntColectiva("1");
    datoCobroFisicoColectivo.setConEntSingular("1");
    datoCobroFisicoColectivo.setCodNucPobla("1");
    DenomPoblaPersResource denomPoblaPersResource = new DenomPoblaPersResource("1", "1", "1");
    datoCobroFisicoColectivo.setDenomPoblaPers(denomPoblaPersResource);
    TipoViaResource tipoViaResource = new TipoViaResource("1", "1", "1");
    datoCobroFisicoColectivo.setTipoVia(tipoViaResource);
    datoCobroFisicoColectivo.setDescDomicilio("1");
    datoCobroFisicoColectivo.setNumDomicilio("1");
    datoCobroFisicoColectivo.setCompNumDomic("1");
    datoCobroFisicoColectivo.setNumBloqueDomic("1");
    datoCobroFisicoColectivo.setNumPortalDomic("1");
    datoCobroFisicoColectivo.setNumEscalDomic("1");
    datoCobroFisicoColectivo.setNumPisoDomic("1");
    datoCobroFisicoColectivo.setNumPuertaDomic("1");
    datoCobroFisicoColectivo.setOtrosDatosDomic("1");

    //DatoCobroInterno Individual
    CobPagInterCompResource cobPagInterCompResource = new CobPagInterCompResource("1", "1", "1");
    datoCobroInternoColectivo.setCobPagInterComp(cobPagInterCompResource);
    //DatosCobroOtrosMedios
    DatosCobroOtrosMedioResource datosCobroOtrosMediosDomain = new DatosCobroOtrosMedioResource("1", "1", "1");
    datosCobroOtrosMedios.add(datosCobroOtrosMediosDomain);
    //DatoMedioCobro Individual
    datoMedioCobroColectivo.setDatoCobroBancario(datoCobroBancarioColectivo);
    datoMedioCobroColectivo.setDatoCobroFisico(datoCobroFisicoColectivo);
    datoMedioCobroColectivo.setDatoCobroInterno(datoCobroInternoColectivo);
    datoMedioCobroColectivo.setDatosCobroOtrosMedios(datosCobroOtrosMedios);

    //DatosOtrosCobPagBanc Individual
    TipoDomBancOtroCobResource tipoDomBancOtroCobResource = new TipoDomBancOtroCobResource("1", "1", "1");
    datosOtrosCobPagBancColectivo.setTipoDomBancOtroCob(tipoDomBancOtroCobResource);
    EntidadBancOtroCobroResource entidadBancOtroCobroResource = new EntidadBancOtroCobroResource("1", "1", "1");
    datosOtrosCobPagBancColectivo.setEntidadBancOtroCobro(entidadBancOtroCobroResource);
    datosOtrosCobPagBancColectivo.setNumDigContrNumCuent("1");
    datosOtrosCobPagBancColectivo.setNumDigContrEntidOfic("1");
    datosOtrosCobPagBancColectivo.setNumCuentaBanc(1D);
    datosOtrosCobPagBancColectivo.setTitulCuentBanc("1");
    datosOtrosCobPagBancColectivo.setCodIban("1");
    datosOtrosCobPagBancColectivo.setCodBic("1");

    //DatoCobro Individual
    List<DatosOtrosCobPagBancResource> listdatosOtrosCobPagBanc = new ArrayList<>();
    listdatosOtrosCobPagBanc.add(datosOtrosCobPagBancColectivo);
    NivelCobroResource nivelCobroResource = new NivelCobroResource("1", "1", "1");
    datoCobroColectivo.setNivelCobro(nivelCobroResource);
    FormaPagoResource formaPagoResource = new FormaPagoResource("1", "1", "1");
    datoCobroColectivo.setFormaPago(formaPagoResource);
    MedioCobroPagoResource medioCobroPagoResource = new MedioCobroPagoResource("1", "1", "1");
    datoCobroColectivo.setMedioCobroPago(medioCobroPagoResource);
    datoCobroColectivo.setDatoMedioCobro(datoMedioCobroColectivo);
    datoCobroColectivo.setDatosOtrosCobPagBanc(listdatosOtrosCobPagBanc);

    //DomicilioPers Individual
    domicilioPersColectivo.setIdDomicilio("1");
    PaisDomPersResource paisDomPersResource = new PaisDomPersResource("1", "1", "1");
    domicilioPersColectivo.setPaisDomPers(paisDomPersResource);
    LocalidadDomPersResource localidadDomPersResource = new LocalidadDomPersResource("1", "1", "1");
    domicilioPersColectivo.setLocalidadDomPers(localidadDomPersResource);
    ProvinciaDomPersResource provinciaDomPersResource = new ProvinciaDomPersResource("1", "1", "1");
    domicilioPersColectivo.setProvinciaDomPers(provinciaDomPersResource);
    domicilioPersColectivo.setCodMunicipio("1");
    domicilioPersColectivo.setCodEntColectiva("1");
    domicilioPersColectivo.setCodEntSingular("1");
    domicilioPersColectivo.setCodNucPobla("1");
    DenomPoblaDomPersResource denomPoblaDomPersResource = new DenomPoblaDomPersResource("1", "1", "1");
    domicilioPersColectivo.setDenomPoblaDomPers(denomPoblaDomPersResource);
    domicilioPersColectivo.setCodPostal("1");
    TipoViaDomPersResource tipoViaDomPersResource = new TipoViaDomPersResource("1", "1", "1");
    domicilioPersColectivo.setTipoViaDomPers(tipoViaDomPersResource);
    domicilioPersColectivo.setDescDomicilio("1");
    domicilioPersColectivo.setNumDomicilio("1");
    domicilioPersColectivo.setNumComplemento("1");
    domicilioPersColectivo.setNumBloque("1");
    domicilioPersColectivo.setNumPortal("1");
    domicilioPersColectivo.setNumEscalera("1");
    domicilioPersColectivo.setNumPiso("1");
    domicilioPersColectivo.setNumPuerta("1");
    domicilioPersColectivo.setIndNormalizado("1");
    domicilioPersColectivo.setDesOtrosDatos("1");

    //MediosDeContacto Individual
    MedioContactoResource medioContactoResource = new MedioContactoResource("1", "1", "1");
    mediosDeContactosColectivo.setMedioContacto(medioContactoResource);
    PaisOrigenResource paisOrigenResource = new PaisOrigenResource("1", "1", "1");
    mediosDeContactosColectivo.setPaisOrigen(paisOrigenResource);
    mediosDeContactosColectivo.setNumTelefono("1");
    mediosDeContactosColectivo.setNomCorreoElectronico("1");

    //DatosPersonales Individual
    TipoRolResource tipoRolResource = new TipoRolResource("1", "1", "1");
    datosPersonalesColectivo.setTipoRol(tipoRolResource);
    datosPersonalesColectivo.setNumOrdenRol("1");
    datosPersonalesColectivo.setNumIdPersona("1");
    datosPersonalesColectivo.setNumIdCliente("1");
    TipoPersonaResource tipoPersonaResource = new TipoPersonaResource("1", "1", "1");
    datosPersonalesColectivo.setTipoPersona(tipoPersonaResource);
    TipoDocumentoResource tipoDocumentoResource = new TipoDocumentoResource("1", "1", "1");
    datosPersonalesColectivo.setTipoDocumento(tipoDocumentoResource);
    datosPersonalesColectivo.setNumDocumento("1");
    SexoPersonaResource sexoPersonaResource = new SexoPersonaResource("1", "1", "1");
    datosPersonalesColectivo.setSexoPersona(sexoPersonaResource);
    datosPersonalesColectivo.setTxtNombre("1");
    datosPersonalesColectivo.setTxtPrimerApellido("1");
    datosPersonalesColectivo.setTxtSegundoApellido("1");
    datosPersonalesColectivo.setTxtRazonSocial("1");
    datosPersonalesColectivo.setFecNacimiento(offsetDateTime);
    ProfesionResource profesionResource = new ProfesionResource("1", "1", "1");
    datosPersonalesColectivo.setProfesion(profesionResource);
    AgrupProfesionResource agrupProfesionResource = new AgrupProfesionResource("1", "1", "1");
    datosPersonalesColectivo.setAgrupProfesion(agrupProfesionResource);
    NacionalidadResource nacionalidadResource = new NacionalidadResource("1", "1", "1");
    datosPersonalesColectivo.setNacionalidad(nacionalidadResource);
    datosPersonalesColectivo.setIndEstadoCivil("1");
    datosPersonalesColectivo.setDomicilioPers(domicilioPersColectivo);
    List<MediosDeContactoResource> listaMediosDeContactos = new ArrayList<>();
    listaMediosDeContactos.add(mediosDeContactosColectivo);
    datosPersonalesColectivo.setMediosDeContactos(listaMediosDeContactos);

    presupuestoColectivo.setDatoCobro(datoCobroColectivo);

    //Figura individual
    figuraColectivo.setNumAsegurados("1");
    List<DatosPersonalesResource> listaDatosPersonales = new ArrayList<>();
    listaDatosPersonales.add(datosPersonalesColectivo);
    figuraColectivo.setDatosPersonales(listaDatosPersonales);

    presupuestoColectivo.setFigura(figuraColectivo);

    //EstructuraComercial Individual
    CanalMediacionResource canalMediacionResource = new CanalMediacionResource("1", "1", "1");
    estructuraComercialColectivo.setCanalMediacion(canalMediacionResource);
    MedioDistribucionResource medioDistribucionResource = new MedioDistribucionResource("1", "1", "1");
    estructuraComercialColectivo.setMedioDistribucion(medioDistribucionResource);
    AgenciaResource agenciaResource = new AgenciaResource("1", "1", "1");
    estructuraComercialColectivo.setAgencia(agenciaResource);
    ZonaResource zonaResource = new ZonaResource("1", "1", "1");
    estructuraComercialColectivo.setZona(zonaResource);
    DistritoResource distritoResource = new DistritoResource("1", "1", "1");
    estructuraComercialColectivo.setDistrito(distritoResource);
    OficinaResource oficinaResource = new OficinaResource("1", "1", "1");
    estructuraComercialColectivo.setOficina(oficinaResource);
    estructuraComercialColectivo.setCodMediador("1");
    TipoMediadorResource tipoMediadorResource = new TipoMediadorResource("1", "1", "1");
    estructuraComercialColectivo.setTipoMediador(tipoMediadorResource);
    TipoColaboradorResource tipoColaboradorResource = new TipoColaboradorResource("1", "1", "1");
    estructuraComercialColectivo.setTipoColaborador(tipoColaboradorResource);
    estructuraComercialColectivo.setCodColaborador("1");
    TipoColaboradorIniResource tipoColaboradorIniResource = new TipoColaboradorIniResource("1", "1", "1");
    estructuraComercialColectivo.setTipoColaboradorIni(tipoColaboradorIniResource);
    estructuraComercialColectivo.setCodColabInicial("1");
    estructuraComercialColectivo.setCodMonitor("1");
    estructuraComercialColectivo.setCodMonitorInicial("1");
    RedVentaResource redVentaResource = new RedVentaResource("1", "1", "1");
    estructuraComercialColectivo.setRedVenta(redVentaResource);
    estructuraComercialColectivo.setIndTipoMedioCom("1");

    //Coordenada Individual
    TipoCoordenadaResource tipoCoordenadaResource = new TipoCoordenadaResource("1", "1", "1");
    coordenadaColectivo.setTipoCoordenada(tipoCoordenadaResource);
    coordenadaColectivo.setNumCoordX(1d);
    coordenadaColectivo.setNumCoordY(1d);
    coordenadaColectivo.setIndSistema("1");

    //DomicilioPresupuesto Individual
    domicilioPresupuestoColectivo.setIdDomicilio("1");
    PaisPresupResource paisPresupResource = new PaisPresupResource("1", "1", "1");
    domicilioPresupuestoColectivo.setPaisPresup(paisPresupResource);
    LocalidadPresupResource localidadPresupResource = new LocalidadPresupResource("1", "1", "1");
    domicilioPresupuestoColectivo.setLocalidadPresup(localidadPresupResource);
    ProvinciaPresupResource provinciaPresupResource = new ProvinciaPresupResource("1", "1", "1");
    domicilioPresupuestoColectivo.setProvinciaPresup(provinciaPresupResource);
    domicilioPresupuestoColectivo.setCodMunicipio("1");
    domicilioPresupuestoColectivo.setCodEntColectiva("1");
    domicilioPresupuestoColectivo.setCodEntSingular("1");
    domicilioPresupuestoColectivo.setCodNucPobla("1");
    DenomPoblaPresupResource denomPoblaPresupResource = new DenomPoblaPresupResource("1", "1", "1");
    domicilioPresupuestoColectivo.setDenomPoblaPresup(denomPoblaPresupResource);
    domicilioPresupuestoColectivo.setCodPostal("1");
    TipoViaPresupResource tipoViaPresupResource = new TipoViaPresupResource("1", "1", "1");
    domicilioPresupuestoColectivo.setTipoViaPresup(tipoViaPresupResource);
    domicilioPresupuestoColectivo.setDescDomicilio("1");
    domicilioPresupuestoColectivo.setNumNumero("1");
    domicilioPresupuestoColectivo.setNumComplemento("1");
    domicilioPresupuestoColectivo.setNumBloque("1");
    domicilioPresupuestoColectivo.setNumPortal("1");
    domicilioPresupuestoColectivo.setNumEscalera("1");
    domicilioPresupuestoColectivo.setNumPiso("1");
    domicilioPresupuestoColectivo.setNumPuerta("1");
    domicilioPresupuestoColectivo.setIndNormalizado("1");
    domicilioPresupuestoColectivo.setDesOtrosDatos("1");

    presupuestoColectivo.setEstructuraComercial(estructuraComercialColectivo);

    //EstructuraGeografica Individual
    estructuraGeograficaColectivo.setCoordenada(coordenadaColectivo);
    estructuraGeograficaColectivo.setDomicilioPresupuesto(domicilioPresupuestoColectivo);

    presupuestoColectivo.setEstructuraGeografica(estructuraGeograficaColectivo);

    //Campanna Individual
    CampannaComercialResource campannaComercialResource = new CampannaComercialResource("1", "1", "1");
    campannasColectivo.setCampannaComercial(campannaComercialResource);
    campannasColectivo.setIdCampanna("1");
    IncentivoCampannaResource incentivoCampannaResource = new IncentivoCampannaResource("1", "1", "1");
    campannasColectivo.setIncentivoCampanna(incentivoCampannaResource);
    TipoCampannaResource tipoCampannaResource = new TipoCampannaResource("1", "1", "1");
    campannasColectivo.setTipoCampanna(tipoCampannaResource);
    campannasColectivo.setFecInicio(offsetDateTime);
    campannasColectivo.setFecFinVigencia(offsetDateTime);
    campannasColectivo.setIndCaracIncentivo("1");
    List<CampannaResource> campannasList = new ArrayList<>();
    campannasList.add(campannasColectivo);
    presupuestoColectivo.setCampannas(campannasList);

    //ObjetosAsegurados Individual
    objetosAseguradosColectivo.setIdObjAsegODL("1");
    List<ObjetosAseguradosResource> objetosAseguradosList = new ArrayList<>();
    objetosAseguradosList.add(objetosAseguradosColectivo);

    presupuestoColectivo.setObjetosAsegurados(objetosAseguradosList);
    /*Auditoria auditoriaDomain = new AuditoriaDomain();
    auditoriaDomain.setCreatedByUser("1");
    auditoriaDomain.setCreatedDate(offsetDateTime);
    auditoriaDomain.setLastModifiedByUser("1");
    auditoriaDomain.setLastModifiedDate(offsetDateTime);*/

    //Metadata Individual
    metadataColectivo.setTxtCircuitoOrigen("1");
    metadataColectivo.setFecSQL(offsetDateTime);
    /*metadataIndividual.setHash("1");*/
    metadataColectivo.setCodVersion("1");
    metadataColectivo.setNumOffsetMDL(1L);
    metadataColectivo.setNumOffsetCarga(1L);
    /*metadataIndividual.setAuditoria(auditoriaDomain);*/
    presupuestoColectivo.setMetadata(metadataColectivo);

    HistPresupuestoColectivoDomainMapper mapper = new HistPresupuestoColectivoDomainMapperImpl();
    PresupuestoColectivoDomain result = mapper.toDomain(presupuestoColectivo);
    assertThat(result).isNotNull();



  }
  @DisplayName("histPresupColToResource")
  @Test
  void toResource() {


    Instant instant = Instant.now();
    PresupuestoColectivoDomain presupuestoColectivo = new PresupuestoColectivoDomain();
    DatoIdentificativoDomain datoIdentificativoColectivo = new DatoIdentificativoDomain();
    TipoMDLDomain tipoMDLDomain = new TipoMDLDomain();
    tipoMDLDomain.setCodMDL("1");
    tipoMDLDomain.setCodOrigen("1");
    tipoMDLDomain.setDescOrigen("1");
    presupuestoColectivo.setId("1");
    ProductoDomain productoColectivo = new ProductoDomain();
    FechaYEstadoDomain fechaYEstadoColectivo = new FechaYEstadoDomain();
    FechaDomain fechaColectivo = new FechaDomain();
    EstadoDomain estadoColectivo = new EstadoDomain();
    HistoricoDomain historicoColectivo = new HistoricoDomain();
    DatoMedioCobroDomain datoMedioCobroColectivo = new DatoMedioCobroDomain();
    DatoCobroDomain datoCobroColectivo = new DatoCobroDomain();
    DatoOtrosCobPagBancDomain datosOtrosCobPagBancColectivo = new DatoOtrosCobPagBancDomain();
    DatoCobroBancarioDomain datoCobroBancarioColectivo = new DatoCobroBancarioDomain();
    DatoCobroFisicoDomain datoCobroFisicoColectivo = new DatoCobroFisicoDomain();
    DatoCobroInternoDomain datoCobroInternoColectivo = new DatoCobroInternoDomain();
    List<DatosCobroOtrosMediosDomain> datosCobroOtrosMedios = new ArrayList<>();
    DomicilioPersDomain domicilioPersColectivo = new DomicilioPersDomain();
    MedioDeContactoDomain mediosDeContactosColectivo = new MedioDeContactoDomain();
    DatoPersonalDomain datosPersonalesColectivo = new DatoPersonalDomain();
    FiguraDomain figuraColectivo = new FiguraDomain();
    EstructuraComercialDomain estructuraComercialColectivo = new EstructuraComercialDomain();
    EstructuraGeograficaDomain estructuraGeograficaColectivo = new EstructuraGeograficaDomain();
    DomicilioPresupuestoDomain domicilioPresupuestoColectivo = new DomicilioPresupuestoDomain();
    CoordenadaDomain coordenadaColectivo = new CoordenadaDomain();
    CampannaDomain campannasColectivo = new CampannaDomain();
    ObjetosAseguradosDomain objetosAseguradosColectivo = new ObjetosAseguradosDomain();
    MetadataDomain metadataColectivo = new MetadataDomain();


    //Datos identificadivos de individual
    datoIdentificativoColectivo.setIdPresupuestoODL("1");
    datoIdentificativoColectivo.setTipoPresupuesto(tipoMDLDomain);
    datoIdentificativoColectivo.setCompanniaRespServ(tipoMDLDomain);
    datoIdentificativoColectivo.setSistemaOrigen(tipoMDLDomain);
    datoIdentificativoColectivo.setSistemaActual(tipoMDLDomain);
    datoIdentificativoColectivo.setNumIdentificacion("1");
    datoIdentificativoColectivo.setCodIdentificacion("1");
    datoIdentificativoColectivo.setNumIdAgrupacion("1");
    datoIdentificativoColectivo.setCodIdAgrupacion("1");
    datoIdentificativoColectivo.setVersPresupuesto("1");
    datoIdentificativoColectivo.setVersPresupuestoODL("1");
    datoIdentificativoColectivo.setIndVersSeleccionada("1");
    datoIdentificativoColectivo.setIdPolizaOrigen("1");
    datoIdentificativoColectivo.setIdMensaje("1");
    datoIdentificativoColectivo.setCodPackComercial("1");
    datoIdentificativoColectivo.setNumPoliza("1");
    datoIdentificativoColectivo.setNumPackPoliza("1");

    datoIdentificativoColectivo.setTipoComision(tipoMDLDomain);
    datoIdentificativoColectivo.setCodRenovacion("1");
    datoIdentificativoColectivo.setIndCompanniaVendedora("1");
    datoIdentificativoColectivo.setCodOportSalesforce("1");
    datoIdentificativoColectivo.setCodVersionComercial("1");
    datoIdentificativoColectivo.setPeriodPoliza(tipoMDLDomain);
    datoIdentificativoColectivo.setIndOrigRecomendador("1");
    datoIdentificativoColectivo.setCodEvento("1");

    presupuestoColectivo.setDatoIdentificativo(datoIdentificativoColectivo);
    //Producto individual

    productoColectivo.setRamoComercial(tipoMDLDomain);

    productoColectivo.setModalidadComercial(tipoMDLDomain);

    productoColectivo.setProductoTecnico(tipoMDLDomain);

    productoColectivo.setProductoComercial(tipoMDLDomain);
    productoColectivo.setDescModalidadInterna("1");

    presupuestoColectivo.setProducto(productoColectivo);

    //Historico individual

    historicoColectivo.setEstadoPresupuesto(tipoMDLDomain);
    historicoColectivo.setFecEstado(instant);

    historicoColectivo.setCausaEstPresupuesto(tipoMDLDomain);
    historicoColectivo.setNumOrdenMovEstado("1");
    historicoColectivo.setIndBloqueoRevision("1");


    //Estado individual
    List<HistoricoDomain> listaHistoricosIndividual = new ArrayList<>();
    listaHistoricosIndividual.add(historicoColectivo);
    estadoColectivo.setHistoricos(listaHistoricosIndividual);


    //Fecha individual
    fechaColectivo.setFecAlta(instant);
    fechaColectivo.setFecEfectoPoliza(instant);
    fechaColectivo.setFecFinValidez(instant);
    fechaColectivo.setFecTarificacion(instant);
    fechaColectivo.setFecVencPoliza(instant);

    //FechaYEstado individual
    fechaYEstadoColectivo.setEstado(estadoColectivo);
    fechaYEstadoColectivo.setFecha(fechaColectivo);

    presupuestoColectivo.setFechaYEstado(fechaYEstadoColectivo);

    //DatoCobroBancario Individual
    datoCobroBancarioColectivo.setCodBic("1");
    datoCobroBancarioColectivo.setCodIban("1");

    datoCobroBancarioColectivo.setTipoDomBancario(tipoMDLDomain);

    datoCobroBancarioColectivo.setEntidadBancaria(tipoMDLDomain);
    datoCobroBancarioColectivo.setNumCuentaBanc("1");
    datoCobroBancarioColectivo.setNumDigContrEntidOfic("1");
    datoCobroBancarioColectivo.setNumDigContrNumCuent("1");
    datoCobroBancarioColectivo.setTitulCuentBanc("1");


    //DatoCobroFisico Individual
    datoCobroFisicoColectivo.setTipoDomCobro(tipoMDLDomain);
    datoCobroFisicoColectivo.setPais(tipoMDLDomain);

    datoCobroFisicoColectivo.setProvincia(tipoMDLDomain);

    datoCobroFisicoColectivo.setLocalidad(tipoMDLDomain);
    datoCobroFisicoColectivo.setCodMunicipio("1");
    datoCobroFisicoColectivo.setCodPostal("1");
    datoCobroFisicoColectivo.setCodEntColectiva("1");
    datoCobroFisicoColectivo.setCodEntSingular("1");
    datoCobroFisicoColectivo.setCodNucPobla("1");

    datoCobroFisicoColectivo.setDenomPoblaPers(tipoMDLDomain);

    datoCobroFisicoColectivo.setTipoVia(tipoMDLDomain);
    datoCobroFisicoColectivo.setDescDomicilio("1");
    datoCobroFisicoColectivo.setNumDomicilio("1");
    datoCobroFisicoColectivo.setCompNumDomic("1");
    datoCobroFisicoColectivo.setNumBloqueDomic("1");
    datoCobroFisicoColectivo.setNumPortalDomic("1");
    datoCobroFisicoColectivo.setNumEscalDomic("1");
    datoCobroFisicoColectivo.setNumPisoDomic("1");
    datoCobroFisicoColectivo.setNumPuertaDomic("1");
    datoCobroFisicoColectivo.setOtrosDatosDomic("1");

    //DatoCobroInterno Individual

    datoCobroInternoColectivo.setCobPagInterComp(tipoMDLDomain);

    //DatosCobroOtrosMedios
    DatosCobroOtrosMediosDomain datosCobroOtrosMediosDomain = new DatosCobroOtrosMediosDomain("1", "1", "1");
    datosCobroOtrosMedios.add(datosCobroOtrosMediosDomain);


    //DatoMedioCobro Individual
    datoMedioCobroColectivo.setDatoCobroBancario(datoCobroBancarioColectivo);
    datoMedioCobroColectivo.setDatoCobroFisico(datoCobroFisicoColectivo);
    datoMedioCobroColectivo.setDatoCobroInterno(datoCobroInternoColectivo);
    datoMedioCobroColectivo.setDatosCobroOtrosMedios(datosCobroOtrosMedios);

    //DatosOtrosCobPagBanc Individual

    datosOtrosCobPagBancColectivo.setTipoDomBancOtroCob(tipoMDLDomain);

    datosOtrosCobPagBancColectivo.setEntidadBancOtroCobro(tipoMDLDomain);
    datosOtrosCobPagBancColectivo.setNumDigContrNumCuent("1");
    datosOtrosCobPagBancColectivo.setNumDigContrEntidOfic("1");
    datosOtrosCobPagBancColectivo.setNumCuentaBanc("1");
    datosOtrosCobPagBancColectivo.setTitulCuentBanc("1");
    datosOtrosCobPagBancColectivo.setCodIban("1");
    datosOtrosCobPagBancColectivo.setCodBic("1");

    //DatoCobro Individual
    List<DatoOtrosCobPagBancDomain> listdatosOtrosCobPagBanc = new ArrayList<>();
    listdatosOtrosCobPagBanc.add(datosOtrosCobPagBancColectivo);

    datoCobroColectivo.setNivelCobro(tipoMDLDomain);

    datoCobroColectivo.setFormaPago(tipoMDLDomain);

    datoCobroColectivo.setMedioCobroPago(tipoMDLDomain);
    datoCobroColectivo.setDatoMedioCobro(datoMedioCobroColectivo);
    datoCobroColectivo.setDatosOtrosCobPagBanc(listdatosOtrosCobPagBanc);

    //DomicilioPers Individual
    domicilioPersColectivo.setIdDomicilio("1");

    domicilioPersColectivo.setPaisDomPers(tipoMDLDomain);

    domicilioPersColectivo.setLocalidadDomPers(tipoMDLDomain);

    domicilioPersColectivo.setProvinciaDomPers(tipoMDLDomain);
    domicilioPersColectivo.setCodMunicipio("1");
    domicilioPersColectivo.setCodEntColectiva("1");
    domicilioPersColectivo.setCodEntSingular("1");
    domicilioPersColectivo.setCodNucPobla("1");

    domicilioPersColectivo.setDenomPoblaDomPers(tipoMDLDomain);
    domicilioPersColectivo.setCodPostal("1");

    domicilioPersColectivo.setTipoViaDomPers(tipoMDLDomain);
    domicilioPersColectivo.setDesDomicilio("1");
    domicilioPersColectivo.setNumDomicilio("1");
    domicilioPersColectivo.setNumComplemento("1");
    domicilioPersColectivo.setNumBloque("1");
    domicilioPersColectivo.setNumPortal("1");
    domicilioPersColectivo.setNumEscalera("1");
    domicilioPersColectivo.setNumPiso("1");
    domicilioPersColectivo.setNumPuerta("1");
    domicilioPersColectivo.setIndNormalizado("1");
    domicilioPersColectivo.setDesOtrosDatos("1");

    //MediosDeContacto Individual

    mediosDeContactosColectivo.setMedioContacto(tipoMDLDomain);

    mediosDeContactosColectivo.setPaisOrigen(tipoMDLDomain);
    mediosDeContactosColectivo.setNumTelefono("1");
    mediosDeContactosColectivo.setNomCorreoElectronico("1");

    //DatosPersonales Individual

    datosPersonalesColectivo.setTipoRol(tipoMDLDomain);
    datosPersonalesColectivo.setNumOrdenRol("1");
    datosPersonalesColectivo.setNumIdPersona("1");
    datosPersonalesColectivo.setNumIdCliente("1");

    datosPersonalesColectivo.setTipoPersona(tipoMDLDomain);

    datosPersonalesColectivo.setTipoDocumento(tipoMDLDomain);
    datosPersonalesColectivo.setNumDocumento("1");

    datosPersonalesColectivo.setSexoPersona(tipoMDLDomain);
    datosPersonalesColectivo.setTxtNombre("1");
    datosPersonalesColectivo.setTxtPrimerApellido("1");
    datosPersonalesColectivo.setTxtSegundoApellido("1");
    datosPersonalesColectivo.setTxtRazonSocial("1");
    datosPersonalesColectivo.setFecNacimiento(instant);

    datosPersonalesColectivo.setProfesion(tipoMDLDomain);

    datosPersonalesColectivo.setAgrupProfesion(tipoMDLDomain);

    datosPersonalesColectivo.setNacionalidad(tipoMDLDomain);
    datosPersonalesColectivo.setIndEstadoCivil("1");
    datosPersonalesColectivo.setDomicilioPers(domicilioPersColectivo);
    List<MedioDeContactoDomain> listaMediosDeContactos = new ArrayList<>();
    listaMediosDeContactos.add(mediosDeContactosColectivo);
    datosPersonalesColectivo.setMediosDeContactos(listaMediosDeContactos);

    presupuestoColectivo.setDatoCobro(datoCobroColectivo);

    //Figura individual
    figuraColectivo.setNumAsegurados("1");
    List<DatoPersonalDomain> listaDatosPersonales = new ArrayList<>();
    listaDatosPersonales.add(datosPersonalesColectivo);
    figuraColectivo.setDatosPersonales(listaDatosPersonales);

    presupuestoColectivo.setFigura(figuraColectivo);

    //EstructuraComercial Individual

    estructuraComercialColectivo.setCanalMediacion(tipoMDLDomain);

    estructuraComercialColectivo.setMedioDistribucion(tipoMDLDomain);

    estructuraComercialColectivo.setAgencia(tipoMDLDomain);

    estructuraComercialColectivo.setZona(tipoMDLDomain);

    estructuraComercialColectivo.setDistrito(tipoMDLDomain);

    estructuraComercialColectivo.setOficina(tipoMDLDomain);
    estructuraComercialColectivo.setCodMediador("1");

    estructuraComercialColectivo.setTipoMediador(tipoMDLDomain);

    estructuraComercialColectivo.setTipoColaborador(tipoMDLDomain);
    estructuraComercialColectivo.setCodColaborador("1");

    estructuraComercialColectivo.setTipoColaboradorIni(tipoMDLDomain);
    estructuraComercialColectivo.setCodColabInicial("1");
    estructuraComercialColectivo.setCodMonitor("1");
    estructuraComercialColectivo.setCodMonitorInicial("1");

    estructuraComercialColectivo.setRedVenta(tipoMDLDomain);
    estructuraComercialColectivo.setIndTipoMedioCom("1");

    //Coordenada Individual

    coordenadaColectivo.setTipoCoordenada(tipoMDLDomain);
    coordenadaColectivo.setNumCoordX(1d);
    coordenadaColectivo.setNumCoordY(1d);
    coordenadaColectivo.setIndSistema("1");

    //DomicilioPresupuesto Individual
    domicilioPresupuestoColectivo.setIdDomicilio("1");

    domicilioPresupuestoColectivo.setPaisPresup(tipoMDLDomain);

    domicilioPresupuestoColectivo.setLocalidadPresup(tipoMDLDomain);

    domicilioPresupuestoColectivo.setProvinciaPresup(tipoMDLDomain);
    domicilioPresupuestoColectivo.setCodMunicipio("1");
    domicilioPresupuestoColectivo.setCodEntColectiva("1");
    domicilioPresupuestoColectivo.setCodEntSingular("1");
    domicilioPresupuestoColectivo.setCodNucPobla("1");

    domicilioPresupuestoColectivo.setDenomPoblaPresup(tipoMDLDomain);
    domicilioPresupuestoColectivo.setCodPostal("1");

    domicilioPresupuestoColectivo.setTipoViaPresup(tipoMDLDomain);
    domicilioPresupuestoColectivo.setDesDomicilio("1");
    domicilioPresupuestoColectivo.setNumNumero("1");
    domicilioPresupuestoColectivo.setNumComplemento("1");
    domicilioPresupuestoColectivo.setNumBloque("1");
    domicilioPresupuestoColectivo.setNumPortal("1");
    domicilioPresupuestoColectivo.setNumEscalera("1");
    domicilioPresupuestoColectivo.setNumPiso("1");
    domicilioPresupuestoColectivo.setNumPuerta("1");
    domicilioPresupuestoColectivo.setIndNormalizado("1");
    domicilioPresupuestoColectivo.setDesOtrosDatos("1");

    presupuestoColectivo.setEstructuraComercial(estructuraComercialColectivo);

    //EstructuraGeografica Individual
    estructuraGeograficaColectivo.setCoordenada(coordenadaColectivo);
    estructuraGeograficaColectivo.setDomicilioPresupuesto(domicilioPresupuestoColectivo);

    presupuestoColectivo.setEstructuraGeografica(estructuraGeograficaColectivo);

    //Campanna Individual

    campannasColectivo.setCampannaComercial(tipoMDLDomain);
    campannasColectivo.setIdCampanna("1");

    campannasColectivo.setIncentivoCampanna(tipoMDLDomain);

    campannasColectivo.setTipoCampanna(tipoMDLDomain);
    campannasColectivo.setFecInicio(instant);
    campannasColectivo.setFecFinVigencia(instant);
    campannasColectivo.setIndCaracIncentivo("1");
    List<CampannaDomain> campannasList = new ArrayList<>();
    campannasList.add(campannasColectivo);
    presupuestoColectivo.setCampannas(campannasList);

    //ObjetosAsegurados Individual
    objetosAseguradosColectivo.setIdObjAsegODL("1");
    List<ObjetosAseguradosDomain> objetosAseguradosList = new ArrayList<>();
    objetosAseguradosList.add(objetosAseguradosColectivo);

    presupuestoColectivo.setObjetosAsegurados(objetosAseguradosList);
    AuditoriaDomain auditoriaDomain = new AuditoriaDomain();
    auditoriaDomain.setCreatedByUser("1");
    auditoriaDomain.setCreatedDate(instant);
    auditoriaDomain.setLastModifiedByUser("1");
    auditoriaDomain.setLastModifiedDate(instant);

    //Metadata Individual
    metadataColectivo.setTxtCircuitoOrigen("1");
    metadataColectivo.setFecSQL(instant);
    metadataColectivo.setHash("1");
    metadataColectivo.setCodVersion("1");
    metadataColectivo.setNumOffsetMDL(1L);
    metadataColectivo.setNumOffsetCarga(1L);
    metadataColectivo.setAuditoria(auditoriaDomain);
    presupuestoColectivo.setMetadata(metadataColectivo);

    HistPresupuestoColectivoDomainMapper mapper = new HistPresupuestoColectivoDomainMapperImpl();
    PresupuestoColectivoRequestBodyResource result = mapper.toResource(presupuestoColectivo);
    assertNotNull(result);
  }
  @DisplayName("histPresupColToResources")
  @Test
  void toDomainsfromResources() {
    OffsetDateTime offsetDateTime = OffsetDateTime.now();
    EntityModelPresupuestoColectivoResource presupuestoColectivo = new EntityModelPresupuestoColectivoResource();
    DatoIdentificativoResource datoIdentificativoColectivo = new DatoIdentificativoResource();
    presupuestoColectivo.setId("1");

    ProductoResource productoColectivo = new ProductoResource();
    FechaYEstadoResource fechaYEstadoColectivo = new FechaYEstadoResource();
    FechaResource fechaColectivo = new FechaResource();
    EstadoResource estadoColectivo = new EstadoResource();
    HistoricoResource historicoColectivo = new HistoricoResource();
    DatoMedioCobroResource datoMedioCobroColectivo = new DatoMedioCobroResource();
    DatoCobroResource datoCobroColectivo = new DatoCobroResource();
    DatosOtrosCobPagBancResource datosOtrosCobPagBancColectivo = new DatosOtrosCobPagBancResource();
    DatoCobroBancarioResource datoCobroBancarioColectivo = new DatoCobroBancarioResource();
    DatoCobroFisicoResource datoCobroFisicoColectivo = new DatoCobroFisicoResource();
    DatoCobroInternoResource datoCobroInternoColectivo = new DatoCobroInternoResource();
    List<DatosCobroOtrosMedioResource> datosCobroOtrosMedios = new ArrayList<>();
    DomicilioPersResource domicilioPersColectivo = new DomicilioPersResource();
    MediosDeContactoResource mediosDeContactosColectivo = new MediosDeContactoResource();
    DatosPersonalesResource datosPersonalesColectivo = new DatosPersonalesResource();
    FiguraResource figuraColectivo = new FiguraResource();
    EstructuraComercialResource estructuraComercialColectivo = new EstructuraComercialResource();
    EstructuraGeograficaResource estructuraGeograficaColectivo = new EstructuraGeograficaResource();
    DomicilioPresupuestoResource domicilioPresupuestoColectivo = new DomicilioPresupuestoResource();
    CoordenadaResource coordenadaColectivo = new CoordenadaResource();
    CampannaResource campannasColectivo = new CampannaResource();
    ObjetosAseguradosResource objetosAseguradosColectivo = new ObjetosAseguradosResource();
    MetadataResource metadataColectivo = new MetadataResource();


    //Datos identificadivos de individual
    datoIdentificativoColectivo.setIdPresupuestoODL("1");
    TipoPresupuestoResource tipoPresupuestoResource = new TipoPresupuestoResource("1", "1", "1");
    datoIdentificativoColectivo.setTipoPresupuesto(tipoPresupuestoResource);
    CompanniaRespServResource companniaRespServResource = new CompanniaRespServResource("1", "1", "1");
    datoIdentificativoColectivo.setCompanniaRespServ(companniaRespServResource);
    SistemaOrigenResource sistemaOrigenResource = new SistemaOrigenResource("1", "1", "1");
    datoIdentificativoColectivo.setSistemaOrigen(sistemaOrigenResource);
    SistemaActualResource sistemaActualResource = new SistemaActualResource("1", "1", "1");
    datoIdentificativoColectivo.setSistemaActual(sistemaActualResource);
    datoIdentificativoColectivo.setNumIdentificacion("1");
    datoIdentificativoColectivo.setCodIdentificacion("1");
    datoIdentificativoColectivo.setNumIdAgrupacion("1");
    datoIdentificativoColectivo.setCodIdAgrupacion("1");
    datoIdentificativoColectivo.setVersPresupuesto(1D);
    datoIdentificativoColectivo.setVersPresupuestoODL("1");
    datoIdentificativoColectivo.setIndVersSeleccionada("1");
    datoIdentificativoColectivo.setIdPolizaOrigen("1");
    datoIdentificativoColectivo.setIdMensaje("1");
    datoIdentificativoColectivo.setCodPackComercial("1");
    datoIdentificativoColectivo.setNumPoliza("1");
    datoIdentificativoColectivo.setNumPackPoliza("1");

    TipoComisionResource tipoComisionResource = new TipoComisionResource("1", "1", "1");
    datoIdentificativoColectivo.setTipoComision(tipoComisionResource);
    datoIdentificativoColectivo.setCodRenovacion("1");
    datoIdentificativoColectivo.setIndCompanniaVendedora("1");
    datoIdentificativoColectivo.setCodOportSalesforce("1");
    datoIdentificativoColectivo.setCodVersionComercial("1");
    PeriodPolizaResource periodPolizaResource = new PeriodPolizaResource("1", "1", "1");
    datoIdentificativoColectivo.setPeriodPoliza(periodPolizaResource);
    datoIdentificativoColectivo.setIndOrigRecomendador("1");
    datoIdentificativoColectivo.setCodEvento("1");

    presupuestoColectivo.setDatoIdentificativo(datoIdentificativoColectivo);
    //Producto individual


    RamoComercialResource ramoComercialResource = new RamoComercialResource("1", "1", "1");
    productoColectivo.setRamoComercial(ramoComercialResource);
    ModalidadComercialResource modalidadComercialResource = new ModalidadComercialResource();
    productoColectivo.setModalidadComercial(modalidadComercialResource);
    ProductoTecnicoResource productoTecnicoResource = new ProductoTecnicoResource("1", "1", "1");
    productoColectivo.setProductoTecnico(productoTecnicoResource);
    ProductoComercialResource productoComercialResource = new ProductoComercialResource("1", "1", "1");
    productoColectivo.setProductoComercial(productoComercialResource);
    productoColectivo.setDescModalidadInterna("1");

    presupuestoColectivo.setProducto(productoColectivo);

    //Historico individual
    EstadoPresupuestoResource estadoPresupuestoResource = new EstadoPresupuestoResource("1", "1", "1");
    historicoColectivo.setEstadoPresupuesto(estadoPresupuestoResource);
    historicoColectivo.setFecEstado(offsetDateTime);
    CausaEstPresupuestoResource causaEstPresupuestoResource = new CausaEstPresupuestoResource("1", "1", "1");
    historicoColectivo.setCausaEstPresupuesto(causaEstPresupuestoResource);
    historicoColectivo.setNumOrdenMovEstado("1");
    historicoColectivo.setIndBloqueoRevision("1");


    //Estado individual
    List<HistoricoResource> listaHistoricosIndividual = new ArrayList<>();
    listaHistoricosIndividual.add(historicoColectivo);
    estadoColectivo.setHistoricos(listaHistoricosIndividual);


    //Fecha individual
    fechaColectivo.setFecAlta(offsetDateTime);
    fechaColectivo.setFecEfectoPoliza(offsetDateTime);
    fechaColectivo.setFecFinValidez(offsetDateTime);
    fechaColectivo.setFecTarificacion(offsetDateTime);
    fechaColectivo.setFecVencPoliza(offsetDateTime);

    //FechaYEstado individual
    fechaYEstadoColectivo.setEstado(estadoColectivo);
    fechaYEstadoColectivo.setFecha(fechaColectivo);

    presupuestoColectivo.setFechaYEstado(fechaYEstadoColectivo);

    //DatoCobroBancario Individual
    datoCobroBancarioColectivo.setCodBic("1");
    datoCobroBancarioColectivo.setCodIban("1");
    TipoDomBancarioResource tipoDomBancarioResource = new TipoDomBancarioResource("1", "1", "1");
    datoCobroBancarioColectivo.setTipoDomBancario(tipoDomBancarioResource);
    EntidadBancariaResource entidadBancariaResource = new EntidadBancariaResource("1", "1", "1");
    datoCobroBancarioColectivo.setEntidadBancaria(entidadBancariaResource);
    datoCobroBancarioColectivo.setNumCuentaBanc(1D);
    datoCobroBancarioColectivo.setNumDigContrEntidOfic("1");
    datoCobroBancarioColectivo.setNumDigContrNumCuent("1");
    datoCobroBancarioColectivo.setTitulCuentBanc("1");


    //DatoCobroFisico Individual
    TipoDomCobroResource tipoDomCobroResource = new TipoDomCobroResource("1", "1", "1");
    datoCobroFisicoColectivo.setTipoDomCobro(tipoDomCobroResource);
    PaisResource paisResource = new PaisResource("1", "1", "1");
    datoCobroFisicoColectivo.setPais(paisResource);
    ProvinciaResource provinciaResource = new ProvinciaResource("1", "1", "1");
    datoCobroFisicoColectivo.setProvincia(provinciaResource);
    LocalidadResource localidadResource = new LocalidadResource("1", "1", "1");
    datoCobroFisicoColectivo.setLocalidad(localidadResource);
    datoCobroFisicoColectivo.setCodMunicipio("1");
    datoCobroFisicoColectivo.setCodPostal("1");
    datoCobroFisicoColectivo.setCodEntColectiva("1");
    datoCobroFisicoColectivo.setConEntSingular("1");
    datoCobroFisicoColectivo.setCodNucPobla("1");
    DenomPoblaPersResource denomPoblaPersResource = new DenomPoblaPersResource("1", "1", "1");
    datoCobroFisicoColectivo.setDenomPoblaPers(denomPoblaPersResource);
    TipoViaResource tipoViaResource = new TipoViaResource("1", "1", "1");
    datoCobroFisicoColectivo.setTipoVia(tipoViaResource);
    datoCobroFisicoColectivo.setDescDomicilio("1");
    datoCobroFisicoColectivo.setNumDomicilio("1");
    datoCobroFisicoColectivo.setCompNumDomic("1");
    datoCobroFisicoColectivo.setNumBloqueDomic("1");
    datoCobroFisicoColectivo.setNumPortalDomic("1");
    datoCobroFisicoColectivo.setNumEscalDomic("1");
    datoCobroFisicoColectivo.setNumPisoDomic("1");
    datoCobroFisicoColectivo.setNumPuertaDomic("1");
    datoCobroFisicoColectivo.setOtrosDatosDomic("1");

    //DatoCobroInterno Individual
    CobPagInterCompResource cobPagInterCompResource = new CobPagInterCompResource("1", "1", "1");
    datoCobroInternoColectivo.setCobPagInterComp(cobPagInterCompResource);
//DatosCobroOtrosMedios
    DatosCobroOtrosMedioResource datosCobroOtrosMediosDomain = new DatosCobroOtrosMedioResource("1", "1", "1");
    datosCobroOtrosMedios.add(datosCobroOtrosMediosDomain);
    //DatoMedioCobro Individual
    datoMedioCobroColectivo.setDatoCobroBancario(datoCobroBancarioColectivo);
    datoMedioCobroColectivo.setDatoCobroFisico(datoCobroFisicoColectivo);
    datoMedioCobroColectivo.setDatoCobroInterno(datoCobroInternoColectivo);
    datoMedioCobroColectivo.setDatosCobroOtrosMedios(datosCobroOtrosMedios);

    //DatosOtrosCobPagBanc Individual
    TipoDomBancOtroCobResource tipoDomBancOtroCobResource = new TipoDomBancOtroCobResource("1", "1", "1");
    datosOtrosCobPagBancColectivo.setTipoDomBancOtroCob(tipoDomBancOtroCobResource);
    EntidadBancOtroCobroResource entidadBancOtroCobroResource = new EntidadBancOtroCobroResource("1", "1", "1");
    datosOtrosCobPagBancColectivo.setEntidadBancOtroCobro(entidadBancOtroCobroResource);
    datosOtrosCobPagBancColectivo.setNumDigContrNumCuent("1");
    datosOtrosCobPagBancColectivo.setNumDigContrEntidOfic("1");
    datosOtrosCobPagBancColectivo.setNumCuentaBanc(1D);
    datosOtrosCobPagBancColectivo.setTitulCuentBanc("1");
    datosOtrosCobPagBancColectivo.setCodIban("1");
    datosOtrosCobPagBancColectivo.setCodBic("1");

    //DatoCobro Individual
    List<DatosOtrosCobPagBancResource> listdatosOtrosCobPagBanc = new ArrayList<>();
    listdatosOtrosCobPagBanc.add(datosOtrosCobPagBancColectivo);
    NivelCobroResource nivelCobroResource = new NivelCobroResource("1", "1", "1");
    datoCobroColectivo.setNivelCobro(nivelCobroResource);
    FormaPagoResource formaPagoResource = new FormaPagoResource("1", "1", "1");
    datoCobroColectivo.setFormaPago(formaPagoResource);
    MedioCobroPagoResource medioCobroPagoResource = new MedioCobroPagoResource("1", "1", "1");
    datoCobroColectivo.setMedioCobroPago(medioCobroPagoResource);
    datoCobroColectivo.setDatoMedioCobro(datoMedioCobroColectivo);
    datoCobroColectivo.setDatosOtrosCobPagBanc(listdatosOtrosCobPagBanc);

    //DomicilioPers Individual
    domicilioPersColectivo.setIdDomicilio("1");
    PaisDomPersResource paisDomPersResource = new PaisDomPersResource("1", "1", "1");
    domicilioPersColectivo.setPaisDomPers(paisDomPersResource);
    LocalidadDomPersResource localidadDomPersResource = new LocalidadDomPersResource("1", "1", "1");
    domicilioPersColectivo.setLocalidadDomPers(localidadDomPersResource);
    ProvinciaDomPersResource provinciaDomPersResource = new ProvinciaDomPersResource("1", "1", "1");
    domicilioPersColectivo.setProvinciaDomPers(provinciaDomPersResource);
    domicilioPersColectivo.setCodMunicipio("1");
    domicilioPersColectivo.setCodEntColectiva("1");
    domicilioPersColectivo.setCodEntSingular("1");
    domicilioPersColectivo.setCodNucPobla("1");
    DenomPoblaDomPersResource denomPoblaDomPersResource = new DenomPoblaDomPersResource("1", "1", "1");
    domicilioPersColectivo.setDenomPoblaDomPers(denomPoblaDomPersResource);
    domicilioPersColectivo.setCodPostal("1");
    TipoViaDomPersResource tipoViaDomPersResource = new TipoViaDomPersResource("1", "1", "1");
    domicilioPersColectivo.setTipoViaDomPers(tipoViaDomPersResource);
    domicilioPersColectivo.setDescDomicilio("1");
    domicilioPersColectivo.setNumDomicilio("1");
    domicilioPersColectivo.setNumComplemento("1");
    domicilioPersColectivo.setNumBloque("1");
    domicilioPersColectivo.setNumPortal("1");
    domicilioPersColectivo.setNumEscalera("1");
    domicilioPersColectivo.setNumPiso("1");
    domicilioPersColectivo.setNumPuerta("1");
    domicilioPersColectivo.setIndNormalizado("1");
    domicilioPersColectivo.setDesOtrosDatos("1");

    //MediosDeContacto Individual
    MedioContactoResource medioContactoResource = new MedioContactoResource("1", "1", "1");
    mediosDeContactosColectivo.setMedioContacto(medioContactoResource);
    PaisOrigenResource paisOrigenResource = new PaisOrigenResource("1", "1", "1");
    mediosDeContactosColectivo.setPaisOrigen(paisOrigenResource);
    mediosDeContactosColectivo.setNumTelefono("1");
    mediosDeContactosColectivo.setNomCorreoElectronico("1");

    //DatosPersonales Individual
    TipoRolResource tipoRolResource = new TipoRolResource("1", "1", "1");
    datosPersonalesColectivo.setTipoRol(tipoRolResource);
    datosPersonalesColectivo.setNumOrdenRol("1");
    datosPersonalesColectivo.setNumIdPersona("1");
    datosPersonalesColectivo.setNumIdCliente("1");
    TipoPersonaResource tipoPersonaResource = new TipoPersonaResource("1", "1", "1");
    datosPersonalesColectivo.setTipoPersona(tipoPersonaResource);
    TipoDocumentoResource tipoDocumentoResource = new TipoDocumentoResource("1", "1", "1");
    datosPersonalesColectivo.setTipoDocumento(tipoDocumentoResource);
    datosPersonalesColectivo.setNumDocumento("1");
    SexoPersonaResource sexoPersonaResource = new SexoPersonaResource("1", "1", "1");
    datosPersonalesColectivo.setSexoPersona(sexoPersonaResource);
    datosPersonalesColectivo.setTxtNombre("1");
    datosPersonalesColectivo.setTxtPrimerApellido("1");
    datosPersonalesColectivo.setTxtSegundoApellido("1");
    datosPersonalesColectivo.setTxtRazonSocial("1");
    datosPersonalesColectivo.setFecNacimiento(offsetDateTime);
    ProfesionResource profesionResource = new ProfesionResource("1", "1", "1");
    datosPersonalesColectivo.setProfesion(profesionResource);
    AgrupProfesionResource agrupProfesionResource = new AgrupProfesionResource("1", "1", "1");
    datosPersonalesColectivo.setAgrupProfesion(agrupProfesionResource);
    NacionalidadResource nacionalidadResource = new NacionalidadResource("1", "1", "1");
    datosPersonalesColectivo.setNacionalidad(nacionalidadResource);
    datosPersonalesColectivo.setIndEstadoCivil("1");
    datosPersonalesColectivo.setDomicilioPers(domicilioPersColectivo);
    List<MediosDeContactoResource> listaMediosDeContactos = new ArrayList<>();
    listaMediosDeContactos.add(mediosDeContactosColectivo);
    datosPersonalesColectivo.setMediosDeContactos(listaMediosDeContactos);

    presupuestoColectivo.setDatoCobro(datoCobroColectivo);

    //Figura individual
    figuraColectivo.setNumAsegurados("1");
    List<DatosPersonalesResource> listaDatosPersonales = new ArrayList<>();
    listaDatosPersonales.add(datosPersonalesColectivo);
    figuraColectivo.setDatosPersonales(listaDatosPersonales);

    presupuestoColectivo.setFigura(figuraColectivo);

    //EstructuraComercial Individual
    CanalMediacionResource canalMediacionResource = new CanalMediacionResource("1", "1", "1");
    estructuraComercialColectivo.setCanalMediacion(canalMediacionResource);
    MedioDistribucionResource medioDistribucionResource = new MedioDistribucionResource("1", "1", "1");
    estructuraComercialColectivo.setMedioDistribucion(medioDistribucionResource);
    AgenciaResource agenciaResource = new AgenciaResource("1", "1", "1");
    estructuraComercialColectivo.setAgencia(agenciaResource);
    ZonaResource zonaResource = new ZonaResource("1", "1", "1");
    estructuraComercialColectivo.setZona(zonaResource);
    DistritoResource distritoResource = new DistritoResource("1", "1", "1");
    estructuraComercialColectivo.setDistrito(distritoResource);
    OficinaResource oficinaResource = new OficinaResource("1", "1", "1");
    estructuraComercialColectivo.setOficina(oficinaResource);
    estructuraComercialColectivo.setCodMediador("1");
    TipoMediadorResource tipoMediadorResource = new TipoMediadorResource("1", "1", "1");
    estructuraComercialColectivo.setTipoMediador(tipoMediadorResource);
    TipoColaboradorResource tipoColaboradorResource = new TipoColaboradorResource("1", "1", "1");
    estructuraComercialColectivo.setTipoColaborador(tipoColaboradorResource);
    estructuraComercialColectivo.setCodColaborador("1");
    TipoColaboradorIniResource tipoColaboradorIniResource = new TipoColaboradorIniResource("1", "1", "1");
    estructuraComercialColectivo.setTipoColaboradorIni(tipoColaboradorIniResource);
    estructuraComercialColectivo.setCodColabInicial("1");
    estructuraComercialColectivo.setCodMonitor("1");
    estructuraComercialColectivo.setCodMonitorInicial("1");
    RedVentaResource redVentaResource = new RedVentaResource("1", "1", "1");
    estructuraComercialColectivo.setRedVenta(redVentaResource);
    estructuraComercialColectivo.setIndTipoMedioCom("1");

    //Coordenada Individual
    TipoCoordenadaResource tipoCoordenadaResource = new TipoCoordenadaResource("1", "1", "1");
    coordenadaColectivo.setTipoCoordenada(tipoCoordenadaResource);
    coordenadaColectivo.setNumCoordX(1d);
    coordenadaColectivo.setNumCoordY(1d);
    coordenadaColectivo.setIndSistema("1");

    //DomicilioPresupuesto Individual
    domicilioPresupuestoColectivo.setIdDomicilio("1");
    PaisPresupResource paisPresupResource = new PaisPresupResource("1", "1", "1");
    domicilioPresupuestoColectivo.setPaisPresup(paisPresupResource);
    LocalidadPresupResource localidadPresupResource = new LocalidadPresupResource("1", "1", "1");
    domicilioPresupuestoColectivo.setLocalidadPresup(localidadPresupResource);
    ProvinciaPresupResource provinciaPresupResource = new ProvinciaPresupResource("1", "1", "1");
    domicilioPresupuestoColectivo.setProvinciaPresup(provinciaPresupResource);
    domicilioPresupuestoColectivo.setCodMunicipio("1");
    domicilioPresupuestoColectivo.setCodEntColectiva("1");
    domicilioPresupuestoColectivo.setCodEntSingular("1");
    domicilioPresupuestoColectivo.setCodNucPobla("1");
    DenomPoblaPresupResource denomPoblaPresupResource = new DenomPoblaPresupResource("1", "1", "1");
    domicilioPresupuestoColectivo.setDenomPoblaPresup(denomPoblaPresupResource);
    domicilioPresupuestoColectivo.setCodPostal("1");
    TipoViaPresupResource tipoViaPresupResource = new TipoViaPresupResource("1", "1", "1");
    domicilioPresupuestoColectivo.setTipoViaPresup(tipoViaPresupResource);
    domicilioPresupuestoColectivo.setDescDomicilio("1");
    domicilioPresupuestoColectivo.setNumNumero("1");
    domicilioPresupuestoColectivo.setNumComplemento("1");
    domicilioPresupuestoColectivo.setNumBloque("1");
    domicilioPresupuestoColectivo.setNumPortal("1");
    domicilioPresupuestoColectivo.setNumEscalera("1");
    domicilioPresupuestoColectivo.setNumPiso("1");
    domicilioPresupuestoColectivo.setNumPuerta("1");
    domicilioPresupuestoColectivo.setIndNormalizado("1");
    domicilioPresupuestoColectivo.setDesOtrosDatos("1");

    presupuestoColectivo.setEstructuraComercial(estructuraComercialColectivo);

    //EstructuraGeografica Individual
    estructuraGeograficaColectivo.setCoordenada(coordenadaColectivo);
    estructuraGeograficaColectivo.setDomicilioPresupuesto(domicilioPresupuestoColectivo);

    presupuestoColectivo.setEstructuraGeografica(estructuraGeograficaColectivo);

    //Campanna Individual
    CampannaComercialResource campannaComercialResource = new CampannaComercialResource("1", "1", "1");
    campannasColectivo.setCampannaComercial(campannaComercialResource);
    campannasColectivo.setIdCampanna("1");
    IncentivoCampannaResource incentivoCampannaResource = new IncentivoCampannaResource("1", "1", "1");
    campannasColectivo.setIncentivoCampanna(incentivoCampannaResource);
    TipoCampannaResource tipoCampannaResource = new TipoCampannaResource("1", "1", "1");
    campannasColectivo.setTipoCampanna(tipoCampannaResource);
    campannasColectivo.setFecInicio(offsetDateTime);
    campannasColectivo.setFecFinVigencia(offsetDateTime);
    campannasColectivo.setIndCaracIncentivo("1");
    List<CampannaResource> campannasList = new ArrayList<>();
    campannasList.add(campannasColectivo);
    presupuestoColectivo.setCampannas(campannasList);

    //ObjetosAsegurados Individual
    objetosAseguradosColectivo.setIdObjAsegODL("1");
    List<ObjetosAseguradosResource> objetosAseguradosList = new ArrayList<>();
    objetosAseguradosList.add(objetosAseguradosColectivo);

    presupuestoColectivo.setObjetosAsegurados(objetosAseguradosList);
    /*Auditoria auditoriaDomain = new AuditoriaDomain();
    auditoriaDomain.setCreatedByUser("1");
    auditoriaDomain.setCreatedDate(offsetDateTime);
    auditoriaDomain.setLastModifiedByUser("1");
    auditoriaDomain.setLastModifiedDate(offsetDateTime);*/

    //Metadata Individual
    metadataColectivo.setTxtCircuitoOrigen("1");
    metadataColectivo.setFecSQL(offsetDateTime);
    /*metadataIndividual.setHash("1");*/
    metadataColectivo.setCodVersion("1");
    metadataColectivo.setNumOffsetMDL(1L);
    metadataColectivo.setNumOffsetCarga(1L);
    /*metadataIndividual.setAuditoria(auditoriaDomain);*/
    presupuestoColectivo.setMetadata(metadataColectivo);

    List<EntityModelPresupuestoColectivoResource> list = new ArrayList<>();
    list.add(presupuestoColectivo);
    HistPresupuestoColectivoDomainMapper mapper = new HistPresupuestoColectivoDomainMapperImpl();
    List<PresupuestoColectivoDomain> result = mapper.toDomainsfromResources(list);
    assertThat(result).isNotNull();

  }
}
