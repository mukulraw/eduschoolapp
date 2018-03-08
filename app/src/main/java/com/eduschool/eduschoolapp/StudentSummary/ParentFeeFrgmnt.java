package com.eduschool.eduschoolapp.StudentSummary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.parentFeesPOJO.FeesDescription;
import com.eduschool.eduschoolapp.parentFeesPOJO.FeesDetail;
import com.eduschool.eduschoolapp.parentFeesPOJO.parentFeesBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 6/27/2017.
 */

public class ParentFeeFrgmnt extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    String cName , sName , stName , cid , sid , stid;
    ProgressBar progress;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.parent_fee_frgmnt2, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Jan"));
        tabLayout.addTab(tabLayout.newTab().setText("Feb"));
        tabLayout.addTab(tabLayout.newTab().setText("March"));
        tabLayout.addTab(tabLayout.newTab().setText("April"));
        tabLayout.addTab(tabLayout.newTab().setText("May"));
        tabLayout.addTab(tabLayout.newTab().setText("June"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        Bundle b = getArguments();

        cName = b.getString("cName");
        sName = b.getString("sName");
        stName = b.getString("stName");
        cid = b.getString("cid");
        sid = b.getString("sid");
        stid = b.getString("stid");






        progress.setVisibility(View.VISIBLE);


        User u = (User) getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);


        Call<parentFeesBean> call = cr.getParentFees(u.school_id , stid , cid , sid);

        call.enqueue(new Callback<parentFeesBean>() {
            @Override
            public void onResponse(Call<parentFeesBean> call, Response<parentFeesBean> response) {


                try {
                    PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(), tabLayout.getTabCount() , response.body().getFeesDetails());
                    viewPager.setAdapter(adapter);
                    viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
                    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


                    tabLayout.setupWithViewPager(viewPager);

                }catch (Exception e)
                {
                    e.printStackTrace();
                }



                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<parentFeesBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Log.d("asdasd" , t.toString());
            }
        });





        return view;
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        List<FeesDetail> list = new ArrayList<>();
        private String[] tabTitles = new String[]{"April","May","June","July","August","September","October","November","December","Jan","Feb","March"};

        public PagerAdapter(FragmentManager fm, int NumOfTabs , List<FeesDetail> list) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            One tab = new One();
            tab.setData(list.get(position).getFeesDescription() , list.get(position).getPaidStatus() , list.get(position).getTotalFee());
            return tab;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    public static class One extends Fragment {
        LinearLayout layout;

        String status , total;
        List<FeesDescription> list = new ArrayList<>();

        TextView admission , tuition , exam , lab , tot , paid;



        public void setData(List<FeesDescription> list , String status , String total)
        {
            this.list = list;
            this.status = status;
            this.total = total;
        }




        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.one, container, false);

            admission = (TextView)view.findViewById(R.id.admision);
            tuition = (TextView)view.findViewById(R.id.tuition);
            exam = (TextView)view.findViewById(R.id.exam);
            lab = (TextView)view.findViewById(R.id.lab);
            tot = (TextView)view.findViewById(R.id.total);
            paid = (TextView)view.findViewById(R.id.paid);

            layout = (LinearLayout) view.findViewById(R.id.layout);

            User u = (User)getContext().getApplicationContext();

            if (Objects.equals(u.user_type, "Teacher"))
            {
                if (Objects.equals(status, "Unpaid"))
                {
                    paid.setText("UNPAID");
                }
                else
                {
                    paid.setText("PAID");
                }
                paid.setVisibility(View.VISIBLE);
            }
            else if (Objects.equals(u.user_type, "Parent"))
            {
                if (Objects.equals(status, "Unpaid"))
                {
                    paid.setText("UNPAID");
                }
                else
                {
                    paid.setText("PAID");
                }
                paid.setVisibility(View.VISIBLE);
            }





            tot.setText("INR " + total);

            try {
                admission.setText("INR " + list.get(0).getAmount());
                exam.setText("INR " + list.get(1).getAmount());
                tuition.setText("INR " + list.get(2).getAmount());
                lab.setText("INR " + list.get(3).getAmount());

            }catch (Exception e)
            {
                e.printStackTrace();
            }




            return view;
        }
    }

}


