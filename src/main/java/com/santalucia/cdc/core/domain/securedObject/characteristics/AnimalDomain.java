package com.santalucia.cdc.core.domain.securedObject.characteristics;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
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
