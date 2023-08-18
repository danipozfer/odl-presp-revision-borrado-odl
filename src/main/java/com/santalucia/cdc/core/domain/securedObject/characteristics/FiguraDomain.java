package com.santalucia.cdc.core.domain.securedObject.characteristics;


import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class FiguraDomain {
    private String idPersona;
    private TipoMDLDomain tipoPersona;
    private String txtNombre;
    private String txtPrimerApellido;
    private String txtSegundoApellido;
    private String txtRazonSocial;
    private TipoMDLDomain tipoDocumento;
    private String numDocumento;
    private Instant fecNacimiento;
    private TipoMDLDomain sexo;
    private TipoMDLDomain nacionalidad;
    private TipoMDLDomain profesion;
    private TipoMDLDomain grupoProfesion;
    private String indEstadoCivil;
    private TipoMDLDomain beneficiario;
}
