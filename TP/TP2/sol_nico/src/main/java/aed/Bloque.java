package aed;
public class Bloque {
    private int id;
    private Transaccion[] transacciones;
    private int sumaMontos;

    public Bloque(int id, Transaccion[] transacciones, int sumaMontos) {
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

    public Transaccion[] transacciones() {
        return transacciones;
    }

    //Fa: Para actualizar el bloque en el punto (7)
    public void actualizar(Transaccion[] t, int s){
        this.transacciones = t; //O(1)
        this.sumaMontos = s; //O(1)
    }
}
