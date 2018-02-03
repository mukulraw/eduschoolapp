package com.eduschool.eduschoolapp.Notifications;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassWork.ClassWorkFrgmnt;
import com.eduschool.eduschoolapp.ClassWork.Teacherclswrk;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.Survey.SurveyFrgmnt1;
import com.eduschool.eduschoolapp.Survey.SurveyFrgmntParent1;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.notificationsPOJO.NotificationList;
import com.eduschool.eduschoolapp.notificationsPOJO.notificationsBean;

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
 * Created by user on 6/30/2017.
 */

public class ParentNotificationFrgmnt extends Fragment{
    Toolbar toolbar;
    RecyclerView grid;
    ProgressBar progress;
    GridLayoutManager manager;
    List<NotificationList> list;
    NotificationsAdapter adapter;


    public ParentNotificationFrgmnt() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.teacher_notificion_frgmnt, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);

        list = new ArrayList<>();

        manager = new GridLayoutManager(getContext() , 1);
        grid = (RecyclerView)v.findViewById(R.id.grid);
        progress = (ProgressBar)v.findViewById(R.id.progress);

        adapter = new NotificationsAdapter(getContext() , list);

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


        Call<notificationsBean> call = cr.getNotifications(u.school_id , u.user_id);

        call.enqueue(new Callback<notificationsBean>() {
            @Override
            public void onResponse(Call<notificationsBean> call, Response<notificationsBean> response) {

                //adapter.setGridData(response.body().getNotificationList());

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<notificationsBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });




        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Notifications");

        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }



    public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder>
    {
        Context context;
        List<NotificationList> list = new ArrayList<>();

        public NotificationsAdapter(Context context , List<NotificationList> list)
        {
            this.context = context;
            this.list = list;
        }

        public void setGridData(List<NotificationList> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.notification_list_model , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final NotificationList item = list.get(position);


            holder.type.setText(item.getType() + " Notification");

            if (Objects.equals(item.getType(), "Survey"))
            {
                String d[] = item.getDates().split(" ");
                holder.date.setText(d[0] + "\n" + d[1] + " " + d[2]);
                holder.text.setText("Survey added on " + item.getDates());
            }
            else if (Objects.equals(item.getType(), "Classwork"))
            {
                holder.date.setText(item.getData().getSection() + " " + item.getData().getSection());
                //holder.text.setText(item.getData().getClassStatus());
            }


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Objects.equals(item.getType(), "Survey"))
                    {
                        FragmentManager fm = ((ParentHome)context).getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        SurveyFrgmntParent1 frag1 = new SurveyFrgmntParent1();
                        ft.replace(R.id.replace, frag1);
                        ft.commit();
                    }
                    else if (Objects.equals(item.getType(), "Classwork"))
                    {
                        FragmentManager fm = ((ParentHome)context).getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ClassWorkFrgmnt frag1 = new ClassWorkFrgmnt();
                        ft.replace(R.id.replace, frag1);
                        ft.commit();
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
            TextView type , date , text;

            public ViewHolder(View itemView) {
                super(itemView);

                type = (TextView)itemView.findViewById(R.id.type);
                date = (TextView)itemView.findViewById(R.id.date);
                text = (TextView)itemView.findViewById(R.id.text);

            }
        }

    }


}
