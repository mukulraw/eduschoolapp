package com.eduschool.eduschoolapp.StudentSummary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.eduschool.eduschoolapp.R;

/**
 * Created by user on 6/28/2017.
 */

public class One extends Fragment {
    LinearLayout layout;


    public One() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.one, container, false);


        layout = (LinearLayout) view.findViewById(R.id.layout);


        return view;
    }
}