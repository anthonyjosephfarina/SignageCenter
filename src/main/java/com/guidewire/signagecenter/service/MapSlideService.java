package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.model.db.PlaylistEntity;
import com.guidewire.signagecenter.model.db.slide.MapSlideEntity;
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

    public MapSlideEntity createMapSlide(MapSlideEntity mapSlide, Long playlistId) {

        PlaylistEntity playlistEntity = playlistService.getPlaylist(playlistId);
        mapSlide.setPlaylistEntity(playlistEntity);

        return mapSlideRepository.save(mapSlide);
    }

    public List<MapSlideEntity> getAll() {
        return mapSlideRepository.findAll();
    }
}
