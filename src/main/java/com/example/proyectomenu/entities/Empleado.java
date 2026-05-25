package com.example.proyectomenu.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor // Genera automaticamente un constructor vacio
public class Empleado extends Persona{

    @NotNull(message = "La fecha no puede ser nula.")
    @PastOrPresent(message = "La fecha debe ser anterior o presente de la fecha actual.")
    private LocalDate fechaVinculacion;

    public Empleado(String nombre, String cedula, String telefono, String correo, LocalDate fechaVinculacion) {
        super(nombre, cedula, telefono, correo);
        this.fechaVinculacion = fechaVinculacion;
    }
}
