package com.teamcodeflux.devcup.android.festival.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultSet {

    List<Event> events;

    List<Post> comments;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Post> getComments() {
        return comments;
    }

    public void setComments(List<Post> posts) {
        this.comments = posts;
    }
}
