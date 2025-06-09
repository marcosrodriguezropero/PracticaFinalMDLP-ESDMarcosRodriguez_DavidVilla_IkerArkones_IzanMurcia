package practicafinal.clases;

public abstract class Unidad {
    protected String nombre;
    protected int hp;
    protected int ataque;
    protected int defensa;
    protected int rangoMovimiento;
    protected int rangoAtaque;
    protected int fila;
    protected int columna;

    protected Jugador propietario;

    public Unidad(String nombre, int hp, int ataque, int defensa, int rangoMovimiento, int rangoAtaque, Jugador propietario) {
        this.nombre = nombre;
        this.hp = hp;
        this.ataque = ataque;
        this.defensa = defensa;
        this.rangoMovimiento = rangoMovimiento;
        this.rangoAtaque = rangoAtaque;
        this.propietario = propietario;
    }

    public abstract void realizarAccion();

    public boolean estaViva() {
        return hp > 0;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public String getNombre() {
        return nombre;
    }
    public int getHp() {
        return hp;
    }


    public static class Ingeniero extends Unidad {
        public Ingeniero(Jugador propietario) {
            super("Ingeniero", 100, 30, 15, 3, 1, propietario);
        }

        @Override
        public void realizarAccion() {}
    }

    public static class Matematico extends Unidad {
        public Matematico(Jugador propietario) {
            super("Matematico", 80, 40, 10, 4, 2, propietario);
        }

        @Override
        public void realizarAccion() {}
    }

    public static class Poeta extends Unidad {
        public Poeta(Jugador propietario) {
            super("Poeta", 90, 25, 20, 3, 1, propietario);
        }

        @Override
        public void realizarAccion() {}
    }

    public static class Filologo extends Unidad {
        public Filologo(Jugador propietario) {
            super("Filologo", 70, 35, 15, 4, 2, propietario);
        }

        @Override
        public void realizarAccion() {}
    }
    public int getRangoMovimiento() {
        return rangoMovimiento;
    }

}

