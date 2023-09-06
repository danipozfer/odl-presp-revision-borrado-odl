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
public class AnimalDomain {
    private String indTipoEspecie;
    private TipoMDLDomain raza;
    private String indTipoAnimalComp;
    private String numIdentAnimalComp;
    private String nomMascota;
    private String numChip;
    private Instant fecNacimiento;
    private double impValorMascota;
    private String indPerroMestizo;
    private String indPerfEstadoSalud;
}
