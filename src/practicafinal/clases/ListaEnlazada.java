package practicafinal.clases;

public class ListaEnlazada<T> {
    private Nodo<T> cabeza;
    private int tamanio;

    public ListaEnlazada() {
        this.cabeza = null;
        this.tamanio = 0;
    }

    public void agregarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamanio++;
    }

    public boolean eliminar(T dato) {
        if (cabeza == null) return false;

        if (cabeza.dato.equals(dato)) {
            cabeza = cabeza.siguiente;
            tamanio--;
            return true;
        }

        Nodo<T> actual = cabeza;
        while (actual.siguiente != null && !actual.siguiente.dato.equals(dato)) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
            tamanio--;
            return true;
        }

        return false;
    }

    public boolean contiene(T dato) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.dato.equals(dato)) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    public T obtener(int indice) {
        if (indice < 0 || indice >= tamanio) return null;
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    public int tamanio() {
        return tamanio;
    }
}