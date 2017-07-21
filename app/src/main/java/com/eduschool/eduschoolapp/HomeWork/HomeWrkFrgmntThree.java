package com.eduschool.eduschoolapp.HomeWork;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

/**
 * Created by User on 5/8/2017.
 */

public class HomeWrkFrgmntThree extends Fragment {
    Toolbar toolbar;
    Button btn_complted;

    public HomeWrkFrgmntThree() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_wrk_details, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);
        btn_complted=(Button)view.findViewById(R.id.btn_complted);
        btn_complted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure want to Submit?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog

                        dialog.dismiss();

                      //  btn_complted.setBackgroundColor( ContextCompat.getColor(getContext(),R.color.btn));
                        btn_complted.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                        btn_complted.setTextColor(ContextCompat.getColor(getContext(),R.color.txtColor));
                        btn_complted.setEnabled(false);
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();



            }
        });

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Home Work Details");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}
