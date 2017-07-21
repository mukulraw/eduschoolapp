package com.eduschool.eduschoolapp.StudentSummary;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.eduschool.eduschoolapp.Profile.TeacherFragmentOne;
import com.eduschool.eduschoolapp.Profile.TeacherFragmentThree;
import com.eduschool.eduschoolapp.Profile.TeacherFrgmntTwo;
import com.eduschool.eduschoolapp.R;

/**
 * Created by user on 6/27/2017.
 */

public class TeacherProfileFrgmnt extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    Button change_password;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.teacher_profile1, container, false);


        change_password = (Button) v.findViewById(R.id.change_password);
        change_password = (Button) v.findViewById(R.id.change_password);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) v.findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setText("Personal Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Emergency Contact Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Work Info"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        final PagerAdapter adapter = new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.setupWithViewPager(viewPager);


        change_password.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.parent_profile_popup);


                // set the custom dialog components - text, image and button

                dialog.show();
            }
        });


        return v;
    }


    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        private String[] tabTitles = new String[]{"Work Info", "Personal Info", "Emergency Contact Info"};

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    TeacherFragmentOne tab1 = new TeacherFragmentOne();
                    return tab1;
                case 1:
                    TeacherFrgmntTwo tab2 = new TeacherFrgmntTwo();
                    return tab2;
                case 2:
                    TeacherFragmentThree tab3 = new TeacherFragmentThree();
                    return tab3;

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

