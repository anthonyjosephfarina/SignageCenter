package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.exception.ResourceNotFoundException;
import com.guidewire.signagecenter.model.db.Playlist;
import com.guidewire.signagecenter.model.db.slide.AbstractSlide;
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

    public AbstractSlide getSlide(Long slideId) {
        return abstractSlideRepository.findById(slideId).orElseThrow(() ->
                new ResourceNotFoundException("AbstractSlide", "id", slideId));
    }

    public List<AbstractSlide> getAll() {
        return abstractSlideRepository.findAll();
    }

    public List<AbstractSlide> getAllByPlaylist(Long playlistId) {
        Playlist playlist = playlistService.getPlaylist(playlistId);
        return playlist.getSlides();
    }

    public void deleteSlide(Long slideId) {
        AbstractSlide slide = getSlide(slideId);
        abstractSlideRepository.delete(slide);
    }
}
