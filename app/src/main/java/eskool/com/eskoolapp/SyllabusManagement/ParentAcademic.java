package eskool.com.eskoolapp.SyllabusManagement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eskool.com.eskoolapp.Home.ParentHome;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.User;

/**
 * Created by User on 5/8/2017.
 */

public class ParentAcademic extends Fragment {
    Toolbar toolbar;

    public ParentAcademic() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.parent_academic, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.tool_bar);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("My Progress");
        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}