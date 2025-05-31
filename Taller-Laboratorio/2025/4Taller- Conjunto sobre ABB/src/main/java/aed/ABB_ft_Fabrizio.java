package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo _raiz;
    private int _cardinal;

    private class Nodo {
        T valor;
        Nodo padre;
        Nodo izq;
        Nodo der;

        Nodo(T v){
            valor = v;
            padre = null;
            izq = null;
            der = null;
        }
    }

    public ABB() {
        _raiz = null;
        _cardinal = 0;
    }

    private Nodo busquedaRecursiva(Nodo n, T elem){
        if(n == null){
            return null;
        }

        Nodo actual = n;

        if(actual.valor.compareTo(elem) > 0){
            if(actual.izq == null){ // Si el valor elem es menor al actual y este nodo no tiene hijo izq, dev nodo
                return actual;
            }else{
              actual = busquedaRecursiva(actual.izq,elem);
            }
        }
        
        if(actual.valor.compareTo(elem) < 0){
            if(actual.der == null){ // Si el valor elem es mayor al actual y este nodo no tiene hijo der, dev nodo
                return actual;
            }else{
              actual = busquedaRecursiva(actual.der,elem);
            }        
        }

        return actual; // Si nada de lo anterior se cumple, dev nodo

    }

    public int cardinal() {
        return _cardinal;
    }

    public T minimo(){
        Nodo actual = _raiz;
        while(actual.izq != null){
            actual = actual.izq;
        }

        return actual.valor;
    }

    public T maximo(){
        Nodo actual = _raiz;
        while(actual.der != null){
            actual = actual.der;
        }

        return actual.valor;    }

    public void insertar(T elem){

        Nodo nuevo = new Nodo(elem);
        if(!pertenece(elem)){
            _cardinal += 1;

            Nodo comparador = busquedaRecursiva(_raiz, elem);
            
            if(comparador == null){
                _raiz = nuevo;
            }else if(comparador.valor.compareTo(elem) > 0){
                comparador.izq = nuevo;
                nuevo.padre = comparador;
            }else if(comparador.valor.compareTo(elem) < 0){
                comparador.der = nuevo;
                nuevo.padre = comparador;
            }

        }

    }


    public boolean pertenece(T elem){
        boolean res = false;

        Nodo comparador = busquedaRecursiva(_raiz, elem);

        if(comparador == null){
            return res;
        }

        if(comparador.valor.compareTo(elem) == 0){
            res = true;
        }


        return res;
    }

    private Nodo predecesorInmediato(T elem){
        /* Esta función busca al predecesor inmediato del elemento elem,
            es decir, al máximo de su árbol izquierdo
        */

        // Consideraciones:

        /* 1. No pido que elem pertenezca al árbol ya que eso lo pido en la función eliminar
         * 2. Doy por hecho que el nodo que contiene el valor elem tiene dos hijos
         */

         Nodo eliminar = busquedaRecursiva(_raiz, elem); // Busco el nodo a eliminar
         Nodo predInm  = eliminar.izq; // me quedo con su sucesor izquierdo

         // Lo de arriba podia haberlo hecho con una sola variable, pero creo que así es más fácil de entender

         while(predInm.der != null){
            predInm = predInm.der;
         }

         return predInm;
    }

    private void eliminarRaiz(T elem){
        // Esta función se encarga del caso en que se desea eliminar una raíz
        // Verán 4 casos:
        /* 1. Raíz sin sucesores
         * 2. Raíz con sucesor izquierdo
         * 3. Raíz con sucesor derecho
         * 4. Raíz con los dos sucesores
         */

        if(_raiz.izq != null && _raiz.der != null){
                Nodo predInm = predecesorInmediato(elem); // Busco el predecesor inmediato
                T valor = predInm.valor;
                eliminar(valor);
                _cardinal +=1;
                _raiz.valor = valor;
            }
          
        else if(_raiz.izq != null && _raiz.der == null){
            _raiz = _raiz.izq;
            _raiz.padre = null;

        }else if(_raiz.izq == null && _raiz.der != null){
            _raiz = _raiz.der;
            _raiz.padre = null;
        }else if(_raiz.izq == null && _raiz.der == null){
            _raiz = null;
        }
    }

    public void eliminar(T elem){

        //Veo si el elemento está en el árbol
        if(_raiz.valor.compareTo(elem) == 0){
            _cardinal -= 1;
            eliminarRaiz(elem);
        }else if(pertenece(elem)){
            _cardinal -= 1;

            Nodo comparador = busquedaRecursiva(_raiz, elem).padre;
            T valorComparador = comparador.valor;

            //Divido los casos en base al padre del elemento

            if(valorComparador.compareTo(elem) > 0){
                Nodo izq = comparador.izq;
                if(izq.izq == null && izq.der == null){
                    comparador.izq = null;
                }

                if(izq.izq != null && izq.der == null){
                    izq.izq.padre = comparador;
                    comparador.izq = izq.izq;
                }

                if(izq.izq == null && izq.der != null){
                    izq.der.padre = comparador;
                    comparador.izq = izq.der;
                }

                  if(izq.izq != null && izq.der != null){
                   Nodo predInm = predecesorInmediato(elem); // Busco el predecesor inmediato
                    T valor = predInm.valor;
                    eliminar(valor); // eliminamos el nodo predInm
                    _cardinal +=1; // sumamos uno al cardinal, pues ejecutamos eliminar 2 veces y solo borramos 1 nodo
                    comparador.izq.valor = valor;
                    
                
                }
            }

            if(valorComparador.compareTo(elem) < 0){
                Nodo der = comparador.der;
                if(der.izq == null && der.der == null){
                    comparador.der = null;
                }

                if(der.izq != null && der.der == null){
                    der.izq.padre = comparador;
                    comparador.der = der.izq;
                }

                if(der.izq == null && der.der != null){
                    der.der.padre = comparador;
                    comparador.der = der.der;
                }

                
                if(der.izq != null && der.der != null){
                    Nodo predInm = predecesorInmediato(elem); // Busco el predecesor inmediato
                    T valor = predInm.valor;
                    eliminar(valor); // eliminamos el nodo predInm
                    _cardinal +=1; // sumamos uno al cardinal, pues ejecutamos eliminar 2 veces y solo borramos 1 nodo
                    comparador.der.valor = valor; // Reemplazo el valor del nodo a eliminar
                }
                
                
            }


        }

    }

    private Nodo sucesorInmediato(T elem){
        /* Esta función busca al predecesor inmediato del elemento elem,
            es decir, al máximo de su árbol izquierdo
        */

        // Consideraciones:

        /* 1. No pido que elem pertenezca al árbol ya que eso lo pido en la función eliminar
         * 2. Doy por hecho que el nodo que contiene el valor elem tiene dos hijos
         */

         Nodo eliminar = busquedaRecursiva(_raiz, elem); // Busco el nodo a eliminar
         Nodo sucInm  = eliminar.der; // me quedo con su sucesor izquierdo

         // Lo de arriba podia haberlo hecho con una sola variable, pero creo que así es más fácil de entender

         while(sucInm != null && sucInm.izq != null){
            sucInm = sucInm.izq;
         }

         return sucInm;
    }

    private String cadenaRecursiva(T elem){
        // Esta función está hecha pensando en que el ABB tiene raíz

        if(elem == maximo()){
            return "" + elem;
        }

        String cadena = "";
        cadena += elem + ",";

        Nodo suc = sucesorInmediato(elem);
        Nodo nodo = busquedaRecursiva(_raiz, elem);

        if(suc != null){
            cadena += cadenaRecursiva(suc.valor); 

        }else if(nodo.padre != null && nodo.padre.valor.compareTo(maximo()) <= 0){
            cadena += cadenaRecursiva(nodo.padre.valor);
        }

        return cadena;
    }

    public String toString(){

        if(_raiz == null){
            return "{}";
        }

        return "{" + cadenaRecursiva(minimo()) + "}";
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        ABB_Iterador(){
            _actual = busquedaRecursiva(_raiz, minimo());
        }


        public boolean haySiguiente() {            
            return _actual.valor.compareTo(maximo()) != 0;
        }
    
        public T siguiente() {
            T value = _actual.valor;
            if(haySiguiente()){
                
                Nodo suc = sucesorInmediato(_actual.valor);

                if(suc != null){
                    _actual = suc; 

                }else if(_actual.padre.valor.compareTo(value) < 0){
                    _actual = _actual.padre.padre;
                }else{
                    _actual = _actual.padre;
                }
            }
            return value;
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
