package eskool.com.eskoolapp.Attendance;


import android.content.Intent;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import eskool.com.eskoolapp.Home.TeacherHome;
import eskool.com.eskoolapp.R;

public class Attendance extends Fragment {
    Button mark_attendance,edit_attendance,view_attendance;
    Toolbar toolbar;


    public Attendance() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_attendance , container , false);

        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

        mark_attendance=(Button)view.findViewById(R.id.mark_attendance);
        view_attendance=(Button)view.findViewById(R.id.view_attendance);
        edit_attendance=(Button)view.findViewById(R.id.edit_attendance);

        mark_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),MarkAttendance.class);
                startActivity(intent);
            }
        });

        edit_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                AttendanceFrgmnt2 frag1 =new AttendanceFrgmnt2();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        view_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                AttendanceFrgmnt2 frag1 =new AttendanceFrgmnt2();
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
        toolbar.setTitle("Attendance");
    }
}