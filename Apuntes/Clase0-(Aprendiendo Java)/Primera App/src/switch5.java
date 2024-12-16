import java.util.Scanner;
public class switch5 {

    public static void main(String[] args) throws Exception {

        // Si tenemos varios "else if" podriamos usar "switch" para estar comparando...
        // EJEMPLO:
        // ¿Que tipo de bebida te gusta?
        /*
        String bebida = "cafe";
        switch (bebida) {
            case "cafe":
                System.out.println("Buenisima eleccion, de paso combina con el curso de Java :D");
                break;
            case "Mate":
                System.out.println("Debes se Argentino, Uruguayo o Paraguayo!");
                break;
            case "Gaseosa":
                System.out.println("Tene cuidado con el azucar");
                break;
            case "Vino":
                System.out.println("Si tomaste vino No manejes basura");
                break;
            default:
                System.out.println("DISFRUTA DE TU BEBIDA");
                break;
        }
        */

    // JUEGO DEL SCANNER:   
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido a la maquina expendedora de bebidas");
        System.out.println("Elegi una opcion de las siguientes: ");
        System.out.println("1 - Cafe");
        System.out.println("2 - Mate");
        System.out.println("3 - Gaseosa");
        System.out.println("4 - Vino");
        
        int opcion = scanner.nextInt();
         
        switch (opcion) {
            case 1:
                System.out.println("Cafe! Buenisima eleccion, de paso combina con el curso de Java :D");
                break;
            case 2:
                System.out.println("Mate! Debes se Argentino, Uruguayo o Paraguayo!");
                break;
            case 3:
                System.out.println("Gaseosa! Tene cuidado con el azucar");
                break;
            case 4:
                System.out.println("Vino! Si tomaste vino no manejes, por favor");
                break;
            default:
                System.out.println("Opcion no valida. Fin del programa");
                break;    
        }   

        if (opcion <= 4) {
            System.out.println("Disfrute de su bebida");
        }  
        scanner.close();



          









  





    
     

    }
}
