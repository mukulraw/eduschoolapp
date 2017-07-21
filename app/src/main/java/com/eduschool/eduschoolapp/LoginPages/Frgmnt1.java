package com.eduschool.eduschoolapp.LoginPages;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;

/**
 * Created by USER on 5/28/2017.
 */

public class Frgmnt1 extends Fragment {
    TextView tv_forgot_something;
    Button btn_login;

    public Frgmnt1() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login_frgmnt1, container, false);


        tv_forgot_something = (TextView) view.findViewById(R.id.tv_forgot_something);
        btn_login = (Button) view.findViewById(R.id.btn_login);

        tv_forgot_something.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ForgotPasswordFrgmnt frag1 = new ForgotPasswordFrgmnt();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ParentHome.class);
                startActivity(intent);
                getActivity().finish();

            }
        });


        return view;
    }

}
