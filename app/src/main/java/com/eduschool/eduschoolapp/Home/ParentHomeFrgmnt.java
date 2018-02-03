package com.eduschool.eduschoolapp.Home;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.parentHomePOJO.ClassWork;
import com.eduschool.eduschoolapp.parentHomePOJO.HomeWork;
import com.eduschool.eduschoolapp.parentHomePOJO.PeriodList;
import com.eduschool.eduschoolapp.parentHomePOJO.parentHomeBean;

import com.eduschool.eduschoolapp.parentTimePOJO.parentTimeBean;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.eduschool.eduschoolapp.Attendance.ParentAttendanceFrgmnt;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

import im.dacer.androidcharts.BarView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by User on 5/13/2017.
 */

public class ParentHomeFrgmnt extends Fragment{
    Toolbar toolbar;
    RelativeLayout layout_attendance;
    RecyclerView grid;
    LinearLayoutManager manager;
    TimeAdapter adapter;
    List<PeriodList> list;
    ProgressBar progress;

    RecyclerView homeList , classList;

    GridLayoutManager manager1;
    GridLayoutManager manager2;

    HomeAdapter adapter1;
    ClassAdapter adapter2;

    List<HomeWork> hList;
    List<ClassWork> cList;

    public ParentHomeFrgmnt() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.parent_home_frgmnt, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);
        progress = (ProgressBar)view.findViewById(R.id.progress);


        list = new ArrayList<>();
        hList = new ArrayList<>();
        cList = new ArrayList<>();

        manager1 = new GridLayoutManager(getContext() , 1);
        manager2 = new GridLayoutManager(getContext() , 1);
        adapter1 = new HomeAdapter(getContext() , hList);
        adapter2 = new ClassAdapter(getContext() , cList);

        homeList = (RecyclerView)view.findViewById(R.id.home_work);
        classList = (RecyclerView)view.findViewById(R.id.class_work);

        homeList.setAdapter(adapter1);
        homeList.setLayoutManager(manager1);

        classList.setAdapter(adapter2);
        classList.setLayoutManager(manager2);

        adapter = new TimeAdapter(getContext() , list);
        grid = (RecyclerView)view.findViewById(R.id.grid);
        manager = new LinearLayoutManager(getContext() , LinearLayout.HORIZONTAL , false);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);

        /*BarChart chart = (BarChart)view.findViewById(R.id.chart);
        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
        chart.animateXY(2000, 2000);
        chart.invalidate();*/



        /*BarView barView = (BarView)view.findViewById(R.id.chart);
        barView.setBottomTextList(strList);
        barView.setBackgroundColor(Color.WHITE);
        barView.setDataList(dataList,100);
*/





        progress.setVisibility(View.VISIBLE);


        User u = (User) getContext().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
      //  progress.setVisibility(View.VISIBLE);



        Call<parentHomeBean> call = cr.getParentHome(u.school_id , u.user_class , u.user_section);

        call.enqueue(new Callback<parentHomeBean>() {
            @Override
            public void onResponse(Call<parentHomeBean> call, Response<parentHomeBean> response) {

                adapter.setGridData(response.body().getTimeTable().getPeriodList());
                adapter1.setGridData(response.body().getHomeWork());
                adapter2.setGridData(response.body().getClassWork());


                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<parentHomeBean> call, Throwable throwable) {
                progress.setVisibility(View.GONE);
            }
        });








        layout_attendance=(RelativeLayout)view.findViewById(R.id.layout_attendance);
        layout_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ParentAttendanceFrgmnt frag1 = new ParentAttendanceFrgmnt();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
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

        u.back = true;
    }


    public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder>
    {
        List<PeriodList> list = new ArrayList<>();
        Context context;

        public TimeAdapter(Context context , List<PeriodList> list)
        {
            this.list = list;
            this.context = context;
        }

        public void setGridData(List<PeriodList> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.time_table_model , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            PeriodList item = list.get(position);

            holder.subject.setText(item.getSubject());

            holder.period.setText(item.getFromTime() + " - " + item.getToTime());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            TextView period , subject;

            public ViewHolder(View itemView) {
                super(itemView);

                period = (TextView)itemView.findViewById(R.id.period);
                subject = (TextView)itemView.findViewById(R.id.subject);

            }
        }
    }



    public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>
    {

        List<HomeWork> list = new ArrayList<>();
        Context context;

        public HomeAdapter(Context context , List<HomeWork> list)
        {
            this.context = context;
            this.list = list;
        }

        public void setGridData(List<HomeWork> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.parent_home_list_model , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            HomeWork item = list.get(position);

            holder.title.setText(item.getTitle());
            holder.status.setText(item.getMessage());

            if (position == list.size() - 1)
            {
                holder.line.setVisibility(View.GONE);
            }
            else
            {
                holder.line.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView title , status , line;

            public ViewHolder(View itemView) {
                super(itemView);

                title = (TextView)itemView.findViewById(R.id.title);
                status = (TextView)itemView.findViewById(R.id.status);
                line = (TextView)itemView.findViewById(R.id.line);

            }
        }

    }


    public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder>
    {

        List<ClassWork> list = new ArrayList<>();
        Context context;

        public ClassAdapter(Context context , List<ClassWork> list)
        {
            this.context = context;
            this.list = list;
        }

        public void setGridData(List<ClassWork> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.parent_home_list_model , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            ClassWork item = list.get(position);

            holder.title.setText(item.getTitle());
            holder.status.setText(item.getMessage());

            if (position == list.size() - 1)
            {
                holder.line.setVisibility(View.GONE);
            }
            else
            {
                holder.line.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView title , status , line;

            public ViewHolder(View itemView) {
                super(itemView);

                title = (TextView)itemView.findViewById(R.id.title);
                status = (TextView)itemView.findViewById(R.id.status);
                line = (TextView)itemView.findViewById(R.id.line);

            }
        }

    }


}

