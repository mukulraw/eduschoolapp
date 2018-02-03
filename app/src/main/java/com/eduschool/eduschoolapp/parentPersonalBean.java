package com.eduschool.eduschoolapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 11/6/2017.
 */

public class parentPersonalBean {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

}
