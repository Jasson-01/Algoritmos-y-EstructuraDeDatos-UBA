package aed;

public class Recordatorio {
    private String mensaje;
    private Fecha fecha;
    private Horario horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        // Implementar
        this.mensaje = mensaje;
        this.fecha = new Fecha(fecha.dia(), fecha.mes());
        this.horario = horario;
    }

    public Horario horario() {
        // Implementar
        return this.horario;
    }

    public Fecha fecha() {
        // Implementar
        Fecha fecha = new Fecha(this.fecha.dia(),this.fecha.mes());
        return fecha;
    }

    public String mensaje() {
        // Implementar
        return this.mensaje;
    }

    @Override
    public String toString() {
        // Implementar
        return mensaje + " " + "@" + " " + fecha + " " + horario;
    }

    @Override
    public boolean equals(Object otro) {
        // Implementar
        boolean otraEsNull = (otro == null);
        boolean noEsRecordatorio = otro.getClass() != this.getClass();

        if (otraEsNull || noEsRecordatorio) {
            return false;
        }

        Recordatorio otroRecordatorio = (Recordatorio) otro;

        return (this.mensaje == otroRecordatorio.mensaje) && (this.fecha.equals(otroRecordatorio.fecha)) && (this.horario.equals(otroRecordatorio.horario)); 
    }

}
