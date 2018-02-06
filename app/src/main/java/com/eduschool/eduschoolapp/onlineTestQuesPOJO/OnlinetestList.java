package com.eduschool.eduschoolapp.onlineTestQuesPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by USER on 06-02-2018.
 */

public class OnlinetestList {

    @SerializedName("onlinetest_id")
    @Expose
    private String onlinetestId;
    @SerializedName("questin_id")
    @Expose
    private String questinId;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("hr")
    @Expose
    private String hr;
    @SerializedName("min")
    @Expose
    private String min;
    @SerializedName("total_question")
    @Expose
    private Integer totalQuestion;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("question_option")
    @Expose
    private List<QuestionOption> questionOption = null;

    public String getOnlinetestId() {
        return onlinetestId;
    }

    public void setOnlinetestId(String onlinetestId) {
        this.onlinetestId = onlinetestId;
    }

    public String getQuestinId() {
        return questinId;
    }

    public void setQuestinId(String questinId) {
        this.questinId = questinId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public Integer getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(Integer totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<QuestionOption> getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(List<QuestionOption> questionOption) {
        this.questionOption = questionOption;
    }


}
