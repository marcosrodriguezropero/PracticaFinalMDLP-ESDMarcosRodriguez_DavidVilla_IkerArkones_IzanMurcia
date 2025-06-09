package practicafinal.clases;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InterfazTablero extends Application {

    private ControladorJuego controlador;
    private Label labelTurno;
    private VBox root;

    @Override
    public void start(Stage primaryStage) {
        Tablero tablero = new Tablero(10, 10);
        GrafoTablero grafo = new GrafoTablero(tablero);

        Jugador jugador1 = new Jugador("Jugador 1", false);
        Jugador jugador2 = new Jugador("Jugador 2", false);

        controlador = new ControladorJuego(tablero, grafo, jugador1, jugador2);
        controlador.setInterfaz(this);

        Unidad u1 = new Unidad.Ingeniero(jugador1);
        Unidad u2 = new Unidad.Matematico(jugador1);
        Unidad u3 = new Unidad.Filologo(jugador2);
        Unidad u4 = new Unidad.Poeta(jugador2);

        tablero.getCasilla(0, 0).colocarUnidad(u1);
        tablero.getCasilla(0, 9).colocarUnidad(u2);
        tablero.getCasilla(9, 0).colocarUnidad(u3);
        tablero.getCasilla(9, 9).colocarUnidad(u4);

        labelTurno = new Label();
        actualizarLabelTurno();

        GridPane tableroVisual = controlador.generarVistaTablero();

        root = new VBox(10);
        root.getChildren().addAll(labelTurno, tableroVisual);

        Scene scene = new Scene(root, 800, 850);
        primaryStage.setTitle("Juego Conquista");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void actualizarLabelTurno() {
        labelTurno.setText("Turno de: " + controlador.getJugadorActual().getNombre());
    }

    public void recargarInterfaz(ControladorJuego controlador) {
        GridPane nuevoTablero = controlador.generarVistaTablero();
        root.getChildren().set(1, nuevoTablero);
        actualizarLabelTurno();
    }
}
