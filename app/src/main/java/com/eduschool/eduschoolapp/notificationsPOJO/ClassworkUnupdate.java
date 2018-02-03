package com.eduschool.eduschoolapp.notificationsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 03-02-2018.
 */

public class ClassworkUnupdate {

    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("status")
    @Expose
    private String status;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
