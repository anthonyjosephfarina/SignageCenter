package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.db.slide.AbstractSlide;
import com.guidewire.signagecenter.service.AbstractSlideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slide")
public class AbstractSlideController {

    private static final Logger logger = LoggerFactory.getLogger(AbstractSlideController.class);

    @Autowired
    private AbstractSlideService abstractSlideService;

    @GetMapping("/playlist/{playlistId}")
    public List<AbstractSlide> getAllByPlaylist(@PathVariable Long playlistId) {
        return abstractSlideService.getAllByPlaylist(playlistId);
    }

    @GetMapping("/all")
    public List<AbstractSlide> getAllAbstractSlides() {
        return abstractSlideService.getAll();
    }

    @DeleteMapping("/{slideId}")
    public ResponseEntity<?> deleteSlide(@PathVariable Long slideId) {
        abstractSlideService.deleteSlide(slideId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
