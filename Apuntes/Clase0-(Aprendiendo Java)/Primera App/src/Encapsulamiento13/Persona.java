
public class Persona {

    //        Atributos (estado/caracteristicas de un objeto)
    private String nombre;
    private String apellido;
    private int edad;

    //       CONSTRUCTOR:
    public Persona(String nombre, String apellido, int edad) {

        this.nombre = nombre; // Este this se refiere a los atributos no al de los parametros!.
        this.apellido = apellido;
        this.edad = edad;
    }

    //         Metodos (comportamiento de un objeto)
    public String darNombreCompleto() {
        return getApellido() + ", " + getNombre();
    }

    public String enviarSaludo(String saludado) {
        if (edad > 40) {
            return "Buenos dias, querido " + saludado; 
        }else {
            return "Hola, ¿Como estas" + saludado + "?";
        }
    }
    
    //        Getters and Setters
    public int getEdad(){
        return edad;
    }

    public String getNombre(){
        return this.nombre;
    }
    
    public String getApellido(){
        return this.apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void  setApellido(String apellido){
        this.apellido = apellido;
    }

}
