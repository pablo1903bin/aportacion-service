package com.tesoramobil.aportacion.exeptions;

/**
 * Excepción personalizada lanzada cuando no se encuentra un token solicitado.
 * 
 * Esta excepción se utiliza comúnmente cuando el sistema espera encontrar un token 
 * (por ejemplo, de verificación, sesión o autenticación) y no está presente en la base de datos 
 * o almacenamiento correspondiente.
 */
public class TokenNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor que recibe un mensaje descriptivo del error.
     * 
     * @param message Mensaje que describe el motivo del error.
     */
    public TokenNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor que recibe un mensaje y la excepción original que causó el error.
     * 
     * @param message Mensaje que describe el error.
     * @param cause Causa original del error.
     */
    public TokenNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
