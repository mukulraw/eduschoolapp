package eskool.com.eskoolapp.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import eskool.com.eskoolapp.SyllabusManagement.TeacherAcademic;
import eskool.com.eskoolapp.Attendance.Attendance;
import eskool.com.eskoolapp.ClassWork.Teacherclswrk;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.User;

/**
 * Created by User on 5/13/2017.
 */

public class TeacherHomeFrgmnt extends Fragment {
    Toolbar toolbar;
    LinearLayout attendance;
    TextView academic,classwork;

    public TeacherHomeFrgmnt() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher_home_frgmnt, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        attendance=(LinearLayout)view.findViewById(R.id.attendance);
        academic=(TextView)view.findViewById(R.id.academic);
        classwork=(TextView)view.findViewById(R.id.classwork);

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Attendance frag1 = new Attendance();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();

            }
        });


        academic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherAcademic frag1 = new TeacherAcademic();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();


            }
        });


        classwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Teacherclswrk frag1 = new Teacherclswrk();
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
        toolbar.setTitle("Home");
        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}