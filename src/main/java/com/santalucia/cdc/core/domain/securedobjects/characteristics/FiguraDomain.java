package com.santalucia.cdc.core.domain.securedobjects.characteristics;


import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
