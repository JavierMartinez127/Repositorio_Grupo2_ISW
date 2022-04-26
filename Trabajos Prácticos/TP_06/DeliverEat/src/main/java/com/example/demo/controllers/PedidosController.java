package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.example.demo.models.Pedido;
import com.example.demo.models.DetallePedido;
import com.example.demo.models.Tarjeta;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidosController {

  public final Pedido pedido = new Pedido();

  @GetMapping("carrito")
  public String GetCarrito(Model model) {
    this.pedido.setCalle("");
    this.pedido.setCiudad("");
    this.pedido.setEsLoAntesPosible(true);
    this.pedido.setNumero("");
    this.pedido.setEsPagoEfectivo(true);
    this.pedido.setReferencia("");
this.pedido.setFecha("");
this.pedido.setHora("");
    List<DetallePedido> detales = new ArrayList<>();
    DetallePedido producto1 = new DetallePedido();
    producto1.setDescripcion("Lomo completo");
    producto1.setSubTotal(1000.0f);
    detales.add(producto1);
    DetallePedido producto2 = new DetallePedido();
    producto2.setDescripcion("Pizza");
    producto2.setSubTotal(400.0f);
    detales.add(producto2);
    /**
     *
     DetallePedido producto3 = new DetallePedido();
    producto3.setDescripcion("x");
    producto2.setSubTotal(0.0f);
    detales.add(producto3);
     * 
     * 
     */
    this.pedido.setTotal(0f);
    for (DetallePedido item : detales) {
      Float total = this.pedido.getTotal() + item.getSubTotal();
      this.pedido.setTotal(total);
    }

    this.pedido.setDetallesPedido(detales);
    model.addAttribute("pedido", this.pedido);
    return "pedidos/carrito";
  }

  @PostMapping("carrito")
  public String carritoListo(Model model) {
    model.addAttribute("pedido", this.pedido);
    return "redirect:/pedidos/realizar-pedido";
  }

  @GetMapping("realizar-pedido")
  public String getPedido(Model model) {

    model.addAttribute("pedido", this.pedido);
    return "pedidos/pedido-paso-uno.html";
  }

  @PostMapping("realizar-pedido")
  public String getPedido(@Valid Pedido pedido, BindingResult result, Model model, RedirectAttributes attributes) {

    this.pedido.setCalle(pedido.getCalle());
    this.pedido.setCiudad(pedido.getCiudad());
    this.pedido.setEsLoAntesPosible(pedido.esLoAntesPosible());
    this.pedido.setNumero(pedido.getNumero());
    this.pedido.setEsPagoEfectivo(pedido.esPagoEnEfectivo());
    this.pedido.setReferencia(pedido.getReferencia());
this.pedido.setFecha(pedido.getFecha());
this.pedido.setHora(pedido.getHora());

    String retorno = "pedidos/pedido-paso-uno.html";
    model.addAttribute("pedido", this.pedido);
    if (result.hasErrors()) {
      retorno = "pedidos/pedido-paso-uno.html";
    } else {
      if (pedido.esPagoEnEfectivo()) {
        retorno = "redirect:/pedidos/pago-efectivo";
      } else {
        retorno = "redirect:/pedidos/pago-tarjeta";
      }
    }
    return retorno;
  }

  @GetMapping("pago-efectivo")
  public String pagarEfectivo(Model model, RedirectAttributes attributes) {

    model.addAttribute("pedido", this.pedido);
    return "pedidos/pago-efectivo";
  }

  @PostMapping("pago-efectivo")
  public String pagarEfectivo(@Valid Pedido pedido, BindingResult result, Model model, RedirectAttributes attributes) {
    String retorno = "pedidos/pago-efectivo";
    if (pedido.getMontoEnEfectivo() < this.pedido.getTotal()) {
      model.addAttribute("pedido", this.pedido);
      model.addAttribute("montoInvalido", true);
    } else {
      this.pedido.setMontoEnEfectivo(pedido.getMontoEnEfectivo());
      model.addAttribute("pedido", this.pedido);
      retorno = "redirect:/pedidos/pedido-exitoso";
    }
    return retorno;
  }

  @GetMapping("pago-tarjeta")
  public String pagarConTarjeta(Model model, RedirectAttributes attributes) {
    model.addAttribute("pedido", this.pedido);
    model.addAttribute("tarjeta", new Tarjeta());
    return "pedidos/pago-tarjeta";
  }

  @PostMapping("pago-tarjeta")
  public String pagarConTarjeta(@Valid Tarjeta tarjeta, BindingResult result, Model model,
      RedirectAttributes attributes) {
    String retorno = "redirect:/pedidos/pedido-exitoso";
    if (result.hasErrors() || !esfechaVencimientoValida(tarjeta)) {
      model.addAttribute("pedido", this.pedido);
      model.addAttribute("tarjeta", tarjeta);
      if (!esfechaVencimientoValida(tarjeta)) {
        model.addAttribute("fechaInvalida", true);
      }

      retorno = "pedidos/pago-tarjeta";
    }
    return retorno;
  }

  @GetMapping("pedido-cancelado")
  public String cancelarPedido(Model model, RedirectAttributes attributes) {
    return "pedidos/pedido-cancelado";
  }

  @GetMapping("pedido-exitoso")
  public String pedidoExitoso(Model model, RedirectAttributes attributes) {
    model.addAttribute("pedido", this.pedido);
    return "pedidos/pedido-exitoso";
  }

  

  public Boolean esfechaVencimientoValida(Tarjeta tarjeta) {
    Boolean esValida = false;
    if (tarjeta.getAnioVencimiento() > 22) {
      if (tarjeta.getMesVencimiento() > 0 && tarjeta.getMesVencimiento() < 13) {
        esValida = true;
      }
    } else {
      if (tarjeta.getAnioVencimiento() == 22) {
        if (tarjeta.getMesVencimiento() > 4 && tarjeta.getMesVencimiento() < 13) {
          esValida = true;
        } else {
          esValida = false;
        }
      }
    }

    return esValida;
  }

}
