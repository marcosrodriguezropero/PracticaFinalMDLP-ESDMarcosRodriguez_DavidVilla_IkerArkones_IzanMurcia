package practicafinal.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import practicafinal.clases.*;

public class ListaEnlazadaTest {

    @Test
    public void testAgregarYObtener() {
        ListaEnlazada<String> lista = new ListaEnlazada<>();
        lista.agregarFinal("uno");
        lista.agregarFinal("dos");

        assertEquals(2, lista.tamanio());
        assertEquals("uno", lista.obtener(0));
        assertEquals("dos", lista.obtener(1));
    }

    @Test
    public void testEliminarYContiene() {
        ListaEnlazada<String> lista = new ListaEnlazada<>();
        lista.agregarFinal("uno");
        lista.agregarFinal("dos");

        assertTrue(lista.eliminar("uno"));
        assertFalse(lista.contiene("uno"));
        assertTrue(lista.contiene("dos"));
        assertEquals(1, lista.tamanio());

        assertFalse(lista.eliminar("tres"));
    }
}

