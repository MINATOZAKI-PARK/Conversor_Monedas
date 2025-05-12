import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestorApiKey {
    private final String APIKEY;
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

    public GestorApiKey() {
        APIKEY = "<KEY>";
    }

    public String getAPIKEY() {
        return APIKEY;
    }
}
