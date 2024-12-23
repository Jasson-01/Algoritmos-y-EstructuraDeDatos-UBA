import java.util.ArrayList;
import java.util.List;


public class AppL {
    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenidos a la fiesta dde los superHeroes!!");
        
        List<String> superHeroes = new ArrayList<>();
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
        
        System.out.println("Hay un superHeroe que esta ebrio, el superheroe ebrio es: " + superHeroes.get(0));
 
        superHeroes.set(6, "Toni Stark");
        System.out.println("IronMan se saca el traje mostrando su verdadera identidad que es: " + superHeroes.get(6));


        superHeroes.remove(3);

        if(!superHeroes.contains("Hulk")){
            System.out.println("Hulk se ha ido de la fiesta");
        }

        // Fue y volvio tan rapido al kiosko (tienda) que no nos dimos cuenta que se fue
        superHeroes.add("Superman");
       // superHeroes.add("Superman");
       // superHeroes.add("Superman"); Aca si hay repetidos!!!
       
       if(superHeroes.isEmpty()){
        System.out.println("La fiesta ha terminado todos se han ido");
       } else {
        System.out.println("La fiesta continua aun hay " + superHeroes.size() + " superHeroes"); 
       } 

       System.out.println("¿Quienes estan aun en la fiesta?");
       for (String superHeroe : superHeroes){
         System.out.println(superHeroe);
       }

       System.out.println(superHeroes); 

    }

    /* NOTAS:
       - Por defecto viene ordenada
       - hay repetidos
       - Se puede ingresar por indice 
   
       NOTAS-2.0
         import java.util.linkedList; 
       - List<String> superHeroes = new LinkedList<>(); // Como esta doblemente enlazado la modificacion es mucha mas rapida
         
         import java.util.Vector;
       - List<String> superHeroes = new Vector<>();  // Tiene multiHilo se pueden hacer varias tareas(actividades) al mismo tiempo (sincronizado)

    */


}
