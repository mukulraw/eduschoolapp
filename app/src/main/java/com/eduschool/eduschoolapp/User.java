package com.eduschool.eduschoolapp;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


public class User extends Application {
    public boolean back=false;

    public String baseURL = "http://eduschoolapp.com/";
    public String studName;
    public String school_id,user_id,user_type,user_name,user_class,user_section,class_teacher,class_Name,section_Name;

    public String hw2 = "0";
    public String cw2 = "0";



    @Override
    public void onCreate() {
        super.onCreate();


        DisplayImageOptions opts = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(opts).build();
        ImageLoader.getInstance().init(config);


        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonty.ttf");

    }
}



