package com.example.demo.models;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Se debe indicar la dirección (calle, número, ciudad y referencia opcional en formato de texto).
● Se debe poder seleccionar la ciudad de un listado de Ciudades disponibles.
● Se debe seleccionar la forma de pago: Efectivo o Tarjeta VISA,
● Si paga en Efectivo debe indicar el monto con el que va a pagar.
● Si paga con Tarjeta VISA debe ingresar el número de la tarjeta, nombre y apellido del Titular, fecha de
vencimiento (MM/AAAA) y CVC.
● Debe ingresar cuando quiere recibirlo: “Lo antes posible” o una fecha/hora de recepción.
● El Carrito debe contener al menos un Producto del Comercio adherido.
 */

@NoArgsConstructor
@Data
public class Pedido {

  @NotNull
  @NotBlank
  @Size( max = 255)
  private String calle;

  @NotNull
  @NotBlank
  @Size( max = 255)
  private String numero;

  @NotNull
  @NotBlank
  @Size( max = 255)
  private String ciudad;

  @Size( max = 800)
  private String referencia;
  
  private Boolean esPagoEfectivo = true;

  private Boolean esLoAntesPosible = true;

  private Float montoEnEfectivo;
  private Float total;

  private List<DetallePedido> detallesPedido = new ArrayList<DetallePedido>(); 

  /**
   * Consulta si el pago del pedido se realizara en efectivo
   * @return true si es en efectivo
   */
  public Boolean esPagoEnEfectivo() {
    return this.esPagoEfectivo;
  }

  public Boolean esLoAntesPosible() {
    return this.esLoAntesPosible;
  }

}

