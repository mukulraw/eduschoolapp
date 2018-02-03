package com.eduschool.eduschoolapp.Profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.R;

import java.util.concurrent.TimeoutException;

/**
 * Created by User on 5/5/2017.
 */

public class TeacherFrgmntTwo extends Fragment {


    TextView dob , marital , spouse , address , city , state , country;


    public TeacherFrgmntTwo() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.teacher_profile_frgmnt2, container, false);

        dob = (TextView)v.findViewById(R.id.dob);
        marital = (TextView)v.findViewById(R.id.marital);
        spouse = (TextView)v.findViewById(R.id.spouse);
        address = (TextView)v.findViewById(R.id.address);
        city = (TextView)v.findViewById(R.id.city);
        state = (TextView)v.findViewById(R.id.state);
        country = (TextView)v.findViewById(R.id.country);

        try {
            Bundle b = getArguments();

            dob.setText(b.getString("dob"));
            marital.setText(b.getString("marital"));
            spouse.setText(b.getString("spouse"));
            address.setText(b.getString("address"));
            city.setText(b.getString("city"));
            state.setText(b.getString("state"));
            country.setText(b.getString("country"));

        }catch (Exception e)
        {
            e.printStackTrace();
        }



        return v;
    }
}

