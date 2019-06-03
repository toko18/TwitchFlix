package com.toko.twitchflix;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

public class LoginActivity extends AppCompatActivity {

    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText text_user = findViewById(R.id.editText_user);
        final EditText text_pass = findViewById(R.id.editText_pass2);
        Button login_button = findViewById(R.id.login_button);
        TextView register_button = findViewById(R.id.register_button);

        register_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        MessageDigest messageDigest = null;

                        try {
                            messageDigest = MessageDigest.getInstance("MD5");
                        } catch (Exception e) {
                        }

                        messageDigest.reset();
                        messageDigest.update(text_pass.getText().toString().getBytes());
                        byte[] digest = messageDigest.digest();
                        BigInteger bigInt = new BigInteger(1, digest);
                        String hashtext = bigInt.toString(16);
                        while (hashtext.length() < 32) {
                            hashtext = "0" + hashtext;
                        }

                        String username = text_user.getText().toString();
                        String password = hashtext;

                        try {
                            String url = "http://" + Server.getIP() + ":" + Server.getPortDB() + "/tyger/checklogin?user=" + username + "&pass=" + password;

                            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                            conn.setRequestMethod("GET");

                            BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                            if (input.readLine() == null) {
                                flag = 0;
                            } else
                                flag = 1;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return null;
                    }
                    @Override
                    protected void onPostExecute(Void result){
                        super.onPostExecute(result);

                        if(flag == 1) {
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Wrong username or password!", Toast.LENGTH_LONG).show();
                        }
                    }

                }.execute();
            }
        });
    }
}
