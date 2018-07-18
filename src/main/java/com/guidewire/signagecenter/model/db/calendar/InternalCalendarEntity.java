package com.guidewire.signagecenter.model.db.calendar;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = CalendarType.Values.INTERNAL)
public class InternalCalendarEntity extends AbstractCalendarEntity {

    @JsonBackReference
    @OneToMany(mappedBy = "calendar", fetch = FetchType.LAZY)
    private List<InternalCalendarEventEntity> events = new ArrayList<>();

    public List<InternalCalendarEventEntity> getEvents() {
        return events;
    }

    public void setEvents(List<InternalCalendarEventEntity> events) {
        this.events = events;
    }
}
