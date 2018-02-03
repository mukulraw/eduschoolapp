package com.eduschool.eduschoolapp.HomeWrkDetailsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mukul on 20/09/17.
 */

public class FileAttachment {

    @SerializedName("attach_id")
    @Expose
    private String attachId;
    @SerializedName("attach_file")
    @Expose
    private String attachFile;

    public String getAttachId() {
        return attachId;
    }

    public void setAttachId(String attachId) {
        this.attachId = attachId;
    }

    public String getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }

}
