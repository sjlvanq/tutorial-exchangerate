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
        // Cada endpoint corresponderá a una consulta distinta.

        // Usaremos el endpoint /pair con el cual podremos consultar
        // el ratio de conversión de las monedas que se le indiquen
        // o la conversión de una cantidad determinada.

        // Para el primer caso, ratio de conversión, usaremos
        // GET https://v6.exchangerate-api.com/v6/CLAVE-DE-API/pair/MONEDA-1/MONEDA-2

        // Información extra para ampliar:
        //     GET indica el métod0 HTTP que habitualmente será GET o POST.
        //     Las solicitudes GET pueden hacerse desde la barra de direcciones
        //     del navegador web.

        // Para el segundo caso, conversión de una cantidad determinada, usaremos
        // GET https://v6.exchangerate-api.com/v6/CLAVE-DE-API/pair/MONEDA-1/MONEDA-2/CANTIDAD

        // El endpoint, como puede verse, recibirá valores variables:
        // -- CLAVE-DE-API
        // -- MONEDA-1
        // -- MONEDA-2
        // -- CANTIDAD

        // ¡Declarémoslas como variables en nuestro programa
        // y asignémosle valores!

        // A la clave de API la obtuvimos en https://app.exchangerate-api.com/sign-up
        // Usemos esta, no es necesario crear otra.
        String claveDeApi = "382e8d7a9bae4cf4c9c58bfa";

        // Códigos: https://www.exchangerate-api.com/docs/supported-currencies
        String moneda1 = "EUR"; // Código de euro.
        String moneda2 = "USD"; // Código de dolar de EEUU.

        String cantidad = "2"; // 2 euros a 2 dólares

        // Al siguiente String se lo pasaremos luego al
        // métod0 de HttpClient que construye un objeto
        // del tipo solicitud HttpRequest.
        String url = "https://v6.exchangerate-api.com/v6/"+claveDeApi+"/pair/"+moneda1+"/"+moneda2+"/"+cantidad;
        // Expandiendo las variables la URL de la solicitud quedará como
        // https://v6.exchangerate-api.com/v6/382e8d7a9bae4cf4c9c58bfa/pair/EUR/USD/2


        // El Cliente es el objeto que envía la consulta
        // como un objeto HttpRequest y recibe la respuesta
        // como un objeto HttpResponse.
        HttpClient client = HttpClient.newHttpClient();

        // El Request es la solicitud que envía el Cliente
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        // Aquí se envía la solicitud y se almacena la
        // respuesta en una variable.
        // Invocamos al métod0 send que devuelve un objeto
        // de tipo respuesta HttpResponse.
        HttpResponse<String> response = client
            .send(request, HttpResponse.BodyHandlers.ofString());

        // Aquí ya hicimos la solicitud y cerramos el cliente
        // para que deje de ocupar memoria y recursos.
        // Invocamos al métod0 close() del objeto HttpClient.
        client.close();

        // La respuesta tendrá un encabezado con información
        // acerca de cómo el servidor manejó la consulta.
        // y un cuerpo que será la información que solicitamos.
        // Invocamos al métod0 body() del objeto HttpResponse.
        responseBody = response.body();

        System.out.println(responseBody);
        // Ahora obtendremos algo similar a:
        // {"result":"success","documentation":"https://www.exchangerate-api.com/docs","terms_of_use":"https://www.exchangerate-api.com/terms","time_last_update_unix":1747440002,"time_last_update_utc":"Sat, 17 May 2025 00:00:02 +0000","time_next_update_unix":1747526402,"time_next_update_utc":"Sun, 18 May 2025 00:00:02 +0000","base_code":"EUR","target_code":"USD","conversion_rate":1.1169,"conversion_result":2.2338}
        // Esto es un archivo Json, un formato de texto para intercambiar datos
        // estructurados entre máquinas.
        // Formateado lo veremos mejor:
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

        // ¡ALLÍ ESTÁ LA CONVERSIÓN HECHA!
        // en el valor de la clave 'conversion_result'
    }
}