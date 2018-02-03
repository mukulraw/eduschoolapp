package com.eduschool.eduschoolapp.OnlineTest;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.onlinePOJO.OnlinetestList;
import com.eduschool.eduschoolapp.onlinePOJO.onlineBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class OnlineTestSummery extends Fragment {
    TextView take_test;
    Toolbar toolbar;

    RecyclerView grid;
    ProgressBar progress;

    GridLayoutManager manager;

    OnlineAdapter adapter;


    List<OnlinetestList> list;

    public OnlineTestSummery() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_online_test_summery, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);
        //take_test = (TextView) view.findViewById(R.id.take_test);
        /*take_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),OnlineTestActivity.class);
                startActivity(intent);
            }
        });
*/
        grid = (RecyclerView) view.findViewById(R.id.grid);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getContext(), 1);

        list = new ArrayList<>();

        adapter = new OnlineAdapter(getContext(), list);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Online Test");

        User u = (User) getContext().getApplicationContext();

        u.back = true;


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);


        Call<onlineBean> call = cr.getTestList(u.school_id, u.user_id, u.user_class, u.user_section);

        call.enqueue(new Callback<onlineBean>() {
            @Override
            public void onResponse(Call<onlineBean> call, Response<onlineBean> response) {

                adapter.setGridData(response.body().getOnlinetestList());

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<onlineBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


    }


    public class OnlineAdapter extends RecyclerView.Adapter<OnlineAdapter.ViewHolder> {


        List<OnlinetestList> list = new ArrayList<>();
        Context context;


        public OnlineAdapter(Context context, List<OnlinetestList> list) {
            this.context = context;
            this.list = list;
        }

        public void setGridData(List<OnlinetestList> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.online_list_model, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            final OnlinetestList item = list.get(position);


            if (Objects.equals(item.getTakeTest().getStatus(), "0"))
            {

                holder.take.setVisibility(View.VISIBLE);
                holder.details.setVisibility(View.GONE);

            }
            else if (Objects.equals(item.getTakeTest().getStatus(), "1"))
            {

                holder.take.setVisibility(View.GONE);
                holder.details.setVisibility(View.VISIBLE);

            }


            String sc = item.getMinScore();
            String hi = item.getMaxScore();
            String pas = item.getPassPercantage();

            int scc = Integer.parseInt(sc);
            int hii = Integer.parseInt(hi);


            float per = (scc/hii) * 100;

            if (per >= Integer.parseInt(pas))
            {
                holder.details.setBackgroundResource(R.drawable.green_popup);
            }
            else
            {
                holder.details.setBackgroundResource(R.drawable.red_back);
            }


            holder.take.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context , OnlineTestActivity.class);
                    intent.putExtra("title" , item.getChapterData().get(0).getChapterName());
                    intent.putExtra("id" , item.getOnlinetestId());
                    startActivity(intent);

                }
            });


            String dat = item.getStartDate();

            String[] d1 = dat.split("-");

            holder.date.setText(d1[0]);

            holder.month.setText(d1[1] + " " + d1[2]);

            holder.score.setText(item.getMinScore() + "/" + item.getMaxScore());

            holder.name.setText(item.getTestName());


        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView date, month, name, score , take;
            LinearLayout details;

            public ViewHolder(View itemView) {
                super(itemView);

                date = (TextView) itemView.findViewById(R.id.date);
                month = (TextView) itemView.findViewById(R.id.month);
                name = (TextView) itemView.findViewById(R.id.name);
                score = (TextView) itemView.findViewById(R.id.score);
                take = (TextView)itemView.findViewById(R.id.take);
                details = (LinearLayout)itemView.findViewById(R.id.details);

            }
        }

    }


}

