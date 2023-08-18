package com.santalucia.cdc.core.domain.securedObject;
import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class TipoComplementoDomain extends TipoMDLDomain {

    private String numOrdenComplemento;
}
