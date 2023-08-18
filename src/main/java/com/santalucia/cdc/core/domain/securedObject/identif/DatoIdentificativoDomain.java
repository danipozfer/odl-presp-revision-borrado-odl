package com.santalucia.cdc.core.domain.securedObject.identif;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class DatoIdentificativoDomain {
    private String idObjAsegODL;
    private String idPresupuestoODL;
    private String numOrden;
    private String numVersionPresOrigen;
    private String numVersionPresODL;
    private String idMensaje;
    private TipoMDLDomain tipoObjAseg;
    private String desLocalidadTarif;
    private String desProvinciaTarif;
    private String indCategoria;
    private String codModServicio;
    private String codTipoServicio;
    private String codSubtipoServicio;
}
