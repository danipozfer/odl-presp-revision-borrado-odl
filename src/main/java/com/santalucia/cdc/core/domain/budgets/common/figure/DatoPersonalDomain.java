package com.santalucia.cdc.core.domain.budgets.common.figure;

import com.santalucia.cdc.core.domain.budgets.common.figure.contactData.DomicilioPersDomain;
import com.santalucia.cdc.core.domain.budgets.common.figure.contactData.MedioDeContactoDomain;
import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatoPersonalDomain {
    private TipoMDLDomain tipoRol;
    private String numOrdenRol;
    private String numIdPersona;
    private String numIdCliente;
    private TipoMDLDomain tipoPersona;
    private TipoMDLDomain tipoDocumento;
    private String numDocumento;
    private TipoMDLDomain sexoPersona;
    private String txtNombre;
    private String txtPrimerApellido;
    private String txtSegundoApellido;
    private String txtRazonSocial;
    private Instant fecNacimiento;
    private TipoMDLDomain profesion;
    private TipoMDLDomain agrupProfesion;
    private TipoMDLDomain nacionalidad;
    private String indEstadoCivil;
    private DomicilioPersDomain domicilioPers;
    private List<MedioDeContactoDomain> mediosDeContactos;
}
