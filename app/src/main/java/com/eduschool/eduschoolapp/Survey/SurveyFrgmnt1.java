package com.eduschool.eduschoolapp.Survey;

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

import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

/**
 * Created by user on 6/15/2017.
 */

public class SurveyFrgmnt1 extends Fragment {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private AdapterSurvey adapter;
    private List<Album> albumList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.survey_frgmnt1, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);



        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        albumList = new ArrayList<>();
        adapter = new AdapterSurvey(getContext(), albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);




        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Survey");
        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}
