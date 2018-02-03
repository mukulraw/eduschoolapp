package com.eduschool.eduschoolapp.StudentSummary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.R;

/**
 * Created by user on 6/27/2017.
 */

public class ParentResultFrgmnt  extends Fragment {
    TextView exam;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.parent_exm_result, container, false);
        //exam = (TextView) view.findViewById(R.id.exam);
        /*exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm =getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ParentResultFrgmnt2 frag1 =new ParentResultFrgmnt2();
                ft.replace(R.id.replace1, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });*/
        return view;

    }

}


