package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.exception.ResourceNotFoundException;
import com.guidewire.signagecenter.model.Playlist;
import com.guidewire.signagecenter.model.slide.CalendarSlide;
import com.guidewire.signagecenter.repository.CalendarSlideRepository;
import com.guidewire.signagecenter.repository.PlaylistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarSlideService {

    private static final Logger logger = LoggerFactory.getLogger(CalendarSlideService.class);

    @Autowired
    private CalendarSlideRepository calendarSlideRepository;
    @Autowired
    private PlaylistService playlistService;

    public CalendarSlide createCalendarSlide(CalendarSlide calendarSlide, Long playlistId) {

        Playlist playlist = playlistService.getPlaylist(playlistId);
        calendarSlide.setPlaylist(playlist);

        return calendarSlideRepository.save(calendarSlide);
    }

    public List<CalendarSlide> getAll() {
        return calendarSlideRepository.findAll();
    }
}
