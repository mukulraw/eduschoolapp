package eskool.com.eskoolapp.Events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import eskool.com.eskoolapp.Home.TeacherHome;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.User;

/**
 * Created by user on 6/14/2017.
 */

public class ViewCalendarFrgmnt extends Fragment {
    Toolbar toolbar;
    CalendarView calendarView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.events_frgmnt2, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

        calendarView = (CalendarView) view.findViewById(R.id.calender);

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


        calendarView.setMaxDate(max);
        calendarView.setMinDate(min);



        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Events");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}
