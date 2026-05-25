package com.example.proyectomenu.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor // Constructor vacío
public class Chef extends Empleado{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idChef;
    @Positive(message = "El salario debe ser positivo")
    private double salario;

    public Chef(String nombre, String cedula, String telefono, String correo, LocalDate fechaVinculacion, double salario) {
        super(nombre, cedula, telefono, correo, fechaVinculacion);
        this.salario = salario;
    }
}
