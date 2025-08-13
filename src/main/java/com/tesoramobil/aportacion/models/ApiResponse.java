package com.tesoramobil.aportacion.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Clase genérica para representar una respuesta estándar de la API.
 * 
 * @param <T> El tipo de dato que se devuelve como contenido en la respuesta.
 * 
 * Esta clase encapsula un código de estado, un mensaje y un objeto de datos. 
 * Se utiliza comúnmente para estructurar respuestas uniformes en endpoints REST.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    /**
     * Código de estado de la respuesta.
     * Ejemplos: "OK", "ERROR", "401", etc.
     */
    private String codigo;

    /**
     * Mensaje explicativo asociado al resultado de la operación.
     * Ejemplos: "Operación exitosa", "Token no encontrado", etc.
     */
    private String mensaje;

    /**
     * Contenido devuelto por la operación. Puede ser cualquier objeto o tipo.
     * Por ejemplo: un usuario, una lista, un token, etc.
     */
    private T data;
}
