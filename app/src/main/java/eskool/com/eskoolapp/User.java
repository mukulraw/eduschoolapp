package eskool.com.eskoolapp;

import android.app.Application;
import android.os.Bundle;


public class User extends Application {
    public boolean back=false;


    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonty.ttf");

    }
}



