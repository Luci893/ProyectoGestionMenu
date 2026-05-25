package com.example.proyectomenu.controllers;

import com.example.proyectomenu.entities.Menu;
import com.example.proyectomenu.services.MenuService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*") // Permite el acceso a la API desde distintos origenes o clientes
@RequestMapping(path= "/api/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Menu menu, BindingResult result) {
        try{
            // Verifica si hubo errores de validación
            if(result.hasErrors()){
                Map<String, String> errores = new HashMap<>();

                // Obtiene todos los errores de los campos, devolviendo nombre del atributo y su mensaje.
                result.getFieldErrors().forEach(error -> { errores.put(
                            error.getField(),
                            error.getDefaultMessage()
                    );
                });

                return ResponseEntity
                        .badRequest()
                        .body(errores);
            }
            // Guarda un menu y muestra un status OK si se guardo correctamente.
            return ResponseEntity.status(HttpStatus.CREATED).body(menuService.save(menu));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    // Trae todas los elementos en formato JSON
    public ResponseEntity<?> getAll() {
        try{
            // Devuelve lista de menus con un status OK si hay en la BD.
            return ResponseEntity.ok(menuService.findAll());
        } catch (Exception e) {
            // Si no hay elementos retorna status No encontrado con un mensaje en formato JSON
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            // Eliminar un elemento.
            menuService.delete(id);
            return ResponseEntity.noContent().build(); // build crea la respuesta HTTP vacia
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }

}
