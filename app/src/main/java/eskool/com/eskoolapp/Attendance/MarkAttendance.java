package eskool.com.eskoolapp.Attendance;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import eskool.com.eskoolapp.R;

public class MarkAttendance extends AppCompatActivity {
    Toolbar toolbar;

    TextView p,a,l;
    boolean aBoolean=false,pBoolean=true,lBoolean=false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Mark Attendance");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        p=(TextView)findViewById(R.id.p);
        a=(TextView)findViewById(R.id.a);
        l=(TextView)findViewById(R.id.l);

if (pBoolean)
{
    p.setBackground(getResources().getDrawable(R.drawable.checkbox));
}



        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.setBackground(getResources().getDrawable(R.drawable.checkbox));
                p.setTextColor(Color.WHITE);
                a.setBackground(getResources().getDrawable(R.drawable.defaultcheckbox));
                l.setBackground(getResources().getDrawable(R.drawable.defaultcheckbox));

            }
        });


        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pBoolean=false;
                a.setBackground(getResources().getDrawable(R.drawable.absentcheckbox));
                a.setTextColor(Color.WHITE);
                p.setBackground(getResources().getDrawable(R.drawable.defaultcheckbox));
                l.setBackground(getResources().getDrawable(R.drawable.defaultcheckbox));


            }
        });


        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                l.setBackground(getResources().getDrawable(R.drawable.leavecheckbox));
                l.setTextColor(Color.WHITE);
                a.setBackground(getResources().getDrawable(R.drawable.defaultcheckbox));
                p.setBackground(getResources().getDrawable(R.drawable.defaultcheckbox));

            }
        });

    }
}
