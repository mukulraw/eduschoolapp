package com.eduschool.eduschoolapp.Notifications;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.eduschool.eduschoolapp.HomeWork.HomeWorkFrgmnt;
import com.eduschool.eduschoolapp.Library.ParentLibrary1;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.RaiseRequest.RaiseReqquestHome;
import com.eduschool.eduschoolapp.Survey.SurveyFrgmnt1;
import com.eduschool.eduschoolapp.Survey.SurveyFrgmntParent1;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.allNotificationPOJO.allNotificationBean;
import com.eduschool.eduschoolapp.notiBean;
import com.eduschool.eduschoolapp.notificationsPOJO.NotificationList;
import com.eduschool.eduschoolapp.notificationsPOJO.notificationsBean;
import com.eduschool.eduschoolapp.updateNotificationBean;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    List<notiBean> list;
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


        User u = (User)getContext().getApplicationContext();

        progress.setVisibility(View.VISIBLE);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);


        Call<allNotificationBean> call = cr.getAllNotifications(u.school_id, u.user_id);

        call.enqueue(new Callback<allNotificationBean>() {
            @Override
            public void onResponse(Call<allNotificationBean> call, Response<allNotificationBean> response) {


                try {
                    list.clear();



                    for (int i = 0 ; i < response.body().getHomeworkDue().size() ; i++)
                    {
                        notiBean bean = new notiBean();

                        bean.setDate(response.body().getHomeworkDue().get(i).getDueDate());
                        bean.setTitle(response.body().getHomeworkDue().get(i).getTitle());
                        bean.setType("Homework Notification");
                        bean.setDesc("Due Date");

                        bean.setData(response.body().getHomeworkDue().get(i).getNotifyId());
                        if (bean.getDate() != null)
                        {
                            list.add(bean);
                        }

                    }

                    for (int i = 0 ; i < response.body().getHomeworkUpdate().size() ; i++)
                    {
                        notiBean bean = new notiBean();

                        bean.setDate(response.body().getHomeworkUpdate().get(i).getCreatedDate());
                        bean.setTitle(response.body().getHomeworkUpdate().get(i).getTitle());
                        bean.setType("Homework Notification");
                        bean.setDesc("Updated");

                        bean.setData(response.body().getHomeworkUpdate().get(i).getNotifyId());
                        if (bean.getDate() != null)
                        {
                            list.add(bean);
                        }
                    }

                    for (int i = 0 ; i < response.body().getHomeworkCreate().size() ; i++)
                    {
                        notiBean bean = new notiBean();

                        bean.setDate(response.body().getHomeworkCreate().get(i).getCreatedDate());
                        bean.setTitle(response.body().getHomeworkCreate().get(i).getTitle());
                        bean.setType("Homework Notification");
                        bean.setDesc("Added");

                        bean.setData(response.body().getHomeworkCreate().get(i).getNotifyId());
                        if (bean.getDate() != null)
                        {
                            list.add(bean);
                        }
                    }



                    for (int i = 0; i < response.body().getClassworkCreate().size(); i++) {
                        notiBean bean = new notiBean();

                        bean.setDate(response.body().getClassworkCreate().get(i).getCreateDate());
                        bean.setTitle(response.body().getClassworkCreate().get(i).getTitle());
                        bean.setType("Classwork Notification");
                        bean.setDesc(response.body().getClassworkCreate().get(i).getClassStatus());

                        bean.setData(response.body().getClassworkCreate().get(i).getNotifyId());
                        if (bean.getDate() != null)
                        {
                            list.add(bean);
                        }
                    }

                    for (int i = 0; i < response.body().getClassworkUpdate().size(); i++) {
                        notiBean bean = new notiBean();

                        bean.setDate(response.body().getClassworkUpdate().get(i).getCreateDate());
                        bean.setTitle(response.body().getClassworkUpdate().get(i).getTitle());
                        bean.setType("Classwork Notification");
                        bean.setDesc("Classwork Updated");

                        bean.setData(response.body().getClassworkUpdate().get(i).getNotifyId());
                        if (bean.getDate() != null)
                        {
                            list.add(bean);
                        }
                    }

                    for (int i = 0 ; i < response.body().getBirthdayNotify().size() ; i++)
                    {
                        notiBean bean = new notiBean();

                        bean.setType("Birthday Notification");
                        bean.setTitle(response.body().getBirthdayNotify().get(i).getUserName() + " (" + response.body().getBirthdayNotify().get(i).getUserType() + ")");
                        bean.setDate(response.body().getBirthdayNotify().get(i).getBirthDate());
                        bean.setDesc(response.body().getBirthdayNotify().get(i).getClass_() + " " + response.body().getBirthdayNotify().get(i).getSection());

                        bean.setData(response.body().getBirthdayNotify().get(i).getNotifyId());
                        if (bean.getDate() != null)
                        {
                            list.add(bean);
                        }
                    }



                    for (int i = 0 ; i < response.body().getCommunication().size() ; i++)
                    {
                        notiBean bean = new notiBean();

                        bean.setType("Communication Notification");
                        bean.setTitle(response.body().getCommunication().get(i).getType());
                        bean.setDate(response.body().getCommunication().get(i).getPostDate());
                        bean.setDesc(response.body().getCommunication().get(i).getDetail());
                        bean.setData(response.body().getCommunication().get(i).getNotifyId());
                        if (bean.getDate() != null)
                        {
                            list.add(bean);
                        }
                    }



                    for (int i = 0 ; i < response.body().getSurvey().size() ; i++)
                    {
                        notiBean bean = new notiBean();

                        bean.setType("Survey Notification");
                        bean.setTitle("New Survey Added (" + response.body().getSurvey().get(i).getSurveyTitle() + ")");
                        bean.setDate(response.body().getSurvey().get(i).getCreatedOn());
                        bean.setDesc("Total Questions - " + response.body().getSurvey().get(i).getTotalQuestion());

                        bean.setData(response.body().getSurvey().get(i).getNotifyId());
                        if (bean.getDate() != null)
                        {
                            list.add(bean);
                        }
                    }


                    for (int i = 0 ; i < response.body().getLibraryBookreturn().size() ; i++)
                    {
                        notiBean bean = new notiBean();



                        bean.setType("Library Notification");
                        bean.setTitle(response.body().getLibraryBookreturn().get(i).getBookName());
                        bean.setDate(response.body().getLibraryBookreturn().get(i).getReturnDate());
                        bean.setDesc("Issue Date - " + response.body().getLibraryBookreturn().get(i).getIssueDate());

                        bean.setData(response.body().getLibraryBookreturn().get(i).getNotifyId());

                        if (bean.getDate() != null)
                        {
                            list.add(bean);
                        }
                    }


                    Collections.sort(list, new Comparator<notiBean>() {
                        @Override
                        public int compare(notiBean o1, notiBean o2) {





                            if (o1.getDate().length() == 0 || o2.getDate().length() == 0)
                                return 0;


                            return o2.getDate().compareTo(o1.getDate());


                        }
                    });


                    Log.d("notification Count" , String.valueOf(list.size()));

                    adapter.setGridData(list);

                } catch (Exception e)
                {
                    e.printStackTrace();
                }



                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<allNotificationBean> call, Throwable t) {
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



    public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {
        Context context;
        List<notiBean> list = new ArrayList<>();

        public NotificationsAdapter(Context context, List<notiBean> list) {
            this.context = context;
            this.list = list;
        }

        public void setGridData(List<notiBean> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.notification_list_model, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.setIsRecyclable(true);

            final notiBean item = list.get(position);



            try {

                String d[] = item.getDate().split("-");

                holder.date.setText(d[0] + "\n\n" + d[1] + " " + d[2]);

            }catch (Exception e)
            {
                e.printStackTrace();
            }


            holder.text.setText(item.getTitle());
            holder.type.setText(item.getType());
            holder.status.setText(item.getDesc());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    progress.setVisibility(View.VISIBLE);

                    User u = (User)getContext().getApplicationContext();


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(u.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllAPIs cr = retrofit.create(AllAPIs.class);


                    Call<updateNotificationBean> call = cr.updateParentNotifications(item.getData() , u.user_id , "Parent");

                    call.enqueue(new Callback<updateNotificationBean>() {
                        @Override
                        public void onResponse(Call<updateNotificationBean> call, Response<updateNotificationBean> response) {



                            if (Objects.equals(item.getType(), "Homework Notification"))
                            {
                                android.support.v4.app.FragmentManager fm=((AppCompatActivity)context).getSupportFragmentManager();
                                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                                HomeWorkFrgmnt frag1 = new HomeWorkFrgmnt();
                                ft.replace(R.id.replace, frag1);
                                //ft.addToBackStack(null);
                                ft.commit();
                            }
                            else if (Objects.equals(item.getType(), "Classwork Notification"))
                            {
                                android.support.v4.app.FragmentManager fm=((AppCompatActivity)context).getSupportFragmentManager();
                                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                                ClassWorkFrgmnt frag1 = new ClassWorkFrgmnt();
                                ft.replace(R.id.replace, frag1);
                                //ft.addToBackStack(null);
                                ft.commit();
                            }
                            else if (Objects.equals(item.getType(), "Communication Notification"))
                            {
                                android.support.v4.app.FragmentManager fm=((AppCompatActivity)context).getSupportFragmentManager();
                                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                                RaiseReqquestHome frag1 = new RaiseReqquestHome();
                                ft.replace(R.id.replace, frag1);
                                //ft.addToBackStack(null);
                                ft.commit();
                            }
                            else if (Objects.equals(item.getType(), "Survey Notification"))
                            {
                                android.support.v4.app.FragmentManager fm=((AppCompatActivity)context).getSupportFragmentManager();
                                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                                SurveyFrgmntParent1 frag1 = new SurveyFrgmntParent1();
                                ft.replace(R.id.replace, frag1);
                                //ft.addToBackStack(null);
                                ft.commit();
                            }
                            else if (Objects.equals(item.getType(), "Library Notification"))
                            {
                                android.support.v4.app.FragmentManager fm=((AppCompatActivity)context).getSupportFragmentManager();
                                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                                ParentLibrary1 frag1 = new ParentLibrary1();
                                ft.replace(R.id.replace, frag1);
                                //ft.addToBackStack(null);
                                ft.commit();
                            }



                        }

                        @Override
                        public void onFailure(Call<updateNotificationBean> call, Throwable t) {

                        }
                    });




                }
            });


        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView type, date, text, status;

            public ViewHolder(View itemView) {
                super(itemView);

                type = (TextView) itemView.findViewById(R.id.type);
                date = (TextView) itemView.findViewById(R.id.date);
                text = (TextView) itemView.findViewById(R.id.text);
                status = (TextView) itemView.findViewById(R.id.status);

            }
        }

    }

}
