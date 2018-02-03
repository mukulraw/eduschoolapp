package com.eduschool.eduschoolapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ExamAndResults.ExamfrgmntTwo;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.parentExamPOJO.ResultDetail;
import com.eduschool.eduschoolapp.parentExamPOJO.parentExamBean;
import com.eduschool.eduschoolapp.studentResultPOJO.studentResultBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by mukul on 16/11/17.
 */

public class Exam2 extends AppCompatActivity {

    Toolbar toolbar;
    String id;
    ProgressBar progress;

    TextView percent, cgpa, grade, marks, name;
    RecyclerView grid;
    GridLayoutManager manager;
    ResultAdapter adapter;

    List<ResultDetail> list;

    String cid, sid, sname, stid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_result);

        Log.d("asdasd", "Adasd");

        Bundle b = getIntent().getExtras();

        id = b.getString("id");
        cid = b.getString("cid");
        sid = b.getString("sid");
        sname = b.getString("sName");
        stid = b.getString("stid");


        list = new ArrayList<>();


        grid = (RecyclerView) findViewById(R.id.grid);
        manager = new GridLayoutManager(this, 1);
        adapter = new ResultAdapter(this, list);


        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);


        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle(b.getString("title"));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


        percent = (TextView) findViewById(R.id.percent);
        cgpa = (TextView) findViewById(R.id.cgpa);
        grade = (TextView) findViewById(R.id.grade);
        marks = (TextView) findViewById(R.id.marks);
        name = (TextView) findViewById(R.id.name);
        progress = (ProgressBar) findViewById(R.id.progress);


        progress.setVisibility(View.VISIBLE);

        Log.d("asdasd", "1");


        User u = (User) getApplicationContext();


        name.setText("Name of Student - " + sname);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);

        Call<parentExamBean> call = cr.getStudentResult(u.school_id, cid, sid, stid, id);

        call.enqueue(new Callback<parentExamBean>() {
            @Override
            public void onResponse(Call<parentExamBean> call, Response<parentExamBean> response) {

                Log.d("asdasd" , String.valueOf(response.body().getResultDetail().size()));

                if (response.body().getResultDetail().size() > 0)
                {
                    adapter.setGridData(response.body().getResultDetail());
                }
                else
                {

                    Log.d("asdasd" , String.valueOf(response.body().getResultDetail().size()));

                    final Dialog dialog = new Dialog(Exam2.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(true);
                    dialog.setContentView(R.layout.no_result_popup);
                    dialog.show();


                    Button ok = (Button)dialog.findViewById(R.id.ok);


                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            dialog.dismiss();

                            finish();

                        }
                    });

                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {

                            finish();

                        }
                    });

                }


                try {

                    percent.setText(response.body().getResult().getPercantage() + "%");
                    grade.setText(response.body().getResult().getGrade());

                    Double c = Double.parseDouble(response.body().getResult().getPercantage()) / 9.5;

                    cgpa.setText(String.format("%.1f", c));
                    marks.setText(response.body().getResult().getTotalObtaimarks());

                }catch (Exception e)
                {
                    e.printStackTrace();
                }



                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<parentExamBean> call, Throwable throwable) {
                progress.setVisibility(View.GONE);
            }
        });


    }


    public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

        List<ResultDetail> list = new ArrayList<>();
        Context context;

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

        public ResultAdapter(Context context, List<ResultDetail> list) {
            this.list = list;
            this.context = context;
        }

        public void setGridData(List<ResultDetail> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.parent_exam_list_model, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            ResultDetail item = list.get(position);


            String ft = item.getFromTime();
            String tt = item.getToTime();

            String f[] = ft.split(":");
            String t[] = tt.split(":");


            holder.time.setText(String.valueOf(Integer.parseInt(t[0]) - Integer.parseInt(f[0])) + " hrs");
            holder.scored.setText(item.getObtainMark());
            holder.maximum.setText(item.getMaxMark());
            holder.highest.setText(item.getMinMark());
            holder.subject.setText(item.getSubjectName());


            String date = item.getExamDate();
            String dd[] = date.split("-");

            holder.month.setText(mon[Integer.parseInt(dd[1]) - 1] + " " + dd[0]);

            holder.day.setText(dd[2]);


        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView day, month, subject, time, scored, highest, maximum;

            public ViewHolder(View itemView) {
                super(itemView);

                day = (TextView) itemView.findViewById(R.id.day);
                month = (TextView) itemView.findViewById(R.id.month);
                subject = (TextView) itemView.findViewById(R.id.subject);
                time = (TextView) itemView.findViewById(R.id.time);
                scored = (TextView) itemView.findViewById(R.id.scored);
                highest = (TextView) itemView.findViewById(R.id.highest);
                maximum = (TextView) itemView.findViewById(R.id.max);

            }
        }

    }


}
