package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.dto.WeatherSlideCreateDTO;
import com.guidewire.signagecenter.model.slide.WeatherSlide;
import com.guidewire.signagecenter.service.WeatherSlideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slide/weather")
public class WeatherSlideController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherSlideController.class);

    @Autowired
    private WeatherSlideService weatherSlideService;

    @PostMapping
    public Long createWeatherSlide(@RequestBody WeatherSlideCreateDTO weatherSlideCreateDTO) {

        WeatherSlide weatherSlide = new WeatherSlide();
        weatherSlide.setName(weatherSlideCreateDTO.getName());
        weatherSlide.setDuration(weatherSlideCreateDTO.getDuration());
        weatherSlide.setStartDate(weatherSlideCreateDTO.getStartDate());
        weatherSlide.setEndDate(weatherSlideCreateDTO.getEndDate());
        weatherSlide.setCityId(weatherSlideCreateDTO.getCityId());

        return weatherSlideService.createWeatherSlide(weatherSlide, weatherSlideCreateDTO.getPlaylistId()).getId();
    }

    @GetMapping("/all")
    public List<WeatherSlide> getAllWeatherSlides() {
        return weatherSlideService.getAll();
    }
}
