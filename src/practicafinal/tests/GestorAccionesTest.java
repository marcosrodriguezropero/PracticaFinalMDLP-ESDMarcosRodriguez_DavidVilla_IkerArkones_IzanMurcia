package practicafinal.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import practicafinal.clases.*;

public class GestorAccionesTest {

    private Tablero tablero;
    private GrafoTablero grafo;
    private Unidad unidad;
    private Jugador jugador;

    @BeforeEach
    public void setup() {
        tablero = new Tablero(5,5);
        grafo = new GrafoTablero(tablero);
        jugador = new Jugador("Jugador", false);
        unidad = new Unidad.Ingeniero(jugador);
        tablero.getCasilla(0,0).colocarUnidad(unidad);
    }

    @Test
    public void testMoverUnidad() {
        boolean exito = GestorAcciones.moverUnidad(unidad, 1,1, grafo, tablero);
        assertTrue(exito);
        assertEquals(unidad, tablero.getCasilla(1,1).getUnidad());
        assertFalse(tablero.getCasilla(0,0).estaOcupada());
    }

    @Test
    public void testMoverUnidadFueraDeRango() {
        // Mover más allá del rango de movimiento de la unidad (rango 3)
        boolean exito = GestorAcciones.moverUnidad(unidad, 4,4, grafo, tablero);
        assertFalse(exito);
        assertEquals(unidad, tablero.getCasilla(0,0).getUnidad());
    }
}

