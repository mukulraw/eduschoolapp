package eskool.com.eskoolapp.Profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eskool.com.eskoolapp.R;

/**
 * Created by User on 5/5/2017.
 */

public class ParentFragmentTwo extends Fragment {

    public ParentFragmentTwo() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.parent_profile_frgmnt2, container, false);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
