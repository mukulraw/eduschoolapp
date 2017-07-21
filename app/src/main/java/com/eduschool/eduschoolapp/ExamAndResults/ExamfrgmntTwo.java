package com.eduschool.eduschoolapp.ExamAndResults;

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
 * Created by User on 5/9/2017.
 */

public class ExamfrgmntTwo extends Fragment {
    Toolbar toolbar;

    public ExamfrgmntTwo() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.parent_exm_result, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);

        return view;

    }


    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("FA-1");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}


