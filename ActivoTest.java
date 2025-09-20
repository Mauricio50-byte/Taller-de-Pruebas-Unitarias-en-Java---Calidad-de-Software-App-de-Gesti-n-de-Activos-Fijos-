package com.miApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

// Ajustamos estas inportaciones segun tu estructura de paquetes
import com.miApp.Activo;
import com.miApp.FechaDeCompra;

/**
 * EJEMPLO DE PRUEBA CON FALLO INTENCIONAL
 * 
 * Este archivo muestra cómo simular un fallo en las pruebas.
 * Para usarlo:
 * 1. Renombrar este archivo a ActivoTest.java
 * 2. Reemplazar el archivo original en src/test/java/com/miApp/
 * 3. Ejecutar mvn clean test
 * 4. Observar que las pruebas fallan
 * 5. Restaurar el archivo original para que las pruebas pasen
 */
@DisplayName("Pruebas Unitarias para la clase Activo - CON FALLOS INTENCIONALES")
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
    @DisplayName("Test de creacion de activo - CON FALLO INTENCIONAL")
    void testCreacionActivo() {
        // JUnit 5 assertions
        assertNotNull(activo);
        
        // FALLO INTENCIONAL: Esperamos un número diferente
        assertEquals(9999, activo.getNumeroDeActivo()); // Debería ser 1001, pero ponemos 9999
        
        // FALLO INTENCIONAL: Esperamos una descripción diferente
        assertEquals("Computador de escritorio HP", activo.getDescripcion()); // Debería ser "Computador portatil Dell"
        
        // AssertJUnit assertions mas fluidas
        assertThat(activo.getValorHistorico()).isGreaterThan(0);
        assertThat(activo.getDescripcion()).contains("Computador");
    }

    @Test
    @DisplayName("Test de validaciones - CON FALLO INTENCIONAL")
    void testValidaciones() {
        assertThat(activo)
            .isNotNull()
            .extracting(
                Activo::getNumeroDeActivo,
                Activo::getDescripcion,
                Activo::getValorHistorico
            )
            .containsExactly(
                2002, // FALLO INTENCIONAL: Debería ser 1001
                "Impresora Canon", // FALLO INTENCIONAL: Debería ser "Computador portatil Dell"
                3000000.0 // FALLO INTENCIONAL: Debería ser 2500000.0
            );
    }
}