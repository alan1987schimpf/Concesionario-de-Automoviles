package com.example.Concesionario.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor                                //Reemplaza el constructor con argumentos
@NoArgsConstructor                                //Reemplaza al constructor sin argumentos
@Data                                                //Reemplaza getters y setters
public class Automovil {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String marca;
    private String modelo;
    private String patente;
    private String color;
    private String fechadefabricacion;
    private String caja;
    private String puertas;
    private String foto;

    public Automovil(String marca, String modelo, String patente, String color, String fechadefabricacion, String caja, String puertas, String foto) {
        this.marca = marca;
        this.modelo = modelo;
        this.patente = patente;
        this.color = color;
        this.fechadefabricacion = fechadefabricacion;
        this.caja = caja;
        this.puertas = puertas;
        this.foto = foto;
    }
}
