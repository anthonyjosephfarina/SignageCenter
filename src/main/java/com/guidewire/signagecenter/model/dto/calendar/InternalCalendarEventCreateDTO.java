package com.guidewire.signagecenter.model.dto.calendar;

import com.guidewire.signagecenter.model.db.calendar.CalendarEventType;

import java.time.Instant;

public class InternalCalendarEventCreateDTO {

    private String name;

    private String description;

    private CalendarEventType type;

    private Instant date;

    private boolean allDay = true;

    private Long calendarId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CalendarEventType getType() {
        return type;
    }

    public void setType(CalendarEventType type) {
        this.type = type;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    public Long getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(Long calendarId) {
        this.calendarId = calendarId;
    }
}
