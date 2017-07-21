package com.eduschool.eduschoolapp.ExamAndResults;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

/**
 * Created by user on 5/18/2017.
 */

public class TeacherFrgmnt1 extends Fragment {
    TextView exam;
    Toolbar toolbar;

    public TeacherFrgmnt1() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher_frgmnt1, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        exam = (TextView) view.findViewById(R.id.exam);
        exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm =getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherFrgmnt2 frag1 =new TeacherFrgmnt2();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Exam");
        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}

