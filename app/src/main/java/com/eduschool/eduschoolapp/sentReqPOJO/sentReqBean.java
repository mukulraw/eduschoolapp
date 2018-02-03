package com.eduschool.eduschoolapp.sentReqPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 9/20/2017.
 */

public class sentReqBean {

    @SerializedName("request_list")
    @Expose
    private List<RequestList> requestList = null;

    public List<RequestList> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<RequestList> requestList) {
        this.requestList = requestList;
    }

}
