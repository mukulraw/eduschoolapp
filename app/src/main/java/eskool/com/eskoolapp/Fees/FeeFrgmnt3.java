package eskool.com.eskoolapp.Fees;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eskool.com.eskoolapp.Home.ParentHome;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.User;

/**
 * Created by user on 6/23/2017.
 */

public class FeeFrgmnt3 extends Fragment {
    Toolbar toolbar;

    public FeeFrgmnt3() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fee_frgmnt3, container, false);

        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.tool_bar);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Admission Fees");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }

}
