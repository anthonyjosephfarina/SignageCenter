package com.guidewire.signagecenter.model.dto.calendar;

import com.guidewire.signagecenter.model.db.calendar.CalendarEventType;
import com.guidewire.signagecenter.model.db.calendar.InternalCalendarEventEntity;

import java.time.Instant;
/**
 * DTO for CalendarEvent .
 *
 * @author
 */
public class CalendarEventGetDTO {

    private Long id;

    private String name;

    private String description;

    private CalendarEventType type;

    private Instant date;

    private boolean allDay = false;

    private Long calendarId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public static CalendarEventGetDTO map(InternalCalendarEventEntity event) {
        CalendarEventGetDTO calendarEventGetDTO = new CalendarEventGetDTO();
        calendarEventGetDTO.setId(event.getId());
        calendarEventGetDTO.setName(event.getName());
        calendarEventGetDTO.setDescription(event.getDescription());
        calendarEventGetDTO.setType(event.getType());
        calendarEventGetDTO.setDate(event.getDate());
        calendarEventGetDTO.setAllDay(event.isAllDay());
        calendarEventGetDTO.setCalendarId(event.getCalendar().getId());
        return calendarEventGetDTO;
    }
}
