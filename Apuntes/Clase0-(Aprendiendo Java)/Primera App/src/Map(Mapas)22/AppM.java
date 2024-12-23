import java.util.HashMap;
import java.util.Map;


public class AppM {
    public static void main(String[] args) throws Exception {
        System.out.println("Bienvenido a nuestra verduleria");

        Map<String, Double> inventario = new HashMap<>(); 

        inventario.put("Banana", 0.98); 
        inventario.put("Tomate", 0.75); 
        inventario.put("Palta", 0.95); 
        inventario.put("Frutilla", 0.65); 
        inventario.put("Pimiento", 0.77); 
        
        System.out.println("Este es el inventario de frutas y verduras: ");

        // KeySet me dda un arreglo con las keys
        for (String fruta : inventario.keySet()){
            // con el get pueddo accedder al valor
            System.out.println(fruta + ": $" + inventario.get(fruta));
        }

        String frutaBuscada = "Frutilla";
        System.out.println("Se acerca un cliente y nos pide la siguiente fruta: " + frutaBuscada);
        
        if (inventario.containsKey(frutaBuscada)){
            System.out.println(frutaBuscada + " esta en el inventario, encantados le vendemos");
        } else {
            System.out.println("Lamentablemente nos quedamos sin " + frutaBuscada);
        }

        String sinStock = "Frutilla";
        inventario.remove(sinStock);

        System.out.println("Nos hemos quedado sin " + sinStock);

        System.out.println("Inventario Actualizado: ");
        for (String fruta : inventario.keySet()){
            // Con el get puedd acceder al valor
            System.out.println(fruta + ": $" + inventario.get(fruta));
        }

        System.out.println("La cantidad de mecaderia es: " + inventario.size());
    
    }

    /* NOTAS:
    - HashMap -> No respeta el orden
      
      import java.util.TreeMap;
    - Map<String, Double> inventario = new TreeMap<>();  // Lo ordena por orden alfabetico (Podemos pasar nosotros el criterio de orden)

      import java.util.LinkedHashMap;
    - Map<String, Double> inventario = new LinkedHashMap<>(); // Mantiene el orden con el que se fue agregando los elementos a la lista.

    */
}
