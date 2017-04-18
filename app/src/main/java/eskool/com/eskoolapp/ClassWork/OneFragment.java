package eskool.com.eskoolapp.ClassWork;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import eskool.com.eskoolapp.MainActivity;
import eskool.com.eskoolapp.R;

/**
 * Created by user on 4/12/2017.
 */

public class OneFragment extends Fragment {
    Spinner status1;
    TextView className,sectionName;
    AlertDialog.Builder alertDialog;
    View convertView;

    public OneFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_one, container, false);


        className = (TextView) v.findViewById(R.id.className);
        sectionName = (TextView) v.findViewById(R.id.sectonName);
        status1 = (Spinner) v.findViewById(R.id.status);
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

        // attaching data adapter to spinner
        status1.setAdapter(dataAdapter);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, section);



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


        return v;


    }


}
