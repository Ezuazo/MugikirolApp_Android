package eus.ehu.tta.mugikirolapp_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    public static final String EXTRA_SPORTID = "i";
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        num = intent.getIntExtra(EXTRA_SPORTID,100);

        String[] textos = getString(R.string.deportes).split(";");
        this.setTitle(textos[num]);
        ImageView gif = (ImageView)findViewById(R.id.gif);
        switch (num) {
            case 0:
                gif.setImageResource(R.drawable.hockey);
                break;
            case 1:
                gif.setImageResource(R.drawable.baloncesto);
                break;
            case 2:
                gif.setImageResource(R.drawable.balonmano);
                break;
            case 3:
                gif.setImageResource(R.drawable.volleyball);
                break;
        }

    }


    public void gotoTest(View view){
        /*
        Intent intent = new Intent(this,MenuActivity.class);
        intent.putExtra(MenuActivity.EXTRA_SPORTID, num);
        startActivity(intent);*/

        Toast.makeText(getApplicationContext(),R.string.errorfunction,Toast.LENGTH_SHORT).show();
    }

    public void gotoReglas(View view){
        Intent intent = new Intent(this,ReglasActivity.class);
        intent.putExtra(MenuActivity.EXTRA_SPORTID, num);
        startActivity(intent);

    }

    public void gotoLexico(View view){
        Intent intent = new Intent(this,LexicoActivity.class);
        intent.putExtra(LexicoActivity.EXTRA_SPORTID, num);
        startActivity(intent);
    }
    public void gotoEstrategia(View view){
        Intent intent = new Intent(this,EstrategiaActivity.class);
        intent.putExtra(EstrategiaActivity.EXTRA_SPORTID, num);
        startActivity(intent);
    }

}
