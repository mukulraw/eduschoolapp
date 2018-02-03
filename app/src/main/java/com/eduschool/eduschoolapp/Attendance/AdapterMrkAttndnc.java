package com.eduschool.eduschoolapp.Attendance;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.ClassWork.AdapterClsWrkList;
import com.eduschool.eduschoolapp.ClassWork.TeacherClsWrk2;
import com.eduschool.eduschoolapp.ClassWrkListPOJO.ClassworkList;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.attList;
import com.eduschool.eduschoolapp.studentLeavePOJO.StudentList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Created by user on 8/19/2017.
 */

public class AdapterMrkAttndnc extends RecyclerView.Adapter<AdapterMrkAttndnc.myviewholder> {


    Context context;
    List<attList> list = new ArrayList<>();
    boolean aBoolean = false, pBoolean = true, lBoolean = false;
    List<String> p = new ArrayList<>();
    Boolean flag;

    public AdapterMrkAttndnc(Context context, List<attList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<attList> list, List<String> p , Boolean flag) {
        this.list = list;
        this.p = p;
        this.flag = flag;
        notifyDataSetChanged();
    }


    public List<String> getCheckList() {
        return p;
    }

    @Override
    public AdapterMrkAttndnc.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.mark_attndnc_model, parent, false);

        return new AdapterMrkAttndnc.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final myviewholder holder, final int position) {

        attList item = list.get(position);

        //Log.d("size" , String.valueOf(p.size()));

        if (position == list.size() - 1) {
            holder.line.setVisibility(View.GONE);
        } else {
            holder.line.setVisibility(View.VISIBLE);
        }


        holder.name.setText(item.getStudentName());


        holder.no.setText(String.valueOf(position + 1));


        //Log.d("adapter" , p.get(position));

        try {


            if (!flag)
            {
                if (Objects.equals(p.get(position), "2"))
                {
                    p.set(position, "2");
                    holder.l.setBackground(context.getResources().getDrawable(R.drawable.leavecheckbox));
                    holder.l.setTextColor(Color.WHITE);
                    holder.p.setTextColor(Color.BLACK);
                    holder.a.setTextColor(Color.BLACK);
                    holder.a.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                    holder.p.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                }
                else if (Objects.equals(p.get(position), "1"))
                {
                    p.set(position, "1");
                    holder.p.setBackground(context.getResources().getDrawable(R.drawable.checkbox));
                    holder.p.setTextColor(Color.WHITE);
                    holder.a.setTextColor(Color.BLACK);
                    holder.l.setTextColor(Color.BLACK);
                    holder.a.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                    holder.l.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                }
            }
            else if(flag)
            {

                Log.d("att" , item.getAttendance());

                if (Objects.equals(item.getAttendance(), "2"))
                {
//                    p.set(position, "2");
                    holder.l.setBackground(context.getResources().getDrawable(R.drawable.leavecheckbox));
                    holder.l.setTextColor(Color.WHITE);
                    holder.p.setTextColor(Color.BLACK);
                    holder.a.setTextColor(Color.BLACK);
                    holder.a.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                    holder.p.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                }
                else if (Objects.equals(item.getAttendance(), "1"))
                {
//                    p.set(position, "1");
                    holder.p.setBackground(context.getResources().getDrawable(R.drawable.checkbox));
                    holder.p.setTextColor(Color.WHITE);
                    holder.a.setTextColor(Color.BLACK);
                    holder.l.setTextColor(Color.BLACK);
                    holder.a.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                    holder.l.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                }
                else if (Objects.equals(item.getAttendance(), "0"))
                {
//                  p.set(position, "0");
                    holder.a.setBackground(context.getResources().getDrawable(R.drawable.absentcheckbox));
                    holder.a.setTextColor(Color.WHITE);
                    holder.p.setTextColor(Color.BLACK);
                    holder.l.setTextColor(Color.BLACK);
                    holder.p.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                    holder.l.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                }
            }




        }catch (Exception e)
        {
            e.printStackTrace();
        }




            holder.a.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        p.set(position, "0");
                        pBoolean = false;
                        holder.a.setBackground(context.getResources().getDrawable(R.drawable.absentcheckbox));
                        holder.a.setTextColor(Color.WHITE);
                        holder.p.setTextColor(Color.BLACK);
                        holder.l.setTextColor(Color.BLACK);
                        holder.p.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                        holder.l.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }


                }
            });


            holder.p.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        p.set(position, "1");
                        holder.p.setBackground(context.getResources().getDrawable(R.drawable.checkbox));
                        holder.p.setTextColor(Color.WHITE);
                        holder.a.setTextColor(Color.BLACK);
                        holder.l.setTextColor(Color.BLACK);
                        holder.a.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                        holder.l.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }


                }
            });

            holder.l.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        p.set(position, "2");
                        holder.l.setBackground(context.getResources().getDrawable(R.drawable.leavecheckbox));
                        holder.l.setTextColor(Color.WHITE);
                        holder.p.setTextColor(Color.BLACK);
                        holder.a.setTextColor(Color.BLACK);
                        holder.a.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                        holder.p.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }


                }
            });





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        TextView name, p, a, l, no, line;


        public myviewholder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            p = (TextView) itemView.findViewById(R.id.p);
            a = (TextView) itemView.findViewById(R.id.a);
            l = (TextView) itemView.findViewById(R.id.l);
            no = (TextView) itemView.findViewById(R.id.no);
            line = (TextView) itemView.findViewById(R.id.line);

          /*  itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String pos = list.get(getAdapterPosition()).getStudentId();

                    android.support.v4.app.FragmentManager fm=((AppCompatActivity)context).getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    TeacherClsWrk2 frag1 = new TeacherClsWrk2();
                    Bundle bundle=new Bundle();
                    bundle.putString("message", pos);
                    frag1.setArguments(bundle);
                    ft.replace(R.id.replace, frag1);
                    ft.addToBackStack(null);
                    ft.commit();


                }
            });*/

        }


    }
}

