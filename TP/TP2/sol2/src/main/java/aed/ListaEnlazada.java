package aed;
import java.util.*;

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

    // Esta clase sí es pública
    public class Handle {
        private Nodo nodo;
    
        private Handle(Nodo n) {
            this.nodo = n;
        }
    
        public T valor() {
            return nodo.valor;
        }
    
        public Handle siguiente() {
            return (nodo.next != null) ? new Handle(nodo.next) : null;
        }
    
        private Nodo getInterno() {
            return nodo;
        }
    }
    

    public ListaEnlazada() {
        first = null;
        last = null;
        size = 0;
    }

    public T valorUltimoNodo() {
        return last.valor;
    }

    public T valorPrimerNodo() {
        return first.valor;
    }

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

    public Handle obtenerPrimero() {
        if (first == null) {
            return null;  // Devolver null si la lista está vacía
        }
        return new Handle(first);
    }
    
    public Handle obtenerUltimo() {
        if (last == null) {
            return null;  // Devolver null si la lista está vacía
        }
        return new Handle(last);
    }

    /* PRE DEBUG DEL HACKEO
    public Handle obtenerUltimo() {
        return new Handle(last);
    }

    public Handle obtenerPrimero() {
        return new Handle(first);
    }
    */

    public void eliminarRapido(Handle h) {
    Nodo n = h.getInterno();
    if (n == null) return;
    if (n.previous != null) n.previous.next = n.next;
    else first = n.next;
    if (n.next != null) n.next.previous = n.previous;
    else last = n.previous;
    // Limpia los punteros del nodo eliminado para evitar referencias colgantes
    n.next = null;
    n.previous = null;
    size--;
}

    public int longitud() {
        return size;
    }
   
}


/*
package aed;

import java.util.*;

public class ListaEnlazada<T> { 
    private Nodo first;
    private Nodo last;
    private int size;


    private class Nodo {
        T valor;
        Nodo next;
        Nodo previous;

        Nodo( T v){ valor = v;}

        T valorNodo(){return valor;}
        Nodo siguiente(){return next;}
    }

    // Constructor
    public ListaEnlazada() {
        first = null;
        last = null;
        size = 0; //Fa: Falto esto
    }

    public T valorUltimoNodo(){
        return last.valorNodo(); 
    }

    public T valorPrimerNodo(){
        return first.valorNodo(); 
    }

    // Lo necesitamos para agregar el bloque al final de la cadena rapido
    // O(1)
    public void agregarAtras(T elem) {
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
    }

    // Lo necesitamos para eliminar directamente el nodo en HeapHandleLE.
    // O(1)
    public void eliminarRapido(Nodo n){
        if(n != first && n != last){
            n.previous.next = n.next;
            n.next.previous = n.previous;
        }else if(n == first){
            first = n.next;
            first.previous = null;
        }else{
            last = n.previous;
            last.next = null;
        }
        
        size--; 
    }

    // Lo necesitamos para 'bloques.longitud() en (2)'
    // O(1)
    public int longitud() {
        return size;
    }

    // Lo necesitamos en (2).
    // O(1)
    public Nodo obtenerUltimo() {
        return last;
    }

    //Fa: necesario para el (4); O(1)
    public Nodo obtenerPrimero(){
        return first;
    }

}


--------------

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
