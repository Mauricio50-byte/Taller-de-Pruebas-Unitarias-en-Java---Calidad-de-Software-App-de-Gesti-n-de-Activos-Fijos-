package com.miApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

// Ajustamos estas inportaciones segun tu estructura de paquetes
import com.miApp.Activo;
import com.miApp.FechaDeCompra;

@DisplayName("Pruebas Unitarias para la clase Activo")
public class ActivoTest {

    private Activo activo;
    private FechaDeCompra fechaDeCompra;

    @BeforeEach
    void setUp() {
        fechaDeCompra = new FechaDeCompra(2023, 5, 15);
        activo = new Activo(
            1001,
            "Computador portatil Dell",
            fechaDeCompra,
            "12345678",
            "Juan Perez",
            2500000.0,
            2000000.0,
            "Oficina 205"
        );
    }

    @Test
    @DisplayName("Test de creacion de activo")
    void testCreacionActivo() {
        // JUnit 5 assertions
        assertNotNull(activo);
        assertEquals(1001, activo.getNumeroDeActivo());
        assertEquals("Computador portatil Dell", activo.getDescripcion());
        
        // AssertJUnit assertions mas fluidas
        assertThat(activo.getValorHistorico()).isGreaterThan(0);
        assertThat(activo.getDescripcion()).contains("Computador");
    }

    @Test
    @DisplayName("Test de validaciones")
    void testValidaciones() {
        assertThat(activo)
            .isNotNull()
            .extracting(
                Activo::getNumeroDeActivo,
                Activo::getDescripcion,
                Activo::getValorHistorico
            )
            .containsExactly(
                1001,
                "Computador portatil Dell", 
                2500000.0
            );
    }
}