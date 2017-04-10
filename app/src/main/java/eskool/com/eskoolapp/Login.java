package eskool.com.eskoolapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Login extends AppCompatActivity {

    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView imageView = new ImageView(this);


        final int[] images = {R.drawable.logo, R.drawable.logo, R.drawable.logo,
                R.drawable.logo};

        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.bottomView);

        for (int x = 0; x < images.length; x++) {
            final Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(),
                    images[x]);

            int width = bitmapOrg.getWidth();
            int height = bitmapOrg.getHeight();
            int newWidth = 200;
            int newHeight = 200;

            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;

            Matrix matrix = new Matrix();

            matrix.postScale(scaleWidth, scaleHeight);
            matrix.postRotate(0);

            Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0, width,
                    height, matrix, true);
            BitmapDrawable bmd = new BitmapDrawable(getResources(),
                    resizedBitmap);


            imageView.setPadding(2, 0, 9, 5);
            imageView.setImageDrawable(bmd);
            imageView.setTag(x);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });



        }
        linearLayout1.addView(imageView);



    }

}
