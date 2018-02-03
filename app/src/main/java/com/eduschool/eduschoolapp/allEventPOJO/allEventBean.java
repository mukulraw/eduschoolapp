package com.eduschool.eduschoolapp.allEventPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 11/16/2017.
 */

public class allEventBean {

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
