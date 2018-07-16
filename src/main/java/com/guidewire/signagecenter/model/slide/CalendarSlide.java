package com.guidewire.signagecenter.model.slide;


import com.guidewire.signagecenter.model.calendar.AbstractCalendar;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = SlideType.Values.CALENDAR)
public class CalendarSlide extends AbstractSlide {

    @Column(length = 100)
    private String title;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")
    private List<AbstractCalendar> calendars = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AbstractCalendar> getCalendars() {
        return calendars;
    }

    public void setCalendars(List<AbstractCalendar> calendars) {
        this.calendars = calendars;
    }
}
