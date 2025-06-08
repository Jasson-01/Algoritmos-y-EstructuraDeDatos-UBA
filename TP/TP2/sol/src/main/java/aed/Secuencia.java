package aed;

interface Secuencia<T> {

    /**
     * Devuelve el largo de la secuencia.
     * 
     */
    public int longitud();

    /**
     * Agrega un elemento al principio de la secuencia.
     * 
     */
    public void agregarAdelante(T elem);

    /**
     * Agrega un elemento al final de la secuencia.
     * 
     */
    public void agregarAtras(T elem);

    /**
     * Retorna el elemento en la i-esima posicion.
     * 
     */
    public T obtener(int indice);


}