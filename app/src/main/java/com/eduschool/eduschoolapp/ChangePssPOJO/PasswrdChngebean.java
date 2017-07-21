package com.eduschool.eduschoolapp.ChangePssPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 7/18/2017.
 */

public class PasswrdChngebean {
    @SerializedName("password_change")
    @Expose
    private List<PasswordChange> passwordChange = null;

    public List<PasswordChange> getPasswordChange() {
        return passwordChange;
    }

    public void setPasswordChange(List<PasswordChange> passwordChange) {
        this.passwordChange = passwordChange;
    }

}
