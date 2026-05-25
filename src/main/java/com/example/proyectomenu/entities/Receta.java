package com.example.proyectomenu.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Receta {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idReceta;
    @NotBlank(message = "El nombre de la receta no puede estar vacío.")
    private String nombreReceta;
    @NotBlank(message = "La descripción no puede estar vacía.")
    private String descripcionProceso;

    @Valid
    //Relacion N(receta) a 1(chef)
    @OneToOne(cascade = CascadeType.ALL) //cascade: Permite que se generen operaciones en cascada
    @JoinColumn(name="idChef") //Indica el nombre de la columna que será FK
    private Chef chef;

    @Valid
    //Relacion 1(receta) a N(ingredientes)
    @OneToMany(mappedBy="receta", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Ingrediente> ingredientes = new ArrayList();

    public Receta(String nombreReceta, String descripcionProceso, Chef chef) {
        this.nombreReceta = nombreReceta;
        this.descripcionProceso = descripcionProceso;
        this.chef = chef;
    }
}
