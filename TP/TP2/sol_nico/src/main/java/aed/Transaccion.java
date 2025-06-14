package aed;

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
        int res;
        if (this.monto > otro.monto){
            res = 1;
        }else if(this.monto < otro.monto){
            res = -1;
        }else{
            if(this.id > otro.id){
                res = 1;
            }else{
                res = -1;
            }
        }

        return res;
    }

    @Override
    public boolean equals(Object otro){
        boolean otraEsNull = (otro == null);
        boolean claseDistinta = otro.getClass() != this.getClass();

        if (otraEsNull || claseDistinta){
            return false;
        }

        Transaccion otraTrans = (Transaccion) otro;

	    return this.monto == otraTrans.monto && this.id == otraTrans.id &&  this.id_comprador == otraTrans.id_comprador && this.id_vendedor == otraTrans.id_vendedor;
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
}