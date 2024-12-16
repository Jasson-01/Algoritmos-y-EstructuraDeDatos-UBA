public class arreglos9 {

    public static void main(String[] args) throws Exception {
        
        // ARREGLOS: Estructuras de datos que tienen valores del mismo tipo bajo un mismo nombre
        // Estos valores se distribuyen secuencialmente en la memoria y se puede acceder a ellos mediante un indice
        // LOS ARREGLOS SON TAMBIEN LLAMADOS VECTORES O MATRICES
        
        // NOTA: el indice se comienza a contar por 0 y no por 1

        /*
        -------- 1era FORMA:

        int[] numeros = new int[5]; // Se le asigna a priori la longitud del arreglo
        
        numeros[0] = 1;
        numeros[1] = 2;
    
        numeros[3] = 4;
        numeros[4] = 5;
        */
        
      //  System.err.println(numeros[0]); // muestra 1
      //  System.err.println(numeros[2]); // Como no tiene asignado un numero se asigna por default 0
        
        
        //-------- 2da FORMA:
        //  
        int[] numeros = {10,20,30,40,50};

        numeros[2] = 70; // Esto asigna a la posicion 2 el numero 70


        for (int index = 0; index < numeros.length; index++){ // Al usar length en un array no dice el tamaño de la matriz.
            System.out.println(numeros[index]); // Esto imprime -> 10,20,70,40,50
        } 

       //-------- 3era FORMA: 
       int indice = 0;
       // forEach
       // Un elemento de arreglo
       for (int numero: numeros) {
           System.out.println(numero);
           System.out.println(indice);
           indice++;
       }
     
       /*
       Nota: length de un arreglo y un string son distintos, pero ambos nos dicen la cantidad de elementos que tienen 
       arreglo.length
       string.length()
       */
       String palabra = "Abecedario";
       System.out.println(palabra.length());


    }
}
