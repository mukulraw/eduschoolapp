package com.eduschool.eduschoolapp.Survey;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SurveyQusPOJO.SurveyQusBean;
import com.eduschool.eduschoolapp.UpdateSurveyPOJO.UpdateSurveyBean;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.takeSurveyBean.surveyAnsBean;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class TakeSurveyParent extends AppCompatActivity {
    Toolbar toolbar;
    Button submit;
    ProgressBar progress;
    RadioGroup grp;

    String Id, QusId, ansId , status;
    LinearLayout hide;

    TextView answer;

    Boolean bool;

    TextView qus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_survey_parent);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Take Survey");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progress = (ProgressBar) findViewById(R.id.progress);
        grp = (RadioGroup) findViewById(R.id.radio);
        qus = (TextView) findViewById(R.id.qus);

        hide = (LinearLayout)findViewById(R.id.hide);
        answer = (TextView)findViewById(R.id.answer);

        Id = getIntent().getStringExtra("Id");
        QusId = getIntent().getStringExtra("QusId");
        status = getIntent().getStringExtra("status");


        if (Objects.equals(status, "Pending"))
        {
            grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(RadioGroup arg0, int arg1) {
                    int selectedId = grp.getCheckedRadioButtonId();
                    Log.i("ID", String.valueOf(selectedId));
                    ansId = Integer.toString(selectedId);

                }
            });
        }




        submit = (Button) findViewById(R.id.submit);



        if (Objects.equals(status, "Complete"))
        {
            submit.setVisibility(View.GONE);
            hide.setVisibility(View.VISIBLE);
            bool = false;


        }
        else if (Objects.equals(status, "Pending"))
        {
            submit.setVisibility(View.VISIBLE);
            hide.setVisibility(View.GONE);
            bool = true;

        }









        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(TakeSurveyParent.this);
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

                        Call<UpdateSurveyBean> call = cr.update_qus(b.school_id,"Parent", b.user_id, Id, QusId, ansId);

                        call.enqueue(new Callback<UpdateSurveyBean>() {
                            @Override
                            public void onResponse(Call<UpdateSurveyBean> call, Response<UpdateSurveyBean> response) {

                               /* qus.setText("Qus : "+response.body().getSurveyQuestion().get(0).getQuestion());

                                for(int i = 0 ; i < response.body().getSurveyQuestion().get(0).getQuestionOption().size() ; i++)
                                {
                                    RadioButton btn = new RadioButton(Take_Survey.this);
                                    btn.setText(response.body().getSurveyQuestion().get(0).getQuestionOption().get(i).getOptionValue());
                                    btn.setId(response.body().getSurveyQuestion().get(0).getQuestionOption().get(i).getOptionId());
                                    grp.addView(btn);

                                }
*/

                                if (response.body().getSurveyListteacher().equals("1")) {
                                    Toast.makeText(TakeSurveyParent.this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(TakeSurveyParent.this, "Not Submitted Successfully!", Toast.LENGTH_SHORT).show();
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


    }

    @Override
    protected void onResume() {
        super.onResume();


        if (!bool)
        {

            User b = (User) getApplicationContext();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);
            progress.setVisibility(View.VISIBLE);


            Call<surveyAnsBean> call = cr.getAnswer("Parent" , b.user_id , Id , QusId);

            call.enqueue(new Callback<surveyAnsBean>() {
                @Override
                public void onResponse(Call<surveyAnsBean> call, Response<surveyAnsBean> response) {


                    qus.setText("Qus : " + response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getOptionValue());

                    grp.removeAllViews();

                    for (int i = 0; i < response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getSurveyOption().size(); i++) {



                        RadioButton btn = new RadioButton(TakeSurveyParent.this);
                        btn.setText(response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getSurveyOption().get(i).getOptionValue());
                        btn.setId(Integer.parseInt(response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getSurveyOption().get(i).getOptionId()));

                        btn.setEnabled(false);


                        grp.addView(btn);





                    }


                    grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(RadioGroup arg0, int arg1) {
                            int selectedId = grp.getCheckedRadioButtonId();
                            Log.i("ID", String.valueOf(selectedId));
                            ansId = Integer.toString(selectedId);

                        }
                    });


                    try {
                        RadioButton b = (RadioButton) findViewById(Integer.parseInt(response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getYourAnswerId()));
                        b.setChecked(true);

                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }



                    answer.setText(response.body().getSurveyAnswer().get(0).getSurveyData().get(0).getYourAnswer());

                    progress.setVisibility(View.GONE);


                }

                @Override
                public void onFailure(Call<surveyAnsBean> call, Throwable t) {
                    progress.setVisibility(View.GONE);
                }
            });

        }
        else
        {
            User b = (User) getApplicationContext();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllAPIs cr = retrofit.create(AllAPIs.class);
            progress.setVisibility(View.VISIBLE);

            Call<SurveyQusBean> call = cr.survey_qus(Id, QusId);

            call.enqueue(new Callback<SurveyQusBean>() {
                @Override
                public void onResponse(Call<SurveyQusBean> call, Response<SurveyQusBean> response) {

                    qus.setText("Qus : " + response.body().getSurveyQuestion().get(0).getQuestion());

                    grp.removeAllViews();

                    for (int i = 0; i < response.body().getSurveyQuestion().get(0).getQuestionOption().size(); i++) {



                        RadioButton btn = new RadioButton(TakeSurveyParent.this);
                        btn.setText(response.body().getSurveyQuestion().get(0).getQuestionOption().get(i).getOptionValue());
                        btn.setId(response.body().getSurveyQuestion().get(0).getQuestionOption().get(i).getOptionId());

                        btn.setEnabled(true);


                        grp.addView(btn);

                        grp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                            @Override
                            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                                int selectedId = grp.getCheckedRadioButtonId();
                                Log.i("ID", String.valueOf(selectedId));
                                ansId = Integer.toString(selectedId);

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
        }




    }
}

