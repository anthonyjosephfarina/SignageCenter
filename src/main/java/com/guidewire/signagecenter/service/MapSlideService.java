package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.model.db.Playlist;
import com.guidewire.signagecenter.model.db.slide.MapSlide;
import com.guidewire.signagecenter.repository.MapSlideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapSlideService {

    private static final Logger logger = LoggerFactory.getLogger(MapSlideService.class);

    @Autowired
    private MapSlideRepository mapSlideRepository;

    @Autowired
    private PlaylistService playlistService;

    public MapSlide createMapSlide(MapSlide mapSlide, Long playlistId) {

        Playlist playlist = playlistService.getPlaylist(playlistId);
        mapSlide.setPlaylist(playlist);

        return mapSlideRepository.save(mapSlide);
    }

    public List<MapSlide> getAll() {
        return mapSlideRepository.findAll();
    }
}
