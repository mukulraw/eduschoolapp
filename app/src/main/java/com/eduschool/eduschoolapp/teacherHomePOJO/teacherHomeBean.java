package com.eduschool.eduschoolapp.teacherHomePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 11/13/2017.
 */

public class teacherHomeBean {

    @SerializedName("class_teacher")
    @Expose
    private String classTeacher;
    @SerializedName("my_class")
    @Expose
    private String myClass;
    @SerializedName("my_section")
    @Expose
    private String mySection;
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
    @SerializedName("event")
    @Expose
    private List<Event> event = null;

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    public String getMyClass() {
        return myClass;
    }

    public void setMyClass(String myClass) {
        this.myClass = myClass;
    }

    public String getMySection() {
        return mySection;
    }

    public void setMySection(String mySection) {
        this.mySection = mySection;
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

    public List<Event> getEvent() {
        return event;
    }

    public void setEvent(List<Event> event) {
        this.event = event;
    }
}
