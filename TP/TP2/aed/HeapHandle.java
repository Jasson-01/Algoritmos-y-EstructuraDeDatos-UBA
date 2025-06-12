package aed;
import java.util.ArrayList;


public class HeapHandle{
    private Heap<Transaccion> heap;
    private ArrayList<ListaEnlazada<Transaccion>.Nodo> handle;

    Transaccion verRaiz(){
        return heap.verRaiz();
    }

    void agregar(ListaEnlazada<Transaccion>.Nodo nodo){
        heap.insertar(nodo.valor);
        this.handle.set(nodo.valor.id, nodo);
    }

    ListaEnlazada<Transaccion>.Nodo getNodoMax(){
        Transaccion transMax = heap.verRaiz();
        return handle.get(transMax.id);
    }

    void eliminar( ListaEnlazada<Transaccion> t){
        Transaccion transMax = heap.verRaiz();
        heap.extraerRaiz();
        ListaEnlazada<Transaccion>.Nodo i = handle.get(transMax.id);
        t.eliminarRapido(i);
        

    }
}
