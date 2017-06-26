package eskool.com.eskoolapp.Library;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import eskool.com.eskoolapp.Home.ParentHome;
import eskool.com.eskoolapp.Home.TeacherHome;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.Survey.Album;
import eskool.com.eskoolapp.User;

/**
 * Created by user on 6/24/2017.
 */

public class ParentLibrary1 extends Fragment {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private AdapterLibrary adapter;
    private List<Album> albumList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.library1, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.tool_bar);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        albumList = new ArrayList<>();
        adapter = new AdapterLibrary(getContext(), albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Library");
        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}

