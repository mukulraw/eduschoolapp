package com.eduschool.eduschoolapp.StudentSummary;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.Library.Album;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.parentAttendanceBean;
import com.eduschool.eduschoolapp.parentAttendanceSummaryPOJO.AttendanceSummary;
import com.eduschool.eduschoolapp.parentAttendanceSummaryPOJO.parentAttendanceSummaryBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 6/27/2017.
 */

public class ParentAttendanceFrgmnt extends Fragment {
    private RecyclerView recyclerView;

    private AdapterCalendar adapter;

    String cName , sName , stName , cid , sid , stid;

    ProgressBar progress;

    TextView name;

    List<AttendanceSummary> list;

    TextView day , month;

    TextView sec;

    public ParentAttendanceFrgmnt() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_view_attendance, container, false);

        name = (TextView)view.findViewById(R.id.name);
        sec = (TextView)view.findViewById(R.id.classection);

        progress = (ProgressBar)view.findViewById(R.id.progress);
        day = (TextView)view.findViewById(R.id.day);
        month = (TextView)view.findViewById(R.id.month);


        String date = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());

        String[] d1 = date.split("-");

        day.setText(d1[0]);
        month.setText(d1[1] + " " + d1[2]);


        list = new ArrayList<>();

        Bundle b = getArguments();

        cName = b.getString("cName");
        sName = b.getString("sName");
        stName = b.getString("stName");
        cid = b.getString("cid");
        sid = b.getString("sid");
        stid = b.getString("stid");

        sec.setText(cName + sName);

        name.setText("Name of Student - " + stName);



        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        list = new ArrayList<>();
        adapter=new AdapterCalendar(getContext(),list);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();



        progress.setVisibility(View.VISIBLE);


        User u = (User) getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);


        Call<parentAttendanceSummaryBean> call = cr.getParentAttendance(u.school_id , stid , cid , sid);

        call.enqueue(new Callback<parentAttendanceSummaryBean>() {
            @Override
            public void onResponse(Call<parentAttendanceSummaryBean> call, Response<parentAttendanceSummaryBean> response) {


                adapter.setGridData(response.body().getAttendanceSummary());


                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<parentAttendanceSummaryBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Log.d("asdasd" , t.toString());
            }
        });


    }

    public class AdapterCalendar extends RecyclerView.Adapter<AdapterCalendar.myviewholder> {

        Context context;
        private List<AttendanceSummary> list;

        public AdapterCalendar(Context context, List<AttendanceSummary> albumList) {
            this.context = context;
            this.list = albumList;
        }

          public void setGridData(List<AttendanceSummary> list)
          {
              this.list = list;
              notifyDataSetChanged();
          }

        @Override
        public myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.parent_attendance_model, parent, false);

            return new myviewholder(itemView);
        }


        @Override
        public void onBindViewHolder(final myviewholder holder, int position) {

            AttendanceSummary item = list.get(position);

            holder.month.setText(item.getMonth() + " " + item.getYear());
            holder.working.setText(String.valueOf(item.getWorkingDay()));

            holder.absent.setText(String.valueOf("ABSENT"+ System.getProperty("line.separator") + System.getProperty("line.separator") + item.getAbsent()));
            holder.present.setText(String.valueOf("PRESENT"+ System.getProperty("line.separator") + System.getProperty("line.separator") + item.getPresent()));

        }


        @Override
        public int getItemCount() {
            return list.size();
        }

        public class myviewholder extends RecyclerView.ViewHolder {


            TextView month , working , present , absent;


            public myviewholder(View itemView) {
                super(itemView);

                month = (TextView)itemView.findViewById(R.id.month);
                working = (TextView)itemView.findViewById(R.id.working);
                present = (TextView)itemView.findViewById(R.id.present);
                absent = (TextView)itemView.findViewById(R.id.absent);



            }

        }


    }

}
