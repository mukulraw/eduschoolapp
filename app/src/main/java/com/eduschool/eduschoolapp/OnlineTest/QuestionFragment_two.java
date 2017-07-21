package com.eduschool.eduschoolapp.OnlineTest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eduschool.eduschoolapp.R;


public class QuestionFragment_two extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_two, container, false);



        return v;
    }

    public static QuestionFragment newInstance(int text) {

        QuestionFragment f = new QuestionFragment();
        Bundle b = new Bundle();
        b.putInt("msg", text);

        f.setArguments(b);

        return f;
    }
}