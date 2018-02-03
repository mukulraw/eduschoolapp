package com.eduschool.eduschoolapp.ClassWork;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassWrkParent1POJO.ClasssworkList;
import com.eduschool.eduschoolapp.ClassWrkParent1POJO.ClasssworkListBean;
import com.eduschool.eduschoolapp.ClassWrkParent1POJO.ClassworkList;
import com.eduschool.eduschoolapp.ClassWrkParentPOJO.ClasssubjectListBean;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.HomeWork.AdapterParent1;
import com.eduschool.eduschoolapp.HomeWork.HomeWorkFrgmntTwo;
import com.eduschool.eduschoolapp.HomeWrkParent1POJO.HomeWorkListBean;
import com.eduschool.eduschoolapp.HomeWrkParent1POJO.HomeworkList_;
import com.eduschool.eduschoolapp.HomewrkParentPOJO.ParentSubjectListBean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectList;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectListBean;
import com.eduschool.eduschoolapp.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by User on 5/8/2017.
 */

public class ClassWorkFrgmntTwo extends Fragment {
    Toolbar toolbar;
    ProgressBar progress;
    private RecyclerView recyclerView;
    GridLayoutManager manager;
    String date;
    String strtext;
    List<ClassworkList> list;
    AdapterCwParent1 adapter;
    String selectedSubId;
    List<SubjectList> listSubject;
    List<String> subjectlist;
    List<String> subjectId;
    boolean isSearch=false;
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
            "Dec"
    };

    TextView name, classSection;
    public ClassWorkFrgmntTwo() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.class_wrk_frgmnt_two, container, false);
        strtext = getArguments().getString("message");
        name = (TextView) view.findViewById(R.id.name);
        classSection = (TextView) view.findViewById(R.id.classSection);

        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.fab);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);


        list = new ArrayList<>();
        subjectlist = new ArrayList<>();
        subjectId = new ArrayList<>();

        adapter = new AdapterCwParent1(getActivity(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String[] sd = {""};
                final String[] ed = {""};
                String su = "";

                final Dialog dialog = new Dialog(getActivity());
                dialog.setCancelable(true);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.home_wrk_filter);


                final Spinner subject = (Spinner) dialog.findViewById(R.id.subject);
                final TextView start = (TextView) dialog.findViewById(R.id.start);
                final TextView end = (TextView) dialog.findViewById(R.id.end);
                Button submit = (Button) dialog.findViewById(R.id.filter);
                final ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.progress);


                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        final Dialog dialog1 = new Dialog(getActivity());
                        dialog1.setCancelable(true);
                        dialog1.setContentView(R.layout.datepicker_dialog);
                        dialog1.show();


                        final DatePicker datePicker = (DatePicker) dialog1.findViewById(R.id.date);
                        Button submit = (Button) dialog1.findViewById(R.id.submit);


                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                int year = datePicker.getYear();
                                int month = datePicker.getMonth();
                                int day = datePicker.getDayOfMonth();

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, month, day);

                                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                                String strDate = format.format(calendar.getTime());

                                sd[0] = strDate;

                                start.setText(strDate);


                                dialog1.dismiss();


                            }
                        });


                    }
                });

                end.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        final Dialog dialog1 = new Dialog(getActivity());
                        dialog1.setCancelable(true);
                        dialog1.setContentView(R.layout.datepicker_dialog);
                        dialog1.show();


                        final DatePicker datePicker = (DatePicker) dialog1.findViewById(R.id.date);
                        Button submit = (Button) dialog1.findViewById(R.id.submit);


                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                int year = datePicker.getYear();
                                int month = datePicker.getMonth();
                                int day = datePicker.getDayOfMonth();

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, month, day);

                                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                                String strDate = format.format(calendar.getTime());

                                ed[0] = strDate;

                                end.setText(strDate);

                                dialog1.dismiss();
                            }
                        });


                    }
                });

                final User b = (User) getActivity().getApplicationContext();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllAPIs cr = retrofit.create(AllAPIs.class);

                Call<SubjectListBean> call1 = cr.subjectList(b.school_id, b.user_class, b.user_section);

                progressBar.setVisibility(View.VISIBLE);

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

                        ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_list_item_1, subjectlist);

                        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        subject.setAdapter(adp);
                        progressBar.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<SubjectListBean> call, Throwable throwable) {
                        progressBar.setVisibility(View.GONE);

                    }
                });


                subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                        selectedSubId = subjectlist.get(position);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if (sd[0].length() > 0)
                        {

                            if (ed[0].length() > 0)
                            {

                                List<ClassworkList> l2 = new ArrayList<>();



                                for (int i = 0 ; i < list.size() ; i++)
                                {

                                    String ssuubb = list.get(i).getSubjectName();

                                    try {
                                        if (Objects.equals(selectedSubId, ssuubb)) {
                                            //Log.d("asdasd" , "subject");
                                            l2.add(list.get(i));
                                        }
                                    }catch (Exception e)
                                    {
                                        e.printStackTrace();
                                    }

                                }

                                HashSet<ClassworkList> hs1 = new HashSet<>();


                                /*hs1.addAll(l2);
                                l2.clear();
                                l2.addAll(hs1);
                                adapter.setGridData(l2);
*/

                                List<ClassworkList> l3 = new ArrayList<>();

                                boolean flag = false;

                                for (int i = 0; i < l2.size(); i++) {


                                    String crDate = l2.get(i).getPostedDate();

                                    Log.d("crDate" , crDate);

                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

                                    Date date3 = null;
                                    Date date1 = null;
                                    Date date2 = null;
                                    try {

                                        date1 = sdf.parse(crDate);
                                        date2 = sdf.parse(sd[0]);
                                        date3 = sdf.parse(ed[0]);

                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }


                            /*try {
                                if (Objects.equals(selectedSubId, ssuubb)) {
                                    Log.d("asdasd" , "subject");
                                    l2.add(list.get(i));
                                }
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }*/


                            /*try {
                                if (date1.after(date2)) {
                                    Log.d("asdasd" , "date1");
                                    l3.add(l2.get(i));
                                    flag = true;
                                }
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }



                            try {
                                if (date1.before(date3)) {
                                    Log.d("asdasd" , "date2");
                                    l3.add(l2.get(i));
                                    flag = true;
                                }
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }*/

                                    try {
                                        if (date1.before(date3) && date1.after(date2) || date1.equals(date3) || date1.equals(date2))
                                        {
                                            Log.d("asdasd" , "date3");
                                            l3.add(l2.get(i));
                                            flag = true;
                                        }
                                    }catch (Exception e)
                                    {
                                        e.printStackTrace();
                                    }

                                }

                                HashSet<ClassworkList> hs = new HashSet<>();


                                hs.addAll(l3);
                                l3.clear();
                                l3.addAll(hs);
                                adapter.setGridData(l3);



                                dialog.dismiss();



                            }
                            else
                            {
                                Toast.makeText(getContext() , "Please Select an End Date" , Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(getContext() , "Please Select a Start Date" , Toast.LENGTH_SHORT).show();
                        }






                    }
                });

                dialog.show();



            }
        });


      /*  card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ClassWrkFrgmntThree frag1 = new ClassWrkFrgmntThree();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });*/

            User b = (User) getActivity().getApplicationContext();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);
            progress.setVisibility(View.VISIBLE);

            Call<ClasssworkListBean> call = cr.claass_wrk(b.school_id, b.user_class, b.user_section, b.user_id, strtext);

            Log.d("vsdvs", String.valueOf(b.user_class));
            Log.d("vsdvs", String.valueOf(b.user_section));


            call.enqueue(new Callback<ClasssworkListBean>() {
                @Override
                public void onResponse(Call<ClasssworkListBean> call, Response<ClasssworkListBean> response) {

                    try {

                        if (response.body().getClasssworkList().size() > 0)
                        {
                            name.setText("Name of Student - " + response.body().getClasssworkList().get(0).getStudentName());
                            classSection.setText(response.body().getClasssworkList().get(0).getClassName() + " " + response.body().getClasssworkList().get(0).getSectionName());


                            list = response.body().getClasssworkList().get(0).getClassworkList();

                            adapter.setGridData(list);
                            adapter.notifyDataSetChanged();

                        }
                        else
                        {
                            Toast.makeText(getContext() , "No Data Found" , Toast.LENGTH_SHORT).show();
                        }

                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<ClasssworkListBean> call, Throwable throwable) {

                    progress.setVisibility(View.GONE);

                }
            });


        return view;

    }


    /*@SuppressLint("ValidFragment")
    public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            @SuppressLint("WrongConstant") int yy = calendar.get(Calendar.YEAR);
            @SuppressLint("WrongConstant") int mm = calendar.get(Calendar.MONTH);
            @SuppressLint("WrongConstant") int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }


        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = sdf.format(c.getTime());
            date = formattedDate;

            User b = (User) getActivity().getApplicationContext();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);
            progress.setVisibility(View.VISIBLE);

            Log.d("sdffdf",date);
            Call<ClasssworkListBean> call = cr.claass_wrk(b.school_id, b.user_class, b.user_section, b.user_id, strtext,date);

            call.enqueue(new Callback<ClasssworkListBean>() {
                @Override
                public void onResponse(Call<ClasssworkListBean> call, Response<ClasssworkListBean> response) {

                    name.setText("Name of Student - " + response.body().getClasssworkList().get(0).getStudentName());
                    classSection.setText(response.body().getClasssworkList().get(0).getClassName() + " " + response.body().getClasssworkList().get(0).getSectionName());
                    adapter.setGridData(response.body().getClasssworkList().get(0).getClassworkList());
                    adapter.notifyDataSetChanged();
                    progress.setVisibility(View.GONE);


                }

                @Override
                public void onFailure(Call<ClasssworkListBean> call, Throwable throwable) {

                    progress.setVisibility(View.GONE);

                }
            });


        }

    }*/


    @Override
    public void onResume() {

        super.onResume();
        toolbar.setTitle("Class Work");
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = ((ParentHome) getContext()).getSupportFragmentManager();
                fm.popBackStack();

            }
        });
        User u = (User) getContext().getApplicationContext();

        u.back = false;




    }
}