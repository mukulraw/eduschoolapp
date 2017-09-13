package com.eduschool.eduschoolapp.Attendance;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.ParentAttendancePOJO.AttendanceList;
import com.eduschool.eduschoolapp.ParentAttendancePOJO.paraneAttendanceBean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.imanoweb.calendarview.CustomCalendarView;
import com.imanoweb.calendarview.DayDecorator;
import com.imanoweb.calendarview.DayView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by User on 5/6/2017.
 */

public class ParentAttendanceFrgmnt extends Fragment {
    Toolbar toolbar;
    CompactCalendarView calendarView;
    Calendar currentCalendar;
    List<AttendanceList> list;

    public ParentAttendanceFrgmnt() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.parent_attendance1, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);

        list = new ArrayList<>();

        calendarView = (CompactCalendarView) view.findViewById(R.id.calendar);

        int day1 = 8;
        int month1 = 5;
        int year1 = 2018;
        int day = 1;
        int month = 5;
        int year = 2017;

        java.util.Calendar c1 = java.util.Calendar.getInstance();
        java.util.Calendar c = java.util.Calendar.getInstance();

        c1.set(year1, month1, day1);
        c.set(year, month, day);
        Long max = c1.getTime().getTime();
        Long min = c.getTime().getTime();


        //calendarView.setMaxDate(max);
        //calendarView.setMinDate(min);


        currentCalendar = Calendar.getInstance(Locale.getDefault());


        calendarView.setFirstDayOfWeek(Calendar.MONDAY);

//Show/hide overflow days of a month
        //calendarView.setShowOverflowDate(false);

//call refreshCalendar to update calendar the view
        //calendarView.refreshCalendar(currentCalendar);

        DayDecorator d = new DayDecorator() {
            @Override
            public void decorate(DayView dayView) {

                dayView.decorate();

            }
        };




        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Attendance");
        User u = (User) getContext().getApplicationContext();
        u.back = true;


        updateCalendar();




    }


    public void updateCalendar()
    {

        User u = (User) getContext().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);

        Call<paraneAttendanceBean> call = cr.getAttendance(u.school_id , u.user_class , u.user_section , "aug" , "2017" , u.user_id);

        call.enqueue(new Callback<paraneAttendanceBean>() {
            @Override
            public void onResponse(Call<paraneAttendanceBean> call, Response<paraneAttendanceBean> response) {


                for (int i = 0 ; i < response.body().getAttendanceList().size() ; i++)
                {
                    Event ev1 = new Event(Color.GREEN, 1433701251000L, "Some extra data that I want to store.");
                    calendarView.addEvent(ev1);
                }


            }

            @Override
            public void onFailure(Call<paraneAttendanceBean> call, Throwable t) {

            }
        });
    }


}