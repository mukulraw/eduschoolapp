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
;

import eskool.com.eskoolapp.Home.ParentHome;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.User;

/**
 * Created by User on 5/8/2017.
 */

public class HomeWorkFrgmntTwo extends Fragment {
    CardView card;
    Toolbar toolbar;

    public HomeWorkFrgmntTwo() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.parent_hw2, container, false);

        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.fab);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.tool_bar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.FragmentManager fm = getActivity().getFragmentManager();
                eskool.com.eskoolapp.ClassWork.FilterDailog ratingBarFragment = new eskool.com.eskoolapp.ClassWork.FilterDailog();

                ratingBarFragment.show(fm, "dialog");

            }
        });

        card = (CardView) view.findViewById(R.id.card);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                HomeWrkFrgmntThree frag1 = new HomeWrkFrgmntThree();
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
        toolbar.setTitle("Home Work");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }
}