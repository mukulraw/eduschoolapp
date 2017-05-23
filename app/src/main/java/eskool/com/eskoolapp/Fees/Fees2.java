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

import static java.security.AccessController.getContext;

/**
 * Created by user on 5/18/2017.
 */

public class Fees2 extends Fragment{
    Toolbar toolbar;

    public Fees2() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fees_frgmnt2, container, false);

        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.tool_bar);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Fees");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }

}

