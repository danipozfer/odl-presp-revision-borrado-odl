package com.santalucia.cdc.core.domain.securedobjects.identif;

import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
