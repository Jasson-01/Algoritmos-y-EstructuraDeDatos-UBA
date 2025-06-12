package aed;
import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T> {
    private ArrayList<T> array;
    private Comparator<T> cmp;

    public Heap() {
        this((a, b) -> ((Comparable<T>) a).compareTo(b));
    }

    public Heap(Comparator<T> cmp) {
        array = new ArrayList<>();
        this.cmp = cmp;
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
            if (cmp.compare(array.get(i), array.get(padre)) > 0) { // heap de máximo
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
            int mayor = i;

            if (izq < n && cmp.compare(array.get(izq), array.get(mayor)) > 0)
                mayor = izq;
            if (der < n && cmp.compare(array.get(der), array.get(mayor)) > 0)
                mayor = der;

            if (mayor != i) {
                swap(i, mayor);
                i = mayor;
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

