package com.santalucia.cdc.core.domain.securedobjects;

import com.santalucia.cdc.core.domain.MetadataDomain;
import com.santalucia.cdc.core.domain.securedobjects.campaigns.CampannaDomain;
import com.santalucia.cdc.core.domain.securedobjects.characteristics.CaracteristicaDomain;
import com.santalucia.cdc.core.domain.securedobjects.identif.DatoIdentificativoDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.UnidadTarificacionDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObjetosAseguradosDomain {
    private String id;
    private DatoIdentificativoDomain datoIdentificativo;
    private CaracteristicaDomain caracteristica;
    private List<UnidadTarificacionDomain> unidadesTarificacion;
    private List<CampannaDomain> campannas;
    private MetadataDomain metadata;
}
