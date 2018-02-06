package com.eduschool.eduschoolapp.OnlineTest;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.WorkSource;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.MyViewPager;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.onlineTestQuesPOJO.OnlinetestList;
import com.eduschool.eduschoolapp.onlineTestQuesPOJO.QuestionOption;
import com.eduschool.eduschoolapp.onlineTestQuesPOJO.onlineTestQuesBean;
import com.eduschool.eduschoolapp.takeTestPOJO.takeTestBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class OnlineTestActivity extends AppCompatActivity {
    TextView countDown;
    Toolbar toolbar;
    MyAdapter mAdapter;
    static MyViewPager mPager;
    //TextView count;

    static String id;
    String title;

    TextView titl;

    static ProgressBar progress;

    static List<String> quesIds;
    static List<String> ansId;

    static int coun = 0;

    Button start;
    final static Fragment[] layouts = new Fragment[]{QuestionFragment.newInstance(0), QuestionFragment_two.newInstance(1)};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_test);

        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");



        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        progress = (ProgressBar)findViewById(R.id.progress);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        quesIds = new ArrayList<>();
        ansId = new ArrayList<>();

        titl = (TextView)findViewById(R.id.title);

        titl.setText(title);

        android.app.FragmentManager fm = this.getFragmentManager();
        //TakeTestDailog ratingBarFragment = new TakeTestDailog();
        //ratingBarFragment.show(fm, "dailog");

































        //count = (TextView) findViewById(R.id.count);

        mPager = (MyViewPager) findViewById(R.id.viewPager);

        /*Button button = (Button) findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPager.setCurrentItem(2);
                count.setText("2/30");
            }
        });
        button = (Button) findViewById(R.id.prev);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPager.setCurrentItem(0);
                count.setText("1/30");
            }
        });*/


        countDown = (TextView) findViewById(R.id.countDown);



        progress.setVisibility(View.VISIBLE);

        Log.d("asdasd" , "1");


        User u = (User)getApplicationContext();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);

        Call<onlineTestQuesBean> call = cr.getOnlineQues(id);


        call.enqueue(new Callback<onlineTestQuesBean>() {
            @Override
            public void onResponse(Call<onlineTestQuesBean> call, final Response<onlineTestQuesBean> response) {




                coun = response.body().getOnlinetestList().size();

                mAdapter = new MyAdapter(getSupportFragmentManager() , response.body().getOnlinetestList());
                mPager.setAdapter(mAdapter);

                mPager.setSwipeable(false);



                for (int i = 0 ; i < response.body().getOnlinetestList().size() ; i++)
                {


                    quesIds.add(response.body().getOnlinetestList().get(i).getQuestinId());
                    ansId.add("0");


                }







                final Dialog dialog = new Dialog(OnlineTestActivity.this);
                dialog.setCancelable(false);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.online_test_dailog);

                dialog.show();


                Button start = (Button)dialog.findViewById(R.id.start_test);

                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int hoursToGo = Integer.parseInt(response.body().getOnlinetestList().get(0).getHr());
                        int minutesToGo = Integer.parseInt(response.body().getOnlinetestList().get(0).getMin());
                        int secondsToGo = 0;
                        int millisToGo = secondsToGo * 1000 + minutesToGo * 1000 * 60 + hoursToGo * 1000 * 60 * 60;

                        dialog.dismiss();
                        new CountDownTimer(millisToGo, 1000) {

                            @Override
                            public void onTick(long millis) {
                                int seconds = (int) (millis / 1000) % 60;
                                int minutes = (int) ((millis / (1000 * 60)) % 60);
                                int hours = (int) ((millis / (1000 * 60 * 60)) % 24);
                                String text = String.format("%02d:%02d:%02d ", hours, minutes, seconds);
                                countDown.setText(text);
                            }

                            @Override
                            public void onFinish() {
                                countDown.setText("Done!");
                            }
                        }.start();


                    }
                });
                dialog.setOnKeyListener(new Dialog.OnKeyListener() {

                    @Override
                    public boolean onKey(DialogInterface arg0, int keyCode,
                                         KeyEvent event) {
                        // TODO Auto-generated method stub
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            finish();
                            dialog.dismiss();
                        }
                        return true;
                    }
                });


                progress.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<onlineTestQuesBean> call, Throwable t) {

                progress.setVisibility(View.GONE);

            }
        });



    }

    public static class MyAdapter extends FragmentStatePagerAdapter {

        List<OnlinetestList> list = new ArrayList<>();

        public MyAdapter(FragmentManager fm , List<OnlinetestList> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int position) {

            Page page = new Page();
            page.setData(list.get(position).getQuestionOption());

            Bundle b = new Bundle();

            b.putString("id" , list.get(position).getOnlinetestId());
            b.putString("ques" , list.get(position).getQuestion());
            b.putInt("pos" , position);

            page.setArguments(b);

            return page;
        }
    }

    public static class Page extends Fragment
    {

        RadioGroup group;
        TextView question;

        String quesId , ques;

        int pos;

        TextView countt;

        Button prev , next , submit;

        List<QuestionOption> ll = new ArrayList<>();


        public void setData(List<QuestionOption> ll)
        {
            this.ll = ll;
        }

        Bundle b;

        @Nullable
        @Override
        public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            final View view = inflater.inflate(R.layout.question_layout , container , false);


            question = (TextView)view.findViewById(R.id.question);
            group = (RadioGroup)view.findViewById(R.id.group);

            prev = (Button)view.findViewById(R.id.prev);
            next = (Button)view.findViewById(R.id.next);
            submit = (Button)view.findViewById(R.id.submit);

            countt = (TextView)view.findViewById(R.id.count);

            b = getArguments();

            quesId = b.getString("id");
            ques = b.getString("ques");
            pos = b.getInt("pos");

            question.setText(String.valueOf(pos + 1) + ". " + ques);


            countt.setText(String.valueOf(pos + 1) + "/" + String.valueOf(coun));


            for (int i = 0 ; i < ll.size() ; i++)
            {

                RadioButton btn = new RadioButton(getActivity());
                btn.setText(ll.get(i).getOption());
                btn.setId(Integer.parseInt(ll.get(i).getId()));
                group.addView(btn);

            }


            if (pos == 0)
            {
                prev.setVisibility(View.GONE);
            }
            else
            {
                prev.setVisibility(View.VISIBLE);
            }


            if (pos == coun - 1)
            {
                next.setVisibility(View.GONE);
                submit.setVisibility(View.VISIBLE);
            }
            else
            {
                next.setVisibility(View.VISIBLE);
                submit.setVisibility(View.GONE);
            }


            prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (pos > 0)
                    {
                        mPager.setCurrentItem(pos - 1);
                    }

                }
            });


            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {

                    ansId.set(pos , String.valueOf(checkedId));

                }
            });


            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (pos < coun - 1)
                    {

                        mPager.setCurrentItem(pos + 1);

                        //ansId.set(pos , String.valueOf(group.getCheckedRadioButtonId()));

                    }


                }
            });



            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {






                    final Dialog dialog = new Dialog(getActivity());
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.submit_test_model);
                    dialog.show();

                    TextView attempted = (TextView)dialog.findViewById(R.id.attempted);
                    TextView unattempted = (TextView)dialog.findViewById(R.id.unattempted);

                    TextView no = (TextView)dialog.findViewById(R.id.no);
                    TextView yes = (TextView)dialog.findViewById(R.id.yes);




                    int uncount = 0;
                    int attcount = 0;


                    for (int i = 0 ; i < ansId.size() ; i++)
                    {
                        if (Integer.parseInt(ansId.get(i)) > 0)
                        {
                            attcount++;
                        }
                        else
                        {
                            uncount++;
                        }
                    }



                    attempted.setText(String.valueOf(attcount));
                    unattempted.setText(String.valueOf(uncount));



                    final ProgressBar progressBar = (ProgressBar)dialog.findViewById(R.id.progress);

                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog.dismiss();

                        }
                    });



                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            progressBar.setVisibility(View.VISIBLE);

                            Log.d("asdasd" , "1");


                            User u = (User)getContext().getApplicationContext();



                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(u.baseURL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllAPIs cr = retrofit.create(AllAPIs.class);

                            Call<takeTestBean> call = cr.takeTest(quesId , u.user_id , TextUtils.join(",", quesIds) , TextUtils.join(",", ansId));

                            Log.d("question Id" , quesId);
                            Log.d("userid" , u.user_id);
                            Log.d("quesIds" , TextUtils.join(",", quesIds));
                            Log.d("ansIds" , TextUtils.join(",", ansId));

                            call.enqueue(new Callback<takeTestBean>() {
                                @Override
                                public void onResponse(Call<takeTestBean> call, Response<takeTestBean> response) {



                                    final Dialog dialog1 = new Dialog(getActivity());
                                    dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    dialog1.setCancelable(true);
                                    dialog1.setContentView(R.layout.online_test_success_popup);
                                    dialog1.show();



                                    dialog1.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                        @Override
                                        public void onCancel(DialogInterface dialog) {

                                            dialog1.dismiss();
                                            dialog.dismiss();
                                            getActivity().finish();

                                        }
                                    });



                                    progressBar.setVisibility(View.GONE);


                                }

                                @Override
                                public void onFailure(Call<takeTestBean> call, Throwable t) {
                                    progressBar.setVisibility(View.GONE);

                                }
                            });



                        }
                    });










                }
            });



            return view;
        }
    }



}