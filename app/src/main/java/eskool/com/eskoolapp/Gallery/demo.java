package eskool.com.eskoolapp.Gallery;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

import eskool.com.eskoolapp.R;
import in.myinnos.awesomeimagepicker.activities.AlbumSelectActivity;
import in.myinnos.awesomeimagepicker.helpers.ConstantsCustomGallery;
import in.myinnos.awesomeimagepicker.models.Image;

public class demo extends AppCompatActivity {

    private static final int READ_STORAGE_PERMISSION = 4000;
    private static final int LIMIT = 3;
    private ImageView imageView, imageView1, imageView2;
    private TextView txImageSelects, choose_images;
    Toolbar toolbar;

    ImageView[] IMGS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);


        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Gallery");
        toolbar.setTitleTextColor(0xFFFFFFFF);

        txImageSelects = (TextView) findViewById(R.id.txImageSelects);
        choose_images = (TextView) findViewById(R.id.choose_images);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);

     /*   IMGS[0]=imageView;
        IMGS[1]=imageView1;
        IMGS[2]=imageView2;*/


        choose_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (!Helper.checkPermissionForExternalStorage(demo.this)) {
                        Helper.requestStoragePermission(demo.this, READ_STORAGE_PERMISSION);
                    } else {
                        // opining custom gallery
                        Intent intent = new Intent(demo.this, AlbumSelectActivity.class);
                        intent.putExtra(ConstantsCustomGallery.INTENT_EXTRA_LIMIT, LIMIT);
                        startActivityForResult(intent, ConstantsCustomGallery.REQUEST_CODE);
                    }
                } else {
                    Intent intent = new Intent(demo.this, AlbumSelectActivity.class);
                    intent.putExtra(ConstantsCustomGallery.INTENT_EXTRA_LIMIT, LIMIT);
                    startActivityForResult(intent, ConstantsCustomGallery.REQUEST_CODE);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IMGS[0]=imageView;
        IMGS[1]=imageView1;
        IMGS[2]=imageView2;
        if (requestCode == ConstantsCustomGallery.REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            //The array list has the image paths of the selected images
            ArrayList<Image> images = data.getParcelableArrayListExtra(ConstantsCustomGallery.INTENT_EXTRA_IMAGES);

            imageView.setVisibility(View.VISIBLE);
            imageView1.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.VISIBLE);

            for (int i = 0; i < images.size(); i++) {
                Uri uri = Uri.fromFile(new File(images.get(0).path));

                for (int j = 0; j < images.size(); j++) {


                    Glide.with(this).load(uri)
                            .placeholder(R.color.colorAccent)
                            .override(400, 400)
                            .crossFade()
                            .centerCrop()
                            .into(IMGS[j]);




/*txImageSelects.setText(txImageSelects.getText().toString().trim()
                        + "\n" +
                        String.valueOf(i + 1) + ". " + String.valueOf(uri));*/

                }

            }
        }

    }
}