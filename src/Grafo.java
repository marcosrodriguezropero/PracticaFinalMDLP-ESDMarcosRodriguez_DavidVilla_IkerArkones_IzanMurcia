class Grafo {
    int fila;
    int columna;
    int costeMovimiento;
    ListaEnlazada<Grafo> adyacentes;

    public Grafo(int fila, int columna, int costeMovimiento) {
        this.fila = fila;
        this.columna = columna;
        this.costeMovimiento = costeMovimiento;
        this.adyacentes = new ListaEnlazada<>();
    }

    public void agregarAdyacente(Grafo vecino) {
        adyacentes.agregarFinal(vecino);
    }

    public ListaEnlazada<Grafo> getAdyacentes() {
        return adyacentes;
    }
}