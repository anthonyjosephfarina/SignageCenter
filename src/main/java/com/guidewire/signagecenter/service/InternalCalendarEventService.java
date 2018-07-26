package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.exception.ResourceNotFoundException;
import com.guidewire.signagecenter.messaging.MessageType;
import com.guidewire.signagecenter.messaging.MessagingService;
import com.guidewire.signagecenter.messaging.payload.InternalCalendarEventMessage;
import com.guidewire.signagecenter.model.db.calendar.AbstractCalendarEntity;
import com.guidewire.signagecenter.model.db.calendar.InternalCalendarEntity;
import com.guidewire.signagecenter.model.db.calendar.InternalCalendarEventEntity;
import com.guidewire.signagecenter.repository.InternalCalendarEventRepository;
import com.guidewire.signagecenter.repository.PlaylistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * InternalCalendarEventService
 * @author
 */
@Primary
@Service
public class InternalCalendarEventService extends MessagingService {

    /**
     * The Logger for InternalCalendarEventService.
     */
    private static final Logger logger = LoggerFactory.getLogger(InternalCalendarEventService.class);

    /**
     * Inject InternalCalendarEventRepository.
     */
    @Autowired
    private InternalCalendarEventRepository internalCalendarEventRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

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
        delete(getCalendarEvent(calendarEventId));
    }

    /**
     * Deletes the InternalCalendarEventEntity from the repository
     *
     * @param internalCalendarEventEntity
     */
    public void delete(InternalCalendarEventEntity internalCalendarEventEntity) {
        internalCalendarEventRepository.delete(internalCalendarEventEntity);
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

    public void addCreateMessage(InternalCalendarEventEntity internalCalendarEvent) {
        AbstractCalendarEntity abstractCalendarEntity = internalCalendarEvent.getCalendar();

        for (BigInteger playlistId : playlistRepository.findDistinctByCalendarId(abstractCalendarEntity.getId())) {
            String destination = "/topic/playlist-" + playlistId.toString();

            InternalCalendarEventMessage payload = new InternalCalendarEventMessage();
            payload.setId(internalCalendarEvent.getId());
            payload.setMessageType(MessageType.CALENDAR_EVENT_ADD);

            this.sendMessage(destination, payload);
        }
    }

    public void addUpdateMessage(InternalCalendarEventEntity internalCalendarEvent) {
        AbstractCalendarEntity abstractCalendarEntity = internalCalendarEvent.getCalendar();

        for (BigInteger playlistId : playlistRepository.findDistinctByCalendarId(abstractCalendarEntity.getId())) {
            String destination = "/topic/playlist-" + playlistId.toString();

            InternalCalendarEventMessage payload = new InternalCalendarEventMessage();
            payload.setId(internalCalendarEvent.getId());
            payload.setMessageType(MessageType.CALENDAR_EVENT_UPDATE);

            this.sendMessage(destination, payload);
        }
    }

    public void addDeleteMessage(InternalCalendarEventEntity internalCalendarEvent) {
        AbstractCalendarEntity abstractCalendarEntity = internalCalendarEvent.getCalendar();

        for (BigInteger playlistId : playlistRepository.findDistinctByCalendarId(abstractCalendarEntity.getId())) {
            String destination = "/topic/playlist-" + playlistId.toString();

            InternalCalendarEventMessage payload = new InternalCalendarEventMessage();
            payload.setId(internalCalendarEvent.getId());
            payload.setMessageType(MessageType.CALENDAR_EVENT_DELETE);

            this.sendMessage(destination, payload);
        }
    }

}
