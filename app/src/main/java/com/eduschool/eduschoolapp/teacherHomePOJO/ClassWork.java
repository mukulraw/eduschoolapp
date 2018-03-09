package com.eduschool.eduschoolapp.teacherHomePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 11/13/2017.
 */

public class ClassWork {

    @SerializedName("class_work_id")
    @Expose
    private String classWorkId;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("class_status")
    @Expose
    private String classStatus;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public String getClassWorkId() {
        return classWorkId;
    }

    public void setClassWorkId(String classWorkId) {
        this.classWorkId = classWorkId;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
