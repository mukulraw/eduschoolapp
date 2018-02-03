package com.eduschool.eduschoolapp.Library;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.ParentHome;
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

public class ParentLibrary1 extends Fragment {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private AdapterLibrary adapter;
    private List<BookList> albumList;
    EditText search;
    ProgressBar progress;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.library1, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);

        search = (EditText)view.findViewById(R.id.search);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        albumList = new ArrayList<>();
        adapter = new AdapterLibrary(getContext(), albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);




        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length()>0)
                {

                    User b = (User) getActivity().getApplicationContext();


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllAPIs cr = retrofit.create(AllAPIs.class);
                    progress.setVisibility(View.VISIBLE);

                    Call<BookListBean> call = cr.search_book(b.school_id , s.toString());


                    call.enqueue(new Callback<BookListBean>() {
                        @Override
                        public void onResponse(Call<BookListBean> call, Response<BookListBean> response) {


                            //albumList = response.body().getBookList();

                            adapter.setGridData(response.body().getBookList());
                            adapter.notifyDataSetChanged();
                            progress.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<BookListBean> call, Throwable throwable) {

                            progress.setVisibility(View.GONE);

                        }
                    });


                }
                else
                {
                    adapter.setGridData(albumList);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

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


    }
}

