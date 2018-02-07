package com.eduschool.eduschoolapp.allNotificationPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by USER on 07-02-2018.
 */

public class allNotificationBean {

    @SerializedName("total_notification")
    @Expose
    private Integer totalNotification;
    @SerializedName("birthday_notify")
    @Expose
    private List<BirthdayNotify> birthdayNotify = null;
    @SerializedName("homework_due")
    @Expose
    private List<HomeworkDue> homeworkDue = null;
    @SerializedName("homework_create")
    @Expose
    private List<HomeworkCreate> homeworkCreate = null;
    @SerializedName("homework_update")
    @Expose
    private List<HomeworkUpdate> homeworkUpdate = null;
    @SerializedName("classwork_create")
    @Expose
    private List<ClassworkCreate> classworkCreate = null;
    @SerializedName("classwork_update")
    @Expose
    private List<ClassworkUpdate> classworkUpdate = null;
    @SerializedName("survey")
    @Expose
    private List<Survey> survey = null;
    @SerializedName("attendance")
    @Expose
    private List<Object> attendance = null;
    @SerializedName("communication")
    @Expose
    private List<Communication> communication = null;
    @SerializedName("library_bookreturn")
    @Expose
    private List<LibraryBookreturn> libraryBookreturn = null;

    public Integer getTotalNotification() {
        return totalNotification;
    }

    public void setTotalNotification(Integer totalNotification) {
        this.totalNotification = totalNotification;
    }

    public List<BirthdayNotify> getBirthdayNotify() {
        return birthdayNotify;
    }

    public void setBirthdayNotify(List<BirthdayNotify> birthdayNotify) {
        this.birthdayNotify = birthdayNotify;
    }

    public List<HomeworkDue> getHomeworkDue() {
        return homeworkDue;
    }

    public void setHomeworkDue(List<HomeworkDue> homeworkDue) {
        this.homeworkDue = homeworkDue;
    }

    public List<HomeworkCreate> getHomeworkCreate() {
        return homeworkCreate;
    }

    public void setHomeworkCreate(List<HomeworkCreate> homeworkCreate) {
        this.homeworkCreate = homeworkCreate;
    }

    public List<HomeworkUpdate> getHomeworkUpdate() {
        return homeworkUpdate;
    }

    public void setHomeworkUpdate(List<HomeworkUpdate> homeworkUpdate) {
        this.homeworkUpdate = homeworkUpdate;
    }

    public List<ClassworkCreate> getClassworkCreate() {
        return classworkCreate;
    }

    public void setClassworkCreate(List<ClassworkCreate> classworkCreate) {
        this.classworkCreate = classworkCreate;
    }

    public List<ClassworkUpdate> getClassworkUpdate() {
        return classworkUpdate;
    }

    public void setClassworkUpdate(List<ClassworkUpdate> classworkUpdate) {
        this.classworkUpdate = classworkUpdate;
    }

    public List<Survey> getSurvey() {
        return survey;
    }

    public void setSurvey(List<Survey> survey) {
        this.survey = survey;
    }

    public List<Object> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<Object> attendance) {
        this.attendance = attendance;
    }

    public List<Communication> getCommunication() {
        return communication;
    }

    public void setCommunication(List<Communication> communication) {
        this.communication = communication;
    }

    public List<LibraryBookreturn> getLibraryBookreturn() {
        return libraryBookreturn;
    }

    public void setLibraryBookreturn(List<LibraryBookreturn> libraryBookreturn) {
        this.libraryBookreturn = libraryBookreturn;
    }


}
