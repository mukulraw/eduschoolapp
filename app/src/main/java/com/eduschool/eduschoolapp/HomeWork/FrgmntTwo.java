package com.eduschool.eduschoolapp.HomeWork;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

/**
 * Created by user on 5/20/2017.
 */

public class FrgmntTwo extends Fragment {
    CardView card;
    Toolbar toolbar;



    public FrgmntTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.teacher_hw_frgmnt2, container, false);

        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.fab);
        card=(CardView)view.findViewById(R.id.card);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherHwFrgmntTwo frag1 = new TeacherHwFrgmntTwo();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                android.app.FragmentManager fm = getActivity().getFragmentManager();
                com.eduschool.eduschoolapp.ClassWork.FilterDailog ratingBarFragment = new com.eduschool.eduschoolapp.ClassWork.FilterDailog();
                ratingBarFragment.show(fm, "dialog");

            }
        });









        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Home Work");

        User u = (User) getContext().getApplicationContext();

        u.back = true;


    }
}