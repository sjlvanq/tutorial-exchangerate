import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main{
    public static void main(String[] args) throws IOException, InterruptedException {
        String responseBody;

        // Ahora probaremos hacer la consulta al servidor
        // de ExchangeRate que es una Web API.

        // Documentación completa: https://www.exchangerate-api.com/docs/

        // Una Web API tiene distintos endpoints.
        // Cada endpoint corresponde a una consulta distinta.

        // Usaremos el endpoint /pair con el cual podremos consultar
        // el ratio de conversión de las monedas que se le indiquen
        // o la conversión de una cantidad determinada de dinero entre
        // esas monedas.

        // Para el primer caso, obtener el ratio de conversión, usaremos
        // GET https://v6.exchangerate-api.com/v6/CLAVE-DE-API/pair/MONEDA-1/MONEDA-2

        // Información extra para ampliar:
        //     GET indica el métod0 HTTP requerido. Habitualmente será GET o POST.
        //     Las solicitudes GET pueden hacerse desde la barra de direcciones
        //     del navegador web.

        // Para el segundo caso, convertir una cantidad determinada de dinero, usaremos
        // GET https://v6.exchangerate-api.com/v6/CLAVE-DE-API/pair/MONEDA-1/MONEDA-2/CANTIDAD

        // El endpoint, como puede verse, recibirá valores variables:
        // -- CLAVE-DE-API
        // -- MONEDA-1
        // -- MONEDA-2
        // -- CANTIDAD

        // Declarémoslas como variables en nuestro programa

        // A la clave de API la obtuvimos en https://app.exchangerate-api.com/sign-up
        // Usemos esta, no es necesario crear otra.
        String claveDeApi = "382e8d7a9bae4cf4c9c58bfa";

        // Códigos de monedas: https://www.exchangerate-api.com/docs/supported-currencies
        String moneda1 = "EUR"; // Código de euro.
        String moneda2 = "USD"; // Código de dolar de EEUU.

        String cantidad = "2"; // 2 euros a 2 dólares

        // Ahora construiremos la URL usando estas variables
        String url = "https://v6.exchangerate-api.com/v6/"+claveDeApi+"/pair/"+moneda1+"/"+moneda2+"/"+cantidad;
        // Expandiendo las variables la URL de la solicitud quedará como
        // https://v6.exchangerate-api.com/v6/382e8d7a9bae4cf4c9c58bfa/pair/EUR/USD/2


        // Ya hemos visto qué es y qué hace el objeto Cliente.
        HttpClient client = HttpClient.newHttpClient();

        // Ya hemos visto qué es y para qué se emplea el objeto Response.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        // Enviamos la solicitud
        HttpResponse<String> response = client
            .send(request, HttpResponse.BodyHandlers.ofString());

        // Cerramos el cliente
        client.close();

        // La parte de la respuesta que nos interesa aquí es el body().
        responseBody = response.body();

        System.out.println(responseBody);

        // Esta vez obtendremos algo similar a:
        // {"result":"success","documentation":"https://www.exchangerate-api.com/docs","terms_of_use":"https://www.exchangerate-api.com/terms","time_last_update_unix":1747440002,"time_last_update_utc":"Sat, 17 May 2025 00:00:02 +0000","time_next_update_unix":1747526402,"time_next_update_utc":"Sun, 18 May 2025 00:00:02 +0000","base_code":"EUR","target_code":"USD","conversion_rate":1.1169,"conversion_result":2.2338}
        // Esto es un archivo Json, un formato de texto para intercambiar datos
        // estructurados entre máquinas.

        // Formateado:
        /*
        {
            "result": "success",
            "documentation": "https://www.exchangerate-api.com/docs",
            "terms_of_use": "https://www.exchangerate-api.com/terms",
            "time_last_update_unix": 1747440002,
            "time_last_update_utc": "Sat, 17 May 2025 00:00:02 +0000",
            "time_next_update_unix": 1747526402,
            "time_next_update_utc": "Sun, 18 May 2025 00:00:02 +0000",
            "base_code": "EUR",
            "target_code": "USD",
            "conversion_rate": 1.1169,
            "conversion_result": 2.2338
        }
        */

        // ¡Allí está la conversión hecha
        // en el valor de la clave 'conversion_result'!
    }
}