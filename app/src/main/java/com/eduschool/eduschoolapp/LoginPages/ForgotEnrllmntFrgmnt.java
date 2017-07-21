package com.eduschool.eduschoolapp.LoginPages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eduschool.eduschoolapp.R;

/**
 * Created by User on 5/6/2017.
 */

public class ForgotEnrllmntFrgmnt extends Fragment {

    public ForgotEnrllmntFrgmnt() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.forgot_enrollmnt_no , container , false);

        return view;

    }
}
