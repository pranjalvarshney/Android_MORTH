package com.example.qwerty.app_ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash_screen extends AppCompatActivity {


    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent splash_intent = new Intent(Splash_screen.this,Contractor_login_activity.class);
                startActivity(splash_intent);
                finish();
            }
        },3000);

    }
}
