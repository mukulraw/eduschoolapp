package eskool.com.eskoolapp.Events;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import eskool.com.eskoolapp.Attendance.AttendanceFrgmnt2;
import eskool.com.eskoolapp.Home.TeacherHome;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.User;

import static java.util.Calendar.AM_PM;

/**
 * Created by user on 6/15/2017.
 */

public class CreateEventFrgmnt extends Fragment {


    Toolbar toolbar;
    TextView date, time1, time2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.create_event, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        date = (TextView) view.findViewById(R.id.date);
        time1 = (TextView) view.findViewById(R.id.time1);
        time2 = (TextView) view.findViewById(R.id.time2);



        date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment2();
                newFragment.show(getActivity().getFragmentManager(), "df");
            }
        });

        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getActivity().getFragmentManager(), "TimePicker");

            }
        });

        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TimePickerFragment1();
                newFragment.show(getActivity().getFragmentManager(), "TimePicker");

            }
        });


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Create Event");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }

    @SuppressLint("ValidFragment")
    public class DatePickerFragment2 extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int day) {
            String years = "" + year;
            String months = "" + (monthOfYear + 1);
            String days = "" + day;

            if (monthOfYear >= 0 && monthOfYear < 9) {
                months = "0" + (monthOfYear + 1);
            }
            if (day > 0 && day < 10) {
                days = "0" + day;

            }
            date.setText(days + "/" + months + "/" + years);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //use the current date as the default date in the picker
            Calendar c = Calendar.getInstance();
            @SuppressLint("WrongConstant") int year = c.get(Calendar.YEAR);
            @SuppressLint("WrongConstant") int month = c.get(Calendar.MONTH);
            @SuppressLint("WrongConstant") int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = null;
            datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);

            return datePickerDialog;
        }

    }

    class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //Use the current time as the default values for the time picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            String AM_PM;
            if (hour < 12) {
                AM_PM = "AM";
            } else {
                AM_PM = "PM";
            }


            //Create and return a new instance of TimePickerDialog
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        private String getTime(int hr, int min) {
            Time tme = new Time(hr, min, 0);//seconds by default set to zero
            Format formatter;
            formatter = new SimpleDateFormat("h:mm a");
            return formatter.format(tme);
        }

        //onTimeSet() callback method
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            //Do something with the user chosen time
            //Get reference of host activity (XML Layout File) TextView widget

            //Set a message for user

            //Display the user changed time on TextView
            time1.setText(getTime(hourOfDay, minute));

        }
    }


    class TimePickerFragment1 extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //Use the current time as the default values for the time picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            String AM_PM;
            if (hour < 12) {
                AM_PM = "AM";
            } else {
                AM_PM = "PM";
            }


            //Create and return a new instance of TimePickerDialog
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        private String getTime(int hr, int min) {
            Time tme = new Time(hr, min, 0);//seconds by default set to zero
            Format formatter;
            formatter = new SimpleDateFormat("h:mm a");
            return formatter.format(tme);
        }

        //onTimeSet() callback method
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            //Do something with the user chosen time
            //Get reference of host activity (XML Layout File) TextView widget

            //Set a message for user

            //Display the user changed time on TextView
            time2.setText(getTime(hourOfDay, minute));

        }
    }

}

