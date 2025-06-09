package practicafinal.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import practicafinal.clases.*;

public class BuscadorMovimientoTest {

    private Tablero tablero;
    private GrafoTablero grafo;
    private Unidad unidad;

    @BeforeEach
    public void setup() {
        int filas = 3;
        int columnas = 3;
        tablero = new Tablero(filas, columnas);
        grafo = new GrafoTablero(filas, columnas, tablero);
        Jugador jugador = new Jugador("Jugador", false);
        unidad = new Unidad.Ingeniero(jugador);
        tablero.getCasilla(1, 1).colocarUnidad(unidad);
    }

    @Test
    public void testObtenerCasillasAlcanzables() {
        ListaEnlazada<Grafo> alcanzables = BuscadorMovimiento.obtenerCasillasAlcanzables(grafo, unidad);
        assertNotNull(alcanzables);
        assertTrue(alcanzables.tamanio() > 0);

        boolean contieneActual = false;
        for (int i = 0; i < alcanzables.tamanio(); i++) {
            Grafo nodo = alcanzables.obtener(i);
            if (nodo.getFila() == 1 && nodo.getColumna() == 1) {
                contieneActual = true;
                break;
            }
        }
        assertTrue(contieneActual);
    }
}
