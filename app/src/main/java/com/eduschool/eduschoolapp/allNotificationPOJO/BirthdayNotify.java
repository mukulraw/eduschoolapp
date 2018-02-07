package com.eduschool.eduschoolapp.allNotificationPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 07-02-2018.
 */

public class BirthdayNotify {

    @SerializedName("notify_id")
    @Expose
    private String notifyId;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("section")
    @Expose
    private String section;

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

}
