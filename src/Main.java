import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main{
    public static void main(String[] args) {
        String responseBody;

        // Manejo de excepciones
//
//        El métod0 send() de HTTPClient nos
//        obliga a hacer algo con las excepciones
//        IOException y InterruptedException, que
//        pueden ser lanzadas por diversos
//        problemas en la conexión o en la
//        obtención de los datos.
//
//        En versiones anteriores de este código
//        las declarábamos en la firma del métod0
//        main:
//            public static void main(String[] args) throws IOException, InterruptedException
//
//        pero esto era sólo para evitar errores
//        en la compilación.
//
//        Ese tipo de declaraciones hechas en la
//        firma del métod0 puede ser útil cuando
//        se delega el manejo de las excepciones
//        a otra parte del programa, pero siendo
//        main nuestro métod0 principal no hay a
//        dónde delegar nada.
//
//        Lo ideal sería que si se produjera algún
//        error en la conexión, pudiera presentarse
//        al usuario un mensaje amigable
//        informándoselo.
//
//        Entonces vamos ahora a dotar al código
//        de una estructura básica para hacerlo.

        String claveDeApi = "382e8d7a9bae4cf4c9c58bfa";

        String moneda1 = "EUR";
        String moneda2 = "USD";
        String cantidad = "2";

        String url = "https://v6.exchangerate-api.com/v6/"+claveDeApi+"/pair/"+moneda1+"/"+moneda2+"/"+cantidad;

        // La forma try(recurso) se asegura de cerrar
        // el objeto entre paréntesis se produzca o
        // no algún error durante el proceso.

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            responseBody = response.body();
            System.out.println(responseBody);

        } catch (IOException | InterruptedException e) {
            System.err.println("Ocurrió un error durante la solicitud HTTP:");
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }

        // __ A tener en cuenta:__
//        No se producirá una excepción si, por
//        ejemplo, se envía un código de moneda
//        incorrecto. En ese caso no habrá habido
//        ningún problema con la conexión y el
//        manejo será distinto; bien se podrá
//        consultar el encabezado de la respuesta,
//        o bien la clave result del Json recibido 
//        (para el caso específico de la API de 
//        ExchangeRate).

    }
}