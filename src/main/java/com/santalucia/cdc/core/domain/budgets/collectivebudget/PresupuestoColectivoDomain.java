package com.santalucia.cdc.core.domain.budgets.collectivebudget;

import com.santalucia.cdc.core.domain.MetadataDomain;
import com.santalucia.cdc.core.domain.budgets.common.campaigns.CampannaDomain;
import com.santalucia.cdc.core.domain.budgets.common.commercial.EstructuraComercialDomain;
import com.santalucia.cdc.core.domain.budgets.common.dateandstate.FechaYEstadoDomain;
import com.santalucia.cdc.core.domain.budgets.common.figure.FiguraDomain;
import com.santalucia.cdc.core.domain.budgets.common.geograph.EstructuraGeograficaDomain;
import com.santalucia.cdc.core.domain.budgets.common.identif.DatoIdentificativoDomain;
import com.santalucia.cdc.core.domain.budgets.common.payment.DatoCobroDomain;
import com.santalucia.cdc.core.domain.budgets.common.product.ProductoDomain;
import com.santalucia.cdc.core.domain.budgets.common.securedobject.ObjetosAseguradosDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PresupuestoColectivoDomain {
    private String id;
    private DatoIdentificativoDomain datoIdentificativo;
    private ProductoDomain producto;
    private FechaYEstadoDomain fechaYEstado;
    private DatoCobroDomain datoCobro;
    private FiguraDomain figura;
    private EstructuraComercialDomain estructuraComercial;
    private EstructuraGeograficaDomain estructuraGeografica;
    private List<CampannaDomain> campannas;
    private List<ObjetosAseguradosDomain> objetosAsegurados;
    private MetadataDomain metadata;
}
