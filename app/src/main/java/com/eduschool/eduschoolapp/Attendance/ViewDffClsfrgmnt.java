package com.eduschool.eduschoolapp.Attendance;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.AttendncDatePOJO.AttendanceDatum;
import com.eduschool.eduschoolapp.AttendncDatePOJO.AttendanceListBean;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.sectionPOJO.sectionBean;
import com.eduschool.eduschoolapp.studentPOJO.studentBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.apptik.widget.multiselectspinner.BaseMultiSelectSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 8/22/2017.
 */

public class ViewDffClsfrgmnt extends Fragment {
    Toolbar toolbar;
    ProgressBar progress;
    private RecyclerView recyclerView;
    GridLayoutManager manager;
    AdapterViewAttndnc adapter;
    public List<String> studentlist,studentId;
    public List<AttendanceDatum> list;
    String Sdate,month,year,day,class_name,section_name,class_id,section_id;
    TextView Tday,classSection,Tmonth;
    Button submit;

    LinearLayout dateChange;
    ImageButton edit;

    List<String>s;
    public ViewDffClsfrgmnt() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.edit_previous_different, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

        recyclerView = (RecyclerView)view. findViewById(R.id.recycler);
        progress = (ProgressBar)view. findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);
        submit=(Button)view.findViewById(R.id.submit);
        Tday=(TextView)view.findViewById(R.id.day);
        classSection=(TextView)view.findViewById(R.id.classSection);
        Tmonth=(TextView)view.findViewById(R.id.month);

        dateChange = (LinearLayout)view.findViewById(R.id.date_change);
        edit = (ImageButton)view.findViewById(R.id.edit);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);



        Sdate = getArguments().getString("Date");
        class_name = getArguments().getString("Class");
        section_name = getArguments().getString("Section");
        section_id = getArguments().getString("SectionId");
        class_id = getArguments().getString("ClassId");

        Log.d("class",class_name);
        Log.d("class",class_id);
        Log.d("class",section_id);
        Log.d("class",section_name);
        Log.d("class",Sdate);


        String d1[] = Sdate.split("-");




        final DateFormat month =  new SimpleDateFormat("MMM");
        final Date month1 = new Date();

        final DateFormat year =  new SimpleDateFormat("yyyy");
        final Date year1 = new Date();


        final DateFormat day =  new SimpleDateFormat("dd");
        final Date day1 = new Date();

        Log.d("sds",month.format(month1));
        Log.d("sds",year.format(year1));
        Log.d("sds",day.format(day1));

        Tday.setText(d1[0]);
        Tmonth.setText(d1[1]+" "+year.format(year1));



        list = new ArrayList<>();
        s=new ArrayList<>();

        adapter = new AdapterViewAttndnc(getActivity(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        studentlist = new ArrayList<>();
        list = new ArrayList<>();
        studentId = new ArrayList<>();

//

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.class_section_dialog);
                dialog.show();

                final Spinner classSpinner = (Spinner)dialog.findViewById(R.id.class_spinner);
                final Spinner sectionSpinner = (Spinner)dialog.findViewById(R.id.section_spinner);
                final ProgressBar progressBar = (ProgressBar)dialog.findViewById(R.id.progress);
                Button ok = (Button)dialog.findViewById(R.id.ok);


                final List<String> clasNames = new ArrayList<>();
                final List<String> clasIds = new ArrayList<>();

                final List<String> secNames = new ArrayList<>();
                final List<String> secIds = new ArrayList<>();

                final String[] cName = {""};
                final String[] sName = {""};
                final String[] cId = {""};
                final String[] sId = {""};

                final User b = (User) getActivity().getApplicationContext();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllAPIs cr = retrofit.create(AllAPIs.class);

                final Call<ClassListbean> call = cr.classList(b.school_id);
                progressBar.setVisibility(View.VISIBLE);

                call.enqueue(new Callback<ClassListbean>() {
                    @Override
                    public void onResponse(Call<ClassListbean> call, Response<ClassListbean> response) {


                        for (int i = 0; i < response.body().getClassList().size(); i++)
                        {

                            clasNames.add(response.body().getClassList().get(i).getClassName());
                            clasIds.add(response.body().getClassList().get(i).getClassId());

                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter <String>(getActivity(), android.R.layout.simple_list_item_1, clasNames);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                        classSpinner.setAdapter(adapter);


                        progressBar.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<ClassListbean> call, Throwable throwable) {

                        progressBar.setVisibility(View.GONE);

                    }
                });

                classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                        cName[0] = clasNames.get(position);
                        cId[0] = clasIds.get(position);


                        secNames.clear();
                        secIds.clear();


                        progressBar.setVisibility(View.VISIBLE);


                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        final AllAPIs cr = retrofit.create(AllAPIs.class);

                        Call<SectionListbean> call2 = cr.sectionList(b.school_id, cId[0]);

                        progress.setVisibility(View.VISIBLE);


                        call2.enqueue(new Callback<SectionListbean>() {

                            @Override
                            public void onResponse(Call<SectionListbean> call, Response<SectionListbean> response) {


                                for (int i = 0; i < response.body().getSectionList().size(); i++) {
                                    secNames.add(response.body().getSectionList().get(i).getSectionName());
                                    secIds.add(response.body().getSectionList().get(i).getSectionId());
                                }


                                ArrayAdapter<String> adapter = new ArrayAdapter <String>(getActivity(), android.R.layout.simple_list_item_1, secNames);

                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                                sectionSpinner.setAdapter(adapter);



                                progressBar.setVisibility(View.GONE);


                            }

                            @Override
                            public void onFailure(Call<SectionListbean> call, Throwable throwable) {
                                progressBar.setVisibility(View.GONE);

                            }
                        });






                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });




                sectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        sId[0] = secIds.get(position);
                        sName[0] = secNames.get(position);




                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();

                        loadData(cName[0] , sName[0] , cId[0] , sId[0]);

                    }
                });



            }
        });




        loadData(class_name , section_name , class_id , section_id);





        return view;

    }


    private void loadData(String className , String sectionName , String classId , String sectionId)
    {
        User b = (User) getActivity().getApplicationContext();
        classSection.setText(className+" "+sectionName);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<AttendanceListBean> call = cr.attnce_list(b.school_id,Sdate, classId, sectionId);


        call.enqueue(new Callback<AttendanceListBean>() {
            @Override
            public void onResponse(Call<AttendanceListBean> call, Response<AttendanceListBean> response) {


                try {

                    if (response.body().getAttendanceList().size() > 0)
                    {
                        adapter.setGridData(response.body().getAttendanceList().get(0).getAttendanceData());
                        adapter.notifyDataSetChanged();
                    }
                    else
                    {
                        List<AttendanceDatum> list = new ArrayList<>();
                        adapter.setGridData(list);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext() , "No attendance taken" , Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e)
                {
                    e.printStackTrace();
                }

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<AttendanceListBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Attendance");
        User u = (User) getContext().getApplicationContext();

        loadData(class_name , section_name , class_id , section_id);

        u.back = false;
    }
}

