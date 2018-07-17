package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.db.slide.CalendarSlide;
import com.guidewire.signagecenter.model.db.slide.SlideType;
import com.guidewire.signagecenter.model.dto.slide.CalendarSlideCreateDTO;
import com.guidewire.signagecenter.model.dto.slide.CalendarSlideGetDTO;
import com.guidewire.signagecenter.service.CalendarSlideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/slide/calendar")
public class CalendarSlideController {

    private static final Logger logger = LoggerFactory.getLogger(CalendarSlideController.class);

    @Autowired
    private CalendarSlideService calendarSlideService;

    @PostMapping
    public CalendarSlideGetDTO createCalendarSlide(@RequestBody CalendarSlideCreateDTO calendarSlideCreateDTO) {

        // create slide
        CalendarSlide calendarSlide = new CalendarSlide();
        calendarSlide.setSlideType(SlideType.CALENDAR);
        calendarSlide.setName(calendarSlideCreateDTO.getName());
        calendarSlide.setDuration(calendarSlideCreateDTO.getDuration());
        calendarSlide.setStartDate(calendarSlideCreateDTO.getStartDate());
        calendarSlide.setEndDate(calendarSlideCreateDTO.getEndDate());
        calendarSlide = calendarSlideService.createCalendarSlide(calendarSlide,
                calendarSlideCreateDTO.getPlaylistId(), calendarSlideCreateDTO.getCalendarIds());

        // convert to dto
        return CalendarSlideGetDTO.map(calendarSlide);
    }

    @GetMapping("/all")
    public List<CalendarSlideGetDTO> getAllCalendarSlides() {
        return calendarSlideService.getAll().stream().map(CalendarSlideGetDTO::map).collect(Collectors.toList());
    }
}
