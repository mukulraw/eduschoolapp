package com.eduschool.eduschoolapp.Library;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.HomeWork.AdapterParent;
import com.eduschool.eduschoolapp.HomewrkParentPOJO.ParentSubjectListBean;
import com.eduschool.eduschoolapp.LibraryBookPOJO.BookList;
import com.eduschool.eduschoolapp.LibraryBookPOJO.BookListBean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 6/24/2017.
 */

public class TeacherLibrary1 extends Fragment {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    ProgressBar progress;
    private AdapterLibraryTeacher adapter;
    private List<BookList> albumList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.library1, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        progress = (ProgressBar) view.findViewById(R.id.progress);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        albumList = new ArrayList<>();
        adapter = new AdapterLibraryTeacher(getContext(), albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);




        User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<BookListBean> call = cr.book_list(b.school_id);


        call.enqueue(new Callback<BookListBean>() {
            @Override
            public void onResponse(Call<BookListBean> call, Response<BookListBean> response) {



                adapter.setGridData(response.body().getBookList());
                adapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<BookListBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


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
