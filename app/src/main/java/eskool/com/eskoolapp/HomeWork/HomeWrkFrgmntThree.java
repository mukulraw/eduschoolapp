package eskool.com.eskoolapp.HomeWork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import eskool.com.eskoolapp.Home.ParentHome;
import eskool.com.eskoolapp.R;

/**
 * Created by user on 5/8/2017.
 */

public class HomeWrkFrgmntThree extends Fragment {
    Toolbar toolbar;

    public HomeWrkFrgmntThree() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_wrk_details, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.tool_bar);

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Home Work Details");
    }
}
