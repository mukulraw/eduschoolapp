package eskool.com.eskoolapp.HomeWork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eskool.com.eskoolapp.Home.TeacherHome;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.User;

/**
 * Created by user on 5/25/2017.
 */

public class TeacherHwFrgmntThree extends Fragment {
    Toolbar toolbar;

    public TeacherHwFrgmntThree() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher_hw_frgmnt_three, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Home Work");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}