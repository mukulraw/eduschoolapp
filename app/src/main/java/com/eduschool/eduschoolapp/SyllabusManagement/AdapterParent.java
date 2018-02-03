package com.eduschool.eduschoolapp.SyllabusManagement;

import android.content.Context;
import android.graphics.Color;
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
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SyllabusPOJO.SyllabusList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 8/23/2017.
 */

public class AdapterParent extends RecyclerView.Adapter<AdapterParent.myviewholder> {


    Context context;
    List<SyllabusList> list = new ArrayList<>();

    public AdapterParent(Context context, List<SyllabusList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<SyllabusList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterParent.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.syllabus_model, parent, false);

        return new AdapterParent.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterParent.myviewholder holder, int position) {

        SyllabusList item = list.get(position);


        //loader.displayImage(item.get);
        holder.exam.setText(item.getExamName());
        holder.chapter.setText(item.getChapterListStatus().get(0).getChapterName());
        holder.no.setText(String.valueOf(position + 1) + ".");

        if (item.getChapterListStatus().get(0).getChapterStatus().equals("Pending")) {

            holder.status.setBackgroundResource(R.drawable.red_popup);
            holder.status.setText("Pending");
        } else if (item.getChapterListStatus().get(0).getChapterStatus().equals("Completed")) {
            holder.status.setBackgroundResource(R.drawable.green_popup);
            holder.status.setText("Completed");
        } else if (item.getChapterListStatus().get(0).getChapterStatus().equals("Ongoing")) {
            holder.status.setBackgroundResource(R.drawable.orange_popup);
            holder.status.setText("Ongoing");
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        TextView status, chapter, no;
        TextView exam;


        public myviewholder(View itemView) {
            super(itemView);

            status = (TextView) itemView.findViewById(R.id.status);
            chapter = (TextView) itemView.findViewById(R.id.chapter);
            no = (TextView) itemView.findViewById(R.id.no);
            exam = (TextView) itemView.findViewById(R.id.exam);


        }


    }
}

