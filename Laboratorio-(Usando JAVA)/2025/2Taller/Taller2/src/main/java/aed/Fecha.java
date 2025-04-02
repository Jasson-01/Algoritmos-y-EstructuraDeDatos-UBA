package aed;

public class Fecha {
    private int dia;
    private int mes;

    public Fecha(int dia, int mes) {
        // Implementar
        this.dia = dia;
        this.mes = mes;
    }

    public Fecha(Fecha fecha) {
        // Implementar
        dia = fecha.dia;
        mes = fecha.mes;
    }

    public Integer dia() {
        // Implementar
        return dia;
    }

    public Integer mes() {
        // Implementar
        return mes;
    }
    
    @Override
    public String toString() {
        // Implementar
        return dia + "/" + mes;
    }

    @Override
    public boolean equals(Object otra) {
        // Implementar
        boolean otraEsNull = (otra == null);
        boolean claseDistinta = otra.getClass() != this.getClass();

        if (otraEsNull || claseDistinta) {
            return false;
        }

        Fecha otraFecha = (Fecha) otra;

        return dia == otraFecha.dia && mes == otraFecha.mes;
    }

    public void incrementarDia() {
        // Implementar

        if ( dia < diasEnMes(mes) ){
            dia += 1;
        } else {
            dia = 1;
            if (mes < 12) {
               mes += 1;
            }else{
               mes = 1;
               dia = 1; 
            }
        } 
         
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
