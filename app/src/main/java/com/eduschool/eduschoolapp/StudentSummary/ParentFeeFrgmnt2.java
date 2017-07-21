package com.eduschool.eduschoolapp.StudentSummary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eduschool.eduschoolapp.Fees.one;
import com.eduschool.eduschoolapp.R;

/**
 * Created by user on 6/29/2017.
 */

public class ParentFeeFrgmnt2 extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.parent_fee_frgmnt2, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Jan"));
        tabLayout.addTab(tabLayout.newTab().setText("Feb"));
        tabLayout.addTab(tabLayout.newTab().setText("March"));
        tabLayout.addTab(tabLayout.newTab().setText("April"));
        tabLayout.addTab(tabLayout.newTab().setText("May"));
        tabLayout.addTab(tabLayout.newTab().setText("June"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);




        final PagerAdapter adapter = new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.setupWithViewPager(viewPager);





        return view;
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        private String[] tabTitles = new String[]{"Jan","Feb","March","April","May","June"};

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    One tab1 = new One();
                    return tab1;
                case 1:
                    One tab2 = new One();
                    return tab2;
                case 2:
                    One tab3 = new One();
                    return tab3;
                case 3:
                    One tab4 = new One();
                    return tab4;
                case 4:
                    One tab5 = new One();
                    return tab5;
                case 5:
                    One tab6 = new One();
                    return tab6;
                case 6:
                    One tab7 = new One();
                    return tab7;

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

