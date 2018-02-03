package com.eduschool.eduschoolapp.StudentSummary;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.eduschool.eduschoolapp.ExamAndResults.ExamFrgmntOne;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

public class StudentSummaryParent extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    Toolbar toolbar;

    String classId , secId , stuId , className , sectionName , studentName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_summary_parent);

        Bundle b = getIntent().getExtras();


        try {
            classId = b.getString("class");
            secId = b.getString("section");
            stuId = b.getString("student");
            className = b.getString("cName");
            sectionName = b.getString("sName");
            studentName = b.getString("stName");
        }catch (Exception e)
        {
            User u = (User)getApplicationContext();
            classId = u.user_class;
            secId = u.user_section;
            stuId = u.user_id;
            className = u.class_Name;
            sectionName = u.section_Name;
            studentName = u.studName;

            e.printStackTrace();
        }


        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Student Summary");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Attendance"));
        tabLayout.addTab(tabLayout.newTab().setText("Fees Report"));
        tabLayout.addTab(tabLayout.newTab().setText("Result"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        viewPager.setOffscreenPageLimit(adapter.getCount() - 1);

        tabLayout.setupWithViewPager(viewPager);


    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        private String[] tabTitles = new String[]{"Profile", "Attendance", "Fees Report", "Result"};

        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    ParentProfileFrgmnt tab1 = new ParentProfileFrgmnt();

                    Bundle b = new Bundle();
                    b.putString("id" , stuId);
                    tab1.setArguments(b);
                    return tab1;
                case 1:
                    ParentAttendanceFrgmnt tab2 = new ParentAttendanceFrgmnt();

                    Bundle b1 = new Bundle();
                    b1.putString("cName" , className);
                    b1.putString("sName" , sectionName);
                    b1.putString("stName" , studentName);
                    b1.putString("cid" , classId);
                    b1.putString("sid" , secId);
                    b1.putString("stid" , stuId);

                    tab2.setArguments(b1);
                    return tab2;
                case 2:
                    ParentFeeFrgmnt tab3 = new ParentFeeFrgmnt();

                    Bundle b2 = new Bundle();
                    b2.putString("cName" , className);
                    b2.putString("sName" , sectionName);
                    b2.putString("stName" , studentName);
                    b2.putString("cid" , classId);
                    b2.putString("sid" , secId);
                    b2.putString("stid" , stuId);

                    tab3.setArguments(b2);

                    return tab3;
                case 3:
                    Exam1 tab4 = new Exam1();
                    Bundle b3 = new Bundle();
                    b3.putString("cName" , className);
                    b3.putString("sName" , sectionName);
                    b3.putString("stName" , studentName);
                    b3.putString("cid" , classId);
                    b3.putString("sid" , secId);
                    b3.putString("stid" , stuId);
                    tab4.setArguments(b3);
                    return tab4;

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
