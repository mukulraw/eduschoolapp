package com.eduschool.eduschoolapp.viewEventPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 11/6/2017.
 */

public class viewEventBean {

    @SerializedName("event_list")
    @Expose
    private List<EventList> eventList = null;

    public List<EventList> getEventList() {
        return eventList;
    }

    public void setEventList(List<EventList> eventList) {
        this.eventList = eventList;
    }

}
