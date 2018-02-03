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
import com.eduschool.eduschoolapp.parentPersonalBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by User on 5/5/2017.
 */

public class ParentFragmentTwo extends Fragment {

    String stuId;
    ProgressBar progress;

    TextView classs , section , gender , rollNo , admNo , dob;

    public ParentFragmentTwo() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.parent_profile_frgmnt2, container, false);

        try {
            stuId = getArguments().getString("id");
        }catch (Exception e)
        {
            User u = (User)getContext().getApplicationContext();
            stuId = u.user_id;
            e.printStackTrace();
        }

        progress = (ProgressBar)v.findViewById(R.id.progress);
        classs = (TextView)v.findViewById(R.id.classs);
        section = (TextView)v.findViewById(R.id.section);
        gender = (TextView)v.findViewById(R.id.gender);
        rollNo = (TextView)v.findViewById(R.id.rollNo);
        admNo = (TextView)v.findViewById(R.id.admissonNo);
        dob = (TextView)v.findViewById(R.id.dob);




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


        Call<parenChildBean> call = cr.getParentChild(u.school_id , stuId);

        call.enqueue(new Callback<parenChildBean>() {
            @Override
            public void onResponse(Call<parenChildBean> call, Response<parenChildBean> response) {

                classs.setText(response.body().getClass_());
                section.setText(response.body().getSection());
                gender.setText(response.body().getGender());
                rollNo.setText(response.body().getRollNo());
                admNo.setText(response.body().getAdmissionNo());
                dob.setText(response.body().getBirthDate());

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<parenChildBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });




    }
}
