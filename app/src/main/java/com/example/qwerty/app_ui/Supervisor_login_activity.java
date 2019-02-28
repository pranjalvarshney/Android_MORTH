package com.example.qwerty.app_ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Supervisor_login_activity extends AppCompatActivity {

    EditText et_project_id,et_project_password;
    Button btn_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_login_activity);

        et_project_id = findViewById(R.id.et_project_id);
        et_project_password = findViewById(R.id.et_project_password);

        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


    }

    public void login(){

        String user = et_project_id.getText().toString();
        String password = et_project_password.getText().toString();


        if(user.trim().length() > 0 && password.trim().length() > 0){

            if(user.equals("root") && password.equals("root")){

                Intent j= new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(j);

            }else{

                Toast.makeText(getApplicationContext(),"Username/Password is incorrect",Toast.LENGTH_LONG).show();

                et_project_password.setText("");
                et_project_id.setText("");

            }
        }else{
            Toast.makeText(getApplicationContext(),"Please enter username and password",Toast.LENGTH_LONG).show();

        }

    }
}
