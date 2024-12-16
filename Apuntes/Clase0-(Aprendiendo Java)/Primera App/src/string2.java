public class string2 {
    public static void main(String[] args) throws Exception {

        String texto = "Este es un texto asignado a una variable string";
        System.out.println(texto);

        int longitud = texto.length();
        System.out.println(longitud);

        char caracter = texto.charAt(8);
        System.out.println(caracter); 

        String subString = texto.substring(5,16);
        System.out.println(subString);

        String minuscula = texto.toLowerCase();
        System.out.println(minuscula);

        String mayuscula = texto.toUpperCase();
        System.out.println(mayuscula);

        int indice = texto.indexOf("variable");
        System.out.println(indice);

        String reemplazado = texto.replace("texto", "parrafo");
        System.out.println(reemplazado);

        boolean contiene = texto.contains("asignado");
        System.out.println("¿Tiene la palabra asignado?: "+ contiene);
        
        String sinEspacios = texto.trim();
        System.out.println(sinEspacios); 
         
    }
}
