package practicafinal.tests;

import practicafinal.clases.*;

public class IABasicaTest {
    public static void main(String[] args) {
        Tablero tablero = new Tablero(10,15);

        Jugador IA = new Jugador("IA",true);
        Jugador normal = new Jugador("Human",false);

        Unidad unidad1 = new Unidad.Matematico(IA);
        Unidad unidad2 = new Unidad.Ingeniero(normal);

        tablero.getCasilla(1,1).colocarUnidad(unidad1);
        tablero.getCasilla(3,4).colocarUnidad(unidad2);

        IABasica ia = new IABasica(IA, normal, tablero);

        System.out.println("Se empieza en...");
        imprimirEstadoTablero(tablero);

        ia.Turno();

        System.out.println("Después en...");
        imprimirEstadoTablero(tablero);
    }

    private static void imprimirEstadoTablero(Tablero tablero) {
        for (int fila = 0; fila < tablero.getFilas(); fila++) {
            for (int columna = 0; columna < tablero.getColumnas(); columna++) {
                Casilla casilla = tablero.getCasilla(fila, columna);
                if (casilla.estaOcupada()){
                    Unidad unidad = casilla.getUnidad();
                    System.out.println("[" + unidad.getNombre().charAt(0) + " - " + unidad.getHp() + "]");
                } else {
                    System.out.println("[ ]");
                }
            }
            System.out.println();
        }
    }
}
