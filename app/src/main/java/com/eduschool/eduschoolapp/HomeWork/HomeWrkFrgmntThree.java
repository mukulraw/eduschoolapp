package com.eduschool.eduschoolapp.HomeWork;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.HomeWrkDetailsPOJO.FileAttachment;
import com.eduschool.eduschoolapp.HomeWrkDetailsPOJO.HomeWrkDetailsBean;
import com.eduschool.eduschoolapp.HomeWrkParent1POJO.HomeWorkListBean;
import com.eduschool.eduschoolapp.HomeWrkUpdPOJO.HomeworkUpdateBean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;

import com.squareup.okhttp.internal.Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by User on 5/8/2017.
 */

public class HomeWrkFrgmntThree extends Fragment {
    Toolbar toolbar;
    Button btn_complted;
    ProgressBar progress;
    TextView subject, date, classSection, title, note, dueDate;

    List<FileAttachment> l;
    RecyclerView grid;
    GridLayoutManager manager;
    DownloadAdapter adapter;

    public HomeWrkFrgmntThree() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_wrk_details, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);
        final String strtext = getArguments().getString("message");
        grid = (RecyclerView)view.findViewById(R.id.grid);
        manager = new GridLayoutManager(getContext() , 1);
        subject = (TextView) view.findViewById(R.id.subject);
        l = new ArrayList<>();
        date = (TextView) view.findViewById(R.id.date);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        classSection = (TextView) view.findViewById(R.id.classSection);
        title = (TextView) view.findViewById(R.id.title);
        note = (TextView) view.findViewById(R.id.note);
        dueDate = (TextView) view.findViewById(R.id.due_date);

        adapter = new DownloadAdapter(getContext() , l);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);

        User b = (User) getActivity().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<HomeWrkDetailsBean> call = cr.hw_details(b.school_id, b.user_id, strtext);


        call.enqueue(new Callback<HomeWrkDetailsBean>() {
            @Override
            public void onResponse(Call<HomeWrkDetailsBean> call, Response<HomeWrkDetailsBean> response) {

                subject.setText(response.body().getHomeworkDetail().get(0).getSubjectName());
                classSection.setText(response.body().getHomeworkDetail().get(0).getClassName() + " " + response.body().getHomeworkDetail().get(0).getSectionName());
                title.setText(response.body().getHomeworkDetail().get(0).getHomeworkTitle());
                note.setText(response.body().getHomeworkDetail().get(0).getAdditionalNote());
                dueDate.setText("Due Date : " + response.body().getHomeworkDetail().get(0).getDueDate() + "");
                date.setText(response.body().getHomeworkDetail().get(0).getPostedDate());

                try {
                    if (response.body().getHomeworkDetail().get(0).getHomeworkStatus().equals("Complete")){
                        btn_complted.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                        btn_complted.setTextColor(ContextCompat.getColor(getContext(), R.color.txtColor));
                        btn_complted.setEnabled(false);
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }




                adapter.setGridData(response.body().getHomeworkDetail().get(0).getFileAttachment());


                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<HomeWrkDetailsBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


        btn_complted = (Button) view.findViewById(R.id.btn_complted);
        btn_complted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Confirm");
                builder.setMessage("Are you sure want to Submit?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog

                        User b = (User) getActivity().getApplicationContext();


                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllAPIs cr = retrofit.create(AllAPIs.class);
                        progress.setVisibility(View.VISIBLE);

                        Call<HomeworkUpdateBean> call = cr.hw_update(b.user_id, strtext);


                        call.enqueue(new Callback<HomeworkUpdateBean>() {
                            @Override
                            public void onResponse(Call<HomeworkUpdateBean> call, Response<HomeworkUpdateBean> response) {


                                btn_complted.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                                btn_complted.setTextColor(ContextCompat.getColor(getContext(), R.color.txtColor));
                                btn_complted.setEnabled(false);

                                progress.setVisibility(View.GONE);

                            }

                            @Override
                            public void onFailure(Call<HomeworkUpdateBean> call, Throwable throwable) {

                                progress.setVisibility(View.GONE);

                            }
                        });

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

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Home Work Details");
        User u = (User) getContext().getApplicationContext();

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    FragmentManager fm = ((ParentHome) getContext()).getSupportFragmentManager();
                    fm.popBackStack();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
        });

        u.back = false;
    }


    public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.ViewHolder>
    {


        List<FileAttachment> l = new ArrayList<>();

        Context context;

        public DownloadAdapter(Context context , List<FileAttachment> l)
        {
            this.context = context;
            this.l = l;
        }


        public void setGridData(List<FileAttachment> l)
        {
            this.l = l;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.download_model , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            final FileAttachment item = l.get(position);

            String dpath = item.getAttachFile();
            String[] dd = dpath.split("/");


            holder.idd.setText(dd[dd.length - 1]);

            holder.download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    progress.setVisibility(View.VISIBLE);

                    User b = (User) getActivity().getApplicationContext();


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .build();

                    AllAPIs cr = retrofit.create(AllAPIs.class);

                    cr.getFile(item.getAttachFile()).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {



                            try {

                                String dpath = item.getAttachFile();
                                String[] dd = dpath.split("/");

                                DownloadFileAsyncTask downloadFileAsyncTask = new DownloadFileAsyncTask(item.getAttachFile(), dd[dd.length - 1]);
                                downloadFileAsyncTask.execute(response.body().byteStream());

                            }catch (Exception e)
                            {
                                progress.setVisibility(View.GONE);
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }

                    });
                }

            });

        }

        @Override
        public int getItemCount() {
            return l.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {


            TextView idd , download;

            public ViewHolder(View itemView) {
                super(itemView);

                idd = (TextView)itemView.findViewById(R.id.name);
                download = (TextView)itemView.findViewById(R.id.download);

            }
        }

    }


    private class DownloadFileAsyncTask extends AsyncTask<InputStream, Void, Boolean> {

        String TAG = "asdasdasd";



        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()+ "/EduSchoolApp/");



        final String filename;

        String key;

        String path = Environment.getExternalStorageDirectory().toString();

        byte[] decodedBytes = null;

        File file;

        public DownloadFileAsyncTask(String key , String name)
        {
            this.key = key;
            this.filename = name;
        }


        @Override
        protected Boolean doInBackground(InputStream... params) {

            if (!(dir.exists() && dir.isDirectory())) {
                dir.mkdirs();
            }

            InputStream inputStream = params[0];



            try {
                file = new File(dir , filename);

                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }


            OutputStream output = null;
            try {
                output = new FileOutputStream(file);

                byte[] buffer = new byte[1024]; // or other buffer size
                int read;

                Log.d(TAG, "Attempting to write to: " + dir + "/" + filename);
                while ((read = inputStream.read(buffer)) != -1) {
                    output.write(buffer, 0, read);
                    Log.v(TAG, "Writing to buffer to output stream.");
                }
                Log.d(TAG, "Flushing output stream.");
                output.flush();
                Log.d(TAG, "Output flushed.");
            } catch (IOException e) {
                Log.e(TAG, "IO Exception: " + e.getMessage());
                e.printStackTrace();
                return false;
            } finally {
                try {
                    if (output != null) {
                        output.close();
                        Log.d("Asdasdasd", "Output stream closed sucessfully.");
                    }
                    else{
                        Log.d(TAG, "Output stream is null");
                    }
                } catch (IOException e){
                    Log.e("Asdasdasd", "Couldn't close output stream: " + e.getMessage());
                    e.printStackTrace();
                    return false;
                }












            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            progress.setVisibility(View.GONE);

            Toast.makeText(getContext() , "File Successfully Downloaded in Downloads/EduSchoolApp" , Toast.LENGTH_LONG).show();

        }
    }


}
