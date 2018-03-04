package com.eduschool.eduschoolapp.Events;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.allEventPOJO.EventList;
import com.eduschool.eduschoolapp.allEventPOJO.allEventBean;
import com.eduschool.eduschoolapp.viewEventPOJO.viewEventBean;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 6/14/2017.
 */

public class ViewCalendarFrgmnt extends Fragment {
    Toolbar toolbar;
    MaterialCalendarView calendarView;
    List<CalendarDay> cl;
    ProgressBar progress;

    //TextView type, time, createdBy;
    List<String> socIds;

    List<EventList> list;
    //TextView to;

    RecyclerView eventList;

    List<eventBean> list2;

    GridLayoutManager manager;

    EventAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.events_frgmnt2, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);


        list2 = new ArrayList<>();

        manager = new GridLayoutManager(getContext() , 1);
        //to = (TextView)view.findViewById(R.id.to);

        adapter = new EventAdapter(getContext() , list2);

        eventList = (RecyclerView)view.findViewById(R.id.event_list);

        cl = new ArrayList<>();

        list = new ArrayList<>();

        eventList.setAdapter(adapter);
        eventList.setLayoutManager(manager);

        calendarView = (MaterialCalendarView) view.findViewById(R.id.calender);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        //type = (TextView) view.findViewById(R.id.type);
        //time = (TextView) view.findViewById(R.id.time);
        //createdBy = (TextView) view.findViewById(R.id.created_by);

        socIds = new ArrayList<>();

        int day1 = 8;
        int month1 = 5;
        int year1 = 2018;
        int day = 1;
        int month = 5;
        int year = 2017;

        java.util.Calendar c1 = java.util.Calendar.getInstance();
        java.util.Calendar c = java.util.Calendar.getInstance();

        c1.set(year1, month1, day1);
        c.set(year, month, day);
        Long max = c1.getTime().getTime();
        Long min = c.getTime().getTime();


        //calendarView.setMaxDate(max);
        //calendarView.setMinDate(min);


        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {



                int position = -1;

                list2.clear();

                for (int i = 0; i < list.size(); i++) {


                    try {
                        String[] d1 = list.get(i).getStartDate().split("-");

                        Calendar calendar = Calendar.getInstance();

                        int year = Integer.parseInt(d1[2]);
                        int day = Integer.parseInt(d1[0]);

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(new SimpleDateFormat("MMM").parse(d1[1]));
                        int monthInt = cal.get(Calendar.MONTH);

                        int month = monthInt;

                        //Log.d("monthInt" , String.valueOf(month));

                        //Log.d("asdasd", String.valueOf(day) + " " + String.valueOf(month) + " " + String.valueOf(year));

                        calendar.set(year, month, day);

                        CalendarDay calendarDay = CalendarDay.from(calendar);

                        //Log.d("calendar" , String.valueOf(calendarDay));
                        //Log.d("date" , String.valueOf(date));

                        //Log.d("date" , String.valueOf(calendarDay));




                        eventBean bean = new eventBean();

                        if (Objects.equals(String.valueOf(calendarDay), String.valueOf(date)))
                        {

                            //Log.d("equal" , "entered");

                            position = i;

                            bean.setType(list.get(position).getEventType());
                            bean.setTime(list.get(position).getTime());
                            bean.setCreatedBy(list.get(position).getEventCrateBy());



                            //type.setText(list.get(position).getEventType());
                            //time.setText(list.get(position).getTime());
                            //createdBy.setText(list.get(position).getEventCrateBy());

                            StringBuilder t = new StringBuilder();

                            for (int j = 0 ; j < list.get(position).getSection().size() ; j++)
                            {
                                t.append(list.get(position).getSection().get(j).getClassname()).append(" ").append(list.get(position).getSection().get(j).getSectionname()).append(System.getProperty("line.separator"));
                            }

                            bean.setTo(t.toString());

                            //to.setText(t);


                            list2.add(bean);



                            //Log.d("position" , String.valueOf(i));

                            //Log.d("type" , list.get(i).getEventType());

                            /*type.setText(list.get(i).getEventType());
                            time.setText(list.get(i).getTime());
                            createdBy.setText(list.get(i).getEventCrateBy());

                            String t = "";

                            for (int j = 0 ; j < list.get(i).getSection().size() ; j++)
                            {
                                t = t + list.get(i).getSection().get(j).getClassname() + " " + list.get(i).getSection().get(j).getSectionname() + System.getProperty("line.separator");
                            }

                            to.setText(t);*/
                        }
                        /*else
                        {
                            type.setText("");
                            time.setText("");
                            createdBy.setText("");
                            to.setText("");
                        }
*/

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }



                /*if (position >= 0)
                {

                }
                else {
                    type.setText("");
                    time.setText("");
                    createdBy.setText("");
                    to.setText("");
                }*/


                adapter.setGridData(list2);


            }
        });


        updateCalendar();

        return view;

    }


    public void updateCalendar() {

        progress.setVisibility(View.VISIBLE);

        cl.clear();
        final Calendar calendar = Calendar.getInstance();

        socIds.clear();

        User u = (User) getContext().getApplicationContext();

        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(getContext().getCacheDir(), cacheSize);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);

        Call<allEventBean> call = cr.getAllEvents(u.school_id, "Teacher", u.user_id);


        call.enqueue(new Callback<allEventBean>() {
            @Override
            public void onResponse(Call<allEventBean> call, Response<allEventBean> response) {

                cl.clear();

                list.clear();

                list = response.body().getEventList();

                for (int i = 0; i < response.body().getEventList().size(); i++) {


                    socIds.add(response.body().getEventList().get(i).getId());


                    String dat = response.body().getEventList().get(i).getStartDate();

                    //Log.d("adsad", dat);


                    try {
                        String[] d1 = dat.split("-");


                        int year = Integer.parseInt(d1[2]);
                        int day = Integer.parseInt(d1[0]);

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(new SimpleDateFormat("MMM").parse(d1[1]));
                        int monthInt = cal.get(Calendar.MONTH);

                        int month = monthInt;

                        //Log.d("monthInt" , String.valueOf(month));

                        //Log.d("asdasd", String.valueOf(day) + " " + String.valueOf(month) + " " + String.valueOf(year));

                        calendar.set(year, month, day);

                        CalendarDay calendarDay = CalendarDay.from(calendar);

                        //Log.d("calendarDay" , String.valueOf(calendarDay));

                        cl.add(calendarDay);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                progress.setVisibility(View.GONE);

                calendarView.addDecorators(new EventDecorator(Color.RED, cl));

            }

            @Override
            public void onFailure(Call<allEventBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


    }


    private class EventDecorator implements DayViewDecorator {

        private final int color;
        private final HashSet<CalendarDay> dates;

        public EventDecorator(int color, Collection<CalendarDay> dates) {
            this.color = color;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(15, color));
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Events");
        User u = (User) getContext().getApplicationContext();

        u.back = false;

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = ((TeacherHome) getContext()).getSupportFragmentManager();
                fm.popBackStack();

            }
        });

    }



    class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>
    {

        Context context;
        List<eventBean> list = new ArrayList<>();

        public EventAdapter(Context context , List<eventBean> list)
        {
            this.context = context;
            this.list = list;
        }

        public void setGridData(List<eventBean> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.event_list_model , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            eventBean item = list.get(position);

            holder.type.setText(item.getType());
            holder.time.setText(item.getTime());
            holder.createdBy.setText(item.getCreatedBy());
            holder.to.setText(item.getTo());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {

            TextView type , time , createdBy , to;

            public ViewHolder(View itemView) {
                super(itemView);

                type = (TextView)itemView.findViewById(R.id.type);
                time = (TextView)itemView.findViewById(R.id.time);
                createdBy = (TextView)itemView.findViewById(R.id.created_by);
                to = (TextView)itemView.findViewById(R.id.to);

            }
        }

    }



}
