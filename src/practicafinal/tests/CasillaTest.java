package practicafinal.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import practicafinal.clases.*;

public class CasillaTest {

    @Test
    public void testColocarYQuitarUnidad() {
        Casilla c = new Casilla(1, 2, 1, 0);
        Jugador j = new Jugador("Ana", false);
        Unidad u = new Unidad.Matematico(j);

        assertFalse(c.estaOcupada());

        c.colocarUnidad(u);
        assertTrue(c.estaOcupada());
        assertEquals(u, c.getUnidad());
        assertEquals(1, u.getFila());
        assertEquals(2, u.getColumna());

        c.quitarUnidad();
        assertFalse(c.estaOcupada());
        assertNull(c.getUnidad());
    }
}

