
public class Animal {

    // Atributos: 
    String nombre;
    int edad;
    static int contadorAnimales = 0;
    // STATIC : 
    // static en Java es una palabra clave que se utiliza para indicar que un miembro de una clase (ya sea una variable, un método o un bloque) pertenece a la clase en sí, en lugar de a una instancia específica de esa clase. Esto significa que solo existirá una copia de ese miembro, compartida por todas las instancias de la clase.
    // Cualquier cambio realizado a un miembro estático se refleja en todas las instancias.
    

    // Constructor
    public Animal(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        contadorAnimales++;
    }
       
    // Metodos:
    public String hacerSonido() {
        return "Grrrr";
    }

    public static int getCantidadAnimales(){
        return contadorAnimales;
    }


//Nota:
//                        static:

// Constantes que no cambian.
// Variables de conteo.
// Métodos utilitarios que no dependen del estado de un objeto.


//                       Getters:

// Para acceder a las propiedades de un objeto individual.
// Para encapsular los datos y controlar el acceso a ellos.
// Para cambiar el valor de una propiedad, se suelen usar los setters.

}
