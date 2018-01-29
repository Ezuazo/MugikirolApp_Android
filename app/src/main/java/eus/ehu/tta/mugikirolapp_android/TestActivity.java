package eus.ehu.tta.mugikirolapp_android;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String EXTRA_SPORTID = "i";
    public static final String EXTRA_NUMPREG = "a";
    private static final int MAXPREG = 3;
    int correct = 0;
    private int selected;
    RadioGroup group;
    private int num;
    private int numpreg;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        context = this.context;

        Intent intent = getIntent();
        num = intent.getIntExtra(EXTRA_SPORTID,100);
        numpreg = intent.getIntExtra(EXTRA_NUMPREG,0);

        this.setTitle("Test");

        TextView pregunta = (TextView)findViewById(R.id.pregunta);
        String question = "Pregunta default";
        /*
        switch (num) {
            case 0:
                question = getResources().getStringArray(R.array.Hockey)[numpreg];
                break;
            case 1:
                question = getResources().getStringArray(R.array.Basket)[numpreg];
                break;
            case 2:
                question = getResources().getStringArray(R.array.Balonmano)[numpreg];
                break;
            case 3:
                question = getResources().getStringArray(R.array.Volley)[numpreg];
                break;
        }*/

        Resources res = getResources();
        TypedArray ta = res.obtainTypedArray(R.array.preguntas);
        int id = ta.getResourceId(num,-1);
        String[] array = res.getStringArray(id);
        pregunta.setText(array[numpreg]);
        group = (RadioGroup)findViewById(R.id.test_choices);

        TypedArray ta_res = res.obtainTypedArray(R.array.respuestas);
        int id_res = ta_res.getResourceId(num,-1);
        TypedArray ta_res_deporte = res.obtainTypedArray(id_res);
        int id_res_deporte = ta_res_deporte.getResourceId(numpreg,-1);
        String[] respuestas_array = res.getStringArray(id_res_deporte);

        TypedArray ta_sol = res.obtainTypedArray(R.array.correctas);
        int id_sol = ta_sol.getResourceId(num,-1);
        int[] sol = res.getIntArray(id_sol);
        for (int k=0; k<4;k++){
            RadioButton radio = new RadioButton(this);
            radio.setText(respuestas_array[k]);
            radio.setOnClickListener(this);
            group.addView(radio);
            if( k == sol[numpreg] ){
                correct = k;
            }
        }



    }


    public void check(View view){

        ViewGroup layout = (ViewGroup)view.getParent();
        int choices = group.getChildCount();
        for (int i = 0; i < choices; i++){
            group.getChildAt(i).setEnabled(false);
        }
        layout.removeView(findViewById(R.id.button_send_test));
        group.getChildAt(correct).setBackgroundColor(Color.GREEN);
        if (selected != correct){
            group.getChildAt(selected).setBackgroundColor(Color.RED);
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Correcto",Toast.LENGTH_SHORT).show();

        }

        if(numpreg == MAXPREG-1){
           findViewById(R.id.button_finish).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.button_next).setVisibility(View.VISIBLE);
        }

    }

    public void next(View view){
        Intent intent = new Intent(this,TestActivity.class);
        intent.putExtra(TestActivity.EXTRA_SPORTID, num);
        intent.putExtra(TestActivity.EXTRA_NUMPREG, numpreg+1);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    @Override
    public void onClick(View view){
        findViewById(R.id.button_send_test).setVisibility(View.VISIBLE);
        selected = group.indexOfChild(view);
    }

    public void finish(View view){
        /*Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(MenuActivity.EXTRA_SPORTID, num);
        startActivity(intent);*/
        finish();
    }
}







