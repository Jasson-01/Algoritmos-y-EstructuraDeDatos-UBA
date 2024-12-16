public class estructurasDeControl4 {
    public static void main(String[] args) throws Exception {
       
       int edad = 18;

       if (edad > 18 && edad <= 60){
            System.out.println("Puedes entrar a la disco");
       } else if (edad == 18) {
            System.out.println("Tienes la edad justa para ingresar"); 
         // Podemos poner varios "else if" mas    
       }
       else {
        System.out.println("No tienes edad suficiente para entrar a la disco");
       }
         
    }
}


