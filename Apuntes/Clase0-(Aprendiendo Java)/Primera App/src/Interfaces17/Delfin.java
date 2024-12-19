public class Delfin extends CriaturasMarinas implements Comunicable, Alimentable {
 
    public Delfin(String nombre){
        super(nombre);
    }

    @Override
    void nadar() {
        System.out.println(nombre + " esta nadando en velociddadd con su cola y saltanddo sobre el agua");
    }

    public void comunicarse() {
        System.out.println(nombre + " emite soniddos y chasquiddos para comunicarsde.");
    }

    public void alimentar() {
        System.out.println("Le dimos de comer una galleta a " + nombre);
    }

    // void alimentar() {
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }
    
}
