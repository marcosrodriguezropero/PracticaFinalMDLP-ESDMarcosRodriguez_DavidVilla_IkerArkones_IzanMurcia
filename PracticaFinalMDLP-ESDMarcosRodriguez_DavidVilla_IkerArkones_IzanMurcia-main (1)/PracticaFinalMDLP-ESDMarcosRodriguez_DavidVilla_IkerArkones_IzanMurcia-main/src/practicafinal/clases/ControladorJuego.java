package practicafinal.clases;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.HashSet;
import java.util.Set;

public class ControladorJuego {
    private Tablero tablero;
    private GrafoTablero grafo;
    private Unidad unidadSeleccionada;

    private Jugador jugadorActual;
    private Jugador jugador1;
    private Jugador jugador2;

    private InterfazTablero interfaz; // referencia para actualizar la interfaz

    private boolean juegoTerminado = false;

    public ControladorJuego(Tablero tablero, GrafoTablero grafo, Jugador jugador1, Jugador jugador2) {
        this.tablero = tablero;
        this.grafo = grafo;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.jugadorActual = jugador1;
    }

    public void setInterfaz(InterfazTablero interfaz) {
        this.interfaz = interfaz;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public Unidad getUnidadSeleccionada() {
        return unidadSeleccionada;
    }

    private boolean jugadorTieneUnidades(Jugador jugador) {
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                Casilla c = tablero.getCasilla(i, j);
                if (c.estaOcupada() && c.getUnidad().getPropietario() == jugador) {
                    return true;
                }
            }
        }
        return false;
    }

    private void cambiarTurno() {
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;

        if (interfaz != null) {
            interfaz.recargarInterfaz(this);
        }

        if (jugadorActual.esIA()) {
            Jugador jugador = (jugadorActual == jugador1) ? jugador2 : jugador1;

            IABasica ia = new IABasica(jugadorActual, jugador, tablero);
            ia.Turno();

            jugadorActual = jugador;

            if (interfaz != null) {
                interfaz.recargarInterfaz(this);
            }
        }
        System.out.println("Turno de " + jugadorActual.getNombre());
    }

    public void seleccionarUnidad(int fila, int columna) {
        if (juegoTerminado) return;

        Casilla c = tablero.getCasilla(fila, columna);
        if (c != null && c.estaOcupada()) {
            Unidad u = c.getUnidad();
            if (u.getPropietario() == jugadorActual) {
                unidadSeleccionada = u;
                System.out.println("Unidad seleccionada: " + unidadSeleccionada.getNombre());
            } else {
                System.out.println("No puedes seleccionar una unidad del oponente.");
                unidadSeleccionada = null;
            }
        } else {
            unidadSeleccionada = null;
        }

        if (interfaz != null) interfaz.recargarInterfaz(this);
    }

    public boolean moverUnidadSeleccionada(int fila, int columna) {
        if (juegoTerminado) return false;
        if (unidadSeleccionada == null) return false;

        boolean exito = GestorAcciones.moverUnidad(unidadSeleccionada, fila, columna, grafo, tablero);

        if (exito) {
            System.out.println("Unidad movida a " + fila + "," + columna);
            unidadSeleccionada = null;
            cambiarTurno();

            if (interfaz != null) interfaz.recargarInterfaz(this);
        } else {
            System.out.println("Movimiento no válido.");
        }
        return exito;
    }

    public boolean atacarUnidadSeleccionada(int fila, int columna) {
        if (juegoTerminado) return false;
        if (unidadSeleccionada == null) return false;

        Casilla objetivo = tablero.getCasilla(fila, columna);
        if (objetivo != null && objetivo.estaOcupada()) {
            Unidad objetivoUnidad = objetivo.getUnidad();

            // No se puede atacar a una unidad propia
            if (objetivoUnidad.getPropietario() == unidadSeleccionada.getPropietario()) {
                System.out.println("No puedes atacar a una unidad aliada.");
                return false;
            }

            boolean exito = GestorAcciones.atacarUnidad(unidadSeleccionada, objetivoUnidad);

            if (exito) {
                System.out.println(unidadSeleccionada.getNombre() + " atacó a " + objetivoUnidad.getNombre());

                if (!objetivoUnidad.estaViva()) {
                    objetivo.quitarUnidad();
                    System.out.println(objetivoUnidad.getNombre() + " ha sido derrotado.");

                    Jugador jugadorContrario = objetivoUnidad.getPropietario();
                    Jugador ganador = (jugadorContrario == jugador1) ? jugador2 : jugador1;

                    if (!jugadorTieneUnidades(jugadorContrario)) {
                        System.out.println("¡El jugador " + ganador.getNombre() + " ha ganado!");
                        juegoTerminado = true;
                    }
                }

                unidadSeleccionada = null;

                if (!juegoTerminado) {
                    cambiarTurno();
                }

                if (interfaz != null) interfaz.recargarInterfaz(this);
            } else {
                System.out.println("Ataque fallido.");
            }
            return exito;
        }
        return false;
    }

    public GridPane generarVistaTablero() {
        GridPane grid = new GridPane();

        Set<Grafo> casillasAlcanzables = new HashSet<>();
        Set<String> casillasAtacables = new HashSet<>();

        if (unidadSeleccionada != null) {
            ListaEnlazada<Grafo> lista = BuscadorMovimiento.obtenerCasillasAlcanzables(grafo, unidadSeleccionada);
            for (int i = 0; i < lista.tamanio(); i++) {
                casillasAlcanzables.add(lista.obtener(i));
            }

            int rango = unidadSeleccionada.rangoAtaque;
            int filaSel = unidadSeleccionada.getFila();
            int colSel = unidadSeleccionada.getColumna();

            for (int i = filaSel - rango; i <= filaSel + rango; i++) {
                for (int j = colSel - rango; j <= colSel + rango; j++) {
                    Casilla c = tablero.getCasilla(i, j);
                    if (c != null && c.estaOcupada()) {
                        Unidad u = c.getUnidad();
                        if (u.getPropietario() != unidadSeleccionada.getPropietario()) {
                            casillasAtacables.add(i + "," + j);
                        }
                    }
                }
            }
        }

        for (int fila = 0; fila < tablero.getFilas(); fila++) {
            for (int col = 0; col < tablero.getColumnas(); col++) {
                Casilla casilla = tablero.getCasilla(fila, col);
                Unidad unidad = casilla.getUnidad();

                Button boton = new Button();
                boton.setPrefSize(60, 60);

                if (unidad != null) {
                    boton.setText(unidad.getNombre() + "\nHP: " + unidad.getHp());
                    boton.setFont(Font.font("Arial", FontWeight.BOLD, 10));
                } else {
                    boton.setText("");
                }

                Grafo nodo = grafo.getNodo(fila, col);
                String clave = fila + "," + col;

                if (casillasAtacables.contains(clave)) {
                    boton.setStyle("-fx-background-color: salmon;");
                } else if (casillasAlcanzables.contains(nodo)) {
                    boton.setStyle("-fx-background-color: lightblue;");
                }

                final int f = fila;
                final int c = col;

                boton.setOnAction(e -> {
                    Casilla destino = tablero.getCasilla(f, c);
                    String claveDestino = f + "," + c;

                    if (juegoTerminado) {
                        System.out.println("El juego ha terminado. Reinicia para jugar otra vez.");
                        return;
                    }

                    if (casillasAtacables.contains(claveDestino)) {
                        atacarUnidadSeleccionada(f, c);
                    } else if (destino.estaOcupada()) {
                        seleccionarUnidad(f, c);
                    } else {
                        boolean exito = moverUnidadSeleccionada(f, c);
                        if (exito) {
                            System.out.println("Unidad movida a (" + f + "," + c + ")");
                        } else {
                            System.out.println("Movimiento inválido.");
                        }
                    }
                    if (interfaz != null) interfaz.recargarInterfaz(this);
                });

                grid.add(boton, col, fila);
            }
        }

        return grid;
    }
}