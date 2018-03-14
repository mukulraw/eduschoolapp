package com.eduschool.eduschoolapp.eventParentPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class eventParentBean {

    @SerializedName("holiday_data")
    @Expose
    private List<HolidayDatum> holidayData = null;
    @SerializedName("event_data")
    @Expose
    private List<EventDatum> eventData = null;

    public List<HolidayDatum> getHolidayData() {
        return holidayData;
    }

    public void setHolidayData(List<HolidayDatum> holidayData) {
        this.holidayData = holidayData;
    }

    public List<EventDatum> getEventData() {
        return eventData;
    }

    public void setEventData(List<EventDatum> eventData) {
        this.eventData = eventData;
    }

}
