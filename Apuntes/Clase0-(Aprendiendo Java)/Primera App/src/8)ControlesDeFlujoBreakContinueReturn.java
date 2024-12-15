
public class ControlesDeFlujoBreakContinueReturn {

    public static void main(String[] args) throws Exception {

        /*

    CONTROLES DE FLUJO:
    los controles de flujo que vamos a ver aca son: Break, Continue y Return
    # break -> Corta el bucle
    # continue -> Saltea las lineas que continua del scope
    # return -> Devuelve valores, termina la funcion

         */
        //EJM break
        int i;
        for (i = 1; i <= 10; i++) {
            System.out.println(i);
            if (i == 5) {
                break;
            }
            System.out.println(i);
        }
        /*
        //EJM continue
        int i;
        for (i = 1; i <= 10; i++) {
            System.out.println(i);
            if (i == 5) {
                continue;
            }
            System.out.println(i);
        }


        //EJM return
        int i;
        for (i = 1; i <= 10; i++) {
            System.out.println(i);
            if (i == 5) {
                return;
            }
            System.out.println(i);
        }
         */

    }
}
