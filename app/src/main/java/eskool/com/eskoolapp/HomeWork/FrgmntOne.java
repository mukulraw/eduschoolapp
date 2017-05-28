package eskool.com.eskoolapp.HomeWork;

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
import android.widget.ListView;
import android.widget.TextView;

import eskool.com.eskoolapp.ClassWork.OneFragment;
import eskool.com.eskoolapp.Home.ParentHome;
import eskool.com.eskoolapp.Home.TeacherHome;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.User;

/**
 * Created by user on 5/20/2017.
 */

public class FrgmntOne extends Fragment {
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

                DialogFragment picker = new OneFragment.DatePickerFragment();
                picker.show(getFragmentManager(),"datePicker");

            }
        });



        return v;

    }

}
