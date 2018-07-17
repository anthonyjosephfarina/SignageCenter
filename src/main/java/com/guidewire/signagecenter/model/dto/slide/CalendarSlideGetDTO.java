package com.guidewire.signagecenter.model.dto.slide;

import com.guidewire.signagecenter.model.dto.calendar.CalendarEventGetDTO;

import java.util.ArrayList;
import java.util.List;

public class CalendarSlideGetDTO extends AbstractSlideGetDTO {

    private List<CalendarEventGetDTO> events = new ArrayList<>();

    public List<CalendarEventGetDTO> getEvents() {
        return events;
    }

    public void setEvents(List<CalendarEventGetDTO> events) {
        this.events = events;
    }
}
