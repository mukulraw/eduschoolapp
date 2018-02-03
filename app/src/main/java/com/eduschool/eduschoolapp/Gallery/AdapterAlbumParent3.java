package com.eduschool.eduschoolapp.Gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.eduschool.eduschoolapp.GalleryParentPOJO.GalleryImage;
import com.eduschool.eduschoolapp.GalleryParentPOJO.GalleryList;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.New;
import com.eduschool.eduschoolapp.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 8/16/2017.
 */

public class AdapterAlbumParent3 extends RecyclerView.Adapter<AdapterAlbumParent3.myviewholder> {

    Context context;
    List<GalleryList> list = new ArrayList<>();

    public AdapterAlbumParent3(Context context, List<GalleryList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<GalleryList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterAlbumParent3.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.gallery_parent_model2, parent, false);

        return new AdapterAlbumParent3.myviewholder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterAlbumParent3.myviewholder holder, final int position) {

        GalleryList item = list.get(position);


        try {
            ImageLoader loader = ImageLoader.getInstance();
            loader.displayImage(item.getGalleryImage().get(0).getImae() ,  holder.img);

            Log.d("try" , "Exception");

        }catch (Exception e)
        {
            e.printStackTrace();
        }


        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = ((ParentHome) context).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                Page frag = new Page();
                frag.setData(list);
                Bundle b = new Bundle();
                b.putInt("position" , position);
                frag.setArguments(b);
                ft.replace(R.id.replace , frag);
                ft.addToBackStack(null);
                ft.commit();


            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener {


        ImageView img;

        public myviewholder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.img);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String name=list.get(getAdapterPosition()).getGalleryImage().get(0).getId();


                    Intent intent=new Intent(context,New.class);
                    intent.putExtra("position",name);
                }
            });


        }


        @Override
        public void onClick(View view) {


        }
    }

    public static class Page extends Fragment
    {

        int position;
        Toolbar toolbar;
        ViewPager pager;
        ProgressBar progress;

        List<GalleryList> list = new ArrayList<>();

        public void setData(List<GalleryList> list)
        {
            this.list = list;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            position = getArguments().getInt("position");



        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.image_frag_layout , container , false);

            toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);

            pager = (ViewPager)view.findViewById(R.id.pager);
            progress = (ProgressBar)view.findViewById(R.id.progress);

            //list.remove(list.size() - 1);

            ImagePagerAdapter adapter = new ImagePagerAdapter(getChildFragmentManager() , list);
            pager.setAdapter(adapter);

            pager.setCurrentItem(position);

            return view;
        }

        @Override
        public void onResume() {
            super.onResume();

            toolbar.setNavigationIcon(R.drawable.back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentManager fm = ((ParentHome) getContext()).getSupportFragmentManager();
                    fm.popBackStack();

                }
            });

        }
    }

    public static class ImagePagerAdapter extends FragmentStatePagerAdapter
    {

        List<GalleryList> list = new ArrayList<>();

        public ImagePagerAdapter(FragmentManager fm , List<GalleryList> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            Pages frag = new Pages();
            Bundle b = new Bundle();
//            Log.d("url" , list.get(position).getGalleryImage().get(0).getImae());

            try {
                b.putString("url" , list.get(position).getGalleryImage().get(0).getImae());
            }catch (Exception e)
            {
                e.printStackTrace();
            }


            frag.setArguments(b);
            return frag;
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }

    public static class Pages extends Fragment
    {

        ImageView image;
        ProgressBar progress;
        String url;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.pages_layout , container , false);

            url = getArguments().getString("url");

            image = (ImageView)view.findViewById(R.id.image);
            progress = (ProgressBar)view.findViewById(R.id.progress);



            ImageLoader loader = ImageLoader.getInstance();
            //loader.displayImage(url , image);

            loader.loadImage(url, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progress.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    image.setImageBitmap(loadedImage);
                    progress.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });


            return view;
        }
    }


}

