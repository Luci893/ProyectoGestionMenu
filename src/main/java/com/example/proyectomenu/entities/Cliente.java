package com.example.proyectomenu.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // Constructor vacío
public class Cliente extends Persona{
    private String usuario;
    private String contrasenia;

    public Cliente(String nombre, String cedula, String telefono, String correo, String usuario, String contrasenia) {
        super(nombre, cedula, telefono, correo);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }
}
