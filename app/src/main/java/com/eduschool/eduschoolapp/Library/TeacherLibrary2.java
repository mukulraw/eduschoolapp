package com.eduschool.eduschoolapp.Library;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.eduschool.eduschoolapp.AllAPIs;
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

public class TeacherLibrary2 extends AppCompatActivity{
    Toolbar toolbar;
    Button reserve;
    String id,status;
    ProgressBar progress;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_library2);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Hamlet");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progress=(ProgressBar)findViewById(R.id.prograss);

        id=getIntent().getStringExtra("Id");
        status=getIntent().getStringExtra("Status");


        User b = (User) getApplicationContext();


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



                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<BookListBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });




        reserve=(Button)findViewById(R.id.reserve);
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(TeacherLibrary2.this);
                dialog.setCancelable(false);
                dialog.setMessage("Are you sure you want to reserve the book ?" );
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Action for "Delete".
                        dialog.dismiss();
                        finish();
                    }
                })
                        .setNegativeButton("No ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for "Cancel".
                            }
                        });

                final AlertDialog alert = dialog.create();
                alert.show();
            }
        });

    }

}

