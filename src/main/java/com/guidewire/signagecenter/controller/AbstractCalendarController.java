package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.calendar.AbstractCalendar;
import com.guidewire.signagecenter.service.AbstractCalendarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendar")
public class AbstractCalendarController {

    private static final Logger logger = LoggerFactory.getLogger(AbstractCalendarController.class);

    @Autowired
    private AbstractCalendarService abstractCalendarService;

    @GetMapping("/all")
    public List<AbstractCalendar> getAllAbstractCalendars() {
        return abstractCalendarService.getAll();
    }

    @DeleteMapping("/{calendarId}")
    public ResponseEntity<?> deleteCalendar(@PathVariable Long calendarId) {
        abstractCalendarService.deleteCalendar(calendarId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
