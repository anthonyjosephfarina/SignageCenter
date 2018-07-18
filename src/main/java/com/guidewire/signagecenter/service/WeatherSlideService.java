package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.model.db.PlaylistEntity;
import com.guidewire.signagecenter.model.db.slide.WeatherSlideEntity;
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

    public WeatherSlideEntity createWeatherSlide(WeatherSlideEntity weatherSlide, Long playlistId) {

        PlaylistEntity playlistEntity = playlistService.getPlaylist(playlistId);
        weatherSlide.setPlaylistEntity(playlistEntity);

        return weatherSlideRepository.save(weatherSlide);
    }

    public List<WeatherSlideEntity> getAll() {
        return weatherSlideRepository.findAll();
    }
}
