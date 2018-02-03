package com.eduschool.eduschoolapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;



public class BasicCellViewGroup extends FrameLayout implements
        TableFixHeaderAdapter.FirstHeaderBinder<String>,
        TableFixHeaderAdapter.HeaderBinder<String>,
         TableFixHeaderAdapter.FirstBodyBinder<List<String>>,
        TableFixHeaderAdapter.BodyBinder<List<String>>,
        TableFixHeaderAdapter.SectionBinder<List<String>> {

    public TextView textView , first;

    View view;

    public BasicCellViewGroup(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.table_model , this , true);
        //LayoutInflater.from(context).inflate(R.layout.table_model, this, true);
        textView = (TextView) view.findViewById(R.id.text1);
        first = (TextView) view.findViewById(R.id.first);
    }


    @Override
    public void bindFirstHeader(String headerName) {
        //textView.setBackgroundColor(Color.parseColor("#cdcdcd"));
        //first.setBackgroundColor(Color.parseColor("#cdcdcd"));
        view.setBackgroundColor(Color.parseColor("#cdcdcd"));
        try {
            String[] d1 = headerName.split(",");

            textView.setText(d1[1]);

            first.setText(d1[0]);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void bindHeader(String headerName, int column) {
        view.setBackgroundColor(Color.parseColor("#cdcdcd"));
        //textView.setBackgroundColor(Color.parseColor("#cdcdcd"));

        try {
            String[] d1 = headerName.split(",");


            String sss = Html.fromHtml("<b>" + d1[0] + "</b>") + System.getProperty("line.separator") + System.getProperty("line.separator") + d1[1];

            SpannableString sr = new SpannableString(d1[0] + System.getProperty("line.separator") + System.getProperty("line.separator") + d1[1]);
            sr.setSpan(new StyleSpan(Typeface.BOLD), 0, d1[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            sr.setSpan((new ForegroundColorSpan(Color.parseColor("#01A8DD"))), 0, d1[0].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


            textView.setText(sr);
            textView.setGravity(Gravity.CENTER);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        first.setVisibility(GONE);

    }

    @Override
    public void bindFirstBody(List<String> items, int row) {
        //textView.setBackgroundColor(Color.parseColor("#cdcdcd"));
        //first.setBackgroundColor(Color.parseColor("#cdcdcd"));
        view.setBackgroundColor(Color.parseColor("#cdcdcd"));

        try {
            String[] d1 = items.get(row).split(",");

            first.setText(d1[0]);
            textView.setText(d1[1]);
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        //first.setVisibility(GONE);
    }

    @Override
    public void bindBody(List<String> items, int row, int column) {
        textView.setText(items.get(column));
        textView.setGravity(Gravity.CENTER);
        first.setVisibility(GONE);
    }

    @Override
    public void bindSection(List<String> item, int row, int column) {
        textView.setText(column == 0 ? "Section:" + (row + 1) : "");
        first.setVisibility(GONE);
    }

}
