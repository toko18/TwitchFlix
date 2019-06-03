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
                showLoginActivity();
            }
        }, 2000);
    }

    private void showLoginActivity() {
        Intent intent = new Intent(
                SplashActivity.this,LoginActivity.class
        );
        startActivity(intent);
        finish();
    }
}