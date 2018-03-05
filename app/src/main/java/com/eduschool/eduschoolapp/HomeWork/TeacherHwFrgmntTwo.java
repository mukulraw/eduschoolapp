package com.eduschool.eduschoolapp.HomeWork;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.HomewrkPOJO.FileAttachment;
import com.eduschool.eduschoolapp.HomewrkPOJO.HomewrkBean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentListbean;
import com.eduschool.eduschoolapp.User;

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
 * Created by user on 5/25/2017.
 */

public class TeacherHwFrgmntTwo extends Fragment {
    Toolbar toolbar;
    Button edit;
    ProgressBar progress;
    TextView subjectName, classSection, createDate, dueDate, title, completed, incomplete, note , dd;
    LinearLayout complited, incomplited;
    List<String> list, complete;


    RecyclerView grid;
    GridLayoutManager manager;
    List<FileAttachment> l;
    DownloadAdapter adapter;
    String strtext;

    public TeacherHwFrgmntTwo() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher_hw_frgmnt_two, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);
        strtext = getArguments().getString("message");
        //Toast.makeText(getActivity(),String.valueOf(strtext),Toast.LENGTH_SHORT).show();

        complited = (LinearLayout) view.findViewById(R.id.complitedLayout);
        incomplited = (LinearLayout) view.findViewById(R.id.incompletelayout);
        subjectName = (TextView) view.findViewById(R.id.subjectName);
        dd = (TextView) view.findViewById(R.id.dd);
        classSection = (TextView) view.findViewById(R.id.classSection);
        createDate = (TextView) view.findViewById(R.id.createDate);
        dueDate = (TextView) view.findViewById(R.id.dueDate);
        title = (TextView) view.findViewById(R.id.title);
        note = (TextView) view.findViewById(R.id.note);
        completed = (TextView) view.findViewById(R.id.completed);
        incomplete = (TextView) view.findViewById(R.id.incomplete);
        edit = (Button) view.findViewById(R.id.edit);
        progress = (ProgressBar) view.findViewById(R.id.progress);

        l = new ArrayList<>();

        grid = (RecyclerView)view.findViewById(R.id.grid);

        manager = new GridLayoutManager(getContext() , 1);
        adapter = new DownloadAdapter(getContext() , l);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);

        list = new ArrayList<>();
        complete = new ArrayList<>();




        /*incomplited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setTitle("Homework Incomplete:-");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.completed_list);

                ListView listView = (ListView) dialog.findViewById(R.id.listView);
                TextView back = (TextView) dialog.findViewById(R.id.back);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, android.R.id.text1, list);

                listView.setAdapter(adapter);

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }


        });*/


        /*complited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setTitle("Homework Completed:-");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.completed_list);

                ListView listView = (ListView) dialog.findViewById(R.id.listView);
                TextView back = (TextView) dialog.findViewById(R.id.back);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, android.R.id.text1, complete);

                // Assign adapter to ListView
                listView.setAdapter(adapter);

                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }


        });
*/

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                TeacherHwFrgmntThree frag1 = new TeacherHwFrgmntThree();
                Bundle bundle=new Bundle();
                bundle.putString("message", strtext);
                frag1.setArguments(bundle);
                ft.replace(R.id.replace, frag1);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Home Work Details");
        User u = (User) getContext().getApplicationContext();
        u.back = false;

        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getFragmentManager();
                fm.popBackStack();

            }
        });

        final User b = (User) getActivity().getApplicationContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final AllAPIs cr = retrofit.create(AllAPIs.class);
        Call<HomewrkBean> call2 = cr.homewrk(b.school_id, strtext);
        progress.setVisibility(View.VISIBLE);


        call2.enqueue(new Callback<HomewrkBean>() {

            @Override
            public void onResponse(Call<HomewrkBean> call, Response<HomewrkBean> response) {


                subjectName.setText(response.body().getHomeworkData().get(0).getSubject());
                classSection.setText(response.body().getHomeworkData().get(0).getClass_() + " " + response.body().getHomeworkData().get(0).getSection());
                createDate.setText(response.body().getHomeworkData().get(0).getCreateDate());
                title.setText(response.body().getHomeworkData().get(0).getTitle());
                dueDate.setText("(" + response.body().getHomeworkData().get(0).getDueDate() + ")");
                dd.setText("Due Date (" + response.body().getHomeworkData().get(0).getDueDate() + ")");
                note.setText(response.body().getHomeworkData().get(0).getNotes());
                completed.setText(response.body().getHomeworkData().get(0).getTotalCompletehomworkStudent().toString());
                incomplete.setText(response.body().getHomeworkData().get(0).getTotalPendinghomworkStudent().toString());

                Log.d("sdc","sdxs");

                progress.setVisibility(View.GONE);



                adapter.setGridData(response.body().getHomeworkData().get(0).getFileAttachment());



            }

            @Override
            public void onFailure(Call<HomewrkBean> call, Throwable throwable) {
                progress.setVisibility(View.GONE);

            }

        });



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

            Toast.makeText(getContext() , "Successfully Downloaded in Downloads/EduSchoolApp" , Toast.LENGTH_LONG).show();

        }
    }

}

