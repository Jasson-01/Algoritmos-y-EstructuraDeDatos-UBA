package aed;

import java.util.*;

public class ListaEnlazada<T extends Comparable<T>> { 
    private Nodo first;
    private Nodo last;
    private int size;


    private class Nodo {
        T valor;
        Nodo next;
        Nodo previous;

        Nodo( T v){ valor = v;}
    }

    public class Handle implements Comparable<Handle> {
        private Nodo handle;
        private Handle(Nodo handle) {
            this.handle = handle;
        }
        public T valor() {
            return handle.valor;
        }
        public Handle siguiente() {
            return handle.next == null ? null : new Handle(handle.next);
        }
        public Nodo getNodo() {
            return handle;
        }

        @Override
        public int compareTo(Handle otro) {
            return this.valor().compareTo(otro.valor());
        }
    }


    // Constructor
    public ListaEnlazada() {
        first = null;
        last = null;
        size = 0; //Fa: Falto esto
    }

    // Lo necesitamos para agregar el bloque al final de la cadena rapido
    // O(1)
    public Handle agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if(first == null){
            first = nuevo;
            last = nuevo;
        }else{
            last.next = nuevo;
            nuevo.previous= last;
            last = nuevo;
        }
        size++;
        return new Handle(nuevo);
    }

    // Lo necesitamos para eliminar directamente el nodo en HeapHandleLE.
    // O(1)
    public void eliminarRapido(Handle h){
        if (h == null) return;
        Nodo n = h.getNodo();
        if (n == null) return;
        // Si el nodo ya no está en la lista, no hacer nada
        if (n.previous != null) n.previous.next = n.next;
        else first = n.next;
        if (n.next != null) n.next.previous = n.previous;
        else last = n.previous;
        size--;
    }

    // Lo necesitamos para 'bloques.longitud() en (2)'
    // O(1)
    public int longitud() {
        return size;
    }

    // Lo necesitamos en (2).
    // O(1)
    public Handle obtenerUltimo() {
        return last == null ? null : new Handle(last);
    }

    //Fa: necesario para el (4); O(1)
    public Handle obtenerPrimero(){
        return first == null ? null : new Handle(first);
    }

}



/*
    public void modificarValor(int indice, T elem) { 
        Nodo actual = first;
        if(indice == 0){
            actual.valor = elem;
        }

        actual = actual.next;
        int j = 1;
        while(j < indice){
            actual = actual.next;
            j++;
        }
        actual.valor = elem;       
    }

    public int longitud() {
        return size;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);

        if(first == null){
            first = nuevo;
            last = nuevo;
        }else{
            nuevo.next = first;
            first = nuevo;
        }

        size++;
       
    }

    public T obtener(int i) {
        Nodo actual = first;
        if(i == 0){
            return actual.valor;
        }

        actual = actual.next;
        int j = 1;
        while(j < i){
            actual = actual.next;
            j++;
        }

        return actual.valor;
    }

    public void eliminar(T valor) {
        Nodo actual = first;
        Nodo anterior;

        while(){
            if (actual.valor == valor) {
                actual = actual.next;
            }else{
                anterior = actual.previous;
                anterior.next = actual.next;
            }
        }

        size--;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        Nodo actual = lista.first;
        while(actual != null){
            agregarAtras(actual.valor);
            actual = actual.next;
        }
    }

    @Override
    public String toString() {
        Nodo actual = first;
        String cadena = "";

        while(actual != null){
            cadena += actual.valor;
            if(actual.next != null){
                cadena += ", ";
            }
            actual = actual.next;
        }
        
        return "[" + cadena + "]";

    }

    private class ListaIterador implements Iterador<T> {

            int dedito;
            ListaIterador(){
                dedito = 0;
            }

        public boolean haySiguiente() {

            if ( dedito + 1 <= size) {
                return true;
            }else{
                return false;
            }
        }
        
        public boolean hayAnterior() {
	        if(dedito - 1 < 0){
                return false;
            }else{
                return true;
            }
        }

        public T siguiente() {
	        T nodo = obtener(dedito);
            dedito++;
            return nodo;
        }
        

        public T anterior() {
            T nodo = obtener(dedito-1);
            dedito--;
            return nodo;        }
    }

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }

}
*/
