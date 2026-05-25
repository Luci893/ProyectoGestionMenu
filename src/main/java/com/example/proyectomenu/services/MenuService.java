package com.example.proyectomenu.services;

import com.example.proyectomenu.entities.Alimento;
import com.example.proyectomenu.entities.Ingrediente;
import com.example.proyectomenu.entities.Menu;
import com.example.proyectomenu.entities.Receta;
import com.example.proyectomenu.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Transactional
    public Menu save(Menu menu) {

        for (Alimento alimento : menu.getAlimentos()){
            alimento.setMenu(menu);

            if(alimento.getReceta() != null){
                Receta receta = alimento.getReceta();

                for (Ingrediente ingrediente : receta.getIngredientes()){
                    ingrediente.setReceta(receta);
                }
            }
        }

        return menuRepository.save(menu);
    }

    @Transactional
    public boolean delete(Long id){
        if(menuRepository.existsById(id)){
            menuRepository.deleteById(id);
            return true;
        }else{
            throw new RuntimeException("No se encontró el menu.");
        }
    }

    @Transactional(readOnly = true)
    public List<Menu> findAll(){
        return menuRepository.findAll();
    }

}
