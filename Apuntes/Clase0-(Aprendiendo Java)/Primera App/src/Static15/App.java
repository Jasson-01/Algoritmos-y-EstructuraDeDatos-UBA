public class App {
      public static void main(String[] args) throws Exception {

        Animal animal = new Animal("bestia",200);
        Gato gato = new Gato("Glottis", 15);
        Perro perro = new Perro("Firulais", 3);
          
        System.out.println("El animal llamdo " + animal.nombre + " hace: ");
        System.out.println(animal.hacerSonido());
        
        System.out.println("El gato llamdo " + gato.nombre + " hace: ");
        System.out.println(gato.hacerSonido());

        System.out.println("El perro llamdo " + perro.nombre + " hace: ");
        System.out.println(perro.hacerSonido());

        System.out.println("El total de animales creados es de : " + Animal.getCantidadAnimales());
        System.out.println("Yo atiendo a mis animalitos en " + Veterinaria.nombre);

      }  
}
