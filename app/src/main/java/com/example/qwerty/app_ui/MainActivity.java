package com.example.qwerty.app_ui;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefs = new SharedPrefs(getApplicationContext());

        loadFragment(new Home_fragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()){

            case R.id.home:
                fragment = new Home_fragment();
                break;

            case R.id.request_eot:
                fragment = new Request_eot_fragment();
                break;

            case R.id.description:
                fragment = new Description_fragment();
                break;
        }
        return loadFragment(fragment);
    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragments condition
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view, fragment).commit();
            return true;
        }
        return false;
    }
}