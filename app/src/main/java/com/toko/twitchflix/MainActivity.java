package com.toko.twitchflix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showSettings()
    {
        Toast settingsToast = Toast.makeText(getApplicationContext(), "Antes de mais, boa tarde. Está em SETTINGS! Parabéns. Boa tarde.", Toast.LENGTH_LONG);
        settingsToast.show();
    }
}
