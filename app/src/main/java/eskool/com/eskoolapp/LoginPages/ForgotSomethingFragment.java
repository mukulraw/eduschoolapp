package eskool.com.eskoolapp.LoginPages;


import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import eskool.com.eskoolapp.R;

/**
 * Created by User on 5/6/2017.
 */

public class ForgotSomethingFragment extends Fragment {
    Button btn_enrollmnt, btn_password, btn_school;

    public ForgotSomethingFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.forgot_somethinng, container, false);
        btn_enrollmnt = (Button) view.findViewById(R.id.btn_enrollment);
        btn_school = (Button) view.findViewById(R.id.btn_school);
        btn_password = (Button) view.findViewById(R.id.btn_password);


        btn_enrollmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ForgotEnrllmntFrgmnt frag1 = new ForgotEnrllmntFrgmnt();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        btn_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ForgotSchoolFrgmnt frag1 = new ForgotSchoolFrgmnt();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        btn_password.setOnClickListener(new View.OnClickListener() {
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

        return view;

    }
}
