package com.eduschool.eduschoolapp.Profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.parenChildBean;
import com.eduschool.eduschoolapp.parentcontactBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by User on 5/4/2017.
 */

public class ParentFragmentOne extends Fragment {

    String stuId;
    TextView name , number , email , temp , permanent;
    ProgressBar progress;

    public ParentFragmentOne() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.parent_profile_frgmnt1, container, false);

        try {
            stuId = getArguments().getString("id");
        }catch (Exception e)
        {
            User u = (User)getContext().getApplicationContext();
            stuId = u.user_id;
            e.printStackTrace();
        }


        name = (TextView)v.findViewById(R.id.name);
        number = (TextView)v.findViewById(R.id.number);
        email = (TextView)v.findViewById(R.id.email);
        temp = (TextView)v.findViewById(R.id.temp);
        permanent = (TextView)v.findViewById(R.id.permanent);

        progress = (ProgressBar)v.findViewById(R.id.progress);



        return v;
    }


    @Override
    public void onResume() {
        super.onResume();


        progress.setVisibility(View.VISIBLE);


        User u = (User) getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);


        Call<parentcontactBean> call = cr.getParentContact(u.school_id , stuId);

        call.enqueue(new Callback<parentcontactBean>() {
            @Override
            public void onResponse(Call<parentcontactBean> call, Response<parentcontactBean> response) {

                
                name.setText(response.body().getContactName());
                number.setText(response.body().getContactPhone());
                email.setText(response.body().getContactEmail());
                temp.setText(response.body().getContactTempaddress());
                permanent.setText(response.body().getContactPermaaddress());


                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<parentcontactBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });



    }
}

