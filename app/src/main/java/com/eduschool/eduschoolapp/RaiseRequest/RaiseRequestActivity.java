package com.eduschool.eduschoolapp.RaiseRequest;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.examTypePOJO.examTypeBean;
import com.eduschool.eduschoolapp.raiseREquestBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RaiseRequestActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView start, end;
    private String[] arraySpinner;
    Toolbar toolbar;

    Spinner s,s1 , query , exam;


    LinearLayout leaveLayout , examLayout;


    String d1 = "", d2 = "";

    Button send;

    String sd1 ,sd2;

    ProgressBar progress;

    List<String> examList;
    List<String> examId;

    List<String> q;

    String exId = "";
    String quer = "";

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_request);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        q = new ArrayList<>();
        examList = new ArrayList<>();
        examId = new ArrayList<>();

        leaveLayout = (LinearLayout)findViewById(R.id.leave_layout);
        examLayout = (LinearLayout)findViewById(R.id.exam_layout);

        send = (Button)findViewById(R.id.send);
        progress = (ProgressBar)findViewById(R.id.progress);

        query = (Spinner)findViewById(R.id.query);
        exam = (Spinner)findViewById(R.id.exam);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Raise Request");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        start = (TextView) findViewById(R.id.start);
        end = (TextView) findViewById(R.id.end);

        this.arraySpinner = new String[]{
                "Leave Request", "Exam & Result"
        };

        q.add("When will I get the result?");
        q.add("Query regarding marks");
        q.add("When will we get report card?");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                R.layout.spinnertext, q);
        adapter1.setDropDownViewResource(R.layout.listviewspinner);
        query.setAdapter(adapter1);

        final String[] str = {"Sick", "Out of Station"};

        s = (Spinner) findViewById(R.id.request_type);
        s1 = (Spinner) findViewById(R.id.reaason);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinnertext, arraySpinner);
        adapter.setDropDownViewResource(R.layout.listviewspinner);
        s.setAdapter(adapter);

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
                R.layout.spinnertext, str);
        adp1.setDropDownViewResource(R.layout.listviewspinner);
        s1.setAdapter(adp1);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datepicker");
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment2();
                newFragment.show(getFragmentManager(), "datepicker");
            }
        });





        progress.setVisibility(View.VISIBLE);

        User u = (User) getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);

        Call<examTypeBean> call = cr.getExamList(u.school_id , u.user_class,  u.user_section);

        call.enqueue(new Callback<examTypeBean>() {
            @Override
            public void onResponse(Call<examTypeBean> call, Response<examTypeBean> response) {


                for (int i = 0 ; i < response.body().getExamTypeList().size() ; i++)
                {
                    examList.add(response.body().getExamTypeList().get(i).getName());
                    examId.add(response.body().getExamTypeList().get(i).getId());
                }


                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(RaiseRequestActivity.this,
                        R.layout.spinnertext, examList);
                adapter2.setDropDownViewResource(R.layout.listviewspinner);
                exam.setAdapter(adapter2);


                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<examTypeBean> call, Throwable throwable) {
                progress.setVisibility(View.GONE);
            }
        });



        query.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                quer = q.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        exam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                exId = examId.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                sd1 = arraySpinner[i];

                if (i == 0)
                {

                    leaveLayout.setVisibility(View.VISIBLE);
                    examLayout.setVisibility(View.GONE);

                }
                else if (i == 1)
                {
                    leaveLayout.setVisibility(View.GONE);
                    examLayout.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                sd2 = str[i];

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progress.setVisibility(View.VISIBLE);


                User u = (User) getApplicationContext();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(u.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllAPIs cr = retrofit.create(AllAPIs.class);

                Call<raiseREquestBean> call = cr.raiseRequest(
                        u.school_id ,
                        u.user_id,
                        "Parent",
                        u.user_class,
                        u.user_section,
                        sd1,
                        d1,
                        d2,
                        sd2,
                        "",
                        quer,
                        exId
                        );

                call.enqueue(new Callback<raiseREquestBean>() {
                    @Override
                    public void onResponse(Call<raiseREquestBean> call, Response<raiseREquestBean> response) {

                        Dialog dialog = new Dialog(RaiseRequestActivity.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.raise_success_popup);
                        dialog.setCancelable(true);
                        dialog.show();

                        /*dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialogInterface) {
                                finish();
                            }
                        });*/

                        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialogInterface) {
                                finish();
                            }
                        });

                        //Toast.makeText(RaiseRequestActivity.this , "Request sent Successfully" , Toast.LENGTH_SHORT).show();
                        //finish();
                        progress.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<raiseREquestBean> call, Throwable throwable) {
                        progress.setVisibility(View.GONE);
                    }
                });


            }
        });







    }


    @SuppressLint("ValidFragment")
    public class DatePickerFragment2 extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int day) {
            String years = "" + year;
            String months = "" + (monthOfYear + 1);
            String days = "" + day;
            if (monthOfYear >= 0 && monthOfYear < 9) {
                months = "0" + (monthOfYear + 1);
            }
            if (day > 0 && day < 10) {
                days = "0" + day;

            }
            d2 = days + "-" + mon[monthOfYear] + "-" + years;
            end.setText(days + "-" + mon[monthOfYear] + "-" + years);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //use the current date as the default date in the picker
            Calendar c = Calendar.getInstance();
            @SuppressLint("WrongConstant") int year = c.get(Calendar.YEAR);
            @SuppressLint("WrongConstant") int month = c.get(Calendar.MONTH);
            @SuppressLint("WrongConstant") int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = null;
            datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);

            return datePickerDialog;
        }

    }


    @SuppressLint("ValidFragment")
    public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int day) {
            String years = "" + year;
            String months = "" + (monthOfYear + 1);
            String days = "" + day;
            if (monthOfYear >= 0 && monthOfYear < 9) {
                months = "0" + (monthOfYear + 1);
            }
            if (day > 0 && day < 10) {
                days = "0" + day;

            }
            d1 = days + "-" + mon[monthOfYear] + "-" + years;
            start.setText(days + "-" + mon[monthOfYear] + "-" + years);


        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //use the current date as the default date in the picker
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = null;
            datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);

            return datePickerDialog;
        }

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }
}
