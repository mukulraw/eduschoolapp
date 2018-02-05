package com.eduschool.eduschoolapp.Notifications;

import android.app.VoiceInteractor;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Attendance.Attendance;
import com.eduschool.eduschoolapp.ClassWork.Teacherclswrk;
import com.eduschool.eduschoolapp.Communication.Frgmnt1;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.HomeWork.TeacherHw;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.Survey.SurveyFrgmnt1;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.notiBean;
import com.eduschool.eduschoolapp.notificationsPOJO.NotificationList;
import com.eduschool.eduschoolapp.notificationsPOJO.notificationsBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class TeacherNotificationFrgmnt extends Fragment {
    Toolbar toolbar;
    RecyclerView grid;
    ProgressBar progress;
    GridLayoutManager manager;
    //List<NotificationList> list;
    List<notiBean> list;
    NotificationsAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.teacher_notificion_frgmnt, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

        list = new ArrayList<>();

        manager = new GridLayoutManager(getContext(), 1);
        grid = (RecyclerView) v.findViewById(R.id.grid);
        progress = (ProgressBar) v.findViewById(R.id.progress);

        adapter = new NotificationsAdapter(getContext(), list);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);


        progress.setVisibility(View.VISIBLE);


        final User u = (User) getContext().getApplicationContext();


        /*RequestQueue requestQueue = Volley.newRequestQueue(getContext());


        StringRequest request = new StringRequest(Request.Method.POST, "http://eduschoolapp.com/eduschool_app/all_notificationteacher.php", new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                list.clear();


                try {


                    JSONObject object = new JSONObject(response);

                    JSONArray notiArray = object.getJSONArray("notification_list");

                    for (int i = 0; i < notiArray.length(); i++) {

                        notiBean bean = new notiBean();

                        JSONObject data = notiArray.getJSONObject(i);

                        String type = data.getString("type") + " " + "Notification";

                        bean.setType(type);

                        String date = data.getString("dates");

                        bean.setDate(date);


                        if (Objects.equals(type, "Survey")) {
                            JSONObject data1 = data.getJSONObject("data");
                            String title = data1.getString("survey_title");
                            bean.setTitle(title);
                            bean.setDesc("");
                        } else if (Objects.equals(type, "Classwork")) {
                            JSONObject data1 = data.getJSONObject("data");
                            String title = data1.getString("title");
                            bean.setTitle(title);
                            bean.setDesc("Pending");
                        } else if (Objects.equals(type, "Homework")) {
                            if (Objects.equals(type, "Homework")) {

                                JSONObject data1 = data.getJSONObject("data");
                                String title = data1.getString("title");
                                bean.setTitle(title);
                                bean.setDesc("Due Date");
                            } else if (Objects.equals(type, "Homework Complete")) {

                                JSONObject data1 = data.getJSONObject("data");
                                String title = data1.getString("title");
                                String desc = data1.getString("status");
                                bean.setTitle(title);
                                bean.setDesc(desc);
                            }
                        } else if (Objects.equals(type, "Attendance")) {
                            JSONObject data1 = data.getJSONObject("data");
                            String title = data1.getString("title");
                            String desc = data1.getString("status");
                            bean.setTitle(title);
                            bean.setDesc(desc);
                        } else if (Objects.equals(type, "Communication")) {
                            JSONObject data1 = data.getJSONObject("data");
                            String title = data1.getString("type");
                            String desc = data1.getString("from_name");
                            bean.setTitle(title);
                            bean.setDesc(desc);
                        }


                        list.add(bean);


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


                progress.setVisibility(View.GONE);

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                progress.setVisibility(View.GONE);


                Log.d("Error", error.getMessage());


            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("school_id", u.school_id);
                params.put("teacher_id", u.user_id);

                return params;
            }
        };

        request.setRetryPolicy(
                new DefaultRetryPolicy(
                        500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
        );


        requestQueue.add(request);*/
        //Volley.getInstance().addToRequestQueue(strreq);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);


        Call<notificationsBean> call = cr.getNotifications(u.school_id, u.user_id);

        call.enqueue(new Callback<notificationsBean>() {
            @Override
            public void onResponse(Call<notificationsBean> call, Response<notificationsBean> response) {


                list.clear();



                for (int i = 0 ; i < response.body().getHomeworkDue().size() ; i++)
                {
                    notiBean bean = new notiBean();

                    bean.setDate(response.body().getHomeworkDue().get(i).getDueDate());
                    bean.setTitle(response.body().getHomeworkDue().get(i).getTitle());
                    bean.setType("Homework Notification");
                    bean.setDesc("Due Date");

                    list.add(bean);
                }


                for (int i = 0; i < response.body().getOther().size(); i++) {


                    String type = response.body().getOther().get(i).getType();

                    if (Objects.equals(type, "Communication"))
                    {


                        try {

                            notiBean bean = new notiBean();

                            bean.setDate(response.body().getOther().get(i).getDates());
                            //bean.setTitle(response.body().getHomeworkDue().get(i).getTitle());
                            bean.setType(response.body().getOther().get(i).getType() + "Notification");

                            JSONObject object = new JSONObject(response.body().getOther().get(i).getData().toString());

                            String name = object.getString("from_name");
                            String clas = object.getString("class");
                            String sec = object.getString("section");

                            String title = name + " (" + clas + " " + sec + ")";

                            bean.setTitle(title);

                            String desc = object.getString("type");

                            bean.setDesc(desc);

                            list.add(bean);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                    else if (Objects.equals(type, "Attendance"))
                    {

                        try {

                            notiBean bean = new notiBean();

                            bean.setDate(response.body().getOther().get(i).getDates());
                            //bean.setTitle(response.body().getHomeworkDue().get(i).getTitle());
                            bean.setType(response.body().getOther().get(i).getType() + "Notification");
                            bean.setDesc("Attendance Pending");

                            JSONObject object = new JSONObject(response.body().getOther().get(i).getData().toString());

                            String clas = object.getString("class");
                            String sec = object.getString("section");

                            bean.setTitle(clas + " " + sec);

                            list.add(bean);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }



                }


                for (int i = 0; i < response.body().getClassworkUnupdate().size(); i++) {
                    notiBean bean = new notiBean();

                    bean.setDate(response.body().getClassworkUnupdate().get(i).getDatetime());
                    //bean.setTitle(response.body().getHomeworkDue().get(i).getTitle());
                    bean.setType("Classwork Notification");
                    bean.setDesc("Classwork not updated");

                    list.add(bean);
                }


                for (int i = 0 ; i < response.body().getBirthdayNotify().size() ; i++)
                {
                    notiBean bean = new notiBean();

                    bean.setType("Birthday Notification");
                    bean.setTitle(response.body().getBirthdayNotify().get(i).getUserName() + " (" + response.body().getBirthdayNotify().get(i).getUserType() + ")");
                    bean.setDate(response.body().getBirthdayNotify().get(i).getBirthDate());
                    bean.setDesc(response.body().getBirthdayNotify().get(i).getClass_() + " " + response.body().getBirthdayNotify().get(i).getSection());

                    list.add(bean);
                }




                adapter.setGridData(list);

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

            /*holder.type.setText(item.getType() + " Notification");

            if (Objects.equals(item.getType(), "Survey")) {

                try {

                    String d[] = item.getDate().split("-");
                    holder.date.setText(d[0] + "\n\n" + d[1] + " " + d[2]);
                    holder.text.setText(item.getTitle());

                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else if (Objects.equals(item.getType(), "Classwork")) {
                try {

                    String d[] = item.getDate().split("-");
                    holder.date.setText(d[0] + "\n\n" + d[1] + " " + d[2]);
                    holder.text.setText(item.getTitle());
                    holder.status.setText(item.getDesc());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (Objects.equals(item.getType(), "Homework")) {
                try {

                    if (Objects.equals(item.getType(), "Homework")) {
                        String d[] = item.getDate().split("-");
                        holder.date.setText(d[0] + "\n\n" + d[1] + " " + d[2]);
                        holder.text.setText(item.getTitle());
                        holder.status.setText("Due Date");
                    } else if (Objects.equals(item.getType(), "Homework Complete")) {
                        String d[] = item.getDate().split("-");
                        holder.date.setText(d[0] + "\n\n" + d[1] + " " + d[2]);
                        holder.text.setText(item.getTitle());
                        holder.status.setText(item.getDesc());
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (Objects.equals(item.getType(), "Attendance")) {
                try {

                    String d[] = item.getDate().split("-");
                    holder.date.setText(d[0] + "\n\n" + d[1] + " " + d[2]);
                    holder.text.setText(item.getTitle());
                    holder.status.setText(item.getDesc());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (Objects.equals(item.getType(), "Communication")) {
                try {

                    String d[] = item.getDate().split("-");
                    holder.date.setText(d[0] + "\n\n" + d[1] + " " + d[2]);
                    holder.text.setText(item.getTitle());
                    holder.status.setText(item.getDesc());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }*/


            /*try {

                if (item.getDesc().length() > 0) {
                    holder.status.setVisibility(View.VISIBLE);

                } else {
                    holder.status.setVisibility(View.GONE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            String d[] = item.getDate().split("-");
            holder.date.setText(d[0] + "\n\n" + d[1] + " " + d[2]);

            holder.type.setText(item.getType());

            holder.text.setText(item.getTitle());
            holder.status.setText(item.getDesc());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Objects.equals(item.getType(), "Survey")) {
                        FragmentManager fm = ((TeacherHome) context).getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        SurveyFrgmnt1 frag1 = new SurveyFrgmnt1();
                        ft.replace(R.id.replace, frag1);
                        ft.commit();
                    } else if (Objects.equals(item.getType(), "Classwork") || Objects.equals(item.getType(), "Classwork Update")) {
                        FragmentManager fm = ((TeacherHome) context).getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Teacherclswrk frag1 = new Teacherclswrk();
                        ft.replace(R.id.replace, frag1);
                        ft.commit();
                    } else if (Objects.equals(item.getType(), "Homework") || Objects.equals(item.getType(), "Homework Update") || Objects.equals(item.getType(), "Homework Complete")) {
                        FragmentManager fm = ((TeacherHome) context).getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        TeacherHw frag1 = new TeacherHw();
                        ft.replace(R.id.replace, frag1);
                        ft.commit();
                    } else if (Objects.equals(item.getType(), "Attendance")) {
                        FragmentManager fm = ((TeacherHome) context).getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Attendance frag1 = new Attendance();
                        ft.replace(R.id.replace, frag1);
                        ft.commit();
                    } else if (Objects.equals(item.getType(), "Communication")) {
                        FragmentManager fm = ((TeacherHome) context).getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Frgmnt1 frag1 = new Frgmnt1();
                        ft.replace(R.id.replace, frag1);
                        ft.commit();
                    }

                }
            });*/


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

