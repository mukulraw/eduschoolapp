package com.eduschool.eduschoolapp.TimeTable;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.CustomViewPager;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.teacherTimeTablePOJO.Day;
import com.eduschool.eduschoolapp.teacherTimeTablePOJO.PeriodList;
import com.eduschool.eduschoolapp.teacherTimeTablePOJO.teacherTimeTableBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 5/18/2017.
 */

public class TimeTableTeacher extends Fragment {
    ImageView left, right;
    TextView date;

    CustomViewPager pager;

    ProgressBar progress;

    TextView text;

    Calendar c, c2;

    Toolbar toolbar;


    public TimeTableTeacher() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.time_table_parent, container, false);
        progress = (ProgressBar)view.findViewById(R.id.progress);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

        text = (TextView)view.findViewById(R.id.text);

        c = Calendar.getInstance();
        c2 =Calendar.getInstance();

        left = (ImageView) view.findViewById(R.id.left);
        right = (ImageView) view.findViewById(R.id.right);
        date = (TextView) view.findViewById(R.id.date);

        pager = (CustomViewPager) view.findViewById(R.id.pager);

        loadTimeTable(0);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadTimeTable(-7);

            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadTimeTable(7);

            }
        });

        return view;

    }


    public void loadTimeTable(int amount)
    {
        progress.setVisibility(View.VISIBLE);


        User u = (User) getContext().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);



        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df2=new SimpleDateFormat("dd-MMM-yy");
        System.out.println("Date " + c.getTime());
        c.add(Calendar.DATE,amount);

        c2.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        c2.set(Calendar.HOUR_OF_DAY,0);
        c2.set(Calendar.MINUTE,0);
        c2.set(Calendar.SECOND,0);

        c2.setTime(c.getTime());

        System.out.println(df.format(c2.getTime()));



        String mon = df.format(c.getTime());


        c2.add(Calendar.DATE,6);
        System.out.println(df.format(c2.getTime()));      // Next Sunday

        Call<teacherTimeTableBean> call = cr.getTeacherTimeTable(u.school_id , df.format(c2.getTime()) , mon , u.user_id);

        date.setText(df2.format(c.getTime()) + "  to  " + df2.format(c2.getTime()));


        Log.d("call" , "call");

        call.enqueue(new Callback<teacherTimeTableBean>() {
            @Override
            public void onResponse(Call<teacherTimeTableBean> call, Response<teacherTimeTableBean> response) {

                Log.d("response" , "entered");

                pager.setOffscreenPageLimit(0);
                try {
                    FragStatePagerAdapter adapter = new FragStatePagerAdapter(getChildFragmentManager() , response.body().getTimeTableList().get(0).getDay());
                    pager.setAdapter(adapter);

                    text.setVisibility(View.GONE);
                    pager.setVisibility(View.VISIBLE);

                }catch (Exception e)
                {

                    text.setVisibility(View.VISIBLE);
                    pager.setVisibility(View.GONE);
                    e.printStackTrace();
                }

                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<teacherTimeTableBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Log.d("asdad" , t.toString());
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Time Table");
        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }


    public class FragStatePagerAdapter extends FragmentStatePagerAdapter
    {

        List<Day> list = new ArrayList<>();

        public FragStatePagerAdapter(FragmentManager fm , List<Day> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {

            //date.setText(list.get(position).getDate());

            Frag frag = new Frag();
            frag.setList(list.get(position).getPeriodList() , list.get(position).getDay());
            return frag;
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


    public static class Frag extends Fragment {

        RecyclerView grid;
        TimeAdapter adapter;
        GridLayoutManager manager;
        TextView day;
        List<PeriodList> list;
        String monday;
        TextView holiday;

        public void setList(List<PeriodList> list , String monday)
        {
            this.list = list;
            this.monday = monday;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.time_fragment , container , false);

            day = (TextView)view.findViewById(R.id.day);
            grid = (RecyclerView) view.findViewById(R.id.grid);
            holiday = (TextView)view.findViewById(R.id.holiday);

            manager = new GridLayoutManager(getContext(), 1);


            adapter = new TimeAdapter(getContext(), list);


            day.setText(monday);


            if (Objects.equals(monday.toLowerCase(), "sunday"))
            {
                holiday.setVisibility(View.VISIBLE);
            }
            else
            {
                holiday.setVisibility(View.GONE);
            }


            grid.setLayoutManager(manager);
            grid.setAdapter(adapter);


            return view;
        }
    }


    public static class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {
        Context context;
        List<PeriodList> list = new ArrayList<>();

        public TimeAdapter(Context context, List<PeriodList> list) {
            this.context = context;
            this.list = list;
        }

        public void setGridData(List<PeriodList> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.time_table_model2, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            PeriodList item = list.get(position);


            holder.period.setText(item.getFromTime() + " - " + item.getToTime());
            holder.subject.setText(item.getSubject());
            holder.teacher.setText(item.getTeacher());

            holder.number.setText("Period " + String.valueOf(position + 1));

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView period, subject, teacher , number;


            public ViewHolder(View itemView) {
                super(itemView);

                period = (TextView) itemView.findViewById(R.id.period);
                subject = (TextView) itemView.findViewById(R.id.subject);
                teacher = (TextView) itemView.findViewById(R.id.teacher);
                number = (TextView) itemView.findViewById(R.id.num);



            }
        }
    }


}
