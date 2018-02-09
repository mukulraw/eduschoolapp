package com.eduschool.eduschoolapp;

import com.android.volley.toolbox.StringRequest;

/**
 * Created by TBX on 31-01-2018.
 */

public class notiBean {

    String type;
    String date;
    String title;
    String desc;
    String data;
    String noti;


    public String getNoti() {
        return noti;
    }

    public void setNoti(String noti) {
        this.noti = noti;
    }



    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
