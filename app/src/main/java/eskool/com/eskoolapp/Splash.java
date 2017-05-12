package eskool.com.eskoolapp;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import eskool.com.eskoolapp.Home.ParentHome;
import eskool.com.eskoolapp.LoginPages.LoginPage;
import eskool.com.eskoolapp.Profile.ParentProfile;

public class Splash extends AppCompatActivity {
    Timer t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        t = new Timer();
        try {
            t.schedule(new TimerTask() {
                @Override
                public void run() {

                    Intent intent = new Intent(Splash.this, LoginPage.class);
                    startActivity(intent);
                    finish();

                }
            }, 1500);
        } catch (ActivityNotFoundException e) {

        }
    }
}
