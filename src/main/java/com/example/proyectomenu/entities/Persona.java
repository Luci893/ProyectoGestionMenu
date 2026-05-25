package com.example.proyectomenu.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor // Genera automaticamente un constructor vacio
@AllArgsConstructor // Genera automaticamente un constructor lleno
public class Persona {

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Pattern(regexp = "[a-zA-Z\\s]+",
            message = "El nombre debe contener solo letras")
    private String nombre;
    @Pattern(regexp = "\\d{10}",
            message = "La cédula debe tener 10 caracteres.")
    private String cedula;
    @Pattern(regexp = "[0-9]+",
    message = "El teléfono solo debe contener números") // regexp garantiza que la expresión que se le coloca se cumpla.
    private String telefono;
    @Email(message = "Correo inválido.")
    private String correo;

}
