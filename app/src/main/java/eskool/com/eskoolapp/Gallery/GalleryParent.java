package eskool.com.eskoolapp.Gallery;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;

import java.util.Calendar;

import eskool.com.eskoolapp.Home.ParentHome;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.User;


public class GalleryParent extends Fragment {
    ImageView album1;
    Toolbar toolbar;

    public GalleryParent() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.gallery_parent, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.tool_bar);
        album1 = (ImageView) view.findViewById(R.id.album1);
        FloatingActionButton fab=(FloatingActionButton)view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.FragmentManager fm = getActivity().getFragmentManager();
                FilterDailog filterDailog = new FilterDailog();
                filterDailog.show(fm, "dialog");
            }
        });


        album1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
               GalleryParent2 frag1 = new GalleryParent2();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Gallery");

        User u = (User) getContext().getApplicationContext();

        u.back = true;
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

        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //use the current date as the default date in the picker
            Calendar c=Calendar.getInstance();
            @SuppressLint("WrongConstant") int year=c.get(Calendar.YEAR);
            @SuppressLint("WrongConstant") int month=c.get(Calendar.MONTH);
            @SuppressLint("WrongConstant") int day=c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog=null;
            datePickerDialog=new DatePickerDialog(getActivity(), this, year,  month, day);

            return datePickerDialog;
        }

    }

}
