import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// ¿Cuál es la funcionalidad esencial de nuestro programa?
// -- Conectarse a un recurso de internet y obtener la respuesta

// * El cliente envía una solicitud a un servidor
// * El servidor procesa la solicitud y devuelve una respuesta

public class Main{
    public static void main(String[] args) throws IOException, InterruptedException {

        // Variable donde almacenaremos la respuesta
        String responseBody;

        // Nuestra solicitud se hará al servidor lode.uno,
        // le pediremos el recurso holamundo.txt"
        // Al siguiente String se lo pasaremos luego al
        // métod0 de HttpClient que construye un objeto
        // del tipo solicitud HttpRequest.
        String url = "https://lode.uno/holamundo.txt";

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
    }
}