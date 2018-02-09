package com.eduschool.eduschoolapp.teacherSurveyPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 11/3/2017.
 */

public class teacherSurveryBean {

    @SerializedName("survey_listteacher")
    @Expose
    private List<SurveyListteacher> surveyListteacher = null;

    public List<SurveyListteacher> getSurveyListteacher() {
        return surveyListteacher;
    }

    public void setSurveyListteacher(List<SurveyListteacher> surveyListteacher) {
        this.surveyListteacher = surveyListteacher;
    }
}
