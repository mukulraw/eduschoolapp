package com.eduschool.eduschoolapp.Notifications;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

/**
 * Created by user on 6/30/2017.
 */

public class TeacherNotificationFrgmnt extends Fragment {
    Toolbar toolbar;


    public TeacherNotificationFrgmnt() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.teacher_notificion_frgmnt, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);



        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Notifications");

        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}

