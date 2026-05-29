package com.example.proyectomenu.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor // Constructor vacío
public class Mesero extends Empleado {

    private double salario;

    public Mesero(String nombre, String cedula, String telefono, String correo, LocalDate fechaVinculacion, double salario) {
        super(nombre, cedula, telefono, correo, fechaVinculacion);
        this.salario = salario;
    }
}
