package com.example.demo.controllers;

import com.example.demo.models.Pedido;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidosController {
    
    @GetMapping("index")
    public String GetPedido(Model model){

        model.addAttribute("pedido", new Pedido());
        return "pedidos/index";
    }

    @GetMapping("carrito")
    public String GetCarrito(){
        return "pedidos/carrito";
    }
}
