package com.santalucia.cdc.core.mappers.budget;


import com.santalucia.arq.ams.odl.historico.presupuestos.declaraciones.api.model.*;
import com.santalucia.cdc.core.domain.MetadataDomain;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.declaration.com.CaracteristicaDomain;
import com.santalucia.cdc.core.domain.declaration.com.DatoIdentificativoDomain;
import com.santalucia.cdc.core.domain.declaration.com.resp.RespuestaDomain;
import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class HistDeclaracionDomainMapperTest {

  @Test
  void toDomain() {

    EntityModelDeclaracionResource declaracionResource = new EntityModelDeclaracionResource();
    declaracionResource.setId("1");

    DatoIdentificativoResource datoIdentificativoResource= new DatoIdentificativoResource();
    datoIdentificativoResource.setIdDeclaracionesODL("1");
    datoIdentificativoResource.setIdMensaje("1");
    TipoDeclaracionResource tipoDeclaracionResource = new TipoDeclaracionResource();
    tipoDeclaracionResource.setCodOrigen("1");
    tipoDeclaracionResource.setDescOrigen("1");
    tipoDeclaracionResource.setCodMDL("1");
    datoIdentificativoResource.setTipoDeclaracion(tipoDeclaracionResource);
    datoIdentificativoResource.setIdPresupuestoODL("1");
    datoIdentificativoResource.setNumVersionPresOrigen("1");
    datoIdentificativoResource.setNumIdDeclPre("1");
    datoIdentificativoResource.setNumVersionPresODL("1");
    declaracionResource.setDatoIdentificativo(datoIdentificativoResource);


    //CARACTERISTICA
    List<CaracteristicaResource> caracteristicaDomainList = new ArrayList<>();
    CaracteristicaResource caracteristicaDomain = new CaracteristicaResource();
    PreguntaResource preguntaResource = new PreguntaResource();
    preguntaResource.setCodMDL("1");
    preguntaResource.setCodOrigen("1");
    preguntaResource.setDescOrigen("1");
    caracteristicaDomain.setPregunta(preguntaResource);
    caracteristicaDomain.setIndAplicPregunta("1");
    List<RespuestaResource> respuestaDomainList = new ArrayList<>();
    RespuestaResource respuestaDomain = new RespuestaResource();
    respuestaDomain.setIndTipoRespuesta("1");
    RespFacilitadaResource respFacilitadaResource = new RespFacilitadaResource();
    respuestaDomain.setRespFacilitada(respFacilitadaResource);
    respuestaDomainList.add(respuestaDomain);
    caracteristicaDomain.setRespuestas(respuestaDomainList);
    caracteristicaDomainList.add(caracteristicaDomain);

    declaracionResource.setCaracteristicas(caracteristicaDomainList);

    MetadataResource metadataResource = new MetadataResource();

    declaracionResource.setMetadata(metadataResource);

    HistDeclaracionDomainMapper mapp = new HistDeclaracionDomainMapperImpl();
    DeclaracionDomain declaracionDomain = mapp.toDomain(declaracionResource);

    assertThat(declaracionDomain).isNotNull();
  }

  @Test
  void toResource() {
    TipoMDLDomain tipoMDLDomain = new TipoMDLDomain();
    tipoMDLDomain.setCodOrigen("1");
    tipoMDLDomain.setDescOrigen("1");

    HistDeclaracionDomainMapper mapper = new HistDeclaracionDomainMapperImpl();
    DeclaracionDomain declaracionDomainTest = new DeclaracionDomain();

    declaracionDomainTest.setId("1");
    //DATO IDENTIFICATIVO
    DatoIdentificativoDomain datoIdentificativoDomain = new DatoIdentificativoDomain();
    datoIdentificativoDomain.setIdDeclaracionesODL("1");
    datoIdentificativoDomain.setIdObjAsegODL("1");
    datoIdentificativoDomain.setIdPresupuestoODL("1");
    datoIdentificativoDomain.setNumVersionPresOrigen("1");
    datoIdentificativoDomain.setNumVersionPresOrigen("1");
    datoIdentificativoDomain.setIdDeclaracionesODL("1");
    datoIdentificativoDomain.setIdMensaje("1");
    datoIdentificativoDomain.setTipoDeclaracion(tipoMDLDomain);
    //CARACTERISTICA
    List<CaracteristicaDomain> caracteristicaDomainList = new ArrayList<>();
    CaracteristicaDomain caracteristicaDomain = new CaracteristicaDomain();
    caracteristicaDomain.setPregunta(tipoMDLDomain);
    caracteristicaDomain.setIndAplicPregunta("1");
    List<RespuestaDomain> respuestaDomainList = new ArrayList<>();
    RespuestaDomain respuestaDomain = new RespuestaDomain();
    respuestaDomain.setIndTipoRespuesta("1");
    respuestaDomain.setRespFacilitada(tipoMDLDomain);
    respuestaDomainList.add(respuestaDomain);
    caracteristicaDomain.setRespuestas(respuestaDomainList);
    caracteristicaDomainList.add(caracteristicaDomain);

    MetadataDomain metadataDomain = new MetadataDomain();

    declaracionDomainTest.setDatoIdentificativo(datoIdentificativoDomain);
    declaracionDomainTest.setCaracteristicas(caracteristicaDomainList);
    declaracionDomainTest.setMetadata(metadataDomain);

    DeclaracionRequestBodyResource result = mapper.toResource(declaracionDomainTest);

    assertThat(result).isNotNull();

  }
}
