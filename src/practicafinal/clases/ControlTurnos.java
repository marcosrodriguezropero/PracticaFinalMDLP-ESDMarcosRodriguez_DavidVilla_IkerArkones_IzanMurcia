package practicafinal.clases;

class ControlTurnos {
    private ColaCircular<Jugador> colaTurnos;
    private Jugador actual;

    public ControlTurnos(Jugador jugador1, Jugador jugador2) {
        colaTurnos = new ColaCircular<>(10);
        colaTurnos.encolar(jugador1);
        colaTurnos.encolar(jugador2);
        actual = colaTurnos.desencolar();
        colaTurnos.encolar(actual);
    }

    public Jugador getJugadorActual() {
        return actual;
    }

    public void avanzarTurno() {
        actual = colaTurnos.desencolar();
        colaTurnos.encolar(actual);
    }
}
