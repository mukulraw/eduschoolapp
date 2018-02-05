package com.eduschool.eduschoolapp.WishingCards;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CheckableImageButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.BirthStuListPOJO.BirthStuList;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.birthPOJO.BirthList;

import java.util.ArrayList;
import java.util.List;

import static com.eduschool.eduschoolapp.R.id.holiday;
import static com.eduschool.eduschoolapp.R.id.simpleCheckedTextView;

/**
 * Created by user on 8/23/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.myviewholder> {


    Context context;
    List<BirthList> list = new ArrayList<>();

    List<AdapterBean> p = new ArrayList<>();

    public Adapter(Context context, List<BirthList> list) {
        this.context = context;
        this.list = list;
    }

    public void setGridData(List<BirthList> list, List<AdapterBean> p) {
        this.list = list;
        this.p = p;
        notifyDataSetChanged();
    }

    public List<AdapterBean> getCheckList() {


        return p;
    }

    @Override
    public Adapter.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {



        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.birthday_student_model, parent, false);

        return new Adapter.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final Adapter.myviewholder holder, final int position) {

        holder.setIsRecyclable(false);

        final AdapterBean bean = new AdapterBean();
        final BirthList item = list.get(position);

        bean.setId(item.getId());
        bean.setStatus("0");
        p.add(position, bean);

        holder.ctv.setText(item.getName());

        holder.ctv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

//                    holder.ctv.setChecked(false);

                    AdapterBean bean1 = new AdapterBean();

                    //p.clear();
                    bean1.setId(item.getId());
                    bean1.setStatus("1");
                    p.set(position, bean1);

                } else {
  //                  holder.ctv.setChecked(true);
                    AdapterBean bean1 = new AdapterBean();
                    //p.clear();
                    bean1.setId(item.getId());
                    bean1.setStatus("0");
                    p.set(position, bean1);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        CheckBox ctv;


        public myviewholder(View itemView) {
            super(itemView);

            ctv = (CheckBox) itemView.findViewById(R.id.checkedTextView1);

            this.setIsRecyclable(false);

        }


    }
}
