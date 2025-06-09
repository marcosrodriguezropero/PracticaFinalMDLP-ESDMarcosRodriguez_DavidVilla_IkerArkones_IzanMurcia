package practicafinal.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import practicafinal.clases.*;

public class JugadorTest {

    @Test
    public void testConstructorYGetters() {
        Jugador j1 = new Jugador("JugadorTest", true);
        assertEquals("JugadorTest", j1.getNombre());
        assertTrue(j1.esIA());

        Jugador j2 = new Jugador("Humano", false);
        assertEquals("Humano", j2.getNombre());
        assertFalse(j2.esIA());
    }
}

