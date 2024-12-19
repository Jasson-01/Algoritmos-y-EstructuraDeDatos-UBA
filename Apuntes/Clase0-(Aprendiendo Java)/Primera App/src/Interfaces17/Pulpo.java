public class Pulpo extends CriaturasMarinas implements Comunicable, Alimentable{
    
    // Atributo
    int cantTentaculos;

    public Pulpo(String nombre, int par){
        super(nombre);
        this.cantTentaculos = cantTentaculos;
    }

    @Override
    void nadar() {
        System.out.println(nombre + " esta nadando con sus ocho tentaculos.");
    }
    
    public void comunicarse() {
        System.out.println(nombre + " saludda con sus " + cantTentaculos + " tentaculos");
    }
    
    public void alimentar() {
        System.out.println("Estamos alimentanddo con peces a " + nombre);
    }

}
