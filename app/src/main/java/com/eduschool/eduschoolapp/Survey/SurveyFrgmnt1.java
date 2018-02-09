package com.eduschool.eduschoolapp.Survey;

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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassWrkListPOJO.ClassWrkListbean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.teacherSurveyPOJO.SurveyListteacher;
import com.eduschool.eduschoolapp.teacherSurveyPOJO.teacherSurveryBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 6/15/2017.
 */

public class SurveyFrgmnt1 extends Fragment {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private AdapterSurvey adapter;
    private List<SurveyListteacher> albumList;
    ProgressBar progress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.survey_frgmnt1, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        progress=(ProgressBar)view.findViewById(R.id.progress);



        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        albumList = new ArrayList<>();
        adapter = new AdapterSurvey(getContext(), albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);


        User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<teacherSurveryBean> call = cr.survey_list(b.school_id,"Teacher", b.user_id);

        call.enqueue(new Callback<teacherSurveryBean>() {
            @Override
            public void onResponse(Call<teacherSurveryBean> call, Response<teacherSurveryBean> response) {

                adapter.setGridData(response.body().getSurveyListteacher());
                adapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<teacherSurveryBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });



        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Survey");
        User u = (User) getContext().getApplicationContext();

        u.back = true;

        User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<teacherSurveryBean> call = cr.survey_list(b.school_id,"Teacher", b.user_id);

        call.enqueue(new Callback<teacherSurveryBean>() {
            @Override
            public void onResponse(Call<teacherSurveryBean> call, Response<teacherSurveryBean> response) {


                adapter.setGridData(response.body().getSurveyListteacher());
                adapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<teacherSurveryBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


    }



    public class AdapterSurvey extends RecyclerView.Adapter<AdapterSurvey.myviewholder> {

        Context context;
        private List<SurveyListteacher> list;

        public AdapterSurvey(Context context, List<SurveyListteacher> albumList) {
            this.context = context;
            this.list = albumList;

        }

        public void setGridData(List<SurveyListteacher> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.survey_frgmnt_model, parent, false);

            return new myviewholder(itemView);
        }


        @Override
        public void onBindViewHolder(final myviewholder holder, final int position) {
            final SurveyListteacher item = list.get(position);

            holder.qus.setText(item.getSurveyTitle());

            //if (Objects.equals(item.getOpenSurvey(), "yes"))
            //{
                for (int i = 0 ; i < item.getServeyData().size() ; i++)
                {
                    if (Objects.equals(item.getServeyData().get(i).getQuestionStatus(), "Pending"))
                    {
                        if (!Objects.equals(item.getOpenSurvey(), "yes"))
                        {
                            holder.status.setText("Closed");
                        }
                        else
                        {
                            holder.status.setText("Pending");
                        }


                    }
                    else
                    {
                        holder.status.setText("Complete");
                    }
                }
            //}
            //else
            //{
            //
            //}



            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (!Objects.equals(holder.status.getText().toString(), "Closed"))
                    {
                        String Id = list.get(position).getSurveyId();

                        // ts.setData(list.get(position).getServeyData());

                        Intent intent = new Intent(context, Take_Survey.class);
                        intent.putExtra("Id", Id);
                        intent.putExtra("title" , list.get(position).getSurveyTitle());
                        context.startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(context , "This Survey is closed" , Toast.LENGTH_SHORT).show();
                    }





                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class myviewholder extends RecyclerView.ViewHolder {


            TextView status, qus;

            public myviewholder(View itemView) {
                super(itemView);

                status = (TextView) itemView.findViewById(R.id.status);
                qus = (TextView) itemView.findViewById(R.id.qus);



            }

        }


    }


}
