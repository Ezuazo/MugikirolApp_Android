package eus.ehu.tta.mugikirolapp_android;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Modelo.Actividad;
import Modelo.ProgessTask;
import Modelo.Servidor;
import Modelo.Usuario;

public class SportsActivity extends AppCompatActivity  {

    public static final String EXTRA_USER = "user";
    Usuario user;
    Servidor server;
    DatePicker begin;
    DatePicker end;
    Spinner sport;
    Actividad activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        Intent intent = getIntent();
        user = (Usuario) intent.getSerializableExtra(EXTRA_USER);
        server = new Servidor();
        activity = new Actividad();
    }

    public void gotoSport(View view) {
        int i = Integer.parseInt(view.getTag().toString());
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(MenuActivity.EXTRA_SPORTID, i);
        startActivity(intent);

    }


    public void addActivity(View view) {
        LayoutInflater layourinflater = LayoutInflater.from(this);
        View mView = layourinflater.inflate(R.layout.formulario,null);
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        alertdialog.setView(mView);

        AlertDialog dialog = alertdialog.create();

        Switch switchButton = (Switch)mView.findViewById(R.id.simpleSwitch);
        begin = (DatePicker)mView.findViewById(R.id.begindate);
        end = (DatePicker)mView.findViewById(R.id.enddate);
        sport = (Spinner) mView.findViewById(R.id.spinneropcs);
        final TextView texto = (TextView)mView.findViewById(R.id.escogefecha);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                System.out.println("OncheckedChanged");
                if (isChecked) {
                    begin.setVisibility(View.GONE);
                    end.setVisibility(View.VISIBLE);
                    texto.setText("Escoge la fecha de fin");

                }
                else {
                    end.setVisibility(View.GONE);
                    begin.setVisibility(View.VISIBLE);
                    texto.setText("Escoge la fecha de inicio");
                }

            }
        });

        dialog.show();

    }

    public void seeActivities(View view) {

        new ProgessTask<String>(this) {
            @Override
            protected String work() throws Exception {

                return server.stats();
            }

            @Override
            protected void onFinish(String stats) {

                String[] porcentajes = stats.split(",");
                PieChart pieChart = (PieChart) findViewById(R.id.piechart);

                ArrayList<Entry> yvalues = new ArrayList<Entry>();
                yvalues.add(new Entry(Integer.parseInt(porcentajes[0]), 0));
                yvalues.add(new Entry(Integer.parseInt(porcentajes[1]), 1));
                yvalues.add(new Entry(Integer.parseInt(porcentajes[2]), 2));
                yvalues.add(new Entry(Integer.parseInt(porcentajes[3]), 3));


                PieDataSet dataSet = new PieDataSet(yvalues, "Deportes");

                ArrayList<String> xVals = new ArrayList<String>();

                xVals.add("Hockey");
                xVals.add("Baloncesto");
                xVals.add("Volley");
                xVals.add("Balonmano");


                PieData data = new PieData(xVals, dataSet);
                data.setValueFormatter(new PercentFormatter());
                pieChart.setData(data);
                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                pieChart.setDrawHoleEnabled(true);
                pieChart.setTransparentCircleRadius(38f);

                pieChart.setHoleRadius(38f);
                pieChart.setVisibility(View.VISIBLE);
                pieChart.animateXY(1400, 1800);

            }

        }.execute();
    }

    public void sendActivity(View view){

        String inicio = Integer.toString(begin.getYear());
        inicio = formatearFecha(inicio,begin.getMonth()+1);
        inicio = formatearFecha(inicio,begin.getDayOfMonth());

        String fin = Integer.toString(end.getYear());
        fin = formatearFecha(fin,end.getMonth()+1);
        fin = formatearFecha(fin,end.getDayOfMonth());


        activity.setSport(getResources().getStringArray(R.array.opciones)[sport.getSelectedItemPosition()]);
        activity.setBegindate(inicio);
        activity.setEnddate(fin);
        activity.setUserid(user.getId());


        new ProgessTask<Void>(this){
            @Override
            protected Void work() throws Exception{

                server.newActivity(activity);
                return null;
            }

            @Override
            protected void onFinish(Void user) {
                Toast.makeText(getApplicationContext(),"Actividad añadida",Toast.LENGTH_SHORT).show();

            }

        }.execute();


    }

    public String formatearFecha(String actual, int valor){
        if (valor < 10){
            actual = actual+"0"+Integer.toString(valor);
        }
        else
        {
            actual= actual+Integer.toString(valor);
        }
        return actual;
    }

}