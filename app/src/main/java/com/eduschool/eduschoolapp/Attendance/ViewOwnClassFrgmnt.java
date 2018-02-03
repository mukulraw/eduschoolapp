package com.eduschool.eduschoolapp.Attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.AttendncDatePOJO.AttendanceDatum;
import com.eduschool.eduschoolapp.AttendncDatePOJO.AttendanceList;
import com.eduschool.eduschoolapp.AttendncDatePOJO.AttendanceListBean;
import com.eduschool.eduschoolapp.ClassWrkParentPOJO.ClasssubjectListBean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentList;
import com.eduschool.eduschoolapp.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by User on 5/15/2017.
 */

public class ViewOwnClassFrgmnt extends Fragment {
    Toolbar toolbar;
    ProgressBar progress;
    private RecyclerView recyclerView;
    GridLayoutManager manager;
    AdapterViewAttndnc adapter;
    public List<String> studentlist,studentId;
    public List<AttendanceDatum> list;
    String Sdate,month,year,day;
    TextView Tday,classSection,Tmonth;
    Button submit;
    List<String>s;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_edit__attendance, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

        recyclerView = (RecyclerView)view. findViewById(R.id.recycler);
        progress = (ProgressBar)view. findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);
        submit=(Button)view.findViewById(R.id.submit);
        Tday=(TextView)view.findViewById(R.id.day);
        classSection=(TextView)view.findViewById(R.id.classSection);
        Tmonth=(TextView)view.findViewById(R.id.month);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);
        Sdate = getArguments().getString("message");




        String d1[] = Sdate.split("-");




        final DateFormat month =  new SimpleDateFormat("MMM");
        final Date month1 = new Date();

        final DateFormat year =  new SimpleDateFormat("yyyy");
        final Date year1 = new Date();


        final DateFormat day =  new SimpleDateFormat("dd");
        final Date day1 = new Date();

        Log.d("sds",month.format(month1));
        Log.d("sds",year.format(year1));
        Log.d("sds",day.format(day1));

        Tday.setText(d1[0]);
        Tmonth.setText(d1[1]+" "+year.format(year1));



        list = new ArrayList<>();
        s=new ArrayList<>();

        adapter = new AdapterViewAttndnc(getActivity(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        studentlist = new ArrayList<>();
        list = new ArrayList<>();
        studentId = new ArrayList<>();




        User b = (User) getActivity().getApplicationContext();
        classSection.setText(b.class_Name+" "+b.section_Name);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<AttendanceListBean> call = cr.attnce_list(b.school_id,Sdate, b.user_class, b.user_section);


        call.enqueue(new Callback<AttendanceListBean>() {
            @Override
            public void onResponse(Call<AttendanceListBean> call, Response<AttendanceListBean> response) {

                try {

                    if (response.body().getAttendanceList().size() > 0)
                    {
                        adapter.setGridData(response.body().getAttendanceList().get(0).getAttendanceData());
                        adapter.notifyDataSetChanged();
                    }
                    else
                    {
                        Toast.makeText(getContext() , "No Data Found" , Toast.LENGTH_SHORT).show();
                    }



                }catch (Exception e)
                {
                    e.printStackTrace();
                }


                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<AttendanceListBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });





        return view;

    }


    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Attendance");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}

