package aed;

public class Usuario implements Comparable<Usuario> {
    private int id;
    private int patrimonio;

    public Usuario(int id) {
        this.id = id;
        this.patrimonio = 0;
    }

    @Override
    public int compareTo(Usuario otro){
        if (this.patrimonio != otro.patrimonio) {
            return Integer.compare(otro.patrimonio, this.patrimonio); // heap de máximo
        } else {
            return Integer.compare(otro.id, this.id); // menor id primero en empate (heap de máximo)
        }
    }
    
    @Override
    public boolean equals(Object otro){
        boolean otraEsNull = (otro == null);
        boolean claseDistinta = otro.getClass() != this.getClass();

        if (otraEsNull || claseDistinta){
            return false;
        }

        Usuario otraUsuario = (Usuario) otro;

	    return this.patrimonio == otraUsuario.patrimonio && this.id == otraUsuario.id;    
    }

    public int patrimonio() {
        return patrimonio;
    }

    public int id() {
        return id;
    }

    public void sumar(int monto) {
        patrimonio += monto;
    }
    
    public void restar(int monto) {
        patrimonio -= monto;
    }
}
