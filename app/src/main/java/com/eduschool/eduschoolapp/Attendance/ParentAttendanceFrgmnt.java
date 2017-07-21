package com.eduschool.eduschoolapp.Attendance;

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

import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

/**
 * Created by User on 5/6/2017.
 */

public class ParentAttendanceFrgmnt extends Fragment {
    Toolbar toolbar;
    CalendarView calendarView;

    public ParentAttendanceFrgmnt() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.parent_attendance1, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);

        calendarView = (CalendarView) view.findViewById(R.id.calendar);

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


        calendarView.setMaxDate(max);
        calendarView.setMinDate(min);


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Attendance");
        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }


}