package com.eduschool.eduschoolapp.Fees;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

/**
 * Created by user on 6/23/2017.
 */

public class FeeFrgmnt3 extends Fragment {

    public FeeFrgmnt3() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fee_frgmnt3, container, false);



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }

}
