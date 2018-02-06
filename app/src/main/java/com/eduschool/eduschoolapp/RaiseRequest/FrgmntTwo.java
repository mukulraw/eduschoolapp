package com.eduschool.eduschoolapp.RaiseRequest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.sentReqPOJO.RequestList;
import com.eduschool.eduschoolapp.sentReqPOJO.sentReqBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by User on 5/10/2017.
 */

public class FrgmntTwo extends Fragment{


    RecyclerView grid;
    ProgressBar progress;
    GridLayoutManager manager;
    SentAdapter adapter;
    Toolbar toolbar;
    List<RequestList> list;


    public FrgmntTwo() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.raise_request_frgnt2, container, false);

        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);

        progress = (ProgressBar)v.findViewById(R.id.progress);
        grid = (RecyclerView)v.findViewById(R.id.grid);

        manager = new GridLayoutManager(getContext() , 1);

        list = new ArrayList<>();
        adapter = new SentAdapter(getContext() , list);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);


        return v;
    }


    @Override
    public void onResume() {
        super.onResume();

        toolbar.setTitle("Request");
        User u = (User) getContext().getApplicationContext();

        u.back = true;




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);


        Call<sentReqBean> call = cr.getSent(u.school_id , u.user_id , u.user_class , u.user_section , "Parent");

        call.enqueue(new Callback<sentReqBean>() {
            @Override
            public void onResponse(Call<sentReqBean> call, Response<sentReqBean> response) {

                adapter.setGridData(response.body().getRequestList());

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<sentReqBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


    }

    public class SentAdapter extends RecyclerView.Adapter<SentAdapter.ViewHolder>
    {

        List<RequestList> list = new ArrayList<>();
        Context context;

        public SentAdapter(Context context , List<RequestList> list)
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
            View view = inflater.inflate(R.layout.sent_list_model , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            RequestList item = list.get(position);

            String dat = item.getPostDate();

            if (Objects.equals(item.getType(), "Birthday Card"))
            {
                holder.title.setText("To: ");
                String re = "";
                for (int i = 0 ; i < item.getTo().size() ; i++)
                {
                    re = re + item.getTo().get(i).getName() + System.getProperty("line.separator");
                }
                holder.reason.setText(re);

                holder.dates.setVisibility(View.GONE);

            }
            else
            {
                holder.title.setText("Reason: ");

                if (item.getFromDate().length() > 0)
                {
                    holder.reason.setText(item.getDetail());
                }
                else
                {
                    holder.reason.setText(item.getDetail());
                }

                holder.dates.setVisibility(View.VISIBLE);

            }

            holder.dates.setText(item.getFromDate() + " - " + item.getToDate());

            try {

                String[] d1 = dat.split("-");

                holder.date.setText(d1[0]);

                holder.month.setText(d1[1] + " " + d1[2]);


            }catch (Exception e)
            {
                e.printStackTrace();
            }

            holder.type.setText(item.getType());



        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {

            TextView date , month , type , reason , title , dates;

            public ViewHolder(View itemView) {
                super(itemView);

                date = (TextView)itemView.findViewById(R.id.date);
                title = (TextView)itemView.findViewById(R.id.title);
                month = (TextView)itemView.findViewById(R.id.month);
                type = (TextView)itemView.findViewById(R.id.type);
                reason = (TextView)itemView.findViewById(R.id.reason);
                dates = (TextView)itemView.findViewById(R.id.dates);

            }
        }
    }


}