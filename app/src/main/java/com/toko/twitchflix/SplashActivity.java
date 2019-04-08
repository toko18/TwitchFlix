package com.toko.twitchflix;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showMainActivity();
            }
        }, 2000);
    }

    private void showMainActivity() {
        Intent intent = new Intent(
                SplashActivity.this,MainActivity.class
        );
        startActivity(intent);
        finish();
    }
}