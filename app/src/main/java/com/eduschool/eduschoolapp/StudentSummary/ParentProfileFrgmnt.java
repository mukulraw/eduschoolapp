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

import com.eduschool.eduschoolapp.Profile.ParentFragmentOne;
import com.eduschool.eduschoolapp.Profile.ParentFragmentTwo;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

/**
 * Created by user on 6/27/2017.
 */

public class ParentProfileFrgmnt extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public ParentProfileFrgmnt() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.parent_profile1, container, false);

        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) v.findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setText("Contact Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Child Info"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapter adapter = new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        tabLayout.setupWithViewPager(viewPager);


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
        private String[] tabTitles = new String[]{"Contact Info", "Child Info"};

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    ParentFragmentOne tab1 = new ParentFragmentOne();
                    return tab1;
                case 1:
                    ParentFragmentTwo tab2 = new ParentFragmentTwo();
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
