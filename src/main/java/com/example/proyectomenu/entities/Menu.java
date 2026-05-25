package com.example.proyectomenu.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor // Constructor vacío
public class Menu {

    @Id //Marcamos la primary key de nuestra Base de Datos
    @GeneratedValue(strategy= GenerationType.IDENTITY) //Indicamos que el ID se genere de forma automática
    private Long idMenu;

    @Valid
    //Relacion 1(menu) a N(alimentos)
    @OneToMany(mappedBy="menu", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Alimento> alimentos = new ArrayList();

    @Valid
    //Relacion N(menus) a 1(gerente)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idGerente") //Indica el nombre de la columna que será FK
    private Gerente miGerente;

    public Menu(Gerente miGerente) {
        this.miGerente = miGerente;
    }
}
