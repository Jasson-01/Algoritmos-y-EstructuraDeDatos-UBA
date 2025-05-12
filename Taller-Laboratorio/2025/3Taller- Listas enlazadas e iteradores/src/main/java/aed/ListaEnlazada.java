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
        if (i == 0) {
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
        if (actual == primero) {
            actual.valor = elem;
        }
        if (actual == ultimo) {
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
        Nodo copiaActual = this.primero;
        String cadenaListaCopiada = "[" + copiaActual.valor;
        for (int j = 1; j < this.longitud(); j++) {
            copiaActual = copiaActual.sig;
            cadenaListaCopiada += ", " + copiaActual.valor;
        }
        cadenaListaCopiada += "]";
        return cadenaListaCopiada;
    }

    private class ListaIterador implements Iterador<T> {

        int dedito;

        ListaIterador() {
            dedito = 0;
        }

        public boolean haySiguiente() {
            return dedito < longitud();
        }

        public boolean hayAnterior() {
            return dedito > 0;
        }

        public T siguiente() {
            Nodo copiaActual = primero;
            for (int j = 0; j < dedito; j++) {
                copiaActual = copiaActual.sig;
            }
            dedito++;
            return copiaActual.valor;
        }

        public T anterior() {
            Nodo copiaActual = primero;
            for (int j = 0; j < dedito - 1; j++) {
                copiaActual = copiaActual.sig;
            }
            dedito--;
            return copiaActual.valor;
        }
    }

    public Iterador<T> iterador() {
        return new ListaIterador();
    }

}
