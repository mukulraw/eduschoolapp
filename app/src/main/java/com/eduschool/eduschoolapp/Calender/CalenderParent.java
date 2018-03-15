package com.eduschool.eduschoolapp.Calender;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.HolidayPOJO.HolidayList;
import com.eduschool.eduschoolapp.HolidayPOJO.HolidayListBean;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.calendarBean;
import com.eduschool.eduschoolapp.eventParentPOJO.eventParentBean;
import com.eduschool.eduschoolapp.notiBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 6/27/2017.
 */

public class CalenderParent  extends Fragment {


    Toolbar toolbar;
    ProgressBar progress;
    private RecyclerView recyclerView;
    GridLayoutManager manager;
    List<calendarBean> list;
    AdapterCalender adapter;



    public CalenderParent() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.calendar_teacher, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);

        list = new ArrayList<>();

        adapter = new AdapterCalender(getActivity(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);


        User b = (User) getActivity().getApplicationContext();


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<eventParentBean> call = cr.event_parent(b.school_id , b.user_id);




        call.enqueue(new Callback<eventParentBean>() {
            @Override
            public void onResponse(Call<eventParentBean> call, Response<eventParentBean> response) {

                for (int i = 0 ; i < response.body().getEventData().size() ; i++)
                {
                    calendarBean bea = new calendarBean();
                    bea.setFromDate(response.body().getEventData().get(i).getFromDate());
                    bea.setToDate(response.body().getEventData().get(i).getToDate());
                    bea.setHolidayId(response.body().getEventData().get(i).getId());
                    bea.setOccasion(response.body().getEventData().get(i).getOccasion());
                    list.add(bea);
                }

                for (int i = 0 ; i < response.body().getHolidayData().size() ; i++)
                {
                    calendarBean bea = new calendarBean();
                    bea.setFromDate(response.body().getHolidayData().get(i).getFromDate());
                    bea.setToDate(response.body().getHolidayData().get(i).getToDate());
                    bea.setHolidayId(response.body().getHolidayData().get(i).getId());
                    bea.setOccasion(response.body().getHolidayData().get(i).getOccasion());
                    list.add(bea);
                }

                Collections.sort(list, new Comparator<calendarBean>() {
                    @Override
                    public int compare(calendarBean o1, calendarBean o2) {

                        if (o1.getFromDate().length() == 0 || o2.getFromDate().length() == 0)
                            return 0;


                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");


                        try {
                            return sdf.parse(o1.getFromDate()).compareTo(sdf.parse(o2.getFromDate()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                            return 0;
                        }


                        //return o2.getFromDate().compareTo(o1.getFromDate());
                    }
                });


                adapter.setGridData(list);
                adapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<eventParentBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Calendar");
        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }


    public class AdapterCalender extends RecyclerView.Adapter<AdapterCalender.myviewholder> {



        Context context;
        List<calendarBean> list = new ArrayList<>();

        public AdapterCalender(Context context, List<calendarBean> list) {
            this.context = context;
            this.list = list;
        }

        public void setGridData(List<calendarBean> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public AdapterCalender.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.holiday_list_model, parent, false);

            return new AdapterCalender.myviewholder(itemView);
        }


        @Override
        public void onBindViewHolder(final AdapterCalender.myviewholder holder, int position) {

            calendarBean item = list.get(position);


            //loader.displayImage(item.get);

            if (Objects.equals(item.getToDate(), item.getFromDate()))
            {
                holder.from.setText(item.getFromDate());
            }
            else
            {
                holder.from.setText(item.getFromDate() + "\n\nto\n\n" + item.getToDate());
            }


            if (position == list.size()-1)
            {
                holder.line.setVisibility(View.GONE);
                holder.from.setBackgroundResource(R.drawable.calendar_back);
                holder.holiday.setBackgroundResource(R.drawable.calendar_back_2);
            }
            else
            {
                holder.line.setVisibility(View.VISIBLE);
            }


            holder.holiday.setText(item.getOccasion());


        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class myviewholder extends RecyclerView.ViewHolder {


            TextView from,holiday , line;




            public myviewholder(View itemView) {
                super(itemView);

                from = (TextView) itemView.findViewById(R.id.from);
                holiday = (TextView) itemView.findViewById(R.id.holiday);
                line = (TextView)itemView.findViewById(R.id.line);

            }


        }
    }


}
