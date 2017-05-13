package eskool.com.eskoolapp.ClassWork;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import eskool.com.eskoolapp.HomeWork.HomeWorkFrgmntTwo;
import eskool.com.eskoolapp.R;

/**
 * Created by user on 5/8/2017.
 */

public class ClassWorkFrgmnt extends Fragment {

    TextView subject;

    public ClassWorkFrgmnt() {

    }

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
                ClassWorkFrgmntTwo frag1 =new ClassWorkFrgmntTwo();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;

    }

}