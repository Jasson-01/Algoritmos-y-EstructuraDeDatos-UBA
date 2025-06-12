package aed;

public class Berretacoin {
    // Atributos principales
    private int[] saldosUsuarios;
    private ListaEnlazada<Bloque> bloques;
    private int cantidadUsuarios;

    // Constructor
    public Berretacoin(int nUsuarios) {
        this.cantidadUsuarios = nUsuarios;
        this.saldosUsuarios = new int[nUsuarios]; // Todos los saldos arrancan en 0
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
                    // Actualizar saldos
                    int emisor = tx.emisor();
                    int receptor = tx.receptor();
                    saldosUsuarios[emisor] -= tx.monto();
                    saldosUsuarios[receptor] += tx.monto();
                }
            }
        }
        Bloque nuevoBloque = new Bloque(bloques.longitud(), listaTx, sumaMontos);
        bloques.agregarAtras(nuevoBloque);
    }
    public Transaccion txMayorValorUltimoBloque() {
        if (bloques.longitud() == 0) return null;
        Bloque ultimo = bloques.obtener(bloques.longitud() - 1);
        ListaEnlazada<Transaccion> txs = ultimo.transacciones();
        Transaccion maxTx = null;
        int maxMonto = Integer.MIN_VALUE;
        ListaEnlazada<Transaccion>.Nodo actual = txs.primerNodo();
        while (actual != null) {
            if (actual.valor != null && actual.valor.monto() > maxMonto) {
                maxMonto = actual.valor.monto();
                maxTx = actual.valor;
            }
            actual = actual.next;
        }
        return maxTx;
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
        int maxIdx = 0;
        int maxSaldo = Integer.MIN_VALUE;
        for (int i = 0; i < saldosUsuarios.length; i++) {
            if (saldosUsuarios[i] > maxSaldo) {
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
        int n = txs.longitud();
        ListaEnlazada<Transaccion>.Nodo actual = txs.primerNodo();
        while (actual != null) {
            suma += actual.valor.monto();
            actual = actual.next;
        }
        return n == 0 ? 0 : suma / n;
    }
    public void hackearTx() {
        // Si no hay bloques, no hay nada que hackear
        if (bloques.longitud() == 0) return;
        Bloque ultimo = bloques.obtener(bloques.longitud() - 1);
        ListaEnlazada<Transaccion> txs = ultimo.transacciones();
        ListaEnlazada<Transaccion>.Nodo actual = txs.primerNodo();
        ListaEnlazada<Transaccion>.Nodo maxNodo = null;
        int maxMonto = Integer.MIN_VALUE;
        // Buscar la transacción de mayor monto
        while (actual != null) {
            if (actual.valor != null && actual.valor.monto() > maxMonto) {
                maxMonto = actual.valor.monto();
                maxNodo = actual;
            }
            actual = actual.next;
        }
        // Si hay una transacción máxima, la hackeamos (por ejemplo, ponemos el monto en 0)
        if (maxNodo != null) {
            int emisor = maxNodo.valor.emisor();
            int receptor = maxNodo.valor.receptor();
            int montoOriginal = maxNodo.valor.monto();
            // Revertir el saldo original
            saldosUsuarios[emisor] += montoOriginal;
            saldosUsuarios[receptor] -= montoOriginal;
            // Cambiar el monto a 0
            // Suponiendo que Transaccion tiene un setter o puedes crear una nueva transacción con monto 0
            Transaccion txHackeada = new Transaccion(
                maxNodo.valor.id_comprador(),
                maxNodo.valor.id_comprador(),
                maxNodo.valor.id_vendedor(),
                0
            );
            maxNodo.valor = txHackeada;
        }
    }

}
