package eskool.com.eskoolapp.RaiseRequest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eskool.com.eskoolapp.R;

/**
 * Created by User on 5/10/2017.
 */

public class FrgmntTwo extends Fragment{
    public FrgmntTwo() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.raise_request_frgnt2, container, false);

        return v;
    }
}