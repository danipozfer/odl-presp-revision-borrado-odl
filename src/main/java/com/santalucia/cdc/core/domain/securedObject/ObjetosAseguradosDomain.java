package com.santalucia.cdc.core.domain.securedObject;

import com.santalucia.cdc.core.domain.MetadataDomain;
import com.santalucia.cdc.core.domain.securedObject.campaigns.CampannaDomain;
import com.santalucia.cdc.core.domain.securedObject.characteristics.CaracteristicaDomain;
import com.santalucia.cdc.core.domain.securedObject.identif.DatoIdentificativoDomain;
import com.santalucia.cdc.core.domain.securedObject.pricing.UnidadTarificacionDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class ObjetosAseguradosDomain {
    private String id;
    private DatoIdentificativoDomain datoIdentificativo;
    private CaracteristicaDomain caracteristica;
    private List<UnidadTarificacionDomain> unidadesTarificacion;
    private List<CampannaDomain> campannas;
    private MetadataDomain metadata;
}
