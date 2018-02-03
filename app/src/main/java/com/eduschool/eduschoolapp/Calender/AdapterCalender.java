package com.eduschool.eduschoolapp.Calender;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.ClassWork.AdapterClsWrkList;
import com.eduschool.eduschoolapp.ClassWork.TeacherClsWrk2;
import com.eduschool.eduschoolapp.ClassWrkListPOJO.ClassworkList;
import com.eduschool.eduschoolapp.HolidayPOJO.HolidayList;
import com.eduschool.eduschoolapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by user on 8/22/2017.
 */

public class AdapterCalender extends RecyclerView.Adapter<AdapterCalender.myviewholder> {



    Context context;
    List<HolidayList> list = new ArrayList<>();

    public AdapterCalender(Context context, List<HolidayList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<HolidayList> list) {
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

        HolidayList item = list.get(position);


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

