package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.db.slide.MapSlide;
import com.guidewire.signagecenter.model.db.slide.SlideType;
import com.guidewire.signagecenter.model.dto.slide.MapSlideCreateDTO;
import com.guidewire.signagecenter.model.dto.slide.MapSlideGetDTO;
import com.guidewire.signagecenter.service.MapSlideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        mapSlide.setAddress(mapSlideCreateDTO.getAddress());
        mapSlide.setDuration(mapSlideCreateDTO.getDuration());
        mapSlide.setStartDate(mapSlideCreateDTO.getStartDate());
        mapSlide.setEndDate(mapSlideCreateDTO.getEndDate());
        mapSlide = mapSlideService.createMapSlide(mapSlide, mapSlideCreateDTO.getPlaylistId());

        // convert to dto
        return MapSlideGetDTO.map(mapSlide);
    }

    @GetMapping("/all")
    public List<MapSlideGetDTO> getAllMapSlides() {
        return mapSlideService.getAll().stream().map(MapSlideGetDTO::map).collect(Collectors.toList());
    }
}
