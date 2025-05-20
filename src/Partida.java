class Partida {
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private int turno;

    public Partida(Jugador jugador1, Jugador jugador2, int filas, int columnas) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.tablero = new Tablero(filas, columnas);
        this.turno = 0;
    }

    public void avanzarTurno(){
        turno++;
    }

    public int getTurno(){
        return turno;
    }

    public Tablero getTablero() {
        return tablero;
    }
}