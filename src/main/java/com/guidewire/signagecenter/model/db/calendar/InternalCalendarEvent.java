package com.guidewire.signagecenter.model.db.calendar;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.guidewire.signagecenter.model.db.audit.DateAuditable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
public class InternalCalendarEvent extends DateAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private CalendarEventType type;

    @Column(nullable = false)
    private Instant date;

    @Column(nullable = false)
    private boolean allDay = true;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 250)
    private String description;

    @JsonManagedReference
    @ManyToOne
    private InternalCalendar calendar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public InternalCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(InternalCalendar calendar) {
        this.calendar = calendar;
    }
}
