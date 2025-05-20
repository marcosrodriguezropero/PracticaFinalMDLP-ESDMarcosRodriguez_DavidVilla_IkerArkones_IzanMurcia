class BuscadorMovimiento {

    public static ListaEnlazada<Grafo> obtenerCasillasAlcanzables(GrafoTablero grafoTablero, Unidad unidad) {
        int maxMovimiento = unidad.rangoMovimiento;

        boolean[][] visitado = new boolean[20][20];

        ListaEnlazada<Grafo> alcanzables = new ListaEnlazada<>();

        ColaCircular<NodoBusqueda> cola = new ColaCircular<>(400);

        cola.encolar(new NodoBusqueda(unidad.getFila(), unidad.getColumna(), 0));

        visitado[unidad.getFila()][unidad.getColumna()] = true;

        while (!cola.estaVacia()) {
            NodoBusqueda actual = cola.desencolar();
            Grafo nodo = grafoTablero.getNodo(actual.fila, actual.columna);
            if (actual.coste <= maxMovimiento && actual.coste != 0) {
                alcanzables.agregarFinal(nodo);
            }
            for (int i = 0; i < nodo.getAdyacentes().tamanio(); i++) {
                Grafo vecino = nodo.getAdyacentes().get(i);
                int nuevoCoste = actual.coste + vecino.costeMovimiento;
                if (!visitado[vecino.fila][vecino.columna] && nuevoCoste <= maxMovimiento) {
                    visitado[vecino.fila][vecino.columna] = true;
                    cola.encolar(new NodoBusqueda(vecino.fila, vecino.columna, nuevoCoste));
                }
            }
        }

        return alcanzables;
    }
}