package com.eduschool.eduschoolapp;

import android.app.Application;


public class User extends Application {
    public boolean back=false;

    public String baseURL = "http://technobrix.in/";
    public String school_id,user_id,user_type;

    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonty.ttf");

    }
}



