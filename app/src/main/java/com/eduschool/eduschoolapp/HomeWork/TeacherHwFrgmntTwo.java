package com.eduschool.eduschoolapp.HomeWork;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

/**
 * Created by user on 5/25/2017.
 */

public class TeacherHwFrgmntTwo extends Fragment {
    Toolbar toolbar;
    Button edit;
    LinearLayout complited, incomplited;

    public TeacherHwFrgmntTwo() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher_hw_frgmnt_two, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);


        complited = (LinearLayout) view.findViewById(R.id.complited);
        incomplited = (LinearLayout) view.findViewById(R.id.incomplited);
        edit = (Button) view.findViewById(R.id.edit);


        complited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());

                builderSingle.setTitle("Homework Completed:-");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.custome_list_popup);
                arrayAdapter.add("Hardik");
                arrayAdapter.add("Archit");
                arrayAdapter.add("Jignesh");
                arrayAdapter.add("Umang");
                arrayAdapter.add("Gatti");
                arrayAdapter.add("Hardik");
                arrayAdapter.add("Archit");
                arrayAdapter.add("Jignesh");
                arrayAdapter.add("Umang");
                arrayAdapter.add("Gatti");

                builderSingle.setNegativeButton("Go Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builderSingle.show();

                    }
                });

                builderSingle.show();


            }


        });


        incomplited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());

                builderSingle.setTitle("Homework Incomplete :-");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.custome_list_popup);
                arrayAdapter.add("Hardik");
                arrayAdapter.add("Archit");
                arrayAdapter.add("Jignesh");
                arrayAdapter.add("Umang");
                arrayAdapter.add("Gatti");


                builderSingle.setNegativeButton("Go Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builderSingle.show();

                    }
                });

                builderSingle.show();


            }


        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherHwFrgmntThree frag1 = new TeacherHwFrgmntThree();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Home Work");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}

