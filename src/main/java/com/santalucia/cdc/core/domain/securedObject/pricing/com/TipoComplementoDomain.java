package com.santalucia.cdc.core.domain.securedObject.pricing.com;
import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoComplementoDomain extends TipoMDLDomain {

    private String numOrdenComplemento;
}
