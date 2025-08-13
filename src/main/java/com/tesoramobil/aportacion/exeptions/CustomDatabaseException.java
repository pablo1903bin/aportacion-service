package com.tesoramobil.aportacion.exeptions;

/**
 * Excepción personalizada para errores relacionados con operaciones de base de datos.
 * 
 * Esta clase extiende {@link RuntimeException}, por lo que no requiere manejo obligatorio (unchecked).
 * Puede utilizarse cuando se desea propagar errores específicos de acceso o manipulación de datos,
 * envolviendo mensajes personalizados o excepciones internas.
 */
public class CustomDatabaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor que recibe solo el mensaje del error.
     * 
     * @param message Descripción del error que se quiere propagar.
     */
    public CustomDatabaseException(String message) {
        super(message);
    }

    /**
     * Constructor que recibe un mensaje y la causa original del error.
     * 
     * @param message Descripción del error.
     * @param cause Excepción original que causó este error.
     */
    public CustomDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
