package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.exception.ResourceNotFoundException;
import com.guidewire.signagecenter.model.db.PlaylistEntity;
import com.guidewire.signagecenter.model.db.slide.AbstractSlideEntity;
import com.guidewire.signagecenter.repository.AbstractSlideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbstractSlideService {

    private static final Logger logger = LoggerFactory.getLogger(AbstractSlideService.class);

    @Autowired
    private AbstractSlideRepository abstractSlideRepository;

    @Autowired
    private PlaylistService playlistService;

    public AbstractSlideEntity getSlide(Long slideId) {
        return abstractSlideRepository.findById(slideId).orElseThrow(() ->
                new ResourceNotFoundException("AbstractSlideEntity", "id", slideId));
    }

    public List<AbstractSlideEntity> getAll() {
        return abstractSlideRepository.findAll();
    }

    public List<AbstractSlideEntity> getAllByPlaylist(Long playlistId) {
        PlaylistEntity playlistEntity = playlistService.getPlaylist(playlistId);
        return playlistEntity.getSlides();
    }

    public void deleteSlide(Long slideId) {
        AbstractSlideEntity slide = getSlide(slideId);
        abstractSlideRepository.delete(slide);
    }
}
