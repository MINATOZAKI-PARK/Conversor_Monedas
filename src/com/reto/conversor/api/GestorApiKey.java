package com.reto.conversor.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
/**
 * Clase que gestiona la lectura y almacenamiento de una clave API.
 * Permite cargar la clave desde un archivo y proporciona un método para acceder a ella.
 */
public class GestorApiKey {
    /**
     * Almacena la clave API
     */
    private final String apiKey;

    /**
     * Constructor que lee la clave API desde un archivo
     *
     * @param apiKeyFilePath Ruta al archivo que contiene la clave API
     * @throws IllegalStateException si el archivo está vacío o no se puede leer
     */
    public GestorApiKey(String apiKeyFilePath) {
        try {
            // Lee la API key desde el archivo txt
            Path path = Paths.get(apiKeyFilePath); // Ruta al archivo de API key
            apiKey = Objects.requireNonNull(Files.readString(path), "El archivo de API key es nulo").trim(); // trim() para eliminar espacios o saltos de línea

            if (apiKey.isEmpty()) { // Comprueba que la clave API no sea nula ni vacía
                throw new IllegalStateException("El archivo de API key está vacío"); // Lanza una excepción
            }
        } catch (IOException ex) { // Lanza una excepción si no se puede leer el archivo
            throw new IllegalStateException("No se pudo leer el archivo de API key: " + apiKeyFilePath, ex);
        }
    }

    /**
     * Constructor por defecto que establece una clave API predeterminada
     */
    /**
     * Obtiene la clave API
     *
     * @return La clave API almacenada
     */
    public String getAPIKEY() {
        return apiKey; // Retorna la clave API almacenada
    }
}
