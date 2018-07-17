package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.dto.slide.CalendarSlideCreateDTO;
import com.guidewire.signagecenter.model.dto.slide.CalendarSlideGetDTO;
import com.guidewire.signagecenter.model.slide.CalendarSlide;
import com.guidewire.signagecenter.model.slide.SlideType;
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
        CalendarSlideGetDTO calendarSlideGetDTO = new CalendarSlideGetDTO();
        calendarSlideGetDTO.setId(calendarSlide.getId());
        calendarSlideGetDTO.setName(calendarSlide.getName());
        calendarSlideGetDTO.setSlideType(calendarSlide.getSlideType());
        calendarSlideGetDTO.setDuration(calendarSlide.getDuration());
        calendarSlideGetDTO.setStartDate(calendarSlide.getStartDate());
        calendarSlideGetDTO.setEndDate(calendarSlide.getEndDate());

        return calendarSlideGetDTO;
    }

    @GetMapping("/all")
    public List<CalendarSlide> getAllCalendarSlides() {
        return calendarSlideService.getAll();
    }
}
