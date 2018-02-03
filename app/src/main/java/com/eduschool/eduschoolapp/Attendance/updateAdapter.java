package com.eduschool.eduschoolapp.Attendance;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.AttendncDatePOJO.AttendanceDatum;
import com.eduschool.eduschoolapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class updateAdapter extends RecyclerView.Adapter<updateAdapter.myviewholder> {
    private Context context;
    List<AttendanceDatum> list = new ArrayList<>();
    private boolean aBoolean = false, pBoolean = true, lBoolean = false;
    private List<String> p = new ArrayList<>();


    public updateAdapter(Context context, List<AttendanceDatum> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<AttendanceDatum> list, List<String> p) {
        this.list = list;
        this.p = p;
        notifyDataSetChanged();
    }

    public List<String> getCheckList() {

        return p;
    }

    @Override
    public myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.mark_attndnc_model, parent, false);

        return new myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final myviewholder holder, final int position) {

        AttendanceDatum item = list.get(position);


        if (position == list.size() - 1)
        {
            holder.line.setVisibility(View.GONE);
        }
        else
        {
            holder.line.setVisibility(View.VISIBLE);
        }


        holder.name.setText(item.getStudentName());


        holder.no.setText(String.valueOf(position + 1));



        String pp = item.getAttendance();


        Log.d("posiiton" , pp);

        if (Objects.equals(pp, "0"))
        {
            p.add("0");
            pBoolean = false;
            holder.a.setBackground(context.getResources().getDrawable(R.drawable.absentcheckbox));
            holder.a.setTextColor(Color.WHITE);
            holder.p.setTextColor(Color.BLACK);
            holder.l.setTextColor(Color.BLACK);
            holder.p.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
            holder.l.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
        } else if (Objects.equals(pp, "1"))
        {
            p.add("1");
            holder.p.setBackground(context.getResources().getDrawable(R.drawable.checkbox));
            holder.p.setTextColor(Color.WHITE);
            holder.a.setTextColor(Color.BLACK);
            holder.l.setTextColor(Color.BLACK);
            holder.a.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
            holder.l.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
        } else if (Objects.equals(pp, "2"))
        {
            p.add("2");
            holder.l.setBackground(context.getResources().getDrawable(R.drawable.leavecheckbox));
            holder.l.setTextColor(Color.WHITE);
            holder.p.setTextColor(Color.BLACK);
            holder.a.setTextColor(Color.BLACK);
            holder.a.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
            holder.p.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
        }


        holder.a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                p.set(position, "0");
                pBoolean = false;
                holder.a.setBackground(context.getResources().getDrawable(R.drawable.absentcheckbox));
                holder.a.setTextColor(Color.WHITE);
                holder.p.setTextColor(Color.BLACK);
                holder.l.setTextColor(Color.BLACK);
                holder.p.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                holder.l.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
            }
        });


        holder.p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.set(position, "1");
                holder.p.setBackground(context.getResources().getDrawable(R.drawable.checkbox));
                holder.p.setTextColor(Color.WHITE);
                holder.a.setTextColor(Color.BLACK);
                holder.l.setTextColor(Color.BLACK);
                holder.a.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                holder.l.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
            }
        });

        holder.l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.set(position, "2");
                holder.l.setBackground(context.getResources().getDrawable(R.drawable.leavecheckbox));
                holder.l.setTextColor(Color.WHITE);
                holder.p.setTextColor(Color.BLACK);
                holder.a.setTextColor(Color.BLACK);
                holder.a.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
                holder.p.setBackground(context.getResources().getDrawable(R.drawable.defaultcheckbox));
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        TextView name, p, a, l, no , line;


        public myviewholder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            p = (TextView) itemView.findViewById(R.id.p);
            a = (TextView) itemView.findViewById(R.id.a);
            l = (TextView) itemView.findViewById(R.id.l);
            no = (TextView) itemView.findViewById(R.id.no);
            line = (TextView)itemView.findViewById(R.id.line);

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
