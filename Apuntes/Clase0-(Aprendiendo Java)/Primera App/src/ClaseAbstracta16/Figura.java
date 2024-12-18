public abstract class Figura {

    // Este comportamiendo sera obligatorio en las clases que herede de figura.
    abstract double calcularArea();

    void imprimirInformacion(){
        System.out.println("Esta informacion viene desde la clase abstracta");
    }
    
    /*
    ¿Qué es una clase abstracta?

Imagina una clase que define una plantilla general para un grupo de objetos, pero que no puede ser instanciada directamente para crear objetos. Esta es una clase abstracta. Sirve como base para otras clases, las cuales heredan sus características y las especializan.

    */
}
