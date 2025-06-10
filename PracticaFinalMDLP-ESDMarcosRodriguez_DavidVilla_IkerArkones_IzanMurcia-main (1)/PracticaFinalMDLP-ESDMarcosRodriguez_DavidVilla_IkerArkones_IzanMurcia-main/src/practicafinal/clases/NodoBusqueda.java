package practicafinal.clases;

public class NodoBusqueda {
    private int fila;
    private int columna;
    private int coste;

    public NodoBusqueda(int fila, int columna, int coste) {
        this.fila = fila;
        this.columna = columna;
        this.coste = coste;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public int getCoste() {
        return coste;
    }
}

