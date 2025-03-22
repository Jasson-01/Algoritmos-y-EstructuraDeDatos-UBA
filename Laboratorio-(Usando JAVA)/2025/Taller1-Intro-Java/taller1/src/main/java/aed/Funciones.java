package aed;

class Funciones {
    int cuadrado(int x) {
        int res = x*x;
        return res;
    }

    double distancia(double x, double y) {
        double dist = Math.sqrt(x*x + y*y);
        return dist;
    }

    boolean esPar(int n) {       
       if ( n % 2 == 0) {
           return true;
       } 
       return false; 
    }

    boolean esBisiesto(int n) {
        if (n % 400 == 0) {
            return true;
        }
        if ( (n % 4 == 0) && (n % 100 != 0)){
            return true;   
        }
        return false;
    }

    int factorialIterativo(int n) {
        int res = 1;
        while (n > 0) {
           res *= n;
           n = n-1;
        }
        return res;
        
    }

    int factorialRecursivo(int n) {
        if (n == 0){
            return 1;
        } else {
            return n*(factorialRecursivo(n-1));
        } 
    }

    boolean esPrimo(int n) {
        if ( n < 2){
            return false;
        }
        for ( int a = 2; a < n; a++ ){
             if ( n % a == 0) {
                return false;
             }
        }
        return true;
    }

    int sumatoria(int[] numeros) {
        int res = 0;
        for ( int a:numeros){
            res += a;
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        for ( int i = 0; i < numeros.length; i++){
            if (numeros[i] == buscado) {
                return i;
            }
        }
        return -1;
    }

    boolean tienePrimo(int[] numeros) {
        for (int x:numeros){
            if (esPrimo(x)){
                return true;
            }
        }
        return false;
    }

    boolean todosPares(int[] numeros) {
        for (int x=0; x < numeros.length; x++){
            if ( esPar(numeros[x]) == false ) {
                 return false; 
            }
        }
        return true;
    }

    boolean esPrefijo(String s1, String s2) {
        if ( s1.length() > s2.length()){
            return false;
        }

        for ( int i=0; i< s1.length(); i++){
            if ( s1.charAt(i) != s2.charAt(i) ) {
                 return false;
            }
        }
        return true;
    }

    boolean esSufijo(String s1, String s2) {
     
        if ( s1.length() > s2.length()){
            return false;
        }

        for ( int i=0 ; i<s1.length(); i++){
            if ( s1.charAt(s1.length()-i-1) != s2.charAt(s2.length()-i-1) ) {
                 return false;
            }
        }
        return true;
    }
}
