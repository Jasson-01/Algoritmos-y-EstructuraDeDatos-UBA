package aed;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.Test;


public class NuestrosTests {
    
// Tests sobre Usuarios
    Usuario user = new Usuario(1);
    @Test
    public void montoUser(){

        user.sumar(10);
        assertEquals(10, user.patrimonio());

        user.sumar(-5);
        assertEquals(5, user.patrimonio());;
    }

    @Test
    public void compararUsers(){
        Usuario user_2 = new Usuario(2);
        Usuario user_3 = new Usuario(3);

        assertEquals(user_3.compareTo(user),-1);
        assertEquals(user_2.compareTo(user_3),1);
        assertEquals(user_2.compareTo(user),-1);
        assertEquals(user.compareTo(user_2),1);

        assertTrue(user.equals(user));
        assertFalse(user.equals(user_2));
    }

// Tests sobre Transacciones

    Transaccion trans = new Transaccion(0, 0, 1, 1);
    Transaccion trans_2 = new Transaccion(1,1,2,1);
    Transaccion trans_3 = new Transaccion(2, 2, 1, 2);

    @Test
    public void compararTrans(){
        assertTrue(trans.equals(trans));
        assertFalse(trans.equals(trans_2));

        assertEquals(trans.compareTo(trans_2), -1);
        assertEquals(trans_3.compareTo(trans), 1);
    }
// Tests sobre Bloques

    Bloque bloque = new Bloque(0, new ListaEnlazada<Transaccion>(), 0);

    @Test
    public void testActualizarBloque() {
        assertEquals(0, bloque.montoAcumulado());
        ListaEnlazada<Transaccion> nuevaLE = new ListaEnlazada<Transaccion>();
        nuevaLE.agregarAtras(trans);
        nuevaLE.agregarAtras(trans_2);
        nuevaLE.agregarAtras(trans_3);

        int nuevoMonto = 0;

        ListaEnlazada<Transaccion>.Handle actual = nuevaLE.obtenerPrimero();
        for(int i = 0; i < nuevaLE.longitud(); i++){
            if(actual.valor().id_comprador() != 0){
              nuevoMonto += actual.valor().monto();
            }
        }
        bloque.actualizar(nuevaLE, nuevoMonto);
        assertEquals(nuevoMonto, bloque.montoAcumulado());
        assertEquals(nuevaLE, bloque.transacciones());

    }

// Tests para el Heap
    @Test
    public void testInsertarYVerRaiz() {
        Heap<Integer> heap = new Heap<>();
        heap.insertar(4);
        assertEquals(4, heap.verRaiz());

        heap.insertar(10);
        assertEquals(10, heap.verRaiz());

        heap.insertar(3);
        assertEquals(10, heap.verRaiz());

        heap.insertar(8);
        assertEquals(10, heap.verRaiz());

        heap.insertar(15);
        assertEquals(15, heap.verRaiz());
    }

    @Test
    public void testExtraerRaiz() {
        Heap<Integer> heap = new Heap<>();
        heap.insertar(50);
        heap.insertar(40);
        heap.insertar(20);
        heap.insertar(30);
        heap.insertar(15);
        heap.insertar(5);
        heap.insertar(3);
        heap.insertar(10);

        // Estado inicial: raíz debe ser 50
        assertEquals(50, heap.verRaiz());

        int extraido = heap.extraerRaiz();
        assertEquals(50, extraido);
        assertEquals(40, heap.verRaiz());

        extraido = heap.extraerRaiz();
        assertEquals(40, extraido);
        assertEquals(30, heap.verRaiz());
    }

    @Test
    public void testHeapConUnSoloElemento() {
        Heap<Integer> heap = new Heap<>();
        heap.insertar(7);
        assertEquals(7, heap.verRaiz());

        int extraido = heap.extraerRaiz();
        assertEquals(7, extraido);
        assertNull(heap.verRaiz());
    }

    @Test
    public void testHeapVacio() {
        Heap<Integer> heap = new Heap<>();
        assertNull(heap.verRaiz());
        assertNull(heap.extraerRaiz());
    }



}

