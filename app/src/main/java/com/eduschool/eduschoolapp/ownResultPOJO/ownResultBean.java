package com.eduschool.eduschoolapp.ownResultPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 12/4/2017.
 */

public class ownResultBean {

    @SerializedName("result_list")
    @Expose
    private List<ResultList> resultList = null;

    public List<ResultList> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultList> resultList) {
        this.resultList = resultList;
    }

}
