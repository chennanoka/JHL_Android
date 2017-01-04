package nan.com.jobhuntlog;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import nan.com.jobhuntlog.DevelopKit.UI.NNFragmentActivity;

/**
 * Created by NAN on 2016-09-01.
 */
public class SplashScreen extends NNFragmentActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        LinearLayout layout=(LinearLayout)findViewById(R.id.rootview);
        ImageView imgview= new ImageView(this);
        imgview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
       imgview.setImageResource(R.mipmap.bg_jbl);
        layout.addView(imgview);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
