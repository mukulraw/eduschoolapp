package eskool.com.eskoolapp.Events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import eskool.com.eskoolapp.Home.TeacherHome;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.User;

/**
 * Created by user on 6/14/2017.
 */

public class EventsFrgmnt1 extends Fragment {
    Toolbar toolbar;
    TextView view_event,create_event;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.events_frgmnt1, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

        view_event=(TextView)view.findViewById(R.id.view_event);

        create_event=(TextView)view.findViewById(R.id.create_event);

        view_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
               ViewCalendarFrgmnt frag1 = new ViewCalendarFrgmnt();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        create_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                CreateEventFrgmnt frag1 = new CreateEventFrgmnt();
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
        toolbar.setTitle("Events");
        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}
