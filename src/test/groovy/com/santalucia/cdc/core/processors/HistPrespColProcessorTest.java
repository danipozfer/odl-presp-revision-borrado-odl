package com.santalucia.cdc.core.processors;

import com.santalucia.cdc.core.domain.EventoPresupuestoColDomain;
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
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.declaration.com.resp.RespuestaDomain;
import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import com.santalucia.cdc.core.domain.metadata.AuditoriaDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.AnimalDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.CaracteristicaDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.DispositivoElectrDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.DomicilioDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.BonificacionDescDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.CapitalDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.ComposicionGarantiaDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.DatoIdentifTarifDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.PrimaDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.SobreprimaDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.UnidadTarificacionDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.com.ComposicionCaractDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.com.ComposicionServicioDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.com.DatosPropiosDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.com.PrevisionDomain;
import com.santalucia.cdc.core.service.policies.PolizaColectivaClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HistPrespColProcessorTest {

  @InjectMocks
  private HistPrespColProcessor prespColProcessor;

  @Mock
  private PolizaColectivaClientService polizaService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testProcessWithPolizaColectiva() {
    EventoPresupuestoColDomain inputEvent = mock(EventoPresupuestoColDomain.class);
    PresupuestoColectivoDomain presu = mock(PresupuestoColectivoDomain.class);
    DatoIdentificativoDomain dat = mock(DatoIdentificativoDomain.class);
    when(polizaService.getPolizaColectiva("123")).thenReturn(true);
    when(dat.getNumIdentificacion()).thenReturn("123");
    when(presu.getDatoIdentificativo()).thenReturn(dat);
     when(inputEvent.getPresupuestoColectivo()).thenReturn(presu);
    // Act
    EventoPresupuestoColDomain result = prespColProcessor.process(inputEvent);

    // Assert

    verify(polizaService, times(1)).getPolizaColectiva("123");
    verify(polizaService, never()).getHistoricoColectiva("123");


  }

  @Test
  public void testProcessWithPolizaColectiva2() {
    EventoPresupuestoColDomain inputEvent = mock(EventoPresupuestoColDomain.class);
    PresupuestoColectivoDomain presu = mock(PresupuestoColectivoDomain.class);
    DatoIdentificativoDomain dat = mock(DatoIdentificativoDomain.class);
    when(polizaService.getHistoricoColectiva("123")).thenReturn(true);
    when(polizaService.getPolizaColectiva("123")).thenReturn(false);

    when(dat.getNumIdentificacion()).thenReturn("123");
    when(presu.getDatoIdentificativo()).thenReturn(dat);
    when(inputEvent.getPresupuestoColectivo()).thenReturn(presu);
    // Act
    EventoPresupuestoColDomain result = prespColProcessor.process(inputEvent);

    // Assert

    verify(polizaService, times(1)).getHistoricoColectiva("123");
    verify(polizaService, times(1)).getPolizaColectiva("123");

  }

  @Test
  public void testProcessWithPolizaColectivaAnon() {


    /*EventoPresupuestoColDomain inputEvent = mock(EventoPresupuestoColDomain.class);
    PresupuestoColectivoDomain presu = mock(PresupuestoColectivoDomain.class);
    DatoIdentificativoDomain dat = mock(DatoIdentificativoDomain.class);
    FechaYEstadoDomain fechaYEstadoDomain = mock(FechaYEstadoDomain.class);
    FechaDomain fecha = mock(FechaDomain.class);

    // Configurar comportamiento de mocks
    when(inputEvent.getPresupuestoColectivo()).thenReturn(presu);
    when(presu.getFechaYEstado()).thenReturn(fechaYEstadoDomain);
    when(fechaYEstadoDomain.getFecha()).thenReturn(fecha);
    when(fecha.getFecAlta()).thenReturn(Instant.now().plus(30, ChronoUnit.DAYS));
    when(dat.getNumIdentificacion()).thenReturn("123");
    when(polizaService.getHistoricoColectiva("123")).thenReturn(false);
    when(polizaService.getPolizaColectiva("123")).thenReturn(false);*/


    /*EventoPresupuestoColDomain event = mock(EventoPresupuestoColDomain.class);
    PresupuestoColectivoDomain pres = mock(PresupuestoColectivoDomain.class);
    FechaYEstadoDomain fechaYEstado = mock(FechaYEstadoDomain.class);
    FechaDomain fecha = mock(FechaDomain.class);
    Instant thirtyDaysAfter = event.getPresupuestoColectivo().getFechaYEstado().getFecha().getFecAlta().plus(30, ChronoUnit.DAYS);
    when(prespColProcessor.anonimizate(event)).thenReturn(true);
    when(fecha.getFecAlta()).thenReturn(thirtyDaysAfter);
    when(fechaYEstado.getFecha()).thenReturn(fecha);
    when(pres.getFechaYEstado()).thenReturn(fechaYEstado);
    when(event.getPresupuestoColectivo()).thenReturn(pres);*/


    /*EventoPresupuestoColDomain eventoPresupuestoColDomain = mock(EventoPresupuestoColDomain.class);
    PresupuestoColectivoDomain presupCol = mock(PresupuestoColectivoDomain.class);
    DatoCobroDomain datoCobro = mock(DatoCobroDomain.class);
    DatoMedioCobroDomain datoMedioCobroDomain = mock(DatoMedioCobroDomain.class);
    DatoCobroBancarioDomain datoCobroBancarioDomain = mock(DatoCobroBancarioDomain.class);
    when(prespColProcessor.anonimizate(eventoPresupuestoColDomain));
    when(datoMedioCobroDomain.getDatoCobroBancario()).thenReturn(datoCobroBancarioDomain);
    when(datoCobro.getDatoMedioCobro()).thenReturn(datoMedioCobroDomain);
    when(presupCol.getDatoCobro()).thenReturn(datoCobro);
    when(eventoPresupuestoColDomain.getPresupuestoColectivo()).thenReturn(presupCol);*/


    // Act
    /*EventoPresupuestoColDomain result = prespColProcessor.process(inputEvent);

    // Assert
    verify(prespColProcessor,times(1)).anonimizate(result);
    verify(polizaService, times(1)).getHistoricoColectiva("123");
    verify(polizaService, times(1)).getPolizaColectiva("123");*/

  }




  @Test
  public void testAnonimizate() {
    // Crear un objeto de prueba EventoPresupuestoColDomain
    TipoMDLDomain tipoMDLDomain = new TipoMDLDomain();
    tipoMDLDomain.setCodOrigen("1");
    tipoMDLDomain.setDescOrigen("1");
    tipoMDLDomain.setCodMDL("1");

    Instant instant = OffsetDateTime.now().toInstant();
    EventoPresupuestoColDomain eventoPresupuestoColDomain = new EventoPresupuestoColDomain();


    HistPrespColProcessor processor = new HistPrespColProcessor();

    PresupuestoColectivoDomain presupuestoColectivo = new PresupuestoColectivoDomain();
    DatoIdentificativoDomain datoIdentificativoColectivo = new DatoIdentificativoDomain();

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

    // Llamar al método anonimizate en el objeto de prueba
    eventoPresupuestoColDomain.setPresupuestoColectivo(presupuestoColectivo);
    //OBJETO ASEGURADO

    List<com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain> listaObjs = new ArrayList<>();

    Instant dateToInstant = OffsetDateTime.now().toInstant();

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
    animalResource.setFecNacimiento(dateToInstant);
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
    figuraDomain.setFecNacimiento(dateToInstant);
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
    fechaDomain.setFecEfectoAgrGarantia(dateToInstant);
    fechaDomain.setFecExtincion(dateToInstant);
    fechaDomain.setFecTarificacion(dateToInstant);
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
    List<SobreprimaDomain> sobreprimaResourceList = new ArrayList<>();
    SobreprimaDomain sobreprimaDomain = new SobreprimaDomain();
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
    List<BonificacionDescDomain> bonificacionDescDomains = new ArrayList<>();
    BonificacionDescDomain bonificacionDescDomain = new BonificacionDescDomain();
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
    campannaDomain.setFecInicio(dateToInstant);
    campannaResourceList.add(campannaDomain);
    objAseg.setCampannas(campannaResourceList);

    //METADATA
    MetadataDomain metadataDomain = new MetadataDomain();
    metadataDomain.setCodVersion("1");
    objAseg.setMetadata(metadataDomain);

    listaObjs.add(objAseg);

    eventoPresupuestoColDomain.setObjetosAsegurados(listaObjs);


    DeclaracionDomain declaracionDomainTest = new DeclaracionDomain();

    declaracionDomainTest.setId("1");
    //DATO IDENTIFICATIVO
    com.santalucia.cdc.core.domain.declaration.com.DatoIdentificativoDomain datoIdentificativoDomain1 = new com.santalucia.cdc.core.domain.declaration.com.DatoIdentificativoDomain();
    datoIdentificativoDomain1.setIdDeclaracionesODL("1");
    datoIdentificativoDomain1.setIdObjAsegODL("1");
    datoIdentificativoDomain1.setIdPresupuestoODL("1");
    datoIdentificativoDomain1.setNumVersionPresOrigen("1");
    datoIdentificativoDomain1.setNumVersionPresOrigen("1");
    datoIdentificativoDomain1.setIdDeclaracionesODL("1");
    datoIdentificativoDomain1.setIdMensaje("1");
    datoIdentificativoDomain1.setTipoDeclaracion(tipoMDLDomain);
    //CARACTERISTICA
    List<com.santalucia.cdc.core.domain.declaration.com.CaracteristicaDomain> caracteristicaDomainList = new ArrayList<>();
    com.santalucia.cdc.core.domain.declaration.com.CaracteristicaDomain caracteristicaDomain1 = new com.santalucia.cdc.core.domain.declaration.com.CaracteristicaDomain();
    caracteristicaDomain1.setPregunta(tipoMDLDomain);
    caracteristicaDomain1.setIndAplicPregunta("1");
    List<RespuestaDomain> respuestaDomainList = new ArrayList<>();
    RespuestaDomain respuestaDomain = new RespuestaDomain();
    respuestaDomain.setIndTipoRespuesta("1");
    respuestaDomain.setRespFacilitada(tipoMDLDomain);
    respuestaDomainList.add(respuestaDomain);
    caracteristicaDomain1.setRespuestas(respuestaDomainList);
    caracteristicaDomainList.add(caracteristicaDomain1);

    MetadataDomain metadataDomain1 = new MetadataDomain();

    declaracionDomainTest.setDatoIdentificativo(datoIdentificativoDomain1);
    declaracionDomainTest.setCaracteristicas(caracteristicaDomainList);
    declaracionDomainTest.setMetadata(metadataDomain1);

    List<DeclaracionDomain> declaracionDomainList = new ArrayList<>();
    declaracionDomainList.add(declaracionDomainTest);
    eventoPresupuestoColDomain.setDeclaracion(declaracionDomainList);

    EventoPresupuestoColDomain resultado = processor.anonimizate(eventoPresupuestoColDomain);

    // Verificación

    assertThat(resultado.getPresupuestoColectivo().getFigura().getDatosPersonales().get(0).getNumDocumento()).isEqualTo("**********");
    assertThat(resultado.getPresupuestoColectivo().getFigura().getDatosPersonales().get(0).getNumDocumento()).isEqualTo("**********");
    assertThat(resultado.getObjetosAsegurados().get(0).getCaracteristica().getAnimales().get(0).getImpValorMascota()).isEqualTo(0.0);
    assertThat(resultado.getDeclaracion().get(0).getCaracteristicas().get(0).getIndAplicPregunta()).isEqualTo("**********");

  }
}
