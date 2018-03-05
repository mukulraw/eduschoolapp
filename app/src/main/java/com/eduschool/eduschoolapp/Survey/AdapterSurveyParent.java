package com.eduschool.eduschoolapp.Survey;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SurveyListParentPOJO.ServeyDatum;
import com.eduschool.eduschoolapp.SurveyListParentPOJO.SurveyListteacher;

import java.util.List;
import java.util.Objects;

/**
 * Created by user on 8/18/2017.
 */

public class AdapterSurveyParent extends RecyclerView.Adapter<AdapterSurveyParent.myviewholder> {

    Context context;
    private List<SurveyListteacher> list;

    public AdapterSurveyParent(Context context, List<SurveyListteacher> albumList) {
        this.context = context;
        this.list = albumList;

    }

    public void setGridData(List<SurveyListteacher> list)
    {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public AdapterSurveyParent.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.survey_frgmnt_model, parent, false);

        return new AdapterSurveyParent.myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterSurveyParent.myviewholder holder, int position) {
        SurveyListteacher item = list.get(position);

        holder.qus.setText(item.getSurveyTitle());
        //holder.date.setText(item.getPostDate());


        for (int i = 0 ; i < item.getServeyData().size() ; i++)
        {
            if (Objects.equals(item.getServeyData().get(i).getQuestionStatus(), "Pending"))
            {
                //if (!Objects.equals(item.getOpenSurvey(), "yes"))
                //{
                  //  holder.status.setText("Closed");
                //}
                //else
                //{
                    holder.status.setText("Pending");
                //}


            }
            else
            {
                holder.status.setText("Complete");
            }
        }


        //holder.status.setText(item.getQuestionStatus());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        TextView date,status,qus;

        public myviewholder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.date);
            status = (TextView) itemView.findViewById(R.id.status);
            qus = (TextView) itemView.findViewById(R.id.qus);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String Id = list.get(getAdapterPosition()).getSurveyId();
                    //String qusId = list.get(getAdapterPosition()).();

                    Intent intent=new Intent(context,TakeSurveyParent.class);
                    intent.putExtra("Id", Id);
                    //intent.putExtra("QusId", qusId);
                    intent.putExtra("title" , list.get(getAdapterPosition()).getSurveyTitle());
                    //intent.putExtra("status", list.get(getAdapterPosition()).getQuestionStatus());
                    context.startActivity(intent);




                }
            });
        }

    }


}


