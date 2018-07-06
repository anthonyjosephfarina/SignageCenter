package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.dto.WeatherSlideCreateDTO;
import com.guidewire.signagecenter.model.dto.slide.WeatherSlideGetDTO;
import com.guidewire.signagecenter.model.slide.SlideType;
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
        WeatherSlideGetDTO weatherSlideGetDTO = new WeatherSlideGetDTO();
        weatherSlideGetDTO.setId(weatherSlide.getId());
        weatherSlideGetDTO.setName(weatherSlide.getName());
        weatherSlideGetDTO.setCityId(weatherSlide.getCityId());
        weatherSlideGetDTO.setSlideType(weatherSlide.getSlideType());
        weatherSlideGetDTO.setDuration(weatherSlide.getDuration());
        weatherSlideGetDTO.setStartDate(weatherSlide.getStartDate());
        weatherSlideGetDTO.setEndDate(weatherSlide.getEndDate());

        return weatherSlideGetDTO;
    }

    @GetMapping("/all")
    public List<WeatherSlide> getAllWeatherSlides() {
        return weatherSlideService.getAll();
    }
}
