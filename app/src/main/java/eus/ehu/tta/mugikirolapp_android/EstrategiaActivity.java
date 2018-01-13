package eus.ehu.tta.mugikirolapp_android;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class EstrategiaActivity extends AppCompatActivity {

    public static final String EXTRA_SPORTID = "i";
    public static final int VIDEO_REQUEST_CODE = 1;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estrategia);

        Intent intent = getIntent();
        num = intent.getIntExtra(EXTRA_SPORTID,100);

        this.setTitle("Estrategia");

        WebView video = (WebView)findViewById(R.id.estrategiavideo);
        WebSettings setins = video.getSettings();
        setins.setJavaScriptEnabled(true);

        switch (num) {
            case 0:
                video.loadData("<iframe src=\"https://youtube.com/embed/2L21Tibt-tI\"></iframe>","text/HTML",null);
                break;
            case 1:
                video.loadData("<iframe src=\"https://youtube.com/embed/eb3GbqTtSG4\"></iframe>","text/HTML",null);
                break;
            case 2:
                video.loadData("<iframe src=\"https://youtube.com/embed/LE38nnFDnFk\"></iframe>","text/HTML",null);
                break;
            case 3:
                video.loadData("<iframe src=\"https://youtube.com/embed/IBu4bZ8Mha8\"></iframe>","text/HTML",null);
                break;
        }

        video.setBackgroundColor(Color.TRANSPARENT);
    }

    public void grabar(View view){
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
        {
            Toast.makeText(getApplicationContext(),"No tienes camara",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null){
                startActivityForResult(intent, VIDEO_REQUEST_CODE);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"No tienes app de camara",Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == VIDEO_REQUEST_CODE){
            Toast.makeText(getApplicationContext(),"Video guardado",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Algo ha fallado",Toast.LENGTH_SHORT).show();
        }
    }
}
