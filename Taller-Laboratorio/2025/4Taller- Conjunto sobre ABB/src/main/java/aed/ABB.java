package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    private Nodo _raiz;
    private int _cardinal;

    private class Nodo {
        // Agregar atributos privados del Nodo
        T valor;
        Nodo izq;
        Nodo der;
        Nodo padre;

        // Crear Constructor del nodo
        Nodo(T v) {
            valor = v;
            izq = null;
            der = null;
            padre = null;
        }
    }

    public ABB() {
        _raiz = null;
        _cardinal = 0;
    }

    public int cardinal() {
        return _cardinal;
    }

    public T minimo() {
        if (_raiz == null) {
            return null;
        }
        Nodo actual = _raiz;
        while (actual.izq != null) {
            actual = actual.izq;
        }
        return actual.valor;
    }

    public T maximo() {
        if (_raiz == null) {
            return null;
        }
        Nodo actual = _raiz;
        while (actual.der != null) {
            actual = actual.der;
        }
        return actual.valor;
    }

    public void insertar(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (_raiz == null) {
            _raiz = nuevo;
            _cardinal++;
            return;
        }
        Nodo padre = buscandoElNodoPadre(_raiz, elem);
        if (padre == null) {
            return;
        }
        nuevo.padre = padre;
        if (padre.valor.compareTo(elem) > 0) {
            padre.izq = nuevo;
        } else {
            padre.der = nuevo;
        }
        _cardinal++;
    }

    private Nodo buscandoElNodoPadre(Nodo actual, T elem) {

        if (actual == null) {
            return null;
        }
        if (elem.compareTo(actual.valor) == 0) {
            return null;
        }
        if (elem.compareTo(actual.valor) < 0) {
            if (actual.izq == null) {
                return actual;
            } else {
                return buscandoElNodoPadre(actual.izq, elem);
            }
        }
        if (elem.compareTo(actual.valor) > 0) {
            if (actual.der == null) {
                return actual;
            } else {
                return buscandoElNodoPadre(actual.der, elem);
            }
        }
        return null;
    }

    public boolean pertenece(T elem) {
        Nodo encontrado = buscandoElNodo(_raiz, elem);
        if (encontrado != null){
            return true;
        } else {
            return false;
        }
    }

    private Nodo buscandoElNodo(Nodo actual, T elem) {

        if (actual == null) {
            return null;
        }
        if (actual.valor.compareTo(elem) == 0) {
            return actual;
        } else if (actual.valor.compareTo(elem) > 0) {
            return buscandoElNodo(actual.izq, elem);
        } else {
            return buscandoElNodo(actual.der, elem);
        }
    }

    public void eliminar(T elem) {

        Nodo nodoAEliminar = buscandoElNodo(_raiz, elem);

        if (nodoAEliminar == null) {
            return;
        }

        if (nodoAEliminar.izq == null && nodoAEliminar.der == null) {
            if (nodoAEliminar.padre != null) {
                if (nodoAEliminar.padre.izq == nodoAEliminar) {
                    nodoAEliminar.padre.izq = null;
                } else {
                    nodoAEliminar.padre.der = null;
                }
            } else {
                _raiz = null;
            }
        } else if (nodoAEliminar.izq == null || nodoAEliminar.der == null) {
            Nodo hijo;
            if(nodoAEliminar.izq != null){
                hijo = nodoAEliminar.izq;
            } else {
                hijo = nodoAEliminar.der;
            }
            if (nodoAEliminar.padre != null) {

                if (nodoAEliminar.padre.izq == nodoAEliminar) {
                    nodoAEliminar.padre.izq = hijo;
                } else {
                    nodoAEliminar.padre.der = hijo;
                }
                hijo.padre = nodoAEliminar.padre;
            } else {
                _raiz = hijo;
                hijo.padre = null;
            }
        } else {
            Nodo sucesor = nodoMinimo(nodoAEliminar.der);
            nodoAEliminar.valor = sucesor.valor;
    
            if (sucesor.padre.izq == sucesor) {
                sucesor.padre.izq = sucesor.der;
            } else {
                sucesor.padre.der = sucesor.der;
            }
            if (sucesor.der != null) {
                sucesor.der.padre = sucesor.padre;
            }
        }
        _cardinal--;
    }

    private Nodo nodoMinimo(Nodo desde) {
        Nodo actual = desde;
        while (actual.izq != null) {
            actual = actual.izq;
        }
        return actual;
    }

    public String toString() {
        String res = "{";
        if (_cardinal != 0) {
            Iterador<T> iterador = this.iterador();
            res = res + iterador.siguiente();
            int i = 1;
            while (i < _cardinal) {
                res = res + "," + iterador.siguiente();
                i++;
            }
        }
        return res+"}";
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public ABB_Iterador() {
            _actual = _raiz;
            if (_actual != null) {
                while (_actual.izq != null) {
                    _actual = _actual.izq;
                }
            }
        }

        public boolean haySiguiente() {
            if (_actual.der != null){
                return true;
            }
            return false;
        }

        public T siguiente() {
            T valor = _actual.valor;

            if (haySiguiente()) { 
                _actual = _actual.der;
                while (_actual.izq != null) {
                    _actual = _actual.izq;
                }
            } else {
                Nodo padre = _actual.padre;
                while (padre != null && _actual == padre.der) {
                    _actual = padre;
                    padre = padre.padre;
                }
                _actual = padre;
            }
            return valor;
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
