package eskool.com.eskoolapp.Home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import eskool.com.eskoolapp.Academic.TeacherAcademic;
import eskool.com.eskoolapp.Attendance.Attendance;
import eskool.com.eskoolapp.Attendance.ParentAttendanceFrgmnt;
import eskool.com.eskoolapp.ClassWork.AddClassWork;
import eskool.com.eskoolapp.ExamAndResults.TeacherFrgmnt1;
import eskool.com.eskoolapp.Gallery.GalleryTeacher;
import eskool.com.eskoolapp.HomeWork.TeacherHw;
import eskool.com.eskoolapp.Profile.TeacherProfile;
import eskool.com.eskoolapp.R;
import eskool.com.eskoolapp.TimeTable.TimeTableTeacher;
import eskool.com.eskoolapp.User;

public class TeacherHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Home");
        toolbar.setTitleTextColor(0xFFFFFFFF);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        TeacherHomeFrgmnt frag1 = new TeacherHomeFrgmnt();
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
            Attendance frag1 = new Attendance();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();


        } else if (id == R.id.class_work) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            AddClassWork frag1 = new AddClassWork();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.home_work) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            TeacherHw frag1 = new TeacherHw();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.academic) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            TeacherAcademic frag1 = new TeacherAcademic();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();

        }

        else if (id == R.id.profile) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
           TeacherProfile frag1 = new TeacherProfile();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        }

        else if (id == R.id.time_table) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            TimeTableTeacher frag1 = new TimeTableTeacher();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.home) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            TeacherHomeFrgmnt frag1 = new TeacherHomeFrgmnt();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.gallery) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            GalleryTeacher frag1 = new GalleryTeacher();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        }

        else if (id == R.id.exam_result) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            TeacherFrgmnt1 frag1 = new TeacherFrgmnt1();
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
