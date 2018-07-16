package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.calendar.InternalCalendarEvent;
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
        InternalCalendarEvent internalCalendarEvent = new InternalCalendarEvent();
        internalCalendarEvent.setName(internalCalendarEventCreateDTO.getName());
        internalCalendarEvent.setDescription(internalCalendarEventCreateDTO.getDescription());
        internalCalendarEvent.setType(internalCalendarEventCreateDTO.getType());
        internalCalendarEvent.setDate(internalCalendarEventCreateDTO.getDate());
        internalCalendarEvent.setTime(internalCalendarEventCreateDTO.getTime());
        internalCalendarEvent = internalCalendarEventService.create(internalCalendarEvent, internalCalendarEventCreateDTO.getCalendarId());

        return CalendarEventGetDTO.map(internalCalendarEvent);
    }

    @DeleteMapping("/{calendarEventId}")
    public ResponseEntity<?> deleteCalendarEvent(@PathVariable Long calendarEventId) {
        internalCalendarEventService.delete(calendarEventId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
