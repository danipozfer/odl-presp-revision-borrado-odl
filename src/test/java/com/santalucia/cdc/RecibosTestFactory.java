package com.santalucia.cdc;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santalucia.arq.ams.odl.recibos.api.model.CollectionModelRecibosResource;
import com.santalucia.arq.ams.odl.recibos.api.model.EmbeddedCollectionRecibosResource;
import com.santalucia.arq.ams.odl.recibos.api.model.ReciboDetailResource;
import org.springframework.core.io.ClassPathResource;



public class RecibosTestFactory {
  public static CollectionModelRecibosResource getCollectionRecibos(ObjectMapper mapper) throws IOException {

    EmbeddedCollectionRecibosResource emCollection = new EmbeddedCollectionRecibosResource();
    emCollection.addRecibosItem(ReciboDetailResource01Factory(mapper));
    emCollection.addRecibosItem(ReciboDetailResource02Factory(mapper));

    CollectionModelRecibosResource colModelRecibos = new CollectionModelRecibosResource();
    colModelRecibos.setEmbedded(emCollection);
    return colModelRecibos;
  }

  private static ReciboDetailResource ReciboDetailResource01Factory(ObjectMapper mapper) throws IOException {
    return mapper.readValue(
      new ClassPathResource("/client/recibo-detail-resource-01.json").getFile(),
      ReciboDetailResource.class);
  }

  private static ReciboDetailResource ReciboDetailResource02Factory(ObjectMapper mapper) throws IOException {
    return mapper.readValue(
      new ClassPathResource("/client/recibo-detail-resource-02.json").getFile(),
      ReciboDetailResource.class);
  }

  public static CollectionModelRecibosResource getCollectionRecibosEmptyFactory() {
    EmbeddedCollectionRecibosResource emCollection = new EmbeddedCollectionRecibosResource();

    CollectionModelRecibosResource colModelRecibos = new CollectionModelRecibosResource();
    colModelRecibos.setEmbedded(emCollection);
    return colModelRecibos;
  }
}
