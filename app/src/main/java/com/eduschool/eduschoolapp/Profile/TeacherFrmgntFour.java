package com.eduschool.eduschoolapp.Profile;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.teacherRequestBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class TeacherFrmgntFour extends Fragment implements DatePickerDialog.OnDateSetListener{
    TextView start, end;
    Button send;

    Spinner reason;

    List<String> list;

    String type = "";
    ProgressBar progress;

    public TeacherFrmgntFour() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.teacher_prfl_frgmnt4, container, false);
        start = (TextView)v.findViewById(R.id.start);
        end = (TextView)v.findViewById(R.id.end);

        progress = (ProgressBar)v.findViewById(R.id.progress);

        list = new ArrayList<>();


        reason = (Spinner)v.findViewById(R.id.reason);


        list.add("Sick");
        list.add("Casual");
        list.add("PL");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext() , android.R.layout.simple_list_item_1,list);

        reason.setAdapter(adapter);


        reason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                type = list.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        send=(Button)v.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String s = start.getText().toString();
                String e = end.getText().toString();


                if (s.length() > 0)
                {

                    if (e.length() > 0)
                    {

                        if (type.length() > 0)
                        {


                            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                            dialog.setCancelable(false);
                            dialog.setMessage("Are you sure you want sent Leave for Approval ?" );
                            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(final DialogInterface dialog, int id) {
                                    //Action for "Delete".


                                    User u = (User) getContext().getApplicationContext();


                                    progress.setVisibility(View.VISIBLE);





                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(u.baseURL)
                                            .addConverterFactory(ScalarsConverterFactory.create())
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    AllAPIs cr = retrofit.create(AllAPIs.class);


                                    Call<teacherRequestBean> call = cr.requestLeave(u.school_id , u.user_id , start.getText().toString() , end.getText().toString() , type);

                                    call.enqueue(new Callback<teacherRequestBean>() {
                                        @Override
                                        public void onResponse(Call<teacherRequestBean> call, Response<teacherRequestBean> response) {

                                            Toast.makeText(getContext() , "Request sent Successfully" , Toast.LENGTH_SHORT).show();

                                            progress.setVisibility(View.GONE);
                                            dialog.dismiss();

                                        }

                                        @Override
                                        public void onFailure(Call<teacherRequestBean> call, Throwable t) {
                                            progress.setVisibility(View.GONE);
                                        }
                                    });





                                }
                            })
                                    .setNegativeButton("No ", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //Action for "Cancel".
                                        }
                                    });

                            final AlertDialog alert = dialog.create();
                            alert.show();


                        }
                        else
                        {
                            Toast.makeText(getContext() , "Please choose a Type" , Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(getContext() , "Please Enter an End Date" , Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(getContext() , "Please Enter a Start Date" , Toast.LENGTH_SHORT).show();
                }


            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getFragmentManager(), "datepicker");
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment2();
                newFragment.show(getActivity().getFragmentManager(), "datepicker");
            }
        });




        return v;
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

            CharSequence strDate = null;
            Time chosenDate = new Time();
            chosenDate.set(day, monthOfYear, year);
            long dtDob = chosenDate.toMillis(true);
            strDate = DateFormat.format("dd-MMM-yyyy", dtDob);
            end.setText(strDate);


            //end.setText(days + "/" + months + "/" + years);
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

            CharSequence strDate = null;
            Time chosenDate = new Time();
            chosenDate.set(day, monthOfYear, year);
            long dtDob = chosenDate.toMillis(true);
            strDate = DateFormat.format("dd-MMM-yyyy", dtDob);
            start.setText(strDate);


            //start.setText(days + "/" + months + "/" + years);


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


