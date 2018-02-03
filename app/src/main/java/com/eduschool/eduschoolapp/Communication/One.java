package com.eduschool.eduschoolapp.Communication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.GridLayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.communicationTeacherPOJO.RequestList;
import com.eduschool.eduschoolapp.communicationTeacherPOJO.communicationTeacherBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class One extends Fragment {
    Toolbar toolbar;
    RecyclerView grid;
    GridLayoutManager manager;
    List<RequestList> list;
    ReceivedAdapter adapter;
    ProgressBar progress;


    public One() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.communication_one, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        progress = (ProgressBar)v.findViewById(R.id.progress);

        list = new ArrayList<>();

        grid = (RecyclerView)v.findViewById(R.id.grid);
        manager = new GridLayoutManager(getContext() , 1);

        adapter = new ReceivedAdapter(getContext() , list);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);



        return v;
    }


    @Override
    public void onResume() {
        super.onResume();

        progress.setVisibility(View.VISIBLE);


        User u = (User) getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);

        Call<communicationTeacherBean> call = cr.getTeacherReceived(u.school_id , u.user_id , "Teacher");

        call.enqueue(new Callback<communicationTeacherBean>() {
            @Override
            public void onResponse(Call<communicationTeacherBean> call, Response<communicationTeacherBean> response) {

                adapter.setGridData(response.body().getRequestList());
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<communicationTeacherBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });

    }

    public class ReceivedAdapter extends RecyclerView.Adapter<ReceivedAdapter.ViewHolder>
    {

        Context context;
        List<RequestList> list = new ArrayList<>();

        public ReceivedAdapter(Context context , List<RequestList> list)
        {
            this.context = context;
            this.list = list;
        }

        public void setGridData(List<RequestList> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.received_teacher_model , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            RequestList item = list.get(position);



            String d[] = item.getPostDate().split("-");
            String d1[] = item.getFromDate().split("-");
            String d2[] = item.getToDate().split("-");
            holder.day.setText(d[0]);
            holder.month.setText(d[1] + " " + d[2]);
            holder.name.setText(item.getFromName());
            holder.type.setText(item.getType());
            holder.desc.setText(item.getDetail());
            try {
                holder.date.setText(d1[0] + " " + d1[1] + " " + d1[2] + " - " + d2[0]  + " " +  d2[1] + " " + d2[2]);
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{


            TextView day , month , name , type , desc , date;


            public ViewHolder(View itemView) {
                super(itemView);
                day = (TextView)itemView.findViewById(R.id.day);
                month = (TextView)itemView.findViewById(R.id.month);
                name = (TextView)itemView.findViewById(R.id.name);
                type = (TextView)itemView.findViewById(R.id.type);
                desc = (TextView)itemView.findViewById(R.id.desc);
                date = (TextView)itemView.findViewById(R.id.date);
            }
        }
    }

}