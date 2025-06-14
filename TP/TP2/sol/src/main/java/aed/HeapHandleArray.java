package aed;

import java.util.ArrayList;

public class HeapHandleArray {
    private Heap<Usuario> heap;
    private ArrayList<Integer> handle;

    public HeapHandleArray(int n) {
        heap = new Heap<Usuario>();
        handle = new ArrayList<Integer>();
        for (int i = 1; i <= n; i++) {
            Usuario u = new Usuario(i);
            heap.insertar(u);
            handle.add(0); // inicializar con 0, se corrige abajo
        }
        // Sincronizar handle con la posición real de cada usuario en el heap
        for (int i = 0; i < n; i++) {
            Usuario u = heap.get(i);
            handle.set(u.id() - 1, i);
        }
    }

    public Usuario verRaiz() {
        return heap.verRaiz();
    }

    /**
     * Actualiza el patrimonio del usuario dado su ID (entero > 0) y reordena el
     * heap.
     */
    public void actualizarPatrimonio(int idUsuario, int monto) {
        // Solo actualizar si el idUsuario es válido
        if (idUsuario <= 0 || idUsuario > handle.size())
            return;
        int pos = handle.get(idUsuario - 1);
        Usuario usuario = heap.get(pos);
        usuario.sumar(monto);
        // Reajustar heap hacia arriba
        ArrayList<Heap<Usuario>.Tupla> intercambios = heap.siftUp(pos);
        for (Heap<Usuario>.Tupla t : intercambios) {
            handle.set(t.valor.id() - 1, t.indice);
        }
        // Reajustar heap hacia abajo
        intercambios = heap.siftDown(pos);
        for (Heap<Usuario>.Tupla t : intercambios) {
            handle.set(t.valor.id() - 1, t.indice);
        }
    }
}
