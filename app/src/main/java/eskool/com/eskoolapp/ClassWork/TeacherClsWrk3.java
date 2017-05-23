package eskool.com.eskoolapp.ClassWork;

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
 * Created by user on 5/19/2017.
 */

public class TeacherClsWrk3  extends Fragment{
    Toolbar toolbar;

    public TeacherClsWrk3() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher_cls_wrk3, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

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
