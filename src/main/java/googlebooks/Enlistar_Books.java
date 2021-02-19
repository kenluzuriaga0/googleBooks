package googlebooks;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author Proyectos
 */
public class Enlistar_Books {
//enlista libros cuantos seas posible <=40

    public void verLibros() throws IOException {
        Libro libro = new Libro();

         Scanner scn = new Scanner(System.in);
         System.out.println("Ingrese un titulo");
         String titulo = scn.nextLine();
         //System.out.println("Ingrese un autor");
         //String autor = scn.nextLine();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://www.googleapis.com/books/v1/volumes?q=intitle:+"+titulo+"&maxResults=10&key=" + libro.getApi_key() + "&fields=items.volumeInfo(title,authors,publisher,publishedDate,description,pageCount,categories,averageRating,imageLinks.smallThumbnail)&")
                .method("GET", null)
                .build();
        
         String elJson = null;
        try {
            Response response = client.newCall(request).execute();
            elJson = response.body().string();

        } catch (Exception ex) {
            System.out.println("Fallo en la peticion " + ex.getMessage());
        }

        

        Gson gson = new Gson();
        //System.out.println(elJson);


        //Crear un json y parsearlo desde el string
        JsonObject jsonDatos = new JsonParser().parse(elJson).getAsJsonObject();
        JsonElement array = jsonDatos.get("items"); //crear un nuevo json a partir el atributo "item" (array) 
        System.out.println(String.valueOf(array));

        //Al querer leer un array de objetos, se usa un TypeTolken
        Type listType = new TypeToken<List<Items>>() {
        }.getType();
        ArrayList<Items> arrayDeJson = gson.fromJson(String.valueOf(array), listType);

        //imprimir algunos datos de los libros solicitados (Titulo y autor)
        for (Items arr : arrayDeJson) {
            System.out.println(arr.getVolumeInfo().getTitle() + " .- " + arr.getVolumeInfo().getAutorNombre(0));
        }
    }

}
