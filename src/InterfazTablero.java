import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class InterfazTablero extends Application {

    private final int TAM_CASILLA = 40;
    private Tablero tablero;
    private GrafoTablero grafo;
    private ControladorJuego controlador;
    private GridPane grid;

    @Override
    public void start(Stage stage) {
        Jugador jugador1 = new Jugador("Jugador 1", false);
        Jugador jugador2 = new Jugador("Jugador 2", true);

        tablero = new Tablero(10, 10);
        grafo = new GrafoTablero(10, 10, tablero);
        controlador = new ControladorJuego(tablero, grafo);

        Ingeniero unidad = new Ingeniero(jugador1);
        tablero.getCasilla(1, 1).colocarUnidad(unidad);

        grid = new GridPane();
        construirTablero();

        Scene scene = new Scene(grid);
        stage.setTitle("Conquista - Tablero");
        stage.setScene(scene);
        stage.show();
    }

    private void construirTablero() {
        grid.getChildren().clear();

        ListaEnlazada<Grafo> alcanzables = controlador.obtenerMovimientoDisponible();

        for (int fila = 0; fila < 10; fila++) {
            for (int col = 0; col < 10; col++) {
                Rectangle rect = new Rectangle(TAM_CASILLA, TAM_CASILLA);
                rect.setStroke(Color.GRAY);
                Casilla c = tablero.getCasilla(fila, col);

                Color colorBase = Color.BEIGE;
                if (c.estaOcupada()) {
                    colorBase = Color.LIGHTBLUE;
                }

                // Resaltar si es alcanzable
                for (int i = 0; i < alcanzables.tamanio(); i++) {
                    Grafo g = alcanzables.get(i);
                    if (g.fila == fila && g.columna == col) {
                        colorBase = Color.LIGHTGREEN;
                        break;
                    }
                }

                // Resaltar unidad seleccionada
                Unidad seleccionada = controlador.getUnidadSeleccionada();
                if (seleccionada != null && seleccionada.getFila() == fila && seleccionada.getColumna() == col) {
                    colorBase = Color.YELLOW;
                }

                rect.setFill(colorBase);

                StackPane stack = new StackPane();
                stack.getChildren().addAll(rect);

                int finalFila = fila;
                int finalCol = col;

                stack.setOnMouseClicked(e -> {
                    Casilla casilla = tablero.getCasilla(finalFila, finalCol);

                    if (casilla.estaOcupada()) {
                        controlador.seleccionarUnidad(finalFila, finalCol);
                    } else if (controlador.getUnidadSeleccionada() != null) {
                        controlador.moverUnidadSeleccionada(finalFila, finalCol);
                    }

                    construirTablero();
                });

                grid.add(stack, col, fila);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}