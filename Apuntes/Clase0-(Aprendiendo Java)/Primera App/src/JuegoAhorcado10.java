
import java.util.Scanner;

public class JuegoAhorcado10 {

    public static void main(String[] args) throws Exception {

        // Clase scanner que nos permite que el usuario escriba
        Scanner scanner = new Scanner(System.in);

        // Declaraciones y asignaciones
        String palabraSecreta = "inteligencia";
        int intentosMaximos = 10;
        int intentos = 0;
        boolean palabraAdivinada = false;

        // Arreglos:
        char[] letrasAdivinadas = new char[palabraSecreta.length()];

        // Estructura de control: Iterativa (Bucle)
        for (int i = 0; i < letrasAdivinadas.length; i++) {
            letrasAdivinadas[i] = '_';
        }

        // Estructura de control: Iterativa (Bucle)
        while (!palabraAdivinada && intentos < intentosMaximos) {
            // valueOf -> convierte valores de diferentes tipos en cadenas (String) o en objetos envolventes (Integer, Double, etc.)
            System.out.println("Palabra a adivinar: " + String.valueOf(letrasAdivinadas));

            System.out.println("Introduce una letra, por favor");

            /*
            Usamos la clase scanner para pedir una letra

            Character -> La clase Character amplía la funcionalidad del tipo primitivo char al proporcionar métodos útiles y permitir que los caracteres se manejen como objetos en Java. Es especialmente útil cuando se trabaja con colecciones o se necesita funcionalidad adicional para analizar y procesar caracteres.

            next() -> Si necesitas leer palabras individualmente (Es un metodo de la clase scanner)

            charAt(i) -> Se utiliza para obtener un carácter específico de una cadena basado en su índice.
             */
            char letra = Character.toLowerCase(scanner.next().charAt(0));

            boolean letraCorrecta = false;

            //Estructura de control: Iterativa (bucle)
            for (int i = 0; i < palabraSecreta.length(); i++) {
                //Estructura de control: condicional
                // charAt -> recorre letra por letras
                if (palabraSecreta.charAt(i) == letra) {
                    letrasAdivinadas[i] = letra;
                    letraCorrecta = true;
                }

            }

            if (!letraCorrecta) {
                intentos++;
                System.out.println("¡Incorrecto! Te quedan " + (intentosMaximos - intentos) + " intentos.");
            }
            
            // equals -> el método equals se utiliza para comparar si dos objetos son lógicamente iguales
            if (String.valueOf(letrasAdivinadas).equals(palabraSecreta)) {
                palabraAdivinada = true;
                System.out.println("¡Felicidades, has adivinado la palabra secreta: " + palabraSecreta);
            }

        }

        if (!palabraAdivinada) {
            System.out.println("¡Que pena te has quedado sin intentos! GAME OVER");
        }
        scanner.close();
    }
}
