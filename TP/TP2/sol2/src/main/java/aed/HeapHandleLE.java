package aed;
import java.util.ArrayList;

public class HeapHandleLE {
    private Heap<Transaccion> heap;
    private ArrayList<ListaEnlazada<Transaccion>.Handle> handle;

    public HeapHandleLE() {
        heap = new Heap<Transaccion>();
        handle = new ArrayList<>();
    }

    public ListaEnlazada<Transaccion>.Handle obtenerHandle(int idTransaccion) {
        return handle.get(idTransaccion); // O(1)
    }

    public Transaccion verRaiz() {
        return heap.verRaiz(); 
    }

    public void insertar(ListaEnlazada<Transaccion>.Handle h) {
        heap.insertar(h.valor()); // O(log n)
        // Asegurar que el ArrayList tenga el tamaño suficiente
        while (handle.size() <= h.valor().id()) {
            handle.add(null);
        }
        handle.set(h.valor().id(), h); // O(1)
    }

    public void extraerRaiz() {
        if (heap.verRaiz() == null) return;
        
        Transaccion transMax = heap.verRaiz(); // O(1)
        heap.extraerRaiz(); // O(log n)
        
        // Eliminar de la lista enlazada
        ListaEnlazada<Transaccion>.Handle h = handle.get(transMax.id());
        if (h != null) {
            // La eliminación de la lista enlazada debe hacerse desde Berretacoin
            // ya que necesitamos acceso a la lista del bloque actual
        }
    }

    public void limpiar() {
        heap = new Heap<Transaccion>();
        handle = new ArrayList<>();
    }
}

/* 
public class HeapHandleLE {
    private Heap<Transaccion> heap;
    private ArrayList<ListaEnlazada<Transaccion>.Handle> handle;

    public HeapHandleLE() {
        heap = new Heap<Transaccion>();
        handle = new ArrayList<>();
    }

    public Transaccion verRaiz() {
        return heap.verRaiz(); 
    }

    public void insertar(ListaEnlazada<Transaccion>.Handle h) {
        heap.insertar(h.valor()); // O(log n)
        this.handle.add(h.valor().id(), h); // O(1)
    }

    public void extraerRaiz(ListaEnlazada<Transaccion> t) {
        Transaccion transMax = heap.verRaiz(); // O(1)
        heap.extraerRaiz(); // O(log n)
        ListaEnlazada<Transaccion>.Handle h = handle.get(transMax.id());
        t.eliminarRapido(h); // O(1)
    }
}
*/



/* 
package aed;
import java.util.ArrayList;


public class HeapHandleLE{
    private Heap<Transaccion> heap; //1era Estructura
    private ArrayList<ListaEnlazada<Transaccion>.Nodo> handle; //2da Estructura

    public HeapHandleLE(){
        heap = new Heap<Transaccion>();
        handle = new ArrayList<ListaEnlazada<Transaccion>.Nodo>();
    }
    // O(1)
    public Transaccion verRaiz(){
        return heap.verRaiz(); 
    }

    // Mantiene O(log n)
    public void insertar(ListaEnlazada<Transaccion>.Nodo nodo){
        heap.insertar(nodo.valor); //O(log n)
        this.handle.add(nodo.valor.id(), nodo); //O(1)
    }

    // Mantiene 0(log n)
    public void extraerRaiz( ListaEnlazada<Transaccion> t){
        Transaccion transMax = heap.verRaiz(); // O(1)
        heap.extraerRaiz(); // O(log n) 
        ListaEnlazada<Transaccion>.Nodo i = handle.get(transMax.id()); //O(1)
        t.eliminarRapido(i); //O(1)
        

    }

     //Fa: No se si esté bien implementado,
     //  pero sería muy útil obtener el nodo donde se aloja el valor máximo, por eso lo descomento. 

    public ListaEnlazada<Transaccion>.Nodo getNodoMax(){ //O(1)
        Transaccion transMax = heap.verRaiz(); //O(1)
        return handle.get(transMax.id()); //O(1)
    }
}
*/