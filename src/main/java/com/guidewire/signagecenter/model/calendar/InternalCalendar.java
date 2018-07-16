package com.guidewire.signagecenter.model.calendar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = CalendarType.Values.INTERNAL)
public class InternalCalendar {

}
