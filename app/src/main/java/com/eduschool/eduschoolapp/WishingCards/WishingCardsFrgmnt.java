package com.eduschool.eduschoolapp.WishingCards;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

import java.util.ArrayList;

/**
 * Created by user on 6/30/2017.
 */

public class WishingCardsFrgmnt extends Fragment {
    Button send;
    Toolbar toolbar;
    String value;
    String[] superStarNames = {"Mukul", "Rohit", "Sandeep"};

    public WishingCardsFrgmnt() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.wishing_cards_frgmnt, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);


        ListView listView = (ListView)view.findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter(getContext(), superStarNames);
        listView.setAdapter(customAdapter);



        send=(Button)view.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setCancelable(false);
                dialog.setMessage("Are you sure you want to send card ?" );
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Action for "Delete".
                        dialog.dismiss();

                    }
                })
                        .setNegativeButton("No ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for "Cancel".
                            }
                        });

                final AlertDialog alert = dialog.create();
                alert.show();

            }
        });

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Send Birthday Cards");
        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}

