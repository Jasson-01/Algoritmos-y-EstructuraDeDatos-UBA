public class CalculadoraException extends Exception {
   
    String descripcion; 

    public CalculadoraException(String descripcion) {
        setDescripcion(descripcion);
    }

    @Override
    public String getMessage() {
        return getDescripcion();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
}
