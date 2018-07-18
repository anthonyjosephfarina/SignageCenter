package com.guidewire.signagecenter.model.db.slide;


import com.guidewire.signagecenter.model.db.calendar.AbstractCalendarEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = SlideType.Values.CALENDAR)
public class CalendarSlideEntity extends AbstractSlideEntity {

    @Column(length = 100)
    private String title;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")
    private List<AbstractCalendarEntity> calendars = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AbstractCalendarEntity> getCalendars() {
        return calendars;
    }

    public void setCalendars(List<AbstractCalendarEntity> calendars) {
        this.calendars = calendars;
    }
}
