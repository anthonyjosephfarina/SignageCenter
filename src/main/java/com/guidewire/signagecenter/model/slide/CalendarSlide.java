package com.guidewire.signagecenter.model.slide;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = SlideType.Values.CALENDAR)
public class CalendarSlide extends AbstractSlide {

    @Column(length = 100)
    private String title;

}
