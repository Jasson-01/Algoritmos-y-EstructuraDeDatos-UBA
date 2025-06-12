package aed;

import java.util.ArrayList;

public class Berretacoin {
    private ListaEnlazada<Bloque> cb;
    private Heap<Usuario> usuarios;
    private ListaEnlazada<Transaccion> trans;

    public Berretacoin(int n_usuarios){
        usuarios = new Heap();

        int i = 0;

        while(i < n_usuarios){
            Usuario user = new Usuario(i);
            usuarios.insertar(user);
            i++;
        }
    }

    public void agregarBloque(Transaccion[] transacciones){
        throw new UnsupportedOperationException("Implementar!");
    }

    public Transaccion txMayorValorUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public Transaccion[] txUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public int maximoTenedor(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public int montoMedioUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public void hackearTx(){
        throw new UnsupportedOperationException("Implementar!");
    }
}
