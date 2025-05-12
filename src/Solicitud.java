import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;

public class Solicitud {
    private final String APIKEY; //
    private final String URL = "https://v6.exchangerate-api.com/v6/";

    Solicitud() {
        GestorApiKey key = new GestorApiKey("D:\\Proyectos-Java\\conversor\\src\\key");
        this.APIKEY = key.getAPIKEY();
    }


    public HttpResponse<String> solicitarCotizacion(String divisa)
            throws IOException, InterruptedException {
            HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL + APIKEY + "/latest/"+divisa))
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            Solicitud s = new Solicitud();
            HttpResponse respuesta = s.solicitarCotizacion("MXN");
            System.out.println(respuesta.body());
            System.out.println(respuesta.statusCode());
        } catch (HttpTimeoutException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
