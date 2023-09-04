package com.santalucia.cdc.core.domain.declaration.com;


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
    private String idDeclaracionesODL;
    private String idObjAsegODL;
    private String idPresupuestoODL;
    private String numVersionPresOrigen;
    private String numVersionPresODL;
    private String numIdDeclPre;
    private String idMensaje;
    private TipoMDLDomain tipoDeclaracion;
}
