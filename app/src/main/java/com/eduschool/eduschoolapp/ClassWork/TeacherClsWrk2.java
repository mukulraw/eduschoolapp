package com.eduschool.eduschoolapp.ClassWork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

/**
 * Created by user on 5/19/2017.
 */

public class TeacherClsWrk2 extends Fragment {
    Toolbar toolbar;
    Button edit;

    public TeacherClsWrk2() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher_cls_wrk2, container, false);

        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);


        edit = (Button) view.findViewById(R.id.edit);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherClsWrk3 frag1 = new TeacherClsWrk3();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
            }
        });


        return view;

    }


    @Override
    public void onResume() {

        super.onResume();
        toolbar.setTitle("Class Work");

        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}
