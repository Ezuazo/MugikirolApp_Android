package eus.ehu.tta.mugikirolapp_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SportsActivity extends AppCompatActivity {

    public static final String EXTRA_LOGIN = "login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
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

