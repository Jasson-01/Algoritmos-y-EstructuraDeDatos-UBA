package aed;
import java.util.ArrayList;


public class HeapHandleLE{
    private Heap<ListaEnlazada<Transaccion>.Handle> heap; // Cambiar el tipo del heap para almacenar handles directamente
    private ArrayList<ListaEnlazada<Transaccion>.Handle> handle; //2da Estructura

    public HeapHandleLE(){
        heap = new Heap<ListaEnlazada<Transaccion>.Handle>();
        handle = new ArrayList<ListaEnlazada<Transaccion>.Handle>();
    }
    // O(1)
    public Transaccion verRaiz(){
        return heap.verRaiz() == null ? null : heap.verRaiz().valor(); 
    }

    // Mantiene O(log n)
    public void insertar(ListaEnlazada<Transaccion>.Handle h){
        heap.insertar(h); // Insertar el handle directamente
        int uid = h.valor().uid();
        while (handle.size() <= uid) {
            handle.add(null);
        }
        this.handle.set(uid, h); // Indexar por uid
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
            if (uid >= 0 && uid < handle.size()) {
                handle.set(uid, null); // Eliminar el handle de la lista interna
            }
        }
    }

    // Obtener el handle donde se aloja el valor máximo
    public ListaEnlazada<Transaccion>.Handle getHandleMax(){ //O(1)
        return heap.verRaiz(); // El handle máximo es la raíz del heap
    }
}
