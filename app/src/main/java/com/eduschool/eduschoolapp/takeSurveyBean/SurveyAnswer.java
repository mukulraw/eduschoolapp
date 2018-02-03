package com.eduschool.eduschoolapp.takeSurveyBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 9/20/2017.
 */

public class SurveyAnswer {

    @SerializedName("survey_id")
    @Expose
    private String surveyId;
    @SerializedName("survey_data")
    @Expose
    private List<SurveyDatum> surveyData = null;

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public List<SurveyDatum> getSurveyData() {
        return surveyData;
    }

    public void setSurveyData(List<SurveyDatum> surveyData) {
        this.surveyData = surveyData;
    }

}
