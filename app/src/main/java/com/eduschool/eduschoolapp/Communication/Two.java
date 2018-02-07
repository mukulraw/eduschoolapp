package com.eduschool.eduschoolapp.Communication;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.sentCommunicationTeacher.RequestList;
import com.eduschool.eduschoolapp.sentCommunicationTeacher.sentCommunicationTeacherBean;
import com.nostra13.universalimageloader.core.ImageLoader;

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
 * Created by user on 5/25/2017.
 */

public class Two extends Fragment {
    Toolbar toolbar;

    RecyclerView grid;
    ProgressBar progress;
    GridLayoutManager manager;
    List<RequestList> list;
    SentAdapter adapter;

    public Two() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.communication_two, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

        list = new ArrayList<>();

        manager = new GridLayoutManager(getContext() , 1);
        progress = (ProgressBar)v.findViewById(R.id.progress);
        grid = (RecyclerView) v.findViewById(R.id.grid);

        adapter = new SentAdapter(getContext() , list);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);



        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Communication");


        progress.setVisibility(View.VISIBLE);


        User u = (User) getContext().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);

        Call<sentCommunicationTeacherBean> call = cr.getTeacherCommunication(u.school_id , u.user_id , "Teacher");

        call.enqueue(new Callback<sentCommunicationTeacherBean>() {
            @Override
            public void onResponse(Call<sentCommunicationTeacherBean> call, Response<sentCommunicationTeacherBean> response) {

                adapter.setGridData(response.body().getRequestList());

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<sentCommunicationTeacherBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });



    }

    public class SentAdapter extends RecyclerView.Adapter<SentAdapter.ViewHolder>
    {
        Context context;
        List<RequestList> list = new ArrayList<>();

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
            View view = inflater.inflate(R.layout.teacher_sent_list_model , parent , false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            //holder.setIsRecyclable(false);

            final RequestList item = list.get(position);


            /*if (Objects.equals(item.getType(), "PTM"))
            {
                holder.date.setText(item.getToDate());
            }
            else if (Objects.equals(item.getType(), "Collect Report Card"))
            {
                holder.date.setText(item.getToDate());
            }*/


            if (Objects.equals(item.getType(), "Birthday Card"))
            {


                Log.d("type" , item.getType());

                //holder.date.setText(item.getPostDate());

                holder.date.setVisibility(View.GONE);


                if (item.getTime().length() > 0)
                {
                    holder.time.setVisibility(View.VISIBLE);
                }
                else
                {
                    holder.time.setVisibility(View.GONE);
                }



                //holder.time.setText(item.getTime());
                holder.title.setText(item.getType());
                //holder.desc.setText(item.getDetail());

                holder.desc.setVisibility(View.GONE);

                String p = item.getPostDate();

                String to = "";

                for (int i = 0 ; i < item.getTo().size() ; i++)
                {
                    to = to + item.getTo().get(i).getName() + System.getProperty("line.separator");
                }


                Log.d("to" , to);


                holder.to.setText(to);

                try {

                    String p1[] = p.split("-");

                    holder.day.setText(p1[0]);
                    holder.month.setText(p1[1] + " " + p1[2]);

                }catch (Exception e)
                {
                    e.printStackTrace();
                }



            }
            else
            {
                holder.date.setText(item.getToDate());

                holder.date.setVisibility(View.VISIBLE);


                if (item.getTime().length() > 0)
                {
                    holder.time.setVisibility(View.VISIBLE);
                }
                else
                {
                    holder.time.setVisibility(View.GONE);
                }



                holder.time.setText(item.getTime());
                holder.title.setText(item.getType());
                holder.desc.setText(item.getDetail());

                holder.desc.setVisibility(View.VISIBLE);

                String p = item.getFromDate();

                String to = "";

                for (int i = 0 ; i < item.getSection().size() ; i++)
                {

                    to = to + item.getSection().get(i).getClassname() + " " + item.getSection().get(i).getSectionname() + System.getProperty("line.separator");

                }

                holder.to.setText(to);

                try {

                    String p1[] = p.split("-");

                    holder.day.setText(p1[0]);
                    holder.month.setText(p1[1] + " " + p1[2]);

                }catch (Exception e)
                {
                    e.printStackTrace();
                }

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

            TextView day , month , title , desc , date , time , to;



            public ViewHolder(View itemView) {
                super(itemView);

                day = (TextView)itemView.findViewById(R.id.day);
                month = (TextView)itemView.findViewById(R.id.month);
                title = (TextView)itemView.findViewById(R.id.title);
                desc = (TextView)itemView.findViewById(R.id.desc);
                date = (TextView)itemView.findViewById(R.id.date);
                time = (TextView)itemView.findViewById(R.id.time);
                to = (TextView)itemView.findViewById(R.id.to);

                this.setIsRecyclable(false);

            }
        }

    }

}
