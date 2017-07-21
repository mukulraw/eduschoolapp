package com.eduschool.eduschoolapp.StudentSummary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.R;

/**
 * Created by user on 6/27/2017.
 */

public class ParentFeeFrgmnt extends Fragment {
    TextView monthly, quaterly, half_yearly, yearly;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fees_frgmnt, container, false);


        monthly = (TextView) view.findViewById(R.id.monthly);
        quaterly = (TextView) view.findViewById(R.id.quaterly);
        half_yearly = (TextView) view.findViewById(R.id.half_yearly);
        yearly = (TextView) view.findViewById(R.id.yearly);

        monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ParentFeeFrgmnt2 fees2 = new ParentFeeFrgmnt2();
                fragmentTransaction.replace(R.id.replace, fees2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        quaterly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ParentFeeFrgmnt2 fees2 = new ParentFeeFrgmnt2();
                fragmentTransaction.replace(R.id.replace, fees2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        half_yearly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ParentFeeFrgmnt2 fees2 = new ParentFeeFrgmnt2();
                fragmentTransaction.replace(R.id.replace, fees2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        yearly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ParentFeeFrgmnt2 fees2 = new ParentFeeFrgmnt2();
                fragmentTransaction.replace(R.id.replace, fees2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        return view;
    }


}


