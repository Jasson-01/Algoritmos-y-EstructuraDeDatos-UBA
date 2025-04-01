package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] recordatorios;

    public ArregloRedimensionableDeRecordatorios() {
        // Implementar
        this.recordatorios = new Recordatorio[0];
    }

    public int longitud() {
        // Implementar
        return this.recordatorios.length;
    }

    public void agregarAtras(Recordatorio i) {
        // Implementar

        Recordatorio[] nuevoRecordatorios = new Recordatorio[this.recordatorios.length + 1];

        for (int j = 0; j < this.recordatorios.length; j++) {
            nuevoRecordatorios[j] = obtener(j);
        }

        nuevoRecordatorios[nuevoRecordatorios.length - 1] = new Recordatorio(i.mensaje(), i.fecha(), i.horario());

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
        this.recordatorios = new Recordatorio[vector.longitud()];

        for (int i = 0; i < vector.longitud(); i++) {
            Recordatorio redimensionable = vector.obtener(i);
            this.recordatorios[i] = new Recordatorio(redimensionable.mensaje(), redimensionable.fecha(),
                    redimensionable.horario());

        }
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        // Implementar
        ArregloRedimensionableDeRecordatorios copiaArreglo = new ArregloRedimensionableDeRecordatorios();
        copiaArreglo.recordatorios = this.recordatorios;
        ArregloRedimensionableDeRecordatorios copiaFinal = new ArregloRedimensionableDeRecordatorios(copiaArreglo);
        return copiaFinal;
    }
}
