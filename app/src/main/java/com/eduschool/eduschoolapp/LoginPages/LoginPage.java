package com.eduschool.eduschoolapp.LoginPages;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.LoginPOJO.Loginbean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginPage extends AppCompatActivity {
    TextView tv_forgot_something;
    Button btn_login;
    ProgressBar progress;
    EditText username, password;
    String Susername, Spassword;
    SharedPreferences pref;
    SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        tv_forgot_something = (TextView) findViewById(R.id.tv_forgot_something);
        btn_login = (Button) findViewById(R.id.btn_login);
        progress = (ProgressBar) findViewById(R.id.progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        pref = getSharedPreferences("mypref", MODE_PRIVATE);
        edit = pref.edit();

        tv_forgot_something.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ForgotPasswordFrgmnt frag1 = new ForgotPasswordFrgmnt();
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Susername = username.getText().toString().trim();
                Spassword = password.getText().toString().trim();

                if (Susername.length() > 0) {

                    if (Spassword.length() > 0) {

                        User b = (User) getApplicationContext();

                        progress.setVisibility(View.VISIBLE);
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllAPIs cr = retrofit.create(AllAPIs.class);


                        Call<Loginbean> call1 = cr.login(Susername, Spassword);


                        call1.enqueue(new Callback<Loginbean>() {
                            @Override
                            public void onResponse(Call<Loginbean> call, Response<Loginbean> response) {



                                String s = response.body().getUserType().toString();
                                User b = (User) getApplicationContext();

                                if (Objects.equals(response.body().getStatus(), "0")) {
                                    Toast.makeText(LoginPage.this, "Invalid Login Details", Toast.LENGTH_SHORT).show();
                                    progress.setVisibility(View.GONE);
                                } else if (Objects.equals(response.body().getStatus(), "1")) {

                                    if (s.equals("Teacher")) {

                                        if (Objects.equals(response.body().getClassTeacher(), "yes"))
                                        {

                                            b.school_id = response.body().getSchoolId();
                                            b.user_id = response.body().getId();
                                            b.user_type = response.body().getUserType();
                                            b.user_name=response.body().getName();
                                            b.user_class=response.body().getClassId();
                                            b.user_section=response.body().getSectionId();
                                            b.class_teacher=response.body().getClassTeacher();
                                            b.class_Name=response.body().getClassName();
                                            b.section_Name=response.body().getSectionName();
                                            b.studName = response.body().getName();


                                            edit.putString("type", s);
                                            edit.putString("username", Susername);
                                            edit.putString("pass", Spassword);
                                            edit.apply();

                                            Intent intent = new Intent(LoginPage.this, TeacherHome.class);
                                            intent.putExtra("teacher" , "yes");

                                            if (Objects.equals(response.body().getModule().get(0).getStatus(), "no") || Objects.equals(response.body().getModule().get(26).getStatus(), "no"))
                                            {
                                                intent.putExtra("academic" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("academic" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(1).getStatus(), "no") || Objects.equals(response.body().getModule().get(22).getStatus(), "no") || Objects.equals(response.body().getModule().get(23).getStatus(), "no"))
                                            {
                                                intent.putExtra("classwork" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("classwork" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(4).getStatus(), "no") || Objects.equals(response.body().getModule().get(5).getStatus(), "no") || Objects.equals(response.body().getModule().get(6).getStatus(), "no"))
                                            {
                                                intent.putExtra("homework" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("homework" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(7).getStatus(), "no") || Objects.equals(response.body().getModule().get(10).getStatus(), "no"))
                                            {
                                                intent.putExtra("event" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("event" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(8).getStatus(), "no") || Objects.equals(response.body().getModule().get(11).getStatus(), "no") || Objects.equals(response.body().getModule().get(12).getStatus(), "no") || Objects.equals(response.body().getModule().get(13).getStatus(), "no"))
                                            {
                                                intent.putExtra("communication" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("communication" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(9).getStatus(), "no") || Objects.equals(response.body().getModule().get(14).getStatus(), "no"))
                                            {
                                                intent.putExtra("calendar" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("calendar" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(15).getStatus(), "no") || Objects.equals(response.body().getModule().get(16).getStatus(), "no") || Objects.equals(response.body().getModule().get(18).getStatus(), "no"))
                                            {
                                                intent.putExtra("exam" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("exam" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(19).getStatus(), "no") || Objects.equals(response.body().getModule().get(20).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(21).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(22).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(23).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(24).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(25).getStatus(), "no")
                                                    )
                                            {
                                                intent.putExtra("gallery" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("gallery" , "yes");
                                            }


                                            if (Objects.equals(response.body().getModule().get(27).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(28).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(32).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(33).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(34).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(35).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(36).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(37).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(38).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(39).getStatus(), "no"))
                                            {
                                                intent.putExtra("library" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("library" , "yes");
                                            }


                                            if (Objects.equals(response.body().getModule().get(29).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(30).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(31).getStatus(), "no")
                                                    )
                                            {
                                                intent.putExtra("attendance" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("attendance" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(43).getStatus(), "no"))
                                            {
                                                intent.putExtra("summary" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("summary" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(49).getStatus(), "no"))
                                            {
                                                intent.putExtra("time" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("time" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(51).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(52).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(53).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(54).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(55).getStatus(), "no")
                                                    )
                                            {
                                                intent.putExtra("stay" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("stay" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(44).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(45).getStatus(), "no")
                                                    )
                                            {
                                                intent.putExtra("survey" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("survey" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(46).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(47).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(48).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(50).getStatus(), "no")
                                                    )
                                            {

                                                intent.putExtra("profile" , "no");

                                            }
                                            else
                                            {
                                                intent.putExtra("profile" , "yes");
                                            }


                                            startActivity(intent);
                                            finish();

                                        }
                                        else
                                        {

                                            b.school_id = response.body().getSchoolId();
                                            b.user_id = response.body().getId();
                                            b.user_type = response.body().getUserType();
                                            b.user_name=response.body().getName();
                                            b.user_class=response.body().getClassId();
                                            b.user_section=response.body().getSectionId();
                                            b.class_teacher=response.body().getClassTeacher();
                                            b.class_Name=response.body().getClassName();
                                            b.section_Name=response.body().getSectionName();
                                            b.studName = response.body().getName();


                                            edit.putString("type", s);
                                            edit.putString("username", Susername);
                                            edit.putString("pass", Spassword);
                                            edit.apply();

                                            Intent intent = new Intent(LoginPage.this, TeacherHome.class);
                                            intent.putExtra("teacher" , "no");

                                            if (Objects.equals(response.body().getModule().get(0).getStatus(), "no") || Objects.equals(response.body().getModule().get(26).getStatus(), "no"))
                                            {
                                                intent.putExtra("academic" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("academic" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(1).getStatus(), "no") || Objects.equals(response.body().getModule().get(22).getStatus(), "no") || Objects.equals(response.body().getModule().get(23).getStatus(), "no"))
                                            {
                                                intent.putExtra("classwork" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("classwork" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(4).getStatus(), "no") || Objects.equals(response.body().getModule().get(5).getStatus(), "no") || Objects.equals(response.body().getModule().get(6).getStatus(), "no"))
                                            {
                                                intent.putExtra("homework" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("homework" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(7).getStatus(), "no") || Objects.equals(response.body().getModule().get(10).getStatus(), "no"))
                                            {
                                                intent.putExtra("event" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("event" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(8).getStatus(), "no") || Objects.equals(response.body().getModule().get(11).getStatus(), "no") || Objects.equals(response.body().getModule().get(12).getStatus(), "no") || Objects.equals(response.body().getModule().get(13).getStatus(), "no"))
                                            {
                                                intent.putExtra("communication" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("communication" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(9).getStatus(), "no") || Objects.equals(response.body().getModule().get(14).getStatus(), "no"))
                                            {
                                                intent.putExtra("calendar" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("calendar" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(15).getStatus(), "no") || Objects.equals(response.body().getModule().get(16).getStatus(), "no") || Objects.equals(response.body().getModule().get(18).getStatus(), "no"))
                                            {
                                                intent.putExtra("exam" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("exam" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(19).getStatus(), "no") || Objects.equals(response.body().getModule().get(20).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(21).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(22).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(23).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(24).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(25).getStatus(), "no")
                                                    )
                                            {
                                                intent.putExtra("gallery" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("gallery" , "yes");
                                            }


                                            if (Objects.equals(response.body().getModule().get(27).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(28).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(32).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(33).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(34).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(35).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(36).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(37).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(38).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(39).getStatus(), "no"))
                                            {
                                                intent.putExtra("library" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("library" , "yes");
                                            }


                                            if (Objects.equals(response.body().getModule().get(29).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(30).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(31).getStatus(), "no")
                                                    )
                                            {
                                                intent.putExtra("attendance" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("attendance" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(43).getStatus(), "no"))
                                            {
                                                intent.putExtra("summary" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("summary" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(49).getStatus(), "no"))
                                            {
                                                intent.putExtra("time" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("time" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(51).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(52).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(53).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(54).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(55).getStatus(), "no")
                                                    )
                                            {
                                                intent.putExtra("stay" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("stay" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(44).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(45).getStatus(), "no")
                                                    )
                                            {
                                                intent.putExtra("survey" , "no");
                                            }
                                            else
                                            {
                                                intent.putExtra("survey" , "yes");
                                            }

                                            if (Objects.equals(response.body().getModule().get(46).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(47).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(48).getStatus(), "no")
                                                    || Objects.equals(response.body().getModule().get(50).getStatus(), "no")
                                                    )
                                            {

                                                intent.putExtra("profile" , "no");

                                            }
                                            else
                                            {
                                                intent.putExtra("profile" , "yes");
                                            }
                                            startActivity(intent);
                                            finish();

                                        }



                                    } else if (s.equals("Parent")) {

                                        b.school_id = response.body().getSchoolId();
                                        b.user_id = response.body().getId();
                                        b.user_type = response.body().getUserType();
                                        b.user_name=response.body().getName();
                                        b.user_class=response.body().getClassId();
                                        b.user_section=response.body().getSectionId();
                                        b.class_Name=response.body().getClassName();
                                        b.section_Name=response.body().getSectionName();
                                        b.studName = response.body().getName();

                                        edit.putString("type", s);
                                        edit.putString("username", Susername);
                                        edit.putString("pass", Spassword);
                                        edit.apply();

                                        Intent intent = new Intent(LoginPage.this, ParentHome.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    progress.setVisibility(View.GONE);


                                }
                            }

                            @Override
                            public void onFailure(Call<Loginbean> call, Throwable throwable) {
                                Toast.makeText(LoginPage.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                                progress.setVisibility(View.GONE);
                            }
                        });

                    } else {
                        password.setError("Invalid Password");
                    }
                } else {

                    username.setError("Invalid Username");
                }

            }
        });


    }
}
