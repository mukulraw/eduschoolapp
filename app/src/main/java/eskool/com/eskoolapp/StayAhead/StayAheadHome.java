package eskool.com.eskoolapp.StayAhead;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import eskool.com.eskoolapp.Home.ParentHome;
import eskool.com.eskoolapp.Home.TeacherHome;
import eskool.com.eskoolapp.HomeWork.TeacherHwFrgmntTwo;
import eskool.com.eskoolapp.Profile.ParentFragmentOne;
import eskool.com.eskoolapp.Profile.ParentFragmentTwo;
import eskool.com.eskoolapp.Profile.ParentProfile;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.User;

/**
 * Created by user on 6/26/2017.
 */

public class StayAheadHome extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Button change_password;
    Toolbar toolbar;


    public StayAheadHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.stay_ahead_home, container, false);

        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.tool_bar);
        change_password = (Button) view.findViewById(R.id.change_password);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setText("Wikipedia"));
        tabLayout.addTab(tabLayout.newTab().setText("Thesaurus"));
        tabLayout.addTab(tabLayout.newTab().setText("Dictionary"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapter adapter = new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        tabLayout.setupWithViewPager(viewPager);

        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.tool_bar);


        return view;
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        private String[] tabTitles = new String[]{"Wikipedia", "Thesaurus", "Dictionary"};

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    frgmnt1 tab1 = new frgmnt1();
                    return tab1;
                case 1:
                    frgmnt2 tab2 = new frgmnt2();
                    return tab2;
                case 2:
                    frgmnt3 tab3 = new frgmnt3();
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

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Stay Ahead / Score More");

        User u = (User) getContext().getApplicationContext();

        u.back = true;


    }
}
