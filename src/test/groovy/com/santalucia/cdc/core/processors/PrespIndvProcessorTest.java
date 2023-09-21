package com.santalucia.cdc.core.processors;

import com.santalucia.cdc.core.domain.EventoPresupuestoIndvDomain;
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
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.declaration.com.resp.RespuestaDomain;
import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import com.santalucia.cdc.core.domain.metadata.AuditoriaDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.AnimalDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.CaracteristicaDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.DispositivoElectrDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.DomicilioDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.CapitalDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.ComposicionGarantiaDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.DatoIdentifTarifDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.PrimaDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.UnidadTarificacionDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.com.ComposicionCaractDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.com.ComposicionServicioDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.com.DatosPropiosDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.com.PrevisionDomain;
import com.santalucia.cdc.core.service.policies.PolizaIndividualClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PrespIndvProcessorTest {

  @InjectMocks
  private PrespIndvProcessor prespIndvProcessor;

  @Mock
  private PolizaIndividualClientService polizaService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testProcessWithPolizaColectiva() {
    // Arrange
    EventoPresupuestoIndvDomain inputEvent = new EventoPresupuestoIndvDomain();
    PresupuestoIndividualDomain budget = new PresupuestoIndividualDomain();
    DatoIdentificativoDomain datoIdentificativo = new DatoIdentificativoDomain();
    datoIdentificativo.setNumIdentificacion("123");
    budget.setDatoIdentificativo(datoIdentificativo);
    inputEvent.setPresupuestoIndividual(budget);

    when(polizaService.getPolizaIndividual("123")).thenReturn(true);

    // Act
    EventoPresupuestoIndvDomain result = prespIndvProcessor.process(inputEvent);

    // Assert
    verify(polizaService, times(1)).getPolizaIndividual("123");
    verify(polizaService, never()).getHistoricoIndividual("123");
  }

  @Test
  public void testAnonimizate() {
    TipoMDLDomain tipoMDLDomain = new TipoMDLDomain();
    tipoMDLDomain.setCodOrigen("1");
    tipoMDLDomain.setDescOrigen("1");
    tipoMDLDomain.setCodMDL("1");


    EventoPresupuestoIndvDomain eventoPresupuestoIndvDomain = new EventoPresupuestoIndvDomain();


    PrespIndvProcessor processor = new PrespIndvProcessor();

    Instant instant = Instant.now();
    PresupuestoIndividualDomain presupuestoIndividual = new PresupuestoIndividualDomain();
    DatoIdentificativoDomain datoIdentificativoIndividual = new DatoIdentificativoDomain();

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
    datoCobroIndividual.setDatoMedioCobro(datoMedioCobroIndividual);

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
    com.santalucia.cdc.core.domain.budgets.individualbudget.amount.level.SobreprimaDomain sobreprimas = new com.santalucia.cdc.core.domain.budgets.individualbudget.amount.level.SobreprimaDomain();
    sobreprimas.setImpSobreprima(1D);
    List<SobreprimaDomain> sobreprimasList = new ArrayList<>();
    sobreprimasList.add(sobreprimas);
    nivelesPresupuestos.setSobreprimas(sobreprimasList);
    com.santalucia.cdc.core.domain.budgets.individualbudget.amount.level.BonificacionDescDomain bonificacionesDesc = new com.santalucia.cdc.core.domain.budgets.individualbudget.amount.level.BonificacionDescDomain();
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

    eventoPresupuestoIndvDomain.setPresupuestoIndividual(presupuestoIndividual);


    com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain objAseg = new com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain();
    //DATO IDENTIFICATIVO
    com.santalucia.cdc.core.domain.securedobjects.identif.DatoIdentificativoDomain datoIdentificativoDomain = new com.santalucia.cdc.core.domain.securedobjects.identif.DatoIdentificativoDomain();
    datoIdentificativoDomain.setIdMensaje("1");
    datoIdentificativoDomain.setIdPresupuestoODL("1");
    datoIdentificativoDomain.setNumOrden("1");
    datoIdentificativoDomain.setNumVersionPresOrigen("1");
    datoIdentificativoDomain.setNumVersionPresODL("1");
    datoIdentificativoDomain.setDesLocalidadTarif("1");
    datoIdentificativoDomain.setDesProvinciaTarif("1");
    datoIdentificativoDomain.setCodTipoServicio("1");
    datoIdentificativoDomain.setTipoObjAseg(tipoMDLDomain);
    datoIdentificativoDomain.setIndCategoria("1");
    datoIdentificativoDomain.setCodModServicio("1");
    datoIdentificativoDomain.setCodSubtipoServicio("1");
    objAseg.setDatoIdentificativo(datoIdentificativoDomain);
    //CARACTERISTICA
    CaracteristicaDomain caracteristicaDomain = new CaracteristicaDomain();

    List<AnimalDomain> animalResourceList = new ArrayList<>();
    AnimalDomain animalResource = new AnimalDomain();
    animalResource.setFecNacimiento(instant);
    animalResource.setIndTipoEspecie("1");
    animalResource.setRaza(tipoMDLDomain);
    animalResource.setIndTipoAnimalComp("1");
    animalResource.setNumIdentAnimalComp("1");
    animalResource.setNomMascota("1");
    animalResource.setNumChip("1");
    animalResource.setImpValorMascota(1.0);
    animalResource.setIndPerroMestizo("1");
    animalResource.setIndPerfEstadoSalud("1");
    animalResourceList.add(animalResource);
    caracteristicaDomain.setAnimales(animalResourceList);

    List<DomicilioDomain> domicilioResources = new ArrayList<>();
    DomicilioDomain domicilioDomain = new DomicilioDomain();
    domicilioDomain.setIdDomicilio("1");
    domicilioDomain.setPais(tipoMDLDomain);
    domicilioDomain.setCodMunicipio("1");
    domicilioDomain.setProvincia(tipoMDLDomain);
    domicilioDomain.setLocalidad(tipoMDLDomain);
    domicilioDomain.setCodMunicipio("1");
    domicilioDomain.setCodEntColectiva("1");
    domicilioDomain.setCodNucPobla("1");
    domicilioDomain.setDenomPobla(tipoMDLDomain);
    domicilioDomain.setCodPostal("1");
    domicilioDomain.setTipoVia(tipoMDLDomain);
    domicilioDomain.setDesDomicilio("1");
    domicilioDomain.setNumNumero("1");
    domicilioDomain.setNumComplemento("1");
    domicilioDomain.setNumBloque("1");
    domicilioDomain.setNumPortal("1");
    domicilioDomain.setNumEscalera("1");
    domicilioDomain.setNumPiso("1");
    domicilioDomain.setNumPuerta("1");
    domicilioDomain.setIndNormalizado("1");
    domicilioDomain.setDesOtrosDatos("1");
    domicilioResources.add(domicilioDomain);
    caracteristicaDomain.setDomicilios(domicilioResources);

    List<com.santalucia.cdc.core.domain.securedobjects.characteristics.FiguraDomain> figuraResourcesList = new ArrayList<>();
    com.santalucia.cdc.core.domain.securedobjects.characteristics.FiguraDomain figuraDomain = new com.santalucia.cdc.core.domain.securedobjects.characteristics.FiguraDomain();
    figuraDomain.setIdPersona("1");
    figuraDomain.setTipoPersona(tipoMDLDomain);
    figuraDomain.setTxtNombre("1");
    figuraDomain.setTxtPrimerApellido("1");
    figuraDomain.setTxtSegundoApellido("1");
    figuraDomain.setTxtRazonSocial("1");
    figuraDomain.setTipoDocumento(tipoMDLDomain);
    figuraDomain.setNumDocumento("1");
    figuraDomain.setFecNacimiento(instant);
    figuraDomain.setSexo(tipoMDLDomain);
    figuraDomain.setNacionalidad(tipoMDLDomain);
    figuraDomain.setProfesion(tipoMDLDomain);
    figuraDomain.setGrupoProfesion(tipoMDLDomain);
    figuraDomain.setIndEstadoCivil("1");
    figuraDomain.setBeneficiario(tipoMDLDomain);

    figuraResourcesList.add(figuraDomain);
    caracteristicaDomain.setFiguras(figuraResourcesList);


    List<DispositivoElectrDomain> dispositivoElectrResourceList = new ArrayList<>();
    DispositivoElectrDomain dispositivoElectrDomain = new DispositivoElectrDomain();
    dispositivoElectrDomain.setCodIdentificador("1");
    dispositivoElectrDomain.setImpCoste(1.0);
    dispositivoElectrDomain.setMarcaDispElectr(tipoMDLDomain);
    dispositivoElectrDomain.setModeloDispElectr(tipoMDLDomain);
    dispositivoElectrResourceList.add(dispositivoElectrDomain);
    caracteristicaDomain.setDispositivosElectr(dispositivoElectrResourceList);
    objAseg.setCaracteristica(caracteristicaDomain);

    //UNIDADES TARIF
    List<UnidadTarificacionDomain> unidadTarificacionResources = new ArrayList<>();
    UnidadTarificacionDomain unidadTarificacionDomain = new UnidadTarificacionDomain();

    unidadTarificacionDomain.setNumIdNivTarificacion("1");
    unidadTarificacionDomain.setTipoPrima(tipoMDLDomain);
    unidadTarificacionDomain.setMoneda(tipoMDLDomain);
    unidadTarificacionDomain.setNivelTarificacion(tipoMDLDomain);

    DatoIdentifTarifDomain datoIdentifTarifDomain = new DatoIdentifTarifDomain();
    datoIdentifTarifDomain.setAgrupGarantia(tipoMDLDomain);
    datoIdentifTarifDomain.setTipoComplemento(tipoMDLDomain);
    datoIdentifTarifDomain.setNumOrdenComplemento("1");
    datoIdentifTarifDomain.setCategoria(tipoMDLDomain);
    datoIdentifTarifDomain.setNumOrdenAgrupGar("1");
    datoIdentifTarifDomain.setAgrupGarantiaHog("1");
    datoIdentifTarifDomain.setGarantiaUnitaria(tipoMDLDomain);
    datoIdentifTarifDomain.setNumOrdenPersonaRol("1");
    datoIdentifTarifDomain.setRiesgo(tipoMDLDomain);
    datoIdentifTarifDomain.setEdadTarificacion("1");
    datoIdentifTarifDomain.setCodAplicacion("1");

    unidadTarificacionDomain.setDatoIdentifTarif(datoIdentifTarifDomain);
    com.santalucia.cdc.core.domain.securedobjects.pricing.FechaDomain fechaDomain = new com.santalucia.cdc.core.domain.securedobjects.pricing.FechaDomain();
    fechaDomain.setFecEfectoAgrGarantia(instant);
    fechaDomain.setFecExtincion(instant);
    fechaDomain.setFecTarificacion(instant);
    unidadTarificacionDomain.setFecha(fechaDomain);
    DatosPropiosDomain datosPropiosDomain = new DatosPropiosDomain();
    datosPropiosDomain.setItinerario(tipoMDLDomain);
    datosPropiosDomain.setModalidadContable(tipoMDLDomain);
    datosPropiosDomain.setRamoContable(tipoMDLDomain);
    unidadTarificacionDomain.setDatosPropios(datosPropiosDomain);
    PrimaDomain primaDomain = new PrimaDomain();
    primaDomain.setImpPrimaBruta(1.0);
    primaDomain.setImpPrimaTarifa(1d);
    primaDomain.setImpPrimaNoConsumida(1d);
    primaDomain.setImpPrimaNatural(1d);
    primaDomain.setImpPrimaNivelada(1d);
    unidadTarificacionDomain.setPrima(primaDomain);
    CapitalDomain capitalDomain = new CapitalDomain();
    capitalDomain.setImpCapital(1d);
    capitalDomain.setImpCapitalReducido(1d);
    capitalDomain.setImpCapitalBasico(1d);
    capitalDomain.setImpCapitalNivelado(1d);
    capitalDomain.setImpCapitalConsolidado(1d);
    capitalDomain.setImpTramitacionSiniestros(1d);
    capitalDomain.setImpTrasladoMut(1d);
    capitalDomain.setPorIncrementoCapital(1d);
    List<PrevisionDomain> previsionResourceList = new ArrayList<>();
    PrevisionDomain previsionDomain = new PrevisionDomain();
    previsionDomain.setImpCapital(1d);
    previsionDomain.setNumAnnoSeguro("1");
    previsionResourceList.add(previsionDomain);
    capitalDomain.setPrevisiones(previsionResourceList);
    unidadTarificacionDomain.setCapital(capitalDomain);
    List<com.santalucia.cdc.core.domain.securedobjects.pricing.SobreprimaDomain> sobreprimaResourceList = new ArrayList<>();
    com.santalucia.cdc.core.domain.securedobjects.pricing.SobreprimaDomain sobreprimaDomain = new com.santalucia.cdc.core.domain.securedobjects.pricing.SobreprimaDomain();
    sobreprimaDomain.setImpSobreprima(1d);
    sobreprimaDomain.setImpTasaSobreprima(1d);
    sobreprimaDomain.setIndCalculoSobreprima("1");
    sobreprimaDomain.setSobrepNvlPoliza(tipoMDLDomain);
    sobreprimaResourceList.add(sobreprimaDomain);
    unidadTarificacionDomain.setSobreprimas(sobreprimaResourceList);

    List<ComposicionGarantiaDomain> composicionGarantiaDomains = new ArrayList<>();
    ComposicionGarantiaDomain composicionGarantiaDomain = new ComposicionGarantiaDomain();
    composicionGarantiaDomain.setGarantia(tipoMDLDomain);
    composicionGarantiaDomain.setIndOrigenRecomendador("1");
    List<TipoMDLDomain> composicionSubgarantResources = new ArrayList<>();
    composicionSubgarantResources.add(tipoMDLDomain);
    composicionGarantiaDomain.setComposicionesSubgarant(composicionSubgarantResources);
    List<ComposicionServicioDomain> composicionServicioDomains = new ArrayList<>();
    ComposicionServicioDomain composicionServicioDomain = new ComposicionServicioDomain();
    composicionServicioDomain.setIdServicio("1");
    composicionServicioDomain.setSubgarantia(tipoMDLDomain);
    composicionServicioDomain.setObligatoriedadSer(tipoMDLDomain);
    composicionServicioDomain.setImpCosteServicio(1d);
    composicionServicioDomains.add(composicionServicioDomain);
    composicionGarantiaDomain.setComposicionesServicios(composicionServicioDomains);
    List<ComposicionCaractDomain> composicionCaractDomains = new ArrayList<>();
    ComposicionCaractDomain composicionCaractDomain = new ComposicionCaractDomain();
    composicionCaractDomain.setCaracGarantia(tipoMDLDomain);
    composicionCaractDomain.setDescRespuestaCarac("1");
    composicionCaractDomains.add(composicionCaractDomain);
    composicionGarantiaDomain.setComposicionesCaract(composicionCaractDomains);
    composicionGarantiaDomains.add(composicionGarantiaDomain);
    unidadTarificacionDomain.setComposicionesGarantias(composicionGarantiaDomains);
    List<com.santalucia.cdc.core.domain.securedobjects.pricing.BonificacionDescDomain> bonificacionDescDomains = new ArrayList<>();
    com.santalucia.cdc.core.domain.securedobjects.pricing.BonificacionDescDomain bonificacionDescDomain = new com.santalucia.cdc.core.domain.securedobjects.pricing.BonificacionDescDomain();
    bonificacionDescDomains.add(bonificacionDescDomain);
    unidadTarificacionDomain.setBonificacionesDesc(bonificacionDescDomains);
    unidadTarificacionResources.add(unidadTarificacionDomain);
    objAseg.setUnidadesTarificacion(unidadTarificacionResources);
    //CAMPANNAS
    List<com.santalucia.cdc.core.domain.securedobjects.campaigns.CampannaDomain> campannaResourceList = new ArrayList<>();
    com.santalucia.cdc.core.domain.securedobjects.campaigns.CampannaDomain campannaDomain = new com.santalucia.cdc.core.domain.securedobjects.campaigns.CampannaDomain();
    campannaDomain.setCampannaComercial(tipoMDLDomain);
    campannaDomain.setCodAplicacion("1");
    campannaDomain.setIdCampanna("1");
    campannaDomain.setTipoCampanna(tipoMDLDomain);
    campannaDomain.setIncentivo(tipoMDLDomain);
    campannaDomain.setIndCaracIncentivo("1");
    campannaDomain.setFecInicio(instant);
    campannaResourceList.add(campannaDomain);
    objAseg.setCampannas(campannaResourceList);

    //METADATA
    MetadataDomain metadataDomain = new MetadataDomain();
    metadataDomain.setCodVersion("1");
    objAseg.setMetadata(metadataDomain);

    List<com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain> objtos = new ArrayList<>();
    objtos.add(objAseg);
    eventoPresupuestoIndvDomain.setObjetosAsegurados(objtos);

    //DEC

    DeclaracionDomain declaracionDomainTest = new DeclaracionDomain();

    declaracionDomainTest.setId("1");
    //DATO IDENTIFICATIVO
    com.santalucia.cdc.core.domain.declaration.com.DatoIdentificativoDomain datoIdentificativoDomain2 = new com.santalucia.cdc.core.domain.declaration.com.DatoIdentificativoDomain();
    datoIdentificativoDomain2.setIdDeclaracionesODL("1");
    datoIdentificativoDomain2.setIdObjAsegODL("1");
    datoIdentificativoDomain2.setIdPresupuestoODL("1");
    datoIdentificativoDomain2.setNumVersionPresOrigen("1");
    datoIdentificativoDomain2.setNumVersionPresOrigen("1");
    datoIdentificativoDomain2.setIdDeclaracionesODL("1");
    datoIdentificativoDomain2.setIdMensaje("1");
    datoIdentificativoDomain2.setTipoDeclaracion(tipoMDLDomain);
    //CARACTERISTICA
    List<com.santalucia.cdc.core.domain.declaration.com.CaracteristicaDomain> caracteristicaDomainList = new ArrayList<>();
    com.santalucia.cdc.core.domain.declaration.com.CaracteristicaDomain caracteristicaDomain2 = new com.santalucia.cdc.core.domain.declaration.com.CaracteristicaDomain();
    caracteristicaDomain2.setPregunta(tipoMDLDomain);
    caracteristicaDomain2.setIndAplicPregunta("1");
    List<RespuestaDomain> respuestaDomainList = new ArrayList<>();
    RespuestaDomain respuestaDomain = new RespuestaDomain();
    respuestaDomain.setIndTipoRespuesta("1");
    respuestaDomain.setRespFacilitada(tipoMDLDomain);
    respuestaDomainList.add(respuestaDomain);
    caracteristicaDomain2.setRespuestas(respuestaDomainList);
    caracteristicaDomainList.add(caracteristicaDomain2);

    MetadataDomain metadataDomain2 = new MetadataDomain();

    declaracionDomainTest.setDatoIdentificativo(datoIdentificativoDomain2);
    declaracionDomainTest.setCaracteristicas(caracteristicaDomainList);
    declaracionDomainTest.setMetadata(metadataDomain2);


    List<DeclaracionDomain> declaracionDomainList = new ArrayList<>();
    declaracionDomainList.add(declaracionDomainTest);
    eventoPresupuestoIndvDomain.setDeclaracion(declaracionDomainList);
    EventoPresupuestoIndvDomain resultado = processor.anonimizate(eventoPresupuestoIndvDomain);

    // Verificación

    assertThat(resultado.getPresupuestoIndividual().getFigura().getDatosPersonales().get(0).getNumDocumento()).isEqualTo("**********");
    assertThat(resultado.getPresupuestoIndividual().getFigura().getDatosPersonales().get(0).getNumDocumento()).isEqualTo("**********");
    assertThat(resultado.getObjetosAsegurados().get(0).getCaracteristica().getAnimales().get(0).getImpValorMascota()).isEqualTo(0.0);
    assertThat(resultado.getDeclaracion().get(0).getCaracteristicas().get(0).getIndAplicPregunta()).isEqualTo("**********");

  }
}
