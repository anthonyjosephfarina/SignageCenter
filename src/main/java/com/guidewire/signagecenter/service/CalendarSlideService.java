package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.model.db.PlaylistEntity;
import com.guidewire.signagecenter.model.db.calendar.AbstractCalendarEntity;
import com.guidewire.signagecenter.model.db.slide.CalendarSlideEntity;
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

    public CalendarSlideEntity createCalendarSlide(CalendarSlideEntity calendarSlide, Long playlistId, List<Long> calendarIds) {

        PlaylistEntity playlistEntity = playlistService.getPlaylist(playlistId);
        calendarSlide.setPlaylistEntity(playlistEntity);

        List<AbstractCalendarEntity> calendars = calendarIds.stream()
                .map(id -> abstractCalendarService.getCalendar(id)).collect(Collectors.toList());
        calendarSlide.setCalendars(calendars);

        return calendarSlideRepository.save(calendarSlide);
    }

    public List<CalendarSlideEntity> getAll() {
        return calendarSlideRepository.findAll();
    }
}
