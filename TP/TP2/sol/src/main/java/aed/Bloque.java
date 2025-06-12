package aed;
public class Bloque {
    private int id;
    private ListaEnlazada<Transaccion> transacciones;
    private int sumaMontos;
    private HeapHandle heapTx; // HeapHandle para transacciones

    public Bloque(int id, ListaEnlazada<Transaccion> transacciones, int sumaMontos, Transaccion[] arrayOriginal) {
        this.id = id;
        this.transacciones = transacciones;
        this.sumaMontos = sumaMontos;
        this.heapTx = new HeapHandle();
        ListaEnlazada<Transaccion>.Nodo actual = transacciones.primerNodo();
        while (actual != null) {
            // Buscar el índice de aparición en el array original
            int idx = -1;
            for (int i = 0; i < arrayOriginal.length; i++) {
                if (arrayOriginal[i] == actual.valor) {
                    idx = i;
                    break;
                }
            }
            heapTx.agregar(actual, idx);
            actual = actual.next;
        }
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

    public HeapHandle heapTransacciones() {
        return heapTx;
    }
}
