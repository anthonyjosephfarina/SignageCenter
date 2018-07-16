package com.guidewire.signagecenter.model.dto.calendar;

import com.guidewire.signagecenter.model.calendar.CalendarType;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCalendarGetDTO {

    private Long id;

    private String name;

    private String description;

    private CalendarType type;

    private Long officeId;

    private String officeName;

    private Instant createdAt;

    private List<CalendarEventGetDTO> events = new ArrayList<>();

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

    public CalendarType getType() {
        return type;
    }

    public void setType(CalendarType type) {
        this.type = type;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<CalendarEventGetDTO> getEvents() {
        return events;
    }

    public void setEvents(List<CalendarEventGetDTO> events) {
        this.events = events;
    }
}
