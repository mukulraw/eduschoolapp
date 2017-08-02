package com.eduschool.eduschoolapp.HomeWork;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.ViewHomeWrkPOJO.HomeworkList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 7/27/2017.
 */

public class AdaapterSearchList extends RecyclerView.Adapter<AdaapterSearchList.myviewholder> {

    Context context;
    List<com.eduschool.eduschoolapp.HwListSearchPOJO.HomeworkList> list = new ArrayList<>();

    public AdaapterSearchList(Context context, List<com.eduschool.eduschoolapp.HwListSearchPOJO.HomeworkList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<com.eduschool.eduschoolapp.HwListSearchPOJO.HomeworkList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdaapterSearchList.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.hw_list_model, parent, false);

        return new AdaapterSearchList.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdaapterSearchList.myviewholder holder, int position) {

        com.eduschool.eduschoolapp.HwListSearchPOJO.HomeworkList item = list.get(position);


        //loader.displayImage(item.get);
        holder.title.setText(item.getTitle());
        holder.className.setText(item.getClass_());
        holder.section.setText(item.getSection());
        holder.subject.setText("Subject : "+item.getSubject());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        TextView title, section, subject;
        TextView className;
        CardView card;

        public myviewholder(View itemView) {
            super(itemView);


            title = (TextView) itemView.findViewById(R.id.title);
            section=(TextView) itemView.findViewById(R.id.section);
            subject = (TextView) itemView.findViewById(R.id.subject);
            className = (TextView) itemView.findViewById(R.id.className);
            card = (CardView) itemView.findViewById(R.id.card);


        }


    }
}