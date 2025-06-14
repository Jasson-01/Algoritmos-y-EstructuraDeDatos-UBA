package aed;
import java.util.HashMap;


public class HeapHandleLE{
    private Heap<ListaEnlazada<Transaccion>.Handle> heap; // Cambiar el tipo del heap para almacenar handles directamente
    private HashMap<Integer, ListaEnlazada<Transaccion>.Handle> handleMap; // Usar un Map para sincronización robusta

    public HeapHandleLE(){
        heap = new Heap<ListaEnlazada<Transaccion>.Handle>();
        handleMap = new HashMap<>();
    }
    // O(1)
    public Transaccion verRaiz(){
        return heap.verRaiz() == null ? null : heap.verRaiz().valor(); 
    }

    // Mantiene O(log n)
    public void insertar(ListaEnlazada<Transaccion>.Handle h){
        heap.insertar(h); // Insertar el handle directamente
        int uid = h.valor().uid();
        handleMap.put(uid, h); // Indexar por uid
    }

    // Mantiene O(log n)
    public void extraerRaiz( ListaEnlazada<Transaccion> t){
        ListaEnlazada<Transaccion>.Handle h = heap.verRaiz(); // Obtener el handle directamente
        heap.extraerRaiz(); // Eliminar del heap
        if (h != null && t != null) {
            t.eliminarRapido(h); // Eliminar de la lista enlazada
        }
        if (h != null) {
            int uid = h.valor().uid();
            handleMap.remove(uid); // Eliminar el handle del map
        }
    }

    // Obtener el handle donde se aloja el valor máximo
    public ListaEnlazada<Transaccion>.Handle getHandleMax(){ //O(1)
        return heap.verRaiz(); // El handle máximo es la raíz del heap
    }
}
