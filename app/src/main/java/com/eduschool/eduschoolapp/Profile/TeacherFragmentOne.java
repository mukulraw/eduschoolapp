package com.eduschool.eduschoolapp.Profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.R;

import org.w3c.dom.Text;

import java.util.concurrent.TimeoutException;

/**
 * Created by User on 5/5/2017.
 */

public class TeacherFragmentOne extends Fragment {


    TextView empId , joinDate , category , department , position , email , exp;


    public TeacherFragmentOne() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.teacher_profile_frgmnt1, container, false);

        empId = (TextView)v.findViewById(R.id.emp_id);
        joinDate = (TextView)v.findViewById(R.id.join);
        category = (TextView)v.findViewById(R.id.category);
        department = (TextView)v.findViewById(R.id.department);
        position = (TextView)v.findViewById(R.id.position);
        email = (TextView)v.findViewById(R.id.email);
        exp = (TextView)v.findViewById(R.id.exp);

        try {
            Bundle b = getArguments();

            empId.setText(b.getString("empId"));
            joinDate.setText(b.getString("joinDate"));
            category.setText(b.getString("category"));
            department.setText(b.getString("department"));
            position.setText(b.getString("position"));
            email.setText(b.getString("email"));
            exp.setText(b.getString("exp"));

        }catch (Exception e)
        {
            e.printStackTrace();
        }



        return v;
    }
}
