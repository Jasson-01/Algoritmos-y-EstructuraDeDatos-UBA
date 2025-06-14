package aed;

public class HandleTx implements Comparable<HandleTx> {
    private ListaEnlazada<Transaccion> lista;
    private ListaEnlazada<Transaccion>.Nodo nodo;

    HandleTx(ListaEnlazada<Transaccion> lista, ListaEnlazada<Transaccion>.Nodo nodo) {
        this.lista = lista;
        this.nodo = nodo;
    }

    public Transaccion getTransaccion() {
        return nodo.valor; // nodo es accesible porque está en el mismo paquete
    }

    public int getId() {
        return getTransaccion().id();
    }

    public void eliminar() {
        lista.eliminarRapido(nodo);
    }

    public ListaEnlazada<Transaccion>.Nodo getNodo() {
        return nodo;
    }

    @Override
    public int compareTo(HandleTx otro) {
        return Integer.compare(this.getTransaccion().monto(), otro.getTransaccion().monto());
    }
}
