package aed;

import java.util.ArrayList;

public class Berretacoin {
    private ListaEnlazada<Bloque> bloques;
    private ListaEnlazada<Transaccion> transacciones; 
    private HeapHandleLE heapHandleLE; 
    private HeapHandleArray heapHandleArray; 


    public Berretacoin(int n_usuarios){
        this.transacciones = new ListaEnlazada<>();
        this.bloques = new ListaEnlazada<>();
        this.heapHandleLE = new HeapHandleLE();
        this.heapHandleArray = new HeapHandleArray(n_usuarios); //O(P)-Aclaracion en el constructor.+

    }

    public void agregarBloque(Transaccion[] transacciones){
        {   
            // Transacciones del bloque
            // 0(nb*logP)
            ListaEnlazada<Transaccion> nuevoBloqueTxs = new ListaEnlazada<>(); //O(1)
            for (Transaccion t : transacciones) { // O(nb)
                // Agregamos cada transacción al bloque
                nuevoBloqueTxs.agregarAtras(t); //O(1)
                // Declaro a la transferencia agregada como un nodo de LE
                ListaEnlazada<Transaccion>.Nodo nodo = nuevoBloqueTxs.obtenerUltimo();
    
                // Registramos el nodo en el heap y el handle
                heapHandleLE.insertar(nodo); //O(log P)
    
                // Actualizamos el patrimonio de los usuarios
                if (t.id_comprador() == 0) { // Transaccion de creacion
                    heapHandleArray.actualizarPatrimonio(t.id_vendedor()-1,t.monto()); 
                } else {
                    heapHandleArray.actualizarPatrimonio(t.id_comprador()-1, -t.monto());
                    heapHandleArray.actualizarPatrimonio(t.id_vendedor()-1,t.monto());
                }
            }
    
            int id_bloque = bloques.longitud(); //0(1)
            int montoAcumulado = 0; //0(1)
            for (Transaccion t : transacciones) { //0(nb)
                if (t.id_comprador() != 0) { 
                    montoAcumulado += t.monto();
                }
            }
            // Agregamos el bloque a la lista de bloques
            Bloque bloque = new Bloque(id_bloque, nuevoBloqueTxs, montoAcumulado); //O(1)
            bloques.agregarAtras(bloque); //O(1)
        }
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
        return heapHandleLE.verRaiz(); //O(1)
    }

    public Transaccion[] txUltimoBloque(){
        Transaccion[] lista = new Transaccion[bloques.obtenerUltimo().valor.transacciones().longitud()];
        ListaEnlazada<Transaccion>.Nodo actual = bloques.obtenerUltimo().valor.transacciones().obtenerPrimero();
        for(int i = 0; i < lista.length; i++){ //O(nb)
            lista[i] = actual.valor;
            actual = actual.next;
        }
        return lista;
    }

    public int maximoTenedor(){
        return heapHandleArray.verRaiz().id(); //O(1)
    }

    public int montoMedioUltimoBloque(){
        ListaEnlazada<Bloque>.Nodo ultimo = bloques.obtenerUltimo(); //O(1)
        int res =  0;
        if(ultimo.valor.transacciones().longitud()!=1){
            res = ultimo.valor.sumaMontos()/(ultimo.valor.transacciones().longitud()-1);
        }
        return res; 
    }

    public void hackearTx(){

        /*Este modifica:
         * Heap de transacciones (elimina el maximo)
         * Heap de usuarios (usamos el método hackeo para actualizar los patrimonios)
         * sumaMontos del Bloque
         * longitud del la LE de transacciones
        */
        Transaccion eliminado = heapHandleLE.verRaiz(); //Guardo el elemento a eliminar - O(1)
        heapHandleLE.extraerRaiz(transacciones);//Borramos la raiz del heap de trans - O(log nb)
        //Intento actualizar  la lista de trans y el bloque
        ListaEnlazada<Transaccion>.Nodo nodoAEliminar = heapHandleLE.getNodoMax();  //Obtengo el nodo a eliminar - O(1)
        transacciones.eliminarRapido(nodoAEliminar); // Elimino el nodo - O(1)

        //Guardo monto actual del bloque
        int sumaMontoActualBloque = bloques.obtenerUltimo().valor.sumaMontos(); //O(1)
        int sumaActualizada = sumaMontoActualBloque - eliminado.monto(); //O(1)
        /*Actualizo el bloque*/
        bloques.obtenerUltimo().valor.actualizar(transacciones, sumaActualizada); //O(1)
        //heapHandleArray.hackeo(eliminado.id_comprador(),eliminado.id_vendedor(),eliminado.monto()); 
        //hackeamos a los usuarios - O(log P) - creo
    }
}
