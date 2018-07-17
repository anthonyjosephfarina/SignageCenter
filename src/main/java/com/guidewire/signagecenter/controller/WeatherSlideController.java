package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.db.slide.SlideType;
import com.guidewire.signagecenter.model.db.slide.WeatherSlide;
import com.guidewire.signagecenter.model.dto.slide.WeatherSlideCreateDTO;
import com.guidewire.signagecenter.model.dto.slide.WeatherSlideGetDTO;
import com.guidewire.signagecenter.service.WeatherSlideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/slide/weather")
public class WeatherSlideController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherSlideController.class);

    @Autowired
    private WeatherSlideService weatherSlideService;

    @PostMapping
    public WeatherSlideGetDTO createWeatherSlide(@RequestBody WeatherSlideCreateDTO weatherSlideCreateDTO) {

        // create slide
        WeatherSlide weatherSlide = new WeatherSlide();
        weatherSlide.setSlideType(SlideType.WEATHER);
        weatherSlide.setName(weatherSlideCreateDTO.getName());
        weatherSlide.setDuration(weatherSlideCreateDTO.getDuration());
        weatherSlide.setStartDate(weatherSlideCreateDTO.getStartDate());
        weatherSlide.setEndDate(weatherSlideCreateDTO.getEndDate());
        weatherSlide.setCityId(weatherSlideCreateDTO.getCityId());
        weatherSlide = weatherSlideService.createWeatherSlide(weatherSlide, weatherSlideCreateDTO.getPlaylistId());

        // convert to dto
        return WeatherSlideGetDTO.map(weatherSlide);
    }

    @GetMapping("/all")
    public List<WeatherSlideGetDTO> getAllWeatherSlides() {
        return weatherSlideService.getAll().stream().map(WeatherSlideGetDTO::map).collect(Collectors.toList());
    }
}
