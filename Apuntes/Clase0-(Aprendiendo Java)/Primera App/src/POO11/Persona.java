public class Persona {
    // Atributos (estado/caracteristicas de un objeto)
    String nombre;
    String apellido;
    int edad;
    Carrera carrera;



    // Metodos (comportamiento de un objeto)
    
    public String darNombreCompleto(){
        return apellido + ", " + nombre;
    }

    public String enviarSaludo(String saludado){
        if(edad > 40) return "Buenos dias, querido " + saludado;
        else return "Hola, ¿Como estas" + saludado + "?";
    }

}
