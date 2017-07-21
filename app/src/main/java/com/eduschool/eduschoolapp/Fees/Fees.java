package com.eduschool.eduschoolapp.Fees;

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

import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

/**
 * Created by user on 5/18/2017.
 */

public class Fees extends Fragment {
    Toolbar toolbar;
    TextView monthly, quaterly, half_yearly, yearly;

    public Fees() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fees_frgmnt, container, false);

        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);

        monthly = (TextView) view.findViewById(R.id.monthly);
        quaterly = (TextView) view.findViewById(R.id.quaterly);
        half_yearly = (TextView) view.findViewById(R.id.half_yearly);
        yearly = (TextView) view.findViewById(R.id.yearly);

        monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FeesFrgmnt2 fees2 = new FeesFrgmnt2();
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
                FeesFrgmnt2 fees2 = new   FeesFrgmnt2();
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
                FeesFrgmnt2 fees2 = new   FeesFrgmnt2();
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
                FeesFrgmnt2 fees2 = new   FeesFrgmnt2();
                fragmentTransaction.replace(R.id.replace, fees2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        return view;
    }

    @Override
    public void onResume() {

        super.onResume();
        toolbar.setTitle("Fees");

        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }

}


