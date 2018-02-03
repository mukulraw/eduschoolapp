package com.eduschool.eduschoolapp.StudentSummary;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Profile.ParentFragmentOne;
import com.eduschool.eduschoolapp.Profile.ParentFragmentTwo;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.RoundedImageView;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.parentPersonalBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 6/27/2017.
 */

public class ParentProfileFrgmnt extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView name;
    RoundedImageView image;
    ProgressBar progress;
    String stuId;

    public ParentProfileFrgmnt() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.parent_profile1, container, false);

        stuId = getArguments().getString("id");

        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        name = (TextView)v.findViewById(R.id.name);
        image = (RoundedImageView)v.findViewById(R.id.image);

        progress = (ProgressBar)v.findViewById(R.id.progress);

        tabLayout.addTab(tabLayout.newTab().setText("Child Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Contact Info"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapter adapter = new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        tabLayout.setupWithViewPager(viewPager);





        progress.setVisibility(View.VISIBLE);


        final User u = (User) getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);


        Call<parentPersonalBean> call = cr.getParentPersonal(u.school_id , stuId);

        call.enqueue(new Callback<parentPersonalBean>() {
            @Override
            public void onResponse(Call<parentPersonalBean> call, Response<parentPersonalBean> response) {


                //name.setText(response.body().getName());

                name.setText(response.body().getName());

                ImageLoader loader = ImageLoader.getInstance();

                loader.displayImage(response.body().getProfilePic() , image);

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<parentPersonalBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });





        return v;
    }

    @Override
    public void onResume() {
        super.onResume();


        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }


    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        private String[] tabTitles = new String[]{"Child Info","Contact Info"};

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:

                    ParentFragmentTwo tab1 = new ParentFragmentTwo();
                    Bundle b = new Bundle();
                    b.putString("id" , stuId);
                    tab1.setArguments(b);
                    return tab1;

                case 1:
                    ParentFragmentOne tab2 = new ParentFragmentOne();
                    Bundle bq = new Bundle();
                    bq.putString("id" , stuId);
                    tab2.setArguments(bq);
                    return tab2;

                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }
}
