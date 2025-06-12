package aed;
public class Bloque {
    private int id;
    private ListaEnlazada<Transaccion> transacciones;
    private int sumaMontos;

    public Bloque(int id, ListaEnlazada<Transaccion> transacciones, int sumaMontos) {
        this.id = id;
        this.transacciones = transacciones;
        this.sumaMontos = sumaMontos;
    }

    public int id() {
        return id;
    }

    public int sumaMontos() {
        return sumaMontos;
    }

    public ListaEnlazada<Transaccion> transacciones() {
        return transacciones;
    }

    // Faltan funciones sobre manejo de heap de transacciones y la lista enlazada de transacciones
    
}
