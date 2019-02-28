package com.example.qwerty.app_ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Contractor_login_activity extends AppCompatActivity {

    RequestQueue queue;
    private EditText et_project_id, et_project_password;

    private SharedPrefs sharedPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_login_activity);

        Button btn_supervisor_login = findViewById(R.id.btn_supervisor_login);
        Button  btn_login = findViewById(R.id.btn_login);

        queue = Volley.newRequestQueue(getApplicationContext());

        et_project_password = findViewById(R.id.et_project_password);
        et_project_id = findViewById(R.id.et_project_id);

        sharedPrefs = new SharedPrefs(getApplicationContext());


        if(sharedPrefs.readLoginStatus()){
            Intent i = new Intent(Contractor_login_activity.this,MainActivity.class);
            startActivity(i);
            finish();
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               login();

            }
        });

        btn_supervisor_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_intent_2 = new Intent(Contractor_login_activity.this,Supervisor_login_activity.class);
                startActivity(login_intent_2);
                finish();
            }
        });



    }

    public void login(){

        String username = et_project_id.getText().toString();
        String password = et_project_password.getText().toString();

        String url ="http://thisisnow.tk/ters/contractorlogin.php?projectid="+username+"&pass="+password;

        if(username.trim().length() > 0 && password.trim().length() > 0){

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {

                        String status = response.getString("status");
                        if(status.equals("failure")){
                            Toast.makeText(getApplicationContext(),response.getString("reason"),Toast.LENGTH_SHORT).show();
                            et_project_password.setText("");
                            et_project_id.setText("");
                        }
                        else if(status.equals("success")){

                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            sharedPrefs.writeLoginStatus(true,username,password);
                            startActivity(i);
                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT);

                }
            });

            queue.add(request);

        }else{
            Toast.makeText(getApplicationContext(),"Please enter username and password",Toast.LENGTH_LONG).show();

        }

    }


}