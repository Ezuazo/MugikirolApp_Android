package eus.ehu.tta.mugikirolapp_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import Modelo.Usuario;

public class SportsActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "user";
    Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        Intent intent = getIntent();
        user = (Usuario)intent.getSerializableExtra(EXTRA_USER);
    }

    public void gotoSport(View view){
        int i = Integer.parseInt(view.getTag().toString());
        Intent intent = new Intent(this,MenuActivity.class);
        intent.putExtra(MenuActivity.EXTRA_SPORTID, i);
        startActivity(intent);

    }
    public void addActivity(View view){
        Toast.makeText(getApplicationContext(),R.string.errorlogin,Toast.LENGTH_SHORT).show();

    }

    public void seeActivities(View view){
        Toast.makeText(getApplicationContext(),R.string.errorlogin,Toast.LENGTH_SHORT).show();
    }
}

