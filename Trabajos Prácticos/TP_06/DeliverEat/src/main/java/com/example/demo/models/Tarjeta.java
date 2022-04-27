package com.example.demo.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
  @Size(max = 32)
  private String nombreTitular;

  // @NotBlank
  // @NotNull
  // @Size(max = 16)
  // private String apellidoTitular;

  @NotNull
  @Min(1)
  @Max(12)
  private Integer mesVencimiento;

  @NotNull
  @Min(22)
  @Max(99)
  private Integer anioVencimiento;

  @NotBlank
  @NotNull
  @Pattern(regexp = "^[0-9]{3}$", message = "Numero Invalido")
  private String  codigoSeguridad;

}
