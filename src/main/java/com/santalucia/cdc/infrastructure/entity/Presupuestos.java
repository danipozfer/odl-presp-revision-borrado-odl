package com.santalucia.cdc.infrastructure.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Presupuestos {
  private Long id;
  private String nombre;
  private String apellido;
}
