import com.google.gson.Gson;
import dto.Compa;

public class Main{
    public static void main(String[] args) {
        // Nos hemos conectado a la Web API y hemos obtenido
        // la información que necesitábamos.

        // Hay que avanzar hacia un programa que alguien
        // pueda usar sin necesidad de modificar el código
        // ni buscar la información que le interesa dentro
        // de más código como es el Json que recibimos del
        // servidor.

        // Para esto último usaremos Gson
        // Con Gson podemos transformar un Json
        // { nombre: 'El programador', anio: 2025 }
        // en un objeto de Java que nos permita, por ejemplo,
        // mostrar en pantalla el valor de 'nombre' haciendo:
        // System.out.println(compa.nombre);

        String json = "{nombre: 'El programador', anio: 2025}";

        // Hemos definido un tipo de objeto al cual volcar
        // esta información en src/dto/Compa.java

        // Lo que sigue es, en principio, tan simple como hacer:
        Gson gson = new Gson(); // Creamos un objeto de tipo Gson
        Compa programador = gson.fromJson(json, Compa.class);

        // Imprimimos el nombre
        System.out.println(programador.nombre());

        // El record Compa ha creado automáticamente
        // un atributo privado nombre
        // y un getter para ese atributo nombre()

        // También lo ha hecho con el año
        System.out.println(programador.anio());

    }
}