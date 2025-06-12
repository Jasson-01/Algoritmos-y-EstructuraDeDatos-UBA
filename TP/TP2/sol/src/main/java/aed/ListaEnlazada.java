package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo first;
    private Nodo last;
    private int size;


    public class Nodo {
        T valor;
        Nodo next;
        Nodo previous;

        Nodo( T v){ valor = v;}
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

    public void eliminar(T valor) {
        Nodo actual = first;
        Nodo anterior = null;
        while(actual != null){
            if (actual.valor == valor) {
                if (anterior == null) {
                    first = actual.next;
                    if (first != null) first.previous = null;
                } else {
                    anterior.next = actual.next;
                    if (actual.next != null) actual.next.previous = anterior;
                }
                size--;
                return;
            }
            anterior = actual;
            actual = actual.next;
        }
    }

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

    public Nodo primerNodo() {
        return first;
    }
}
