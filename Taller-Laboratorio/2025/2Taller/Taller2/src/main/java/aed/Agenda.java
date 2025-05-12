package aed;

public class Agenda {
    private Fecha fechaActual;
    private ArregloRedimensionableDeRecordatorios recordatorios;
  
    public Agenda(Fecha fechaActual) {
        // Implementar
        this.fechaActual = new Fecha(fechaActual.dia(),fechaActual.mes());
        this.recordatorios = new ArregloRedimensionableDeRecordatorios();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        // Implementar
        recordatorios.agregarAtras(recordatorio);

    }

    @Override
    public String toString() {
        // Implementar
       String fechaToDo = fechaActual.dia() + "/" + fechaActual.mes() + "\n" + "=====" + "\n";

       for (int i = 0; i<recordatorios.longitud(); i++){
           Recordatorio toDo = recordatorios.obtener(i);
           if (toDo.fecha().equals(fechaActual)){
               fechaToDo += toDo.toString() + "\n";
           }
       } 
       return fechaToDo;
    }

    public void incrementarDia() {
        // Implementar
        fechaActual.incrementarDia();
    }

    public Fecha fechaActual() {
        // Implementar
        Fecha fechaActual = new Fecha(this.fechaActual.dia(),this.fechaActual.mes());
        return fechaActual;
    }

}
