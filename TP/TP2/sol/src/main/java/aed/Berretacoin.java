package aed;

import java.util.ArrayList;

public class Berretacoin {
    private ListaEnlazada<Bloque> bloques;
    private HeapHandleLE heapHandleLE; 
    private HeapHandleArray heapHandleArray; 


    public Berretacoin(int n_usuarios){
        this.bloques = new ListaEnlazada<>();
        this.heapHandleLE = new HeapHandleLE();
        this.heapHandleArray = new HeapHandleArray(n_usuarios); //O(P)
    }

    public void agregarBloque(Transaccion[] transacciones){
        if (transacciones == null || transacciones.length == 0) return; // No agregar bloques vacíos
        ListaEnlazada<Transaccion> nuevoBloqueTxs = new ListaEnlazada<>();
        int montoAcumulado = 0;
        for (Transaccion t : transacciones) {
            nuevoBloqueTxs.agregarAtras(t);
            ListaEnlazada<Transaccion>.Handle handleTx = nuevoBloqueTxs.obtenerUltimo();
            heapHandleLE.insertar(handleTx);
            if (t.id_comprador() == 0) {
                if (t.id_vendedor() > 0) {
                    heapHandleArray.actualizarPatrimonio(t.id_vendedor() - 1, t.monto());
                }
            } else {
                if (t.id_comprador() > 0) {
                    heapHandleArray.actualizarPatrimonio(t.id_comprador() - 1, -t.monto());
                }
                if (t.id_vendedor() > 0) {
                    heapHandleArray.actualizarPatrimonio(t.id_vendedor() - 1, t.monto());
                }
                montoAcumulado += t.monto(); // Solo sumar si no es de creación
            }
        }
        int id_bloque = bloques.longitud();
        Bloque bloque = new Bloque(id_bloque, nuevoBloqueTxs, montoAcumulado);
        bloques.agregarAtras(bloque);
    }

/*
EJEMPLO DETALLADO DE agregarBloque PASO A PASO

Supongamos que se llama a:

agregarBloque(new Transaccion[] {
    new Transaccion(0, 0, 1, 50), // Transacción de creación al usuario 1
    new Transaccion(1, 2, 3, 10), // Transferencia del 2 al 3
    new Transaccion(2, 1, 2, 30)  // Transferencia del 1 al 2
});

------------------------------------------------------------
ESTRUCTURAS INICIALES:
usuarios[] = [null, (1, 0), (2, 0), (3, 0)] // La creamos en (1)
bloques = vacía
heapHandleLE.heap = vacío
heapHandleLE.handle = vacío
------------------------------------------------------------

1. Creamos nuevoBloqueTxs = nueva ListaEnlazada<Transaccion>
2. Iteramos por cada transacción:

------------------------------------------------------------
(1) Transacción t0: (id=0, comprador=0, vendedor=1, monto=50)

2.1 Se agrega a nuevoBloqueTxs → se crea Nodo0 con valor t0
2.2 Nodo0 = nuevoBloqueTxs.obtenerUltimo() // Necesito que sea un nodo xq asi lo requiere heaphandleLE.agregar()
2.3 heapHandleLE.agregar(Nodo0)

  Dentro de heapHandleLE.agregar:
    - heap.insertar(t0): agrega Transacción(0, ..., 50) al heap.
    - handle.set(0, Nodo0): guarda Nodo0 en handle[0] --> La guarda en el id='id'=0 (en orden...)

2.4 Como t0 es de creación (id_comprador == 0):
    usuarios[1].sumar(50)

------------------------------------------------------------
(2) Transacción t1: (id=1, comprador=2, vendedor=3, monto=10)

2.1 Se agrega a nuevoBloqueTxs → se crea Nodo1 con valor t1
2.2 Nodo1 = nuevoBloqueTxs.obtenerUltimo()
2.3 heapHandleLE.agregar(Nodo1)

  Dentro de heapHandleLE.agregar:
    - heap.insertar(t1)
    - handle.set(1, Nodo1)

2.4 No es de creación:
    usuarios[2].restar(10) → patrimonio = -10 -> Aca no se habria un problema... No seria correcto que haya patrimonois negativos...
    usuarios[3].sumar(10)  → patrimonio = 10

------------------------------------------------------------
(3) Transacción t2: (id=2, comprador=1, vendedor=2, monto=30)

2.1 Se agrega a nuevoBloqueTxs → se crea Nodo2 con valor t2
2.2 Nodo2 = nuevoBloqueTxs.obtenerUltimo()
2.3 heapHandleLE.agregar(Nodo2)

  Dentro de heapHandleLE.agregar:
    - heap.insertar(t2)
    - handle.set(2, Nodo2)

2.4 No es de creación:
    usuarios[1].restar(30) → patrimonio = 20
    usuarios[2].sumar(30)  → patrimonio = 20

------------------------------------------------------------
3. Calculamos id_bloque = bloques.longitud() → 0 (seria el primero)
4. Calculamos montoAcumulado = 10 + 30 = 40 (sin el de creación)
5. Creamos nuevo Bloque:
   bloque = new Bloque(0, nuevoBloqueTxs, 40)
6. bloques.agregarAtras(bloque)

------------------------------------------------------------
ESTADO FINAL DE ESTRUCTURAS

usuarios[]:
  [null,
   (1, 20),  // +50 -30
   (2, 20),  // -10 +30
   (3, 10)]  // +10

heapHandleLE.heap:
  contiene t0 (50), t1 (10), t2 (30), ordenado por monto desc

heapHandleLE.handle:
  handle[0] = Nodo0 (t0)
  handle[1] = Nodo1 (t1)
  handle[2] = Nodo2 (t2)

bloques:
  [ 0, las 3 transacciones, 40 ]

Complejidad general: O(nb * log P):
OBS: El uso de P entra en la complejidad del heap ya que puede contener
hasta P transacciones activas, el costo de cada insertar(...) es O(log P).

*/

    public Transaccion txMayorValorUltimoBloque(){
        // Buscar la transacción de mayor valor solo en el último bloque
        ListaEnlazada<Bloque>.Handle bloqueHandle = bloques.obtenerUltimo();
        if (bloqueHandle == null) return null;
        ListaEnlazada<Transaccion> txs = bloqueHandle.valor().transacciones();
        ListaEnlazada<Transaccion>.Handle actual = txs.obtenerPrimero();
        Transaccion max = null;
        while (actual != null) {
            if (max == null || actual.valor().compareTo(max) > 0) {
                max = actual.valor();
            }
            actual = actual.siguiente();
        }
        return max;
    }

    public Transaccion[] txUltimoBloque(){
        ListaEnlazada<Bloque>.Handle bloqueHandle = bloques.obtenerUltimo();
        if (bloqueHandle == null) return new Transaccion[0];
        Bloque bloque = bloqueHandle.valor();
        ListaEnlazada<Transaccion> txs = bloque.transacciones();
        int len = txs.longitud();
        if (len <= 0) return new Transaccion[0];
        Transaccion[] lista = new Transaccion[len];
        ListaEnlazada<Transaccion>.Handle actual = txs.obtenerPrimero();
        for(int i = 0; i < lista.length && actual != null; i++){
            lista[i] = actual.valor();
            actual = actual.siguiente();
        }
        return lista;
    }

    public int maximoTenedor(){
        return heapHandleArray.verRaiz().id(); //O(1)
    }

    public int montoMedioUltimoBloque(){
        ListaEnlazada<Bloque>.Handle ultimo = bloques.obtenerUltimo();
        Bloque bloque = ultimo.valor();
        int res =  0;
        if(bloque.transacciones().longitud()!=1){
            res = bloque.sumaMontos()/(bloque.transacciones().longitud()-1);
        }
        return res; 
    }

    public void hackearTx() {
        // Obtener el handle de la transacción de mayor valor global
        ListaEnlazada<Transaccion>.Handle nodoAEliminar = heapHandleLE.getHandleMax();
        if (nodoAEliminar == null) return;
        Transaccion eliminado = nodoAEliminar.valor();
        // Buscar el bloque al que pertenece la transacción
        ListaEnlazada<Bloque>.Handle bloqueHandle = bloques.obtenerPrimero();
        Bloque bloqueAfectado = null;
        ListaEnlazada<Transaccion> txs = null;
        while (bloqueHandle != null && bloqueAfectado == null) {
            Bloque b = bloqueHandle.valor();
            txs = b.transacciones();
            ListaEnlazada<Transaccion>.Handle h = txs.obtenerPrimero();
            while (h != null) {
                if (h == nodoAEliminar) {
                    bloqueAfectado = b;
                    break;
                }
                h = h.siguiente();
            }
            if (bloqueAfectado == null) bloqueHandle = bloqueHandle.siguiente();
        }
        if (bloqueAfectado == null) return;
        // Eliminar del heap y de la lista enlazada del bloque
        heapHandleLE.extraerRaiz(txs);
        // Actualizar suma de montos del bloque
        int sumaMontoActualBloque = bloqueAfectado.sumaMontos();
        int sumaActualizada = sumaMontoActualBloque;
        if (eliminado.id_comprador() != 0) {
            sumaActualizada -= eliminado.monto();
        }
        bloqueAfectado.actualizar(txs, sumaActualizada);
        // Revertir el saldo de los usuarios
        if (eliminado.id_comprador() == 0) {
            if (eliminado.id_vendedor() > 0) {
                heapHandleArray.actualizarPatrimonio(eliminado.id_vendedor() - 1, -eliminado.monto());
            }
        } else {
            if (eliminado.id_comprador() > 0) {
                heapHandleArray.actualizarPatrimonio(eliminado.id_comprador() - 1, eliminado.monto());
            }
            if (eliminado.id_vendedor() > 0) {
                heapHandleArray.actualizarPatrimonio(eliminado.id_vendedor() - 1, -eliminado.monto());
            }
        }
        // Eliminar todos los bloques vacíos de forma segura (reforzado)
        ListaEnlazada<Bloque>.Handle bh = bloques.obtenerPrimero();
        while (bh != null) {
            ListaEnlazada<Bloque>.Handle next = bh.siguiente();
            if (bh.valor().transacciones().longitud() == 0) {
                bloques.eliminarRapido(bh);
            }
            bh = next;
        }
        // Segunda pasada por si quedaron bloques vacíos por encadenamiento
        bh = bloques.obtenerPrimero();
        while (bh != null) {
            ListaEnlazada<Bloque>.Handle next = bh.siguiente();
            if (bh.valor().transacciones().longitud() == 0) {
                bloques.eliminarRapido(bh);
            }
            bh = next;
        }
    }
}
