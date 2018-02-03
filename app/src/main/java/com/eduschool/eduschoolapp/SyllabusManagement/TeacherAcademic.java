package com.eduschool.eduschoolapp.SyllabusManagement;

import android.app.Dialog;
import android.content.Context;
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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ChapterListPOJO.ChapterListbean;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectListBean;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.academicTeacherPOJO.ExamType;
import com.eduschool.eduschoolapp.academicTeacherPOJO.SyllabusList;
import com.eduschool.eduschoolapp.academicTeacherPOJO.academicTeacherBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class TeacherAcademic extends Fragment{
    TextView change;
    Toolbar toolbar;
    RecyclerView grid;
    List<ExamType> list;
    AcademicAdapter adapter;
    GridLayoutManager manager;
    ProgressBar progress;
    TextView day , month;

    TextView subb;

    public TeacherAcademic() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher_academic, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        change=(TextView)view.findViewById(R.id.change);
        grid = (RecyclerView)view.findViewById(R.id.grid);
        progress = (ProgressBar)view.findViewById(R.id.progress);

        subb = (TextView)view.findViewById(R.id.sub);

        day = (TextView)view.findViewById(R.id.day);
        month = (TextView)view.findViewById(R.id.month);




        String date = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());

        String[] d1 = date.split("-");

        User u = (User) getContext().getApplicationContext();


        day.setText(u.class_Name + " " + u.section_Name);
        month.setText(d1[0] + " " + d1[1] + " " + d1[2]);


        manager = new GridLayoutManager(getContext() , 1);

        list = new ArrayList<>();

        adapter = new AcademicAdapter(getContext() , list);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(getActivity());
                dialog.setCancelable(true);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.academic_popup);
                dialog.show();

                final ProgressBar bar = (ProgressBar)dialog.findViewById(R.id.progress);

                final Spinner cls = (Spinner)dialog.findViewById(R.id.cls);
                final Spinner sec = (Spinner)dialog.findViewById(R.id.section);
                final Spinner sub = (Spinner)dialog.findViewById(R.id.subject);

                final String[] cid = new String[1];
                final String[] sid = new String[1];
                final String[] sname = new String[1];
                final String[] secid = new String[1];
                final String[] secna = new String[1];
                final String[] clsna = new String[1];

                final List<String> clasNames = new ArrayList<>();
                final List<String> clasIds = new ArrayList<>();

                final List<String> secNames = new ArrayList<>();
                final List<String> secIds = new ArrayList<>();

                final List<String> subNames = new ArrayList<>();
                final List<String> subIds = new ArrayList<>();


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

                        clasIds.clear();
                        clasNames.clear();

                        for (int i = 0; i < response.body().getClassList().size(); i++)
                        {

                            clasNames.add(response.body().getClassList().get(i).getClassName());
                            clasIds.add(response.body().getClassList().get(i).getClassId());

                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter <String>(getActivity(), android.R.layout.simple_list_item_1, clasNames);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                        cls.setAdapter(adapter);


                        bar.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<ClassListbean> call, Throwable throwable) {

                        bar.setVisibility(View.GONE);

                    }
                });



                cls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                        cid[0] = clasIds.get(position);
                        clsna[0] = clasNames.get(position);

                        Call<SectionListbean> call2 = cr.sectionList(b.school_id, clasIds.get(position));

                        //Toast.makeText(getActivity(), a, Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.VISIBLE);


                        call2.enqueue(new Callback<SectionListbean>() {

                            @Override
                            public void onResponse(Call<SectionListbean> call, Response<SectionListbean> response) {


                                //listSection = response.body().getSectionList();

                                secNames.clear();
                                secIds.clear();

                                for (int i = 0; i < response.body().getSectionList().size(); i++) {

                                    secNames.add(response.body().getSectionList().get(i).getSectionName());

                                    secIds.add(response.body().getSectionList().get(i).getSectionId());
                                }


                                ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                        android.R.layout.simple_list_item_1, secNames);

                                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                adp.notifyDataSetChanged();
                                sec.setAdapter(adp);

                                bar.setVisibility(View.GONE);


                            }

                            @Override
                            public void onFailure(Call<SectionListbean> call, Throwable throwable) {
                                bar.setVisibility(View.GONE);

                            }

                        });



                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });




                sec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        secid[0] = secIds.get(position);
                        secna[0] = secNames.get(position);

                        Call<SubjectListBean> call1 = cr.subjectList(b.school_id, cid[0],secIds.get(position));

                        bar.setVisibility(View.VISIBLE);

                        call1.enqueue(new Callback<SubjectListBean>() {

                            @Override
                            public void onResponse(Call<SubjectListBean> call, Response<SubjectListBean> response) {



                                subNames.clear();
                                subIds.clear();

                                for (int i = 0; i < response.body().getSubjectList().size(); i++) {

                                    subNames.add(response.body().getSubjectList().get(i).getSubjectName());

                                    subIds.add(response.body().getSubjectList().get(i).getSubjectId());
                                }


                                ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                        android.R.layout.simple_list_item_1, subNames);

                                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                adp.notifyDataSetChanged();
                                sub.setAdapter(adp);

                                bar.setVisibility(View.GONE);





                            }

                            @Override
                            public void onFailure(Call<SubjectListBean> call, Throwable throwable) {
                                bar.setVisibility(View.GONE);

                            }
                        });

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });



                sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        sname[0] = subNames.get(position);
                        sid[0] = subIds.get(position);







                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });



                Button submit=(Button)dialog.findViewById(R.id.submit);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        User u = (User) getContext().getApplicationContext();


                        bar.setVisibility(View.VISIBLE);


                        Call<academicTeacherBean> call = cr.getAcademicBySubject(u.school_id , sid[0] , cid[0] , secid[0]);
                        call.enqueue(new Callback<academicTeacherBean>() {
                            @Override
                            public void onResponse(Call<academicTeacherBean> call, Response<academicTeacherBean> response) {

                                day.setText(clsna[0] + " " + secna[0]);

                                subb.setText(sname[0]);

                                try {

                                    if (response.body().getSyllabusList().size() > 0)
                                    {

                                        Log.d("data" , "haveData");

                                        adapter.setData(response.body().getSyllabusList().get(0).getExamType());
                                    }
                                    else
                                    {
                                        Log.d("data" , "noData");

                                        List<ExamType> ll = new ArrayList<>();
                                        adapter.setData(ll);
                                        Toast.makeText(getContext() , "No Data Found" , Toast.LENGTH_SHORT).show();
                                    }

                                }catch (Exception e)
                                {
                                    e.printStackTrace();
                                }

                                bar.setVisibility(View.GONE);

                                dialog.dismiss();

                            }

                            @Override
                            public void onFailure(Call<academicTeacherBean> call, Throwable throwable) {
                                Log.d("asdasd" , throwable.toString());
                                bar.setVisibility(View.GONE);
                            }
                        });


                        dialog.dismiss();

                    }
                });
            }
        });

        progress.setVisibility(View.VISIBLE);

        Log.d("asdasd" , "1");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);

        Call<academicTeacherBean> call = cr.getTeacherAcademic(u.school_id , u.user_id);
        call.enqueue(new Callback<academicTeacherBean>() {
            @Override
            public void onResponse(Call<academicTeacherBean> call, Response<academicTeacherBean> response) {


                try {
                    subb.setText(response.body().getSyllabusList().get(0).getSubjectName());
                    if (response.body().getSyllabusList().size() > 0)
                    {
                        adapter.setData(response.body().getSyllabusList().get(0).getExamType());
                    }
                    else
                    {
                        List<ExamType> ll = new ArrayList<>();
                        adapter.setData(ll);
                        Toast.makeText(getContext() , "No Data Found" , Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e)
                {
                    e.printStackTrace();
                }




                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<academicTeacherBean> call, Throwable throwable) {
                Log.d("asdasd" , throwable.toString());
                progress.setVisibility(View.GONE);
            }
        });

        return view;

    }


    public class AcademicAdapter extends RecyclerView.Adapter<AcademicAdapter.ViewHolder>
    {

        List<ExamType> list = new ArrayList<>();
        Context context;
        LayoutInflater inflater;

        public AcademicAdapter(Context context , List<ExamType> list)
        {
            this.context = context;
            this.list = list;
        }

        public void setData(List<ExamType> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.academic_model , parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            ExamType item = list.get(position);

            holder.title.setText(item.getTitle());

            holder.add.removeAllViews();

            for ( int i = 0 ; i < item.getChapterList().size() ; i++)
            {
                View v = inflater.inflate(R.layout.syllabus_management_model , null);

                TextView index = (TextView)v.findViewById(R.id.index);
                TextView chapter = (TextView)v.findViewById(R.id.chapter);
                TextView status = (TextView)v.findViewById(R.id.status);
                TextView line = (TextView)v.findViewById(R.id.line);


                if (i == item.getChapterList().size()-1)
                {
                    line.setVisibility(View.GONE);
                }
                else
                {
                    line.setVisibility(View.VISIBLE);
                }


                index.setText(String.valueOf(i + 1));
                chapter.setText(item.getChapterList().get(i).getChapterName());

                if (Objects.equals(item.getChapterList().get(i).getChapterStatus(), "Ongoing"))
                {
                    status.setText(item.getChapterList().get(i).getChapterStatus());
                    status.setBackgroundResource(R.drawable.orange_popup);
                }
                else if (Objects.equals(item.getChapterList().get(i).getChapterStatus(), "Pending"))
                {
                    status.setText(item.getChapterList().get(i).getChapterStatus());
                    status.setBackgroundResource(R.drawable.red_popup);
                }
                else if (Objects.equals(item.getChapterList().get(i).getChapterStatus(), "Completed"))
                {
                    status.setText(item.getChapterList().get(i).getChapterStatus());
                    status.setBackgroundResource(R.drawable.green_popup);
                }


                holder.add.addView(v);


            }

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView title;
            LinearLayout add;

            public ViewHolder(View itemView) {
                super(itemView);

                title = (TextView)itemView.findViewById(R.id.title);
                add = (LinearLayout)itemView.findViewById(R.id.add);

            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Syllabus Management");
        User u = (User) getContext().getApplicationContext();

        u.back = true;






    }
}