package eus.ehu.tta.mugikirolapp_android;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class ReglasActivity extends AppCompatActivity {

    public static final String EXTRA_SPORTID = "i";
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reglas);

        Intent intent = getIntent();
        num = intent.getIntExtra(EXTRA_SPORTID,100);

        this.setTitle("Reglas");

        WebView video = (WebView)findViewById(R.id.reglasvideo);
        WebSettings setins = video.getSettings();
        setins.setJavaScriptEnabled(true);
        video.loadData("<iframe src=\"https://www.youtube.com/embed/b89CnP0Iq30\"></iframe>","text/HTML",null);
        video.setBackgroundColor(Color.TRANSPARENT);



/*        switch (num) {
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
        }*/
    }
}
