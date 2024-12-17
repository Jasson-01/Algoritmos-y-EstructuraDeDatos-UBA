public class App {
      public static void main(String[] args) throws Exception {


          Persona persona1 = new Persona("Pedro","Pascal",48,"Mandaloriano"); 
          // persona1.nombre = "Patricia";
          // persona1.apellido = "Sosa";
          // persona1.edad = 60;
          // persona1.carrera.nombre = "Musica";

          // Carrera carrera1 = new Carrera();
          // carrera1.nombre = "Cantante";
          // carrera1.duracion = 4;
          // carrera1.estaEnCurso = false;
            
          // persona1.carrera = carrera1;

        //   String saludado = "Jason";

          System.out.println(persona1.darNombreCompleto() + "tiene " + persona1.edad + " años y esta recibido de " + persona1.carrera.nombre);
        



      }  
}
