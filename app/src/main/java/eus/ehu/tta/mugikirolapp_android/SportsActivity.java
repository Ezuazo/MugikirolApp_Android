package eus.ehu.tta.mugikirolapp_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import Modelo.ProgessTask;
import Modelo.Servidor;
import Modelo.Usuario;

public class SportsActivity extends AppCompatActivity  {

    public static final String EXTRA_USER = "user";
    Usuario user;
    Servidor server;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        Intent intent = getIntent();
        user = (Usuario) intent.getSerializableExtra(EXTRA_USER);
        server = new Servidor();
    }

    public void gotoSport(View view) {
        int i = Integer.parseInt(view.getTag().toString());
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra(MenuActivity.EXTRA_SPORTID, i);
        startActivity(intent);

    }

    public void addActivity(View view) {
        Toast.makeText(getApplicationContext(), R.string.errorlogin, Toast.LENGTH_SHORT).show();

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

}