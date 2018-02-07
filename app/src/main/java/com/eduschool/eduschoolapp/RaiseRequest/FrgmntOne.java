package com.eduschool.eduschoolapp.RaiseRequest;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.recReqPOJO.RecevrequestList;
import com.eduschool.eduschoolapp.recReqPOJO.recReqBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by User on 5/10/2017.
 */

public class FrgmntOne extends Fragment {

    RecyclerView grid;
    ProgressBar progress;
    Toolbar toolbar;
    GridLayoutManager manager;
    List<RecevrequestList> list;
    RecAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.raise_request_frgmnt1, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);

        progress = (ProgressBar)v.findViewById(R.id.progress);
        grid = (RecyclerView)v.findViewById(R.id.grid);
        manager = new GridLayoutManager(getContext() , 1);
        list = new ArrayList<>();

        adapter = new RecAdapter(getActivity() , list);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Raise Request");
        User u = (User) getContext().getApplicationContext();

        u.back = true;




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<recReqBean> call = cr.getRec(u.school_id , u.user_id , u.user_class , u.user_section , "Parent");

        call.enqueue(new Callback<recReqBean>() {
            @Override
            public void onResponse(Call<recReqBean> call, Response<recReqBean> response) {

                adapter.setGridData(response.body().getRecevrequestList());

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<recReqBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });




    }

    public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder>
    {

        Context context;
        List<RecevrequestList> list = new ArrayList<>();

        public RecAdapter(Context context , List<RecevrequestList> list)
        {
            this.context = context;
            this.list = list;
        }

        public void setGridData(List<RecevrequestList> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.rec_req_model , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            final RecevrequestList item = list.get(position);

            if (item.getBirthCard().length() > 0)
            {
                String dat = item.getPostDate();

                String[] d1 = dat.split("-");

                holder.date.setText(d1[0]);

                try {
                    holder.month.setText(d1[1] + " " + d1[2]);
                }catch (Exception e2)
                {
                    e2.printStackTrace();
                }

            }
            else {
                String dat = item.getStartDate();

                String[] d1 = dat.split("-");

                holder.date.setText(d1[0]);

                try {
                    holder.month.setText(d1[1] + " " + d1[2]);
                }catch (Exception e2)
                {
                    e2.printStackTrace();
                }

            }




            holder.text.setText(item.getAdditionalDetail());

            if (item.getTime().length() > 0)
            {
                holder.time.setText(item.getTime());
                holder.time.setVisibility(View.VISIBLE);
            }
            else
            {
                holder.time.setVisibility(View.GONE);
            }



            holder.name.setText(item.getEventType() + " (by " + item.getFrom() + ")");

            String e = item.getEndDate();

            String[] dd = e.split("-");

            try {
                holder.end.setText(dd[0] + " " + dd[1] + " " + dd[2]);
            }catch (Exception e1)
            {
                e1.printStackTrace();
            }



            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (item.getBirthCard().length() > 0)
                    {
                        Dialog dialog = new Dialog(context);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.birth_popup);
                        dialog.setCancelable(true);
                        dialog.show();

                        ImageView ikm = (ImageView)dialog.findViewById(R.id.image);

                        ImageLoader loader = ImageLoader.getInstance();
                        loader.displayImage(item.getBirthCard() , ikm);

                    }




                }
            });



        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {

TextView date , month , name , end , text , time;

            public ViewHolder(View itemView) {
                super(itemView);

                date = (TextView)itemView.findViewById(R.id.date);
                month = (TextView)itemView.findViewById(R.id.month);
                name = (TextView)itemView.findViewById(R.id.name);
                end = (TextView)itemView.findViewById(R.id.end);
                text = (TextView)itemView.findViewById(R.id.text);
                time = (TextView)itemView.findViewById(R.id.time);

            }
        }
    }

}
