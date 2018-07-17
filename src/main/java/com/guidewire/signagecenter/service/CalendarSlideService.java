package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.model.Playlist;
import com.guidewire.signagecenter.model.calendar.AbstractCalendar;
import com.guidewire.signagecenter.model.slide.CalendarSlide;
import com.guidewire.signagecenter.repository.CalendarSlideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarSlideService {

    private static final Logger logger = LoggerFactory.getLogger(CalendarSlideService.class);

    @Autowired
    private CalendarSlideRepository calendarSlideRepository;

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private AbstractCalendarService abstractCalendarService;

    public CalendarSlide createCalendarSlide(CalendarSlide calendarSlide, Long playlistId, List<Long> calendarIds) {

        Playlist playlist = playlistService.getPlaylist(playlistId);
        calendarSlide.setPlaylist(playlist);

        List<AbstractCalendar> calendars = calendarIds.stream()
                .map(id -> abstractCalendarService.getCalendar(id)).collect(Collectors.toList());
        calendarSlide.setCalendars(calendars);

        return calendarSlideRepository.save(calendarSlide);
    }

    public List<CalendarSlide> getAll() {
        return calendarSlideRepository.findAll();
    }
}
