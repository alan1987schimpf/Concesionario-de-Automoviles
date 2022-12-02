package com.example.Concesionario.exception;

public class AutomovilNotFoundException extends RuntimeException{

    public AutomovilNotFoundException(Long id){

        super("No se puede encontrar la pelicula con el ID: " + id);

    }
}
