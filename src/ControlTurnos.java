class ControlTurnos {
    private ColaCircular<Jugador> colaTurnos;
    private Jugador actual;

    public ControlTurnos(Jugador jugador1, Jugador jugador2) {
        colaTurnos = new ColaCircular<>(10); // capacidad suficiente para pocos jugadores
        colaTurnos.encolar(jugador1);
        colaTurnos.encolar(jugador2);
        actual = colaTurnos.desencolar();
        colaTurnos.encolar(actual); // lo volvemos a meter al final
    }

    public Jugador getJugadorActual() {
        return actual;
    }

    public void avanzarTurno() {
        actual = colaTurnos.desencolar();
        colaTurnos.encolar(actual);
    }
}