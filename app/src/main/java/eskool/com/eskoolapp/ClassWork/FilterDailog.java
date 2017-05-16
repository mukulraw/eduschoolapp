package eskool.com.eskoolapp.ClassWork;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import java.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


import java.util.Locale;

import eskool.com.eskoolapp.R;

/**
 * Created by User on 4/21/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.N)


public class FilterDailog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    TextView start, end;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.class_work_edit, container, false);
        getDialog().requestWindowFeature(STYLE_NO_TITLE);
        start = (TextView) view.findViewById(R.id.start);
        end = (TextView) view.findViewById(R.id.end);




        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment= new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datepicker");
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment= new DatePickerFragment2();
                newFragment.show(getFragmentManager(), "datepicker");
            }
        });



        return view;





    }




    @SuppressLint("ValidFragment")
    public class DatePickerFragment2 extends DialogFragment implements    DatePickerDialog.OnDateSetListener{
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
            end.setText(days+"/"+months+"/"+years);
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

    @SuppressLint("ValidFragment")
    public class DatePickerFragment extends DialogFragment implements    DatePickerDialog.OnDateSetListener{
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
            start.setText(days+"/"+months+"/"+years);


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
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }




}

