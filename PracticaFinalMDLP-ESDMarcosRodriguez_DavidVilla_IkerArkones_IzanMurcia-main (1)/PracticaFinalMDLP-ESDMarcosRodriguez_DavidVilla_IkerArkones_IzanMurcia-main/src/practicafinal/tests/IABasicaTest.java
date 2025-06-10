package practicafinal.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import practicafinal.clases.*;

public class IABasicaTest {

    private Tablero tablero;
    private Jugador jugadorIA;
    private Jugador jugadorHumano;
    private IABasica ia;

    @BeforeEach
    public void setup() {
        jugadorIA = new Jugador("IA", true);
        jugadorHumano = new Jugador("Humano", false);

        tablero = new Tablero(5, 5);

        // Crear unidades IA
        Unidad ingenieroIA = new Unidad.Ingeniero(jugadorIA);
        Unidad matematicoIA = new Unidad.Matematico(jugadorIA);

        // Crear unidades humano
        Unidad poetaHumano = new Unidad.Poeta(jugadorHumano);
        Unidad filologoHumano = new Unidad.Filologo(jugadorHumano);

        // Posicionar unidades en el tablero
        tablero.getCasilla(0, 0).colocarUnidad(ingenieroIA);
        ingenieroIA.setFila(0);
        ingenieroIA.setColumna(0);

        tablero.getCasilla(0, 1).colocarUnidad(matematicoIA);
        matematicoIA.setFila(0);
        matematicoIA.setColumna(1);

        tablero.getCasilla(4, 4).colocarUnidad(poetaHumano);
        poetaHumano.setFila(4);
        poetaHumano.setColumna(4);

        tablero.getCasilla(4, 3).colocarUnidad(filologoHumano);
        filologoHumano.setFila(4);
        filologoHumano.setColumna(3);

        // Agregar unidades a los jugadores
        jugadorIA.getUnidades().agregarFinal(ingenieroIA);
        jugadorIA.getUnidades().agregarFinal(matematicoIA);

        jugadorHumano.getUnidades().agregarFinal(poetaHumano);
        jugadorHumano.getUnidades().agregarFinal(filologoHumano);

        ia = new IABasica(jugadorIA, jugadorHumano, tablero);
    }

    @Test
    public void testTurnoIABasicaMueveOAtaca() {
        Unidad unidadIA = jugadorIA.getUnidades().obtener(0);
        int filaInicial = unidadIA.getFila();
        int colInicial = unidadIA.getColumna();

        // Guardar hp inicial enemigos
        ListaEnlazada<Unidad> enemigos = jugadorHumano.getUnidades();
        int[] hpIniciales = new int[enemigos.tamanio()];
        for (int i = 0; i < enemigos.tamanio(); i++) {
            hpIniciales[i] = enemigos.obtener(i).getHp();
        }

        ia.Turno();

        Unidad unidadIADespués = jugadorIA.getUnidades().obtener(0);

        assertTrue(unidadIADespués.estaViva());
        assertEquals(unidadIA.getNombre(), unidadIADespués.getNombre());

        boolean seMovio = (unidadIADespués.getFila() != filaInicial) || (unidadIADespués.getColumna() != colInicial);

        boolean enemigoHerido = false;
        for (int i = 0; i < enemigos.tamanio(); i++) {
            if (enemigos.obtener(i).getHp() < hpIniciales[i]) {
                enemigoHerido = true;
                break;
            }
        }

        assertTrue(seMovio || enemigoHerido, "La IA debe moverse o atacar en su turno.");
    }
}
