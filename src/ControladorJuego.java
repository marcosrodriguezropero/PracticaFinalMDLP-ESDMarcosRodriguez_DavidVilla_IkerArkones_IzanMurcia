class ControladorJuego {
    private Tablero tablero;
    private GrafoTablero grafo;
    private Unidad unidadSeleccionada;

    public ControladorJuego(Tablero tablero, GrafoTablero grafo) {
        this.tablero = tablero;
        this.grafo = grafo;
    }

    public void seleccionarUnidad(int fila, int columna) {
        Casilla c = tablero.getCasilla(fila, columna);
        if (c.estaOcupada()) {
            unidadSeleccionada = c.getUnidad();
            System.out.println("Unidad seleccionada: " + unidadSeleccionada.getNombre());
        }
    }

    public ListaEnlazada<Grafo> obtenerMovimientoDisponible() {
        if (unidadSeleccionada == null) return new ListaEnlazada<>();
        return BuscadorMovimiento.obtenerCasillasAlcanzables(grafo, unidadSeleccionada);
    }

    public boolean moverUnidadSeleccionada(int fila, int columna) {
        if (unidadSeleccionada == null) return false;
        boolean exito = GestorAcciones.moverUnidad(unidadSeleccionada, fila, columna, grafo, tablero);
        if (exito) {
            System.out.println("Unidad movida a " + fila + "," + columna);
            unidadSeleccionada = null;
        }
        return exito;
    }

    public Unidad getUnidadSeleccionada() {
        return unidadSeleccionada;
    }
}