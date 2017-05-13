package eskool.com.eskoolapp.Attendance;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import eskool.com.eskoolapp.Home.ParentHome;
import eskool.com.eskoolapp.R;

public class Attendance extends Fragment {
    Button mark_attendance;
    Toolbar toolbar;


    public Attendance() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_attendance , container , false);

        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.tool_bar);

        mark_attendance=(Button)view.findViewById(R.id.mark_attendance);
        mark_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),MarkAttendance.class);
                startActivity(intent);
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