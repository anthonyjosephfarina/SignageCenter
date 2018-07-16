package com.guidewire.signagecenter.model.calendar;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class InternalCalendarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private CalendarEventType type;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = true)
    private LocalTime time;

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
