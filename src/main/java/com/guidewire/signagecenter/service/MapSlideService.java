package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.model.db.PlaylistEntity;
import com.guidewire.signagecenter.model.db.slide.MapSlideEntity;
import com.guidewire.signagecenter.repository.MapSlideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * MapSlideService retrieves the saved MapSlide details and save the newly created MapSlide
 * @author
 */
@Service
public class MapSlideService {
    /**
     * The Logger for MapSlideService.
     */
    private static final Logger logger = LoggerFactory.getLogger(MapSlideService.class);

    /**
     *  Inject MapSlideRepository.
     */
    @Autowired
    private MapSlideRepository mapSlideRepository;

    /**
     *  Inject PlaylistService.
     */
    @Autowired
    private PlaylistService playlistService;

    /**
     * Creates the MapSlideEntity .
     * @param mapSlide <code>MapSlideEntity</code>.
     * @param playlistId <code>Long</code>.
     * @return MapSlideEntity.
     * @throws
     */
    public MapSlideEntity createMapSlide(MapSlideEntity mapSlide, Long playlistId) {

        PlaylistEntity playlistEntity = playlistService.getPlaylist(playlistId);
        mapSlide.setPlaylistEntity(playlistEntity);

        return mapSlideRepository.save(mapSlide);
    }

    /**
     *  Retrieves all  values from the  MapSlideEntity  in database.
     *  @return List<MapSlideEntity> .
     *  @throws
     */
    public List<MapSlideEntity> getAll() {
        return mapSlideRepository.findAll();
    }
}
