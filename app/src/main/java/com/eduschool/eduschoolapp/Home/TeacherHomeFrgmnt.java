package com.eduschool.eduschoolapp.Home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.AttendanceSummaryPOJO.AttendanceSummaryBean;
import com.eduschool.eduschoolapp.Events.ViewCalendarFrgmnt;
import com.eduschool.eduschoolapp.HomeWork.TeacherHwFrgmntTwo;
import com.eduschool.eduschoolapp.SyllabusManagement.TeacherAcademic;
import com.eduschool.eduschoolapp.Attendance.Attendance;
import com.eduschool.eduschoolapp.ClassWork.Teacherclswrk;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.parentTimePOJO.parentTimeBean;
import com.eduschool.eduschoolapp.teacherHomePOJO.ClassWork;
import com.eduschool.eduschoolapp.teacherHomePOJO.HomeWork;
import com.eduschool.eduschoolapp.teacherHomePOJO.PeriodList;
import com.eduschool.eduschoolapp.teacherHomePOJO.teacherHomeBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by User on 5/13/2017.
 */

public class TeacherHomeFrgmnt extends Fragment {
    Toolbar toolbar;
    LinearLayout attendance;
    TextView academic, classwork;
    TimeAdapter adapter;
    TextView date1;
    List<PeriodList> list;
    ProgressBar progress;
    RecyclerView grid;
    LinearLayoutManager manager;

    RecyclerView homeList, classList;
    GridLayoutManager manage;
    GridLayoutManager manage1;
    HomeWorkAdapter homeWorkAdapter;
    ClassWorkAdapter classWorkAdapter;
    List<HomeWork> list1;
    List<ClassWork> list2;

    TextView academicClass, academicPercent;
    ProgressBar academicProgress;

    TextView totalStudents, presentStudents, absentStudents;

    TextView eday, emonth, etitle;

    LinearLayout eventLayout;

    TextView class_name;

    TextView timetext;

    public TeacherHomeFrgmnt() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher_home_frgmnt, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

        class_name = (TextView) view.findViewById(R.id.class_name);

        eday = (TextView) view.findViewById(R.id.eday);
        emonth = (TextView) view.findViewById(R.id.emonth);
        etitle = (TextView) view.findViewById(R.id.etitle);

        eventLayout = (LinearLayout) view.findViewById(R.id.event_layout);

        timetext = (TextView)view.findViewById(R.id.timetext);

        attendance = (LinearLayout) view.findViewById(R.id.attendance);
        academic = (TextView) view.findViewById(R.id.academic);
        classwork = (TextView) view.findViewById(R.id.classwork);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        date1 = (TextView) view.findViewById(R.id.date);

        academicClass = (TextView) view.findViewById(R.id.academic_class);
        academicPercent = (TextView) view.findViewById(R.id.academic_percent);

        academicProgress = (ProgressBar) view.findViewById(R.id.academic_progress);

        totalStudents = (TextView) view.findViewById(R.id.total_students);
        presentStudents = (TextView) view.findViewById(R.id.present_students);
        absentStudents = (TextView) view.findViewById(R.id.absent_students);


        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        manage = new GridLayoutManager(getContext(), 1);
        manage1 = new GridLayoutManager(getContext(), 1);

        homeList = (RecyclerView) view.findViewById(R.id.home_list);
        classList = (RecyclerView) view.findViewById(R.id.class_list);

        homeWorkAdapter = new HomeWorkAdapter(getContext(), list1);
        classWorkAdapter = new ClassWorkAdapter(getContext(), list2);

        homeList.setAdapter(homeWorkAdapter);
        homeList.setLayoutManager(manage);

        classList.setAdapter(classWorkAdapter);
        classList.setLayoutManager(manage1);

        list = new ArrayList<>();

        adapter = new TimeAdapter(getContext(), list);

        grid = (RecyclerView) view.findViewById(R.id.grid);
        manager = new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL, false);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);


        progress.setVisibility(View.VISIBLE);


        final User u = (User) getContext().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final AllAPIs cr = retrofit.create(AllAPIs.class);
        //  progress.setVisibility(View.VISIBLE);


        Call<teacherHomeBean> call = cr.getTeacherHome(u.school_id, u.user_id);

        call.enqueue(new Callback<teacherHomeBean>() {
            @Override
            public void onResponse(Call<teacherHomeBean> call, Response<teacherHomeBean> response) {


                try {

                    date1.setText(response.body().getTodayDate());
                    homeWorkAdapter.setGridData(response.body().getHomeWork());

                    if (Objects.equals(response.body().getTimeTable().getHoliday(), "Yes"))
                    {
                        timetext.setVisibility(View.VISIBLE);
                        timetext.setText("Today is Holiday");
                        adapter.setGridData(new ArrayList<PeriodList>());
                    }
                    else
                    {
                        if (response.body().getTimeTable().getPeriodList().size() > 0)
                        {
                            timetext.setVisibility(View.GONE);
                            timetext.setText("No Time Table Found");
                            adapter.setGridData(response.body().getTimeTable().getPeriodList());
                        }
                        else
                        {
                            timetext.setVisibility(View.VISIBLE);
                            timetext.setText("No Time Table Found");
                        }


                    }



                }catch (Exception e)
                {
                    e.printStackTrace();
                }


                class_name.setText(response.body().getMyClass() + " " + response.body().getMySection());

                try {

                    if (response.body().getEvent().size() > 0) {
                        eventLayout.setVisibility(View.VISIBLE);
                        String d1[] = response.body().getEvent().get(0).getStartDate().split("-");
                        eday.setText(d1[0]);
                        emonth.setText(d1[1] + " " + d1[2]);
                        etitle.setText(response.body().getEvent().get(0).getEventType());
                    } else {
                        eventLayout.setVisibility(View.GONE);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


                Call<AttendanceSummaryBean> call2 = cr.attendnc_summry(u.school_id, u.user_id, date1.getText().toString());

                call2.enqueue(new Callback<AttendanceSummaryBean>() {
                    @Override
                    public void onResponse(Call<AttendanceSummaryBean> call, Response<AttendanceSummaryBean> response) {

                        try {

                            totalStudents.setText(String.valueOf(response.body().getAttendanceSummary().get(0).getTotalStudent()));
                            presentStudents.setText(String.valueOf(response.body().getAttendanceSummary().get(0).getTotalPresentStu()));
                            absentStudents.setText(String.valueOf(response.body().getAttendanceSummary().get(0).getTotalAbsentStu()));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<AttendanceSummaryBean> call, Throwable throwable) {
                        progress.setVisibility(View.GONE);
                    }
                });


                classWorkAdapter.setGridData(response.body().getClassWork());

                float tot = 0;
                float com = 0;

                for (int i = 0; i < response.body().getAccadmics().size(); i++) {
                    tot = tot + response.body().getAccadmics().get(i).getTotalChapter();
                    com = com + response.body().getAccadmics().get(i).getTotalCompleteChapter();

                    Log.d("asdasdtotal", String.valueOf(tot));
                    Log.d("asdasdcomp", String.valueOf(com));

                }

                Log.d("asdasdtotal", String.valueOf(tot));
                Log.d("asdasdcomp", String.valueOf(com));


                academicClass.setText(response.body().getMyClass() + " " + response.body().getMySection());
                float per = com / tot * 100;

                Log.d("asdper", String.valueOf(per));

                academicProgress.setProgress((int) per);
                academicPercent.setText(String.valueOf((int) per) + " %");


                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<teacherHomeBean> call, Throwable throwable) {
                progress.setVisibility(View.GONE);
                Log.d("asdads", throwable.toString());
            }
        });


        eventLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ViewCalendarFrgmnt frag1 = new ViewCalendarFrgmnt();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


        progress.setVisibility(View.VISIBLE);


        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Attendance frag1 = new Attendance();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();

            }
        });


        academic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherAcademic frag1 = new TeacherAcademic();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();


            }
        });


        classwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Teacherclswrk frag1 = new Teacherclswrk();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
            }
        });


        classList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Teacherclswrk frag1 = new Teacherclswrk();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
            }
        });


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Home");
        User u = (User) getContext().getApplicationContext();


        DrawerLayout drawer = (DrawerLayout)((TeacherHome) getContext()).findViewById(R.id.drawer_asiana);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();


        u.back = true;
    }


    public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {
        List<PeriodList> list = new ArrayList<>();
        Context context;

        public TimeAdapter(Context context, List<PeriodList> list) {
            this.list = list;
            this.context = context;
        }

        public void setGridData(List<PeriodList> list) {
            this.list = list;
            notifyDataSetChanged();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.time_table_model, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            PeriodList item = list.get(position);

            holder.subject.setText(item.getSubject());

            holder.period.setText(item.getFromTime() + " - " + item.getToTime());


            if (position == list.size() - 1) {
                holder.line.setVisibility(View.GONE);
            } else {
                holder.line.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView period, subject, line;

            public ViewHolder(View itemView) {
                super(itemView);

                period = (TextView) itemView.findViewById(R.id.period);
                subject = (TextView) itemView.findViewById(R.id.subject);
                line = (TextView) itemView.findViewById(R.id.line);

            }
        }
    }


    public class HomeWorkAdapter extends RecyclerView.Adapter<HomeWorkAdapter.ViewHolder> {
        List<HomeWork> list = new ArrayList<>();
        Context context;

        public HomeWorkAdapter(Context context, List<HomeWork> list) {
            this.list = list;
            this.context = context;
        }

        public void setGridData(List<HomeWork> list) {
            this.list = list;
            notifyDataSetChanged();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.home_homework_model, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            final HomeWork item = list.get(position);

            holder.classSec.setText(item.getClass_() + " " + item.getSection());
            holder.text.setText("Due Date for" + System.getProperty("line.separator") + item.getTitle());

            if (position == list.size() - 1) {
                holder.line.setVisibility(View.GONE);
            } else {
                holder.line.setVisibility(View.VISIBLE);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    android.support.v4.app.FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                    TeacherHwFrgmntTwo frag1 = new TeacherHwFrgmntTwo();
                    Bundle bundle = new Bundle();
                    bundle.putString("message", item.getHomeWorkId());
                    frag1.setArguments(bundle);
                    ft.replace(R.id.replace, frag1);
                    ft.addToBackStack(null);
                    ft.commit();


                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView classSec, text, line;

            public ViewHolder(View itemView) {
                super(itemView);

                classSec = (TextView) itemView.findViewById(R.id.class_sec);
                text = (TextView) itemView.findViewById(R.id.text);
                line = (TextView) itemView.findViewById(R.id.line);

            }
        }
    }


    public class ClassWorkAdapter extends RecyclerView.Adapter<ClassWorkAdapter.ViewHolder> {
        List<ClassWork> list = new ArrayList<>();
        Context context;

        public ClassWorkAdapter(Context context, List<ClassWork> list) {
            this.list = list;
            this.context = context;
        }

        public void setGridData(List<ClassWork> list) {
            this.list = list;
            notifyDataSetChanged();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.home_homework_model, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            ClassWork item = list.get(position);

            holder.classSec.setText(item.getClass_());



            holder.text.setText("Pending");

            if (position == list.size() - 1) {
                holder.line.setVisibility(View.GONE);
            } else {
                holder.line.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView classSec, text, line;

            public ViewHolder(View itemView) {
                super(itemView);

                classSec = (TextView) itemView.findViewById(R.id.class_sec);
                text = (TextView) itemView.findViewById(R.id.text);
                line = (TextView) itemView.findViewById(R.id.line);

            }
        }
    }


}