package com.guidewire.signagecenter.model.calendar;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = CalendarType.Values.INTERNAL)
public class InternalCalendar extends AbstractCalendar {

    @JsonBackReference
    @OneToMany(mappedBy = "calendar", fetch = FetchType.LAZY)
    private List<InternalCalendarEvent> events = new ArrayList<>();

    public List<InternalCalendarEvent> getEvents() {
        return events;
    }

    public void setEvents(List<InternalCalendarEvent> events) {
        this.events = events;
    }
}
