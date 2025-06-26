package aed;

public class Berretacoin {
    private ListaEnlazada<Bloque> bloques;
    private HeapHandleLE heapHandleLE; 
    private HeapHandleArray heapHandleArray; 


    public Berretacoin(int n_usuarios){
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
                nuevoBloqueTxs.agregarAtras(t); // Agregamos la transaccion al final del Bloque.
                ListaEnlazada<Transaccion>.Handle nodo = nuevoBloqueTxs.obtenerUltimo(); // Obtenemos el puntero al nodo de la transaccion agregada.
    
                // Insteramos la transaccion en el heap y en el handle
                heapHandleLE.insertar(nodo); //O(log nb)
    
                // Actualizamos el patrimonio de los usuarios
                // O(log p)
                if (t.id_comprador() == 0) { // Transaccion de creacion
                    heapHandleArray.actualizarPatrimonio(t.id_vendedor(),t.monto());   
                } else {
                    heapHandleArray.actualizarPatrimonio(t.id_comprador(), -t.monto()); 
                    heapHandleArray.actualizarPatrimonio(t.id_vendedor(),t.monto()); 
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
    DUDA: Creemos que esto no respeta la complejidad 0(log P*nb) pedida ya que en el 'heapHandleLE.insertar(nodo)'
    estamos reordenando el heap por montos cada vez que agregamos una transaccion, y esto seria 0(log nb), y lo estamos
    haciendo nb veces. OBS: Estamos pensando que siempre nb > P... (puede tener cientos de transacciones entre algunos usuarios...)
     */


    /*
    EJEMPLO DE COMO LO ESTAMOS PENSADO QUE FUNCIONA...
    ---------------------------------------------------
    Supongamos que hacemos:
    Transaccion[] transacciones = {
        new Transaccion(0, 0, 1, 100),  // id=0, monto=100
        new Transaccion(1, 2, 3,  50),  // id=1, monto=50
        new Transaccion(2, 1, 2,  75)   // id=2, monto=75
    };

    berretacoin.agregarBloque(transacciones);
    Al comenzar, heapHandleLE está vacío:

    heap:             handleList:
    [  ]              [  ]

    ——— Iteración 1: t = transacciones[0] (id=0, monto=100) --
    1) nuevoBloqueTxs.agregarAtras(t0)  
        • Crea Nodo(0) en la lista.
    2) Handle h0 = nuevoBloqueTxs.obtenerUltimo()  
        • Apunta a Nodo(0).
    3) heapHandleLE.insertar(h0):  
        a) heap.insertar(t0) → heap = [ (0,100) ]  
        b) handleList.add(0, h0) → handleList = [ h0 ]

    heap:             handleList:
    [(0,100)]         [0 → h0 → Nodo(0)]

    ——— Iteración 2: t = transacciones[1] (id=1, monto=50) ———
    1) nuevoBloqueTxs.agregarAtras(t1) → lista: Nodo(0)→Nodo(1)  
    2) Handle h1 = obtUltimo() → apunta a Nodo(1).  
    3) heapHandleLE.insertar(h1):  
        a) heap.insertar(t1) → heap = [ (0,100) root, (1,50) child ]  
        b) handleList.add(1, h1) → handleList = [ h0, h1 ]

    heap:                 handleList:
            (0,100)           0 → h0
            /     \           1 → h1
        (1,50)

    ——— Iteración 3: t = transacciones[2] (id=2, monto=75) ———
    1) nuevoBloqueTxs.agregarAtras(t2) → lista: Nodo(0)→Nodo(1)→Nodo(2)  
    2) Handle h2 = obtUltimo() → apunta a Nodo(2).  
    3) heapHandleLE.insertar(h2):  
        a) heap.insertar(t2) → rebalancea heap:
                (0,100)
            /      \
            (1,50)   (2,75)
        b) handleList.add(2, h2) → handleList = [ h0, h1, h2 ]

    heap:                    handleList:
            (0,100)               0 → h0
            /      \               1 → h1
        (1,50)    (2,75)           2 → h2

    ——— Fin de agregarBloque ———
    • El heap contiene todas las tx del bloque, listo para:
        - txMayorValorUltimoBloque(): heap.verRaiz() → O(1)
        - hackearTx():
            1) heap.extraerRaiz() → O(log nb)
            2) h = handleList.get(id) → O(1)
            3) lista.eliminarRapido(h) → O(1)
    • El handleList mapea id→Handle en O(1), permitiendo eliminar el nodo exacto sin búsquedas.


    (SEGUIR EL EJEMPLO CON el HandleArrayList para la actualizacion de patrimonios de los usuarios y demas)

    */

    

    public Transaccion txMayorValorUltimoBloque(){
        return heapHandleLE.verRaiz(); //O(1)
    }

    /*
    Aca claramente me interesa buscar la informacion con el Heap para que sea O(1) (Por eso en el handle 
    verRaiz() queda igual!)
     */

    public Transaccion[] txUltimoBloque(){
        Transaccion[] lista = new Transaccion[bloques.obtenerUltimo().valor().transacciones().longitud()];
        ListaEnlazada<Transaccion>.Handle actual = bloques.obtenerUltimo().valor().transacciones().obtenerPrimero(); // Tiene Nodo 
        for(int i = 0; i < lista.length; i++){ //O(nb)
            lista[i] = actual.valor();
            actual = actual.siguiente();
        }
        return lista;
    }

    /*
    Aca me interesa usar el handle del ArrayList de LE que justamente me guarda la referencia a cada nodo ordenado por id de transaccion.
    Por eso puedo simplemente iterar y copiar elemento a elemento de la ArrayList de LE de transacciones.
    */

    public int maximoTenedor(){
        return heapHandleArray.verRaiz().id(); //O(1)
    }

    /*
    HeapHandleArray tambien en verRaiz queda igual que el heap 'generico' ya que es suficiente.
    */

    public int montoMedioUltimoBloque(){
        Bloque ultimo = bloques.obtenerUltimo().valor(); //O(1) 
        int res =  0;
        if(ultimo.transacciones().longitud()>1){
            res = ultimo.montoAcumulado()/(ultimo.transacciones().longitud()-1);
        }
        else{
            res = ultimo.montoAcumulado(); //Si no hay transacciones, el monto medio es 0.
        }
        return res; 
    }

    /*
    Es 0(1) porque aprovechamos que el bloque ya tiene el monto acumulado y la cantidad de transacciones.
    (Fuimos calculando el monto acumulado al agregar las transacciones al bloque).
    */

    public void hackearTx(){ 

        /* Buscamos el último bloque de la cadena */
        Bloque ultimoBloque = bloques.obtenerUltimo().valor(); //O(1)
        ListaEnlazada<Transaccion> trans = ultimoBloque.transacciones(); //O(1) - Tomamos su lista de transacciones

        /* Guardamos el valor de la raíz del handleLE en una variable */
        Transaccion transRaiz = heapHandleLE.verRaiz();//O(1)
        
        /* Eliminamos la transacción de mayor valor */
        heapHandleLE.extraerRaiz(trans);/* O(log nb) - Esto elimina del heap y de la lista */

        /* Actualizamos el patrimonio de los usuarios involucrados en la transacción*/
        if (transRaiz.id_comprador() == 0) {
            heapHandleArray.actualizarPatrimonio(transRaiz.id_vendedor(), -transRaiz.monto()); // O(log P)
        } else {
            heapHandleArray.actualizarPatrimonio(transRaiz.id_comprador(), transRaiz.monto()); // O(log P)
            heapHandleArray.actualizarPatrimonio(transRaiz.id_vendedor(), -transRaiz.monto()); // O(log P)
        }

        /* Actualizamos el monto total de la lista de transacciones del último bloque*/
        int nuevoMontoAcumulado = ultimoBloque.montoAcumulado(); // O(1)
        if (transRaiz.id_comprador() != 0) {
            nuevoMontoAcumulado -= transRaiz.monto();// O(1)
        }
        ultimoBloque.actualizar(trans, nuevoMontoAcumulado);// O(1)

    }
}
