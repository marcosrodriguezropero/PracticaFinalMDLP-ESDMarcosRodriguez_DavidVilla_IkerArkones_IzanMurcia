package practicafinal.clases;

public class Tablero {
    private int filas;
    private int columnas;
    private Casilla[][] casillas;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        casillas = new Casilla[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                casillas[i][j] = new Casilla(i, j, 1, 0);

            }
        }
    }

    public Casilla getCasilla(int fila, int columna) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            return casillas[fila][columna];
        }
        return null;
    }
    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

}
