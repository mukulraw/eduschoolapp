package com.eduschool.eduschoolapp.ExamAndResults;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

/**
 * Created by user on 5/18/2017.
 */

public class TeacherFrgmnt2 extends Fragment {
    TextView own_class,diff_class;
    Toolbar toolbar;

    public TeacherFrgmnt2() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher_frgmnt2, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        own_class = (TextView) view.findViewById(R.id.own_class);
        diff_class = (TextView) view.findViewById(R.id.diff_class);


        own_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm =getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherOwnCls frag1 =new TeacherOwnCls();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        diff_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setCancelable(true);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.attendance_diff_class_dialog);
                Button submit = (Button) dialog.findViewById(R.id.submit);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        FragmentManager fm =getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        TeacherOwnCls frag1 =new TeacherOwnCls();
                        ft.replace(R.id.replace, frag1);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                });

                dialog.show();

            }
        });

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Select Class");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}
