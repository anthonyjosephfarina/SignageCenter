package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.db.calendar.InternalCalendarEventEntity;
import com.guidewire.signagecenter.model.dto.calendar.CalendarEventGetDTO;
import com.guidewire.signagecenter.model.dto.calendar.InternalCalendarEventCreateDTO;
import com.guidewire.signagecenter.service.InternalCalendarEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calendar/event/internal")
public class InternalCalendarEventController {

    private static final Logger logger = LoggerFactory.getLogger(InternalCalendarEventController.class);

    @Autowired
    private InternalCalendarEventService internalCalendarEventService;

    @PostMapping
    public CalendarEventGetDTO create(@RequestBody InternalCalendarEventCreateDTO internalCalendarEventCreateDTO) {

        // create internal calendar event
        InternalCalendarEventEntity internalCalendarEventEntity = new InternalCalendarEventEntity();
        internalCalendarEventEntity.setName(internalCalendarEventCreateDTO.getName());
        internalCalendarEventEntity.setDescription(internalCalendarEventCreateDTO.getDescription());
        internalCalendarEventEntity.setType(internalCalendarEventCreateDTO.getType());
        internalCalendarEventEntity.setDate(internalCalendarEventCreateDTO.getDate());
        internalCalendarEventEntity.setAllDay(internalCalendarEventCreateDTO.isAllDay());
        internalCalendarEventEntity = internalCalendarEventService.create(internalCalendarEventEntity, internalCalendarEventCreateDTO.getCalendarId());

        internalCalendarEventService.addCreateMessage(internalCalendarEventEntity);

        return CalendarEventGetDTO.map(internalCalendarEventEntity);
    }

    @DeleteMapping("/{calendarEventId}")
    public ResponseEntity<?> deleteCalendarEvent(@PathVariable Long calendarEventId) {
        // need entity for message
        InternalCalendarEventEntity internalCalendarEventEntity = internalCalendarEventService.getCalendarEvent(calendarEventId);

        // delete the entity
        internalCalendarEventService.delete(internalCalendarEventEntity);

        // create new message
        internalCalendarEventService.addDeleteMessage(internalCalendarEventEntity);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
