package practicafinal.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import practicafinal.clases.Tablero;
import practicafinal.clases.GrafoTablero;
import practicafinal.clases.Unidad;
import practicafinal.clases.Jugador;
import practicafinal.clases.ControladorJuego;

public class ControladorJuegoTest {

    private ControladorJuego controlador;
    private Tablero tablero;
    private GrafoTablero grafo;
    private Jugador jugador1;
    private Jugador jugador2;
    private Unidad unidad;

    @BeforeEach
    public void setup() {
        tablero = new Tablero(5,5);
        grafo = new GrafoTablero(tablero);

        jugador1 = new Jugador("Jugador 1", false);
        jugador2 = new Jugador("Jugador 2", false);

        controlador = new ControladorJuego(tablero, grafo, jugador1, jugador2);

        unidad = new Unidad.Ingeniero(jugador1);
        tablero.getCasilla(0,0).colocarUnidad(unidad);
    }

    @Test
    public void testSeleccionarUnidad() {
        controlador.seleccionarUnidad(0,0);
        assertEquals(unidad, controlador.getUnidadSeleccionada());
    }

    @Test
    public void testMoverUnidadSeleccionada() {
        controlador.seleccionarUnidad(0,0);
        boolean exito = controlador.moverUnidadSeleccionada(1,1);
        assertTrue(exito);
        assertNull(controlador.getUnidadSeleccionada()); // tras mover se deselecciona
        assertEquals(unidad, tablero.getCasilla(1,1).getUnidad());
        assertFalse(tablero.getCasilla(0,0).estaOcupada());
    }

    @Test
    public void testMoverUnidadNoSeleccionada() {
        boolean exito = controlador.moverUnidadSeleccionada(1,1);
        assertFalse(exito);
    }
}


