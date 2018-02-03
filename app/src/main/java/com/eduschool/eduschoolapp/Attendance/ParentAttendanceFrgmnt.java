package com.eduschool.eduschoolapp.Attendance;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.ParentAttendancePOJO.AttendanceList;
import com.eduschool.eduschoolapp.ParentAttendancePOJO.paraneAttendanceBean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.TodaysAttendanceSummaryPOJO.todayBean;
import com.eduschool.eduschoolapp.User;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.imanoweb.calendarview.CustomCalendarView;
import com.imanoweb.calendarview.DayDecorator;
import com.imanoweb.calendarview.DayView;
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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by User on 5/6/2017.
 */

public class ParentAttendanceFrgmnt extends Fragment {
    Toolbar toolbar;
    MaterialCalendarView calendarView;
    Calendar currentCalendar;
    List<AttendanceList> list;
    ProgressBar progress;
    TextView reason;
    TextView stuName;
    TextView date, month , className;


    String[] mon = {
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
            "Aug",
            "Sep",
            "Oct",
            "Nov",
            "Dec",
    };

    String date1;
    public ParentAttendanceFrgmnt() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.parent_attendance1, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);

        stuName = (TextView)view.findViewById(R.id.stu_name);

        date = (TextView)view.findViewById(R.id.date);
        month = (TextView)view.findViewById(R.id.month);
        className = (TextView)view.findViewById(R.id.class_name);


        date1 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());


        String[] d1 = date1.split("-");

        date.setText(d1[2]);

        month.setText(mon[Integer.parseInt(d1[1]) - 1] + " " + d1[0]);


        reason = (TextView)view.findViewById(R.id.reason);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        list = new ArrayList<>();

        calendarView = (MaterialCalendarView) view.findViewById(R.id.calendar);


        java.util.Calendar c1 = java.util.Calendar.getInstance();
        java.util.Calendar c = java.util.Calendar.getInstance();



        //calendarView.setMaxDate(max);
        //calendarView.setMinDate(min);


        currentCalendar = Calendar.getInstance(Locale.getDefault());


        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {

                progress.setVisibility(View.VISIBLE);

                Log.d("asdasd" , String.valueOf(calendarDay.getDay()));

                User u = (User) getContext().getApplicationContext();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(u.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllAPIs cr = retrofit.create(AllAPIs.class);

                String day = "";

                if (calendarDay.getDay() < 10)
                {
                    day = "0" + String.valueOf(calendarDay.getDay());
                }else
                {
                    day = String.valueOf(calendarDay.getDay());
                }


                String dat = day + "-" + mon[calendarDay.getMonth()] + "-" + calendarDay.getYear();

                Log.d("asdasd" , mon[calendarDay.getMonth()]);

                Call<todayBean> call = cr.getTodaysAttendance(u.school_id , u.user_class , u.user_section , dat , u.user_id);

                call.enqueue(new Callback<todayBean>() {
                    @Override
                    public void onResponse(Call<todayBean> call, Response<todayBean> response) {

                        try {

                            if (response.body().getAttendanceSummary().size() > 0)
                            {
                                reason.setText(response.body().getAttendanceSummary().get(0).getAttendanceValue());
                            }
                            else
                            {
                                reason.setText("");
                            }

                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }



                        progress.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<todayBean> call, Throwable t) {
                        Log.d("asdasd" , t.toString());
                        progress.setVisibility(View.GONE);

                    }
                });

            }
        });

//Show/hide overflow days of a month
        //calendarView.setShowOverflowDate(false);

//call refreshCalendar to update calendar the view
        //calendarView.refreshCalendar(currentCalendar);

        DayDecorator d = new DayDecorator() {
            @Override
            public void decorate(DayView dayView) {

                dayView.decorate();

            }
        };




        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Attendance");
        User u = (User) getContext().getApplicationContext();
        u.back = true;


        updateCalendar();




    }


    public void updateCalendar()
    {

        final List<CalendarDay> cl = new ArrayList<>();
        final Calendar calendar = Calendar.getInstance();

        User u = (User) getContext().getApplicationContext();

        stuName.setText("Name of Student - " + u.studName);

        className.setText(u.class_Name + " " + u.section_Name);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);


        String[] d1 = date1.split("-");


        Call<paraneAttendanceBean> call = cr.getAttendance(u.school_id , u.user_class , u.user_section , mon[Integer.parseInt(d1[1]) - 1] , d1[0] , u.user_id);

        call.enqueue(new Callback<paraneAttendanceBean>() {
            @Override
            public void onResponse(Call<paraneAttendanceBean> call, Response<paraneAttendanceBean> response) {


                for (int i = 0 ; i < response.body().getAttendanceList().get(0).getAttendanceData().size() ; i++) {


                    try {

                        String dd = response.body().getAttendanceList().get(0).getAttendanceData().get(i).getAttendanceDate();

                        String d1[] = dd.split("-");

                        int year = Integer.parseInt(d1[2]);
                        int day = Integer.parseInt(d1[0]);


                        int month1 = 0;
                        for (int j = 0; j < mon.length; j++) {
                            if (Objects.equals(d1[1], mon[j])) {
                                month1 = j;
                            }

                        }

                        //Log.d("asdasd", String.valueOf(day) + " " + String.valueOf(month1) + " " + String.valueOf(year));

                        calendar.set(year, month1, day);

                        CalendarDay calendarDay = CalendarDay.from(calendar);
                        cl.add(calendarDay);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }



                }


                calendarView.addDecorators(new EventDecorator(Color.RED, cl));


            }

            @Override
            public void onFailure(Call<paraneAttendanceBean> call, Throwable t) {

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
            view.addSpan(new DotSpan(5, color));
        }
    }

}