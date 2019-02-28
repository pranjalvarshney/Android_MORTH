package com.example.qwerty.app_ui;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity2 extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        loadFragment(new Home2_fragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation2);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()){

            case R.id.home:
                fragment = new Home2_fragment();
                break;

            case R.id.new_requests:
                fragment = new New_request_fragment();
                break;

            case R.id.my_profile:
                fragment = new My_profile_fragment();
                break;
        }
        return loadFragment(fragment);
    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragments condition
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view2, fragment).commit();
            return true;
        }
        return false;
    }
}