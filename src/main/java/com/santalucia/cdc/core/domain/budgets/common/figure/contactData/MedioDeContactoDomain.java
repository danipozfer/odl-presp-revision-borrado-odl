package com.santalucia.cdc.core.domain.budgets.common.figure.contactData;

import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedioDeContactoDomain {
    private TipoMDLDomain medioContacto;
    private TipoMDLDomain paisOrigen;
    private String numTelefono;
    private String nomCorreoElectronico;
}
