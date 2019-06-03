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

public class RegisterActivity extends AppCompatActivity {

    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText text_user = findViewById(R.id.editText_user);
        final EditText text_pass1 = findViewById(R.id.editText_pass1);
        final EditText text_pass2 = findViewById(R.id.editText_pass2);
        Button register_button = findViewById(R.id.register_button);
        TextView cancel_button = findViewById(R.id.cancel_regist);

        cancel_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });


        register_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String s = text_user.getText().toString();
                int invalid = 0;

                for (int i = 0; i < s.length(); i++){
                    char c = s.charAt(i);
                    if(!(c >= 'A' && c <='z') && !Character.isDigit(c)) {
                        invalid = 1;
                        break;
                    }
                }

                if(invalid == 1) {
                    Toast.makeText(getApplicationContext(), "Invalid username! You can only use simple letters and digits.", Toast.LENGTH_LONG).show();
                }

                else if (!text_pass1.getText().toString().equals(text_pass2.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Passwords don't match!", Toast.LENGTH_LONG).show();
                }
                else if(text_pass1.length() >= 2147483647){
                    Toast.makeText(getApplicationContext(), "Password is too long!", Toast.LENGTH_LONG).show();
                }
                else {

                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... params) {
                            MessageDigest messageDigest = null;

                            try {
                                messageDigest = MessageDigest.getInstance("MD5");
                            } catch (Exception e) {
                            }

                            messageDigest.reset();
                            messageDigest.update(text_pass1.getText().toString().getBytes());
                            byte[] digest = messageDigest.digest();
                            BigInteger bigInt = new BigInteger(1, digest);
                            String hashtext = bigInt.toString(16);
                            while (hashtext.length() < 32) {
                                hashtext = "0" + hashtext;
                            }

                            String username = text_user.getText().toString();
                            String password = hashtext;

                            try {
                                String url = "http://" + Server.getIP() + ":" + Server.getPortDB() + "/tyger/register?user=" + username + "&pass=" + password;

                                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                                conn.setRequestMethod("GET");

                                BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                                if (input.readLine().equals("false")) {
                                    flag = 0;
                                } else
                                    flag = 1;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void result) {
                            super.onPostExecute(result);

                            if (flag == 1) {
                                Toast.makeText(getApplicationContext(), "Successfully registered!", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "This username already exists!", Toast.LENGTH_LONG).show();
                            }
                        }

                    }.execute();
                }
            }
        });
    }
}
