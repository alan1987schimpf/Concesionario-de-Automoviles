package com.example.Concesionario.controller;

import com.example.Concesionario.model.Automovil;
import com.example.Concesionario.service.AutomovilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/automovil")
public class AutomovilController {

    private final AutomovilService automovilService;

    @Autowired
    public AutomovilController(AutomovilService automovilService) {
        this.automovilService = automovilService;
    }

    @GetMapping("/todos")                                         //Endpoint para mostrar todos los registro.
    public List<Automovil> findAllAutomovil(){

        return automovilService.findAllAutomovil();

    }

    @PostMapping("/agregarAutomovil")                                           //Endpoint para agregar un registro.
    public Automovil agregarAutomovil(@RequestBody Automovil automovil){

        return automovilService.agregarAutomovil(automovil);

    }

    @DeleteMapping("/borrarAutomovil/{id}")                                      //Endpoint para borrar un registro.
    public void borrarAutomovil(@PathVariable("id") Long id){

        automovilService.borrarAutomovil(id);

    }

    @GetMapping("/buscarPorId/{id}")                                              //Endpoint para buscar un registro por id.
    public Automovil buscarPorId(@PathVariable("id") Long id){

        return automovilService.buscarPorId(id);

    }

    @PutMapping("/modificarAutomovil/{id}")                                            //Endpoint para modificar un registro.
    public Optional<Automovil> modificarAutomovil(@RequestBody Automovil automovil, @PathVariable Long id){
        return automovilService.modificarAutomovil(automovil, id);
    }
}
