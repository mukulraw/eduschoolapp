package com.eduschool.eduschoolapp.sentCommunicationTeacher;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 11/3/2017.
 */

public class RequestList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("birth_card")
    @Expose
    private String birthCard;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("to_type")
    @Expose
    private String toType;
    @SerializedName("from_date")
    @Expose
    private String fromDate;
    @SerializedName("to_date")
    @Expose
    private String toDate;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("hr_time")
    @Expose
    private String hrTime;
    @SerializedName("min_time")
    @Expose
    private String minTime;
    @SerializedName("post_month")
    @Expose
    private String postMonth;
    @SerializedName("exam_type")
    @Expose
    private String examType;
    @SerializedName("if_important")
    @Expose
    private String ifImportant;
    @SerializedName("import_attach")
    @Expose
    private String importAttach;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("post_date")
    @Expose
    private String postDate;
    @SerializedName("to")
    @Expose
    private List<To> to = null;
    @SerializedName("class")
    @Expose
    private List<Clas> _class = null;
    @SerializedName("section")
    @Expose
    private List<Section> section = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBirthCard() {
        return birthCard;
    }

    public void setBirthCard(String birthCard) {
        this.birthCard = birthCard;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getToType() {
        return toType;
    }

    public void setToType(String toType) {
        this.toType = toType;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHrTime() {
        return hrTime;
    }

    public void setHrTime(String hrTime) {
        this.hrTime = hrTime;
    }

    public String getMinTime() {
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = minTime;
    }

    public String getPostMonth() {
        return postMonth;
    }

    public void setPostMonth(String postMonth) {
        this.postMonth = postMonth;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getIfImportant() {
        return ifImportant;
    }

    public void setIfImportant(String ifImportant) {
        this.ifImportant = ifImportant;
    }

    public String getImportAttach() {
        return importAttach;
    }

    public void setImportAttach(String importAttach) {
        this.importAttach = importAttach;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public List<To> getTo() {
        return to;
    }

    public void setTo(List<To> to) {
        this.to = to;
    }

    public List<Clas> getClass_() {
        return _class;
    }

    public void setClass_(List<Clas> _class) {
        this._class = _class;
    }

    public List<Section> getSection() {
        return section;
    }

    public void setSection(List<Section> section) {
        this.section = section;
    }

}
