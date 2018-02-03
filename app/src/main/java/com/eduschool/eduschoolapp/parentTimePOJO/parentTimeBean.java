package com.eduschool.eduschoolapp.parentTimePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 10/3/2017.
 */

public class parentTimeBean {

    @SerializedName("time_table_list")
    @Expose
    private List<TimeTableList> timeTableList = null;

    public List<TimeTableList> getTimeTableList() {
        return timeTableList;
    }

    public void setTimeTableList(List<TimeTableList> timeTableList) {
        this.timeTableList = timeTableList;
    }

}
