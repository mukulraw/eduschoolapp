package com.eduschool.eduschoolapp.allNotificationPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 07-02-2018.
 */

public class ClassworkUpdate {

    @SerializedName("notify_id")
    @Expose
    private String notifyId;
    @SerializedName("classwork_id")
    @Expose
    private String classworkId;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("chapter")
    @Expose
    private String chapter;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("class_status")
    @Expose
    private String classStatus;

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getClassworkId() {
        return classworkId;
    }

    public void setClassworkId(String classworkId) {
        this.classworkId = classworkId;
    }

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

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(String classStatus) {
        this.classStatus = classStatus;
    }

}
