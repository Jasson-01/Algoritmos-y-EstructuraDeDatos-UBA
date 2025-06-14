package aed;

import java.util.*;

public class ListaEnlazada<T> { 
    private Nodo first;
    private Nodo last;
    private int size;


    public class Nodo {
        T valor;
        Nodo next;
        Nodo previous;

        Nodo( T v){ valor = v;}
    }

    // Constructor
    public ListaEnlazada() {
        first = null;
        last = null;
        size = 0; //Fa: Falto esto
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
        if (n == null) return;
        if (n == first && n == last) {
            first = null;
            last = null;
        } else if (n == first) {
            first = n.next;
            if (first != null) first.previous = null;
        } else if (n == last) {
            last = n.previous;
            if (last != null) last.next = null;
        } else {
            n.previous.next = n.next;
            n.next.previous = n.previous;
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



//Nico


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
