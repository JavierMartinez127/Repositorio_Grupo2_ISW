package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("pedidos")
@RequiredArgsConstructor
public class PedidosController {
    
    @GetMapping("index")
    public String GetPedido(){
        return "pedidos/index";
    }

    @GetMapping("carrito")
    public String GetCarrito(){
        return "pedidos/carrito";
    }
}
