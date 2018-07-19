package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.exception.ResourceNotFoundException;
import com.guidewire.signagecenter.model.db.calendar.InternalCalendarEntity;
import com.guidewire.signagecenter.model.db.calendar.InternalCalendarEventEntity;
import com.guidewire.signagecenter.repository.InternalCalendarEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * InternalCalendarEventService
 * @author
 */
@Service
public class InternalCalendarEventService {

    /**
     * The Logger for InternalCalendarEventService.
     */
    private static final Logger logger = LoggerFactory.getLogger(InternalCalendarEventService.class);

    /**
     * Inject InternalCalendarEventRepository.
     */
    @Autowired
    private InternalCalendarEventRepository internalCalendarEventRepository;

    /**
     * Inject InternalCalendarService.
     */
    @Autowired
    private InternalCalendarService internalCalendarService;

    /**
     * Creates the InternalCalendarEventEntity .
     * @param calendarEvent <code>InternalCalendarEventEntity</code>.
     * @param calendarId <code>Long</code>.
     * @return InternalCalendarEventEntity.
     * @throws
     */
    public InternalCalendarEventEntity create(InternalCalendarEventEntity calendarEvent, Long calendarId) {

        InternalCalendarEntity internalCalendar = internalCalendarService.getCalendar(calendarId);
        calendarEvent.setCalendar(internalCalendar);

        return internalCalendarEventRepository.save(calendarEvent);
    }

    /**
     * Deletes the InternalCalendarEventEntity value matching calendarId.
     * @param calendarEventId <code>Long</code>.
     * @throws
     */
    public void delete(Long calendarEventId) {
        InternalCalendarEventEntity calendar = getCalendarEvent(calendarEventId);
        internalCalendarEventRepository.delete(calendar);
    }
    /**
     * Retrieve the InternalCalendarEventEntity .
     * @param calendarEventId <code>Long</code>.
     * @return InternalCalendarEventEntity.
     * @throws
     */
    public InternalCalendarEventEntity getCalendarEvent(Long calendarEventId) {
        return internalCalendarEventRepository.findById(calendarEventId).orElseThrow(() ->
                new ResourceNotFoundException("InternalCalendarEventEntity", "id", calendarEventId));
    }
}
