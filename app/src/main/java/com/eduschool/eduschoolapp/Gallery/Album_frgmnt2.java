package com.eduschool.eduschoolapp.Gallery;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.DeleteAlbumPOJO.DeleteAlbumBean;
import com.eduschool.eduschoolapp.GalleryAlbumPOJO.AlbumListBean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.HomeWork.FrgmntOne;
import com.eduschool.eduschoolapp.MainActivity;
import com.eduschool.eduschoolapp.New;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentListbean;
import com.eduschool.eduschoolapp.UpdateAlbumPOJO.UpdateAlbumBean;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.imageListPOJO.GalleryImage;
import com.eduschool.eduschoolapp.imageListPOJO.GalleryList;
import com.eduschool.eduschoolapp.imageListPOJO.imageListBean;
import com.eduschool.eduschoolapp.sectionPOJO.sectionBean;
import com.eduschool.eduschoolapp.studentPOJO.studentBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.apptik.widget.multiselectspinner.BaseMultiSelectSpinner;
import io.apptik.widget.multiselectspinner.MultiSelectSpinner;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Album_frgmnt2 extends Fragment {
    Toolbar toolbar;
    String name, albumId;
    ProgressBar progress;

    String crDate , albName;


    String ClassId , SecId , secName;


    String classObject , sectionObject , studentObject;



    String[] mon = {
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
            "Aug",
            "Sep",
            "Oct",
            "Nov",
            "Dec",
    };
    String date1;


    TextView album , day1 , date;

    ImageButton edit;

    TextView day, month, classsection;


    RecyclerView grid;
    GridLayoutManager manager;

    List<GalleryList> list;

    int SELECT_PICTURES = 1;

    ImageAdapter adapter;

    public Album_frgmnt2() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.albm_frgmnt2, container, false);

        list = new ArrayList<>();

        album = (TextView)view.findViewById(R.id.album);
        day1 = (TextView)view.findViewById(R.id.day1);
        date = (TextView)view.findViewById(R.id.date);

        day = (TextView) view.findViewById(R.id.day);
        month = (TextView) view.findViewById(R.id.month);
        classsection = (TextView) view.findViewById(R.id.classSection);

        edit = (ImageButton)view.findViewById(R.id.edit);


        grid = (RecyclerView)view.findViewById(R.id.grid);
        manager = new GridLayoutManager(getContext() , 3);

        adapter = new ImageAdapter(getActivity() , list);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);

        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        setHasOptionsMenu(true);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        name = getArguments().getString("message1");
        albumId = getArguments().getString("message");
        albName = getArguments().getString("album");
        crDate = getArguments().getString("date");
        //Toast.makeText(getContext(),albumId,Toast.LENGTH_SHORT).show();

        date1 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());


        String[] d1 = date1.split("-");

        day.setText(d1[2]);

        month.setText(mon[Integer.parseInt(d1[1]) - 1] + " " + d1[0]);


        classsection.setText(albName);
        day1.setText(crDate);

        date1 = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        //day1.setText(date1);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final List<String> classNames = new ArrayList<>();
                final List<String> classIds = new ArrayList<>();
                final List<String> secNames = new ArrayList<>();
                final List<String> secIds = new ArrayList<>();

                final Dialog dialog=new Dialog(getActivity());
                dialog.setCancelable(true);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.gallery_popup_1);
                dialog.show();

                final Spinner cls = (Spinner)dialog.findViewById(R.id.cls);
                final Spinner section = (Spinner)dialog.findViewById(R.id.section);
                final ProgressBar progress = (ProgressBar)dialog.findViewById(R.id.progress);

                progress.setVisibility(View.VISIBLE);

                final User b = (User) getActivity().getApplicationContext();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllAPIs cr = retrofit.create(AllAPIs.class);

                Call<ClassListbean> call = cr.classList(b.school_id);

                call.enqueue(new Callback<ClassListbean>() {
                    @Override
                    public void onResponse(Call<ClassListbean> call, Response<ClassListbean> response) {


                        for (int i = 0 ; i < response.body().getClassList().size() ; i++)
                        {
                            classNames.add(response.body().getClassList().get(i).getClassName());
                            classIds.add(response.body().getClassList().get(i).getClassId());
                        }

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, classNames);

                        cls.setAdapter(dataAdapter);

                        progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<ClassListbean> call, Throwable t) {
                        progress.setVisibility(View.GONE);
                    }
                });


                cls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



                        final User b = (User) getActivity().getApplicationContext();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllAPIs cr = retrofit.create(AllAPIs.class);

                        progress.setVisibility(View.VISIBLE);

                        ClassId = classIds.get(position);

                        Call<sectionBean> call1 = cr.getSections(b.school_id , "," + classIds.get(position));

                        call1.enqueue(new Callback<sectionBean>() {
                            @Override
                            public void onResponse(Call<sectionBean> call, Response<sectionBean> response) {

                                Log.d("response" , "entered");

                                for (int i = 0 ; i < response.body().getSectionList().size() ; i++)
                                {
                                    secNames.add(response.body().getSectionList().get(i).getSectionName());
                                    secIds.add(response.body().getSectionList().get(i).getSectionId());
                                }

                                ArrayAdapter<String> adapter = new ArrayAdapter <String>(getActivity(), android.R.layout.simple_list_item_1, secNames);

                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                                section.setAdapter(adapter);

                                progress.setVisibility(View.GONE);
                            }

                            @Override
                            public void onFailure(Call<sectionBean> call, Throwable t) {
                                progress.setVisibility(View.GONE);
                                Log.d("response" , t.toString());
                            }
                        });



                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                section.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                        SecId = secIds.get(position);
                        secName = secNames.get(position);



                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });



                Button submit=(Button)dialog.findViewById(R.id.submit);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {




                        //classsection.setText(secName);




                        dialog.dismiss();

                    }
                });

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

                list = response.body().getGalleryList();

                GalleryList gl = new GalleryList();

                GalleryImage gi = new GalleryImage();

                gi.setImae("asd");

                List<GalleryImage> gli = new ArrayList<>();

                gli.add(gi);

                gl.setGalleryImage(gli);

                list.add(gl);

                Log.d("size" , String.valueOf(list.size()));

                adapter.setGridData(list);

                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<imageListBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Log.d("format" , t.toString());
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.filter, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_filter) {



            /*Bundle bundle = new Bundle();
            bundle.putString("m", "m");
            FragmentManager fm = getFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            Update_Album_frgmnt frag1 = new Update_Album_frgmnt();
            ft.replace(R.id.replace, frag1);

            ft.addToBackStack(null);
            ft.commit();*/

            android.support.v4.app.FragmentManager fm = ((AppCompatActivity) getContext()).getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
            Update_Album_frgmnt frag1 = new Update_Album_frgmnt();
            Bundle bundle = new Bundle();
            bundle.putString("message1", name);
            bundle.putString("message", albumId);
            frag1.setArguments(bundle);
            ft.replace(R.id.replace, frag1);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            ft.addToBackStack(null);
            ft.commit();


            return true;
        } else if (id == R.id.action_delete) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
            dialog.setCancelable(false);

            final ProgressDialog dialog1 = ProgressDialog.show(getActivity(), "",
                    "Deleting Album. Please wait...", true);

            dialog.setMessage("Are you sure you want to Delete this album ?");
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
                    Call<DeleteAlbumBean> call = cr.delete_album(b.school_id,albumId);

                    call.enqueue(new Callback<DeleteAlbumBean>() {
                        @Override
                        public void onResponse(Call<DeleteAlbumBean> call, Response<DeleteAlbumBean> response) {


                            if (response.body().getStatus().equals("1")){
                                Toast.makeText(getActivity(),"Album Deleted Successfully",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getActivity(),"Album did not Deleted Successfully !",Toast.LENGTH_SHORT).show();
                            }
                            //progress.setVisibility(View.GONE);
                            dialog1.dismiss();
                            dialog.dismiss();
                            getFragmentManager().popBackStack();

                        }

                        @Override
                        public void onFailure(Call<DeleteAlbumBean> call, Throwable throwable) {

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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle(name);
        User u = (User) getContext().getApplicationContext();
        name = getArguments().getString("message1");
        albumId = getArguments().getString("message");


        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = ((TeacherHome) getContext()).getSupportFragmentManager();
                fm.popBackStack();

            }
        });


        u.back = false;

        //classsection.setText(u.class_Name + u.section_Name);

        loadData();

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
            View view = inflater.inflate(R.layout.image_list_model , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

               GalleryList item = list.get(position);

               Log.d("position" , String.valueOf(position));

               try {
                   ImageLoader loader = ImageLoader.getInstance();
                   loader.displayImage(item.getGalleryImage().get(0).getImae(), holder.image);

                   Log.d("try" , "Exception");

               }catch (Exception e)
               {
                   e.printStackTrace();
               }



               if (position == list.size() - 1)
               {
                   Log.d("if" , String.valueOf(position));
                   Log.d("size-1" , String.valueOf(list.size() - 1));

                   holder.add.setVisibility(View.VISIBLE);
                   holder.image.setVisibility(View.GONE);
               }
               else
               {
                   holder.add.setVisibility(View.GONE);
                   holder.image.setVisibility(View.VISIBLE);
               }

            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentManager fm = ((TeacherHome) getContext()).getSupportFragmentManager();
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

            /*if (position == (list.size() - 1))
            {
                holder.add.setVisibility(View.VISIBLE);
                holder.image.setVisibility(View.GONE);
            }
            else
            {
                holder.image.setVisibility(View.VISIBLE);
                holder.add.setVisibility(View.GONE);
            }*/

            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {





                    final Dialog dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.add_gallery_popup);

                    dialog.show();

                    final MultiSelectSpinner cls = (MultiSelectSpinner)dialog.findViewById(R.id.classs);
                    final MultiSelectSpinner sec = (MultiSelectSpinner)dialog.findViewById(R.id.section);
                    final MultiSelectSpinner stu = (MultiSelectSpinner)dialog.findViewById(R.id.students);
                    final ProgressBar progress = (ProgressBar)dialog.findViewById(R.id.progress);

                    Button submit = (Button)dialog.findViewById(R.id.submit);

                    cls.setSelectAll(true);
                    sec.setSelectAll(true);
                    stu.setSelectAll(true);


                    final List<String> clasNames = new ArrayList<>();
                    final List<String> clasIds = new ArrayList<>();

                    final List<String> secNames = new ArrayList<>();
                    final List<String> secIds = new ArrayList<>();

                    final List<String> stuNames = new ArrayList<>();
                    final List<String> stuIds = new ArrayList<>();

                    final User b = (User) getActivity().getApplicationContext();
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllAPIs cr = retrofit.create(AllAPIs.class);

                    final Call<ClassListbean> call = cr.classList(b.school_id);
                    progress.setVisibility(View.VISIBLE);

                    call.enqueue(new Callback<ClassListbean>() {
                        @Override
                        public void onResponse(Call<ClassListbean> call, Response<ClassListbean> response) {


                            for (int i = 0; i < response.body().getClassList().size(); i++)
                            {

                                clasNames.add(response.body().getClassList().get(i).getClassName());
                                clasIds.add(response.body().getClassList().get(i).getClassId());

                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter <String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, clasNames);

                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                            cls.setListAdapter(adapter);


                            progress.setVisibility(View.GONE);


                        }

                        @Override
                        public void onFailure(Call<ClassListbean> call, Throwable throwable) {

                            progress.setVisibility(View.GONE);

                        }
                    });


                    cls.setListener(new BaseMultiSelectSpinner.MultiSpinnerListener() {
                        @Override
                        public void onItemsSelected(boolean[] booleans) {

                            List<String> checkedClasses = new ArrayList<>();

                            for (int i = 0 ; i < booleans.length ; i++)
                            {
                                if (booleans[i])
                                {
                                    checkedClasses.add(clasIds.get(i));
                                }

                            }



                            final User b = (User) getActivity().getApplicationContext();
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.baseURL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllAPIs cr = retrofit.create(AllAPIs.class);

                            progress.setVisibility(View.VISIBLE);

                            Log.d("checked" , TextUtils.join(",", checkedClasses));

                            classObject = "," + TextUtils.join(",", checkedClasses);

                            Call<sectionBean> call1 = cr.getSections(b.school_id , "," + classObject);

                            call1.enqueue(new Callback<sectionBean>() {
                                @Override
                                public void onResponse(Call<sectionBean> call, Response<sectionBean> response) {

                                    Log.d("response" , "entered");

                                    for (int i = 0 ; i < response.body().getSectionList().size() ; i++)
                                    {
                                        secNames.add(response.body().getSectionList().get(i).getSectionName());
                                        secIds.add(response.body().getSectionList().get(i).getSectionId());
                                    }

                                    ArrayAdapter<String> adapter = new ArrayAdapter <String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, secNames);

                                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                                    sec.setListAdapter(adapter);

                                    progress.setVisibility(View.GONE);
                                }

                                @Override
                                public void onFailure(Call<sectionBean> call, Throwable t) {
                                    progress.setVisibility(View.GONE);
                                    Log.d("response" , t.toString());
                                }
                            });




                        }
                    });


                    sec.setListener(new BaseMultiSelectSpinner.MultiSpinnerListener() {
                        @Override
                        public void onItemsSelected(boolean[] booleans) {


                            List<String> checkedSections = new ArrayList<>();

                            for (int i = 0 ; i < booleans.length ; i++)
                            {
                                if (booleans[i])
                                {
                                    checkedSections.add(secIds.get(i));
                                }

                            }


                            Log.d("checked" , TextUtils.join(",", checkedSections));

                            sectionObject = "," + TextUtils.join(",", checkedSections);

                            final User b = (User) getActivity().getApplicationContext();
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.baseURL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllAPIs cr = retrofit.create(AllAPIs.class);

                            progress.setVisibility(View.VISIBLE);

                            Call<studentBean> call2 = cr.getStudents(b.school_id , "," + TextUtils.join(",", checkedSections));

                            call2.enqueue(new Callback<studentBean>() {
                                @Override
                                public void onResponse(Call<studentBean> call, Response<studentBean> response) {


                                    for (int i = 0 ; i < response.body().getStudentList().size() ; i++)
                                    {
                                        stuNames.add(response.body().getStudentList().get(i).getStuName());
                                        stuIds.add(response.body().getStudentList().get(i).getStudentId());
                                    }

                                    ArrayAdapter<String> adapter = new ArrayAdapter <String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, stuNames);

                                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                    stu.setListAdapter(adapter);


                                    progress.setVisibility(View.GONE);

                                }

                                @Override
                                public void onFailure(Call<studentBean> call, Throwable t) {
                                    progress.setVisibility(View.GONE);
                                }
                            });



                        }
                    });


                    stu.setListener(new BaseMultiSelectSpinner.MultiSpinnerListener() {
                        @Override
                        public void onItemsSelected(boolean[] booleans) {

                            List<String> checkedStudents = new ArrayList<>();

                            for (int i = 0 ; i < booleans.length ; i++)
                            {
                                if (booleans[i])
                                {
                                    checkedStudents.add(stuIds.get(i));
                                }

                            }


                            Log.d("checked" , TextUtils.join(",", checkedStudents));

                            studentObject = "," + TextUtils.join(",", checkedStudents);



                        }
                    });

                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                            intent.setType("image/*");
                            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                            startActivityForResult(Intent.createChooser(intent, "Select Pictures"), SELECT_PICTURES);

                            dialog.dismiss();

                        }
                    });

/*
                    */

                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {

            ImageView image , add;

            public ViewHolder(View itemView) {
                super(itemView);

                image = (ImageView)itemView.findViewById(R.id.image);
                add = (ImageView)itemView.findViewById(R.id.add_image);



            }
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

            toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

            pager = (ViewPager)view.findViewById(R.id.pager);
            progress = (ProgressBar)view.findViewById(R.id.progress);

            try {
                list.remove(list.size() - 1);
            }catch (Exception e)
            {
                e.printStackTrace();
            }



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

                    FragmentManager fm = ((TeacherHome) getContext()).getSupportFragmentManager();
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
            Log.d("url" , list.get(position).getGalleryImage().get(0).getImae());
            b.putString("url" , list.get(position).getGalleryImage().get(0).getImae());
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




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_PICTURES) {
            if(resultCode == Activity.RESULT_OK) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.add_image_dialog);
                dialog.show();

                final ProgressBar bar = (ProgressBar)dialog.findViewById(R.id.progress);
                final TextView text = (TextView)dialog.findViewById(R.id.count);

                if(data.getClipData() != null) {
                    final int count = data.getClipData().getItemCount();
                    final int[] currentItem = {0};

                    Log.d("countt" , String.valueOf(count));

                    while(currentItem[0] < count) {
                        Uri imageUri = data.getClipData().getItemAt(currentItem[0]).getUri();
                        //do something with the image (save it to some directory or whatever you need to do with it here)


                        Log.d("uri" , String.valueOf(imageUri));


                        MultipartBody.Part body = null;


                        String mCurrentPhotoPath = getPath(getContext() , imageUri);

                        File file = new File(mCurrentPhotoPath);


                        RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                        body = MultipartBody.Part.createFormData("albumattach", file.getName(), reqFile);





                        User u = (User) getContext().getApplicationContext();


                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(u.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllAPIs cr = retrofit.create(AllAPIs.class);

                        Call<addImageBean> call = cr.addImage(u.school_id , albumId , "Teacher" , u.user_id , body , classObject , sectionObject , studentObject);


                        Log.d("school" , u.school_id);
                        Log.d("album id" , albumId);
                        Log.d("user id" , u.user_id);


                        call.enqueue(new Callback<addImageBean>() {
                            @Override
                            public void onResponse(Call<addImageBean> call, Response<addImageBean> response) {


                                if (currentItem[0] == count)
                                {

                                    text.setText(String.valueOf(count) +  "/" + String.valueOf(count));

                                    bar.setProgress(100);
                                    Toast.makeText(getContext() , "Images Added Successfully" , Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();

                                    loadData();

                                }
                                else
                                {

                                    float per = ((currentItem[0]) / count) * 100;

                                    bar.setProgress((int)per);

                                    text.setText(String.valueOf(currentItem[0]) +  "/" + String.valueOf(count));

                                }


                            }

                            @Override
                            public void onFailure(Call<addImageBean> call, Throwable t) {

                                dialog.dismiss();

                            }
                        });


                        currentItem[0] = currentItem[0] + 1;
                    }
                } else if(data.getData() != null) {


                    Log.d("single" , "entered");


                    MultipartBody.Part body = null;

                    progress.setVisibility(View.VISIBLE);

                    Uri imagePath = data.getData();
                    //do something with the image (save it to some directory or whatever you need to do with it here)

                    String mCurrentPhotoPath = getPath(getContext() , imagePath);

                    File file = new File(mCurrentPhotoPath);


                    RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                    body = MultipartBody.Part.createFormData("albumattach", file.getName(), reqFile);





                    User u = (User) getContext().getApplicationContext();


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(u.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllAPIs cr = retrofit.create(AllAPIs.class);

                    Call<addImageBean> call = cr.addImage(u.school_id , albumId , "Teacher" , u.user_id , body , classObject , sectionObject , studentObject);

                    call.enqueue(new Callback<addImageBean>() {
                        @Override
                        public void onResponse(Call<addImageBean> call, Response<addImageBean> response) {


                                Toast.makeText(getContext() , "Image Added Successfully" , Toast.LENGTH_SHORT).show();

                            progress.setVisibility(View.GONE);

                            dialog.dismiss();

                            loadData();

                        }

                        @Override
                        public void onFailure(Call<addImageBean> call, Throwable t) {

                            dialog.dismiss();
                            progress.setVisibility(View.GONE);

                        }
                    });

                }
            }
        }
    }



    private static String getPath(final Context context, final Uri uri)
    {
        final boolean isKitKatOrAbove = Build.VERSION.SDK_INT >=  Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isKitKatOrAbove && DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    if ("primary".equalsIgnoreCase(type)) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }

                    // TODO handle non-primary volumes
                }
                // DownloadsProvider
                else if (isDownloadsDocument(uri)) {

                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                    return getDataColumn(context, contentUri, null, null);
                }
                // MediaProvider
                else if (isMediaDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }

                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[] {
                            split[1]
                    };

                    return getDataColumn(context, contentUri, selection, selectionArgs);
                }
            }
            // MediaStore (and general)
            else if ("content".equalsIgnoreCase(uri.getScheme())) {
                return getDataColumn(context, uri, null, null);
            }
            // File
            else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }

        return null;
    }


    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


}
