public class Persona {
    // Atributos (estado/caracteristicas de un objeto)
    String nombre;
    String apellido;
    int edad;
    Carrera carrera;

    //               CONSTRUCTOR
    // -> "this" : Se utiliza para acceder a variables de instancia o metodos de la clase actual y puede ser util para evitar ambiguedades en caso donde hay nombres de variables locales que coinciden con nombre de variables de instacia.


    public Persona(String nombre, String apellido, int edad, String nombreCarrera, int duracionCarrera, boolean estaCursando){
        // carrera = new Carrera();
        carrera = new Carrera(nombreCarrera, duracionCarrera, estaCursando);
        this.nombre = nombre; // Este this se refiere a los atributos no al de los parametros!.
        this.apellido = apellido;
        this.edad = edad;
        // carrera.nombre = nombreCarrera;
    }

    //          SOBRECARGA DE CONSTRUCTOR:
    // Una sobrecarga del constructor en Java es cuando una clase tiene multiples constructores con el mismo nombre pero con diferentes listas de parametros.Sirve para permitir la creacion de objetos de esa clase con diferentes configuraciones iniciales segun los argumentos proporcionados al constructor.

    public Persona(String nombre, String apellido, int edad, String nombreCarrera){
  
        carrera = new Carrera(nombreCarrera);
        this.nombre = nombre; // Este this se refiere a los atributos no al de los parametros!.
        this.apellido = apellido;
        this.edad = edad;
    }



    // Metodos (comportamiento de un objeto)
    
    public String darNombreCompleto(){
         return apellido + ", " + nombre;
    }

    public String enviarSaludo(String saludado){
         if(edad > 40) return "Buenos dias, querido " + saludado;
         else return "Hola, ¿Como estas" + saludado + "?";
    }

}
