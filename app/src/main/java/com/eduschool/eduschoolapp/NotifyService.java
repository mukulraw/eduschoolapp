package com.eduschool.eduschoolapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by User on 3/27/2017.
 */

public class NotifyService extends Service {

    Timer timer;
    ConnectionDetector cd;

    private void doSomethingRepeatedly() {
        timer = new Timer();
        timer.scheduleAtFixedRate( new TimerTask() {
            public void run() {

                try{




/*
                    if (cd.isConnectingToInternet())


                    {*/

                        Log.d("asdasdasdadadasds"  ,  "Notification called");

                        addNotification("szdsdcd", "sdddsdsds");


                        /*Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://nationproducts.in/")
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        Register cr = retrofit.create(Register.class);

                        Call<singleBean> call = cr.getSingle(pref.getString("id" , ""));


                        call.enqueue(new Callback<singleBean>() {
                            @Override
                            public void onResponse(Call<singleBean> call, Response<singleBean> response) {

                                if (response.body().getOffers()!=null) {

                                    Log.d("asdasdasdadadasds"  ,  "Notification called");


                                    List<Offer> list = response.body().getOffers();


                                    if (list.size() > 0) {

                                        if (list.size() == 1) {

                                            addNotification(list.get(0).getText(), list.get(0).getDescription());

                                        } else
                                        {

                                            addNotification2("You have " + String.valueOf(list.size()) + " new Notifications");

                                        }


                                    }

                                }

                            }

                            @Override
                            public void onFailure(Call<singleBean> call, Throwable t) {

                            }
                        });*/


                  /* }*/






                }
                catch (Exception e) {
                    // TODO: handle exception
                }

            }
        }, 0, 50000);



    }

        private void addNotification (String message, String text){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle(message)
                        .setSound(soundUri)
                        .setVibrate(new long[]{1000, 1000})
                        .setAutoCancel(true)
                        .setContentText(Html.fromHtml(text));



       Intent notificationIntent = new Intent(this, Splash.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
            builder.getNotification().flags |= Notification.FLAG_AUTO_CANCEL;

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }


        //Define sound URI
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


    private void addNotification2(String message) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle(message);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }




    SharedPreferences pref;


    @Override
    public void onCreate() {

        pref = getSharedPreferences("pree" , Context.MODE_PRIVATE);
        cd = new ConnectionDetector(getApplicationContext());


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("Asdadadadad" , "Service called");

        doSomethingRepeatedly();

        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
/*
        receive r = new receive();
        unregisterReceiver(r);*/


    }
}