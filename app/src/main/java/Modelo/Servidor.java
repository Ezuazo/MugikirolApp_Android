package Modelo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by endika on 19/01/18.
 */

public class Servidor implements ServidorInterface {
    RestClient client;
    private final String baseUrl = "http://u017633.ehu.eus:28080/Mugikirolapp/rest/kirola";

    @Override
    public void addUser(Usuario user) throws IOException {
        System.out.println("AddUser");
        client = new RestClient(baseUrl);
        JSONObject userJson= new JSONObject();
        try {
            userJson.put("login",user.getLogin());
            userJson.put("name",user.getName());
            userJson.put("password",user.getPassword());
        } catch (JSONException e) {
            System.out.println("Error al rellenar el usuario");
        }

        String code = client.postJson(userJson,"addUser");
        System.out.println(code);
    }

    @Override
    public boolean logIn(Usuario user) throws IOException {
        System.out.println("logIn");
        client = new RestClient(baseUrl);
        JSONObject userJson= new JSONObject();
        try {
            userJson.put("login",user.getLogin());
            userJson.put("name",user.getName());
            userJson.put("password",user.getPassword());
        } catch (JSONException e) {
            System.out.println("Error al rellenar el usuario");
        }

        String respuesta = client.postJson(userJson,"logIn");
        System.out.println(respuesta);
        if(respuesta.equals("Nok")){
            return false;
        }
        else{
            return true;
        }
    }

    @Override
    public String stats() throws IOException {
        System.out.println("Stats");
        client = new RestClient(baseUrl);
        String respuesta = client.getString("stats");
        return respuesta;
    }
}
