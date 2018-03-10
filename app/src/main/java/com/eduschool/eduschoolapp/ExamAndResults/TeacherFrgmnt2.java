package com.eduschool.eduschoolapp.ExamAndResults;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
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
 * Created by user on 5/18/2017.
 */

public class TeacherFrgmnt2 extends Fragment {
    TextView own_class,diff_class;
    Toolbar toolbar;
    String id;

    public TeacherFrgmnt2() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher_frgmnt2, container, false);

        id = getArguments().getString("id");

        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        own_class = (TextView) view.findViewById(R.id.own_class);
        diff_class = (TextView) view.findViewById(R.id.diff_class);


        own_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User u = (User) getContext().getApplicationContext();

                FragmentManager fm =getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherOwnCls frag1 =new TeacherOwnCls();
                Bundle b = new Bundle();
                b.putString("id" , id);
                b.putString("cls" , u.user_class);
                b.putString("sec" , u.user_section);
                b.putString("cname" , u.class_Name);
                b.putString("sname" , u.section_Name);
                frag1.setArguments(b);
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        diff_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setCancelable(true);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.attendance_diff_class_dialog);

                Button submit = (Button) dialog.findViewById(R.id.submit);
                final ProgressBar bar = (ProgressBar)dialog.findViewById(R.id.progress);
                final Spinner cl = (Spinner)dialog.findViewById(R.id.className);
                final Spinner se = (Spinner)dialog.findViewById(R.id.sectionName);


                final List<String> clasNames = new ArrayList<>();
                final List<String> clasIds = new ArrayList<>();

                final List<String> secNames = new ArrayList<>();
                final List<String> secIds = new ArrayList<>();

                final List<String> stuNames = new ArrayList<>();
                final List<String> stuIds = new ArrayList<>();

                final String[] cid = new String[1];
                final String[] sid = new String[1];
                final String[] cname = new String[1];
                final String[] sname = new String[1];

                final User b = (User) getActivity().getApplicationContext();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final AllAPIs cr = retrofit.create(AllAPIs.class);

                final Call<ClassListbean> call = cr.classList(b.school_id);
                bar.setVisibility(View.VISIBLE);

                call.enqueue(new Callback<ClassListbean>() {
                    @Override
                    public void onResponse(Call<ClassListbean> call, Response<ClassListbean> response) {

                        clasNames.clear();
                        clasIds.clear();

                        clasNames.add("Class");

                        for (int i = 0; i < response.body().getClassList().size(); i++)
                        {

                            clasNames.add(response.body().getClassList().get(i).getClassName());
                            clasIds.add(response.body().getClassList().get(i).getClassId());

                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter <String>(getActivity(), android.R.layout.simple_list_item_1, clasNames);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                        cl.setAdapter(adapter);


                        bar.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<ClassListbean> call, Throwable throwable) {

                        bar.setVisibility(View.GONE);

                    }
                });



                cl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if (position > 0)
                        {
                            cid[0] = clasIds.get(position - 1);
                            cname[0] = clasNames.get(position);


                            Call<SectionListbean> call2 = cr.sectionList(b.school_id, cid[0]);

                            bar.setVisibility(View.VISIBLE);


                            call2.enqueue(new Callback<SectionListbean>() {

                                @Override
                                public void onResponse(Call<SectionListbean> call, Response<SectionListbean> response) {


                                    //listSection = response.body().getSectionList();

                                    secNames.clear();
                                    secIds.clear();

                                    secNames.add("Section");

                                    for (int i = 0; i < response.body().getSectionList().size(); i++) {

                                        secNames.add(response.body().getSectionList().get(i).getSectionName());

                                        secIds.add(response.body().getSectionList().get(i).getSectionId());
                                    }


                                    ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                            android.R.layout.simple_list_item_1, secNames);

                                    adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    adp.notifyDataSetChanged();
                                    se.setAdapter(adp);

                                    bar.setVisibility(View.GONE);


                                }

                                @Override
                                public void onFailure(Call<SectionListbean> call, Throwable throwable) {
                                    bar.setVisibility(View.GONE);

                                }

                            });

                        }





                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });



                se.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        if (position > 0)
                        {
                            sid[0] = secIds.get(position - 1);
                            sname[0] = secNames.get(position);
                        }



                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });



                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();


                        if (cid[0].length() > 0)
                        {

                            if (sid[0].length() > 0)
                            {

                                FragmentManager fm =getFragmentManager();
                                FragmentTransaction ft = fm.beginTransaction();
                                TeacherDiffClass frag1 =new TeacherDiffClass();

                                Bundle b = new Bundle();
                                b.putString("id" , id);
                                b.putString("cls" , cid[0]);
                                b.putString("sec" , sid[0]);
                                b.putString("cname" , cname[0]);
                                b.putString("sname" , sname[0]);
                                frag1.setArguments(b);
                                ft.replace(R.id.replace, frag1);
                                ft.addToBackStack(null);
                                ft.commit();

                            }
                            else
                            {
                                Toast.makeText(getContext() , "Please Select Section" , Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(getContext() , "Please Select Class" , Toast.LENGTH_SHORT).show();
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
        toolbar.setTitle("Select Class");
        User u = (User) getContext().getApplicationContext();

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = ((TeacherHome) getContext()).getSupportFragmentManager();
                fm.popBackStack();

            }
        });

        u.back = false;
    }
}
