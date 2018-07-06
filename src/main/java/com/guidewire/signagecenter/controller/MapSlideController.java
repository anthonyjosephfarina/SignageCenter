package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.dto.MapSlideCreateDTO;
import com.guidewire.signagecenter.model.dto.slide.MapSlideGetDTO;
import com.guidewire.signagecenter.model.slide.MapSlide;
import com.guidewire.signagecenter.model.slide.SlideType;
import com.guidewire.signagecenter.service.MapSlideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slide/map")
public class MapSlideController {

    private static final Logger logger = LoggerFactory.getLogger(MapSlideController.class);

    @Autowired
    private MapSlideService mapSlideService;

    @PostMapping
    public MapSlideGetDTO createMapSlide(@RequestBody MapSlideCreateDTO mapSlideCreateDTO) {

        // create slide
        MapSlide mapSlide = new MapSlide();
        mapSlide.setSlideType(SlideType.MAP);
        mapSlide.setName(mapSlideCreateDTO.getName());
        mapSlide.setLatCoord(mapSlideCreateDTO.getLatCoord());
        mapSlide.setLongCoord(mapSlideCreateDTO.getLongCoord());
        mapSlide.setDuration(mapSlideCreateDTO.getDuration());
        mapSlide.setStartDate(mapSlideCreateDTO.getStartDate());
        mapSlide.setEndDate(mapSlideCreateDTO.getEndDate());
        mapSlide = mapSlideService.createMapSlide(mapSlide, mapSlideCreateDTO.getPlaylistId());

        // convert to dto
        MapSlideGetDTO mapSlideGetDTO = new MapSlideGetDTO();
        mapSlideGetDTO.setId(mapSlide.getId());
        mapSlideGetDTO.setName(mapSlide.getName());
        mapSlideGetDTO.setLatCoord(mapSlide.getLatCoord());
        mapSlideGetDTO.setLongCoord(mapSlide.getLongCoord());
        mapSlideGetDTO.setSlideType(mapSlide.getSlideType());
        mapSlideGetDTO.setDuration(mapSlide.getDuration());
        mapSlideGetDTO.setStartDate(mapSlide.getStartDate());
        mapSlideGetDTO.setEndDate(mapSlide.getEndDate());

        return mapSlideGetDTO;
    }

    @GetMapping("/all")
    public List<MapSlide> getAllMapSlides() {
        return mapSlideService.getAll();
    }
}
