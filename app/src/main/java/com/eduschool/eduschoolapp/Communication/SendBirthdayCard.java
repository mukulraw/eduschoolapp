package com.eduschool.eduschoolapp.Communication;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.BirthStuListPOJO.BirthStuList;
import com.eduschool.eduschoolapp.BirthStuListPOJO.BirthStuListBean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SendBirthPOJO.SendBirthBean;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.WishingCards.Adapter;
import com.eduschool.eduschoolapp.WishingCards.AdapterBean;
import com.eduschool.eduschoolapp.birthPOJO.BirthList;
import com.eduschool.eduschoolapp.birthPOJO.birthBean;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SendBirthdayCard extends AppCompatActivity {

    Button send;
    Toolbar toolbar;
    String value, mCurrentPhotoPath;

    RoundedImageView img1, img2, img3, img4, img5;
    RadioButton radio1, radio2, radio3, radio4, radio5;
    RadioGroup group;

    ProgressBar progress;
    RecyclerView recyclerView;
    GridLayoutManager manager;
    List<BirthList> list;
    Adapter adapter;
    Bitmap bitmap;
    List<AdapterBean> s;
    int index = -1;
    File file;

    List<String> studentId;
    Spinner type;

    List<String> typeList;


    int posi;


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishing_card_frgmnt_teacher);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        typeList = new ArrayList<>();

        typeList.add("Student");
        typeList.add("Teacher");

        type = (Spinner) findViewById(R.id.type);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Send Birthday Card");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        progress = (ProgressBar) findViewById(R.id.progress);
        manager = new GridLayoutManager(SendBirthdayCard.this, 1);
        img1 = (RoundedImageView) findViewById(R.id.img1);
        img2 = (RoundedImageView) findViewById(R.id.img2);
        img3 = (RoundedImageView) findViewById(R.id.img3);
        img4 = (RoundedImageView) findViewById(R.id.img4);
        img5 = (RoundedImageView) findViewById(R.id.img5);
        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton) findViewById(R.id.radio2);
        radio3 = (RadioButton) findViewById(R.id.radio3);
        radio4 = (RadioButton) findViewById(R.id.radio4);
        radio5 = (RadioButton) findViewById(R.id.radio5);


        list = new ArrayList<>();
        s = new ArrayList<>();
        studentId = new ArrayList<>();

        adapter = new Adapter(SendBirthdayCard.this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        send = (Button) findViewById(R.id.send);


        group = (RadioGroup) findViewById(R.id.radioGroup);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                index = radioGroup.indexOfChild(radioButton);
            }

        });


        ArrayAdapter<String> adapte1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, typeList);


        type.setAdapter(adapte1);


        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    posi = position;

                    User b = (User) getApplicationContext();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    final AllAPIs cr = retrofit.create(AllAPIs.class);
                    progress.setVisibility(View.VISIBLE);

                    Call<birthBean> call = cr.stu_bithday(b.school_id);


                    call.enqueue(new Callback<birthBean>() {
                        @Override
                        public void onResponse(Call<birthBean> call, Response<birthBean> response) {

                            s.clear();

                            if (response.body().getBirthList().size() > 0) {
                                adapter.setGridData(response.body().getBirthList(), s);
                                adapter.notifyDataSetChanged();
                            }


                            progress.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<birthBean> call, Throwable throwable) {

                            progress.setVisibility(View.GONE);

                        }
                    });

                } else if (position == 1) {

                    posi = position;


                    final User b = (User) getApplicationContext();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    final AllAPIs cr = retrofit.create(AllAPIs.class);
                    progress.setVisibility(View.VISIBLE);

                    Call<birthBean> call = cr.teacher_bithday(b.school_id);


                    call.enqueue(new Callback<birthBean>() {
                        @Override
                        public void onResponse(Call<birthBean> call, Response<birthBean> response) {

                            s.clear();



                            if (response.body().getBirthList().size() > 0) {

                                for (int i = 0; i < response.body().getBirthList().size(); i++) {

                                    if (!Objects.equals(response.body().getBirthList().get(i).getId(), b.user_id))
                                    {
                                        list.add(response.body().getBirthList().get(i));
                                    }

                                }

                                adapter.setGridData(list, s);
                                adapter.notifyDataSetChanged();
                            }
                            progress.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<birthBean> call, Throwable throwable) {

                            progress.setVisibility(View.GONE);

                        }
                    });

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (index > -1)
                {
                    JSONArray jsonArray = new JSONArray();

                    List<AdapterBean> list1 = adapter.getCheckList();

                    final List<String> ll = new ArrayList<>();

                    Log.d("sizezee", String.valueOf(list1.size()));

                    for (int i = 0; i < list1.size(); i++) {
                        JSONObject object = new JSONObject();

                        try {

                            if (Objects.equals(list1.get(i).getStatus(), "1")) {
                                object.put("Id", list1.get(i).getId());
                                ll.add(list1.get(i).getId());
                                jsonArray.put(object);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    final JSONObject jsonObject = new JSONObject();

                    try {
                        jsonObject.put("stu_id", jsonArray);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("dsds", jsonObject.toString());


                    AlertDialog.Builder dialog = new AlertDialog.Builder(SendBirthdayCard.this);
                    dialog.setCancelable(false);
                    dialog.setMessage("Are you sure you want to send card ?");
                    dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                            try {


                                User b = (User) getApplicationContext();
                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(b.baseURL)
                                        .addConverterFactory(ScalarsConverterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                final AllAPIs cr = retrofit.create(AllAPIs.class);
                                progress.setVisibility(View.VISIBLE);


                                String ca = "";

                                if (index == 0) {
                                    ca = "card1.jpg";
                                } else if (index == 1) {
                                    ca = "card2.jpg";
                                } else if (index == 2) {
                                    ca = "card4.jpg";
                                } else if (index == 3) {
                                    ca = "card5.jpg";
                                } else if (index == 4) {
                                    ca = "card6.jpg";
                                }


                                String toType = "";

                                if (posi == 0) {
                                    toType = "Parent";
                                } else if (posi == 1) {
                                    toType = "Teacher";
                                }



                                if (ll.size() > 0)
                                {
                                    Call<SendBirthBean> call = cr.send_card(b.school_id, "Teacher", b.user_id, toType, ca, TextUtils.join(",", ll));

                                    Log.d("asdasd", TextUtils.join(",", ll));

                                    call.enqueue(new Callback<SendBirthBean>() {
                                        @Override
                                        public void onResponse(Call<SendBirthBean> call, Response<SendBirthBean> response) {


                                            if (response.body().getStatus().equals("1")) {

                                                Dialog d1 = new Dialog(SendBirthdayCard.this);
                                                d1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                d1.setCancelable(true);
                                                d1.setContentView(R.layout.birthday_success_popup);
                                                d1.show();


                                                d1.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                    @Override
                                                    public void onDismiss(DialogInterface dialog) {
                                                        finish();
                                                    }
                                                });


                                                progress.setVisibility(View.GONE);

                                            } else {
                                                Toast.makeText(SendBirthdayCard.this, "Birthday Card did not send Successfully.", Toast.LENGTH_SHORT).show();

                                            }

                                            progress.setVisibility(View.GONE);

                                        }

                                        @Override
                                        public void onFailure(Call<SendBirthBean> call, Throwable throwable) {
                                            Log.d("imagg", String.valueOf(throwable));
                                            progress.setVisibility(View.GONE);

                                        }
                                    });
                                }
                                else
                                {
                                    Toast.makeText(SendBirthdayCard.this, "Please select a student", Toast.LENGTH_SHORT).show();
                                }






                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            dialog.dismiss();

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
                else
                {
                    Toast.makeText(SendBirthdayCard.this , "Please select a Card" , Toast.LENGTH_SHORT).show();
                }





            }
        });


    }
}
