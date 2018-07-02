package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.model.slide.AbstractSlide;
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

    public List<AbstractSlide> getAll() {
        return abstractSlideRepository.findAll();
    }

    public List<AbstractSlide> getAllByPlaylist(Long playlistId) {
        return abstractSlideRepository.findByPlaylistId(playlistId);
    }
}
