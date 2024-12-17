// Con esto especificamos que gato hereda de animal.
public class Gato extends Animal {

   public Gato(String nombre, int edad){
       //Con la palabra "super" estamos asignando la informacion heredada.
       super(nombre,edad);
   }
   
   @Override //Esto es polimorfismo , Esto hace que se sobreescriba el codigo 
   public String hacerSonido() {
        return "Miau";
   }



   // NOTA:
   // Polimorfismo -> El polimorfismo en metodos permite que metodos con el mismo nombre pero en diferentes clases se comporten de manera distinta segun el tipo de objeto al que se llama.
}
