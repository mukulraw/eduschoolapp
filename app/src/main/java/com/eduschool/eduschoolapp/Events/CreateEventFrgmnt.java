package com.eduschool.eduschoolapp.Events;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.Communication.ComposeMessage;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.composeBean;
import com.eduschool.eduschoolapp.sectionPOJO.sectionBean;
import com.eduschool.eduschoolapp.studentPOJO.studentBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.apptik.widget.multiselectspinner.BaseMultiSelectSpinner;
import io.apptik.widget.multiselectspinner.MultiSelectSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 6/15/2017.
 */

public class CreateEventFrgmnt extends Fragment {


    Toolbar toolbar;
    LinearLayout date1, date2, time;
    Button submit;
    TextView className; //sectionName;
    static TextView tv_date, tv_time, tv_date1;
    ProgressBar progress;
    EditText desc;
    MultiSelectSpinner cls , sec;

    Spinner subject;

    List<String> clasNames;
    List<String> clasIds;

    String d1 = "";
    String d2 = "";
    static String t = "";

    List<String> checkedClasses = new ArrayList<>();
    List<String> checkedSecIds = new ArrayList<>();
    List<String> checkedSections = new ArrayList<>();



    List<String> secNames;
    List<String> secIds;

    String type;

    JSONObject classObject , sectionObject;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.create_event, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        date1 = (LinearLayout) view.findViewById(R.id.date1);
        date2 = (LinearLayout) view.findViewById(R.id.date2);
        time = (LinearLayout) view.findViewById(R.id.time);
        tv_date1 = (TextView) view.findViewById(R.id.tv_date1);
        tv_time = (TextView) view.findViewById(R.id.tv_time);
        tv_date = (TextView) view.findViewById(R.id.tv_date);
        className = (TextView) view.findViewById(R.id.className);

        cls = (MultiSelectSpinner)view.findViewById(R.id.classs);
        sec = (MultiSelectSpinner)view.findViewById(R.id.section);

        sectionObject = new JSONObject();
        classObject = new JSONObject();

        subject = (Spinner)view.findViewById(R.id.subject);

        //sectionName = (TextView) view.findViewById(R.id.sectonName);
        desc = (EditText)view.findViewById(R.id.desc);
        progress = (ProgressBar) view.findViewById(R.id.progress);

        final String classes[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "12"};
        final String section[] = {"A", "B", "C", "D", "E", "F", "G", "H"};


        clasNames = new ArrayList<>();
        clasIds = new ArrayList<>();

        secNames = new ArrayList<>();
        secIds = new ArrayList<>();


        final List<String> dd = new ArrayList<>();

        dd.add("PTM");
        dd.add("Collect Report Card");

        ArrayAdapter<String> adapter = new ArrayAdapter <String>(getContext(), android.R.layout.simple_list_item_1, dd);

        subject.setAdapter(adapter);

        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                type = dd.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        final User b = (User) getContext().getApplicationContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);

        final Call<ClassListbean> call = cr.classList(b.school_id);
        progress.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<ClassListbean>() {
            @Override
            public void onResponse(Call<ClassListbean> call, Response<ClassListbean> response) {


                for (int i = 0; i < response.body().getClassList().size(); i++)
                {

                    clasNames.add(response.body().getClassList().get(i).getClassName());
                    clasIds.add(response.body().getClassList().get(i).getClassId());

                }

                ArrayAdapter<String> adapter = new ArrayAdapter <String>(getContext(), android.R.layout.simple_list_item_multiple_choice, clasNames);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                cls.setListAdapter(adapter);


                progress.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<ClassListbean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


        cls.setListener(new BaseMultiSelectSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] booleans) {

                checkedClasses = new ArrayList<>();

                for (int i = 0 ; i < booleans.length ; i++)
                {
                    if (booleans[i])
                    {
                        checkedClasses.add(clasIds.get(i));
                    }

                }



                final User b = (User) getContext().getApplicationContext();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllAPIs cr = retrofit.create(AllAPIs.class);

                progress.setVisibility(View.VISIBLE);

                Log.d("checked" , TextUtils.join(",", checkedClasses));

                JSONArray array = new JSONArray();

                for (int i = 0 ; i < checkedClasses.size() ; i++)
                {

                    try {
                        JSONObject object = new JSONObject();
                        object.put("Id" , checkedClasses.get(i));
                        array.put(object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    classObject = new JSONObject();
                    classObject.put("class_id" , array);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Call<sectionBean> call1 = cr.getSections(b.school_id , "," + TextUtils.join(",", checkedClasses));

                call1.enqueue(new Callback<sectionBean>() {
                    @Override
                    public void onResponse(Call<sectionBean> call, Response<sectionBean> response) {

                        Log.d("response" , "entered");

                        secNames.clear();
                        secIds.clear();

                        for (int i = 0 ; i < response.body().getSectionList().size() ; i++)
                        {
                            secNames.add(response.body().getSectionList().get(i).getSectionName());
                            secIds.add(response.body().getSectionList().get(i).getSectionId());
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter <String>(getContext(), android.R.layout.simple_list_item_multiple_choice, secNames);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                        sec.setListAdapter(adapter);

                        progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<sectionBean> call, Throwable t) {
                        progress.setVisibility(View.GONE);
                        Log.d("response" , t.toString());
                    }
                });




            }
        });


        sec.setListener(new BaseMultiSelectSpinner.MultiSpinnerListener() {
            @Override
            public void onItemsSelected(boolean[] booleans) {


                checkedSections = new ArrayList<>();
                checkedSecIds = new ArrayList<>();

                for (int i = 0 ; i < booleans.length ; i++)
                {
                    if (booleans[i])
                    {
                        checkedSections.add(secIds.get(i));
                        checkedSecIds.add(secNames.get(i));
                    }

                }

                JSONArray array = new JSONArray();

                for (int i = 0 ; i < checkedSections.size() ; i++)
                {

                    try {
                        JSONObject object = new JSONObject();
                        object.put("Id" , checkedSections.get(i));
                        array.put(object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    sectionObject = new JSONObject();
                    sectionObject.put("section_id" , array);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Log.d("checked" , TextUtils.join(",", checkedSections));





            }
        });







        /*className.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Select Class");
                builder.setItems(classes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        className.setText(classes[item]);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });*/

        /*sectionName.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Select Section");
                builder.setItems(section, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        sectionName.setText(section[item]);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });*/


        date1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment2();
                newFragment.show(getActivity().getFragmentManager(), "df");
            }
        });


        date2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getFragmentManager(), "df");
            }
        });


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getActivity().getFragmentManager(), "TimePicker");

            }
        });


        submit = (Button) view.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                if (d1.length()>0)
                {

                    if (d2.length()>0)
                    {

                        if (t.length()>0)
                        {

                            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                            dialog.setCancelable(false);
                            dialog.setMessage("Are you sure you want to create the Event ?");
                            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(final DialogInterface dialog, int id) {
                                    //Action for "Delete".

                                    progress.setVisibility(View.VISIBLE);

                                    User u = (User) getContext().getApplicationContext();


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(u.baseURL)
                                            .addConverterFactory(ScalarsConverterFactory.create())
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    AllAPIs cr = retrofit.create(AllAPIs.class);

                                    Call<composeBean> call = cr.compose(
                                            u.school_id,
                                            tv_date1.getText().toString(),
                                            tv_date.getText().toString(),
                                            tv_time.getText().toString(),
                                            "",
                                            "",
                                            type,
                                            desc.getText().toString(),
                                            u.user_id,
                                            "Teacher",
                                            "Parent",
                                            "," + TextUtils.join("," ,checkedClasses),
                                            "," + TextUtils.join("," ,checkedSections)
                                    );

                                    Log.d("classs" , TextUtils.join("," ,checkedClasses));
                                    Log.d("sctio" , TextUtils.join("," ,checkedSections));

                                    call.enqueue(new Callback<composeBean>() {
                                        @Override
                                        public void onResponse(Call<composeBean> call, Response<composeBean> response) {

                                            Toast.makeText(getContext() , "Event Created successfully" , Toast.LENGTH_SHORT).show();


                                            FragmentManager fm = getFragmentManager();
                                            fm.popBackStack();


                                            dialog.dismiss();
                                            progress.setVisibility(View.GONE);

                                        }

                                        @Override
                                        public void onFailure(Call<composeBean> call, Throwable t) {
                                            progress.setVisibility(View.GONE);
                                        }
                                    });

                                }
                            })
                                    .setNegativeButton("No ", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //Action for "Cancel".
                                            dialog.dismiss();
                                        }
                                    });

                            final AlertDialog alert = dialog.create();
                            alert.show();

                        }
                        else {
                            Toast.makeText(getContext() , "Please select event time" , Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(getContext() , "Please select end date" , Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(getContext() , "Please select start date" , Toast.LENGTH_SHORT).show();
                }





            }
        });


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Create Event");
        User u = (User) getContext().getApplicationContext();

        u.back = false;

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = ((TeacherHome) getContext()).getSupportFragmentManager();
                fm.popBackStack();

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

            Date d = new Date(year, monthOfYear, day);
            SimpleDateFormat dateFormatter = new SimpleDateFormat(
                    "dd-MMM-yyyy");


            String[] d2 = dateFormatter.format(d).split("-");

            tv_date.setText(d2[0] + "-" + d2[1] + "-" + String.valueOf(year));
            d1 = d2[0] + "-" + d2[1] + String.valueOf(year);


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

            Date d = new Date(year, monthOfYear, day);
            SimpleDateFormat dateFormatter = new SimpleDateFormat(
                    "dd-MMM-yyyy");

            String[] d3 = dateFormatter.format(d).split("-");

            tv_date1.setText(d3[0] + "-" + d3[1] + "-" + String.valueOf(year));
            d2 = d3[0] + "-" + d3[1] + String.valueOf(year);


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

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //Use the current time as the default values for the time picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            String AM_PM;
            if (hour < 12) {
                AM_PM = "AM";
            } else {
                AM_PM = "PM";
            }


            //Create and return a new instance of TimePickerDialog
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        private String getTime(int hr, int min) {
            Time tme = new Time(hr, min, 0);//seconds by default set to zero
            Format formatter;
            formatter = new SimpleDateFormat("h:mm a");
            return formatter.format(tme);
        }

        //onTimeSet() callback method
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            //Do something with the user chosen time
            //Get reference of host activity (XML Layout File) TextView widget

            //Set a message for user

            //Display the user changed time on TextView
            tv_time.setText(getTime(hourOfDay, minute));
            t = getTime(hourOfDay, minute);

        }
    }


}

