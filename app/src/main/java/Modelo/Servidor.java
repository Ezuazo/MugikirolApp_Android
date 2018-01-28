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
    public String logIn(Usuario user) throws IOException {
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
        return respuesta;
    }

    @Override
    public String stats() throws IOException {
        System.out.println("Stats");
        client = new RestClient(baseUrl);
        String respuesta = client.getString("stats");
        return respuesta;
    }

    @Override
    public void newActivity(Actividad activity) throws IOException {
        System.out.println("newActivity");
        client = new RestClient(baseUrl);
        JSONObject activityJSON = new JSONObject();
        try {
            activityJSON.put("begindate",Integer.parseInt(activity.getBegindate()));
            activityJSON.put("enddate",Integer.parseInt(activity.getEnddate()));
            activityJSON.put("user",activity.getUserid());
            activityJSON.put("sport",activity.getSport());
        } catch (JSONException e) {
            System.out.println("No se ha podido crear el objeto JSON");
        }
        String code = client.postJson(activityJSON,"newActivity");
        System.out.println(code);

    }
}
