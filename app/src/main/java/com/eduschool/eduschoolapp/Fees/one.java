package com.eduschool.eduschoolapp.Fees;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.eduschool.eduschoolapp.R;

/**
 * Created by user on 6/23/2017.
 */

public class one extends Fragment {
    LinearLayout layout;


    public one() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.one1, container, false);


        layout = (LinearLayout) view.findViewById(R.id.layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        return view;
    }
}