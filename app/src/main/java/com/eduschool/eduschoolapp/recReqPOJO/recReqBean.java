package com.eduschool.eduschoolapp.recReqPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 9/20/2017.
 */

public class recReqBean {

    @SerializedName("recevrequest_list")
    @Expose
    private List<RecevrequestList> recevrequestList = null;

    public List<RecevrequestList> getRecevrequestList() {
        return recevrequestList;
    }

    public void setRecevrequestList(List<RecevrequestList> recevrequestList) {
        this.recevrequestList = recevrequestList;
    }


}
