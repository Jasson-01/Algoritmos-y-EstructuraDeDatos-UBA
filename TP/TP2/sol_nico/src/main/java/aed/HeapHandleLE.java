package aed;
import java.util.ArrayList;


public class HeapHandleLE{
    private Heap<HandleTx> heap; //1era Estructura
    private ArrayList<HandleTx> handles; //2da Estructura

    public HeapHandleLE() {
        heap = new Heap<HandleTx>();
        handles = new ArrayList<>();
    }
    // O(1)
    public Transaccion verRaiz() {
        return heap.verRaiz().getTransaccion();
    }

    // Mantiene O(log n)
    public void insertar(HandleTx handle) {
        heap.insertar(handle);
        int id = handle.getId();
        while (handles.size() <= id) handles.add(null);
        handles.set(id, handle);
    }

    // Mantiene 0(log n)
    public void extraerRaiz() {
        HandleTx maxHandle = heap.verRaiz();
        heap.extraerRaiz();
        maxHandle.eliminar(); // elimina de la lista enlazada
    }

     //Fa: No se si esté bien implementado,
     //  pero sería muy útil obtener el nodo donde se aloja el valor máximo, por eso lo descomento. 

     public HandleTx getHandleMax() {
        return heap.verRaiz(); // si querés algo más específico, devolvés handles.get(...)
    }
    
}

