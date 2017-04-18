package eskool.com.eskoolapp.OnlineTest;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import eskool.com.eskoolapp.MyViewPager;
import eskool.com.eskoolapp.R;


public class OnlineTestActivity extends AppCompatActivity {
    TextView countDown;
    static final int NUM_ITEMS = 2;
    MyAdapter mAdapter;
    MyViewPager mPager;
    TextView count;
    final static Fragment[] layouts = new Fragment[]{QuestionFragment.newInstance(0), QuestionFragment_two.newInstance(1)};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_test);
        count=(TextView)findViewById(R.id.count);


        mAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = (MyViewPager) findViewById(R.id.viewPager);
        mPager.setAdapter(mAdapter);

        mPager.setSwipeable(false);

        Button button = (Button) findViewById(R.id.next);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPager.setCurrentItem(2);
                count.setText("2/30");
            }
        });
        button = (Button) findViewById(R.id.prev);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPager.setCurrentItem(0);
                count.setText("1/30");
            }
        });



        countDown = (TextView) findViewById(R.id.countDown);
        int hoursToGo = 0;
        int minutesToGo = 0;
        int secondsToGo = 300;

        int millisToGo = secondsToGo * 1000 + minutesToGo * 1000 * 60 + hoursToGo * 1000 * 60 * 60;


        new CountDownTimer(millisToGo, 1000) {

            @Override
            public void onTick(long millis) {
                int seconds = (int) (millis / 1000) % 60;
                int minutes = (int) ((millis / (1000 * 60)) % 60);
                int hours = (int) ((millis / (1000 * 60 * 60)) % 24);
                String text = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                countDown.setText(text);
            }

            @Override
            public void onFinish() {
                countDown.setText("Done!");
            }
        }.start();

    }

    public static class MyAdapter extends FragmentStatePagerAdapter {


        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public Fragment getItem(int position) {

            return layouts[position];
        }
    }


}