package com.eduschool.eduschoolapp.Transport;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.transportBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 5/18/2017.
 */

public class TransportParent extends Fragment {

    Toolbar toolbar;

    TextView type , driverName, driverPhone , condName , condPhone , secName , secPhone , vehNum , regYear , EngType , pickPoint , pickTime , dropPoint , dropTime;

    ProgressBar progress;

    public TransportParent() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.transport, container, false);

        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);

        type = (TextView)v.findViewById(R.id.type);
        progress = (ProgressBar)v.findViewById(R.id.progress);
        driverName = (TextView)v.findViewById(R.id.driver_name);
        driverPhone = (TextView)v.findViewById(R.id.driver_phone);
        condName = (TextView)v.findViewById(R.id.conductor_name);
        condPhone = (TextView)v.findViewById(R.id.conductor_phone);
        secName = (TextView)v.findViewById(R.id.security_name);
        secPhone = (TextView)v.findViewById(R.id.security_phone);
        vehNum = (TextView)v.findViewById(R.id.vehicle_number);
        regYear = (TextView)v.findViewById(R.id.reg_year);
        EngType = (TextView)v.findViewById(R.id.engine_type);
        pickPoint = (TextView)v.findViewById(R.id.pick_point);
        pickTime = (TextView)v.findViewById(R.id.pick_time);
        dropPoint = (TextView)v.findViewById(R.id.drop_point);
        dropTime = (TextView)v.findViewById(R.id.drop_time);




        progress.setVisibility(View.VISIBLE);


        User u = (User) getContext().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        //  progress.setVisibility(View.VISIBLE);


        Call<transportBean> call = cr.getTransport(u.school_id , u.user_id , u.user_class , u.user_section);

        call.enqueue(new Callback<transportBean>() {
            @Override
            public void onResponse(Call<transportBean> call, Response<transportBean> response) {


                type.setText(response.body().getTransportType());
                driverName.setText(response.body().getDriverName());
                driverPhone.setText(response.body().getDriverNumber());
                condName.setText(response.body().getConductorName());
                condPhone.setText(response.body().getConductorPhone());
                secName.setText(response.body().getSecurityName());
                secPhone.setText(response.body().getSecurityPhone());
                regYear.setText("Registered Year" + response.body().getRegisteredYear());
                vehNum.setText("Vehicle No. "+response.body().getVehicleNumber());
                EngType.setText("Engine Type "+ response.body().getEngineType());
                pickPoint.setText(response.body().getPickupPoint());
                pickTime.setText(response.body().getPickupTime());
                dropTime.setText(response.body().getDropTime());
                dropPoint.setText(response.body().getDropPoint());


                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<transportBean> call, Throwable throwable) {
                progress.setVisibility(View.GONE);
            }
        });



        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Transport");

        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}

