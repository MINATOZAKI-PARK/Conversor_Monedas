import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;

/**
 * Clase que maneja las solicitudes HTTP al servicio de tasas de cambio
 */
public class Solicitud {
    /**
     * Clave API para el servicio
     */
    private final String APIKEY;

    /**
     * URL base del servicio API
     */
    private final String URL = "https://v6.exchangerate-api.com/v6/";

    /**
     * Constructor que inicializa la clave API desde un archivo
     */
    Solicitud() {
        GestorApiKey key = new GestorApiKey("D:\\Proyectos-Java\\conversor\\src\\key");
        this.APIKEY = key.getAPIKEY();
    }

    /**
     * Realiza una solicitud HTTP para obtener la cotización
     *
     * @param base   Moneda base
     * @param target Moneda destino
     * @param amount Monto a convertir
     * @return Respuesta HTTP con la cotización
     * @throws IOException          Si hay error de conexión
     * @throws InterruptedException Si se interrumpe la conexión
     */
    public HttpResponse<String> solicitarCotizacion(String base, String target, int amount)
            throws IOException, InterruptedException {
        // Crea el cliente HTTP con timeout de 10 segundos
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        // Construye la solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/" + APIKEY + "/pair/" + base + "/" + target + "/" + amount))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        // Envía la solicitud y retorna la respuesta
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            Solicitud s = new Solicitud();
            HttpResponse respuesta = s.solicitarCotizacion("USD","MXN",100);
            System.out.println(respuesta.body());
            System.out.println(respuesta.statusCode());
        } catch (HttpTimeoutException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
