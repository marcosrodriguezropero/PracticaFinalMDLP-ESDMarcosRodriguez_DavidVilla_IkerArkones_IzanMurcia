class GestorAcciones {

    public static boolean moverUnidad(Unidad unidad, int destinoFila, int destinoColumna, GrafoTablero grafoTablero, Tablero tablero) {
        ListaEnlazada<Grafo> alcanzables = BuscadorMovimiento.obtenerCasillasAlcanzables(grafoTablero, unidad);

        // Verificamos si la casilla deseada está en la lista alcanzable
        boolean permitido = false;
        for (int i = 0; i < alcanzables.tamanio(); i++) {
            Grafo g = alcanzables.get(i);
            if (g.fila == destinoFila && g.columna == destinoColumna) {
                permitido = true;
                break;
            }
        }

        if (!permitido) return false;

        Casilla casillaDestino = tablero.getCasilla(destinoFila, destinoColumna);
        if (casillaDestino.estaOcupada()) return false;

        // Quitar de la casilla actual
        Casilla actual = tablero.getCasilla(unidad.getFila(), unidad.getColumna());
        actual.quitarUnidad();

        // Colocar en la nueva casilla
        casillaDestino.colocarUnidad(unidad);

        return true;
    }

    public static boolean atacarUnidad(Unidad atacante, Unidad objetivo) {
        int distancia = Math.abs(atacante.getFila() - objetivo.getFila()) + Math.abs(atacante.getColumna() - objetivo.getColumna());

        if (distancia > atacante.rangoAtaque) return false;
        if (atacante.getPropietario() == objetivo.getPropietario()) return false;

        int factor = (int)(Math.random() * 3); // 0, 1 o 2
        int danio = factor * atacante.ataque - objetivo.defensa;
        if (danio < 0) danio = 0;

        objetivo.hp -= danio;
        if (objetivo.hp < 0) objetivo.hp = 0;

        return true;
    }
}