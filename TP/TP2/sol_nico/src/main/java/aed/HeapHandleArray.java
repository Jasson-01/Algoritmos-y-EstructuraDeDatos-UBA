package aed;
import java.util.ArrayList;

// Reclamarle a Fa 
public class HeapHandleArray {
    private Heap<Usuario> heap;  
    private ArrayList<Integer> handle; 

    public HeapHandleArray(int n){
        heap = new Heap<Usuario>(); //O(1)
        handle = new ArrayList<Integer>(); //O(1)

        for(int i = 1; i <= n; i++){ //O(P)

            /* Explicación: Si bien insertar usario a usuario en un heap es O(P log P),
             * como para este caso se insertan todos los usuarios con el mismo
             * patrimonio inicial, que es 0, y en orden de id creciente, se
             * logra tener una complejidad de O(P).
             */

            Usuario u = new Usuario(i);
            heap.insertar(u); // Agrego Usuario 1 en la pos 0.
            handle.add(i-1); // Agrego Usuario 1 en la pos 0.
        }
    }
    
    public Usuario verRaiz(){
        return heap.verRaiz(); //O(1)
    }

    public void actualizarPatrimonio(int id, int monto){
        int pos = handle.get(id); // posición real en el heap
        Usuario usuario = heap.get(pos); //O(1)
        usuario.sumar(monto);//O(1)
        actualizarHeap(pos);//O(log P)
    }

    private void actualizarHeap(int pos){ //O(log P)
        ArrayList<Heap<Usuario>.Tupla>  t = new ArrayList<Heap<Usuario>.Tupla>();
        t = heap.siftUp(pos);//O(log P)
        for(int i = 0; i < t.size(); i++){
            handle.set(t.get(i).valor.id() - 1,t.get(i).indice);
        }
        t = heap.siftDown(pos);//O(log P)
        for(int i = 0; i < t.size(); i++){
            handle.set(t.get(i).valor.id() - 1,t.get(i).indice);
        }
    }

}
