import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.Compa;

public class Main{
    public static void main(String[] args) {
        // En este código sumamos un métod0 que nos será
        // de utilidad para el caso que nos ocupa.

//                      En el ejemplo anterior
//        deserializamos un Json en un objeto
//        cuyos atributos tenían el mismo nombre
//        que las claves. Es decir, un objeto
//        Json como {nombre: "elquino"}, donde
//        nombre es la clave y elquino el valor,
//        se mapeaba a un objeto que tenía el
//        atributo privado nombre y el getter
//        nombre().
//
//                Si revisamos la respuesta que
//        obtenemos de la API de ExchangeRate en
//        los primeros ejemplos, nos encontramos
//        con un problema.
//
//                Ocurre que las claves de la
//        respuesta tienen un formato que no es
//        el recomendado para usar como nombres
//        de atributos o variables en Java.
//
//                Encontramos claves como
//        "time_last_update_utc", siendo que el
//        estilo recomendado para Java sería
//        timeLastUpdateUtc.
//
//            Cuando Gson intente deserializar a
//        nuestro record esa información buscará
//        un atributo con ese nombre... y no lo
//        encontrará.
//
//        Afortunadamente podemos indicarle las
//        equivalencias de una forma sencilla
//        cuando el caso es transformar
//        "snake_case" a "camelCase":

        // -------------------------------
        // Ahora las claves del Json están en snake_case
        // Nuestro DTO tiene los atributos en camelCase
        String json = "{nombre_nombre: 'El Quino', anio_anio: 2025}";

        // LOWER_CASE_WITH_UNDERSCORES le dice a Gson que
        // los nombres en el JSON están en snake_case y
        // deben mapearse a atributos camelCase.
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        Compa elquino = gson.fromJson(json, Compa.class);

        System.out.println(elquino.nombreNombre());
        System.out.println(elquino.anioAnio());

        // ¡OK! Ahora ya sabemos cómo hay que definir
        // el record para mapear la respuesta de la API.

    }
}