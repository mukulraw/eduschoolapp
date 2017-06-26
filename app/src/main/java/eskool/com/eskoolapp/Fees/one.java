package eskool.com.eskoolapp.Fees;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import eskool.com.eskoolapp.ClassWork.ClassWorkFrgmntTwo;
import eskool.com.eskoolapp.Home.ParentHome;
import eskool.com.eskoolapp.R;

/**
 * Created by user on 6/23/2017.
 */

public class one extends Fragment {
    Toolbar toolbar;
    LinearLayout layout;


    public one() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.one, container, false);

        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.tool_bar);

        layout = (LinearLayout) view.findViewById(R.id.layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FeeFrgmnt3 fees2 = new FeeFrgmnt3();
                fragmentTransaction.replace(R.id.replace, fees2);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}