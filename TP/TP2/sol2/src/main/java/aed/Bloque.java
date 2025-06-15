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

    //Fa: Para actualizar el bloque en el punto (7)
    public void actualizar(ListaEnlazada<Transaccion> t, int s){
        this.transacciones = t; //O(1)
        this.sumaMontos = s; //O(1)
    }

    // Para actualizar la suma de montos después de hackear
    public void actualizarSumaMontos(int nuevaSuma) {
        this.sumaMontos = nuevaSuma;
    }

    // Faltan funciones sobre manejo de heap de transacciones y la lista enlazada de transacciones

    
}
