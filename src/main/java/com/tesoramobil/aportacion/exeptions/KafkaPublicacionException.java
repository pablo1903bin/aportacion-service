package com.tesoramobil.aportacion.exeptions;

/**
 * Excepción personalizada para errores ocurridos al intentar publicar mensajes en Kafka.
 * 
 * Esta excepción se utiliza cuando ocurre un fallo en la lógica de publicación a un tópico Kafka,
 * ya sea por problemas de conexión, serialización o cualquier error inesperado durante la operación.
 */
public class KafkaPublicacionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Detalle técnico del error, útil para diagnóstico o logging.
     */
    private final String detalle;

    /**
     * Constructor que recibe un mensaje personalizado y un detalle técnico adicional.
     * 
     * @param mensaje Mensaje de error descriptivo para mostrar o registrar.
     * @param detalleTecnico Información técnica adicional útil para diagnóstico (por ejemplo, detalles del broker, tópico, etc).
     */
    public KafkaPublicacionException(String mensaje, String detalleTecnico) {
        super(mensaje);
        this.detalle = detalleTecnico;
    }

    /**
     * Constructor que recibe un mensaje y la excepción original que causó el error.
     * 
     * @param mensaje Mensaje de error principal.
     * @param causa La excepción original lanzada (por ejemplo, una excepción de red, serialización, etc).
     */
    public KafkaPublicacionException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.detalle = causa.getMessage(); // También podrías usar: ExceptionUtils.getStackTrace(causa)
    }

    /**
     * Obtiene el detalle técnico adicional del error.
     * 
     * @return El mensaje técnico o detalle original del error.
     */
    public String getDetalle() {
        return detalle;
    }
}
