package com.example.Concesionario.service;

import com.example.Concesionario.exception.AutomovilNotFoundException;
import com.example.Concesionario.model.Automovil;
import com.example.Concesionario.repository.AutomovilRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class AutomovilServiceTest {

    @Mock
    private AutomovilRepositorio automovilRepositorio;
    private AutomovilService testedAutomovilService;             //se genera un service testedAutomovilService

    @BeforeEach                                                  //antes de cada test se ejecuta esta accion
    void setUp(){

        System.out.println("Esto se ejecuta antes de cada test");
        testedAutomovilService = new AutomovilService(automovilRepositorio);

    }

    @Test
    void findAllAutomovilTest(){

        testedAutomovilService.findAllAutomovil();             //llamo a findAllAutomovil desde el servicio
        verify(automovilRepositorio).findAll();              //verifico que desde el repositorio se llama a findAll

    }

    @Test
    void agregarAutomovilTest(){

        Automovil automovil = new Automovil(33L, "fiat", "argo", "TYR352",
                "blanco", "25/07/2006", "manual", "3",
                "https://th.bing.com/th/id/R.72853f0aa8c333c58308083380" +
                        "b77d31?rik=KPxFuLHlNpwbIQ&pid=ImgRaw&r=0" );                                  //genero un nuevo automovil
        when(automovilRepositorio.save(any(Automovil.class))).thenReturn(automovil);                 //cuando desde el automovilrepositorio agrego una pelicula
        assertNotNull(testedAutomovilService.agregarAutomovil(new Automovil()));                      //nos aseguramos que lo que se devuelve no esta vacio

    }

    @Test
    void buscarPorIdTest(){

        Long automovilId = 33L;                                                                                //generamos un id
        Automovil mockedAutomovil = new Automovil(automovilId, "audi", "TT",
                "AF698GT", "blanco", "15/06/2020", "automatica 6V",
                "3", "https://th.bing.com/th/id/R.212c0bb751425c29a85aeb6f67009cd5?rik" +
                "=BkyZNWaOluyrAg&pid=ImgRaw&r=0");
        doReturn(Optional.of(mockedAutomovil)).when(automovilRepositorio).findById(automovilId);               //se mockea una pelicula
        Automovil automovilByService = testedAutomovilService.buscarPorId(automovilId);                        //se trae otro automovil

        assertNotNull(automovilByService, "automovilByService with automovilId: " + automovilId + "no se encuentra");
        assertEquals(automovilId, automovilByService.getId());
        assertEquals(mockedAutomovil.getMarca(), automovilByService.getMarca());
        assertEquals(mockedAutomovil.getModelo(), automovilByService.getModelo());
        assertEquals(mockedAutomovil.getPatente(), automovilByService.getPatente());
        assertEquals(mockedAutomovil.getColor(), automovilByService.getColor());
        assertEquals(mockedAutomovil.getFechadefabricacion(), automovilByService.getFechadefabricacion());
        assertEquals(mockedAutomovil.getCaja(), automovilByService.getCaja());
        assertEquals(mockedAutomovil.getPuertas(), automovilByService.getPuertas());
        assertEquals(mockedAutomovil.getFoto(), automovilByService.getFoto());

    }

    @Test
    void buscarPorIdFailedTest(){

        Long wrongAutomovilId = 33L;
        Exception exception = assertThrows(AutomovilNotFoundException.class, () -> {

            testedAutomovilService.buscarPorId(wrongAutomovilId);

        });

        String expectedMessage = "No se puede encontrar la pelicula con el ID: " + wrongAutomovilId;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
}