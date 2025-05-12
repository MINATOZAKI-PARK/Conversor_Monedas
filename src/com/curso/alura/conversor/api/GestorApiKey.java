import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Clase que gestiona la clave API para el servicio de tasas de cambio
 */
public class GestorApiKey {
    /**
     * Almacena la clave API
     */
    private final String APIKEY;

    /**
     * Constructor que lee la clave API desde un archivo
     *
     * @param apiKeyFilePath Ruta al archivo que contiene la clave API
     * @throws IllegalStateException si el archivo está vacío o no se puede leer
     */
    public GestorApiKey(String apiKeyFilePath) {
        try {
            // Lee la API key desde el archivo txt
            Path path = Paths.get(apiKeyFilePath);
            APIKEY = Files.readString(path).trim(); // trim() para eliminar espacios o saltos de línea

            if (APIKEY == null || APIKEY.isEmpty()) {
                throw new IllegalStateException("El archivo de API key está vacío");
            }
        } catch (IOException ex) {
            throw new IllegalStateException("No se pudo leer el archivo de API key: " + apiKeyFilePath, ex);
        }
    }

    /**
     * Constructor por defecto que establece una clave API predeterminada
     */
    public GestorApiKey() {
        APIKEY = "<KEY>";
    }

    /**
     * Obtiene la clave API
     *
     * @return La clave API almacenada
     */
    public String getAPIKEY() {
        return APIKEY;
    }
}
