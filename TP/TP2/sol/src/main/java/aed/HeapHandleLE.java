package aed;
import java.util.ArrayList;


public class HeapHandleLE{
    private Heap<Transaccion> heap; //1era Estructura
    private ArrayList<ListaEnlazada<Transaccion>.Handle> handle; //2da Estructura

    public HeapHandleLE(){
        heap = new Heap<Transaccion>();
        handle = new ArrayList<ListaEnlazada<Transaccion>.Handle>();
    }
    // O(1)
    public Transaccion verRaiz(){
        return heap.verRaiz(); 
    }

    // Mantiene O(log n)
    public void insertar(ListaEnlazada<Transaccion>.Handle h){
        heap.insertar(h.valor()); //O(log n)
        while (handle.size() <= h.valor().id()) {
            handle.add(null);
        }
        this.handle.set(h.valor().id(), h); //O(1)
    }

    // Mantiene 0(log n)
    public void extraerRaiz( ListaEnlazada<Transaccion> t){
        Transaccion transMax = heap.verRaiz(); // O(1)
        heap.extraerRaiz(); // O(log n) 
        ListaEnlazada<Transaccion>.Handle h = handle.get(transMax.id()); //O(1)
        t.eliminarRapido(h); //O(1)
        

    }

    // Obtener el handle donde se aloja el valor máximo
    public ListaEnlazada<Transaccion>.Handle getHandleMax(){ //O(1)
        Transaccion transMax = heap.verRaiz(); //O(1)
        if (transMax == null) return null;
        if (transMax.id() < 0 || transMax.id() >= handle.size()) return null;
        return handle.get(transMax.id()); //O(1)
    }
}
