package eskool.com.eskoolapp.Attendance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eskool.com.eskoolapp.R;

/**
 * Created by user on 5/6/2017.
 */

public class ParentAttendanceFrgmnt extends Fragment {
    public ParentAttendanceFrgmnt() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_view_attendance , container , false);

        return view;

    }
}