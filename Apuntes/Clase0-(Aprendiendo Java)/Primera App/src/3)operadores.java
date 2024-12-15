public class operadores {
    public static void main(String[] args) throws Exception {
       int a = 5;
       int b = 4;
       int c = b - a;
       double d = a / 5;

       System.out.println(c);
       System.out.println(d);

        /*
           % -> resto
           / -> division normal
           c++ -> suma 1 
           c-- -> resta 1
           OPERADORES DE COMPARACION:
           >=, <=, <, >
           == -> COMPARA
           OPERADORES LOGICOS:
           && -> and 
           || -> or
           !  -> negacion (not)
        */ 
       boolean esMayor = a > b;
       boolean esMenor = b > a;

       System.out.println(esMayor); // true
       System.out.println(esMenor); // false

         
    }
}


/*
1) false
2) true
3) false


*/