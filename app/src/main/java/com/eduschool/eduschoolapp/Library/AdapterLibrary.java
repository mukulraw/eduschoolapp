package com.eduschool.eduschoolapp.Library;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.eduschool.eduschoolapp.R;

/**
 * Created by user on 6/24/2017.
 */

public class AdapterLibrary extends RecyclerView.Adapter<AdapterLibrary.myviewholder> {

    Context context;
    private List<Album> albumList;

    public AdapterLibrary(Context context, List<Album> albumList) {
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
    public AdapterLibrary.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.library_model, parent, false);

        return new myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final AdapterLibrary.myviewholder holder, int position) {

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

                    Intent intent = new Intent(context,ParentLibrary2.class);

                    context.startActivity(intent);


                }
            });
        }

    }


}


