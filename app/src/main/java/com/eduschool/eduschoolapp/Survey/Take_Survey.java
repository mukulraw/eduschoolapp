package com.eduschool.eduschoolapp.Survey;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.CustomViewPager;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SurveyListPOJO.SurveyListBean;
import com.eduschool.eduschoolapp.SurveyQusPOJO.SurveyQusBean;
import com.eduschool.eduschoolapp.UpdateSurveyPOJO.UpdateSurveyBean;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.takeSurveyBean.surveyAnsBean;
import com.eduschool.eduschoolapp.teacherSurveyPOJO.ServeyDatum;
import com.eduschool.eduschoolapp.teacherSurveyPOJO.teacherSurveryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Take_Survey extends AppCompatActivity {
    Toolbar toolbar;

    ProgressBar progress;

    String Id, QusId, ansId;

    TextView date;
    ImageView left, right;

    CustomViewPager pager;


    static int count = 0;


    int posi = 0;


    static List<String> quesArray;
    static List<String> ansArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take__survey);

        date = (TextView) findViewById(R.id.date);
        left = (ImageView) findViewById(R.id.left);
        right = (ImageView) findViewById(R.id.right);
        pager = (CustomViewPager) findViewById(R.id.pager);


        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle(getIntent().getStringExtra("title"));
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progress = (ProgressBar) findViewById(R.id.progress);


        Id = getIntent().getStringExtra("Id");


        load();

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                posi = pager.getCurrentItem();

                if (posi > 0) {
                    pager.setCurrentItem(posi - 1);
                }


            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                posi = pager.getCurrentItem();

                if (posi < count) {
                    pager.setCurrentItem(posi + 1);
                }

            }
        });


        pager.setOnPageChangeListener(new CustomViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                date.setText(String.valueOf(position + 1) + "/" + String.valueOf(count));

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


/*
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(Take_Survey.this);
                dialog.setCancelable(false);
                dialog.setMessage("Are you sure you want to submit ?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Action for "Delete".


                        User b = (User) getApplicationContext();


                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllAPIs cr = retrofit.create(AllAPIs.class);
                        progress.setVisibility(View.VISIBLE);

                        Call<UpdateSurveyBean> call = cr.update_qus(b.school_id, "Teacher", b.user_id, Id, QusId, ansId);

                        call.enqueue(new Callback<UpdateSurveyBean>() {
                            @Override
                            public void onResponse(Call<UpdateSurveyBean> call, Response<UpdateSurveyBean> response) {

                               */
/* qus.setText("Qus : "+response.body().getSurveyQuestion().get(0).getQuestion());

                                for(int i = 0 ; i < response.body().getSurveyQuestion().get(0).getQuestionOption().size() ; i++)
                                {
                                    RadioButton btn = new RadioButton(Take_Survey.this);
                                    btn.setText(response.body().getSurveyQuestion().get(0).getQuestionOption().get(i).getOptionValue());
                                    btn.setId(response.body().getSurveyQuestion().get(0).getQuestionOption().get(i).getOptionId());
                                    grp.addView(btn);

                                }
*//*


                                if (response.body().getSurveyListteacher().equals("1")) {
                                    Toast.makeText(Take_Survey.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Take_Survey.this, "Not Submitted Successfully!", Toast.LENGTH_SHORT).show();
                                }

                                progress.setVisibility(View.GONE);

                            }

                            @Override
                            public void onFailure(Call<UpdateSurveyBean> call, Throwable throwable) {

                                progress.setVisibility(View.GONE);

                            }
                        });


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

*/


    }


    public void load() {
        User b = (User) getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<teacherSurveryBean> call = cr.survey_list(b.school_id, "Teacher", b.user_id);

        call.enqueue(new Callback<teacherSurveryBean>() {
            @Override
            public void onResponse(Call<teacherSurveryBean> call, Response<teacherSurveryBean> response) {


                for (int i = 0; i < response.body().getSurveyListteacher().size(); i++) {
                    if (Objects.equals(response.body().getSurveyListteacher().get(i).getSurveyId(), Id)) {

                        count = response.body().getSurveyListteacher().get(i).getServeyData().size();
                        date.setText(String.valueOf(1) + "/" + String.valueOf(count));
                        SurveryPagerAdapter adapter = new SurveryPagerAdapter(getSupportFragmentManager(), response.body().getSurveyListteacher().get(i).getServeyData());
                        pager.setOffscreenPageLimit(response.body().getSurveyListteacher().get(i).getServeyData().size() - 1);
                        pager.setAdapter(adapter);
                    }
                }

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<teacherSurveryBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });

    }


    public class SurveryPagerAdapter extends FragmentStatePagerAdapter {

        List<ServeyDatum> list = new ArrayList<>();


        public SurveryPagerAdapter(FragmentManager fm, List<ServeyDatum> list) {
            super(fm);
            this.list = list;
            quesArray = new ArrayList<>();
            ansArray = new ArrayList<>();

            for (int i = 0 ; i < list.size() ; i++)
            {
                quesArray.add("jhasdhsjkadh");
                ansArray.add("jhasdhsjkadh");
            }

        }


        @Override
        public Fragment getItem(int position) {

            posi = position;

            Page frag = new Page();
            Bundle b = new Bundle();
            b.putString("id", list.get(position).getQuestionId());
            b.putString("status", list.get(position).getQuestionStatus());
            b.putString("survey", list.get(position).getSurveyid());
            b.putString("position", String.valueOf(position));
            frag.setArguments(b);
            return frag;
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }


    public static class Page extends Fragment {

        TextView ques, answer;
        Button submit;
        RadioGroup radio;

        String id;
        String status;

        ProgressBar progress;

        String ansId, surveyId;


        String positiom;


        @Nullable
        @Override
        public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.survey_frag_layout, container, false);

            id = getArguments().getString("id");
            status = getArguments().getString("status");
            positiom = getArguments().getString("position");

            progress = (ProgressBar) view.findViewById(R.id.progress);
            ques = (TextView) view.findViewById(R.id.ques);
            answer = (TextView) view.findViewById(R.id.answer);
            submit = (Button) view.findViewById(R.id.submit);
            radio = (RadioGroup) view.findViewById(R.id.radio);


            surveyId = getArguments().getString("survey");


            if (Integer.parseInt(positiom) == count - 1) {

                submit.setVisibility(View.VISIBLE);

            } else {

                submit.setVisibility(View.GONE);

            }


            loadData();


            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.d("answerSize" , String.valueOf(ansArray.size()));
                    Log.d("questionSize" , String.valueOf(quesArray.size()));


                    boolean flag = true;

                    for (int i = 0; i < quesArray.size(); i++) {

                        if (Objects.equals(quesArray.get(i), "jhasdhsjkadh"))
                        {
                            flag = false;
                        }

                    }


                    if (flag) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                        dialog.setCancelable(false);
                        dialog.setMessage("Are you sure you want to submit ?");
                        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id1) {
                                //Action for "Delete".


                                User b = (User) getContext().getApplicationContext();


                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(b.baseURL)
                                        .addConverterFactory(ScalarsConverterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                AllAPIs cr = retrofit.create(AllAPIs.class);
                                progress.setVisibility(View.VISIBLE);

                                Call<UpdateSurveyBean> call = cr.update_qus(b.school_id, "Teacher", b.user_id, surveyId, TextUtils.join(",", quesArray), TextUtils.join(",", ansArray));

                                call.enqueue(new Callback<UpdateSurveyBean>() {
                                    @Override
                                    public void onResponse(Call<UpdateSurveyBean> call, Response<UpdateSurveyBean> response) {

                                        //loadData();

                                        //loadDataComplete();

                                        ((Take_Survey) getActivity()).load();


                                        progress.setVisibility(View.GONE);

                                    }

                                    @Override
                                    public void onFailure(Call<UpdateSurveyBean> call, Throwable throwable) {

                                        progress.setVisibility(View.GONE);

                                    }
                                });


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
                    } else {
                        Toast.makeText(getContext(), "Please answer all Questions", Toast.LENGTH_SHORT).show();
                    }


                }
            });

            return view;
        }

        public void loadData() {
            if (Objects.equals(status, "Pending")) {
                User b = (User) getContext().getApplicationContext();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllAPIs cr = retrofit.create(AllAPIs.class);
                progress.setVisibility(View.VISIBLE);

                Call<SurveyQusBean> call = cr.survey_qus(surveyId, id);

                call.enqueue(new Callback<SurveyQusBean>() {
                    @Override
                    public void onResponse(Call<SurveyQusBean> call, final Response<SurveyQusBean> response) {

                        ques.setText("Ques. : " + response.body().getSurveyQuestion().get(0).getQuestion());

                        radio.removeAllViews();

                        for (int i = 0; i < response.body().getSurveyQuestion().get(0).getQuestionOption().size(); i++) {

                            Context context = getActivity();

                            try {
                                RadioButton btn = new RadioButton(context);
                                btn.setText(response.body().getSurveyQuestion().get(0).getQuestionOption().get(i).getOptionValue());
                                btn.setId(response.body().getSurveyQuestion().get(0).getQuestionOption().get(i).getOptionId());

                                btn.setEnabled(true);


                                radio.addView(btn);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                            final int finalI = i;
                            radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                                @Override
                                public void onCheckedChanged(RadioGroup arg0, int arg1) {
                                    int selectedId = radio.getCheckedRadioButtonId();
                                    Log.i("ID", String.valueOf(selectedId));
                                    ansId = Integer.toString(selectedId);
                                    Log.i("QuesId", response.body().getSurveyQuestion().get(0).getQuestionId());
                                    ansArray.set(Integer.parseInt(positiom), ansId);
                                    quesArray.set(Integer.parseInt(positiom), response.body().getSurveyQuestion().get(0).getQuestionId());
                                }
                            });

                        }


                        progress.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<SurveyQusBean> call, Throwable throwable) {

                        progress.setVisibility(View.GONE);

                    }
                });


                //submit.setVisibility(View.VISIBLE);
                answer.setVisibility(View.GONE);
            } else {

                User b = (User) getContext().getApplicationContext();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllAPIs cr = retrofit.create(AllAPIs.class);
                progress.setVisibility(View.VISIBLE);


                Log.d("userid", b.user_id);
                Log.d("surveyId", surveyId);
                Log.d("id", id);

                Call<surveyAnsBean> call = cr.getAnswer("Teacher", b.user_id, surveyId, id);

                call.enqueue(new Callback<surveyAnsBean>() {
                    @Override
                    public void onResponse(Call<surveyAnsBean> call, Response<surveyAnsBean> response) {

                        Context context = getActivity();

                        ques.setText("Ques. : " + response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getOptionValue());

                        radio.removeAllViews();

                        try {

                            for (int i = 0; i < response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getSurveyOption().size(); i++) {


                                RadioButton btn = new RadioButton(context);
                                btn.setText(response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getSurveyOption().get(i).getOptionValue());
                                btn.setId(Integer.parseInt(response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getSurveyOption().get(i).getOptionId()));



                                btn.setEnabled(false);


                                radio.addView(btn);


                                if (Objects.equals(response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getSurveyOption().get(i).getOptionId(), response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getYourAnswerId()))
                                {
                                    btn.setChecked(true);
                                }
                                else
                                {
                                    btn.setChecked(false);
                                }


                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                            @Override
                            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                                int selectedId = radio.getCheckedRadioButtonId();
                                Log.i("ID", String.valueOf(selectedId));
                                ansId = Integer.toString(selectedId);

                            }
                        });


                        try {
                            //RadioButton b = (RadioButton) radio.findViewById(Integer.parseInt(response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getYourAnswerId()));
                            //b.setChecked(true);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        answer.setText("Ans. : " + response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getYourAnswer());

                        progress.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<surveyAnsBean> call, Throwable t) {
                        progress.setVisibility(View.GONE);
                    }
                });

                submit.setVisibility(View.GONE);
                answer.setVisibility(View.VISIBLE);
            }
        }


        public void loadDataComplete() {


            User b = (User) getContext().getApplicationContext();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);
            progress.setVisibility(View.VISIBLE);


            Log.d("userid", b.user_id);
            Log.d("surveyId", surveyId);
            Log.d("id", id);

            Call<surveyAnsBean> call = cr.getAnswer("Teacher", b.user_id, surveyId, id);

            call.enqueue(new Callback<surveyAnsBean>() {
                @Override
                public void onResponse(Call<surveyAnsBean> call, Response<surveyAnsBean> response) {

                    Context context = getActivity();

                    ques.setText("Ques. : " + response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getOptionValue());

                    radio.removeAllViews();

                    try {

                        for (int i = 0; i < response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getSurveyOption().size(); i++) {


                            RadioButton btn = new RadioButton(context);
                            btn.setText(response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getSurveyOption().get(i).getOptionValue());
                            btn.setId(Integer.parseInt(response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getSurveyOption().get(i).getOptionId()));

                            btn.setEnabled(false);


                            radio.addView(btn);


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(RadioGroup arg0, int arg1) {
                            int selectedId = radio.getCheckedRadioButtonId();
                            Log.i("ID", String.valueOf(selectedId));
                            ansId = Integer.toString(selectedId);

                        }
                    });


                    try {
                        RadioButton b = (RadioButton) radio.findViewById(Integer.parseInt(response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getYourAnswerId()));
                        b.setChecked(true);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    answer.setText("Ans. : " + response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getYourAnswer());

                    progress.setVisibility(View.GONE);


                }

                @Override
                public void onFailure(Call<surveyAnsBean> call, Throwable t) {
                    progress.setVisibility(View.GONE);
                }
            });

            submit.setVisibility(View.GONE);
            answer.setVisibility(View.VISIBLE);

        }


    }


    @Override
    protected void onResume() {
        super.onResume();

        User b = (User) getApplicationContext();


    }
}
