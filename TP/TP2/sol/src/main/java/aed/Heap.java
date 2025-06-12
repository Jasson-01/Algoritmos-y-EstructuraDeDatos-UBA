package aed;
import java.util.ArrayList;

/*public class Heap<T> {
    private ArrayList<T> array;

    T verRaiz(){
        return array.get(0);
    }

    //insertar (pensar en listas)
    //extraerRaiz
    //ordenar (función privada)

}*/


public class Heap<T extends Comparable<T>> {
    private ArrayList<T> array;

    public Heap() {
        array = new ArrayList<>();
    }

    public T verRaiz() {
        if (array.isEmpty()) return null;
        return array.get(0);
    }

    public void insertar(T elemento) {
        array.add(elemento);
        siftUp(array.size() - 1);
    }

    public T extraerRaiz() {
        if (array.isEmpty()) return null;
        T raiz = array.get(0);
        T ultimo = array.remove(array.size() - 1);
        if (!array.isEmpty()) {
            array.set(0, ultimo);
            siftDown(0);
        }
        return raiz;
    }

    private void siftUp(int i) {
        while (i > 0) {
            int padre = (i - 1) / 2;
            if (array.get(i).compareTo(array.get(padre)) < 0) {
                swap(i, padre);
                i = padre;
            } else {
                break;
            }
        }
    }

    private void siftDown(int i) {
        int n = array.size();
        while (true) {
            int izq = 2 * i + 1;
            int der = 2 * i + 2;
            int menor = i;

            if (izq < n && array.get(izq).compareTo(array.get(menor)) < 0)
                menor = izq;
            if (der < n && array.get(der).compareTo(array.get(menor)) < 0)
                menor = der;

            if (menor != i) {
                swap(i, menor);
                i = menor;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }
}

