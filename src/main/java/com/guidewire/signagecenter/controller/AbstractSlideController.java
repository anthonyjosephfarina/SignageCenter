package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.mapper.AbstractSlideGetDTOMapper;
import com.guidewire.signagecenter.model.db.slide.AbstractSlideEntity;
import com.guidewire.signagecenter.model.dto.slide.AbstractSlideGetDTO;
import com.guidewire.signagecenter.service.AbstractSlideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/slide")
public class AbstractSlideController {

    private static final Logger logger = LoggerFactory.getLogger(AbstractSlideController.class);

    @Autowired
    private AbstractSlideService abstractSlideService;

    @Autowired
    private AbstractSlideGetDTOMapper slideGetMapper;

    @GetMapping("/playlist/{playlistId}")
    public List<AbstractSlideGetDTO> getAllByPlaylist(@PathVariable Long playlistId,
                                                      @RequestParam(required = false, defaultValue = "false") Boolean activeOnly) {

        if (activeOnly) {
            return abstractSlideService.getAllActiveByPlaylist(playlistId).stream()
                    .map(slideGetMapper::mapToDTO)
                    .collect(Collectors.toList());
        }

        return abstractSlideService.getAllByPlaylist(playlistId).stream()
                .map(slideGetMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<AbstractSlideGetDTO> getAllAbstractSlides() {
        return abstractSlideService.getAll().stream()
                .map(slideGetMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{slideId}")
    public AbstractSlideGetDTO getSlide(@PathVariable Long slideId) {
        AbstractSlideEntity slide = abstractSlideService.getSlide(slideId);
        return slideGetMapper.mapToDTO(slide);
    }

    @DeleteMapping("/{slideId}")
    public ResponseEntity<?> deleteSlide(@PathVariable Long slideId) {
        abstractSlideService.deleteSlide(slideId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
