package aed;
import java.util.ArrayList;


public class HeapHandleLE{
    private Heap<Transaccion> heap;
    private ArrayList<ListaEnlazada<Transaccion>.Handle> handle; //OBS: ListaEnlazada<Transaccion>.Handle = La clase anidad 'Handle' dentro de LE<T>

    public HeapHandleLE(){
        heap = new Heap<Transaccion>();
        handle = new ArrayList<ListaEnlazada<Transaccion>.Handle>();
    }

    // O(1)
    public Transaccion verRaiz(){
        return heap.verRaiz(); 
    }

    // Mantiene O(log n)
    public void insertar(ListaEnlazada<Transaccion>.Handle handle){
        heap.insertar(handle.valor()); //O(log n)
        this.handle.add(handle.valor().id(), handle); //O(1)
    }

    // Mantiene 0(log n)
    public void extraerRaiz( ListaEnlazada<Transaccion> t){
        Transaccion transMax = heap.verRaiz(); // O(1)
        heap.extraerRaiz(); // O(log n) 
        ListaEnlazada<Transaccion>.Handle i = handle.get(transMax.id()); //O(1)
        t.eliminarRapido(i); //O(1)
    }
}

/*
DUDAS:
-----
No podemos usar unicamente una LE para las transacciones porque si no, no podriamos 'obtenerRapido' al nodo
de la transacción de mayor monto O(1) (estamos practicamente seguros que esto no se puede hacer y de hecho
en el apunte de 'modulos_basicos' no hay tal metodo para LE). En cambio, con una ArrayList, si lo podemos hacer con ',get()'.
(OBS: Para el caso de agregar, si podriamos implentar 'agregarAtras' en O(1) para la LE, no habria problema).

¿Cuál es el rol de HeapHandleLE?

Objetivo principal:
-Eliminar esa misma transacción de la lista enlazada en O(1) para “deshacer” sus efectos (hackearTx()).

Problema a resolver:
Si solo tuviesemos un Heap<Transaccion>, cuando extraigamos la transacción mayor, perderíamos la referencia al nodo
correspondiente en la lista enlazada. Y para borrar ese valor de la lista tendríamos que buscarlo secuencialmente
(O(nb)), rompiendo la complejidad.
*/