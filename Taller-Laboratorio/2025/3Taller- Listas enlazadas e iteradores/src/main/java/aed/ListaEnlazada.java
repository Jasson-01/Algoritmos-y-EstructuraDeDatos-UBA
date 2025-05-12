package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    // Completar atributos privados
    private Nodo primero;
    private Nodo ultimo;

    private class Nodo {
        T valor; 
        Nodo sig; 
        Nodo ant; 

        Nodo(T v) {
            valor = v;
        } 
    }

    public ListaEnlazada() {
        primero = null; 
        ultimo = null; 
    }

    public int longitud() {
        int count = 0;
        Nodo actual = primero; 
        while (actual != null) {
            count++; 
            actual = actual.sig; 
        }
        return count;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem); 
        nuevo.sig = primero; 
        if (primero != null) {
            primero.ant = nuevo; 
            primero = nuevo; 
        } else {
            primero = nuevo;
            ultimo = nuevo;
        }
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem); 
        if (ultimo != null) {
            ultimo.sig = nuevo; 
            nuevo.ant = ultimo; 
            ultimo = nuevo; 
        } else {
            primero = nuevo; 
            ultimo = nuevo; 
        }
    }

    public T obtener(int i) {
        Nodo actual = primero; 
        if( i == 0) {
            return primero.valor; 
        }
        actual = actual.sig; 
        for (int j = 1; j < i; j++) { 
            actual = actual.sig; 
        }
        return actual.valor; 
    }

    public void eliminar(int i) {
        Nodo actual = primero;
       
        for (int j = 0; j < i; j++) { 
            actual = actual.sig; 
        }
        if (actual == primero) {
            primero = actual.sig;
            if (primero != null) { 
                primero.ant = null;
            } else {
                ultimo = null; 
            }
        }
        if (actual == ultimo) {
            ultimo = actual.ant;
            if (ultimo != null) {
                ultimo.sig = null;
            } else {
                primero = null; 
            }
        } 
        if (actual.ant != null && actual.sig != null) {
            actual.ant.sig = actual.sig;
            actual.sig.ant = actual.ant;
        }
           
    }
       
    public void modificarPosicion(int indice, T elem) {
        Nodo actual = primero;
        for (int j = 0; j < indice; j++) { 
            actual = actual.sig; 
        }
        if (actual == primero){
            actual.valor = elem; 
        }
        if (actual == ultimo){
            actual.valor = elem; 
        }
        if (actual.ant != null && actual.sig != null) {
            actual.valor = elem; 
        }
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        Nodo copiaActual = lista.primero; 
        while (copiaActual != null) {
            agregarAtras(copiaActual.valor); 
            copiaActual = copiaActual.sig;
        }
    }

    @Override
    public String toString() {
        
    }

    private class ListaIterador implements Iterador<T> {
        private T[] elementos;
        private int size;



        public boolean haySiguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }

        public boolean hayAnterior() {
            throw new UnsupportedOperationException("No implementada aun");
        }

        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }

        public T anterior() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        throw new UnsupportedOperationException("No implementada aun");
    }

}
