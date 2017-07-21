package com.eduschool.eduschoolapp.Transport;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

/**
 * Created by user on 5/18/2017.
 */

public class TransportParent extends Fragment {

    Toolbar toolbar;

    public TransportParent() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.transport, container, false);

        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);

        return v;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Transport");

        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}

