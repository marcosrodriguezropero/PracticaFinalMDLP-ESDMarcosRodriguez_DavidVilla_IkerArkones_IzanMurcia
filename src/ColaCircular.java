class ColaCircular<T> {
    private T[] elementos;
    private int frente;
    private int fin;
    private int tamanio;
    private int capacidad;

    @SuppressWarnings("unchecked")
    public ColaCircular(int capacidad){
        this.capacidad = capacidad;
        elementos = (T[]) new Object[capacidad];
        frente = 0;
        fin = -1;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }

    public boolean estaLlena() {
        return tamanio == capacidad;
    }

    public boolean encolar(T elemento) {
        if (estaLlena())
            return false;

        fin = (fin + 1) % capacidad;

        elementos[fin] = elemento;

        tamanio++;
        return true;
    }

    public T desencolar(){
        if (estaVacia())
            return null;
        T elemento = elementos[frente];

        frente = (frente + 1) % capacidad;

        tamanio--;
        return elemento;
    }

    public T frente(){
        if (estaVacia())
            return null;
        return elementos[frente];
    }

    public int tamanio(){
        return tamanio;
    }
}