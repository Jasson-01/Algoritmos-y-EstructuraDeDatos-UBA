package aed;

public class Berretacoin {
    // Atributos principales
    private int[] saldosUsuarios;
    private ListaEnlazada<Bloque> bloques;
    private int cantidadUsuarios;

    // Constructor
    public Berretacoin(int nUsuarios) {
        this.cantidadUsuarios = nUsuarios;
        this.saldosUsuarios = new int[nUsuarios + 1]; // Tamaño n+1, índice 0 no se usa
        this.bloques = new ListaEnlazada<>();
    }

    // Métodos a implementar:
    // agregarBloque, txMayorValorUltimoBloque, txUltimoBloque, maximoTenedor, montoMedioUltimoBloque, hackearTx
    // ...

    public void agregarBloque(Transaccion[] transacciones) {
        ListaEnlazada<Transaccion> listaTx = new ListaEnlazada<>();
        int sumaMontos = 0;
        if (transacciones != null) {
            for (Transaccion tx : transacciones) {
                if (tx != null) {
                    listaTx.agregarAtras(tx);
                    sumaMontos += tx.monto();
                    int comprador = tx.id_comprador();
                    int vendedor = tx.id_vendedor();
                    if (comprador == 0) {
                        if (vendedor > 0 && vendedor < saldosUsuarios.length) {
                            saldosUsuarios[vendedor] += tx.monto();
                        }
                    } else {
                        if (comprador > 0 && comprador < saldosUsuarios.length) {
                            saldosUsuarios[comprador] -= tx.monto();
                        }
                        if (vendedor > 0 && vendedor < saldosUsuarios.length) {
                            saldosUsuarios[vendedor] += tx.monto();
                        }
                    }
                }
            }
        }
        Bloque nuevoBloque = new Bloque(bloques.longitud(), listaTx, sumaMontos);
        bloques.agregarAtras(nuevoBloque);
    }

    public Transaccion txMayorValorUltimoBloque() {
        if (bloques.longitud() == 0) return null;
        Bloque ultimo = bloques.obtener(bloques.longitud() - 1);
        // Usar HeapHandle para obtener la transacción máxima
        return ultimo.heapTransacciones().verRaiz();
    }

    public Transaccion[] txUltimoBloque() {
        if (bloques.longitud() == 0) return new Transaccion[0];
        Bloque ultimo = bloques.obtener(bloques.longitud() - 1);
        ListaEnlazada<Transaccion> txs = ultimo.transacciones();
        int n = txs.longitud();
        Transaccion[] arr = new Transaccion[n];
        ListaEnlazada<Transaccion>.Nodo actual = txs.primerNodo();
        int i = 0;
        while (actual != null) {
            arr[i++] = actual.valor;
            actual = actual.next;
        }
        return arr;
    }

    public int maximoTenedor() {
        int maxSaldo = Integer.MIN_VALUE;
        int maxIdx = 1;
        for (int i = 1; i < saldosUsuarios.length; i++) {
            if (saldosUsuarios[i] > maxSaldo || (saldosUsuarios[i] == maxSaldo && i < maxIdx)) {
                maxSaldo = saldosUsuarios[i];
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    public int montoMedioUltimoBloque() {
        if (bloques.longitud() == 0) return 0;
        Bloque ultimo = bloques.obtener(bloques.longitud() - 1);
        ListaEnlazada<Transaccion> txs = ultimo.transacciones();
        int suma = 0;
        int n = 0;
        ListaEnlazada<Transaccion>.Nodo actual = txs.primerNodo();
        while (actual != null) {
            if (actual.valor.id_comprador() != 0) {
                suma += actual.valor.monto();
                n++;
            }
            actual = actual.next;
        }
        return n == 0 ? 0 : suma / n;
    }

    public void hackearTx() {
        if (bloques.longitud() == 0) return;
        Bloque ultimo = bloques.obtener(bloques.longitud() - 1);
        ListaEnlazada<Transaccion> txs = ultimo.transacciones();
        ListaEnlazada<Transaccion>.Nodo actual = txs.primerNodo();
        ListaEnlazada<Transaccion>.Nodo maxNodo = null;
        int maxMonto = Integer.MIN_VALUE;
        int minOrden = Integer.MAX_VALUE;
        int orden = 0;
        // Buscar la transacción de mayor monto, desempate por primer aparición
        while (actual != null) {
            if (actual.valor != null) {
                int monto = actual.valor.monto();
                if (monto > maxMonto || (monto == maxMonto && orden < minOrden)) {
                    maxMonto = monto;
                    minOrden = orden;
                    maxNodo = actual;
                }
            }
            actual = actual.next;
            orden++;
        }
        if (maxNodo != null && maxMonto > 0) {
            int comprador = maxNodo.valor.id_comprador();
            int vendedor = maxNodo.valor.id_vendedor();
            int montoOriginal = maxNodo.valor.monto();
            // Revertir el saldo original SOLO si el monto era mayor a 0
            if (comprador == 0) {
                if (vendedor > 0 && vendedor < saldosUsuarios.length) {
                    saldosUsuarios[vendedor] -= montoOriginal;
                }
            } else {
                if (comprador > 0 && comprador < saldosUsuarios.length) {
                    saldosUsuarios[comprador] += montoOriginal;
                }
                if (vendedor > 0 && vendedor < saldosUsuarios.length) {
                    saldosUsuarios[vendedor] -= montoOriginal;
                }
            }
            // Cambiar el monto a 0
            Transaccion txHackeada = new Transaccion(
                maxNodo.valor.id,
                maxNodo.valor.id_comprador(),
                maxNodo.valor.id_vendedor(),
                0
            );
            maxNodo.valor = txHackeada;
            // Reconstruir el heap del bloque en el mismo orden de la lista
            HeapHandle nuevoHeap = new HeapHandle();
            ListaEnlazada<Transaccion>.Nodo nodo = txs.primerNodo();
            int idx = 0;
            while (nodo != null) {
                nuevoHeap.agregar(nodo, idx);
                nodo = nodo.next;
                idx++;
            }
            try {
                java.lang.reflect.Field heapField = ultimo.getClass().getDeclaredField("heapTx");
                heapField.setAccessible(true);
                heapField.set(ultimo, nuevoHeap);
            } catch (Exception e) {
                // No hacer nada
            }
        }
    }

}
