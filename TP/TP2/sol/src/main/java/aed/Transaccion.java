package aed;

public class Transaccion implements Comparable<Transaccion> {
    private int id;
    private int id_comprador;
    private int id_vendedor;
    private int monto;
    private static int nextUid = 0;
    private int uid;

    public Transaccion(int id, int id_comprador, int id_vendedor, int monto) {
        this.id = id;
        this.id_comprador = id_comprador;
        this.id_vendedor = id_vendedor;
        this.monto = monto;
        this.uid = nextUid++;
    }

    // Para poder usar el id desde el HeapHandleLE
    public int id() {
        return this.id;
    }

    @Override
    public int compareTo(Transaccion otro) {
        if (this.monto != otro.monto) {
            return Integer.compare(otro.monto, this.monto); // heap de máximo: mayor monto primero
        }
        return Integer.compare(this.id, otro.id); // menor id primero en empate
    }

    @Override
    public boolean equals(Object otro){
        if (this == otro) return true;
        if (otro == null || getClass() != otro.getClass()) return false;
        Transaccion otraTrans = (Transaccion) otro;
        return this.id == otraTrans.id &&
               this.id_comprador == otraTrans.id_comprador &&
               this.id_vendedor == otraTrans.id_vendedor &&
               this.monto == otraTrans.monto;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(id);
        result = 31 * result + Integer.hashCode(id_comprador);
        result = 31 * result + Integer.hashCode(id_vendedor);
        result = 31 * result + Integer.hashCode(monto);
        return result;
    }

    public int monto() {
        return monto;
    }

    public int id_comprador() {
        return id_comprador;
    }
    
    public int id_vendedor() {
        return id_vendedor;
    }

    public int uid() {
        return this.uid;
    }
}