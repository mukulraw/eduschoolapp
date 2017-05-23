package eskool.com.eskoolapp.Home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import eskool.com.eskoolapp.Academic.ParentAcademic;
import eskool.com.eskoolapp.Attendance.ParentAttendanceFrgmnt;
import eskool.com.eskoolapp.ClassWork.ClassWorkFrgmnt;
import eskool.com.eskoolapp.ExamAndResults.ExamFrgmntOne;
import eskool.com.eskoolapp.Fees.Fees;
import eskool.com.eskoolapp.Gallery.GalleryParent;
import eskool.com.eskoolapp.HomeWork.HomeWorkFrgmnt;
import eskool.com.eskoolapp.OnBackPressedListener;
import eskool.com.eskoolapp.OnlineTest.OnlineTestSummery;
import eskool.com.eskoolapp.Profile.ParentProfile;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.RaiseRequest.RaiseReqquestHome;
import eskool.com.eskoolapp.TimeTable.TimeTableParent;
import eskool.com.eskoolapp.Transport.TransportParent;
import eskool.com.eskoolapp.User;

public class ParentHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_home);


        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Home");
        toolbar.setTitleTextColor(0xFFFFFFFF);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ParentHomeFrgmnt frag1 = new ParentHomeFrgmnt();
        ft.replace(R.id.replace, frag1);
        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);


        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    boolean doubleBackToExitPressedOnce = false;

    @SuppressLint("RestrictedApi")
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        boolean b;

        User u = (User) getApplicationContext();
        b = u.back;

        if (!b) {
            super.onBackPressed();
        } else {

            if (doubleBackToExitPressedOnce) {
                //super.onBackPressed();

                finish();

                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.attendance) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ParentAttendanceFrgmnt frag1 = new ParentAttendanceFrgmnt();
            ft.replace(R.id.replace, frag1);
            /*ft.addToBackStack(null);*/
            ft.commit();


        } else if (id == R.id.home_work) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            HomeWorkFrgmnt frag1 = new HomeWorkFrgmnt();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.class_work) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ClassWorkFrgmnt frag1 = new ClassWorkFrgmnt();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.academic) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ParentAcademic frag1 = new ParentAcademic();
            ft.replace(R.id.replace, frag1);
            // ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.online_test) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            OnlineTestSummery frag1 = new OnlineTestSummery();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.exam_result) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ExamFrgmntOne frag1 = new ExamFrgmntOne();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.raise_request) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            RaiseReqquestHome frag1 = new RaiseReqquestHome();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.time_table) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            TimeTableParent frag1 = new TimeTableParent();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.gallery) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            GalleryParent frag1 = new GalleryParent();
            ft.replace(R.id.replace, frag1);
            // ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.profile) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ParentProfile frag1 = new ParentProfile();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.home) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ParentHomeFrgmnt frag1 = new ParentHomeFrgmnt();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.transport) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            TransportParent frag1 = new TransportParent();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.fees) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Fees frag1 = new Fees();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Home");
        User u = (User) getApplicationContext();

        u.back = true;
    }

}
