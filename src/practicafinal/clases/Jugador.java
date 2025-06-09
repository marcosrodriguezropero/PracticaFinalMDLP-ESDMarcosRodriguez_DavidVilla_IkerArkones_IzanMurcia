package practicafinal.clases;

public class Jugador {
    private String nombre;
    private boolean esIA;

    public Jugador(String nombre, boolean esIA) {
        this.nombre = nombre;
        this.esIA = esIA;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esIA() {
        return esIA;
    }
}

