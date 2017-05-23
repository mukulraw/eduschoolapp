package eskool.com.eskoolapp.Attendance;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import eskool.com.eskoolapp.R;

/**
 * Created by user on 5/16/2017.
 */

public class MrkAttndncOwnCls extends AppCompatActivity {

    TextView p,a,l;
    boolean aBoolean=false,pBoolean=true,lBoolean=false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);


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

