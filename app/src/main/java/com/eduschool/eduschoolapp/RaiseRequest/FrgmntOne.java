package com.eduschool.eduschoolapp.RaiseRequest;


import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;


import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
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
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.recReqPOJO.RecevrequestList;
import com.eduschool.eduschoolapp.recReqPOJO.recReqBean;
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
 * Created by User on 5/10/2017.
 */

public class FrgmntOne extends Fragment {

    RecyclerView grid;
    ProgressBar progress;
    Toolbar toolbar;
    GridLayoutManager manager;
    List<RecevrequestList> list;
    RecAdapter adapter;

    CoordinatorLayout coordinate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.raise_request_frgmnt1, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);

        coordinate = (CoordinatorLayout)v.findViewById(R.id.coordinate);

        progress = (ProgressBar)v.findViewById(R.id.progress);
        grid = (RecyclerView)v.findViewById(R.id.grid);
        manager = new GridLayoutManager(getContext() , 1);
        list = new ArrayList<>();

        adapter = new RecAdapter(getActivity() , list);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Raise Request");
        User u = (User) getContext().getApplicationContext();

        u.back = true;




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<recReqBean> call = cr.getRec(u.school_id , u.user_id , u.user_class , u.user_section , "Parent");

        call.enqueue(new Callback<recReqBean>() {
            @Override
            public void onResponse(Call<recReqBean> call, Response<recReqBean> response) {

                adapter.setGridData(response.body().getRecevrequestList());

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<recReqBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });




    }

    public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder>
    {

        Context context;
        List<RecevrequestList> list = new ArrayList<>();

        public RecAdapter(Context context , List<RecevrequestList> list)
        {
            this.context = context;
            this.list = list;
        }

        public void setGridData(List<RecevrequestList> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.rec_req_model , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            final RecevrequestList item = list.get(position);

            if (item.getBirthCard().length() > 0)
            {
                String dat = item.getPostDate();

                String[] d1 = dat.split("-");

                holder.date.setText(d1[0]);

                try {
                    holder.month.setText(d1[1] + " " + d1[2]);
                }catch (Exception e2)
                {
                    e2.printStackTrace();
                }

            }
            else {
                String dat = item.getStartDate();

                String[] d1 = dat.split("-");

                holder.date.setText(d1[0]);

                try {
                    holder.month.setText(d1[1] + " " + d1[2]);
                }catch (Exception e2)
                {
                    e2.printStackTrace();
                }

            }


            if (Objects.equals(item.getIfImportant(), "no"))
            {
                holder.imp.setVisibility(View.GONE);
            }
            else
            {
                holder.imp.setVisibility(View.VISIBLE);
            }



            holder.text.setText(item.getAdditionalDetail());

            if (item.getTime().length() > 0)
            {
                holder.time.setText(item.getTime());
                holder.time.setVisibility(View.VISIBLE);
            }
            else
            {
                holder.time.setVisibility(View.GONE);
            }



            if (item.getImportAttach().length() > 0)
            {
holder.download.setVisibility(View.VISIBLE);
            }
            else
            {
                holder.download.setVisibility(View.GONE);
            }



            holder.name.setText(item.getEventType() + " (by " + item.getFrom() + ")");

            String e = item.getEndDate();

            String[] dd = e.split("-");

            try {
                holder.end.setText(dd[0] + " " + dd[1] + " " + dd[2]);
            }catch (Exception e1)
            {
                e1.printStackTrace();
            }



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


        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {

TextView date , month , name , end , text , time , imp;

ImageView download;

            public ViewHolder(View itemView) {
                super(itemView);

                date = (TextView)itemView.findViewById(R.id.date);
                month = (TextView)itemView.findViewById(R.id.month);
                name = (TextView)itemView.findViewById(R.id.name);
                end = (TextView)itemView.findViewById(R.id.end);
                text = (TextView)itemView.findViewById(R.id.text);
                time = (TextView)itemView.findViewById(R.id.time);
                imp = (TextView)itemView.findViewById(R.id.imp);

                download = (ImageView)itemView.findViewById(R.id.download);

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

        private void openFile(File url) {

            try {

                Uri uri = Uri.fromFile(url);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
                    // Word document
                    intent.setDataAndType(uri, "application/msword");
                } else if (url.toString().contains(".pdf")) {
                    // PDF file
                    intent.setDataAndType(uri, "application/pdf");
                } else if (url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
                    // Powerpoint file
                    intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
                } else if (url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
                    // Excel file
                    intent.setDataAndType(uri, "application/vnd.ms-excel");
                } else if (url.toString().contains(".zip") || url.toString().contains(".rar")) {
                    // WAV audio file
                    intent.setDataAndType(uri, "application/x-wav");
                } else if (url.toString().contains(".rtf")) {
                    // RTF file
                    intent.setDataAndType(uri, "application/rtf");
                } else if (url.toString().contains(".wav") || url.toString().contains(".mp3")) {
                    // WAV audio file
                    intent.setDataAndType(uri, "audio/x-wav");
                } else if (url.toString().contains(".gif")) {
                    // GIF file
                    intent.setDataAndType(uri, "image/gif");
                } else if (url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
                    // JPG file
                    intent.setDataAndType(uri, "image/jpeg");
                } else if (url.toString().contains(".txt")) {
                    // Text file
                    intent.setDataAndType(uri, "text/plain");
                } else if (url.toString().contains(".3gp") || url.toString().contains(".mpg") ||
                        url.toString().contains(".mpeg") || url.toString().contains(".mpe") || url.toString().contains(".mp4") || url.toString().contains(".avi")) {
                    // Video files
                    intent.setDataAndType(uri, "video/*");
                } else {
                    intent.setDataAndType(uri, "*/*");
                }

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, "No application found which can open the file", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
