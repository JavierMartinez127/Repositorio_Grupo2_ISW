package com.example.demo.models;

import java.time.LocalDate;

import lombok.NoArgsConstructor;

/* Si paga con Tarjeta VISA debe ingresar el número de la tarjeta, nombre y apellido del Titular, fecha de
vencimiento (MM/AAAA) y CVC. */

@NoArgsConstructor
public class Tarjeta {

  private Long numeroDeTargeta;

  private String nombreTitular;

  private String apellidoTitular;

  private LocalDate fechaVencimiento;

  
}
