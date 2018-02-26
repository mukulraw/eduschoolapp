package com.eduschool.eduschoolapp.LoginPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mukul on 26-02-2018.
 */

public class Module {

    @SerializedName("module_name")
    @Expose
    private String moduleName;
    @SerializedName("status")
    @Expose
    private String status;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
