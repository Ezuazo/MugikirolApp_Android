package eus.ehu.tta.mugikirolapp_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    public static final String EXTRA_SPORTID = "i";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        int num = intent.getIntExtra(EXTRA_SPORTID,100);
        TextView prueba = (TextView)findViewById(R.id.textoprueba);
        String[] textos = getString(R.string.deportes).split(";");
        prueba.setText(textos[num]);
    }
}
