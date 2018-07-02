package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.model.Playlist;
import com.guidewire.signagecenter.model.slide.WeatherSlide;
import com.guidewire.signagecenter.repository.WeatherSlideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherSlideService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherSlideService.class);

    @Autowired
    private WeatherSlideRepository weatherSlideRepository;

    @Autowired
    private PlaylistService playlistService;

    public WeatherSlide createWeatherSlide(WeatherSlide weatherSlide, Long playlistId) {

        Playlist playlist = playlistService.getPlaylist(playlistId);
        weatherSlide.setPlaylist(playlist);

        return weatherSlideRepository.save(weatherSlide);
    }

    public List<WeatherSlide> getAll() {
        return weatherSlideRepository.findAll();
    }
}
