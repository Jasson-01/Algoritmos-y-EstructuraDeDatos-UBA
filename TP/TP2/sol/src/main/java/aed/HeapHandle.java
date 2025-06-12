package aed;
import java.util.ArrayList;


public class HeapHandle{
    private static class NodoOrdenado {
        ListaEnlazada<Transaccion>.Nodo nodo;
        int orden;
        NodoOrdenado(ListaEnlazada<Transaccion>.Nodo nodo, int orden) {
            this.nodo = nodo;
            this.orden = orden;
        }
    }

    private Heap<NodoOrdenado> heap;
    private ArrayList<ListaEnlazada<Transaccion>.Nodo> handle;

    public HeapHandle() {
        heap = new Heap<>((a, b) -> {
            Transaccion ta = a.nodo.valor;
            Transaccion tb = b.nodo.valor;
            if (ta.monto() > tb.monto()) return -1;
            if (ta.monto() < tb.monto()) return 1;
            return Integer.compare(ta.id, tb.id);
        });
        handle = new ArrayList<>();
    }

    Transaccion verRaiz(){
        NodoOrdenado nodoOrd = heap.verRaiz();
        return nodoOrd != null ? nodoOrd.nodo.valor : null;
    }

    void agregar(ListaEnlazada<Transaccion>.Nodo nodo, int orden){
        NodoOrdenado nodoOrd = new NodoOrdenado(nodo, orden);
        heap.insertar(nodoOrd);
        while (handle.size() <= nodo.valor.id) {
            handle.add(null);
        }
        this.handle.set(nodo.valor.id, nodo);
    }

    ListaEnlazada<Transaccion>.Nodo getNodoMax(){
        NodoOrdenado nodoOrd = heap.verRaiz();
        return nodoOrd != null ? nodoOrd.nodo : null;
    }

    void eliminar(ListaEnlazada<Transaccion> t){
        NodoOrdenado nodoOrd = heap.verRaiz();
        heap.extraerRaiz();
        t.eliminarRapido(nodoOrd.nodo);
    }
}
