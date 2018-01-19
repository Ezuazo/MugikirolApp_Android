package Modelo;

import java.io.IOException;

/**
 * Created by endika on 19/01/18.
 */

public interface ServidorInterface {

    public void addUser(Usuario user) throws IOException;

    public boolean logIn(Usuario user) throws IOException;

    public String stats() throws IOException;
}
