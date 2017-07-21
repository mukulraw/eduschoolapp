package com.eduschool.eduschoolapp.RaiseRequest;


import android.content.Intent;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

/**
 * Created by User on 5/10/2017.
 */

public class FrgmntOne extends Fragment {

    CardView cardView;
    Toolbar toolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.raise_request_frgmnt1, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);
        cardView = (CardView) v.findViewById(R.id.card);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(),SeeBirthdayCard.class);
                startActivity(intent);

            }
        });


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Request");
        User u = (User) getContext().getApplicationContext();

        u.back = true;


    }
}
