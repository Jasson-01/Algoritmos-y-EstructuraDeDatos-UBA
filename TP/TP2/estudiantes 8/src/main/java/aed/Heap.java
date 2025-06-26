package aed;
import java.util.ArrayList;

/*IDEA:
 * Vamos a hacer la implementacion sobre un ArrayList (por lo debatido en la Teorica sobre la mayor facilidad 
 * a la hora de navegar en el). Los algoritmos a implemntar son los siguientes:
 * - verRaiz
 * Devuelve el maximo, que se encuentra en la raiz.
 * - insertar
 * Queremos ubicar el elemento respetanoo la forma del Heap. Usamos la idea propuesta en clase de
 * 'Shift Up' (Mientras sea mayor que su padre, intercambiarlo con el padre).
 * - extraerRaiz
 * Queremos reemplazar la raiz del arbol por el 'ultimo' elemento, respetando la forma del Heap. Usamos
 * la idea propuesta en clase de 'Shift Down' (Mientras sea menor que uno de sus hijos, intercambiarlo
 * con el mayor de sus hijos).
*/


public class Heap<T extends Comparable<T>> {
    private ArrayList<T> array;

    // Constructor
    public Heap() { 
        array = new ArrayList<>(); // O(1)
    }

    // O(1)
    public T verRaiz() {
        if (array.isEmpty()) return null; // O(1)
        return array.get(0); // O(1)
    }
    // 0(1): Acceder al índice 0 de un ArrayList toma una operacion.

    // O(log n)
    public void insertar(T elemento) {
        array.add(elemento); // O(1) 
        siftUp(array.size() - 1); // O(log n)
    }
    // Insertar al final es O(1). SiftUp sube como máximo log(n) niveles.

    // O(log n)
    public T extraerRaiz() {
        if (array.isEmpty()) return null; // O(1)

        T raiz = array.get(0); // O(1)
        T ultimo = array.remove(array.size() - 1); // O(1)

        if (!array.isEmpty()) {
            array.set(0, ultimo); // O(1)
            siftDown(0); // O(log n)
        }
        return raiz; // O(1)
    }
    // Remover último y reemplazar la raíz es O(1) pero siftDown baja como maximo log(n) niveles.



    public class Tupla {
        int indice;
        T valor;

        Tupla( int i, T v){ indice = i; valor = v;}
    }

    // O(log n)
    public  ArrayList<Tupla> siftUp(int i) { 

        /*Crear clase privada  tipo int para indice, y T para valor*/

        ArrayList<Tupla> swapeado = new ArrayList<Tupla>();
        while (i > 0) {  // Hasta log n iteraciones
            int padre = (i - 1) / 2; // O(1)
            
            if (array.get(i).compareTo(array.get(padre)) > 0) { // O(1)
                Tupla t = new Tupla(i, array.get(padre));
                swap(i, padre); // O(1)
                swapeado.add(t);

                i = padre; // O(1)
            } else {
                break;
            }
        }
        Tupla t = new Tupla(i, array.get(i)); 
        swapeado.add(t); // O(1)
        return swapeado;
    }
    // En el peor caso, un nodo puede subir desde el fondo hasta la raíz. Eso es log(n) pasos.

    // O(log n)
    public ArrayList<Tupla>  siftDown(int i) { 
        int n = array.size();  // O(1)
        ArrayList<Tupla> swapeado = new ArrayList<Tupla>();
        while (true) { // Hasta log n iteraciones
            int izq = 2 * i + 1; // O(1)
            int der = 2 * i + 2; // O(1)
            int mayor = i; // O(1)

            if (izq < n && array.get(izq).compareTo(array.get(mayor)) > 0) // O(1)
                mayor = izq;

            if (der < n && array.get(der).compareTo(array.get(mayor)) > 0) // O(1)
                mayor = der;

            if (mayor != i) {
                Tupla t = new Tupla(i, array.get(mayor));
                swap(i, mayor); // O(1)
                swapeado.add(t);
                i = mayor; // O(1)
            } else {

                break; // El nodo ya es mayor que ambos hijos
            }
        }
        Tupla t = new Tupla(i, array.get(i));
        swapeado.add(t); // O(1)
        return swapeado;
    }
    // En el peor caso, un nodo puede bajar desde la raíz hasta una hoja. Eso es log(n) pasos.

    // O(1)
    private void swap(int i, int j) {
        T temp = array.get(i); // O(1)
        array.set(i, array.get(j)); // O(1)
        array.set(j, temp); // O(1)
        }
    
    // Acceder y modificar elementos en un ArrayList por índice es O(1).

    public T get(int i) { 
        return array.get(i); // O(1)
    }

}


/*
JUSTIFICACION DE LA COMPLEJIDAD PARA SIFT DOWN/UP.

Supongamos un estado incial:
Array interno               Árbol representado gráficamente
[50, 30, 20, 10, 15, 5, 3]               50
                                       /    \
                                    30      20
                                   /  \     /  \
                                 10   15   5     3

Que pasa si INSERTAMOS un elemento?
-----------------------------------

Supongamos que queremos agrergar el 40,

Internamente dentro de 'Insterar':
-Se agrega al final → [50, 30, 20, 10, 15, 5, 3, 40];
-Se llama a siftUp(7) (posición de 40);

Ahora, dentro de siftUp:

40 (índice 7) > 10 (índice 3) → swap
→ [50, 30, 20, 40, 15, 5, 3, 10]

40 (índice 3) > 30 (índice 1) → swap
→ [50, 40, 20, 30, 15, 5, 3, 10]

40 (índice 1) < 50 (índice 0) → stop

Notemos como nos ahorramos el reccorrido de toda la lista gracias al
algoritmo implementado... (hicimos 3 iteraciones). Finalmente obtuvimos:

        50
      /    \
    40      20
   /  \    /  \
  30  15  5    3
 /
10

Que pasa si EXTRAEMOS un elemento?
----------------------------------

Supongamos que el estado inicial es el ultimo que nos quedo,

Internamente dentro de 'Extraer':
-Se quita 50, se mueve último (10) a la raíz → [10, 40, 20, 30, 15, 5, 3]

Array interno:
[10, 40, 20, 30, 15, 5, 3]

    Representación como árbol:
            10
          /    \
        40      20
       /  \    /  \
     30  15   5   3

Llamamos a siftDown(0):

=== Iteración 1 ===
i = 0
izq = 1, der = 2
mayor = 0

Comparo array[1] = 40 con array[0] = 10 ⇒ 40 > 10 ⇒ mayor = 1
Comparo array[2] = 20 con array[1] = 40 ⇒ 20 < 40 ⇒ sin cambio

Swap entre índice 0 y 1 ⇒ array = [40, 10, 20, 30, 15, 5, 3]
Actualizo i = 1

=== Iteración 2 ===
i = 1
izq = 3, der = 4
mayor = 1

Comparo array[3] = 30 con array[1] = 10 ⇒ 30 > 10 ⇒ mayor = 3
Comparo array[4] = 15 con array[3] = 30 ⇒ 15 < 30 ⇒ sin cambio

Swap entre índice 1 y 3 ⇒ array = [40, 30, 20, 10, 15, 5, 3]
Actualizo i = 3

=== Iteración 3 ===
i = 3
izq = 7, der = 8 ⇒ fuera de rango

No hay hijos ⇒ termina el siftDown

Resultado final del heap:
[40, 30, 20, 10, 15, 5, 3]

Representación final como árbol:
            40
          /    \
        30      20
       /  \    /  \
     10  15   5   3

Misma idea que don siftUp, nos ahorramos iterar todo la lista y resolvimos
el problema con 3 iteraciones (en este caso en particular... semejante al 
peor), gracias al algoritmo.
*/



