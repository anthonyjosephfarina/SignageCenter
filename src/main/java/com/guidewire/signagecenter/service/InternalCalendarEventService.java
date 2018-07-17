package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.exception.ResourceNotFoundException;
import com.guidewire.signagecenter.model.db.calendar.InternalCalendar;
import com.guidewire.signagecenter.model.db.calendar.InternalCalendarEvent;
import com.guidewire.signagecenter.repository.InternalCalendarEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InternalCalendarEventService {

    private static final Logger logger = LoggerFactory.getLogger(InternalCalendarEventService.class);

    @Autowired
    private InternalCalendarEventRepository internalCalendarEventRepository;

    @Autowired
    private InternalCalendarService internalCalendarService;

    public InternalCalendarEvent create(InternalCalendarEvent calendarEvent, Long calendarId) {

        InternalCalendar internalCalendar = internalCalendarService.getCalendar(calendarId);
        calendarEvent.setCalendar(internalCalendar);

        return internalCalendarEventRepository.save(calendarEvent);
    }

    public void delete(Long calendarEventId) {
        InternalCalendarEvent calendar = getCalendarEvent(calendarEventId);
        internalCalendarEventRepository.delete(calendar);
    }

    public InternalCalendarEvent getCalendarEvent(Long calendarEventId) {
        return internalCalendarEventRepository.findById(calendarEventId).orElseThrow(() ->
                new ResourceNotFoundException("InternalCalendarEvent", "id", calendarEventId));
    }
}
