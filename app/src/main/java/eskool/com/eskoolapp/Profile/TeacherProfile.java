package eskool.com.eskoolapp.Profile;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import eskool.com.eskoolapp.Home.TeacherHome;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.User;

/**
 * Created by User on 5/5/2017.
 */

public class TeacherProfile extends Fragment {
    Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Button change_password;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.teacher_profile, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);


        change_password = (Button) v.findViewById(R.id.change_password);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        change_password = (Button) v.findViewById(R.id.change_password);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) v.findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setText("Personal Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Emergency Contact Info"));
        tabLayout.addTab(tabLayout.newTab().setText("Work Info"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

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


    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("My Profile");

        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        private String[] tabTitles = new String[]{"Work Info","Personal Info", "Emergency Contact Info"};

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

