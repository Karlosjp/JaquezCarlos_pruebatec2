package com.pruebatec2.gestionturnos.utilidades;

/**
 *
 * @author Carlos Jaquez
 */
public enum Estado {
    /**
     * Indica que el proceso no ha iniciado.
     */
    EN_ESPERA("Turno no iniciado."),
    /**
     * Indica que el proceso inicio pero no ha finalizado.
     */
    EN_PROGRESO("Turno en proceso"),
    /**
     * Indica que el proceso finalizo correctamente.
     */
    YA_ATENDIDO("Turno ya terminado");

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
