package com.eduschool.eduschoolapp.recReqPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 9/20/2017.
 */

public class RecevrequestList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("from_type")
    @Expose
    private String fromType;
    @SerializedName("from")
    @Expose
    private String from;
    @SerializedName("birth_card")
    @Expose
    private String birthCard;
    @SerializedName("birth_date")
    @Expose
    private String birthDate;
    @SerializedName("to_type")
    @Expose
    private String toType;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("hr_time")
    @Expose
    private String hrTime;
    @SerializedName("min_time")
    @Expose
    private String minTime;
    @SerializedName("event_type")
    @Expose
    private String eventType;
    @SerializedName("if_important")
    @Expose
    private String ifImportant;
    @SerializedName("import_attach")
    @Expose
    private String importAttach;
    @SerializedName("additional_detail")
    @Expose
    private String additionalDetail;
    @SerializedName("post_date")
    @Expose
    private String postDate;
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

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
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

    public String getAdditionalDetail() {
        return additionalDetail;
    }

    public void setAdditionalDetail(String additionalDetail) {
        this.additionalDetail = additionalDetail;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
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
