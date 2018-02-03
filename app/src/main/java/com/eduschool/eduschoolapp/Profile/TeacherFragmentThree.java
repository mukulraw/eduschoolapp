package com.eduschool.eduschoolapp.Profile;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.teacherProfilePOJO.EducationDetail;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 5/5/2017.
 */

public class TeacherFragmentThree extends Fragment {


    List<EducationDetail> list = new ArrayList<>();
    RecyclerView grid;
    GridLayoutManager manager;


    public void setData(List<EducationDetail> list)
    {
        this.list = list;
    }


    public TeacherFragmentThree() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.teacher_profile_frgmnt3, container, false);

        grid = (RecyclerView)v.findViewById(R.id.grid);
        manager = new GridLayoutManager(getContext() , 1);

        ThirdAdapter adapter = new ThirdAdapter(getContext() , list);
        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);

        return v;
    }


    public class ThirdAdapter extends RecyclerView.Adapter<ThirdAdapter.Viewholder>
    {

        List<EducationDetail> list = new ArrayList<>();
        Context context;

        public ThirdAdapter(Context context , List<EducationDetail> list)
        {
            this.context = context;
            this.list = list;
        }

        @Override
        public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.third_model , parent , false);
            return new Viewholder(view);
        }

        @Override
        public void onBindViewHolder(Viewholder holder, int position) {
            EducationDetail item = list.get(position);

            holder.education.setText(item.getEducation());
            holder.passing.setText(item.getPassOutYear());
            holder.board.setText(item.getBoard());
            holder.grade.setText(item.getPercantageGrade());
            holder.comment.setText(item.getComment());



        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class Viewholder extends RecyclerView.ViewHolder
        {
            TextView education , passing , board , grade , comment;

            public Viewholder(View itemView) {
                super(itemView);

                education = (TextView)itemView.findViewById(R.id.education);
                passing = (TextView)itemView.findViewById(R.id.passing);
                board = (TextView)itemView.findViewById(R.id.board);
                grade = (TextView)itemView.findViewById(R.id.grade);
                comment = (TextView)itemView.findViewById(R.id.comment);

            }
        }

    }



}

