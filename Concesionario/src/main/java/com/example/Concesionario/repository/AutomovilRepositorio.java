package com.example.Concesionario.repository;

import com.example.Concesionario.model.Automovil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutomovilRepositorio extends JpaRepository <Automovil, Long> {
}
