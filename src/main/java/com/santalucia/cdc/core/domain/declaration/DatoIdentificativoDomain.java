package com.santalucia.cdc.core.domain.declaration;


import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
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
