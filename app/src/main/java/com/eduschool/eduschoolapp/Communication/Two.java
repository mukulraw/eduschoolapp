package com.eduschool.eduschoolapp.Communication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;

/**
 * Created by user on 5/25/2017.
 */

public class Two extends Fragment {
    Toolbar toolbar;

    public Two() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.communication_two, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);



        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Parent Request");


    }
}
