package aed;
//import java.util.*;

public class ListaEnlazada<T> {
    private Nodo first;
    private Nodo last;
    private int size;

    private class Nodo {
        T valor;
        Nodo next;
        Nodo previous;

        Nodo(T v) { valor = v; }
    }

    public class Handle {
        private Nodo nodo;
        public Handle(Nodo n) {
            this.nodo = n;
        }
        
        //O(1)
        public T valor() {
            return nodo.valor;
        }
        
        //O(1)
        public Handle siguiente() {
            if (nodo.next == null) {
                return null;
            }
            return new Handle(nodo.next);
        }
        
        //O(1)
        private Nodo getInterno() {
            return nodo;
        }
    }


    //O(1)
    public Handle obtenerPrimero() {
        if (first == null) {
            return null;
        }
        return new Handle(first);
    }

    //O(1)
    public Handle obtenerUltimo() {
        if (last == null) {
            return null;
        }
        return new Handle(last);
    }


    //Constructor
    public ListaEnlazada() {
        first = null;
        last = null;
        size = 0;
    }

    //O(1)
    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (first == null) {
            first = nuevo;
            last = nuevo;
        } else {
            last.next = nuevo;
            nuevo.previous = last;
            last = nuevo;
        }
        size++;
    }

    //O(1)
    public void eliminarRapido(Handle h) {
        Nodo n = h.getInterno();
        if (n.previous != null) n.previous.next = n.next;
        else first = n.next;
        if (n.next != null) n.next.previous = n.previous;
        else last = n.previous;
        size--;
    }

    //O(1)
    public int longitud() {
        return size;
    }
}

/*
IDEA:
=====

Un Handle es (informalmente...) una “referencia segura” al nodo interno de la lista, gracias a el obtenemos dos ventajas claves:

1) Encapsulamiento
-------------------
Si le dieramos al cliente acceso directo al objeto Nodo, estarías exponiendo la implementación interna de la LE (sus punteros next/previous, etc.).
En cambio, con el Handle, nosotos controlamos qué métodos ofrecer (por ejemplo, valor() y siguiente(), y no asi previo() que no es necesario
para este proyecto en particular) y ocultas el resto.

2) Operaciones O(1) basadas en posición
---------------------------------------
Muchas operaciones que queremos hacer (p. ej. insertarlo en un heap para luego extraer el más grande o 
eliminarlo rápidamente de la lista cuando “hackean” la transacción.) deben ser O(1) y no seria necesario en caso
de no utilizar un Handle. Por ejemplo:

Si solo devolvieramos el valor del nodo (o sea la Transaccion) cuando hacemos obtenerUltimo(), luego para, eliminar esa
transacción de la lista tendrías que buscarla (O(n)). En cambio, con el Handle ya tenemos un puntero a ese nodo y
eliminarRapido(handle) se puede hacer en O(1).
*/




