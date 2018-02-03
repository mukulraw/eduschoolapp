package com.eduschool.eduschoolapp.teacherTimeTablePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 11/2/2017.
 */

public class teacherTimeTableBean {

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
