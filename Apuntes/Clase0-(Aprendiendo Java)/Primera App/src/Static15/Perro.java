public class Perro extends Animal{
   
   public Perro(String nombre, int edad){
       //Con la palabra "super" estamos asignando la informacion heredada.
       super(nombre,edad);
   }
   
   @Override//Esto es polimorfismo!! ,Con el decorador override podemos sobreescribir un metodo. 
   public String hacerSonido() {
        return "Guau";
   }


}
