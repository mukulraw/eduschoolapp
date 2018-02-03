package com.eduschool.eduschoolapp.teacherProfilePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TBX on 11/4/2017.
 */

public class EducationDetail {


    @SerializedName("education_id")
    @Expose
    private String educationId;
    @SerializedName("education")
    @Expose
    private String education;
    @SerializedName("pass_out_year")
    @Expose
    private String passOutYear;
    @SerializedName("board")
    @Expose
    private String board;
    @SerializedName("percantage/grade")
    @Expose
    private String percantageGrade;
    @SerializedName("comment")
    @Expose
    private String comment;

    public String getEducationId() {
        return educationId;
    }

    public void setEducationId(String educationId) {
        this.educationId = educationId;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPassOutYear() {
        return passOutYear;
    }

    public void setPassOutYear(String passOutYear) {
        this.passOutYear = passOutYear;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getPercantageGrade() {
        return percantageGrade;
    }

    public void setPercantageGrade(String percantageGrade) {
        this.percantageGrade = percantageGrade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
