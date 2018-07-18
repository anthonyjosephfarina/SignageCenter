package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.db.calendar.CalendarType;
import com.guidewire.signagecenter.model.db.calendar.InternalCalendarEntity;
import com.guidewire.signagecenter.model.dto.calendar.InternalCalendarCreateDTO;
import com.guidewire.signagecenter.model.dto.calendar.InternalCalendarGetDTO;
import com.guidewire.signagecenter.service.InternalCalendarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/calendar/internal")
public class InternalCalendarController {

    private static final Logger logger = LoggerFactory.getLogger(InternalCalendarController.class);

    @Autowired
    private InternalCalendarService internalCalendarService;

    @GetMapping("/all")
    public List<InternalCalendarGetDTO> getAll() {
        return internalCalendarService.getAll().stream().map(InternalCalendarGetDTO::map).collect(Collectors.toList());
    }

    @PostMapping
    public InternalCalendarGetDTO createInternalCalendar(@RequestBody InternalCalendarCreateDTO internalCalendarCreateDTO) {

        // create image slide
        InternalCalendarEntity internalCalendar = new InternalCalendarEntity();
        internalCalendar.setType(CalendarType.INTERNAL);
        internalCalendar.setName(internalCalendarCreateDTO.getName());
        internalCalendar.setDescription(internalCalendarCreateDTO.getDescription());
        internalCalendar = internalCalendarService.create(internalCalendar, internalCalendarCreateDTO.getOfficeId());

        // convert to dto
        return InternalCalendarGetDTO.map(internalCalendar);
    }

    @DeleteMapping("/{calendarId}")
    public ResponseEntity<?> deleteCalendar(@PathVariable Long calendarId) {
        internalCalendarService.delete(calendarId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
