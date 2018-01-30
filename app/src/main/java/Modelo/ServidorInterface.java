package Modelo;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by endika on 19/01/18.
 */

public interface ServidorInterface {

    public void addUser(Usuario user) throws IOException;

    public String logIn(Usuario user) throws IOException;

    public String stats() throws IOException;

    void newActivity(Actividad activity) throws IOException;

    List<Actividad> statsByDate(Actividad activity) throws IOException, JSONException;
}
