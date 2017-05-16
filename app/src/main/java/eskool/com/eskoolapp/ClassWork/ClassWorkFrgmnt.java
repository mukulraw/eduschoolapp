package eskool.com.eskoolapp.ClassWork;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import eskool.com.eskoolapp.Home.ParentHome;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.User;

/**
 * Created by User on 5/8/2017.
 */

public class ClassWorkFrgmnt extends Fragment {

    TextView subject;
    Toolbar toolbar;

    public ClassWorkFrgmnt() {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.parent_cw_home, container, false);
        subject = (TextView) view.findViewById(R.id.subject);

        subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ClassWorkFrgmntTwo frag1 = new ClassWorkFrgmntTwo();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.tool_bar);


        return view;

    }

    @Override
    public void onResume() {

        super.onResume();
        toolbar.setTitle("Class Work");

        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}