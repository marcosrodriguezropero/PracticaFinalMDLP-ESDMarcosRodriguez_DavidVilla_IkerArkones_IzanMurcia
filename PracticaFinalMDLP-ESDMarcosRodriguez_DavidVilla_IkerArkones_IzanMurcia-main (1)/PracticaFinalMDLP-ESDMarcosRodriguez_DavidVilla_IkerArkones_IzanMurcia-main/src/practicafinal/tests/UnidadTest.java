package practicafinal.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import practicafinal.clases.Unidad;
import practicafinal.clases.Jugador;


public class UnidadTest {

    @Test
    public void testUnidadCreacionYEstado() {
        Jugador j = new Jugador("Juan", false);
        Unidad u = new Unidad.Ingeniero(j);

        assertEquals("Ingeniero", u.getNombre());
        assertTrue(u.estaViva());
        assertEquals(j, u.getPropietario());

        u.setFila(2);
        u.setColumna(3);
        assertEquals(2, u.getFila());
        assertEquals(3, u.getColumna());
    }
}
