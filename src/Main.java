import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// ¿Cuál es la funcionalidad esencial de nuestro programa?
// -- Consultar un recurso de internet y obtener una respuesta

// * El cliente envía una solicitud a un servidor
// * El servidor procesa la solicitud y devuelve una respuesta

public class Main{
    public static void main(String[] args) throws IOException, InterruptedException {

        // Variable donde almacenaremos la respuesta
        String responseBody;

        // Nuestra solicitud se hará al servidor lode.uno,
        // le pediremos el recurso holamundo.txt"

        // Al valor de la variable url de tipo String
        // la usaremos luego para construir un objeto
        // del tipo solicitud HttpRequest.
        String url = "https://lode.uno/holamundo.txt";

        // El Cliente es el objeto que envía la consulta
        // como un objeto HttpRequest y recibe la respuesta
        // como un objeto HttpResponse.
        HttpClient client = HttpClient.newHttpClient();

        // Contruimos el objeto HttpRequest que representa
        // la solicitud que enviaremos mediante el Cliente
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        // Enviamos la solicitud y almacenamos la
        // respuesta en la variable response del tipo
        // HttpResponse.
        // Invocamos al métod0 send() del objeto Cliente.
        HttpResponse<String> response = client
            .send(request, HttpResponse.BodyHandlers.ofString());

        // Aquí ya hicimos la solicitud y cerramos el cliente
        // para que deje de ocupar memoria y recursos.
        // Invocamos al métod0 close() del objeto Cliente.
        client.close();

        // La respuesta tendrá un encabezado con información
        // acerca de cómo el servidor manejó la consulta
        // y un cuerpo que contendrá la respuesta en sí.
        // Invocamos al métod0 body() del objeto Response.
        responseBody = response.body();

        System.out.println(responseBody);
    }
}