package com.example.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DetallePedido {
    private String descripcion;
    private Float subTotal;

}