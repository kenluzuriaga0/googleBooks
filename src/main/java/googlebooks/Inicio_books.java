
package googlebooks;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import googlebooks.Libro;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 *
 * @author Proyectos
 */
public class Inicio_books {
    
     public void verLibros() throws IOException{
         Libro libro = new Libro();
         
        //1. vamos a traer los datos de la API (Esto se copia de POSTMAN)
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://www.googleapis.com/books/v1/volumes?q=carrie+inauthor:king&maxResults=1&key="+libro.getApi_key())
                .get().build();
        Response response = client.newCall(request).execute();
        
        //2. creamos variable String Json (se guarda el objeto respuesta en un string)
        String elJson = response.body().string();
        
        //cortar los corchetes
//        elJson = elJson.substring(1, elJson.length()); //extraer(inicio,fin)
//        elJson = elJson.substring(0,elJson.length()-1);
        


        //crear u objeto de la clase Gson
        Gson gson = new Gson();
         JsonParser parser = new JsonParser();
         //para json anidados sin array
            //JsonObject title = parser.parse(elJson).getAsJsonObject().getAsJsonObject("volumeInfo");
            
         // para json anidados con array   
         JsonObject title = parser.parse(elJson).getAsJsonObject().getAsJsonArray("items").get(0).getAsJsonObject().getAsJsonObject("volumeInfo");
        //Libro libro = gson.fromJson(elJson, Libro.class); //convertir la respuesta de la api.. en clase Libro
        
        
        System.out.println("titulo: "+title.get("title").getAsString());
     

    }
    
    public static void main(String[] args) throws IOException {
//       Inicio_books lib = new Inicio_books();
//        lib.verLibros(); //ver el JSON entero
        
        Enlistar_Books lib2 = new Enlistar_Books();
        lib2.verLibros();
        lib2=null;

    }
    
    
}
