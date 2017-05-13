package eskool.com.eskoolapp.Attendance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eskool.com.eskoolapp.Home.ParentHome;
import eskool.com.eskoolapp.R;

/**
 * Created by user on 5/6/2017.
 */

public class ParentAttendanceFrgmnt extends Fragment {
    Toolbar toolbar;
    public ParentAttendanceFrgmnt() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_view_attendance , container , false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.tool_bar);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Attendance");
    }
}