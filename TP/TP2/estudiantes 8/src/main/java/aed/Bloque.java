package aed;
public class Bloque {
    private int id;
    private ListaEnlazada<Transaccion> transacciones;
    private int montoAcumulado;

    public Bloque(int id, ListaEnlazada<Transaccion> transacciones, int montoAcumulado) {
        this.id = id;
        this.transacciones = transacciones;
        this.montoAcumulado = montoAcumulado;
    }

    public int id() {
        return id;
    }

    public int montoAcumulado() {
        return montoAcumulado;
    }

    public ListaEnlazada<Transaccion> transacciones() {
        return transacciones;
    }

    public void actualizar(ListaEnlazada<Transaccion> t, int s){
        this.transacciones = t; //O(1)
        this.montoAcumulado = s; //O(1)
    }
    
}
