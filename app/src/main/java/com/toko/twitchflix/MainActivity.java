package com.toko.twitchflix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);

        CardView watchCard = (CardView) findViewById(R.id.watch_card);
        CardView recordCard = (CardView) findViewById(R.id.record_card);

        watchCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWatchActivity();
            }
        });

        recordCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecordActivity();
            }
        });
    }

    //-----------------------------------START ACTIVITIES-----------------------------------

    public void openWatchActivity() {
        Intent i = new Intent(this, WatchActivity.class);
        this.startActivity(i);
    }

    public void openRecordActivity() {
        Intent i = new Intent(this, RecordActivity.class);
        this.startActivity(i);
    }

    //-----------------------------------OPTIONS MENU-----------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.options_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.option_settings:
                Intent i = new Intent(this, SettingsActivity.class);
                this.startActivity(i);
                return true;

            case R.id.option_logout:
                Intent j = new Intent(this, LoginActivity.class);
                this.startActivity(j);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
