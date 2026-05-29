package com.example.proyectomenu.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Pedido {

    private List<Cliente> clientes = new ArrayList();
    private List<Mesero> meseros = new ArrayList();
    private List<Alimento> alimentosAdquiridos = new ArrayList();
    private LocalDate fechaPedido;
    private Time horaPedido;
    private double precioTotalPedido;
    private boolean estado;

    public Pedido(LocalDate fechaPedido, Time horaPedido, double precioTotalPedido, boolean estado) {
        this.fechaPedido = fechaPedido;
        this.horaPedido = horaPedido;
        this.precioTotalPedido = precioTotalPedido;
        this.estado = estado;
    }
}
