package eskool.com.eskoolapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class New extends AppCompatActivity {


    ViewPager viewPager;
    MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_new);


        viewPager = (ViewPager) findViewById(R.id.view);
        myPagerAdapter = new MyPagerAdapter();
        viewPager.setAdapter(myPagerAdapter);


        int pos = getIntent().getIntExtra("position", 0);
        viewPager.setCurrentItem(pos);


    }


    private class MyPagerAdapter extends PagerAdapter {


        int[] res = {
                R.drawable.teacher,
                R.drawable.studentimage,
                R.drawable.logo1,
                R.drawable.school,
                R.drawable.logo1};

        int[] backgroundcolor = {
                0xFF101010,
                0xFF202020,
                0xFF303030,
                0xFF404040,
                0xFF505050};

        @Override
        public int getCount() {
            return res.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(New.this);
            imageView.setImageResource(res[position]);
            ViewGroup.LayoutParams imageParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(imageParams);

            LinearLayout layout = new LinearLayout(New.this);
            layout.setOrientation(LinearLayout.VERTICAL);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layout.setBackgroundColor(backgroundcolor[position]);
            layout.setLayoutParams(layoutParams);
            layout.addView(imageView);

            final int page = position;
            layout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(New.this,
                            "Page " + page + " clicked",
                            Toast.LENGTH_LONG).show();
                }
            });


            container.addView(layout);
            return layout;


        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }

    }
}
