package com.eduschool.eduschoolapp.StudentSummary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.parentFeesPOJO.FeesDescription;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by user on 6/28/2017.
 */

public class One extends Fragment {
    LinearLayout layout;

    String status , total;
    List<FeesDescription> list = new ArrayList<>();

    TextView admission , tuition , exam , lab , tot , paid;



    public void setData(List<FeesDescription> list , String status , String total)
    {
        this.list = list;
        this.status = status;
        this.total = total;
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.one, container, false);

        admission = (TextView)view.findViewById(R.id.admision);
        tuition = (TextView)view.findViewById(R.id.tuition);
        exam = (TextView)view.findViewById(R.id.exam);
        lab = (TextView)view.findViewById(R.id.lab);
        tot = (TextView)view.findViewById(R.id.total);
        paid = (TextView)view.findViewById(R.id.paid);

        layout = (LinearLayout) view.findViewById(R.id.layout);

        User u = (User)getContext().getApplicationContext();

        if (Objects.equals(u.user_type, "Teacher"))
        {
            paid.setVisibility(View.GONE);
        }
        else if (Objects.equals(u.user_type, "Parent"))
        {
            paid.setVisibility(View.VISIBLE);
        }

        if (Objects.equals(status, "Unpaid"))
        {
            paid.setText("PAY");
        }
        else
        {
            paid.setText("PAID");
        }



        tot.setText("INR " + total);

        try {
            admission.setText("INR " + list.get(0).getAmount());
            exam.setText("INR " + list.get(1).getAmount());
            tuition.setText("INR " + list.get(2).getAmount());
            lab.setText("INR " + list.get(3).getAmount());

        }catch (Exception e)
        {
            e.printStackTrace();
        }




        return view;
    }
}