package practicafinal.clases;

public class Casilla {
    private int fila;
    private int columna;
    private int costeMovimiento;
    private int modificadorDefensa;
    private Unidad unidad;

    public Casilla(int fila, int columna, int costeMovimiento, int modificadorDefensa) {
        this.fila = fila;
        this.columna = columna;
        this.costeMovimiento = costeMovimiento;
        this.modificadorDefensa = modificadorDefensa;
    }

    public boolean estaOcupada(){
        return unidad != null;
    }

    public void colocarUnidad(Unidad u){
        this.unidad = u;
        u.setFila(fila);
        u.setColumna(columna);
    }

    public void quitarUnidad(){
        this.unidad = null;
    }

    public Unidad getUnidad(){
        return unidad;
    }

    public int getCosteMovimiento(){
        return costeMovimiento;
    }

    public int getModificadorDefensa(){
        return modificadorDefensa;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}

