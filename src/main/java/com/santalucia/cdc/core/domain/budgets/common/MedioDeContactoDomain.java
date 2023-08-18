package com.santalucia.cdc.core.domain.budgets.common;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class MedioDeContactoDomain {
    private TipoMDLDomain medioContacto;
    private TipoMDLDomain paisOrigen;
    private String numTelefono;
    private String nomCorreoElectronico;
}
