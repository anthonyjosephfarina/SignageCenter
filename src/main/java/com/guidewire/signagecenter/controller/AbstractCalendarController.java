package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.calendar.AbstractCalendar;
import com.guidewire.signagecenter.model.calendar.CalendarType;
import com.guidewire.signagecenter.model.calendar.InternalCalendar;
import com.guidewire.signagecenter.model.dto.calendar.AbstractCalendarGetDTO;
import com.guidewire.signagecenter.model.dto.calendar.InternalCalendarGetDTO;
import com.guidewire.signagecenter.service.AbstractCalendarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/calendar")
public class AbstractCalendarController {

    private static final Logger logger = LoggerFactory.getLogger(AbstractCalendarController.class);

    @Autowired
    private AbstractCalendarService abstractCalendarService;

    @GetMapping("/all")
    public List<AbstractCalendarGetDTO> getAll() {
        return abstractCalendarService.getAll().stream().map(cal -> {
            if (cal.getType() == CalendarType.INTERNAL) {
                return InternalCalendarGetDTO.map(((InternalCalendar) cal));
            }
            return new InternalCalendarGetDTO();
        }).collect(Collectors.toList());
    }

    @GetMapping("/{calendarId}")
    public AbstractCalendarGetDTO getCalendar(@PathVariable Long calendarId) {
        AbstractCalendar calendar = abstractCalendarService.getCalendar(calendarId);

        if (calendar.getType() == CalendarType.INTERNAL) {
            return InternalCalendarGetDTO.map((InternalCalendar) calendar);
        }

        return null;
    }

    @DeleteMapping("/{calendarId}")
    public ResponseEntity<?> deleteCalendar(@PathVariable Long calendarId) {
        abstractCalendarService.deleteCalendar(calendarId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
