public class App {
    public static void main(String[] args) throws Exception {
        
        // Excepciones: eventos que doubleerrumplen el flujo normal dde la aplicacion.
        double numero1 = 10;
        int numero2 = 5;
        int resultado;
        
        try {

            resultado = (int) numero1 / numero2;
            System.out.println(resultado); 

        } catch (Exception e) {
            // java.lang.ArithmeticException: / by zero
            // at App.main(App.java:11)
            e.printStackTrace();

            // Esto si se imprime
            System.out.println("Esto va despues de la excepcion");

        }

    
    }
}


