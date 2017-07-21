package com.eduschool.eduschoolapp.ClassWork;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eduschool.eduschoolapp.R;

/**
 * Created by User on 4/12/2017.
 */

public class TwoFragment extends Fragment {
    CardView card;


    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.fab);
        card = (CardView) view.findViewById(R.id.card);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherClsWrk2 frag1 = new TeacherClsWrk2();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.FragmentManager fm = getActivity().getFragmentManager();
                com.eduschool.eduschoolapp.ClassWork.FilterDailog ratingBarFragment = new com.eduschool.eduschoolapp.ClassWork.FilterDailog();
                ratingBarFragment.show(fm, "dialog");

            }
        });


        return view;
    }


}