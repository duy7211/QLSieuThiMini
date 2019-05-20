package com.example.qlsieuthimini.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.qlsieuthimini.login;

import java.util.HashMap;

public class Session {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    private static final String Is_login = "islogin";
    public static final  String Key_ID = "ID";
    public static final String Key_NAME = "USERNAME";

    public Session(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String id, String name){
        editor.putBoolean(Is_login,true);
        editor.putString(Key_ID,id);
        editor.putString(Key_NAME,name);
        editor.commit();
    }

    public HashMap<String, String> getUser(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(Key_ID,sharedPreferences.getString(Key_ID,null));
        user.put(Key_NAME,sharedPreferences.getString(Key_NAME,null));
        return user;
    }

    public void checkLogin(){
        if(!isLogin()){
            Intent i = new Intent(context, login.class);
            context.startActivity(i);
        }
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, login.class);
        context.startActivity(i);
    }

    public boolean isLogin(){
        return sharedPreferences.getBoolean(Is_login,false);
    }
}
