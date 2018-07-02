package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.dto.CalendarSlideCreateDTO;
import com.guidewire.signagecenter.model.slide.CalendarSlide;
import com.guidewire.signagecenter.service.CalendarSlideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slide/calendar")
public class CalendarSlideController {

    private static final Logger logger = LoggerFactory.getLogger(CalendarSlideController.class);

    @Autowired
    private CalendarSlideService calendarSlideService;

    @PostMapping
    public Long createCalendarSlide(@RequestBody CalendarSlideCreateDTO calendarSlideCreateDTO) {

        CalendarSlide calendarSlide = new CalendarSlide();
        calendarSlide.setName(calendarSlideCreateDTO.getName());
        calendarSlide.setDuration(calendarSlideCreateDTO.getDuration());
        calendarSlide.setStartDate(calendarSlideCreateDTO.getStartDate());
        calendarSlide.setEndDate(calendarSlideCreateDTO.getEndDate());

        return calendarSlideService.createCalendarSlide(calendarSlide, calendarSlideCreateDTO.getPlaylistId()).getId();
    }

    @GetMapping("/all")
    public List<CalendarSlide> getAllCalendarSlides() {
        return calendarSlideService.getAll();
    }
}
