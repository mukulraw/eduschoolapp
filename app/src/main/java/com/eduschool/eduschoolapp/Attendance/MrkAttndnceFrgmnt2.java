package com.eduschool.eduschoolapp.Attendance;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.AttendanceSummaryPOJO.AttendanceSummaryBean;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassList;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionList;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentListbean;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.checkHolidayBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.app.Activity.RESULT_OK;

/**
 * Created by user on 5/16/2017.
 */

public class MrkAttndnceFrgmnt2 extends Fragment implements DatePickerDialog.OnDateSetListener {

    Toolbar toolbar;
    TextView date, own_attendance, different_class;
    List<ClassList> list;
    List<String> classlist, sectionlist;
    List<String> classId, sectionid;
    List<SectionList> listSection;
    boolean isFirst;
    String ClassName,classId1,SectionName,sectionId1;
    String Sdate;
    String Sdate1;

    int MARK_OWN = 1;
    int MARK_DIFF = 2;

    public MrkAttndnceFrgmnt2() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mrk_attndnc_frgmnt2, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        date = (TextView) view.findViewById(R.id.date);
        own_attendance = (TextView) view.findViewById(R.id.own_class);
        different_class = (TextView) view.findViewById(R.id.different_class);

        list = new ArrayList<>();
        classlist = new ArrayList<>();
        classId = new ArrayList<>();

        listSection = new ArrayList<>();
        sectionlist = new ArrayList<>();
        sectionid = new ArrayList<>();


        date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {



                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.datepicker_dialog);

                dialog.show();


                final ProgressBar progressBar = (ProgressBar)dialog.findViewById(R.id.progress);
                final DatePicker datePicker = (DatePicker)dialog.findViewById(R.id.date);
                Button button = (Button)dialog.findViewById(R.id.submit);

                datePicker.setMaxDate(new Date().getTime());

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {





                        int year = datePicker.getYear();
                        int month = datePicker.getMonth();
                        int day = datePicker.getDayOfMonth();


                        Calendar c = Calendar.getInstance();
                        c.set(year, month, day);



                        SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE");
                        String formattedDate1 = sdf1.format(c.getTime());

                        Log.d("asdasd" , formattedDate1);

                        if (Objects.equals(formattedDate1, "Sunday"))
                        {
                            Toast.makeText(getContext() , "Sorry, you have selected Sunday" , Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                            String formattedDate = sdf.format(c.getTime());
                            Sdate = formattedDate;

                            final User b = (User) getActivity().getApplicationContext();


                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.baseURL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            final AllAPIs cr = retrofit.create(AllAPIs.class);
                            progressBar.setVisibility(View.VISIBLE);
                            Call<checkHolidayBean> call3 = cr.checkHoliday(b.school_id , Sdate);

                            Log.d("schoolId" , b.school_id);
                            Log.d("userId" , b.user_id);
                            Log.d("date" , Sdate);

                            progressBar.setVisibility(View.VISIBLE);


                            call3.enqueue(new Callback<checkHolidayBean>() {

                                @Override
                                public void onResponse(Call<checkHolidayBean> call3, Response<checkHolidayBean> response) {


                                    if (Objects.equals(response.body().getStatus(), "1")){

                                        Toast.makeText(getContext() , "Sorry, you have selected a Holiday" , Toast.LENGTH_SHORT).show();
                                        //dialog.dismiss();

                                    }
                                    else
                                    {


                                        int year = datePicker.getYear();
                                        int month = datePicker.getMonth();
                                        int day = datePicker.getDayOfMonth();


                                        Calendar c = Calendar.getInstance();
                                        c.set(year, month, day);



                                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                                        String formattedDate = sdf.format(c.getTime());
                                        String formattedDate1 = sdf1.format(c.getTime());
                                        Sdate = formattedDate;
                                        Sdate1 = formattedDate1;
                                        date.setText(Sdate);

                                        dialog.dismiss();

                                    }
                                    progressBar.setVisibility(View.GONE);


                                }

                                @Override
                                public void onFailure(Call<checkHolidayBean> call, Throwable throwable) {
                                    progressBar.setVisibility(View.GONE);

                                }
                            });

                        }







                    }
                });


                //DialogFragment newFragment = new SelectDateFragment();
                //newFragment.show(getActivity().getFragmentManager(), "df");
            }
        });


        own_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (date.getText().toString().equals("DATE")) {
                    Toast.makeText(getActivity(), "Please Select Date!", Toast.LENGTH_SHORT).show();
                } else {


                   Intent intent =new Intent(getActivity(),MarkAttendance.class);
                   intent.putExtra("Date",Sdate);
                   intent.putExtra("Date1",Sdate1);
                   //startActivity(intent);
                    startActivityForResult(intent , MARK_OWN);
                }
            }
        });



        different_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setCancelable(true);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.attendance_diff_class_dialog);
                final Button submit = (Button) dialog.findViewById(R.id.submit);
                final Spinner className = (Spinner) dialog.findViewById(R.id.className);
                final Spinner sectionName = (Spinner) dialog.findViewById(R.id.sectionName);
                final ProgressBar progress = (ProgressBar) dialog.findViewById(R.id.progress);


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


                        list = response.body().getClassList();

                        classlist.clear();
                        classId.clear();
                        classlist.add("Select Class");
                        sectionlist.add("Select Section");

                        for (int i = 0; i < list.size(); i++) {

                            classlist.add(list.get(i).getClassName());
                            classId.add(list.get(i).getClassId());


                        }

                        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_list_item_1, classlist);
                        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        className.setAdapter(adp1);



                        progress.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<ClassListbean> call, Throwable throwable) {

                        progress.setVisibility(View.GONE);

                    }
                });


                className.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {

                        isFirst = true;

                        ClassName = adapterView.getItemAtPosition(i).toString();

                        if (i > 0) {

                            isFirst = false;
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.baseURL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllAPIs cr = retrofit.create(AllAPIs.class);


                            Call<SectionListbean> call2 = cr.sectionList(b.school_id, classId.get(i - 1));

                            progress.setVisibility(View.VISIBLE);


                            call2.enqueue(new Callback<SectionListbean>() {

                                @Override
                                public void onResponse(Call<SectionListbean> call, Response<SectionListbean> response) {


                                    listSection = response.body().getSectionList();
                                    sectionlist.clear();
                                    sectionid.clear();


                                    for (int i = 0; i < response.body().getSectionList().size(); i++) {

                                        sectionlist.add(response.body().getSectionList().get(i).getSectionName());

                                        sectionid.add(response.body().getSectionList().get(i).getSectionId());

                                    }


                                    ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                            android.R.layout.simple_list_item_1, sectionlist);

                                    adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                    sectionName.setAdapter(adp);

                                    classId1 = classId.get(i-1);
                                    Log.d("sdsdsss",classId1);


                                    progress.setVisibility(View.GONE);


                                }

                                @Override
                                public void onFailure(Call<SectionListbean> call, Throwable throwable) {
                                    progress.setVisibility(View.GONE);

                                }
                            });


                        } else {
                            isFirst = true;
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                sectionName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                        SectionName = adapterView.getItemAtPosition(i).toString();
                        sectionId1=sectionid.get(i);


                        Log.d("pooja",sectionId1);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (isFirst == true) {
                            Toast.makeText(getActivity(), "Select Class And Section. ", Toast.LENGTH_SHORT).show();
                        } else {

                            dialog.dismiss();

                            if (date.getText().toString().equals("DATE")) {
                                Toast.makeText(getActivity(), "Please Select Date!", Toast.LENGTH_SHORT).show();
                            } else {

                                Intent intent = new Intent(getActivity(), MarkAttndncDiffCls.class);
                                intent.putExtra("Date", Sdate);
                                intent.putExtra("Date1", Sdate1);
                                intent.putExtra("Class", ClassName);
                                intent.putExtra("Section", SectionName);
                                intent.putExtra("SectionId", sectionId1);
                                intent.putExtra("ClassId", classId1);

                                //startActivity(intent);

                                startActivityForResult(intent , MARK_DIFF);

                            }

                        }


                    }
                });

                dialog.show();
            }
        });

        return view;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MARK_OWN)
        {

            if (resultCode == RESULT_OK)
            {
                String sDate = data.getStringExtra("date");

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ViewOwnClassFrgmnt frag1 = new ViewOwnClassFrgmnt();
                Bundle bundle = new Bundle();
                bundle.putString("message", sDate);
                frag1.setArguments(bundle);
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }



        }
        else if (requestCode == MARK_DIFF)
        {
            if (resultCode == RESULT_OK)
            {
                android.support.v4.app.FragmentManager fm=getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ViewDffClsfrgmnt frag1 = new ViewDffClsfrgmnt();
                Bundle bundle=new Bundle();
                bundle.putString("Date", data.getStringExtra("date"));
                bundle.putString("Class", data.getStringExtra("className"));
                bundle.putString("Section", data.getStringExtra("secName"));
                bundle.putString("SectionId", data.getStringExtra("secId"));
                bundle.putString("ClassId", data.getStringExtra("classId"));
                frag1.setArguments(bundle);
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }


        }


    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Mark Attendance");
        User u = (User) getContext().getApplicationContext();

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = ((TeacherHome) getContext()).getSupportFragmentManager();
                fm.popBackStack();

            }
        });

        date.setText("DATE");

        u.back = false;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }


    @SuppressLint("ValidFragment")
    public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            @SuppressLint("WrongConstant") int yy = calendar.get(Calendar.YEAR);
            @SuppressLint("WrongConstant") int mm = calendar.get(Calendar.MONTH);
            @SuppressLint("WrongConstant") int dd = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, yy, mm, dd);
            dialog.getDatePicker().setMaxDate(new Date().getTime());
            return dialog;

            //return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }


        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);




            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");
            String formattedDate = sdf.format(c.getTime());
            String formattedDate1 = sdf1.format(c.getTime());
            Sdate = formattedDate;
            Sdate1 = formattedDate1;
            date.setText(Sdate);

        }
    }

}

