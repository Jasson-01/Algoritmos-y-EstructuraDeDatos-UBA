package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo first;
    private Nodo last;
    private int size;


    private class Nodo {
        T valor;
        Nodo next;
        Nodo previous;

        Nodo( T v){ valor = v;}
    }

    public class Handle {
        private Nodo nodoApuntado;
        public Handle(Nodo n){
            this.nodoApuntado = n;
        }
    }

    public ListaEnlazada() {
        first = null;
        last = null;
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

    public void eliminar(Handle h) {
        Nodo actual = h.nodoApuntado;
        Nodo anterior;

        if (actual == first) {
            first = actual.next;
        } else {
            anterior = actual.previous;
            anterior.next = actual.next;
        }
        size--;
    }
    

    public void modificarValor(Handle h, T elem) { // Hacer con handle en lugar de indice
        Nodo actual = h.nodoApuntado;
        actual.valor = elem;
        /*  Nodo actual = first;
        if(indice == 0){
            actual.valor = elem;
        }

        actual = actual.next;
        int j = 1;
        while(j < indice){
            actual = actual.next;
            j++;
        }
        actual.valor = elem;     */      
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
