package com.santalucia.cdc.core.mappers.budget;


import com.santalucia.arq.ams.odl.historico.presupuestos.objeto.asegurado.api.model.*;
import com.santalucia.cdc.core.domain.MetadataDomain;
import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.domain.securedobjects.campaigns.CampannaDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.*;
import com.santalucia.cdc.core.domain.securedobjects.identif.DatoIdentificativoDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.*;
import com.santalucia.cdc.core.domain.securedobjects.pricing.com.ComposicionCaractDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.com.ComposicionServicioDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.com.DatosPropiosDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.com.PrevisionDomain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HistObjetoAseguradoDomainMapperTest {

  @DisplayName("HistObjetoAsegToResource")
  @Test
  void toDomain() {

    com.santalucia.arq.ams.odl.historico.presupuestos.objeto.asegurado.api.model.EntityModelObjetoAseguradoPresupuestoResource objResource = new com.santalucia.arq.ams.odl.historico.presupuestos.objeto.asegurado.api.model.EntityModelObjetoAseguradoPresupuestoResource();
    objResource.setId("1");
    //DATO IDENTIFICATIVO
    com.santalucia.arq.ams.odl.historico.presupuestos.objeto.asegurado.api.model.DatoIdentificativoResource datoIdentificativoResource = new DatoIdentificativoResource();
    datoIdentificativoResource.setIdMensaje("1");
    datoIdentificativoResource.setIdPresupuestoODL("1");
    datoIdentificativoResource.setNumOrden("1");
    datoIdentificativoResource.setNumVersionPresOrigen("1");
    datoIdentificativoResource.setNumVersionPresODL("1");
    datoIdentificativoResource.setDesLocalidadTarif("1");
    datoIdentificativoResource.setDesProvinciaTarif("1");
    datoIdentificativoResource.setCodTipoServicio("1");
    TipoObjAsegResource tipoObjAsegResource = new TipoObjAsegResource();
    tipoObjAsegResource.setCodMDL("1");
    tipoObjAsegResource.setCodOrigen("1");
    tipoObjAsegResource.setDescOrigen("1");
    datoIdentificativoResource.setTipoObjAseg(tipoObjAsegResource);
    datoIdentificativoResource.setIndCategoria("1");
    datoIdentificativoResource.setCodModServicio("1");
    datoIdentificativoResource.setCodSubtipoServicio("1");
    objResource.setDatoIdentificativo(datoIdentificativoResource);
    //CARACTERISTICA
    CaracteristicaResource caracteristicaResource = new CaracteristicaResource();

    List<AnimalResource> animalResourceList = new ArrayList<>();
    AnimalResource animalResource = new AnimalResource();
    animalResource.setFecNacimiento(OffsetDateTime.now());
    animalResource.setIndTipoEspecie("1");
    RazaResource razaResource = new RazaResource();
    razaResource.setCodMDL("1");
    razaResource.setCodOrigen("1");
    razaResource.setDescOrigen("1");
    animalResource.setRaza(razaResource);
    animalResource.setIndTipoAnimalComp("1");
    animalResource.setNumIdentAnimalComp("1");
    animalResource.setNomMascota("1");
    animalResource.setNumChip("1");
    animalResource.setImpValorMascota(1.0);
    animalResource.setIndPerroMestizo("1");
    animalResource.setIndPerfEstadoSalud("1");
    animalResourceList.add(animalResource);
    caracteristicaResource.setAnimales(animalResourceList);

    List<DomicilioResource> domicilioResources = new ArrayList<>();
    DomicilioResource domicilioResource = new DomicilioResource();
    domicilioResource.setIdDomicilio("1");
    PaisResource paisResource = new PaisResource();
    paisResource.setCodMDL("1");
    paisResource.setCodOrigen("1");
    paisResource.setDescOrigen("1");
    domicilioResource.setPais(paisResource);
    domicilioResource.setCodMunicipio("1");
    ProvinciaResource provinciaResource = new ProvinciaResource();
    provinciaResource.setCodMDL("1");
    provinciaResource.setCodOrigen("1");
    provinciaResource.setDescOrigen("1");
    domicilioResource.setProvincia(provinciaResource);
    LocalidadResource localidadResource = new LocalidadResource();
    localidadResource.setCodMDL("1");
    localidadResource.setCodOrigen("1");
    localidadResource.setDescOrigen("1");
    domicilioResource.setLocalidad(localidadResource);
    domicilioResource.setCodMunicipio("1");
    domicilioResource.setCodEntColectiva("1");
    domicilioResource.setCodNucPobla("1");
    DenomPoblaResource denomPoblaResource = new DenomPoblaResource();
    denomPoblaResource.setCodMDL("1");
    denomPoblaResource.setCodOrigen("1");
    denomPoblaResource.setDescOrigen("1");
    domicilioResource.setDenomPobla(denomPoblaResource);
    domicilioResource.setCodPostal("1");
    TipoViaResource tipoViaResource = new TipoViaResource();
    tipoViaResource.setCodMDL("1");
    tipoViaResource.setCodOrigen("1");
    tipoViaResource.setDescOrigen("1");
    domicilioResource.setTipoVia(tipoViaResource);
    domicilioResource.setDescDomicilio("1");
    domicilioResource.setNumNumero("1");
    domicilioResource.setNumComplemento("1");
    domicilioResource.setNumBloque("1");
    domicilioResource.setNumPortal("1");
    domicilioResource.setNumEscalera("1");
    domicilioResource.setNumPiso("1");
    domicilioResource.setNumPuerta("1");
    domicilioResource.setIndNormalizado("1");
    domicilioResource.setDesOtrosDatos("1");
    domicilioResources.add(domicilioResource);
    caracteristicaResource.setDomicilios(domicilioResources);

    List<FiguraResource> figuraResourcesList = new ArrayList<>();
    FiguraResource figuraResource = new FiguraResource();
    figuraResource.setIdPersona("1");
    TipoPersonaResource tipoPersonaResource = new TipoPersonaResource();
    tipoPersonaResource.setCodMDL("1");
    tipoPersonaResource.setCodOrigen("1");
    tipoPersonaResource.setDescOrigen("1");
    figuraResource.setTipoPersona(tipoPersonaResource);
    figuraResource.setTxtNombre("1");
    figuraResource.setTxtPrimerApellido("1");
    figuraResource.setTxtSegundoApellido("1");
    figuraResource.setTxtRazonSocial("1");
    TipoDocumentoResource tipoDocumentoResource = new TipoDocumentoResource();
    tipoDocumentoResource.setCodMDL("1");
    tipoDocumentoResource.setCodOrigen("1");
    tipoDocumentoResource.setDescOrigen("1");
    figuraResource.setTipoDocumento(tipoDocumentoResource);
    figuraResource.setNumDocumento("1");
    figuraResource.setFecNacimiento(OffsetDateTime.now());
    SexoResource sexoResource = new SexoResource();
    sexoResource.setCodMDL("1");
    sexoResource.setCodOrigen("1");
    sexoResource.setDescOrigen("1");
    figuraResource.setSexo(sexoResource);
    NacionalidadResource nacionalidadResource = new NacionalidadResource();
    nacionalidadResource.setCodMDL("1");
    nacionalidadResource.setCodOrigen("1");
    nacionalidadResource.setDescOrigen("1");
    figuraResource.setNacionalidad(nacionalidadResource);
    ProfesionResource profesionResource = new ProfesionResource();
    profesionResource.setCodMDL("1");
    profesionResource.setCodOrigen("1");
    profesionResource.setDescOrigen("1");
    figuraResource.setProfesion(profesionResource);
    GrupoProfesionResource grupoProfesionResource = new GrupoProfesionResource();
    grupoProfesionResource.setCodMDL("1");
    grupoProfesionResource.setCodOrigen("1");
    grupoProfesionResource.setDescOrigen("1");
    figuraResource.setGrupoProfesion(grupoProfesionResource);
    figuraResource.setIndEstadoCivil("1");
    BeneficiarioResource beneficiarioResource = new BeneficiarioResource();
    beneficiarioResource.setCodMDL("1");
    beneficiarioResource.setCodOrigen("1");
    beneficiarioResource.setDescOrigen("1");
    figuraResource.setBeneficiario(beneficiarioResource);

    figuraResourcesList.add(figuraResource);
    caracteristicaResource.setFiguras(figuraResourcesList);


    List<DispositivoElectrResource> dispositivoElectrResourceList = new ArrayList<>();
    DispositivoElectrResource dispositivoElectrResource = new DispositivoElectrResource();
    dispositivoElectrResource.setCodIdentificador("1");
    dispositivoElectrResource.setImpCoste(1.0);
    MarcaDispElectrResource marcaDispElectrResource = new MarcaDispElectrResource();
    marcaDispElectrResource.setCodMDL("1");
    marcaDispElectrResource.setCodOrigen("1");
    marcaDispElectrResource.setDescOrigen("1");
    dispositivoElectrResource.setMarcaDispElectr(marcaDispElectrResource);
    ModeloDispElectrResource modeloDispElectrResource = new ModeloDispElectrResource();
    modeloDispElectrResource.setCodMDL("1");
    modeloDispElectrResource.setCodOrigen("1");
    modeloDispElectrResource.setDescOrigen("1");
    dispositivoElectrResource.setModeloDispElectr(modeloDispElectrResource);
    dispositivoElectrResourceList.add(dispositivoElectrResource);
    caracteristicaResource.setDispositivosElectr(dispositivoElectrResourceList);
    objResource.setCaracteristica(caracteristicaResource);

    //UNIDADES TARIF
    List<UnidadTarificacionResource> unidadTarificacionResources = new ArrayList<>();
    UnidadTarificacionResource unidadTarificacionResource = new UnidadTarificacionResource();

    unidadTarificacionResource.setNumIdNivTarificacion("1");
    TipoPrimaResource tipoPrimaResource = new TipoPrimaResource();
    tipoPrimaResource.setCodMDL("1");
    tipoPrimaResource.setCodOrigen("1");
    tipoPrimaResource.setDescOrigen("1");
    unidadTarificacionResource.setTipoPrima(tipoPrimaResource);
    MonedaResource monedaResource = new MonedaResource();
    monedaResource.setCodMDL("1");
    monedaResource.setCodOrigen("1");
    monedaResource.setDescOrigen("1");
    unidadTarificacionResource.setMoneda(monedaResource);
    NivelTarificacionResource nivelTarificacionResource = new NivelTarificacionResource();
    nivelTarificacionResource.setCodMDL("1");
    nivelTarificacionResource.setCodOrigen("1");
    nivelTarificacionResource.setDescOrigen("1");
    unidadTarificacionResource.setNivelTarificacion(nivelTarificacionResource);

    DatoIdentifTarifResource datoIdentifTarifResource = new DatoIdentifTarifResource();
    AgrupGarantiaResource agrupGarantiaResource = new AgrupGarantiaResource();
    agrupGarantiaResource.setCodMDL("1");
    agrupGarantiaResource.setCodOrigen("1");
    agrupGarantiaResource.setDescOrigen("1");
    datoIdentifTarifResource.setAgrupGarantia(agrupGarantiaResource);
    TipoComplementoResource tipoComplementoResource = new TipoComplementoResource();
    tipoComplementoResource.setCodMDL("1");
    tipoComplementoResource.setCodOrigen("1");
    tipoComplementoResource.setDescOrigen("1");
    datoIdentifTarifResource.setTipoComplemento(tipoComplementoResource);
    datoIdentifTarifResource.setNumOrdenComplemento("1");
    CategoriaResource categoriaResource = new CategoriaResource();
    categoriaResource.setCodMDL("1");
    categoriaResource.setCodOrigen("1");
    categoriaResource.setDescOrigen("1");
    datoIdentifTarifResource.setCategoria(categoriaResource);
    datoIdentifTarifResource.setNumOrdenAgrupGar("1");
    datoIdentifTarifResource.setAgrupGarantiaHog("1");
    GarantiaUnitariaResource garantiaUnitariaResource = new GarantiaUnitariaResource();
    garantiaUnitariaResource.setCodMDL("1");
    garantiaUnitariaResource.setCodOrigen("1");
    garantiaUnitariaResource.setDescOrigen("1");
    datoIdentifTarifResource.setGarantiaUnitaria(garantiaUnitariaResource);
    datoIdentifTarifResource.setNumOrdenPersonaRol("1");
    RiesgoResource riesgoResource = new RiesgoResource();
    riesgoResource.setCodMDL("1");
    riesgoResource.setCodOrigen("1");
    riesgoResource.setDescOrigen("1");
    datoIdentifTarifResource.setRiesgo(riesgoResource);
    datoIdentifTarifResource.setEdadTarificacion("1");
    datoIdentifTarifResource.setCodAplicacion("1");

    unidadTarificacionResource.setDatoIdentifTarif(datoIdentifTarifResource);
    FechaResource fechaResource = new FechaResource();
    fechaResource.setFecEfectoAgrGarantia(OffsetDateTime.now());
    fechaResource.setFecExtincion(OffsetDateTime.now());
    fechaResource.setFecExtincion(OffsetDateTime.now());
    unidadTarificacionResource.setFecha(fechaResource);
    DatosPropiosResource datosPropiosResource = new DatosPropiosResource();
    ItinerarioResource itinerarioResource = new ItinerarioResource();
    itinerarioResource.setCodMDL("1");
    itinerarioResource.setCodOrigen("1");
    itinerarioResource.setDescOrigen("1");
    datosPropiosResource.setItinerario(itinerarioResource);
    ModalidadContableResource modalidadContableResource = new ModalidadContableResource();
    modalidadContableResource.setCodMDL("1");
    modalidadContableResource.setCodOrigen("1");
    modalidadContableResource.setDescOrigen("1");
    datosPropiosResource.setModalidadContable(modalidadContableResource);
    RamoContableResource ramoContableResource = new RamoContableResource();
    ramoContableResource.setCodMDL("1");
    ramoContableResource.setCodOrigen("1");
    ramoContableResource.setDescOrigen("1");
    datosPropiosResource.setRamoContable(ramoContableResource);
    unidadTarificacionResource.setDatosPropios(datosPropiosResource);
    PrimaResource primaResource = new PrimaResource();
    primaResource.setImpPrimaBruta(1.0);
    primaResource.setImpPrimaTarifa(1d);
    primaResource.setImpPrimaNoConsumida(1d);
    primaResource.setImpPrimaNatural(1d);
    primaResource.setImpPrimaNivelada(1d);
    unidadTarificacionResource.setPrima(primaResource);
    CapitalResource capitalResource = new CapitalResource();
    capitalResource.setImpCapital(1d);
    capitalResource.setImpCapitalReducido(1d);
    capitalResource.setImpCapitalBasico(1d);
    capitalResource.setImpCapitalNivelado(1d);
    capitalResource.setImpCapitalConsolidado(1d);
    capitalResource.setImpTramitacionSiniestros(1d);
    capitalResource.setImpTrasladoMut(1d);
    capitalResource.setPorIncrementoCapital(1d);
    List<PrevisionResource> previsionResourceList = new ArrayList<>();
    PrevisionResource previsionResource = new PrevisionResource();
    previsionResource.setImpCapital(1d);
    previsionResource.setNumAnnoSeguro("1");
    previsionResourceList.add(previsionResource);
    capitalResource.setPrevisiones(previsionResourceList);
    unidadTarificacionResource.setCapital(capitalResource);
    List<SobreprimaResource> sobreprimaResourceList = new ArrayList<>();
    SobreprimaResource sobreprimaResource = new SobreprimaResource();
    sobreprimaResource.setImpSobreprima(1d);
    sobreprimaResource.setImpTasaSobreprima(1d);
    sobreprimaResource.setIndCalculoSobreprima("1");
    SobrepNvlPolizaResource sobrepNvlPolizaResource = new SobrepNvlPolizaResource();
    sobrepNvlPolizaResource.setCodMDL("1");
    sobrepNvlPolizaResource.setCodOrigen("1");
    sobrepNvlPolizaResource.setDescOrigen("1");
    sobreprimaResource.setSobrepNvlPoliza(sobrepNvlPolizaResource);
    sobreprimaResourceList.add(sobreprimaResource);
    unidadTarificacionResource.setSobreprimas(sobreprimaResourceList);

    List<ComposicionGarantiaResource> composicionGarantiaResources = new ArrayList<>();
    ComposicionGarantiaResource composicionGarantiaResource = new ComposicionGarantiaResource();
    GarantiaResource garantiaResource = new GarantiaResource();
    garantiaResource.setCodMDL("1");
    garantiaResource.setCodOrigen("1");
    garantiaResource.setDescOrigen("1");
    composicionGarantiaResource.setGarantia(garantiaResource);
    composicionGarantiaResource.setIndOrigenRecomendador("1");
    List<ComposicionSubgarantResource> composicionSubgarantResources = new ArrayList<>();
    ComposicionSubgarantResource composicionSubgarantResource = new ComposicionSubgarantResource();
    composicionSubgarantResource.setCodMDL("1");
    composicionSubgarantResources.add(composicionSubgarantResource);
    composicionGarantiaResource.setComposicionesSubgarant(composicionSubgarantResources);
    List<ComposicionServicioResource> composicionServicioResourceList = new ArrayList<>();
    ComposicionServicioResource composicionServicioResource = new ComposicionServicioResource();
    composicionServicioResource.setIdServicio("1");
    SubgarantiaResource subgarantiaResource = new SubgarantiaResource();
    subgarantiaResource.setCodMDL("1");
    composicionServicioResource.setSubgarantia(subgarantiaResource);
    ObligatoriedadSerResource obligatoriedadSerResource = new ObligatoriedadSerResource();
    obligatoriedadSerResource.setCodMDL("1");
    composicionServicioResource.setObligatoriedadSer(obligatoriedadSerResource);
    composicionServicioResource.setImpCosteServicio(1d);
    composicionServicioResourceList.add(composicionServicioResource);
    composicionGarantiaResource.setComposicionesServicios(composicionServicioResourceList);
    List<ComposicionCaractResource> composicionCaractResources = new ArrayList<>();
    ComposicionCaractResource composicionCaractResource = new ComposicionCaractResource();
    CaracGarantiaResource caracGarantiaResource = new CaracGarantiaResource();
    caracGarantiaResource.setCodMDL("1");
    composicionCaractResource.setCaracGarantia(caracGarantiaResource);
    composicionCaractResource.setDescRespuestaCarac("1");
    composicionCaractResources.add(composicionCaractResource);
    composicionGarantiaResource.setComposicionesCaract(composicionCaractResources);
    composicionGarantiaResources.add(composicionGarantiaResource);
    unidadTarificacionResource.setComposicionesGarantias(composicionGarantiaResources);
    List<BonificacionDescResource> bonificacionDescResources = new ArrayList<>();
    BonificacionDescResource bonificacionDescResource = new BonificacionDescResource();
    bonificacionDescResource.setImpBonificacion(1d);
    bonificacionDescResources.add(bonificacionDescResource);
    unidadTarificacionResource.setBonificacionesDesc(bonificacionDescResources);
    unidadTarificacionResources.add(unidadTarificacionResource);
    objResource.setUnidadesTarificacion(unidadTarificacionResources);
    //CAMPANNAS
    List<CampannaResource> campannaResourceList = new ArrayList<>();
    CampannaResource campannaResource = new CampannaResource();
    CampannaComercialResource campannaComercialResource = new CampannaComercialResource();
    campannaComercialResource.setCodMDL("1");
    campannaResource.setCampannaComercial(campannaComercialResource);
    campannaResource.setCodAplicacion("1");
    campannaResource.setIdCampanna("1");
    TipoCampannaResource tipoCampannaResource = new TipoCampannaResource();
    tipoCampannaResource.setCodMDL("1");
    campannaResource.setTipoCampanna(tipoCampannaResource);
    IncentivoResource incentivoResource = new IncentivoResource();
    incentivoResource.setCodMDL("1");
    campannaResource.setIncentivo(incentivoResource);
    campannaResource.setIndCaracIncentivo("1");
    campannaResource.setFecInicio(OffsetDateTime.now());
    campannaResourceList.add(campannaResource);
    objResource.setCampannas(campannaResourceList);

    //METADATA
    MetadataResource metadataResource = new MetadataResource();
    metadataResource.setCodVersion("1");
    objResource.setMetadata(metadataResource);

    HistObjetoAseguradoDomainMapper mapp = new HistObjetoAseguradoDomainMapperImpl();

    ObjetosAseguradosDomain result = mapp.toDomain(objResource);

    assertThat(result).isNotNull();
  }

  @DisplayName("HistObjetoAsegToResource")
  @Test
  void toResource() {

    TipoMDLDomain tipoMDLDomain = new TipoMDLDomain();
    tipoMDLDomain.setCodOrigen("1");
    tipoMDLDomain.setDescOrigen("1");
    tipoMDLDomain.setCodMDL("1");

    Instant dateToInstant = OffsetDateTime.now().toInstant();

    ObjetosAseguradosDomain objAseg = new ObjetosAseguradosDomain();
    //DATO IDENTIFICATIVO
    DatoIdentificativoDomain datoIdentificativoDomain = new DatoIdentificativoDomain();
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

    List<FiguraDomain> figuraResourcesList = new ArrayList<>();
    FiguraDomain figuraDomain = new FiguraDomain();
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
    FechaDomain fechaDomain = new FechaDomain();
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
    List<CampannaDomain> campannaResourceList = new ArrayList<>();
    CampannaDomain campannaResource = new CampannaDomain();
    campannaResource.setCampannaComercial(tipoMDLDomain);
    campannaResource.setCodAplicacion("1");
    campannaResource.setIdCampanna("1");
    campannaResource.setTipoCampanna(tipoMDLDomain);
    campannaResource.setIncentivo(tipoMDLDomain);
    campannaResource.setIndCaracIncentivo("1");
    campannaResource.setFecInicio(dateToInstant);
    campannaResourceList.add(campannaResource);
    objAseg.setCampannas(campannaResourceList);

    //METADATA
    MetadataDomain metadataResource = new MetadataDomain();
    metadataResource.setCodVersion("1");
    objAseg.setMetadata(metadataResource);

    HistObjetoAseguradoDomainMapper mapp = new HistObjetoAseguradoDomainMapperImpl();

    ObjetoAseguradoPresupuestoRequestBodyResource result = mapp.toResource(objAseg);

    assertThat(result).isNotNull();

  }


}
