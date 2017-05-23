package eskool.com.eskoolapp.HomeWork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eskool.com.eskoolapp.ClassWork.TeacherClsWrk2;
import eskool.com.eskoolapp.Home.ParentHome;
import eskool.com.eskoolapp.Home.TeacherHome;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.User;

/**
 * Created by user on 5/20/2017.
 */

public class FrgmntTwo extends Fragment {
    CardView card;
    Toolbar toolbar;



    public FrgmntTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.teacher_hw_frgmnt2, container, false);

        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.fab);
        card=(CardView)view.findViewById(R.id.card);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherClsWrk2 frag1 = new TeacherClsWrk2();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
            }
        });

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                android.app.FragmentManager fm = getActivity().getFragmentManager();
                eskool.com.eskoolapp.ClassWork.FilterDailog ratingBarFragment = new eskool.com.eskoolapp.ClassWork.FilterDailog();
                ratingBarFragment.show(fm, "dialog");

            }
        });









        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Home Work");

        User u = (User) getContext().getApplicationContext();

        u.back = true;


    }
}