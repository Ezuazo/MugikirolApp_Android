package eus.ehu.tta.mugikirolapp_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LexicoActivity extends AppCompatActivity {

    public static final String EXTRA_SPORTID = "i";
    private int num;
    private static final int OPT_NUM = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lexico);

        Intent intent = getIntent();
        num = intent.getIntExtra(EXTRA_SPORTID,100);

        this.setTitle("Lexico");

        ViewGroup layout = (ViewGroup) findViewById(R.id.tabla);
        String[] items = new String[]{"1","2","3","4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);

        String[] defhockey = getString(R.string.defhockey).split(";");
        String[] palhockey = getString(R.string.palhockey).split(";");
        String[] defbasket = getString(R.string.defbasket).split(";");
        String[] palbasket = getString(R.string.palbasket).split(";");
        String[] defvolley = getString(R.string.defvolley).split(";");
        String[] palvolley = getString(R.string.palvolley).split(";");
        String[] defbalonmano = getString(R.string.defbalonmano).split(";");
        String[] palbalonmano = getString(R.string.palbalonmano).split(";");

        for (int i =0; i<OPT_NUM; i++){
            TextView definicion = new TextView(this);
            Spinner desplegable = new Spinner(this);
            TextView palabra = new TextView(this);


            switch (num){
                case 0:
                    definicion.setText(defhockey[i]);
                    desplegable.setId(i);
                    desplegable.setAdapter(adapter);
                    palabra.setText(palhockey[i]);
                    break;
                case 1:
                    definicion.setText(defbasket[i]);
                    desplegable.setId(i);
                    desplegable.setAdapter(adapter);
                    palabra.setText(palbasket[i]);
                    break;
                case 2:
                    definicion.setText(defvolley[i]);
                    desplegable.setId(i);
                    desplegable.setAdapter(adapter);
                    palabra.setText(palvolley[i]);
                    break;
                case 3:
                    definicion.setText(defbalonmano[i]);
                    desplegable.setId(i);
                    desplegable.setAdapter(adapter);
                    palabra.setText(palbalonmano[i]);
                    break;
            }

            layout.addView(palabra);
            layout.addView(desplegable);
            layout.addView(definicion);


        }


    }

    public void correct(View view){

        String resp = "";
        int correctas=0;
        String[] sol = {};
        switch (num) {
            case 0:
                sol = new String[]{"1","2","3","4"};
                break;
            case 1:
                sol = new String[]{"1","2","3","4"};
                break;
            case 2:
                sol = new String[]{"1","2","3","4"};
                break;
            case 3:
                sol = new String[]{"1","2","3","4"};
                break;
        }
        for (int i=0;i<OPT_NUM;i++) {
            Spinner desplegable = (Spinner) findViewById(i);
            if ( sol[i] == desplegable.getSelectedItem().toString()){
                correctas++;
            }
        }
        if (correctas == OPT_NUM){
            Toast.makeText(getApplicationContext(),"Enhorabuena! "+OPT_NUM+"/"+OPT_NUM,Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Vaya... \nAcertaste:  "+correctas+"/"+OPT_NUM,Toast.LENGTH_SHORT).show();
        }

    }
}
