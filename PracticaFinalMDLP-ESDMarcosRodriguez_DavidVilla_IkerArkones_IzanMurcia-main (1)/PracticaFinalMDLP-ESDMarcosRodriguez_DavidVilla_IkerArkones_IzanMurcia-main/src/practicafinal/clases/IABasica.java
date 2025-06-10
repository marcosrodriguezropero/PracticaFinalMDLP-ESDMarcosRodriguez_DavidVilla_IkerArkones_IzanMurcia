package practicafinal.clases;

public class IABasica {
    private Jugador jugadorIA;
    private Jugador jugador;
    private Tablero tablero;

    public IABasica(Jugador jugadorIA, Jugador jugador, Tablero tablero) {
        this.jugador = jugador;
        this.jugadorIA = jugadorIA;
        this.tablero = tablero;
    }

    public void Turno() {
        ListaEnlazada<Unidad> unidadesIA = jugadorIA.getUnidades();

        for (int i = 0; i < unidadesIA.tamanio(); i++) {
            Unidad unidad = unidadesIA.obtener(i);
            if (!unidad.estaViva()) continue;

            Unidad obj = UnidadEnemigaMasCercana(unidad);
            if (obj == null) continue;

            if (RangoAtaque(unidad, obj)) {
                atacar(unidad, obj);
            } else {
                mover(unidad, obj);
            }
        }
    }

    private Unidad UnidadEnemigaMasCercana(Unidad unidad) {
        Unidad cercana = null;
        int distanciaMinima = Integer.MAX_VALUE;

        ListaEnlazada<Unidad> unidadesJugador = jugador.getUnidades();
        for (int i = 0; i < unidadesJugador.tamanio(); i++) {
            Unidad unidad1 = unidad;
            if (unidad1 == null || unidad.estaViva()) continue;

            int distancia = Math.abs(unidad1.getFila() - unidad.getFila()) + Math.abs(unidad1.getColumna() - unidad.getColumna());
            if (distancia < distanciaMinima) {
                cercana = unidad1;
                distanciaMinima = distancia;
            }
        }
        return cercana;
    }

    private boolean RangoAtaque (Unidad atacante, Unidad obj){
        int distancia = Math.abs(atacante.getColumna() - obj.getColumna()) + Math.abs(atacante.getFila() - obj.getFila());
        return distancia <= atacante.rangoAtaque;
    }

    private void atacar(Unidad atacante, Unidad defensor) {
        int factor = (int)(Math.random() * 3);
        int danio = Math.max(0, factor * atacante.ataque - defensor.defensa);

        defensor.hp -= danio;

        System.out.println(atacante.getNombre() + "ataca a " + defensor.getNombre() + "causando" + danio + "de daño");

        if (defensor.hp <= 0) {
            System.out.println(defensor.getNombre() + "ha sido eliminado");
            tablero.getCasilla(defensor.getFila(), defensor.getColumna()).quitarUnidad();
            jugador.getUnidades().eliminar(defensor);
        }
    }

    private void mover(Unidad unidad, Unidad obj) {
        int filaPrim = unidad.getFila();
        int columnaPrim = unidad.getColumna();

        int filaFin = unidad.getFila();
        int columnaFin = unidad.getColumna();

        if (obj.getFila() > filaPrim) filaFin++;
        else if (obj.getFila() < filaPrim) filaFin--;

        if (obj.getColumna() > columnaPrim) columnaFin++;
        else if (obj.getColumna() < columnaPrim) columnaFin--;

        Casilla destino = tablero.getCasilla(filaFin, columnaFin);

        if (destino != null && !destino.estaOcupada() && destino.getCosteMovimiento() <= unidad.getRangoMovimiento()) {
            tablero.getCasilla(filaPrim, columnaPrim).quitarUnidad();
            destino.colocarUnidad(unidad);

            System.out.println(unidad.getNombre() + "se mueve a (" + filaFin + "," + columnaFin + ")");
        }
    }

}
