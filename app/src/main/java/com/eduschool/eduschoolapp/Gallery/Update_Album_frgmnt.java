package com.eduschool.eduschoolapp.Gallery;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.New;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.UpdateAlbumPOJO.UpdateAlbumBean;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.imageListPOJO.GalleryImage;
import com.eduschool.eduschoolapp.imageListPOJO.GalleryList;
import com.eduschool.eduschoolapp.imageListPOJO.imageListBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 8/9/2017.
 */

public class Update_Album_frgmnt extends Fragment {
    Toolbar toolbar;
    EditText name;
    String Ename,albumId;
    TextView update;

    RecyclerView grid;

    ProgressBar progress;

    ImageAdapter adapter;

    List<GalleryList> list;

    GridLayoutManager manager;

    public Update_Album_frgmnt() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.update_album, container, false);

        list = new ArrayList<>();

        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        name = (EditText) view.findViewById(R.id.name);
        progress=(ProgressBar)view.findViewById(R.id.progress);
        update = (TextView) view.findViewById(R.id.updte);

        grid = (RecyclerView)view.findViewById(R.id.grid);
        manager = new GridLayoutManager(getContext() , 3);

        Ename = getArguments().getString("message1");
        albumId = getArguments().getString("message");
        name.setText(Ename);

        adapter = new ImageAdapter(getContext() , list);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setCancelable(false);

                final ProgressDialog dialog1 = ProgressDialog.show(getActivity(), "",
                        "Updating Album. Please wait...", true);

                dialog.setMessage("Are you sure you want to Update this album ?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int id) {

                        User b = (User) getActivity().getApplicationContext();


                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllAPIs cr = retrofit.create(AllAPIs.class);
                        //progress.setVisibility(View.VISIBLE);

                        String s=name.getText().toString().trim();
                        Call<UpdateAlbumBean> call = cr.update_album(b.school_id,albumId,s);

                        call.enqueue(new Callback<UpdateAlbumBean>() {
                            @Override
                            public void onResponse(Call<UpdateAlbumBean> call, Response<UpdateAlbumBean> response) {


                                if (response.body().getStatus().equals("1")){
                                    Toast.makeText(getActivity(),"Album Updated Successfully",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getActivity(),"Album not updated Successfully !",Toast.LENGTH_SHORT).show();
                                }
                                //progress.setVisibility(View.GONE);
                                dialog1.dismiss();
                                dialog.dismiss();
                                getFragmentManager().popBackStack();
                                getFragmentManager().popBackStack();

                            }

                            @Override
                            public void onFailure(Call<UpdateAlbumBean> call, Throwable throwable) {

                               // progress.setVisibility(View.GONE);

                            }
                        });

                    }
                })
                        .setNegativeButton("No ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for "Cancel".
                                dialog1.dismiss();
                            }
                        });

                final AlertDialog alert = dialog.create();
                alert.show();
            }
        });

        return view;

    }



    public void loadData()
    {

        progress.setVisibility(View.VISIBLE);

        list.clear();

        User u = (User) getContext().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);

        Call<imageListBean> call = cr.getImages(u.school_id , albumId);

        call.enqueue(new Callback<imageListBean>() {
            @Override
            public void onResponse(Call<imageListBean> call, Response<imageListBean> response) {

                adapter.setGridData(response.body().getGalleryList());

                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<imageListBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Log.d("format" , t.toString());
            }
        });

    }






    public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>
    {

        Context context;
        List<GalleryList> list = new ArrayList<>();

        public ImageAdapter(Context context , List<GalleryList> list)
        {
            this.context = context;
            this.list = list;
        }

        public void setGridData(List<GalleryList> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.update_image_model , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            final GalleryList item = list.get(position);

            Log.d("position" , String.valueOf(position));

            try {
                ImageLoader loader = ImageLoader.getInstance();
                loader.displayImage(item.getGalleryImage().get(0).getImae(), holder.image);

                Log.d("try" , "Exception");

            }catch (Exception e)
            {
                e.printStackTrace();
            }



            holder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {




                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setCancelable(false);

                    final ProgressDialog dialog1 = ProgressDialog.show(getActivity(), "",
                            "Deleting Image. Please wait...", true);

                    dialog.setMessage("Are you sure you want to delete this image ?");

                    dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(final DialogInterface dialog, int id) {

                            //progress.setVisibility(View.VISIBLE);

                            User u = (User) getContext().getApplicationContext();


                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(u.baseURL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllAPIs cr = retrofit.create(AllAPIs.class);

                            Call<addImageBean> call = cr.deleteImage(item.getGalleryImage().get(0).getId());

                            call.enqueue(new Callback<addImageBean>() {
                                @Override
                                public void onResponse(Call<addImageBean> call, Response<addImageBean> response) {

                                    dialog1.dismiss();

                                    dialog.dismiss();


                                    if (Objects.equals(response.body().getStatus(), "1"))
                                    {
                                        Toast.makeText(getContext() , "Image Deleted Successfully" , Toast.LENGTH_SHORT).show();
                                    }


                                    FragmentManager fm = getFragmentManager();
                                    fm.popBackStack();



                                    //loadData();

                                }

                                @Override
                                public void onFailure(Call<addImageBean> call, Throwable t) {

                                    dialog.dismiss();
                                    dialog1.dismiss();
                                }
                            });

                            dialog.dismiss();
                            dialog1.dismiss();

                        }
                    })
                            .setNegativeButton("No ", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //Action for "Cancel".
                                    dialog1.dismiss();
                                }
                            });

                    final AlertDialog alert = dialog.create();
                    alert.show();
















                }
            });


        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {

            ImageView image;
            ImageButton remove;

            public ViewHolder(View itemView) {
                super(itemView);

                image = (ImageView)itemView.findViewById(R.id.image);
                remove = (ImageButton)itemView.findViewById(R.id.delete);



            }
        }

    }






    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Update Gallery");

        loadData();

        User u = (User) getContext().getApplicationContext();



        u.back = false;
    }
}
