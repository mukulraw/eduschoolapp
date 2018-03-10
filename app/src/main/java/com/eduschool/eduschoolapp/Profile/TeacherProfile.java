package com.eduschool.eduschoolapp.Profile;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ChangePssPOJO.PasswrdChngebean;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.LoginPOJO.Loginbean;
import com.eduschool.eduschoolapp.LoginPages.LoginPage;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.RoundedImageView;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.teacherProfilePOJO.teacherProfileBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by User on 5/5/2017.
 */

public class TeacherProfile extends Fragment {
    Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView name;
    Button change_password;
    SharedPreferences pref;
    SharedPreferences.Editor edit;

    RoundedImageView image;

    ProgressBar progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.teacher_profile, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

        image = (RoundedImageView)v.findViewById(R.id.imageView_round);
        name = (TextView)v.findViewById(R.id.name);
        progress = (ProgressBar)v.findViewById(R.id.progress);

        change_password = (Button) v.findViewById(R.id.change_password);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        change_password = (Button) v.findViewById(R.id.change_password);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) v.findViewById(R.id.tabs);


        pref = getContext().getSharedPreferences("mypref", MODE_PRIVATE);
        edit = pref.edit();




        change_password.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.teacher_profile_popup);

                final EditText oldpass, newpass, confrmpass;
                final ProgressBar progress;
                Button submit;

                oldpass = (EditText) dialog.findViewById(R.id.oldpass);
                newpass = (EditText) dialog.findViewById(R.id.newpass);
                confrmpass = (EditText) dialog.findViewById(R.id.confrmpass);
                progress = (ProgressBar) dialog.findViewById(R.id.progress);
                submit = (Button) dialog.findViewById(R.id.submit);


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        final String Soldpass, Snewpass, SconfrmPass;
                        Soldpass = oldpass.getText().toString().trim();
                        Snewpass = newpass.getText().toString().trim();
                        SconfrmPass = confrmpass.getText().toString().trim();

                        //Toast.makeText(getContext(), b.school_id, Toast.LENGTH_SHORT).show();

                        if (Soldpass.length() > 0) {

                            if (Snewpass.length() > 0) {
                                if (SconfrmPass.length() > 0) {

                                    progress.setVisibility(View.VISIBLE);


                                    if (!Snewpass.equals(SconfrmPass)) {

                                        Toast.makeText(getContext(), "New Password and Confirm Password are not similar", Toast.LENGTH_SHORT).show();
                                        progress.setVisibility(View.GONE);
                                    } else {

                                        final User b = (User) getActivity().getApplicationContext();
                                        Retrofit retrofit = new Retrofit.Builder()
                                                .baseUrl(b.baseURL)
                                                .addConverterFactory(ScalarsConverterFactory.create())
                                                .addConverterFactory(GsonConverterFactory.create())
                                                .build();

                                        AllAPIs cr = retrofit.create(AllAPIs.class);


                                        Call<PasswrdChngebean> call1 = cr.chngrPass(b.school_id, b.user_type, Snewpass, Soldpass, b.user_id);


                                        call1.enqueue(new Callback<PasswrdChngebean>() {
                                            @Override
                                            public void onResponse(Call<PasswrdChngebean> call, Response<PasswrdChngebean> response) {


                                                if (response.body().getPasswordChange().get(0).getStatus().equals("1")){

                                                    edit.putString("pass", SconfrmPass);
                                                    edit.commit();

                                                    Toast.makeText(getContext(), "Password Changed Successfully, Please Login again", Toast.LENGTH_SHORT).show();
                                                    newpass.setText("");
                                                    oldpass.setText("");
                                                    confrmpass.setText("");
                                                    progress.setVisibility(View.GONE);

                                                    dialog.dismiss();



                                                    Intent intent = new Intent(getContext(), LoginPage.class);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                                    edit.remove("username");
                                                    edit.remove("type");
                                                    edit.remove("pass");
                                                    edit.apply();
                                                    getActivity().finish();
                                                    startActivity(intent);



                                                }
                                                else {
                                                    progress.setVisibility(View.GONE);
                                                    Toast.makeText(getContext(), "Incorrect details. Try Again !", Toast.LENGTH_SHORT).show();

                                                }

                                            }

                                            @Override
                                            public void onFailure(Call<PasswrdChngebean> call, Throwable throwable) {
                                                progress.setVisibility(View.GONE);
                                            }
                                        });


                                    }

                                } else {
                                    confrmpass.setError("Invalid Password");
                                }
                            } else {
                                newpass.setError("Invalid Password");
                            }
                        } else {
                            oldpass.setError("Invalid Password");
                        }
                    }
                });


                dialog.show();
            }
        });




        User u = (User) getContext().getApplicationContext();

        u.back = true;



        progress.setVisibility(View.VISIBLE);

        Log.d("asdasd" , "1");



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);

        Call<teacherProfileBean> call = cr.getTeacherProfile(u.school_id , u.user_id);

        call.enqueue(new Callback<teacherProfileBean>() {
            @Override
            public void onResponse(Call<teacherProfileBean> call, Response<teacherProfileBean> response) {

                Log.d("asdasd" , "response");

                name.setText(response.body().getTeacherName());

                ImageLoader loader = ImageLoader.getInstance();
                loader.displayImage(response.body().getProfilePic() , image);

                tabLayout.addTab(tabLayout.newTab().setText("Work Info"));
                tabLayout.addTab(tabLayout.newTab().setText("Personal Info"));
                tabLayout.addTab(tabLayout.newTab().setText("Education Details"));
                tabLayout.addTab(tabLayout.newTab().setText("Request Leave"));
                tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

                final PagerAdapter adapter = new PagerAdapter
                        (getChildFragmentManager(), tabLayout.getTabCount() , response.body());
                viewPager.setAdapter(adapter);
                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


                tabLayout.setupWithViewPager(viewPager);


                tabLayout.getTabAt(0).setText("Work Info");
                tabLayout.getTabAt(1).setText("Personal Info");
                tabLayout.getTabAt(2).setText("Education Details");
                tabLayout.getTabAt(3).setText("Request Leave");


                progress.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<teacherProfileBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Log.d("asdasd" , t.toString());
            }
        });


        return v;
    }


    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("My Profile");









    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        teacherProfileBean be;
        private String[] tabTitles = new String[]{"Work Info", "Personal Info", "Education Details", "Request Leave"};

        public PagerAdapter(FragmentManager fm, int NumOfTabs , teacherProfileBean be) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
            this.be = be;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    TeacherFragmentOne tab1 = new TeacherFragmentOne();

                    Bundle b = new Bundle();
                    b.putString("empId" , be.getWorkInfo().getEmpId());
                    b.putString("joinDate" , be.getWorkInfo().getJoiningDate());
                    b.putString("category" , be.getWorkInfo().getCategory());
                    b.putString("department" , be.getWorkInfo().getDepartment());
                    b.putString("position" , be.getWorkInfo().getPosition());
                    b.putString("email" , be.getWorkInfo().getEmail());
                    b.putString("exp" , be.getWorkInfo().getTotalExpirence());

                    tab1.setArguments(b);

                    return tab1;

                case 1:
                    TeacherFrgmntTwo tab2 = new TeacherFrgmntTwo();

                    Bundle b1 = new Bundle();
                    b1.putString("dob" , be.getPersionalInfo().getDateOfBirth());
                    b1.putString("marital" , be.getPersionalInfo().getMaritalStatus());
                    b1.putString("spouse" , be.getPersionalInfo().getSpouseName());
                    b1.putString("address" , be.getPersionalInfo().getPresentAddress());
                    b1.putString("country" , be.getPersionalInfo().getCountry());
                    b1.putString("state" , be.getPersionalInfo().getState());
                    b1.putString("city" , be.getPersionalInfo().getCity());

                    tab2.setArguments(b1);

                    return tab2;
                case 2:
                    TeacherFragmentThree tab3 = new TeacherFragmentThree();
                    tab3.setData(be.getEducationDetail());
                    return tab3;
                case 3:
                    TeacherFrmgntFour tab4 = new TeacherFrmgntFour();
                    return tab4;

                default:
                    return null;
            }
        }

        /*@Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }*/

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
}

