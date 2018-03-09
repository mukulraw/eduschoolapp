package com.eduschool.eduschoolapp.ExamAndResults;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.TeacherHome;
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
 * Created by user on 5/18/2017.
 */

public class TeacherFrgmnt1 extends Fragment {
    TextView exam;
    RecyclerView grid;
    GridLayoutManager manager;
    Toolbar toolbar;
    List<ExamTypeList> list;
    ExamAdapter adapter;
    ProgressBar progress;

    public TeacherFrgmnt1() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.exam_result_1, container, false);

        grid = (RecyclerView)view.findViewById(R.id.grid);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

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

        Call<examTypeBean> call = cr.getExamList(u.school_id , u.user_class , u.user_section);

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
        toolbar.setTitle("Exam");

        DrawerLayout drawer = (DrawerLayout)((TeacherHome) getContext()).findViewById(R.id.drawer_asiana);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        User u = (User) getContext().getApplicationContext();

        u.back = true;
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

                    FragmentManager fm =getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    TeacherFrgmnt2 frag1 =new TeacherFrgmnt2();
                    Bundle b = new Bundle();
                    b.putString("id" , item.getId());
                    b.putString("title" , item.getName());
                    frag1.setArguments(b);
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

