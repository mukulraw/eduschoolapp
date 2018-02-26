package com.eduschool.eduschoolapp.Home;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.Attendance.ParentAttendanceFrgmnt;
import com.eduschool.eduschoolapp.Calender.CalenderParent;
import com.eduschool.eduschoolapp.Calender.CalenderTeacher;
import com.eduschool.eduschoolapp.ClassWork.ClassWorkFrgmnt;
import com.eduschool.eduschoolapp.ExamAndResults.ExamFrgmntOne;
import com.eduschool.eduschoolapp.Fees.Fees;
import com.eduschool.eduschoolapp.Gallery.GalleryParent;
import com.eduschool.eduschoolapp.HomeWork.HomeWorkFrgmnt;
import com.eduschool.eduschoolapp.Library.ParentLibrary1;
import com.eduschool.eduschoolapp.Library.TeacherLibrary1;
import com.eduschool.eduschoolapp.LoginPages.LoginPage;
import com.eduschool.eduschoolapp.LoginPages.MainLogin;
import com.eduschool.eduschoolapp.Notifications.ParentNotificationFrgmnt;
import com.eduschool.eduschoolapp.Notifications.TeacherNotificationFrgmnt;
import com.eduschool.eduschoolapp.OnlineTest.OnlineTestSummery;
import com.eduschool.eduschoolapp.Profile.ParentProfile;
import com.eduschool.eduschoolapp.RaiseRequest.RaiseReqquestHome;
import com.eduschool.eduschoolapp.StayAhead.StayAheadHome;
import com.eduschool.eduschoolapp.StayAhead.StayAheadHomeTeacher;
import com.eduschool.eduschoolapp.StudentSummary.StudentReportFrgmnt;
import com.eduschool.eduschoolapp.StudentSummary.StudentSummaryParent;
import com.eduschool.eduschoolapp.StudentSummary.StudentSummaryTeacher;
import com.eduschool.eduschoolapp.Survey.SurveyFrgmntParent1;
import com.eduschool.eduschoolapp.SyllabusManagement.ParentAcademic;
import com.eduschool.eduschoolapp.SyllabusManagement.TeacherAcademic;
import com.eduschool.eduschoolapp.Attendance.Attendance;
import com.eduschool.eduschoolapp.ClassWork.Teacherclswrk;
import com.eduschool.eduschoolapp.Communication.Frgmnt1;
import com.eduschool.eduschoolapp.Events.EventsFrgmnt1;
import com.eduschool.eduschoolapp.ExamAndResults.TeacherFrgmnt1;
import com.eduschool.eduschoolapp.Gallery.GalleryTeacher;
import com.eduschool.eduschoolapp.HomeWork.TeacherHw;
import com.eduschool.eduschoolapp.Profile.TeacherProfile;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.Survey.SurveyFrgmnt1;
import com.eduschool.eduschoolapp.TimeTable.TimeTableParent;
import com.eduschool.eduschoolapp.TimeTable.TimeTableTeacher;
import com.eduschool.eduschoolapp.Transport.TransportParent;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.WishingCards.WishingCardsFrgmnt;

import java.util.Objects;

public class TeacherHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    LinearLayout hotelName, comm_lay;
    DrawerLayout drawer;
    SharedPreferences pref;
    SharedPreferences.Editor edit;

    TextView academic, comm, attendance, logout, home, events, notification, communication1, homewrk, classwrk, syllabus, exm, calendar, studentSummary, library, timeTable, gallery, survey, stayAhead, raiseRequest, profile;
    TextView academicline, attendanceline, eventsline, communication1line, homewrkline, classwrkline, syllabusline, exmline, calendarline, studentSummaryline, libraryline, timeTableline, galleryline, surveyline, stayAheadline, profileline;
    boolean isCollapsed = true;
    TextView name;
    boolean isCollapsed1 = true;

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

        pref = getSharedPreferences("mypref", MODE_PRIVATE);
        edit = pref.edit();

        name = (TextView)findViewById(R.id.name);


        User u = (User)getApplicationContext();

        name.setText("Welcome " + u.user_name);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        TeacherHomeFrgmnt frag1 = new TeacherHomeFrgmnt();
        ft.replace(R.id.replace, frag1);
        ft.commit();
        hotelName = (LinearLayout) findViewById(R.id.hotel_name_menu);
        comm_lay = (LinearLayout) findViewById(R.id.comm_lay);


        drawer = (DrawerLayout) findViewById(R.id.drawer_asiana);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        academic = (TextView) findViewById(R.id.academic);

        comm = (TextView) findViewById(R.id.comm);
        attendance = (TextView) findViewById(R.id.attendance);
        attendanceline = (TextView) findViewById(R.id.attendance_line);
        logout = (TextView) findViewById(R.id.logout);
        home = (TextView) findViewById(R.id.home1);
        notification = (TextView) findViewById(R.id.notification1);
        homewrk = (TextView) findViewById(R.id.homewrk);
        homewrkline = (TextView) findViewById(R.id.homework_line);
        classwrk = (TextView) findViewById(R.id.classwrk);
        classwrkline = (TextView) findViewById(R.id.classwork_line);
        syllabus = (TextView) findViewById(R.id.syllabus);
        syllabusline = (TextView) findViewById(R.id.syllabus_line);
        exm = (TextView) findViewById(R.id.exm);
        exmline = (TextView) findViewById(R.id.exam_line);
        studentSummary = (TextView) findViewById(R.id.studentSummary);
        studentSummaryline = (TextView) findViewById(R.id.summary_line);
        library = (TextView) findViewById(R.id.library1);
        libraryline = (TextView) findViewById(R.id.library_line);
        events = (TextView) findViewById(R.id.event);
        eventsline = (TextView) findViewById(R.id.event_line);
        timeTable = (TextView) findViewById(R.id.timeTable);
        timeTableline = (TextView) findViewById(R.id.timetable_line);
        gallery = (TextView) findViewById(R.id.gallery1);
        galleryline = (TextView) findViewById(R.id.gallery_line);
        survey = (TextView) findViewById(R.id.survey1);
        surveyline = (TextView) findViewById(R.id.survey_line);
        calendar = (TextView) findViewById(R.id.calendar);
        calendarline = (TextView) findViewById(R.id.calendar_line);
        stayAhead = (TextView) findViewById(R.id.stayAhead);
        stayAheadline = (TextView) findViewById(R.id.stay_line);
        raiseRequest = (TextView) findViewById(R.id.raiseRequest);
        profile = (TextView) findViewById(R.id.profile);
        profileline = (TextView) findViewById(R.id.profile_line);
        communication1 = (TextView) findViewById(R.id.communication1);
        communication1line = (TextView) findViewById(R.id.communication_line);



        if (Objects.equals(getIntent().getStringExtra("teacher"), "no"))
        {

            attendance.setVisibility(View.GONE);
            attendanceline.setVisibility(View.GONE);



            if (Objects.equals(getIntent().getStringExtra("academic"), "no"))
            {
                syllabus.setVisibility(View.GONE);
                syllabusline.setVisibility(View.GONE);
            }
            else
            {
                syllabus.setVisibility(View.VISIBLE);
                syllabusline.setVisibility(View.VISIBLE);
            }



            if (Objects.equals(getIntent().getStringExtra("classwork"), "no"))
            {
                classwrk.setVisibility(View.GONE);
                classwrkline.setVisibility(View.GONE);
            }
            else
            {
                classwrk.setVisibility(View.VISIBLE);
                classwrkline.setVisibility(View.VISIBLE);
            }


            if (Objects.equals(getIntent().getStringExtra("homework"), "no"))
            {
                homewrk.setVisibility(View.GONE);
                homewrkline.setVisibility(View.GONE);
            }
            else
            {
                homewrk.setVisibility(View.VISIBLE);
                homewrkline.setVisibility(View.VISIBLE);
            }


            if (Objects.equals(getIntent().getStringExtra("event"), "no"))
            {
                events.setVisibility(View.GONE);
                eventsline.setVisibility(View.GONE);
            }
            else
            {
                events.setVisibility(View.VISIBLE);
                eventsline.setVisibility(View.VISIBLE);
            }

            if (Objects.equals(getIntent().getStringExtra("communication"), "no"))
            {
                communication1.setVisibility(View.GONE);
                communication1line.setVisibility(View.GONE);
            }
            else
            {
                communication1.setVisibility(View.VISIBLE);
                communication1line.setVisibility(View.VISIBLE);
            }

            if (Objects.equals(getIntent().getStringExtra("calendar"), "no"))
            {
                calendar.setVisibility(View.GONE);
                calendarline.setVisibility(View.GONE);
            }
            else
            {
                calendar.setVisibility(View.VISIBLE);
                calendarline.setVisibility(View.VISIBLE);
            }

            if (Objects.equals(getIntent().getStringExtra("exam"), "no"))
            {
                exm.setVisibility(View.GONE);
                exmline.setVisibility(View.GONE);
            }
            else
            {
                exm.setVisibility(View.VISIBLE);
                exmline.setVisibility(View.VISIBLE);
            }

            if (Objects.equals(getIntent().getStringExtra("gallery"), "no"))
            {
                gallery.setVisibility(View.GONE);
                galleryline.setVisibility(View.GONE);
            }
            else
            {
                gallery.setVisibility(View.VISIBLE);
                galleryline.setVisibility(View.VISIBLE);
            }

            if (Objects.equals(getIntent().getStringExtra("library"), "no"))
            {
                library.setVisibility(View.GONE);
                libraryline.setVisibility(View.GONE);
            }
            else
            {
                library.setVisibility(View.VISIBLE);
                libraryline.setVisibility(View.VISIBLE);
            }

            if (Objects.equals(getIntent().getStringExtra("summary"), "no"))
            {
                studentSummary.setVisibility(View.GONE);
                studentSummaryline.setVisibility(View.GONE);
            }
            else
            {
                studentSummary.setVisibility(View.VISIBLE);
                studentSummaryline.setVisibility(View.VISIBLE);
            }
            if (Objects.equals(getIntent().getStringExtra("time"), "no"))
            {
                timeTable.setVisibility(View.GONE);
                timeTableline.setVisibility(View.GONE);
            }
            else
            {
                timeTable.setVisibility(View.VISIBLE);
                timeTableline.setVisibility(View.VISIBLE);
            }

            if (Objects.equals(getIntent().getStringExtra("stay"), "no"))
            {
                stayAhead.setVisibility(View.GONE);
                stayAheadline.setVisibility(View.GONE);
            }
            else
            {
                stayAhead.setVisibility(View.VISIBLE);
                stayAheadline.setVisibility(View.VISIBLE);
            }

            if (Objects.equals(getIntent().getStringExtra("survey"), "no"))
            {
                survey.setVisibility(View.GONE);
                surveyline.setVisibility(View.GONE);
            }
            else
            {
                survey.setVisibility(View.VISIBLE);
                surveyline.setVisibility(View.VISIBLE);
            }


            if (Objects.equals(getIntent().getStringExtra("profile"), "no"))
            {
                profile.setVisibility(View.GONE);
                profileline.setVisibility(View.GONE);
            }
            else
            {
                profile.setVisibility(View.VISIBLE);
                profileline.setVisibility(View.VISIBLE);
            }


        }
        else
        {

            attendance.setVisibility(View.VISIBLE);
            attendanceline.setVisibility(View.VISIBLE);




            if (Objects.equals(getIntent().getStringExtra("academic"), "no"))
            {
                syllabus.setVisibility(View.GONE);
                syllabusline.setVisibility(View.GONE);
            }
            else
            {
                syllabus.setVisibility(View.VISIBLE);
                syllabusline.setVisibility(View.VISIBLE);
            }



            if (Objects.equals(getIntent().getStringExtra("classwork"), "no"))
            {
                classwrk.setVisibility(View.GONE);
                classwrkline.setVisibility(View.GONE);
            }
            else
            {
                classwrk.setVisibility(View.VISIBLE);
                classwrkline.setVisibility(View.VISIBLE);
            }


            if (Objects.equals(getIntent().getStringExtra("homework"), "no"))
            {
                homewrk.setVisibility(View.GONE);
                homewrkline.setVisibility(View.GONE);
            }
            else
            {
                homewrk.setVisibility(View.VISIBLE);
                homewrkline.setVisibility(View.VISIBLE);
            }


            if (Objects.equals(getIntent().getStringExtra("event"), "no"))
            {
                events.setVisibility(View.GONE);
                eventsline.setVisibility(View.GONE);
            }
            else
            {
                events.setVisibility(View.VISIBLE);
                eventsline.setVisibility(View.VISIBLE);
            }

            if (Objects.equals(getIntent().getStringExtra("communication"), "no"))
            {
                communication1.setVisibility(View.GONE);
                communication1line.setVisibility(View.GONE);
            }
            else
            {
                communication1.setVisibility(View.VISIBLE);
                communication1line.setVisibility(View.VISIBLE);
            }

            if (Objects.equals(getIntent().getStringExtra("calendar"), "no"))
            {
                calendar.setVisibility(View.GONE);
                calendarline.setVisibility(View.GONE);
            }
            else
            {
                calendar.setVisibility(View.VISIBLE);
                calendarline.setVisibility(View.VISIBLE);
            }

            if (Objects.equals(getIntent().getStringExtra("exam"), "no"))
            {
                exm.setVisibility(View.GONE);
                exmline.setVisibility(View.GONE);
            }
            else
            {
                exm.setVisibility(View.VISIBLE);
                exmline.setVisibility(View.VISIBLE);
            }

            if (Objects.equals(getIntent().getStringExtra("gallery"), "no"))
            {
                gallery.setVisibility(View.GONE);
                galleryline.setVisibility(View.GONE);
            }
            else
            {
                gallery.setVisibility(View.VISIBLE);
                galleryline.setVisibility(View.VISIBLE);
            }

            if (Objects.equals(getIntent().getStringExtra("library"), "no"))
            {
                library.setVisibility(View.GONE);
                libraryline.setVisibility(View.GONE);
            }
            else
            {
                library.setVisibility(View.VISIBLE);
                libraryline.setVisibility(View.VISIBLE);
            }

            if (Objects.equals(getIntent().getStringExtra("summary"), "no"))
            {
                studentSummary.setVisibility(View.GONE);
                studentSummaryline.setVisibility(View.GONE);
            }
            else
            {
                studentSummary.setVisibility(View.VISIBLE);
                studentSummaryline.setVisibility(View.VISIBLE);
            }
            if (Objects.equals(getIntent().getStringExtra("time"), "no"))
            {
                timeTable.setVisibility(View.GONE);
                timeTableline.setVisibility(View.GONE);
            }
            else
            {
                timeTable.setVisibility(View.VISIBLE);
                timeTableline.setVisibility(View.VISIBLE);
            }

            if (Objects.equals(getIntent().getStringExtra("stay"), "no"))
            {
                stayAhead.setVisibility(View.GONE);
                stayAheadline.setVisibility(View.GONE);
            }
            else
            {
                stayAhead.setVisibility(View.VISIBLE);
                stayAheadline.setVisibility(View.VISIBLE);
            }

            if (Objects.equals(getIntent().getStringExtra("survey"), "no"))
            {
                survey.setVisibility(View.GONE);
                surveyline.setVisibility(View.GONE);
            }
            else
            {
                survey.setVisibility(View.VISIBLE);
                surveyline.setVisibility(View.VISIBLE);
            }


            if (Objects.equals(getIntent().getStringExtra("profile"), "no"))
            {
                profile.setVisibility(View.GONE);
                profileline.setVisibility(View.GONE);
            }
            else
            {
                profile.setVisibility(View.VISIBLE);
                profileline.setVisibility(View.VISIBLE);
            }

            if (Objects.equals(getIntent().getStringExtra("attendance"), "no"))
            {
                attendance.setVisibility(View.GONE);
                attendanceline.setVisibility(View.GONE);
            }
            else
            {
                attendance.setVisibility(View.VISIBLE);
                attendanceline.setVisibility(View.VISIBLE);
            }


        }



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(TeacherHome.this);

                builder.setMessage("Are you sure you want to logout?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {


                        Intent intent = new Intent(TeacherHome.this, LoginPage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                        edit.remove("username");
                        edit.remove("type");
                        edit.remove("pass");
                        edit.apply();
                        finish();
                        startActivity(intent);
                        dialog.dismiss();


                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        if (isCollapsed) {
            academic.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ddown, 0);
            hotelName.setVisibility(View.GONE);
        } else {
            academic.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.uup, 0);
            hotelName.setVisibility(View.VISIBLE);
        }

        academic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCollapsed) {
                    academic.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.uup, 0);
                    hotelName.setVisibility(View.VISIBLE);
                    isCollapsed = false;
                } else {
                    academic.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ddown, 0);
                    hotelName.setVisibility(View.GONE);
                    isCollapsed = true;
                }


            }
        });

        if (isCollapsed1) {
            comm.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ddown, 0);
            comm_lay.setVisibility(View.GONE);
        } else {
            comm.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.uup, 0);
            comm_lay.setVisibility(View.VISIBLE);
        }

        comm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCollapsed1) {
                    comm.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.uup, 0);
                    comm_lay.setVisibility(View.VISIBLE);
                    isCollapsed1 = false;
                } else {
                    comm.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ddown, 0);
                    comm_lay.setVisibility(View.GONE);
                    isCollapsed1 = true;
                }


            }
        });


        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Attendance frag1 = new Attendance();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();

                drawer.closeDrawer(GravityCompat.START);

            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherHomeFrgmnt frag1 = new TeacherHomeFrgmnt();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherNotificationFrgmnt frag1 = new TeacherNotificationFrgmnt();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        homewrk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherHw frag1 = new TeacherHw();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        classwrk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Teacherclswrk frag1 = new Teacherclswrk();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherAcademic frag1 = new TeacherAcademic();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        exm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherFrgmnt1 frag1 = new TeacherFrgmnt1();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });


        studentSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Intent intent = new Intent(getApplication(), StudentSummaryTeacher.class);
                startActivity(intent);
                drawer.closeDrawer(GravityCompat.START);*/

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                StudentReportFrgmnt frag1 = new StudentReportFrgmnt();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherLibrary1 frag1 = new TeacherLibrary1();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        timeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TimeTableTeacher frag1 = new TimeTableTeacher();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                GalleryTeacher frag1 = new GalleryTeacher();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });


        survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                SurveyFrgmnt1 frag1 = new SurveyFrgmnt1();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                CalenderTeacher frag1 = new CalenderTeacher();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        stayAhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                StayAheadHomeTeacher frag1 = new StayAheadHomeTeacher();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        communication1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Frgmnt1 frag1 = new Frgmnt1();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                EventsFrgmnt1 frag1 = new EventsFrgmnt1();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherProfile frag1 = new TeacherProfile();
                ft.replace(R.id.replace, frag1);
                //ft.addToBackStack(null);
                ft.commit();
                drawer.closeDrawer(GravityCompat.START);

            }
        });

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

            int count = getFragmentManager().getBackStackEntryCount();

            if (count == 0) {
                super.onBackPressed();
                //additional code
            } else {
                getFragmentManager().popBackStack();
            }

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
            Teacherclswrk frag1 = new Teacherclswrk();
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

        } else if (id == R.id.syllabus_management) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            TeacherAcademic frag1 = new TeacherAcademic();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.survey) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            SurveyFrgmnt1 frag1 = new SurveyFrgmnt1();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.calender) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            CalenderTeacher frag1 = new CalenderTeacher();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.events) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            EventsFrgmnt1 frag1 = new EventsFrgmnt1();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.student_summery) {

            Intent intent = new Intent(TeacherHome.this, StudentSummaryTeacher.class);
            startActivity(intent);

        } else if (id == R.id.library) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            TeacherLibrary1 frag1 = new TeacherLibrary1();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.stay_ahead) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            StayAheadHomeTeacher frag1 = new StayAheadHomeTeacher();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.profile) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            TeacherProfile frag1 = new TeacherProfile();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.time_table) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            TimeTableTeacher frag1 = new TimeTableTeacher();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();

        } else if (id == R.id.notification) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            TeacherNotificationFrgmnt frag1 = new TeacherNotificationFrgmnt();
            ft.replace(R.id.replace, frag1);
            // ft.addToBackStack(null);
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
        } else if (id == R.id.exam_result) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            TeacherFrgmnt1 frag1 = new TeacherFrgmnt1();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.communication) {


            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            Frgmnt1 frag1 = new Frgmnt1();
            ft.replace(R.id.replace, frag1);
            //ft.addToBackStack(null);
            ft.commit();
        } else if (id == R.id.log_out) {


            AlertDialog.Builder builder = new AlertDialog.Builder(TeacherHome.this);

            builder.setTitle("Confirm");
            builder.setMessage("Are you sure you want to logout?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // Do nothing but close the dialog

                    dialog.dismiss();
                    Intent intent = new Intent(TeacherHome.this, MainLogin.class);
                    startActivity(intent);


                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Do nothing
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();


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
