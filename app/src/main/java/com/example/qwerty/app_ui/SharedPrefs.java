package com.example.qwerty.app_ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefs {

    SharedPreferences sharedPreferences;
    Context context;

    public static final String BOOLEAN_STATUS ="status";
    public static final String USER_ID ="userId" ;
    public static final String PASSWORD ="password" ;

    public SharedPrefs(Context context)
    {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("My_File",MODE_PRIVATE);
    }

    public void writeLoginStatus(Boolean status, String userId, String password)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(BOOLEAN_STATUS,status);
        editor.putString(USER_ID, userId);
        editor.putString(PASSWORD,password);
        editor.apply();
    }

    public boolean readLoginStatus()
    {
        boolean status = false;
        status = sharedPreferences.getBoolean(BOOLEAN_STATUS,false);

        return status;
    }
    public  String readUser_ID()
    {
        String userID = sharedPreferences.getString(USER_ID,"");
        return userID;
    }
    public  String readPassword()
    {
        String password = sharedPreferences.getString(PASSWORD,"");
        return password;
    }
}

