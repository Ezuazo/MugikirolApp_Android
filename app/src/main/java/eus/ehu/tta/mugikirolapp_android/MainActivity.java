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
        Intent intent = new Intent(this,SportsActivity.class);
        user.setLogin(((EditText)findViewById(R.id.login)).getText().toString());
        user.setPassword(((EditText)findViewById(R.id.passwd)).getText().toString());
        user.setName(user.getLogin()+":mugikirolapp");

        if (authenticate(user.getLogin(),user.getPassword())){
            intent.putExtra(SportsActivity.EXTRA_USER, user);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),R.string.errorlogin,Toast.LENGTH_SHORT).show();
        }
    }

    private boolean authenticate(String login, String password){

        //Aqui habria que checkear con el servidor
        return true;
    }

    public void register (View view){
System.out.println("Register");
        user.setLogin(((TextView)findViewById(R.id.login)).getText().toString());
        user.setPassword(((TextView)findViewById(R.id.passwd)).getText().toString());
        user.setName(user.getLogin()+":mugikirolapp");

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
}
