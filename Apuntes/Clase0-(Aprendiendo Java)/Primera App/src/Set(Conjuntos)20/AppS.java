import java.util.HashSet;
import java.util.Set;

public class AppS {
    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenidos a la fiesta dde los superHeroes!!");
        
        Set<String> superHeroes = new HashSet<>();
        superHeroes.add("Spiderman");
        superHeroes.add("Batman");
        superHeroes.add("CatWoman");
        superHeroes.add("Hulk");
        superHeroes.add("Wonder Woman");
        superHeroes.add("Superman");
 
        // Despues llego tarde nuestro superheroe estrella
        superHeroes.add("Iron Man");

        if (superHeroes.contains("Spiderman")){
            System.out.println("Spiderman esta en la fiesta");
        } 

        superHeroes.remove("Hulk");

        if(!superHeroes.contains("Hulk")){
            System.out.println("Hulk se ha ido de la fiesta");
        }

        // Fue y volvio tan rapido al kiosko (tienda) que no nos dimos cuenta que se fue
        superHeroes.add("Superman");
       // superHeroes.add("Superman");
       // superHeroes.add("Superman"); No importa cuanto agregues repetidos porque en Set no funciona, En Set no hay repetidos!!!.
       
       if(superHeroes.isEmpty()){
        System.out.println("La fiesta ha terminado todos se han ido");
       } else {
        System.out.println("La fiesta continua aun hay " + superHeroes.size() + " superHeroes"); // Hay 6 superHeroes
       } 

       System.out.println("¿Quienes estan aun en la fiesta?");
       for (String superHeroe : superHeroes){
         System.out.println(superHeroe);
       }

       System.out.println(superHeroes); // [Superman, Iron Man, Wonder Woman, CatWoman, Batman, Spiderman]

    }

    /* NOTAS:
       
       import java.util.LinkedHashSet;
    1) Set<String> superHeroes = new TreeSet<>(); -> Ordena alfabeticamente el Set(Conjunto) o podemos pasarle algun tipo de formato de orden
     

       import java.util.TreeSet;
    2) Set<String> superHeroes = LinkedHashSet<>(); -> Hace mantener un doble link para mantener el orden en el cual se agregaron


    */


}
