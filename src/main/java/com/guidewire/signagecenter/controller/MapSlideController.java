package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.dto.MapSlideCreateDTO;
import com.guidewire.signagecenter.model.slide.MapSlide;
import com.guidewire.signagecenter.service.MapSlideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/slide/map")
public class MapSlideController {

    private static final Logger logger = LoggerFactory.getLogger(MapSlideController.class);

    @Autowired
    private MapSlideService mapSlideService;

    @PostMapping
    public Long createMapSlide(@RequestBody MapSlideCreateDTO mapSlideCreateDTO) {

        MapSlide mapSlide = new MapSlide();
        mapSlide.setName(mapSlideCreateDTO.getName());
        mapSlide.setLatCoord(mapSlideCreateDTO.getLatCoord());
        mapSlide.setLongCoord(mapSlideCreateDTO.getLongCoord());
        mapSlide.setDuration(mapSlideCreateDTO.getDuration());
        mapSlide.setStartDate(mapSlideCreateDTO.getStartDate());
        mapSlide.setEndDate(mapSlideCreateDTO.getEndDate());

        return mapSlideService.createMapSlide(mapSlide, mapSlideCreateDTO.getPlaylistId()).getId();
    }

    @GetMapping("/all")
    public List<MapSlide> getAllMapSlides() {
        return mapSlideService.getAll();
    }
}
