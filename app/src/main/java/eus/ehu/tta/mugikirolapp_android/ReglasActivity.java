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
        video.loadData("<iframe src=\"https://youtube.com/embed/vdYAx6kf-78\"></iframe>","text/HTML",null);

        switch (num) {
            case 0:
                video.loadData("<iframe src=\"https://youtube.com/embed/vdYAx6kf-78\"></iframe>","text/HTML",null);
                break;
            case 1:
                video.loadData("<iframe src=\"https://youtube.com/embed/Vwfxbpq0N2o\"></iframe>","text/HTML",null);
                break;
            case 2:
                video.loadData("<iframe src=\"https://youtube.com/embed/Xn4YGyWIQp8\"></iframe>","text/HTML",null);
                break;
            case 3:
                video.loadData("<iframe src=\"https://youtube.com/embed/OOIW4buBOfs\"></iframe>","text/HTML",null);
                break;
        }

        video.setBackgroundColor(Color.TRANSPARENT);
    }
}
