package com.eduschool.eduschoolapp.StudentSummary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;

import com.eduschool.eduschoolapp.Exam2;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.examTypePOJO.ExamTypeList;
import com.eduschool.eduschoolapp.examTypePOJO.examTypeBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by mukul on 16/11/17.
 */

public class Exam1 extends Fragment{

    TextView exam;
    RecyclerView grid;
    GridLayoutManager manager;
    Toolbar toolbar;
    List<ExamTypeList> list;
    ExamAdapter adapter;
    ProgressBar progress;

    String cid , sid , stid , sName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exam_result_1, container, false);

        grid = (RecyclerView)view.findViewById(R.id.grid);

        Bundle b = getArguments();

        cid = b.getString("cid");
        sid = b.getString("sid");
        stid = b.getString("stid");
        sName = b.getString("stName");

        progress = (ProgressBar)view.findViewById(R.id.progress);

        list = new ArrayList<>();

        adapter = new ExamAdapter(getContext() , list);

        manager = new GridLayoutManager(getContext() , 1);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);


        progress.setVisibility(View.VISIBLE);

        User u = (User) getContext().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);

        Call<examTypeBean> call = cr.getExamList(u.school_id , cid,  sid);

        call.enqueue(new Callback<examTypeBean>() {
            @Override
            public void onResponse(Call<examTypeBean> call, Response<examTypeBean> response) {

                adapter.setGridData(response.body().getExamTypeList());
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<examTypeBean> call, Throwable throwable) {
                progress.setVisibility(View.GONE);
            }
        });

        //exam = (TextView) view.findViewById(R.id.exam);
        /*exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });*/
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

        User u = (User) getContext().getApplicationContext();


    }


    public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder>
    {

        List<ExamTypeList> list = new ArrayList<>();
        Context context;

        public ExamAdapter(Context context , List<ExamTypeList> list)
        {
            this.context = context;
            this.list = list;
        }


        public void setGridData(List<ExamTypeList> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.exam_list_model , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            final ExamTypeList item = list.get(position);
            holder.name.setText(item.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent = new Intent(context , Exam2.class);
                    Bundle b = new Bundle();
                    b.putString("id" , item.getId());
                    b.putString("title" , item.getName());
                    b.putString("stid" , stid);
                    b.putString("cid" , cid);
                    b.putString("sid" , sid);
                    b.putString("sName" , sName);
                    intent.putExtras(b);
                    context.startActivity(intent);

                }
            });


        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {

            TextView name;

            public ViewHolder(View itemView) {
                super(itemView);

                name = (TextView)itemView.findViewById(R.id.name);

            }
        }
    }


}
