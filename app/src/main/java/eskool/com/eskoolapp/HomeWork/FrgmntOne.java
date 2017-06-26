package eskool.com.eskoolapp.HomeWork;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;


import java.util.Calendar;

import eskool.com.eskoolapp.Attendance.AttendanceFrgmnt2;
import eskool.com.eskoolapp.Home.TeacherHome;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.User;

/**
 * Created by user on 5/20/2017.
 */

public class FrgmntOne extends Fragment implements DatePickerDialog.OnDateSetListener {
    Toolbar toolbar;
    TextView className, sectionName, date;
    AlertDialog.Builder alertDialog;
    View convertView;

    public FrgmntOne() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.teacher_hw_frgmnt1, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);


        className = (TextView) v.findViewById(R.id.className);
        date = (TextView) v.findViewById(R.id.date);
        sectionName = (TextView) v.findViewById(R.id.sectonName);
        final String classes[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "12"};
        final String section[] = {"A", "B", "C", "D", "E", "F", "G", "H"};
        final String status[] = {"Status", "a", "sdcs", "sds", "sdd", "sdf", "sd", "sdfd"};
        alertDialog = new AlertDialog.Builder(this.getActivity());
        convertView = (View) inflater.inflate(R.layout.custom, null);

        ListView lv = (ListView) convertView.findViewById(R.id.listView1);
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, classes);
        lv.setAdapter(adapter1);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.spinnertext, status);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        className.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Select Class");
                builder.setItems(classes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        className.setText(classes[item]);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });



        sectionName.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Select Section");
                builder.setItems(section, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        sectionName.setText(section[item]);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });



        date.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                android.app.DialogFragment newFragment = new DatePickerFragment2();
                newFragment.show(getActivity().getFragmentManager(), "df");




            }
        });



        return v;

    }

    @SuppressLint("ValidFragment")
    public  class DatePickerFragment2 extends android.app.DialogFragment implements    DatePickerDialog.OnDateSetListener{
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int  day) {
            String years=""+year;
            String months=""+(monthOfYear+1);
            String days=""+day;
            if(monthOfYear>=0 && monthOfYear<9){
                months="0"+(monthOfYear+1);
            }
            if(day>0 && day<10){
                days="0"+day;

            }
            date.setText(days+"/"+months+"/"+years);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //use the current date as the default date in the picker
            Calendar c=Calendar.getInstance();
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog=null;
            datePickerDialog=new DatePickerDialog(getActivity(), this, year,  month, day);

            return datePickerDialog;
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int  day) {
        String years=""+year;
        String months=""+(monthOfYear+1);
        String days=""+day;
        if(monthOfYear>=0 && monthOfYear<9){
            months="0"+(monthOfYear+1);
        }
        if(day>0 && day<10){
            days="0"+day;

        }
        date.setText(days+"/"+months+"/"+years);
    }



}

