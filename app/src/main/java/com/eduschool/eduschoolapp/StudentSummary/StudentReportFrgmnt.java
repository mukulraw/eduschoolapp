package com.eduschool.eduschoolapp.StudentSummary;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentListbean;
import com.eduschool.eduschoolapp.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 6/27/2017.
 */

public class StudentReportFrgmnt extends Fragment{
    Button get_report;
    Toolbar toolbar;
    Spinner cls , sec , stu;
    ProgressBar progress;

    List<String> classNames;
    List<String> classIds;

    List<String> sectionNames;
    List<String> sectionIds;

    List<String> stuNames;
    List<String> stuIds;

    String classId = "" , secId = "" , stuId = "" , cName , sName , stName;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.student_report_frgmnt, container, false);

        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        cls = (Spinner)v.findViewById(R.id.cls);
        sec = (Spinner)v.findViewById(R.id.sec);
        stu = (Spinner)v.findViewById(R.id.stu);

        progress = (ProgressBar)v.findViewById(R.id.progress);


        classNames = new ArrayList<>();
        classIds = new ArrayList<>();

        sectionNames = new ArrayList<>();
        sectionIds = new ArrayList<>();

        stuNames = new ArrayList<>();
        stuIds = new ArrayList<>();


        get_report=(Button)v.findViewById(R.id.get_report);

        get_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(),StudentSummaryParent.class);

                Bundle b = new Bundle();
                b.putString("class" , classId);
                b.putString("section" , secId);
                b.putString("student" , stuId);
                b.putString("cName" , cName);
                b.putString("sName" , sName);
                b.putString("stName" , stName);

                intent.putExtras(b);

                startActivity(intent);
            }
        });


        final User b = (User) getActivity().getApplicationContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);

        Call<ClassListbean> call = cr.classList(b.school_id);
        progress.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<ClassListbean>() {
            @Override
            public void onResponse(Call<ClassListbean> call, Response<ClassListbean> response) {

                classNames.clear();
                classIds.clear();

                classNames.add("Select Class");

                for (int i = 0; i < response.body().getClassList().size(); i++) {

                    classNames.add(response.body().getClassList().get(i).getClassName());
                    classIds.add(response.body().getClassList().get(i).getClassId());


                }

                try {
                    ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_list_item_1, classNames);
                    adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    cls.setAdapter(adp1);

                }catch (Exception e)
                {
                    e.printStackTrace();
                }



                progress.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<ClassListbean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


        cls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {




                if (position > 0)
                {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllAPIs cr = retrofit.create(AllAPIs.class);

                    classId = classIds.get(position - 1);
                    cName = classNames.get(position);

                    Call<SectionListbean> call2 = cr.sectionList(b.school_id, classIds.get(position - 1));

                    progress.setVisibility(View.VISIBLE);


                    call2.enqueue(new Callback<SectionListbean>() {

                        @Override
                        public void onResponse(Call<SectionListbean> call, Response<SectionListbean> response) {

                            sectionNames.clear();
                            sectionIds.clear();

                            sectionNames.add("Select Section");


                            for (int i = 0; i < response.body().getSectionList().size(); i++) {

                                sectionNames.add(response.body().getSectionList().get(i).getSectionName());

                                sectionIds.add(response.body().getSectionList().get(i).getSectionId());

                            }


                            ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_list_item_1, sectionNames);

                            adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            sec.setAdapter(adp);


                            progress.setVisibility(View.GONE);


                        }

                        @Override
                        public void onFailure(Call<SectionListbean> call, Throwable throwable) {
                            progress.setVisibility(View.GONE);

                        }
                    });

                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        sec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                if (position > 0)
                {

                    secId = sectionIds.get(position - 1);
                    sName = sectionNames.get(position);

                    final User b = (User)getContext().getApplicationContext();


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    final AllAPIs cr = retrofit.create(AllAPIs.class);
                    progress.setVisibility(View.VISIBLE);
                    Call<StudentListbean> call3 = cr.student_list(b.school_id, classId , secId);



                    call3.enqueue(new Callback<StudentListbean>() {

                        @Override
                        public void onResponse(Call<StudentListbean> call3, Response<StudentListbean> response) {


                            stuNames.clear();
                            stuIds.clear();

                            stuNames.add("Select Students");


                            for (int i = 0; i < response.body().getStudentList().size(); i++) {

                                stuNames.add(response.body().getStudentList().get(i).getStudentName());
                                stuIds.add(response.body().getStudentList().get(i).getStudentId());

                            }

                            ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_list_item_1, stuNames);

                            adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            stu.setAdapter(adp);

                            progress.setVisibility(View.GONE);



                        }

                        @Override
                        public void onFailure(Call<StudentListbean> call, Throwable throwable) {
                            progress.setVisibility(View.GONE);

                        }
                    });


                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        stu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



                if (position > 0)
                {
                    stuId = stuIds.get(position - 1);
                    stName = stuNames.get(position);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        toolbar.setTitle("Student Summary");
        User u = (User) getContext().getApplicationContext();

        u.back = true;


    }
}