package eskool.com.eskoolapp.Survey;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import eskool.com.eskoolapp.R;

/**
 * Created by user on 6/15/2017.
 */

public class AdapterSurvey extends RecyclerView.Adapter<AdapterSurvey.myviewholder> {

    Context context;
    private List<Album> albumList;

    public AdapterSurvey(Context context, List<Album> albumList) {
        this.context = context;
        this.albumList = albumList;

    }

    /*  public void setGridData(List<CartDatum> list)
      {
          this.list = list;
          notifyDataSetChanged();
      }
  */
    @Override
    public AdapterSurvey.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.survey_frgmnt_model, parent, false);

        return new myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterSurvey.myviewholder holder, int position) {
    }


    @Override
    public int getItemCount() {
        return 8;
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        TextView name;


        public myviewholder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    context.startActivity(new Intent(context, Take_Survey.class));
                }
            });
        }

        }


    }

