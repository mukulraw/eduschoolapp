package com.eduschool.eduschoolapp.StudentSummary;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.eduschool.eduschoolapp.R;

/**
 * Created by user on 6/27/2017.
 */

public class StudentReportFrgmnt extends Fragment{
    Button get_report;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.student_report_frgmnt, container, false);
        get_report=(Button)v.findViewById(R.id.get_report);

        get_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(),StudentSummaryParent.class);
                startActivity(intent);
            }
        });




        return v;
    }
}