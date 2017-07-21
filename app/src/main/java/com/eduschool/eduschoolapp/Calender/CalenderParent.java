package com.eduschool.eduschoolapp.Calender;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

/**
 * Created by user on 6/27/2017.
 */

public class CalenderParent  extends Fragment{


    Toolbar toolbar;


    public CalenderParent() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.calendar_teacher, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);



        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Calender");
        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}

