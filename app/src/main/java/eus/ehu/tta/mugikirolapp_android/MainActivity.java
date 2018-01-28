package eus.ehu.tta.mugikirolapp_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Modelo.ProgessTask;
import Modelo.Servidor;
import Modelo.Usuario;

public class MainActivity extends AppCompatActivity {

    Usuario user;
    Servidor server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        server = new Servidor();
        user = new Usuario();
    }

    public void login(View view){
        rellenarUsuario();
        authenticate();
    }

    private void authenticate(){


        new ProgessTask<String>(this){
            @Override
            protected String work() throws Exception{

                return server.logIn(user);
            }

            @Override
            protected void onFinish(String iduser) {

                if(!iduser.equals("Nok")) {
                    Intent intent = new Intent(this.context, SportsActivity.class);
                    rellenarUsuario();
                    user.setId(Integer.parseInt(iduser));
                    intent.putExtra(SportsActivity.EXTRA_USER, user);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),R.string.errorlogin,Toast.LENGTH_SHORT).show();
                }
            }

        }.execute();


    }

    public void register (View view){
System.out.println("Register");

        rellenarUsuario();
        new ProgessTask<Void>(this){
            @Override
            protected Void work() throws Exception{

                server.addUser(user);
                return null;
            }

            @Override
            protected void onFinish(Void user) {
                Toast.makeText(getApplicationContext(),"Usuario registrado",Toast.LENGTH_SHORT).show();

            }

        }.execute();

    }

    public void rellenarUsuario (){
        user.setLogin(((TextView)findViewById(R.id.login)).getText().toString());
        user.setPassword(((TextView)findViewById(R.id.passwd)).getText().toString());
        user.setName(user.getLogin()+":mugikirolapp");
    }
}
