package com.eduschool.eduschoolapp.Attendance;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.AttendncDatePOJO.AttendanceListBean;
import com.eduschool.eduschoolapp.ClassWork.AdapterCwParent;
import com.eduschool.eduschoolapp.ClassWrkParentPOJO.SubjectList;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.MarkAttendancePOJO.MarkAttendanceBean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentListbean;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.attList;
import com.eduschool.eduschoolapp.studentLeavePOJO.StudentList;
import com.eduschool.eduschoolapp.studentLeavePOJO.studentLeaveBean;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MarkAttendance extends AppCompatActivity {

    Toolbar toolbar;
    ProgressBar progress;
    private RecyclerView recyclerView;
    GridLayoutManager manager;
    AdapterMrkAttndnc adapter;
    public List<String> studentlist, studentId;
    public List<attList> list;
    String Sdate, month, year, day, Sdate1;
    TextView Tday, classSection, Tmonth;
    Button submit;
    List<String> s;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Mark Attendance");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        progress = (ProgressBar) findViewById(R.id.progress);
        manager = new GridLayoutManager(MarkAttendance.this, 1);
        submit = (Button) findViewById(R.id.submit);
        Tday = (TextView) findViewById(R.id.day);
        classSection = (TextView) findViewById(R.id.classSection);
        Tmonth = (TextView) findViewById(R.id.month);


        final Intent intent = getIntent();
        Sdate = intent.getStringExtra("Date");
        Sdate1 = intent.getStringExtra("Date1");


        String d1[] = Sdate.split("-");


        final DateFormat month = new SimpleDateFormat("MMM");
        final Date month1 = new Date();

        final DateFormat year = new SimpleDateFormat("yyyy");
        final Date year1 = new Date();


        final DateFormat day = new SimpleDateFormat("dd");
        final Date day1 = new Date();

        Log.d("sds", month.format(month1));
        Log.d("sds", year.format(year1));
        Log.d("sds", day.format(day1));

        Tday.setText(d1[0]);
        Tmonth.setText(d1[1] + " " + d1[2]);


        list = new ArrayList<>();
        s = new ArrayList<>();

        adapter = new AdapterMrkAttndnc(MarkAttendance.this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        studentlist = new ArrayList<>();
        list = new ArrayList<>();
        studentId = new ArrayList<>();


        final User b = (User) getApplicationContext();


        classSection.setText(b.class_Name + " " + b.section_Name);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<AttendanceListBean> call = cr.attnce_list(b.school_id, Sdate, b.user_class, b.user_section);


        call.enqueue(new Callback<AttendanceListBean>() {
            @Override
            public void onResponse(Call<AttendanceListBean> call, Response<AttendanceListBean> response) {

                try {

                    if (response.body().getAttendanceList().size() > 0)
                    {
                        if (response.body().getAttendanceList().get(0).getAttendanceData().size() > 0) {

                            List<attList> ll = new ArrayList<>();

                            for (int i = 0; i < response.body().getAttendanceList().get(0).getAttendanceData().size(); i++) {
                                attList it = new attList();

                                it.setAttendance(response.body().getAttendanceList().get(0).getAttendanceData().get(i).getAttendance());
                                //it.setClassId(response.body().getAttendanceList().get(0).getAttendanceData().get(i).get());
                                //it.setSectionId(response.body().getAttendanceList().get(0).getAttendanceData().get(i).get());
                                it.setStudentId(response.body().getAttendanceList().get(0).getAttendanceData().get(i).getStudentId());
                                it.setStudentName(response.body().getAttendanceList().get(0).getAttendanceData().get(i).getStudentName());

                                ll.add(it);

                            }


                            submit.setClickable(false);

                            adapter.setGridData(ll, s , true);
                            adapter.notifyDataSetChanged();
                        } else {


                            progress.setVisibility(View.VISIBLE);

                            //Log.d("sdate1" , Sdate1);

                            Call<studentLeaveBean> call3 = cr.student_leave_list(b.school_id, b.user_class, b.user_section, Sdate1);

                            progress.setVisibility(View.VISIBLE);


                            call3.enqueue(new Callback<studentLeaveBean>() {

                                @Override
                                public void onResponse(Call<studentLeaveBean> call3, Response<studentLeaveBean> response) {


                                    classSection.setText(b.class_Name + " " + b.section_Name);

                                    studentlist.clear();
                                    studentId.clear();

                                    if (response.body().getStudentList().size() > 0) {
                                        submit.setVisibility(View.VISIBLE);
                                    } else {
                                        submit.setVisibility(View.GONE);
                                    }

                                    for (int i = 0; i < response.body().getStudentList().size(); i++) {

                                        if (Objects.equals(response.body().getStudentList().get(i).getAttendance(), "Yes")) {
                                            //Log.d("yes" , "entered");
                                            s.add("2");
                                        } else if (Objects.equals(response.body().getStudentList().get(i).getAttendance(), "No")) {
                                            //Log.d("no" , "entered");
                                            s.add("1");
                                        }


                                        studentlist.add(response.body().getStudentList().get(i).getStudentName());

                                        studentId.add(response.body().getStudentList().get(i).getStudentId());

                                    }


                                    for (int i = 0; i < response.body().getStudentList().size(); i++) {
                                        attList it = new attList();

                                        it.setAttendance(response.body().getStudentList().get(i).getAttendance());
                                        it.setClassId(response.body().getStudentList().get(i).getClassId());
                                        it.setSectionId(response.body().getStudentList().get(i).getSectionId());
                                        it.setStudentId(response.body().getStudentList().get(i).getStudentId());
                                        it.setStudentName(response.body().getStudentList().get(i).getStudentName());

                                        list.add(it);

                                    }


//                list = response.body().getStudentList();

                                    adapter.setGridData(list, s , false);
                                    adapter.notifyDataSetChanged();
                                    progress.setVisibility(View.GONE);


                                }

                                @Override
                                public void onFailure(Call<studentLeaveBean> call, Throwable throwable) {
                                    progress.setVisibility(View.GONE);

                                }
                            });


                        }
                    }
                    else {
                        progress.setVisibility(View.VISIBLE);

                        //Log.d("sdate1" , Sdate1);

                        Call<studentLeaveBean> call3 = cr.student_leave_list(b.school_id, b.user_class, b.user_section, Sdate1);

                        progress.setVisibility(View.VISIBLE);


                        call3.enqueue(new Callback<studentLeaveBean>() {

                            @Override
                            public void onResponse(Call<studentLeaveBean> call3, Response<studentLeaveBean> response) {


                                classSection.setText(b.class_Name + " " + b.section_Name);

                                studentlist.clear();
                                studentId.clear();

                                if (response.body().getStudentList().size() > 0) {
                                    submit.setVisibility(View.VISIBLE);
                                } else {
                                    submit.setVisibility(View.GONE);
                                }

                                for (int i = 0; i < response.body().getStudentList().size(); i++) {

                                    if (Objects.equals(response.body().getStudentList().get(i).getAttendance(), "Yes")) {
                                        //Log.d("yes" , "entered");
                                        s.add("2");
                                    } else if (Objects.equals(response.body().getStudentList().get(i).getAttendance(), "No")) {
                                        //Log.d("no" , "entered");
                                        s.add("1");
                                    }


                                    studentlist.add(response.body().getStudentList().get(i).getStudentName());

                                    studentId.add(response.body().getStudentList().get(i).getStudentId());

                                }


                                for (int i = 0; i < response.body().getStudentList().size(); i++) {
                                    attList it = new attList();

                                    it.setAttendance(response.body().getStudentList().get(i).getAttendance());
                                    it.setClassId(response.body().getStudentList().get(i).getClassId());
                                    it.setSectionId(response.body().getStudentList().get(i).getSectionId());
                                    it.setStudentId(response.body().getStudentList().get(i).getStudentId());
                                    it.setStudentName(response.body().getStudentList().get(i).getStudentName());

                                    list.add(it);

                                }


//                list = response.body().getStudentList();

                                adapter.setGridData(list, s , false);
                                adapter.notifyDataSetChanged();
                                progress.setVisibility(View.GONE);


                            }

                            @Override
                            public void onFailure(Call<studentLeaveBean> call, Throwable throwable) {
                                progress.setVisibility(View.GONE);

                            }
                        });
                    }




                } catch (Exception e) {
                    e.printStackTrace();
                }


                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<AttendanceListBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONArray jsonArray = new JSONArray();
                JSONArray jsonArray2 = new JSONArray();

                List<String> list1 = adapter.getCheckList();

                for (int i = 0; i < studentId.size(); i++) {
                    JSONObject object = new JSONObject();
                    JSONObject object2 = new JSONObject();
                    try {
                        object.put("Id", studentId.get(i));
                        object2.put("code", list1.get(i));
                        jsonArray.put(object2);
                        jsonArray2.put(object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                JSONObject jsonObject = new JSONObject();
                JSONObject jsonObject1 = new JSONObject();
                try {
                    jsonObject.put("stu_id", jsonArray2);
                    jsonObject1.put("attend", jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("dsds", jsonObject.toString());
                Log.d("dsds", jsonObject1.toString());


                progress.setVisibility(View.VISIBLE);
                Call<MarkAttendanceBean> call3 = cr.mark_attendance(b.school_id, b.user_class, b.user_section, Sdate, b.user_id, b.class_teacher, month.format(month1), year.format(year1), day.format(day1), TextUtils.join(",", studentId), TextUtils.join(",", list1));


                Log.d("month" , month.format(month1));
                Log.d("year" , year.format(year1));
                Log.d("day" , day.format(day1));


                progress.setVisibility(View.VISIBLE);


                call3.enqueue(new Callback<MarkAttendanceBean>() {

                    @Override
                    public void onResponse(Call<MarkAttendanceBean> call3, Response<MarkAttendanceBean> response) {

                        if (response.body().getStatus().equals("1")) {


                            Dialog dialog = new Dialog(MarkAttendance.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setCancelable(true);
                            dialog.setContentView(R.layout.mark_attendance_dialog);

                            TextView text = (TextView) dialog.findViewById(R.id.text);

                            text.setText("Attendance for Class " + b.class_Name + " Section " + b.section_Name + " has been submitted");

                            dialog.show();

                            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {

                                    dialog.dismiss();

                                    Intent i = new Intent();
                                    i.putExtra("date", Sdate);
                                    setResult(RESULT_OK, i);
                                    finish();

                                }
                            });


                            //Toast.makeText(MarkAttendance.this,"Success",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MarkAttendance.this, "Attendance already taken", Toast.LENGTH_SHORT).show();
                        }

                        progress.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<MarkAttendanceBean> call, Throwable throwable) {
                        progress.setVisibility(View.GONE);

                    }
                });


            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        Intent i = new Intent();
        setResult(RESULT_CANCELED, i);
        finish();


    }

}
