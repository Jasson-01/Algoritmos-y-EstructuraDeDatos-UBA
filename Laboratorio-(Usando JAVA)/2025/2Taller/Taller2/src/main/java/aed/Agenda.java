package aed;

public class Agenda {
    private Fecha fechaActual;
    private ArregloRedimensionableDeRecordatorios recordatorios;
    private Horario horario;

    public Agenda(Fecha fechaActual) {
        // Implementar
        this.fechaActual = new Fecha(fechaActual.dia(),fechaActual.mes());
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        // Implementar
        recordatorios.agregarAtras(recordatorio);

    }

    @Override
    public String toString() {
        // Implementar
        return fechaActual.dia() + "/" + fechaActual.mes() + "\n" + "=====" + "Clase Algo @ " + fechaActual.dia() + "/" + fechaActual.mes() + horario.hora() + ":" + horario.minutos() + "\n" + "Labo Algo @ " + fechaActual.dia() + "/" + fechaActual.mes();
    }

    public void incrementarDia() {
        // Implementar
    }

    public Fecha fechaActual() {
        // Implementar
        Fecha fechaActual = new Fecha(this.fechaActual.dia(),this.fechaActual.mes());
        return fechaActual;
    }

}
