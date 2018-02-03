package com.eduschool.eduschoolapp.Library;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import com.eduschool.eduschoolapp.LibraryBookPOJO.BookList;
import com.eduschool.eduschoolapp.R;

/**
 * Created by user on 6/24/2017.
 */

public class AdapterLibraryTeacher extends RecyclerView.Adapter<AdapterLibraryTeacher.myviewholder> {

    Context context;
    private List<BookList> albumList;

    public AdapterLibraryTeacher(Context context, List<BookList> albumList) {
        this.context = context;
        this.albumList = albumList;

    }

    public void setGridData(List<BookList> albumList) {
        this.albumList = albumList;
        notifyDataSetChanged();
    }

    @Override
    public myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.library_model, parent, false);

        return new myviewholder(itemView);
    }


    @Override
    public void onBindViewHolder(final myviewholder holder, int position) {
        BookList item = albumList.get(position);

        holder.name.setText(item.getTitle());

        if (item.getBookAvailableId().equals("0")) {
            holder.status.setText("Available");
            holder.status.setBackgroundResource(R.drawable.lib2);
        } else if (item.getBookAvailableId().equals("1")) {
            holder.status.setText("Borrowed");
            holder.status.setBackgroundResource(R.drawable.lib3);
        } else if (item.getBookAvailableId().equals("2")) {
            holder.status.setText("Reserved");
            holder.status.setBackgroundResource(R.drawable.lib1);
        }

        holder.author.setText("Author - " + item.getAuthor());
        holder.book_no.setText("Book No. : " + item.getBookNo());

        holder.layout.removeAllViews();

        for (int i = 0; i < item.getBookCategory().size(); i++) {

            TextView text = new TextView(context);

            text.setBackgroundResource(R.drawable.library_background);
            text.setText(item.getBookCategory().get(i).getChapcatName());
            text.setTextSize(16);
            text.setPadding(10 , 10 , 10 ,10);


            holder.layout.addView(text);

        }
    }


    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        TextView name, status, author, book_no;
        LinearLayout layout;


        public myviewholder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            status = (TextView) itemView.findViewById(R.id.status);
            book_no = (TextView) itemView.findViewById(R.id.book_no);
            author = (TextView) itemView.findViewById(R.id.author);
            layout = (LinearLayout) itemView.findViewById(R.id.layout);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //Intent intent = new Intent(context,TeacherLibrary2.class);

                    //context.startActivity(intent);
                    Intent intent = new Intent(context, TeacherLibrary2.class);
                    intent.putExtra("Id",albumList.get(getAdapterPosition()).getBookNoId());
                    intent.putExtra("Status",albumList.get(getAdapterPosition()).getBookAvailableId());
                    context.startActivity(intent);
                }
            });
        }

    }


}



