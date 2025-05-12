package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] recordatorios;

    public ArregloRedimensionableDeRecordatorios() {
        // Implementar
        this.recordatorios = new Recordatorio[0];
    }

    public int longitud() {
        // Implementar
        return recordatorios.length;
    }

    public void agregarAtras(Recordatorio i) {
        // Implementar

        Recordatorio[] nuevoRecordatorios = new Recordatorio[this.recordatorios.length + 1];

        for (int j = 0; j < this.recordatorios.length; j++) {
            nuevoRecordatorios[j] = this.recordatorios[j];
        }

        nuevoRecordatorios[nuevoRecordatorios.length - 1] = i;

        this.recordatorios = nuevoRecordatorios;

    }

    public Recordatorio obtener(int i) {
        // Implementar
        return recordatorios[i];
    }

    public void quitarAtras() {
        // Implementar
        Recordatorio[] quitandoRecordatorios = new Recordatorio[recordatorios.length - 1];

        for (int k = 0; k < recordatorios.length - 1; k++) {
            quitandoRecordatorios[k] = recordatorios[k];
        }

        recordatorios = quitandoRecordatorios;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        // Implementar
        recordatorios[indice] = valor;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        // Implementar
        recordatorios = vector.recordatorios.clone();
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        // Implementar
        ArregloRedimensionableDeRecordatorios copiaRecordatorio = new ArregloRedimensionableDeRecordatorios();
        copiaRecordatorio.recordatorios = new Recordatorio[this.recordatorios.length];

        for (int i=0; i < this.recordatorios.length; i++){
            Recordatorio recordatorioOriginal = this.recordatorios[i];
            copiaRecordatorio.recordatorios[i] = new Recordatorio(recordatorioOriginal.mensaje(),recordatorioOriginal.fecha(),recordatorioOriginal.horario());
        }
        return copiaRecordatorio;

    }
}
