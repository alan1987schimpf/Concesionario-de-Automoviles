package com.example.Concesionario.service;

import com.example.Concesionario.exception.AutomovilNotFoundException;
import com.example.Concesionario.model.Automovil;
import com.example.Concesionario.repository.AutomovilRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AutomovilService {

    private final AutomovilRepositorio automovilRepositorio;

    @Autowired
    public AutomovilService(AutomovilRepositorio automovilRepositorio) {
        this.automovilRepositorio = automovilRepositorio;
    }

    public List<Automovil> findAllAutomovil(){

        return automovilRepositorio.findAll();

    }

    public Automovil agregarAutomovil(Automovil automovil) {

        return automovilRepositorio.save(automovil);

    }

    public void borrarAutomovil(Long id) {

        Optional<Automovil> automovilBorrado = automovilRepositorio.findById(id);
        if(automovilBorrado.isEmpty()){

            throw new AutomovilNotFoundException(id);

        }else{

            automovilRepositorio.deleteById(id);

        }
    }

    public Automovil buscarPorId(Long id) {

        return automovilRepositorio.findById(id).orElseThrow(() -> new AutomovilNotFoundException(id));

    }
    public Optional<Automovil> modificarAutomovil(Automovil nuevoAutomovil, Long id) {
        Optional<Automovil> automovilModificado = automovilRepositorio.findById(id);
        if ((automovilModificado.isEmpty())) {
            throw new AutomovilNotFoundException(id);
        } else {
            return automovilModificado.map(automovil -> {
                automovil.setMarca(nuevoAutomovil.getMarca());
                automovil.setModelo(nuevoAutomovil.getModelo());
                automovil.setPatente(nuevoAutomovil.getPatente());
                automovil.setColor(nuevoAutomovil.getColor());
                automovil.setFechadefabricacion(nuevoAutomovil.getFechadefabricacion());
                automovil.setCaja(nuevoAutomovil.getCaja());
                automovil.setPuertas(nuevoAutomovil.getPuertas());
                automovil.setFoto(nuevoAutomovil.getFoto());
                return automovilRepositorio.save(automovil);
            });
        }
    }
}
