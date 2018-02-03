package com.eduschool.eduschoolapp.parentHomePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mukul on 19/11/17.
 */

public class parentHomeBean {

    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("today_date")
    @Expose
    private String todayDate;
    @SerializedName("home_work")
    @Expose
    private List<HomeWork> homeWork = null;
    @SerializedName("time_table")
    @Expose
    private TimeTable timeTable;
    @SerializedName("accadmics")
    @Expose
    private List<Accadmic> accadmics = null;
    @SerializedName("class_work")
    @Expose
    private List<ClassWork> classWork = null;

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public void setTodayDate(String todayDate) {
        this.todayDate = todayDate;
    }

    public List<HomeWork> getHomeWork() {
        return homeWork;
    }

    public void setHomeWork(List<HomeWork> homeWork) {
        this.homeWork = homeWork;
    }

    public TimeTable getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    public List<Accadmic> getAccadmics() {
        return accadmics;
    }

    public void setAccadmics(List<Accadmic> accadmics) {
        this.accadmics = accadmics;
    }

    public List<ClassWork> getClassWork() {
        return classWork;
    }

    public void setClassWork(List<ClassWork> classWork) {
        this.classWork = classWork;
    }

}
