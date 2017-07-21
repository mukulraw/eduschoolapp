package com.eduschool.eduschoolapp.ClassWork;

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
 * Created by User on 5/8/2017.
 */

public class ClassWrkFrgmntThree extends Fragment{
    Toolbar toolbar;
    public ClassWrkFrgmntThree() {

}

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.class_wrk_details, container, false);
            toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);
            return view;

        }
    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Class Work Details");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}
