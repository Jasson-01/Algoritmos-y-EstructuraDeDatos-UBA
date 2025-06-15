package aed;

import java.util.Objects;

public class Transaccion implements Comparable<Transaccion> {
    private int id;
    private int id_comprador;
    private int id_vendedor;
    private int monto;

    public Transaccion(int id, int id_comprador, int id_vendedor, int monto) {
        this.id = id;
        this.id_comprador = id_comprador;
        this.id_vendedor = id_vendedor;
        this.monto = monto;
    }

    // Para poder usar el id desde el HeapHandleLE
    public int id() {
        return this.id;
    }

    @Override
    public int compareTo(Transaccion otro) {
        if (this.monto > otro.monto) {
            return 1;
        } else if (this.monto < otro.monto) {
            return -1;
        } else {
            // Mayor id es mayor (para max-heap de transacciones)
            if (this.id > otro.id) {
                return 1;
            } else if (this.id < otro.id) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public boolean equals(Object otro) {
        /*
         * boolean otraEsNull = (otro == null);
         * boolean claseDistinta = otro.getClass() != this.getClass();
         * 
         * if (otraEsNull || claseDistinta){
         * return false;
         * }
         * 
         * Transaccion otraTrans = (Transaccion) otro;
         * 
         * return this.monto == otraTrans.monto && this.id == otraTrans.id &&
         * this.id_comprador == otraTrans.id_comprador && this.id_vendedor ==
         * otraTrans.id_vendedor;
         */
        if (this == otro)
            return true;
        if (otro == null || getClass() != otro.getClass())
            return false;

        Transaccion t = (Transaccion) otro;

        return this.id == t.id &&
                this.id_comprador == t.id_comprador &&
                this.id_vendedor == t.id_vendedor &&
                this.monto == t.monto;
    }

    @Override
    public String toString() {
        return "Transaccion{id=" + id + ", comprador=" + id_comprador +
                ", vendedor=" + id_vendedor + ", monto=" + monto + '}';
    }

    // @Override
    // public int hashCode() {
    // return Objects.hash(id, id_comprador, id_vendedor, monto);
    // }

    public int monto() {
        return monto;
    }

    public int id_comprador() {
        return id_comprador;
    }

    public int id_vendedor() {
        return id_vendedor;
    }
}