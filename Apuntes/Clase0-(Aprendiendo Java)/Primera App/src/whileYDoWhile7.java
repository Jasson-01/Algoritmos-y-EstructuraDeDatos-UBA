public class whileYDoWhile7 {

    public static void main(String[] args) throws Exception {
        
        // Do While
        int contador = 1;

        do { 
            
           System.out.println("Esto sucede antes de la condicion"); 
           System.out.println(contador);

           // Actualizacion de la variable
           contador++;

        } while (contador <= 5);

        System.out.println(contador);
       
       /*
       int contador2 = 1;
       while  (contador <= 5){
           System.out.println(contador);  -> para mostrar información estándar

           //Actualizacion de la varible
           contador++;
       }
       System.err.println(contador);   -> notificar errores o situaciones excepcionales. 
       */


     

    }
}
