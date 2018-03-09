package com.eduschool.eduschoolapp.ClassWork;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import com.eduschool.eduschoolapp.ClassListPOJO.ClassList;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.ClassWrkListPOJO.ClassWrkListbean;
import com.eduschool.eduschoolapp.ClassWrkListPOJO.ClassworkList;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.HomeWork.AdapterHwList;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionList;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectList;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectListBean;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.ViewHomeWrkPOJO.HomewrkListbean;

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
 * Created by User on 4/12/2017.
 */

public class TwoFragment extends Fragment {
    CardView card;

    Toolbar toolbar;


    private RecyclerView recyclerView;
    GridLayoutManager manager;
    List<ClassworkList> list;
    List<ClassList> list1;
    List<String> classlist, sectionlist, subjectlist;
    List<String> classId, sectionid, subjectId;
    List<SectionList> listSection;
    List<SubjectList> listSubject;
    boolean isFirst;
    AdapterClsWrkList adapter;
    boolean isSearch = false;
    ProgressBar progress;
    String cId, sId, ssId;

    TextView no;


    String selectedSubId;


    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.fab);
        card = (CardView) view.findViewById(R.id.card);


        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

        no = (TextView)view.findViewById(R.id.no);

       /* card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherClsWrk2 frag1 = new TeacherClsWrk2();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.FragmentManager fm = getActivity().getFragmentManager();
                com.eduschool.eduschoolapp.ClassWork.FilterDailog ratingBarFragment = new com.eduschool.eduschoolapp.ClassWork.FilterDailog();
                ratingBarFragment.show(fm, "dialog");

            }
        });


        return view;
    }


}*/

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);

        list = new ArrayList<>();

        //searchList = new ArrayList<>();


        adapter = new AdapterClsWrkList(getContext(), list);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);


        list1 = new ArrayList<>();
        classlist = new ArrayList<>();
        classId = new ArrayList<>();

        listSection = new ArrayList<>();
        sectionlist = new ArrayList<>();
        sectionid = new ArrayList<>();

        listSubject = new ArrayList<>();
        subjectlist = new ArrayList<>();
        subjectId = new ArrayList<>();


        if (isSearch = false) {

            User b = (User) getActivity().getApplicationContext();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);
            progress.setVisibility(View.VISIBLE);

            Call<ClassWrkListbean> call = cr.classwrk_list(b.school_id, b.user_id);

            call.enqueue(new Callback<ClassWrkListbean>() {
                @Override
                public void onResponse(Call<ClassWrkListbean> call, Response<ClassWrkListbean> response) {

                    try {
                        if (response.body().getClassworkList().size() > 0)
                        {
                            list = response.body().getClassworkList();
                            adapter.setGridData(list);
                            adapter.notifyDataSetChanged();
                            no.setVisibility(View.GONE);
                        }
                        else
                        {
                            list = response.body().getClassworkList();
                            adapter.setGridData(list);
                            adapter.notifyDataSetChanged();
                            no.setVisibility(View.VISIBLE);
                        }
                    }catch (Exception e)
                    {
                        no.setVisibility(View.VISIBLE);
                        e.printStackTrace();
                    }


                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<ClassWrkListbean> call, Throwable throwable) {

                    progress.setVisibility(View.GONE);

                }
            });

        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String[] sd = {""};
                final String[] ed = {""};
                String su = "";

                final String[] clasId = new String[1];
                final String[] secId = new String[1];

                final Dialog dialog = new Dialog(getActivity());
                dialog.setCancelable(true);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.cw_filter);


                final Spinner subject = (Spinner) dialog.findViewById(R.id.subject);
                final Spinner classSpin = (Spinner) dialog.findViewById(R.id.classs);
                final Spinner section = (Spinner) dialog.findViewById(R.id.section);
                final TextView start = (TextView) dialog.findViewById(R.id.start);
                final TextView end = (TextView) dialog.findViewById(R.id.end);
                Button submit = (Button) dialog.findViewById(R.id.filter);
                Button clear = (Button) dialog.findViewById(R.id.clear);
                final ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.progress);




                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        adapter.setGridData(list);
                        dialog.dismiss();


                    }
                });




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
                final Retrofit[] retrofit = {new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()};

                final AllAPIs cr = retrofit[0].create(AllAPIs.class);

                Call<ClassListbean> call = cr.classList(b.school_id);
                progressBar.setVisibility(View.VISIBLE);

                call.enqueue(new Callback<ClassListbean>() {
                    @Override
                    public void onResponse(Call<ClassListbean> call, Response<ClassListbean> response) {

                        classlist.clear();
                        classId.clear();

                        classlist.add("Class");

                        for (int i = 0 ; i < response.body().getClassList().size() ; i++)
                        {
                            classlist.add(response.body().getClassList().get(i).getClassName());
                            classId.add(response.body().getClassList().get(i).getClassId());
                        }


                        ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_list_item_1, classlist);

                        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        classSpin.setAdapter(adp);



                        progressBar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<ClassListbean> call, Throwable throwable) {

                        progressBar.setVisibility(View.GONE);

                    }
                });






                classSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if (position > 0)
                        {
                            clasId[0] = classId.get(position - 1);

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.baseURL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            final AllAPIs cr = retrofit.create(AllAPIs.class);

                            Call<SectionListbean> call2 = cr.sectionList(b.school_id, clasId[0]);


                            Log.d("asdasd" , clasId[0]);


                            progressBar.setVisibility(View.VISIBLE);

                            sectionid.clear();
                            sectionlist.clear();


                            sectionlist.add("Section");


                            call2.enqueue(new Callback<SectionListbean>() {

                                @Override
                                public void onResponse(Call<SectionListbean> call, Response<SectionListbean> response) {


                                    for (int i = 0; i < response.body().getSectionList().size(); i++)
                                    {
                                        sectionlist.add(response.body().getSectionList().get(i).getSectionName());
                                        sectionid.add(response.body().getSectionList().get(i).getSectionId());
                                    }


                                    ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                            android.R.layout.simple_list_item_1, sectionlist);

                                    adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                    section.setAdapter(adp);



                                    progressBar.setVisibility(View.GONE);

                                }

                                @Override
                                public void onFailure(Call<SectionListbean> call, Throwable throwable) {
                                    progressBar.setVisibility(View.GONE);
                                    throwable.printStackTrace();
                                }
                            });
                        }





                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });



                section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if (position > 0)
                        {

                            secId[0] = sectionid.get(position - 1);


                            Call<SubjectListBean> call1 = cr.subjectList(b.school_id, clasId[0], sectionid.get(position - 1));

                            progressBar.setVisibility(View.VISIBLE);

                            call1.enqueue(new Callback<SubjectListBean>() {

                                @Override
                                public void onResponse(Call<SubjectListBean> call, Response<SubjectListBean> response) {

                                    listSubject = response.body().getSubjectList();
                                    subjectlist.clear();
                                    subjectId.clear();

                                    subjectlist.add("Subject");

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
                        }




                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });





                subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                        selectedSubId = subjectId.get(position);


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


                                Log.d("class" , clasId[0]);
                                Log.d("section" , secId[0]);
                                Log.d("subject" , selectedSubId);
                                Log.d("start" , sd[0]);
                                Log.d("end" , ed[0]);






                                User b = (User) getActivity().getApplicationContext();


                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(b.baseURL)
                                        .addConverterFactory(ScalarsConverterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                AllAPIs cr = retrofit.create(AllAPIs.class);
                                progressBar.setVisibility(View.VISIBLE);


                                Call<ClassWrkListbean> cal = cr.classwrk_listsearch(
                                        b.school_id,
                                        b.user_id,
                                        clasId[0],
                                        secId[0],
                                        selectedSubId,
                                        sd[0],
                                        ed[0]
                                );

                                cal.enqueue(new Callback<ClassWrkListbean>() {
                                    @Override
                                    public void onResponse(Call<ClassWrkListbean> call, Response<ClassWrkListbean> response) {


                                        adapter.setGridData(response.body().getClassworkList());


                                        progressBar.setVisibility(View.GONE);


                                    }

                                    @Override
                                    public void onFailure(Call<ClassWrkListbean> call, Throwable t) {
                                        progressBar.setVisibility(View.GONE);
                                    }
                                });









                                /*for (int i = 0 ; i < list.size() ; i++)
                                {

                                    String ssuubb = list.get(i).getSubject();

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


                                *//*hs1.addAll(l2);
                                l2.clear();
                                l2.addAll(hs1);
                                adapter.setGridData(l2);
*//*

                                List<ClassworkList> l3 = new ArrayList<>();

                                boolean flag = false;

                                for (int i = 0; i < l2.size(); i++) {


                                    String crDate = l2.get(i).getCreateDate();

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


                            *//*try {
                                if (Objects.equals(selectedSubId, ssuubb)) {
                                    Log.d("asdasd" , "subject");
                                    l2.add(list.get(i));
                                }
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }*//*


                            *//*try {
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
                            }*//*

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

*/

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


        return view;

    }


    @Override
    public void onResume() {
        super.onResume();


        DrawerLayout drawer = (DrawerLayout) ((TeacherHome) getContext()).findViewById(R.id.drawer_asiana);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        User u = (User) getContext().getApplicationContext();


        if (isSearch == false) {

            User b = (User) getActivity().getApplicationContext();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);
            progress.setVisibility(View.VISIBLE);

            Call<ClassWrkListbean> call = cr.classwrk_list(b.school_id, b.user_id);

            call.enqueue(new Callback<ClassWrkListbean>() {
                @Override
                public void onResponse(Call<ClassWrkListbean> call, Response<ClassWrkListbean> response) {

                    try {
                        if (response.body().getClassworkList().size() > 0)
                        {
                            list = response.body().getClassworkList();
                            adapter.setGridData(list);
                            adapter.notifyDataSetChanged();
                            no.setVisibility(View.GONE);
                        }
                        else
                        {
                            list = response.body().getClassworkList();
                            adapter.setGridData(list);
                            adapter.notifyDataSetChanged();
                            no.setVisibility(View.VISIBLE);
                        }
                    }catch (Exception e)
                    {
                        no.setVisibility(View.VISIBLE);
                        e.printStackTrace();
                    }


                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<ClassWrkListbean> call, Throwable throwable) {
                    progress.setVisibility(View.GONE);

                }
            });


        } else {
            /*Log.d("elseSearchh", String.valueOf(isSearch));
            final User b = (User) getActivity().getApplicationContext();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);

            progress.setVisibility(View.VISIBLE);
            Log.d("iddd", String.valueOf(sId));

            Call<ClassWrkListbean> call = cr.classwrk_list(b.school_id, b.user_id, cId, sId, ssId);

            call.enqueue(new Callback<ClassWrkListbean>() {
                @Override
                public void onResponse(Call<ClassWrkListbean> call, Response<ClassWrkListbean> response) {


                    try {
                        if (response.body().getClassworkList().size() > 0)
                        {
                            list = response.body().getClassworkList();
                            adapter.setGridData(list);
                            adapter.notifyDataSetChanged();
                            no.setVisibility(View.GONE);
                        }
                        else
                        {
                            list = response.body().getClassworkList();
                            adapter.setGridData(list);
                            adapter.notifyDataSetChanged();
                            no.setVisibility(View.VISIBLE);
                        }
                    }catch (Exception e)
                    {
                        no.setVisibility(View.VISIBLE);
                        e.printStackTrace();
                    }





                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<ClassWrkListbean> call, Throwable throwable) {
                    progress.setVisibility(View.GONE);

                }
            });*/


        }
    }


}