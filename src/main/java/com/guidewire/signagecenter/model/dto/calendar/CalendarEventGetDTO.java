package com.guidewire.signagecenter.model.dto.calendar;

import com.guidewire.signagecenter.model.calendar.CalendarEventType;
import com.guidewire.signagecenter.model.calendar.InternalCalendarEvent;

import java.time.LocalDate;
import java.time.LocalTime;

public class CalendarEventGetDTO {

    private Long id;

    private String name;

    private String description;

    private CalendarEventType type;

    private LocalDate date;

    private LocalTime time;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Long getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(Long calendarId) {
        this.calendarId = calendarId;
    }

    public static CalendarEventGetDTO map(InternalCalendarEvent event) {
        CalendarEventGetDTO calendarEventGetDTO = new CalendarEventGetDTO();
        calendarEventGetDTO.setId(event.getId());
        calendarEventGetDTO.setName(event.getName());
        calendarEventGetDTO.setDescription(event.getDescription());
        calendarEventGetDTO.setType(event.getType());
        calendarEventGetDTO.setDate(event.getDate());
        calendarEventGetDTO.setTime(event.getTime());
        calendarEventGetDTO.setCalendarId(event.getCalendar().getId());
        return calendarEventGetDTO;
    }
}
