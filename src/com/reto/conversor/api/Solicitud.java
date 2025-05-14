package com.reto.conversor.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import com.reto.conversor.toGson.ExchangeRateResponse;
import com.google.gson.Gson;

/**
 * Clase que gestiona la creación de solicitudes HTTP para obtener cotizaciones entre monedas
 * desde un servicio de tasas de cambio. Utiliza una clave API para la autorización y opera
 * sobre una base de URL predefinida. Incluye funcionalidades para procesar las respuestas
 * obtenidas del servicio.
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

    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .connectTimeout(Duration.ofSeconds(10))
            .build(); // Crea el cliente HTTP con timeout de 10 segundos


    private static final Gson GSON = new Gson();


    public Solicitud(String apiKeyFilePath) {
        GestorApiKey key = new GestorApiKey(apiKeyFilePath);
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

        if (base == null || target == null || base.isEmpty() || target.isEmpty() || amount <= 0){
            throw new IllegalArgumentException("Parametros invalidos para la conversion");
        }

        // Construye la solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + APIKEY + "/pair/" + base + "/" + target + "/" + amount))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        // Envía la solicitud y retorna la respuesta
        return CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public ExchangeRateResponse procesarRespuesta(HttpResponse<String> response) {
         // Crea un Gson para procesar la respuesta
        return GSON.fromJson(response.body(), ExchangeRateResponse.class); // Procesa la respuesta y devuelve un objeto
    }

    public ExchangeRateResponse obtenerCotizacion(String base, String target, int amount)
            throws IOException, InterruptedException{
        HttpResponse<String> response = solicitarCotizacion(base, target, amount);
        return procesarRespuesta(response);
    }
 }

