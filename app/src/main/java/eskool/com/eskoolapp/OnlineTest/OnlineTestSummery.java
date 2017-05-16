package eskool.com.eskoolapp.OnlineTest;


import android.app.FragmentManager;
import android.content.Intent;
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
import eskool.com.eskoolapp.User;

public class OnlineTestSummery extends Fragment {
    TextView take_test;
Toolbar toolbar;
    public OnlineTestSummery() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_online_test_summery, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.tool_bar);
        take_test = (TextView) view.findViewById(R.id.take_test);
        take_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),OnlineTestActivity.class);
                startActivity(intent);
            }
        });


        return view;

    }
    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Online Test");

        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}

