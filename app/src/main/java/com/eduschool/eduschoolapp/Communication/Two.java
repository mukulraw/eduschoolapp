package com.eduschool.eduschoolapp.Communication;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Path;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.RaiseRequest.FrgmntOne;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.sentCommunicationTeacher.RequestList;
import com.eduschool.eduschoolapp.sentCommunicationTeacher.sentCommunicationTeacherBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

public class Two extends Fragment {
    Toolbar toolbar;

    RecyclerView grid;
    ProgressBar progress;
    GridLayoutManager manager;
    List<RequestList> list;
    SentAdapter adapter;

    public Two() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.communication_two, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

        list = new ArrayList<>();

        manager = new GridLayoutManager(getContext() , 1);
        progress = (ProgressBar)v.findViewById(R.id.progress);
        grid = (RecyclerView) v.findViewById(R.id.grid);

        adapter = new SentAdapter(getContext() , list);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);



        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Communication");


        progress.setVisibility(View.VISIBLE);


        User u = (User) getContext().getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);

        Call<sentCommunicationTeacherBean> call = cr.getTeacherCommunication(u.school_id , u.user_id , "Teacher");

        call.enqueue(new Callback<sentCommunicationTeacherBean>() {
            @Override
            public void onResponse(Call<sentCommunicationTeacherBean> call, Response<sentCommunicationTeacherBean> response) {

                adapter.setGridData(response.body().getRequestList());

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<sentCommunicationTeacherBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });



    }

    public class SentAdapter extends RecyclerView.Adapter<SentAdapter.ViewHolder>
    {
        Context context;
        List<RequestList> list = new ArrayList<>();

        public SentAdapter(Context context , List<RequestList> list)
        {
            this.context = context;
            this.list = list;
        }


        public void setGridData(List<RequestList> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.teacher_sent_list_model , parent , false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            //holder.setIsRecyclable(false);

            final RequestList item = list.get(position);


            /*if (Objects.equals(item.getType(), "PTM"))
            {
                holder.date.setText(item.getToDate());
            }
            else if (Objects.equals(item.getType(), "Collect Report Card"))
            {
                holder.date.setText(item.getToDate());
            }*/


            if (Objects.equals(item.getType(), "Birthday Card"))
            {


                Log.d("type" , item.getType());

                //holder.date.setText(item.getPostDate());

                holder.date.setVisibility(View.GONE);


                if (item.getTime().length() > 0)
                {
                    holder.time.setVisibility(View.VISIBLE);
                }
                else
                {
                    holder.time.setVisibility(View.GONE);
                }



                //holder.time.setText(item.getTime());
                holder.title.setText(item.getType());
                //holder.desc.setText(item.getDetail());

                holder.desc.setVisibility(View.GONE);

                String p = item.getPostDate();

                String to = "";

                for (int i = 0 ; i < item.getTo().size() ; i++)
                {
                    to = to + item.getTo().get(i).getName() + System.getProperty("line.separator");
                }


                Log.d("to" , to);


                holder.to.setText(to);

                try {

                    String p1[] = p.split("-");

                    holder.day.setText(p1[0]);
                    holder.month.setText(p1[1] + " " + p1[2]);

                }catch (Exception e)
                {
                    e.printStackTrace();
                }



            }
            else
            {
                holder.date.setText(item.getToDate());

                holder.date.setVisibility(View.VISIBLE);


                if (item.getTime().length() > 0)
                {
                    holder.time.setVisibility(View.VISIBLE);
                }
                else
                {
                    holder.time.setVisibility(View.GONE);
                }



                holder.time.setText(item.getTime());
                holder.title.setText(item.getType());
                holder.desc.setText(item.getDetail());

                holder.desc.setVisibility(View.VISIBLE);

                String p = item.getFromDate();

                String to = "";

                for (int i = 0 ; i < item.getSection().size() ; i++)
                {

                    to = to + item.getSection().get(i).getClassname() + " " + item.getSection().get(i).getSectionname() + System.getProperty("line.separator");

                }

                holder.to.setText(to);

                try {

                    String p1[] = p.split("-");

                    holder.day.setText(p1[0]);
                    holder.month.setText(p1[1] + " " + p1[2]);

                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

            if (item.getImportAttach().length() > 0)
            {
                holder.download.setVisibility(View.VISIBLE);
            }
            else
            {
                holder.download.setVisibility(View.GONE);
            }

            if (Objects.equals(item.getIfImportant(), "no"))
            {
                holder.imp.setVisibility(View.GONE);
            }
            else
            {
                holder.imp.setVisibility(View.VISIBLE);
            }


            holder.download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    progress.setVisibility(View.VISIBLE);

                    User b = (User) getActivity().getApplicationContext();


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .build();

                    AllAPIs cr = retrofit.create(AllAPIs.class);

                    cr.getFile(item.getImportAttach()).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {



                            try {

                                String dpath = item.getImportAttach();
                                String[] dd = dpath.split("/");

                                DownloadFileAsyncTask downloadFileAsyncTask = new DownloadFileAsyncTask(item.getImportAttach(), dd[dd.length - 1]);
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


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (item.getBirthCard().length() > 0)
                    {
                        Dialog dialog = new Dialog(context);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.birth_popup);
                        dialog.setCancelable(true);
                        dialog.show();

                        ImageView ikm = (ImageView)dialog.findViewById(R.id.image);

                        ImageLoader loader = ImageLoader.getInstance();
                        loader.displayImage(item.getBirthCard() , ikm);

                    }




                }
            });


        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {

            TextView day , month , title , desc , date , time , to , imp;

            ImageView download;


            public ViewHolder(View itemView) {
                super(itemView);

                day = (TextView)itemView.findViewById(R.id.day);
                month = (TextView)itemView.findViewById(R.id.month);
                title = (TextView)itemView.findViewById(R.id.title);
                desc = (TextView)itemView.findViewById(R.id.desc);
                date = (TextView)itemView.findViewById(R.id.date);
                time = (TextView)itemView.findViewById(R.id.time);
                to = (TextView)itemView.findViewById(R.id.to);
                imp = (TextView)itemView.findViewById(R.id.imp);
                download = (ImageView)itemView.findViewById(R.id.download);

                this.setIsRecyclable(false);

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


                /*Snackbar snackbar = Snackbar.make(coordinate , "File Downloaded in Downloads/EduSchoolApp" , Snackbar.LENGTH_SHORT).setAction("VIEW", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        openFile(file);

                    }
                });

                snackbar.show();
*/
                Toast.makeText(getContext() , "File Successfully Downloaded in Downloads/EduSchoolApp" , Toast.LENGTH_LONG).show();

            }
        }

    }

}
