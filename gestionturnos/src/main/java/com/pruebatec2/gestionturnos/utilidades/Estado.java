package com.pruebatec2.gestionturnos.utilidades;

/**
 *
 * @author Carlos Jaquez
 */
public enum Estado {
    /**
     * Indica que el proceso no ha iniciado.
     */
    EN_ESPERA("El proceso aún no ha iniciado. Estado: [ %s ]."),
    /**
     * Indica que el proceso inicio pero no ha finalizado.
     */
    EN_PROGRESO("El proceso se encuentra en progreso. Estado: [ %s ]."),
    /**
     * Indica que el proceso finalizo correctamente.
     */
    YA_ATENDIDO("El proceso finalizó correctamente. Estado: [ %s ].");

    /**
     * Mensaje del estado.
     */
    private String mensaje;

    private Estado(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Retorna el mensaje de la enumeración.
     *
     * @return
     */
    public String getMensaje() {
        return this.mensaje;
    }
}
