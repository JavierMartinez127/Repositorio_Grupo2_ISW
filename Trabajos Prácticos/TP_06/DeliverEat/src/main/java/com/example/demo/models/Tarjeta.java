package com.example.demo.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

/* Si paga con Tarjeta VISA debe ingresar el número de la tarjeta, nombre y apellido del Titular, fecha de
vencimiento (MM/AAAA) y CVC. */

@NoArgsConstructor
@Data
public class Tarjeta {

  @NotBlank
  @NotNull
  @Pattern(regexp = "^(?:4[0-9]{12}(?:[0-9]{3})?)$", message = "Numero Invalido o no corresponde a una tarjeta VISA")
  private String numero;

  @NotBlank
  @NotNull
  @Size(max = 16)
  private String nombreTitular;

  @NotBlank
  @NotNull
  @Size(max = 16)
  private String apellidoTitular;

  @NotBlank
  @NotNull
  @Pattern(regexp = "^[0-9]{2}$",  message = "Numero Invalido")
  private String mesVencimiento;

  @NotBlank
  @NotNull
  @Pattern(regexp = "^[0-9]{2}$", message = "Numero Invalido")
  private String anioVencimiento;

  @NotBlank
  @NotNull
  @Pattern(regexp = "^[0-9]{3}$", message = "Numero Invalido")
  private String  codigoSeguridad;

}
