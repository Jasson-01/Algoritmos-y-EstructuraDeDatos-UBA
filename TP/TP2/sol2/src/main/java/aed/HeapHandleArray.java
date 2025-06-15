package aed;
import java.util.ArrayList;

// Reclamarle a Fa 
public class HeapHandleArray {
    private Heap<Usuario> heap;  
    private Handle handle; 

    private class Handle{
        private ArrayList<Integer> handle;

        // Fa: hice Handle
        Handle(){
            handle = new ArrayList<>();
        }

        void add(int i){
            this.handle.add(i-1);
        }

        void actualizarHandle(int idUsuario, int indice){
            this.handle.set(idUsuario,indice);
        }
    }


    public HeapHandleArray(int n){
        heap = new Heap<Usuario>(); //O(1)
        handle = new Handle();

        for(int i = 1; i <= n; i++){ //O(P)

            /* Explicación: Si bien insertar usario a usuario en un heap es O(P log P),
             * como para este caso se insertan todos los usuarios con el mismo
             * patrimonio inicial, que es 0, y en orden de id creciente, se
             * logra tener una complejidad de O(P).
             */

            Usuario u = new Usuario(i);
            heap.insertar(u); // Agrego Usuario 1 en la pos 0.
            handle.add(i); // Agrego Usuario 1 en la pos 0.
        }
    }
    
    public Usuario verRaiz(){
        return heap.verRaiz(); //O(1)
    }

    public void actualizarPatrimonio(int id, int monto){
        /* 
        heap.get(handle.handle.get(id-1)).sumar(monto); //O(1) // Fa: le resto 1 al id
        actualizarHeap(id);//O(log P)
        */

        System.out.println("Actualizando usuario " + id + " con monto " + monto);

        // Obtener la posición actual del usuario en el heap
        int posicionEnHeap = handle.handle.get(id - 1);
        
        // Actualizar el patrimonio del usuario
        Usuario usuario = heap.get(posicionEnHeap);
        System.out.println("Patrimonio antes: " + usuario.patrimonio());

        usuario.sumar(monto);
        System.out.println("Patrimonio después: " + usuario.patrimonio());

        // Reordenar el heap y actualizar handles
        actualizarHeap(posicionEnHeap);
        System.out.println("Máximo tenedor ahora: " + verRaiz().id() + " con patrimonio " + verRaiz().patrimonio());

        
    }

    private void actualizarHeap(int posicion){ //O(log P)

    // Intentar subir el elemento
    ArrayList<Heap<Usuario>.Tupla> movimientos = heap.siftUp(posicion);
        
    // Actualizar handles basado en los movimientos
    for (Heap<Usuario>.Tupla tupla : movimientos) {
        Usuario usuario = tupla.valor;
        handle.handle.set(usuario.id() - 1, tupla.indice);
    }
    
    // Si no se movió hacia arriba, intentar bajar
    if (movimientos.size() == 1 && movimientos.get(0).indice == posicion) {
        movimientos = heap.siftDown(posicion);
        
        // Actualizar handles basado en los movimientos
        for (Heap<Usuario>.Tupla tupla : movimientos) {
            Usuario usuario = tupla.valor;
            handle.handle.set(usuario.id() - 1, tupla.indice);
        }
    }
}

        /* 
        ArrayList<Heap<Usuario>.Tupla>  t = new ArrayList<Heap<Usuario>.Tupla>();
        int index = handle.handle.get(id-1); // Fa: agregado
        t = heap.siftUp(index);//O(log P)
        
        for(int i = 0; i < t.size();i++){
            handle.actualizarHandle(t.get(i).valor.id() - 1,t.get(i).indice);
        }

        t = heap.siftDown(index); //O(log P)
        for(int i = 0; i < t.size();i++){
            handle.actualizarHandle(t.get(i).valor.id() - 1,t.get(i).indice);
        }
    }
    */

}