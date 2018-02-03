package com.eduschool.eduschoolapp.SyllabusManagement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassWork.AdapterCwParent;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectList;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectListBean;
import com.eduschool.eduschoolapp.SyllabusPOJO.SyllabusList;
import com.eduschool.eduschoolapp.SyllabusPOJO.SyllabusListBean;
import com.eduschool.eduschoolapp.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by User on 5/8/2017.
 */

public class ParentAcademic extends Fragment {
    Toolbar toolbar;
    ProgressBar progress;
    RecyclerView recyclerView;
    GridLayoutManager manager;
    public List<SubjectList> listSubject;
    public List<SyllabusList> list;
    AdapterParent adapter;
    TextView teacherName;
    public List<String> subjectlist, subjectId;
    Spinner subject;
    String subjectID;

    TextView date , month;
    String[] mon = {
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
            "Aug",
            "Sep",
            "Oct",
            "Nov",
            "Dec",
    };
    String date1;

    public ParentAcademic() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.parent_academic, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);
        subject = (Spinner) view.findViewById(R.id.subject);

        date = (TextView)view.findViewById(R.id.date);
        month = (TextView)view.findViewById(R.id.month);

        teacherName = (TextView)view.findViewById(R.id.teacher_name);

        date1 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());


        String[] d1 = date1.split("-");

        date.setText(d1[2]);

        month.setText(mon[Integer.parseInt(d1[1]) - 1] + " " + d1[0]);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);

        listSubject = new ArrayList<>();
        list = new ArrayList<>();

        adapter = new AdapterParent(getActivity(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);


        subjectlist = new ArrayList<>();
        listSubject = new ArrayList<>();
        subjectId = new ArrayList<>();


        final User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);


        Call<SubjectListBean> call1 = cr.subjectList(b.school_id, b.user_class, b.user_section);

        progress.setVisibility(View.VISIBLE);

        call1.enqueue(new Callback<SubjectListBean>() {

            @Override
            public void onResponse(Call<SubjectListBean> call, Response<SubjectListBean> response) {

                listSubject = response.body().getSubjectList();
                subjectlist.clear();
                subjectId.clear();


                for (int i = 0; i < response.body().getSubjectList().size(); i++) {

                    subjectlist.add(response.body().getSubjectList().get(i).getSubjectName());
                    subjectId.add(response.body().getSubjectList().get(i).getSubjectId());
                }

                ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getContext(),
                        R.layout.academic_spinner_model, subjectlist);

                adp1.setDropDownViewResource(R.layout.academic_spinner_model);

                subject.setAdapter(adp1);
                adp1.notifyDataSetChanged();

                progress.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<SubjectListBean> call, Throwable throwable) {
                progress.setVisibility(View.GONE);

            }
        });


        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, View view, int i, long l) {

                subjectID = subjectId.get(i);

                Log.d("iddd",subjectID);

               Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllAPIs cr = retrofit.create(AllAPIs.class);


                Call<SyllabusListBean> call1 = cr.syllabus_mng(b.school_id,subjectID, b.user_class, b.user_section);

                progress.setVisibility(View.VISIBLE);

                call1.enqueue(new Callback<SyllabusListBean>() {

                    @Override
                    public void onResponse(Call<SyllabusListBean> call, Response<SyllabusListBean> response) {

                        teacherName.setText(response.body().getTeacherName());

                        try {

                            if (response.body().getSyllabusList().size() > 0)
                            {
                                adapter.setGridData(response.body().getSyllabusList());
                                adapter.notifyDataSetChanged();
                            }
                            else
                            {
                                List<SyllabusList> ll = new ArrayList<>();
                                adapter.setGridData(ll);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getContext() , "No Data Found" , Toast.LENGTH_SHORT).show();
                            }

                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }


                        progress.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<SyllabusListBean> call, Throwable throwable) {
                        progress.setVisibility(View.GONE);

                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("My Progress");
        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}