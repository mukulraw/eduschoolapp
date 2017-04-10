package eskool.com.eskoolapp;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import eskool.com.eskoolapp.OnlineTest.OnlineTestActivity;
import eskool.com.eskoolapp.WelcomeSlider.WelcomeActivity;

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

                    Intent intent = new Intent(Splash.this, View_attendance.class);
                    startActivity(intent);
                    finish();

                }
            }, 1500);
        } catch (ActivityNotFoundException e) {

        }
    }
}
