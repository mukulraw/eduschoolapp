package com.eduschool.eduschoolapp.Library;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.LibraryBookPOJO.BookListBean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.borrowedBookBean.borrowedBookBean;
import com.eduschool.eduschoolapp.reserveBookBean;
import com.eduschool.eduschoolapp.reservedBookPOJO.reservedBookBean;

import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 6/24/2017.
 */

public class TeacherLibrary2 extends AppCompatActivity {
    Toolbar toolbar;
    Button reserve;
    String id, status;
    ProgressBar progress;

    TextView name, author, book_no, availability;

    LinearLayout category;

    LinearLayout hide1, hide2, hide3;

    String type;

    TextView from_date, to_date, by;
    TextView published_date, date_of_entry, borrowed_by;


    String bookId = "";


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_library2);

        name = (TextView) findViewById(R.id.name);
        author = (TextView) findViewById(R.id.author);
        category = (LinearLayout) findViewById(R.id.category);
        book_no = (TextView) findViewById(R.id.book_no);
        availability = (TextView) findViewById(R.id.available);


        hide1 = (LinearLayout) findViewById(R.id.hide1);
        hide2 = (LinearLayout) findViewById(R.id.hide2);
        hide3 = (LinearLayout) findViewById(R.id.hide3);


        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        //toolbar.setTitle("Hamlet");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progress = (ProgressBar) findViewById(R.id.progress);

        from_date = (TextView) findViewById(R.id.from_date);
        to_date = (TextView) findViewById(R.id.to_date);
        by = (TextView) findViewById(R.id.by);
        published_date = (TextView) findViewById(R.id.published_date);
        date_of_entry = (TextView) findViewById(R.id.date_of_entry);
        borrowed_by = (TextView) findViewById(R.id.borrowed_by);
        reserve = (Button) findViewById(R.id.reserve);


        id = getIntent().getStringExtra("Id");
        status = getIntent().getStringExtra("Status");


        Log.d("asdasd", status);


        if (Objects.equals(status, "0")) {

            available();

        } else if (Objects.equals(status, "2")) {

            reserved();

        } else if (Objects.equals(status, "1")) {

            borrowed();

        }


        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(TeacherLibrary2.this);
                dialog.setCancelable(false);
                dialog.setMessage("Are you sure you want to reserve the book ?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int id2) {
                        //Action for "Delete".

                        progress.setVisibility(View.VISIBLE);

                        User u = (User) getApplicationContext();


                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(u.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllAPIs cr = retrofit.create(AllAPIs.class);


                        Call<reserveBookBean> call = cr.reserveBook(u.school_id , "Teacher" , u.user_id , bookId , id);

                        call.enqueue(new Callback<reserveBookBean>() {
                            @Override
                            public void onResponse(Call<reserveBookBean> call, Response<reserveBookBean> response) {

                                dialog.dismiss();
                                finish();

                                progress.setVisibility(View.GONE);
                            }

                            @Override
                            public void onFailure(Call<reserveBookBean> call, Throwable t) {
                                progress.setVisibility(View.GONE);

                            }
                        });





                    }
                })
                        .setNegativeButton("No ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for "Cancel".
                                dialog.dismiss();
                            }
                        });

                final AlertDialog alert = dialog.create();
                alert.show();
            }
        });

    }


    public void available() {

        from_date.setVisibility(View.GONE);
        to_date.setVisibility(View.GONE);
        by.setVisibility(View.GONE);
        published_date.setVisibility(View.GONE);
        date_of_entry.setVisibility(View.GONE);
        borrowed_by.setVisibility(View.GONE);

        hide1.setVisibility(View.GONE);
        hide2.setVisibility(View.GONE);
        hide3.setVisibility(View.GONE);

        reserve.setVisibility(View.VISIBLE);

        User b = (User) getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<BookListBean> call = cr.book_details(b.school_id, id, status);


        call.enqueue(new Callback<BookListBean>() {
            @Override
            public void onResponse(Call<BookListBean> call, Response<BookListBean> response) {

                toolbar.setTitle(response.body().getBookList().get(0).getTitle());

                bookId = response.body().getBookList().get(0).getBookId();

                name.setText(response.body().getBookList().get(0).getTitle());
                author.setText("Author - " + response.body().getBookList().get(0).getAuthor());
                book_no.setText("Book No. - " + response.body().getBookList().get(0).getBookNo());
                availability.setText(response.body().getBookList().get(0).getBookAvailable());
                availability.setBackgroundResource(R.drawable.lib2);


                category.removeAllViews();

                for (int i = 0; i < response.body().getBookList().get(0).getBookCategory().size(); i++) {

                    TextView text = new TextView(TeacherLibrary2.this);

                    text.setBackgroundResource(R.drawable.library_background);
                    text.setText(response.body().getBookList().get(0).getBookCategory().get(i).getChapcatName());
                    text.setTextSize(16);
                    text.setPadding(10, 10, 10, 10);

                    category.addView(text);

                }


                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<BookListBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


    }

    public void reserved() {

        //from_date.setVisibility(View.GONE);
        //to_date.setVisibility(View.GONE);
        //by.setVisibility(View.GONE);
        //published_date.setVisibility(View.GONE);
        //date_of_entry.setVisibility(View.GONE);
        //borrowed_by.setVisibility(View.GONE);

        reserve.setVisibility(View.GONE);


        User b = (User) getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<reservedBookBean> call = cr.getReservedBook(b.school_id, id, status);


        call.enqueue(new Callback<reservedBookBean>() {
            @Override
            public void onResponse(Call<reservedBookBean> call, Response<reservedBookBean> response) {
                toolbar.setTitle(response.body().getBookList().get(0).getTitle());

                bookId = response.body().getBookList().get(0).getBookId();

                name.setText(response.body().getBookList().get(0).getTitle());
                author.setText("Author - " + response.body().getBookList().get(0).getAuthor());
                book_no.setText("Book No. - " + response.body().getBookList().get(0).getBookNo());
                availability.setText(response.body().getBookList().get(0).getBookAvailable());
                availability.setBackgroundResource(R.drawable.lib1);


                category.removeAllViews();

                for (int i = 0; i < response.body().getBookList().get(0).getBookCategory().size(); i++) {

                    TextView text = new TextView(TeacherLibrary2.this);

                    text.setBackgroundResource(R.drawable.library_background);
                    text.setText(response.body().getBookList().get(0).getBookCategory().get(i).getChapcatName());
                    text.setTextSize(16);
                    text.setPadding(10, 10, 10, 10);

                    category.addView(text);

                }


                published_date.setText(response.body().getBookList().get(0).getReserveFromdate());
                date_of_entry.setText(response.body().getBookList().get(0).getReserveTodate());
                by.setText("Reserved By");

                borrowed_by.setText(response.body().getBookList().get(0).getReserveBy());

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<reservedBookBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


    }


    public void borrowed() {

        hide1.setVisibility(View.VISIBLE);
        hide2.setVisibility(View.VISIBLE);

        from_date.setVisibility(View.VISIBLE);
        to_date.setVisibility(View.VISIBLE);
        //by.setVisibility(View.GONE);
        published_date.setVisibility(View.VISIBLE);
        date_of_entry.setVisibility(View.VISIBLE);
        //borrowed_by.setVisibility(View.GONE);

        reserve.setVisibility(View.GONE);

        User b = (User) getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<borrowedBookBean> call = cr.getBorrowedBook(b.school_id, id, status);


        call.enqueue(new Callback<borrowedBookBean>() {
            @Override
            public void onResponse(Call<borrowedBookBean> call, Response<borrowedBookBean> response) {

                bookId = response.body().getBookList().get(0).getBookId();

                toolbar.setTitle(response.body().getBookList().get(0).getTitle());
                name.setText(response.body().getBookList().get(0).getTitle());
                author.setText("Author - " + response.body().getBookList().get(0).getAuthor());
                book_no.setText("Book No. - " + response.body().getBookList().get(0).getBookNo());
                availability.setText(response.body().getBookList().get(0).getBookAvailable());
                availability.setBackgroundResource(R.drawable.lib3);

                try {

                    published_date.setText(response.body().getBookList().get(0).getBorrowFromdate());
                    date_of_entry.setText(response.body().getBookList().get(0).getBorrowTodate());

                }catch (Exception e)
                {
                    e.printStackTrace();
                }


                category.removeAllViews();

                for (int i = 0; i < response.body().getBookList().get(0).getBookCategory().size(); i++) {

                    TextView text = new TextView(TeacherLibrary2.this);

                    text.setBackgroundResource(R.drawable.library_background);
                    text.setText(response.body().getBookList().get(0).getBookCategory().get(i).getChapcatName());
                    text.setTextSize(16);
                    text.setPadding(10, 10, 10, 10);

                    category.addView(text);

                }


                //published_date.setText(response.body().getBookList().get(0).getReserveFromdate());
                //to_date.setText(response.body().getBookList().get(0).getReserveTodate());
                by.setText("Borrowed By");

                borrowed_by.setText(response.body().getBookList().get(0).getBorrowedBy());

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<borrowedBookBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });

    }


}

