package practicafinal.clases;

public class Jugador {
    private String nombre;
    private boolean esIA;
    private ListaEnlazada<Unidad> unidades;

    public Jugador(String nombre, boolean esIA) {
        this.nombre = nombre;
        this.esIA = esIA;
        this.unidades = new ListaEnlazada<>();
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esIA() {
        return esIA;
    }

    public ListaEnlazada<Unidad> getUnidades() {
        return unidades;
    }


}

