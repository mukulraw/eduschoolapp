package com.eduschool.eduschoolapp.takeSurveyBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 12/6/2017.
 */

public class SurveyDatum {

    @SerializedName("question_id")
    @Expose
    private String questionId;
    @SerializedName("option_value")
    @Expose
    private String optionValue;
    @SerializedName("survey_option")
    @Expose
    private List<SurveyOption> surveyOption = null;
    @SerializedName("your_answer_id")
    @Expose
    private String yourAnswerId;
    @SerializedName("your_answer")
    @Expose
    private String yourAnswer;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    public List<SurveyOption> getSurveyOption() {
        return surveyOption;
    }

    public void setSurveyOption(List<SurveyOption> surveyOption) {
        this.surveyOption = surveyOption;
    }

    public String getYourAnswerId() {
        return yourAnswerId;
    }

    public void setYourAnswerId(String yourAnswerId) {
        this.yourAnswerId = yourAnswerId;
    }

    public String getYourAnswer() {
        return yourAnswer;
    }

    public void setYourAnswer(String yourAnswer) {
        this.yourAnswer = yourAnswer;
    }

}
