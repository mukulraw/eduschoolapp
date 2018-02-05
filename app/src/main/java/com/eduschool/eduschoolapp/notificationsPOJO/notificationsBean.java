package com.eduschool.eduschoolapp.notificationsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 11/6/2017.
 */

public class notificationsBean {

    @SerializedName("total_notification")
    @Expose
    private Integer totalNotification;
    @SerializedName("birthday_notify")
    @Expose
    private List<BirthdayNotify> birthdayNotify = null;
    @SerializedName("homework_due")
    @Expose
    private List<HomeworkDue> homeworkDue = null;
    @SerializedName("classwork_unupdate")
    @Expose
    private List<ClassworkUnupdate> classworkUnupdate = null;
    @SerializedName("other")
    @Expose
    private List<Other> other = null;
    @SerializedName("library_bookreturn")
    @Expose
    private List<Object> libraryBookreturn = null;

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

    public List<ClassworkUnupdate> getClassworkUnupdate() {
        return classworkUnupdate;
    }

    public void setClassworkUnupdate(List<ClassworkUnupdate> classworkUnupdate) {
        this.classworkUnupdate = classworkUnupdate;
    }

    public List<Other> getOther() {
        return other;
    }

    public void setOther(List<Other> other) {
        this.other = other;
    }

    public List<Object> getLibraryBookreturn() {
        return libraryBookreturn;
    }

    public void setLibraryBookreturn(List<Object> libraryBookreturn) {
        this.libraryBookreturn = libraryBookreturn;
    }

}
