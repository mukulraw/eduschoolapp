package com.eduschool.eduschoolapp.takeSurveyBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 9/20/2017.
 */

public class surveyAnsBean {

    @SerializedName("survey_answer")
    @Expose
    private List<SurveyAnswer> surveyAnswer = null;

    public List<SurveyAnswer> getSurveyAnswer() {
        return surveyAnswer;
    }

    public void setSurveyAnswer(List<SurveyAnswer> surveyAnswer) {
        this.surveyAnswer = surveyAnswer;
    }


}
