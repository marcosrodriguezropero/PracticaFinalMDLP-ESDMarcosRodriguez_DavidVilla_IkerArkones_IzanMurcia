package practicafinal.clases;

public class BuscadorMovimiento {

    public static ListaEnlazada<Grafo> obtenerCasillasAlcanzables(GrafoTablero grafo, Unidad unidad) {
        int maxMovimiento = unidad.getRangoMovimiento();

        int filas = grafo.getFilas();
        int columnas = grafo.getColumnas();

        boolean[][] visitado = new boolean[filas][columnas];

        ListaEnlazada<Grafo> alcanzables = new ListaEnlazada<>();

        ColaCircular<NodoBusqueda> cola = new ColaCircular<>(filas * columnas);

        cola.encolar(new NodoBusqueda(unidad.getFila(), unidad.getColumna(), 0));

        visitado[unidad.getFila()][unidad.getColumna()] = true;

        while (!cola.estaVacia()) {
            NodoBusqueda actual = cola.desencolar();
            Grafo nodo = grafo.getNodo(actual.getFila(), actual.getColumna());

            // Incluye también la casilla actual (coste 0)
            if (actual.getCoste() <= maxMovimiento) {
                alcanzables.agregarFinal(nodo);
            }

            for (int i = 0; i < nodo.getAdyacentes().tamanio(); i++) {
                Grafo vecino = nodo.getAdyacentes().obtener(i);
                int nuevoCoste = actual.getCoste() + vecino.getCosteMovimiento();

                if (!visitado[vecino.getFila()][vecino.getColumna()] && nuevoCoste <= maxMovimiento) {
                    visitado[vecino.getFila()][vecino.getColumna()] = true;
                    cola.encolar(new NodoBusqueda(vecino.getFila(), vecino.getColumna(), nuevoCoste));
                }
            }
        }

        return alcanzables;
    }
}

