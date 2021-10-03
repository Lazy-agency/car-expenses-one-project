package agency.lazy.android.carexpenses.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import agency.lazy.android.carexpenses.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_TIME_OUT = 2100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.splashScreenDelayedHandler();
    }

    void splashScreenDelayedHandler(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(
                        new Intent(SplashActivity.this, MainActivity.class)
                );
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}