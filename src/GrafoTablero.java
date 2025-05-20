class GrafoTablero {
    private Grafo[][] nodos;
    private int filas;
    private int columnas;

    public GrafoTablero(int filas, int columnas, Tablero tablero) {
        this.filas = filas;
        this.columnas = columnas;
        nodos = new Grafo[filas][columnas];

        // Crear nodos del grafo basados en casillas del tablero
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Casilla c = tablero.getCasilla(i, j);
                nodos[i][j] = new Grafo(i, j, c.getCosteMovimiento());
            }
        }

        // Conectar nodos adyacentes (arriba, abajo, izquierda, derecha)
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Grafo actual = nodos[i][j];

                if (i > 0) actual.agregarAdyacente(nodos[i - 1][j]);
                if (i < filas - 1) actual.agregarAdyacente(nodos[i + 1][j]);
                if (j > 0) actual.agregarAdyacente(nodos[i][j - 1]);
                if (j < columnas - 1) actual.agregarAdyacente(nodos[i][j + 1]);
            }
        }
    }

    public Grafo getNodo(int fila, int columna) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            return nodos[fila][columna];
        }
        return null;
    }
}