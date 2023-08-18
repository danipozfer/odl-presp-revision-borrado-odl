package com.santalucia.cdc.core.domain.budgets.common.identif;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class DatoIdentificativoDomain {
    private String idPresupuestoODL;
    private TipoMDLDomain tipoPresupuesto;
    private TipoMDLDomain companniaRespServ;
    private TipoMDLDomain sistemaOrigen;
    private TipoMDLDomain sistemaActual;
    private String numIdentificacion;
    private String codIdentificacion;
    private String numIdAgrupacion;
    private String codIdAgrupacion;
    private String versPresupuesto;
    private String versPresupuestoODL;
    private String indVersSeleccionada;
    private String idPolizaOrigen;
    private String idMensaje;
    private String codPackComercial;
    private String numPoliza;
    private String numPackPoliza;
    private TipoMDLDomain tipoComision;
    private String codRenovacion;
    private String indCompanniaVendedora;
    private String codOportSalesforce;
    private String codVersionComercial;
    private TipoMDLDomain periodPoliza;
    private String indOrigRecomendador;
    private String codEvento;
    private String indFormalizado;
}
