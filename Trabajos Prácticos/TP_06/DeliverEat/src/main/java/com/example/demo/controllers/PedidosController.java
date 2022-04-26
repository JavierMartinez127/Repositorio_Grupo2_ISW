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

  private Pedido pedidoNuevo = new Pedido();

  @GetMapping("carrito")
  public String GetCarrito(Model model) {
    List<DetallePedido> detales = new ArrayList<>();
    DetallePedido producto1 = new DetallePedido();
    producto1.setDescripcion("Lomo completo");
    producto1.setSubTotal(1000.0f);
    detales.add(producto1);
    DetallePedido producto2 = new DetallePedido();
    producto2.setDescripcion("Pizza");
    producto2.setSubTotal(400.0f);
    detales.add(producto2);
    pedidoNuevo.setTotal(0f);
    for (DetallePedido item : detales) {
      Float total = pedidoNuevo.getTotal() + item.getSubTotal();
      pedidoNuevo.setTotal(total);
    }

    pedidoNuevo.setDetallesPedido(detales);
    model.addAttribute("pedido", pedidoNuevo);
    return "pedidos/carrito";
  }

  @PostMapping("carrito")
  public String carritoListo(Model model) {
    model.addAttribute("pedido", pedidoNuevo);
    return "redirect:/pedidos/realizar-pedido";
  }

  @GetMapping("realizar-pedido")
  public String getPedido(Model model){

      model.addAttribute("pedido", pedidoNuevo);
      return "pedidos/pedido-paso-uno.html";
  }

  @PostMapping("realizar-pedido")
  public String getPedido(@Valid Pedido pedido, BindingResult result, Model model, RedirectAttributes attributes){

    pedidoNuevo.setCalle(pedido.getCalle()); 
    pedidoNuevo.setCiudad(pedido.getCiudad());
    pedidoNuevo.setEsLoAntesPosible(pedido.esLoAntesPosible());
    pedidoNuevo.setNumero(pedido.getNumero());
    pedidoNuevo.setEsPagoEfectivo(pedido.esPagoEnEfectivo());
    String retorno = "pedidos/pedido-paso-uno.html"; 
    model.addAttribute("pedido", pedidoNuevo);
      if(result.hasErrors()) {
        retorno = "pedidos/pedido-paso-uno.html"; 
      }
      else {
        if (pedido.esPagoEnEfectivo()) {
          retorno = "redirect:/pedidos/pago-efectivo";
        }
        else {
          retorno = "redirect:/pedidos/pago-tarjeta";
        }
      }
    return retorno;
  }

  @GetMapping("pago-efectivo")
  public String pagarEfectivo(Model model, RedirectAttributes attributes) {

    model.addAttribute("pedido", pedidoNuevo);
    return "pedidos/pago-efectivo";
  }

  @PostMapping("pago-efectivo")
  public String pagarEfectivo(@Valid Pedido pedido, BindingResult  result, Model model, RedirectAttributes attributes) {
    String retorno = "pedidos/pago-efectivo";
    if(pedido.getMontoEnEfectivo() < pedidoNuevo.getTotal() ) {
      model.addAttribute("pedido", pedidoNuevo);
      model.addAttribute("montoInvalido", true);
    }
    else {
      retorno = "pedidos/pedido-exitoso";
    }
    return retorno;
  }
  
  @GetMapping("pago-tarjeta")
  public String pagarConTarjeta(Model model, RedirectAttributes attributes) {
    model.addAttribute("pedido", new Pedido());
    model.addAttribute("tarjeta", new Tarjeta());
    return "pedidos/pago-tarjeta";
  }

  @GetMapping("pedido-cancelado")
  public String cancelarPedido(Model model, RedirectAttributes attributes) {
    return "pedidos/pedido-cancelado";
  }

  @GetMapping("pedido-exitoso")
  public String pedidoExitoso(Model model, RedirectAttributes attributes) {
    return "pedidos/pedido-exitoso";
  }

  @PostMapping("pago-tarjeta")
  public String pagarConTarjeta(@Valid Tarjeta tarjeta, BindingResult result, Model model, RedirectAttributes attributes) {
    String retorno = "pedidos/pedido-exitoso";
    if(result.hasErrors() || !esfechaVencimientoValida(tarjeta)) {
      model.addAttribute("pedido", pedidoNuevo);
      model.addAttribute("tarjeta", tarjeta);
      if(!esfechaVencimientoValida(tarjeta)) { 
        model.addAttribute("fechaInvalida", true);
      }

      retorno = "pedidos/pago-tarjeta"; 
    }
    return retorno;
  }



  public Boolean esfechaVencimientoValida(Tarjeta tarjeta) {
    Boolean esValida = false;
    if (tarjeta.getAnioVencimiento() > 22) {
      if(tarjeta.getMesVencimiento() > 0 && tarjeta.getMesVencimiento() < 13 )
      {
        esValida = true;
      }
    }
    else {
      if (tarjeta.getAnioVencimiento() == 22) {
        if(tarjeta.getMesVencimiento() > 4 && tarjeta.getMesVencimiento() < 13 )
        {
          esValida = true;
        }
        else
        {
          esValida = false;
        }
      }
    }
    
    return esValida;
  }

}
